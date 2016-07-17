package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

import org.inra.qualscape.wekatexttoxml.WekaTextfileToXMLTextfile;

import objects.LeituraSensores;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class TreeGenerator {

	LeituraSensoresDAO leituraDAO = new LeituraSensoresDAO();

	public void criarLeituras(int quantidade) {

		for (int i = 0; i < quantidade; i++) {
			LeituraSensores leitura = new LeituraSensores();
			leituraDAO.inserirLeitura(leitura);
		}
	}

	public String gerarArvore () throws Exception {

		TreeGenerator experimento = new TreeGenerator();
		// Desmarcar essa linha para inserir as leituras no banco
		//experimento.criarLeituras(450);

		// Pegando os dados do banco (pode usar hibernate tbm)
		InstanceQuery query = new InstanceQuery();
		query.setUsername("root");
		query.setPassword("");
		query.setQuery("select * from leitura_sensores_experimento");
		Instances data = query.retrieveInstances();

		// opções (filtra os dados)
		String[] options = new String[2];
		options[0] = "-R"; // "range"
		options[1] = "1"; // first attribute
		Remove remove = new Remove(); // new instance of filter
		remove.setOptions(options); // set options
		remove.setInputFormat(data); // inform filter about dataset **AFTER**
										// setting options
		Instances newData = Filter.useFilter(data, remove); // apply filter

		// ???
		newData.setClassIndex(newData.numAttributes() - 1);

		// Instancia a árvore
		J48 arvore = new J48();

		// Usar poda
		arvore.setUnpruned(false);

		// Cria classificador
		arvore.buildClassifier(newData);
		System.out.println(arvore);

		// Validação cruzada
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
		// System.out.println("Cumulative Margin Distribuition: " +
		// eval.toCumulativeMarginDistributionString());
		System.out.println("Matriz: " + eval.toMatrixString());
		System.out.println("Summary: " + eval.toSummaryString());// Importante
		System.out.println("Weighted area under PRC: " + eval.weightedAreaUnderROC());
		System.out.println("Weighted f-measure: " + eval.weightedFMeasure());

		// Criar xml
		BufferedWriter writer = new BufferedWriter(new FileWriter("E:/granjaTree/arvoreXML.xml"));
		writer.write(""); // salva fisicamente
		writer.close();

		BufferedWriter writer2 = new BufferedWriter(new FileWriter("E:/granjaTree/arvoreTXT.txt"));

		BufferedWriter writerTree = new BufferedWriter(new FileWriter("E:/granjaTree/tree.arff"));
		writerTree.write(newData.toString());
		writerTree.newLine();
		writerTree.flush();
		writerTree.close();

		String stgArvore = arvore.toString();
		stgArvore = stgArvore.substring(35, stgArvore.length());
		//stgArvore += eval.toSummaryString();
		System.out.println("aqui: " + stgArvore);
		writer2.write(stgArvore); // salva fisicamente
		writer2.close();

		File arvoreTXT = new File("E:/granjaTree/arvoreTXT.txt");
		File arvoreXML = new File("E:/granjaTree/arvoreXML.xml");

		WekaTextfileToXMLTextfile toXML = new WekaTextfileToXMLTextfile(arvoreTXT, arvoreXML, true, true);
		System.out.println(toXML.writeXmlFromWekaText());

		System.out.println(arvore.graph());
		return arvore.graph();

		// System.out.println(toXML.writeXmlFromWekaText());

		// System.out.println("Lista: ");
		// List list = eval.getMetricsToDisplay();
		// for (Object object : list) {
		// System.out.println(object);
		// }

		// Avaliação
		// System.out.println("Avaliacao inicial: \n");
		// Evaluation avaliacao = new Evaluation(newData);
		// avaliacao.evaluateModel(arvore, newData);
		// System.out.println("Instancias corretas: " + avaliacao.correct() +
		// "\n");

		// double[][] matriz = avaliacao.confusionMatrix();
		// for (int i = 0; i < matriz.length; i++) {
		// System.out.println("\n");
		// for (int j = 0; j < matriz.length; j++) {
		// System.out.print(matriz[i][j]);
		// System.out.print(" ");
		// }
		// }
		//

	}

}
