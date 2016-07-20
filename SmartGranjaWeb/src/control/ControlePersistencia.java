package control;

import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;
import model.ArduinoDAO;
import model.LeituraSensoresDAO;
import model.RelatorioDiarioDAO;
import objects.Delay;
import objects.LeituraSensores;
import objects.ListaLeituraSensores;
import objects.RelatorioDiario;

public class ControlePersistencia implements Observer {

	private int quantidade = 0;
	private int delay = 10;
	private LeituraSensoresDAO leituraDAO;
	private RelatorioDiarioDAO relatorioDAO;
	private ArduinoDAO arduinoDAO;
	private String inputLine = "0/0/0";

	public ControlePersistencia() {
		this.leituraDAO = new LeituraSensoresDAO();
		this.relatorioDAO = new RelatorioDiarioDAO();
		this.arduinoDAO = new ArduinoDAO("COM4", 9600);
		this.arduinoDAO.addObserver(this);
	}

	public void setDelay(Delay delay) {
		this.delay = delay.getValor();
	}

	public String leituraSensorTexto() {
		return arduinoDAO.getInputLine();
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
		String[] entradas = inputLine.split("/");
		LeituraSensores leitura = new LeituraSensores();
		try {
			leitura.setUmidade(Float.parseFloat(entradas[0]));
			leitura.setTemperatura(Float.parseFloat(entradas[1]));
			leitura.setLuminosidade(Float.parseFloat(entradas[2]));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (quantidade >= delay) {
			RelatorioDiario relatorio = relatorioDAO.getById(1);
			Date time = new Date(System.currentTimeMillis());
			leitura.setInstante(time);
			leitura.setRelatorio(relatorio);
			leituraDAO.inserirLeitura(leitura);
			quantidade = 0;
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

	public List<ListaLeituraSensores> getIntervaloLeituraSensor(ListaLeituraSensores listaLeituraSensores) {
		List<ListaLeituraSensores> leituraSensoresRetorno = leituraDAO.getIntervaloLeituraSensor(listaLeituraSensores);
		return leituraSensoresRetorno;
	}

	public void atualizarRelatorio(RelatorioDiario relatorio) {
		relatorioDAO.atualizarRelatorio(relatorio);
	}

	public List<RelatorioDiario> getToModifyRelatoriosDiarios() {
		Vector<RelatorioDiario> relatorioDiarios = relatorioDAO.getToModifyRelatoriosDiarios();
		return relatorioDiarios.subList(0, relatorioDiarios.size());
	}

	public List<RelatorioDiario> getTodosRelatoriosDiarios() {
		Vector<RelatorioDiario> relatorioDiarios = relatorioDAO.listarRelatorioDiarioVector();
		return relatorioDiarios.subList(0, relatorioDiarios.size());
	}

	public RelatorioDiario getrelatoriosdiariosbydata(RelatorioDiario relatorioDiario) {
		return relatorioDAO.getrelatoriosdiariosbydata(relatorioDiario);
	}

}