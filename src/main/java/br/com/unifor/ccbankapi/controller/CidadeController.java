package br.com.unifor.ccbankapi.controller;

import br.com.unifor.ccbankapi.domain.Cidade;
import br.com.unifor.ccbankapi.domain.ErroResponse;
import br.com.unifor.ccbankapi.exception.ErroNegocioException;
import br.com.unifor.ccbankapi.service.CidadeService;
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
@Path("cidade")
public class CidadeController {
    
    private CidadeService cidadeService;
    
    public CidadeController() {
        cidadeService = new CidadeService();
    }
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(String cidadeJson) {
        Response retorno = null;
        try {
            Cidade cidade = cidadeService.inserir(cidadeJson);
            if (cidade == null) 
                retorno = Response.serverError().build();
            if(cidade != null) {
                ObjectMapper mapper = new ObjectMapper(); 
                retorno =  Response.ok(mapper.writeValueAsBytes(cidade)).build();
            }
              
        } catch (ErroNegocioException e) {
            return Response
                    .status(e.getErroResponse().getStatus())
                    .entity(e.getErroResponse())
                    .build();
        } catch (Exception ex) {            
            retorno = Response
                    .serverError()
                    .entity(new ErroResponse("Erro ao inserir cidade. Motivo: " + ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))
                    .build();
        }
        return retorno;
    }
     @PUT
     @Consumes(MediaType.APPLICATION_JSON)
     public Response atualizar(String cidadeRequest) throws JsonProcessingException {
         Response retorno = null;
         
         try {
             Cidade  cidade = cidadeService.editar(cidadeRequest);
             
             if (cidade == null) 
                retorno = Response.serverError().build();
            if(cidade != null) {
                ObjectMapper mapper = new ObjectMapper(); 
                retorno =  Response.ok(mapper.writeValueAsBytes(cidade)).build();
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
     public Response deletar(String cidadeRequest) throws JsonProcessingException{
         Response retorno = null;
         
         try {
             Cidade  cidade = cidadeService.excluir(cidadeRequest);
             if (cidade == null) 
                retorno = Response.serverError().build();
            if(cidade != null) {
                ObjectMapper mapper = new ObjectMapper(); 
                retorno =  Response.ok(mapper.writeValueAsBytes(cidade)).build();
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