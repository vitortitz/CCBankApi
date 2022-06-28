/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unifor.ccbankapi.service;

import br.com.unifor.ccbankapi.dao.UsuarioDao;
import br.com.unifor.ccbankapi.dao.UsuarioDao;
import br.com.unifor.ccbankapi.domain.Pessoa;
import br.com.unifor.ccbankapi.domain.Usuario;
import br.com.unifor.ccbankapi.domain.Usuario;
import br.com.unifor.ccbankapi.exception.ErroNegocioException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.value;
import javax.ws.rs.core.Response;

/**
 *
 * @author wander
 */
public class UsuarioService {
    
    private UsuarioDao usuarioDao;
    
    public UsuarioService() {
        // TODO: Construtor padrão
        usuarioDao = new UsuarioDao();
    }
    
    public Usuario inserir(Usuario usuario) throws ErroNegocioException {
        if (usuario == null) {
            throw new ErroNegocioException("Usuario informada é nulo!");
        }
        if(usuario.getLogin().length() < 3 || usuario.getSenha().length() < 4){
            throw new ErroNegocioException("Login deve ser maior que 3 Digitos e Senha maior que 4");
        }
        return usuarioDao.insert(usuario);
        
    }
    
    public Usuario inserir(String UsuarioJson) throws ErroNegocioException {
        Usuario UsuarioInserida = new Usuario();
        try {
            ObjectMapper mapper = new ObjectMapper(); 
            Usuario Usuario = mapper.readValue(UsuarioJson, Usuario.class);
            UsuarioInserida = this.inserir(Usuario);       //coloquei o this     
        } catch (JsonProcessingException ex) {
            Logger.getLogger(UsuarioService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ErroNegocioException("Erro ao conveter Usuario. Motivo: " + ex.getMessage(), ex, Response.Status.BAD_REQUEST);
        }
        return UsuarioInserida;
    }
    
    public Usuario editar(String UsuarioRequest) throws ErroNegocioException {
        Usuario UsuarioEditada = new Usuario();
        ObjectMapper mapper = new ObjectMapper(); 
        try {
           Usuario Usuario = mapper.readValue(UsuarioRequest, Usuario.class);
           return UsuarioEditada = this.editar(Usuario);
        } catch (JsonProcessingException ex) {
           throw new ErroNegocioException("Erro ao conveter Usuario. Motivo: " + ex.getMessage(), ex, Response.Status.BAD_REQUEST);
        }      
    }
    
    
    public Usuario editar( Usuario Usuario) throws ErroNegocioException {
        if (Usuario == null) {
            throw new ErroNegocioException("Usuario informada é nulo!");
        }
        if(Usuario.getLogin().length() < 3 || Usuario.getSenha().length() < 4){
            throw new ErroNegocioException("Login deve ser maior que 3 Digitos e Senha maior que 4");
        }  
        
        return usuarioDao.update(Usuario);
        
    }
    
    public List<Usuario> getTodosUsuarios() {
        return usuarioDao.findAll();
    }
    
    public Usuario buscar(int id) {
        return usuarioDao.findById(id);
    }
    
    
    
    public Usuario excluir(String UsuarioRequest) throws ErroNegocioException {
        Usuario UsuarioDeletada = new Usuario();
        ObjectMapper mapper = new ObjectMapper(); 
        try {
           Usuario Usuario = mapper.readValue(UsuarioRequest, Usuario.class);
           return UsuarioDeletada = this.excluir(Usuario);
        } catch (JsonProcessingException ex) {
           throw new ErroNegocioException("Erro ao deletar Usuario. Motivo: " + ex.getMessage(), ex, Response.Status.BAD_REQUEST);
        }      

    } 
    
     public Usuario excluir(Usuario Usuario) throws ErroNegocioException {
        if (Usuario.getId() == null || Usuario.getId().toString().equals("")) {
            throw new ErroNegocioException("Usuario informada não encontrada",Response.Status.BAD_REQUEST);
        } 
       Usuario UsuarioADeletar = usuarioDao.findById(Usuario.getId());
       return usuarioDao.delete(UsuarioADeletar);
    }
}
