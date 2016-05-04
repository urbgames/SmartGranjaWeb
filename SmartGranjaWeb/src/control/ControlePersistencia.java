package control;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import objects.Delay;
import objects.LeituraSensores;
import objects.RelatorioDiario;

import model.LeituraSensoresDAO;
import model.RelatorioDiarioDAO;
import model.ArduinoDAO;

public class ControlePersistencia implements Observer {

	private int quantidade = 0;
	private int delay = 10; // Considerando o sleep do arduino de 1 seg o delay
							// = 10 seg
	private LeituraSensoresDAO leituraDAO;
	private RelatorioDiarioDAO relatorioDAO;
	private ArduinoDAO arduinoDAO;
	private String inputLine = "0/0/0";

	public ControlePersistencia() {
		this.leituraDAO = new LeituraSensoresDAO();
		this.relatorioDAO = new RelatorioDiarioDAO();
		this.arduinoDAO = new ArduinoDAO("COM5", 9600);
		this.arduinoDAO.addObserver(this);
	}

	public void setDelay(Delay delay){
		this.delay=delay.getValor();
	}
	
	public void atualizarRelatorio(RelatorioDiario relatorio) {
		relatorioDAO.atualizarRelatorio(relatorio);
	}

	public String leituraSensorTexto() {
		return arduinoDAO.getInputLine();
	}

	public List<RelatorioDiario> getTodosRelatoriosDiarios() {

		Vector<RelatorioDiario> relatorioDiarios = relatorioDAO
				.listarRelatorioDiarioVector();
		return relatorioDiarios.subList(0, relatorioDiarios.size());

	}

	public LeituraSensores getleituraSensores() {

		String[] entradas = inputLine.split("/");
		LeituraSensores leituraSensor = new LeituraSensores();

		leituraSensor.setUmidade(Float.parseFloat(entradas[0]));
		leituraSensor.setTemperatura(Float.parseFloat(entradas[1]));
		leituraSensor.setLuminosidade(Float.parseFloat(entradas[2]));

		return leituraSensor;
	}

	public void persistirDados(String inputLine) {

		quantidade++;
		System.out.println(quantidade);
		String[] entradas = inputLine.split("/");
		LeituraSensores leitura = new LeituraSensores();

		leitura.setUmidade(Float.parseFloat(entradas[0]));
		leitura.setTemperatura(Float.parseFloat(entradas[1]));
		leitura.setLuminosidade(Float.parseFloat(entradas[2]));
		// Mostra os resultados na tela

		if (quantidade >= delay) {

			RelatorioDiario relatorio = relatorioDAO.getById(1);
			Date time = new Date(System.currentTimeMillis());

			// Preenche o restante dos dados de "leitura" necessários para a
			// persistencia no BD
			leitura.setInstante(time);
			leitura.setRelatorio(relatorio);

			leituraDAO.inserirLeitura(leitura);
			quantidade = 0;

			System.out.print("Umidade: " + entradas[0] + "\t");
			System.out.print("Temperatura: " + entradas[1] + "\t");
			System.out.println("Luminosidade: " + entradas[2]);

		}
	}

	public void update(Observable arg0, Object arg1) {
		persistirDados(arduinoDAO.getInputLine());
		inputLine = arduinoDAO.getInputLine();

	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

}