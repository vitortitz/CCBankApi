package br.com.unifor.ccbankapi.controller;

import br.com.unifor.ccbankapi.domain.Usuario;
import br.com.unifor.ccbankapi.domain.ErroResponse;
import br.com.unifor.ccbankapi.exception.ErroNegocioException;
import br.com.unifor.ccbankapi.service.UsuarioService;
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
@Path("usuario")
public class UsuarioController {
    
    private UsuarioService UsuarioService;
    
    public UsuarioController() {
        UsuarioService = new UsuarioService();
    }
    
    @GET
     @Produces(MediaType.APPLICATION_JSON)
	public Response listar() throws JsonProcessingException {
                Response retorno = null;
		List<Usuario> Usuarios = UsuarioService.getTodosUsuarios();
                ObjectMapper mapper = new ObjectMapper(); 
                retorno =  Response.ok(mapper.writeValueAsBytes(Usuarios)).build();
		return retorno;
	}
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{codigo}")// passa o codigo para o endereço
    public Response buscar(@PathParam("codigo") int id) throws JsonProcessingException { // Amarrando o parametro codigo que o usuario digita, ao
		Response retorno = null;												// objeto codigo
		Usuario Usuario = UsuarioService.buscar(id);        
		ObjectMapper mapper = new ObjectMapper(); 
                retorno =  Response.ok(mapper.writeValueAsBytes(Usuario)).build();
		return retorno;

	}
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response inserir(String UsuarioJson) {
        Response retorno = null;
        try {
            Usuario Usuario = UsuarioService.inserir(UsuarioJson);
            if (Usuario == null) 
                retorno = Response.serverError().build();
            if(Usuario != null) {
                ObjectMapper mapper = new ObjectMapper(); 
                retorno =  Response.ok(mapper.writeValueAsBytes(Usuario)).build();
            }
              
        } catch (ErroNegocioException e) {
            return Response
                    .status(e.getErroResponse().getStatus())
                    .entity(e.getErroResponse())
                    .build();
        } catch (Exception ex) {            
            retorno = Response
                    .serverError()
                    .entity(new ErroResponse("Erro ao inserir Usuario. Motivo: " + ex.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()))
                    .build();
        }
        return retorno;
    }
     @PUT
     @Consumes(MediaType.APPLICATION_JSON)
     public Response atualizar(String UsuarioRequest) throws JsonProcessingException {
         Response retorno = null;
         
         try {
             Usuario  Usuario = UsuarioService.editar(UsuarioRequest);
             
             if (Usuario == null) 
                retorno = Response.serverError().build();
            if(Usuario != null) {
                ObjectMapper mapper = new ObjectMapper(); 
                retorno =  Response.ok(mapper.writeValueAsBytes(Usuario)).build();
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
     public Response deletar(String UsuarioRequest) throws JsonProcessingException{
         Response retorno = null;
         
         try {
             Usuario  Usuario = UsuarioService.excluir(UsuarioRequest);
             if (Usuario == null) 
                retorno = Response.serverError().build();
            if(Usuario != null) {
                ObjectMapper mapper = new ObjectMapper(); 
                retorno =  Response.ok(mapper.writeValueAsBytes(Usuario)).build();
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