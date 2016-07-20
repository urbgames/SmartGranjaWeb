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
import objects.Tree;
import control.WebServiceController;

@Path("")
@Provider
public class View {

	private final static WebServiceController serviceController = new WebServiceController();

	@POST
	@Path("/getclassificacao")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getClassificacao(LeituraSensores leituraSensores) {
		return serviceController.getClassificacao(leituraSensores);
	}

	@GET
	@Path("/getarvore")
	@Produces(MediaType.APPLICATION_JSON)
	public Tree getArvore() {
		return serviceController.getArvore();
	}

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
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		return serviceController.getToModifyRelatoriosDiarios();
	}

	@POST
	@Path("/setrelatoriosdiarios")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RelatorioDiario setRelatorioDiario(RelatorioDiario relatorioDiario) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
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
		List<ListaLeituraSensores> leituraSensoresRetorno = serviceController
				.getIntervaloLeituraSensor(listaLeituraSensores);
		return leituraSensoresRetorno;
	}

}
