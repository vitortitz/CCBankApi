package br.com.unifor.ccbankapi.controller;

import br.com.unifor.ccbankapi.domain.Cidade;
import br.com.unifor.ccbankapi.domain.Conta;
import br.com.unifor.ccbankapi.domain.ErroResponse;
import br.com.unifor.ccbankapi.exception.ErroNegocioException;
import br.com.unifor.ccbankapi.service.ContaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Wander
 */
@Path("conta")
public class ContaController {

    private ContaService ContaService;

    public ContaController() {
        ContaService = new ContaService();
    }

    @GET
     @Produces(MediaType.APPLICATION_JSON)
	public Response listar() throws JsonProcessingException {
                Response retorno = null;
		List<Conta> Contas = ContaService.getTodasContas();
                ObjectMapper mapper = new ObjectMapper(); 
                retorno =  Response.ok(mapper.writeValueAsBytes(Contas)).build();
		return retorno;
	}
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codigo}")// passa o codigo para o endereço
    public Response buscar(@PathParam("codigo") int id) throws JsonProcessingException { // Amarrando o parametro codigo que o usuario digita, ao
		Response retorno = null;												// objeto codigo
		Conta Conta = ContaService.buscar(id);        
		ObjectMapper mapper = new ObjectMapper(); 
                retorno =  Response.ok(mapper.writeValueAsBytes(Conta)).build();
		return retorno;

	}

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(String ContaJson) {
        Response retorno = null;
        try {
            Conta Conta = ContaService.inserir(ContaJson);
            if (Conta == null)
                retorno = Response.serverError().build();
            if (Conta != null) {
                ObjectMapper mapper = new ObjectMapper();
                retorno = Response.ok(mapper.writeValueAsBytes(Conta)).build();
            }

        } catch (ErroNegocioException e) {
            return Response
                    .status(e.getErroResponse().getStatus())
                    .entity(e.getErroResponse())
                    .build();
        } catch (Exception ex) {
            retorno = Response
                    .serverError()
                    .entity(new ErroResponse("Erro ao inserir Conta. Motivo: " + ex.getMessage(),
                            Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))
                    .build();
        }
        return retorno;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(String ContaRequest) throws JsonProcessingException {
        Response retorno = null;

        try {
            Conta Conta = ContaService.editar(ContaRequest);

            if (Conta == null)
                retorno = Response.serverError().build();
            if (Conta != null) {
                ObjectMapper mapper = new ObjectMapper();
                retorno = Response.ok(mapper.writeValueAsBytes(Conta)).build();
            }
        } catch (ErroNegocioException e) {
            retorno = Response
                    .status(e.getErroResponse().getStatus())
                    .entity(e.getErroResponse())
                    .build();
        }
        return retorno;
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletar(String ContaRequest) throws JsonProcessingException {
        Response retorno = null;

        try {
            Conta Conta = ContaService.excluir(ContaRequest);
            if (Conta == null)
                retorno = Response.serverError().build();
            if (Conta != null) {
                ObjectMapper mapper = new ObjectMapper();
                retorno = Response.ok(mapper.writeValueAsBytes(Conta)).build();
            }
        } catch (ErroNegocioException e) {
            retorno = Response
                    .status(e.getErroResponse().getStatus())
                    .entity(e.getErroResponse())
                    .build();
        }
        return retorno;
    }
}