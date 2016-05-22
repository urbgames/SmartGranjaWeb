package objects;

import java.util.Date;
import java.util.List;

public class ListaLeituraSensores {

	private Date dataInicio;
	private Date dataFim;
	private List<LeituraSensores> listaLeituraSensores;
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public List<LeituraSensores> getListaLeituraSensores() {
		return listaLeituraSensores;
	}
	public void setListaLeituraSensores(List<LeituraSensores> listaLeituraSensores) {
		this.listaLeituraSensores = listaLeituraSensores;
	}
	
}
