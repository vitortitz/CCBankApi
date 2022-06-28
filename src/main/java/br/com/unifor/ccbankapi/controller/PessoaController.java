package br.com.unifor.ccbankapi.controller;

import br.com.unifor.ccbankapi.domain.Pessoa;
import br.com.unifor.ccbankapi.domain.ErroResponse;
import br.com.unifor.ccbankapi.exception.ErroNegocioException;
import br.com.unifor.ccbankapi.service.PessoaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Wander
 */
@Path("pessoa")
public class PessoaController {

    private PessoaService PessoaService;

    public PessoaController() {
        PessoaService = new PessoaService();
    }

    @GET
    public Response ping() {
        return Response
                .ok("ping")
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(String PessoaJson) {
        Response retorno = null;
        try {
            Pessoa Pessoa = PessoaService.inserir(PessoaJson);
            if (Pessoa == null)
                retorno = Response.serverError().build();
            if (Pessoa != null) {
                ObjectMapper mapper = new ObjectMapper();
                retorno = Response.ok(mapper.writeValueAsBytes(Pessoa)).build();
            }

        } catch (ErroNegocioException e) {
            return Response
                    .status(e.getErroResponse().getStatus())
                    .entity(e.getErroResponse())
                    .build();
        } catch (Exception ex) {
            retorno = Response
                    .serverError()
                    .entity(new ErroResponse("Erro ao inserir Pessoa. Motivo: " + ex.getMessage(),
                            Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))
                    .build();
        }
        return retorno;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response atualizar(String PessoaRequest) throws JsonProcessingException {
        Response retorno = null;

        try {
            Pessoa Pessoa = PessoaService.editar(PessoaRequest);

            if (Pessoa == null)
                retorno = Response.serverError().build();
            if (Pessoa != null) {
                ObjectMapper mapper = new ObjectMapper();
                retorno = Response.ok(mapper.writeValueAsBytes(Pessoa)).build();
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
    public Response deletar(String PessoaRequest) throws JsonProcessingException {
        Response retorno = null;

        try {
            Pessoa Pessoa = PessoaService.excluir(PessoaRequest);
            if (Pessoa == null)
                retorno = Response.serverError().build();
            if (Pessoa != null) {
                ObjectMapper mapper = new ObjectMapper();
                retorno = Response.ok(mapper.writeValueAsBytes(Pessoa)).build();
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