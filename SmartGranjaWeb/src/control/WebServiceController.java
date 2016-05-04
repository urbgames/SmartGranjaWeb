package control;

import java.util.List;

import objects.Delay;
import objects.LeituraSensores;
import objects.RelatorioDiario;


public class WebServiceController {
	
	private ControlePersistencia controlePersistencia = new ControlePersistencia();
	
	public LeituraSensores getLeituraSensor(){
		return controlePersistencia.getleituraSensores();
	}
	
	public List<RelatorioDiario> getTodosRelatoriosDiarios(){
		return controlePersistencia.getTodosRelatoriosDiarios();
	}

	public void setRelatorioDiario(RelatorioDiario relatorioDiario) {
		
		controlePersistencia.atualizarRelatorio(relatorioDiario);
		
	}
	
	public void setDaley(Delay delay) {
		
		controlePersistencia.setDelay(delay);
		
	}

}
