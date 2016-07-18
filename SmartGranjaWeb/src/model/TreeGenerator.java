package model;

import java.util.Random;

import objects.LeituraSensores;
import objects.Tree;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class TreeGenerator {

	private LeituraSensoresDAO leituraDAO = new LeituraSensoresDAO();
	private Instances data;
	private Tree tree;

	private void criarLeituras(int quantidade) {

		for (int i = 0; i < quantidade; i++) {
			LeituraSensores leitura = new LeituraSensores();
			leituraDAO.inserirLeitura(leitura);
		}
	}

	public Tree gerarArvore () throws Exception {

		loadDataBase();
		
		String[] options = new String[2];
		options[0] = "-R";
		options[1] = "1";
		Remove remove = new Remove();
		remove.setOptions(options); 
		remove.setInputFormat(data);

		Instances newData = Filter.useFilter(data, remove);
		newData.setClassIndex(newData.numAttributes() - 1);

		J48 arvore = new J48();
		arvore.setUnpruned(false);
		arvore.buildClassifier(newData);

		Evaluation eval = new Evaluation(newData);
		eval.crossValidateModel(arvore, newData, 10, new Random(1));

		System.out.println("Taxa de erro: " + eval.errorRate());
		System.out.println("Revision: " + eval.getRevision());
		System.out.println("Kappa: " + eval.kappa());
		System.out.println("KB information: " + eval.KBInformation());
		System.out.println("KB mean information: " + eval.KBMeanInformation());
		System.out.println("KB relative information: " + eval.KBRelativeInformation());
		System.out.println("Mean absolute error: " + eval.meanAbsoluteError());
		System.out.println("Root prior square error: " + eval.rootMeanPriorSquaredError());
		System.out.println("to class detail: " + eval.toClassDetailsString());
		System.out.println("Cumulative Margin Distribuition: " + eval.toCumulativeMarginDistributionString());
		System.out.println("Matriz: " + eval.toMatrixString());
		System.out.println("Summary: " + eval.toSummaryString());
		System.out.println("Weighted area under PRC: " + eval.weightedAreaUnderROC());
		System.out.println("Weighted f-measure: " + eval.weightedFMeasure());
		System.out.println("Arvore: " + arvore.toString());
		System.out.println(arvore.graph());
		
		tree = new Tree();
		tree.setErrorRate(""+eval.errorRate());
		tree.setRevision(eval.getRevision());
		tree.setKappa(""+eval.kappa());
		tree.setkBInformation(""+eval.KBInformation());
		tree.setKbRelativeInformation(""+eval.KBRelativeInformation());
		tree.setkBMeanInformation(""+eval.KBMeanInformation());
		tree.setMeanAbsoluteError(""+eval.meanAbsoluteError());
		tree.setRootMeanPriorSquaredError(""+eval.rootMeanPriorSquaredError());
		tree.setClassDetails(eval.toClassDetailsString());
		tree.setCumulativeMarginDistribution(eval.toCumulativeMarginDistributionString());
		tree.setMatrix(eval.toMatrixString());
		tree.setSummary(eval.toSummaryString());
		tree.setWeightedAreaUnderROC(""+eval.weightedAreaUnderROC());
		tree.setWeightedFMeasure(""+eval.weightedFMeasure());
		tree.setTree(arvore.toString());
		tree.setTreeGraph(arvore.graph());
		
		
		return tree;

	}
	
	private void loadDataBase() throws Exception{
		
		InstanceQuery query = new InstanceQuery();
		query.setUsername("root");
		query.setPassword("");
		query.setQuery("select * from leitura_sensores_experimento");
		data = query.retrieveInstances();
		
	}

}
