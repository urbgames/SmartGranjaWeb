package view;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import objects.Pessoa;

@Path("")
@Provider
public class View {
	
	@GET
	@Path("/pessoa")
	@Produces(MediaType.APPLICATION_JSON)
	public Pessoa getPessoa(){
		
		Pessoa pessoa = new Pessoa();
		pessoa.setIdade(20);
		pessoa.setNome("Vi");
		
		return pessoa;
		
	}
	
	@POST
	@Path("/cadastrarpessoa")
	@Consumes(MediaType.APPLICATION_JSON)
	public void cadastrarPessoa(Pessoa pessoa){
		
		Pessoa pessoa2 = pessoa;
		System.out.println(pessoa2.getIdade());
		System.out.println(pessoa.getNome());
		
	}

}
