package control;

import java.util.List;

import model.TreeGenerator;
import objects.Delay;
import objects.LeituraSensores;
import objects.ListaLeituraSensores;
import objects.RelatorioDiario;

public class WebServiceController {

	private ControlePersistencia controlePersistencia = new ControlePersistencia();
	private TreeGenerator treeGenerator = new TreeGenerator();

	public LeituraSensores getLeituraSensor() {
		return controlePersistencia.getleituraSensores();
	}

	public List<RelatorioDiario> getTodosRelatoriosDiarios() {
		return controlePersistencia.getTodosRelatoriosDiarios();
	}

	public void setDaley(Delay delay) {

		controlePersistencia.setDelay(delay);

	}

	public List<ListaLeituraSensores> getIntervaloLeituraSensor(ListaLeituraSensores listaLeituraSensores) {
		return controlePersistencia.getIntervaloLeituraSensor(listaLeituraSensores);
	}


	public void setRelatorioDiario(RelatorioDiario relatorioDiario) {

		controlePersistencia.atualizarRelatorio(relatorioDiario);

	}

	public List<RelatorioDiario> getToModifyRelatoriosDiarios() {
		return controlePersistencia.getToModifyRelatoriosDiarios();
	}

	public RelatorioDiario getrelatoriosdiariosbydata(RelatorioDiario relatorioDiario) {
		return controlePersistencia.getrelatoriosdiariosbydata(relatorioDiario);		
	}
	
	public String getArvore(){
		String resultado="";
		try {
			resultado= treeGenerator.gerarArvore();
		} catch (Exception e) {
			resultado = e.toString();
		}
		return resultado;
	}
	
}
