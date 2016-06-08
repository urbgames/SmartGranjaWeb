package view;

import java.util.List;
import java.util.TimeZone;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import objects.Delay;
import objects.LeituraSensores;
import objects.ListaLeituraSensores;
import objects.RelatorioDiario;
import control.WebServiceController;

@Path("")
@Provider
public class View {

	private final static WebServiceController serviceController = new WebServiceController();

	@GET
	@Path("/getleiturasensor")
	@Produces(MediaType.APPLICATION_JSON)
	public LeituraSensores getLeituraSensor() {
		return serviceController.getLeituraSensor();
	}

	@GET
	@Path("/gettodosrelatoriosdiarios")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RelatorioDiario> getTodosRelatoriosDiarios() {
		return serviceController.getTodosRelatoriosDiarios();
	}
	
	
	@POST
	@Path("/getrelatoriosdiariosbydata")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RelatorioDiario getrelatoriosdiariosbydata(RelatorioDiario relatorioDiario) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		return serviceController.getrelatoriosdiariosbydata(relatorioDiario);
	}
		
	@GET
	@Path("/gettomodifyrelatoriosdiarios")
	@Produces(MediaType.APPLICATION_JSON)
	public List<RelatorioDiario> getToModifyRelatoriosDiarios() {
		return serviceController.getToModifyRelatoriosDiarios();
	}
	
	@POST
	@Path("/setleiturasensor")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setLeituraSensor(LeituraSensores leituraSensores) {
		System.out.println(leituraSensores);
	}

	@POST
	@Path("/setrelatoriosdiarios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RelatorioDiario setRelatorioDiario(RelatorioDiario relatorioDiario) {
		serviceController.setRelatorioDiario(relatorioDiario);
		return relatorioDiario;
	}
	
	@POST
	@Path("/setdelay")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Delay setDelay(Delay Delay) {
		serviceController.setDaley(Delay);
		return Delay;
	}
	
	@POST
	@Path("/getIntervaloLeituraSensor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<ListaLeituraSensores> getIntervaloLeituraSensor(ListaLeituraSensores listaLeituraSensores) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		List<ListaLeituraSensores> leituraSensoresRetorno = serviceController.getIntervaloLeituraSensor(listaLeituraSensores);
		return leituraSensoresRetorno;
	}

}
