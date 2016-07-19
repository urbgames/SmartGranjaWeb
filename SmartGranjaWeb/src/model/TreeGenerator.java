package model;

import java.util.ArrayList;
import java.util.Random;

import objects.LeituraSensores;
import objects.Tree;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class TreeGenerator {

	private LeituraSensoresDAO leituraDAO = new LeituraSensoresDAO();
	private Instances data;
	private Tree tree;
	private J48 arvore;
	private Instance inst_co;

	private void criarLeituras(int quantidade) {

		for (int i = 0; i < quantidade; i++) {
			LeituraSensores leitura = new LeituraSensores();
			leituraDAO.inserirLeitura(leitura);
		}
	}

	public String classificarDados(LeituraSensores leituraSensores) throws Exception {

		gerarArvore();
		ArrayList<Attribute> attributeList = new ArrayList<Attribute>(2);

		Attribute luminosidade = new Attribute("luminosidade");
		Attribute temperatura = new Attribute("temperatura");
		Attribute umidade = new Attribute("umidade");

		ArrayList<String> classVal = new ArrayList<String>();
		classVal.add("baixa");
		classVal.add("media");
		classVal.add("alta");

		attributeList.add(luminosidade);
		attributeList.add(temperatura);
		attributeList.add(umidade);
		attributeList.add(new Attribute("@@class@@", classVal));

		Instances data = new Instances("TestInstances", attributeList, 4);

		inst_co = new DenseInstance(data.numAttributes());
		data.add(inst_co);
			
		System.out.println(leituraSensores.getTemperatura());
		
		inst_co.setValue(luminosidade, leituraSensores.getLuminosidade());
		inst_co.setValue(temperatura, leituraSensores.getTemperatura());
		inst_co.setValue(umidade, leituraSensores.getUmidade());
		data.setClassIndex(data.numAttributes() - 1);
		inst_co.setDataset(data);

		return "" + arvore.classifyInstance(inst_co);

	}

	public Tree gerarArvore() throws Exception {

		loadDataBase();

		String[] options = new String[2];
		options[0] = "-R";
		options[1] = "1";
		Remove remove = new Remove();
		remove.setOptions(options);
		remove.setInputFormat(data);

		Instances newData = Filter.useFilter(data, remove);
		newData.setClassIndex(newData.numAttributes() - 1);

		arvore = new J48();
		arvore.setUnpruned(false);
		arvore.buildClassifier(newData);

		Evaluation eval = new Evaluation(newData);
		eval.crossValidateModel(arvore, newData, 10, new Random(1));

		tree = new Tree();
		tree.setErrorRate("" + eval.errorRate());
		tree.setRevision(eval.getRevision());
		tree.setKappa("" + eval.kappa());
		tree.setkBInformation("" + eval.KBInformation());
		tree.setKbRelativeInformation("" + eval.KBRelativeInformation());
		tree.setkBMeanInformation("" + eval.KBMeanInformation());
		tree.setMeanAbsoluteError("" + eval.meanAbsoluteError());
		tree.setRootMeanPriorSquaredError("" + eval.rootMeanPriorSquaredError());
		tree.setClassDetails(eval.toClassDetailsString());
		tree.setCumulativeMarginDistribution(eval.toCumulativeMarginDistributionString());
		tree.setMatrix(eval.toMatrixString());
		tree.setSummary(eval.toSummaryString());
		tree.setWeightedAreaUnderROC("" + eval.weightedAreaUnderROC());
		tree.setWeightedFMeasure("" + eval.weightedFMeasure());
		tree.setTree(arvore.toString());
		tree.setTreeGraph(arvore.graph());

		return tree;

	}

	private void loadDataBase() throws Exception {

		InstanceQuery query = new InstanceQuery();
		query.setUsername("root");
		query.setPassword("");
		query.setQuery("select * from leitura_sensores_experimento");
		data = query.retrieveInstances();

	}

}
