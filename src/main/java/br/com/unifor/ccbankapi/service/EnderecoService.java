/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unifor.ccbankapi.service;

import br.com.unifor.ccbankapi.dao.EnderecoDao;
import br.com.unifor.ccbankapi.domain.Conta;
import br.com.unifor.ccbankapi.domain.Endereco;
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
public class EnderecoService {
    
    private EnderecoDao EnderecoDao;
    
    public EnderecoService() {
        // TODO: Construtor padrão
        EnderecoDao = new EnderecoDao();
    }
    
    public Endereco inserir(Endereco Endereco) throws ErroNegocioException {
        if(Endereco.getCep().length() < 9)
            throw new ErroNegocioException("Cep informado inválido!");
        if(Endereco.getLogradouro() == null)
            throw new ErroNegocioException("Logradouro informado inválido!");
        if(Endereco.getNumero()== null)
            throw new ErroNegocioException("Numero informado inválido!");
         if(Endereco.getBairro()== null)
            throw new ErroNegocioException("Bairro informado inválido!");

                       
        return EnderecoDao.insert(Endereco);
    }
    public List<Endereco> getTodosEnderecos() {
        return EnderecoDao.findAll();
    }
    
    public Endereco buscar(int id) {
        return EnderecoDao.findById(id);
    }
    
    public Endereco inserir(String EnderecoJson) throws ErroNegocioException {
        Endereco EnderecoInserida = new Endereco();
        try {
            ObjectMapper mapper = new ObjectMapper(); 
            Endereco Endereco = mapper.readValue(EnderecoJson, Endereco.class);
            EnderecoInserida = this.inserir(Endereco);       //coloquei o this     
        } catch (JsonProcessingException ex) {
            Logger.getLogger(EnderecoService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ErroNegocioException("Erro ao conveter Endereco. Motivo: " + ex.getMessage(), ex, Response.Status.BAD_REQUEST);
        }
        return EnderecoInserida;
    }
    
    public Endereco editar(String EnderecoRequest) throws ErroNegocioException {
        Endereco EnderecoEditada = new Endereco();
        ObjectMapper mapper = new ObjectMapper(); 
        try {
           Endereco Endereco = mapper.readValue(EnderecoRequest, Endereco.class);
           return EnderecoEditada = this.editar(Endereco);
        } catch (JsonProcessingException ex) {
           throw new ErroNegocioException("Erro ao conveter Endereco. Motivo: " + ex.getMessage(), ex, Response.Status.BAD_REQUEST);
        }      
    }
    
    
    public Endereco editar( Endereco Endereco) throws ErroNegocioException {
        if (Endereco.getId() == null || Endereco.getId().toString().equals("")) {
            throw new ErroNegocioException("Endereco informada não encontrada",Response.Status.BAD_REQUEST);
        }   
        if(Endereco.getCep().length() < 9)
            throw new ErroNegocioException("Cep informado inválido!");
        if(Endereco.getLogradouro() == null)
            throw new ErroNegocioException("Logradouro informado inválido!");
        if(Endereco.getNumero()== null)
            throw new ErroNegocioException("Numero informado inválido!");
         if(Endereco.getBairro()== null)
            throw new ErroNegocioException("Bairro informado inválido!");
        
        return EnderecoDao.update(Endereco);
        
    }
    
    public List<Endereco> getTodasEnderecos() {
        return EnderecoDao.findAll();
    }
    
    public Endereco excluir(String EnderecoRequest) throws ErroNegocioException {
        Endereco EnderecoDeletada = new Endereco();
        ObjectMapper mapper = new ObjectMapper(); 
        try {
           Endereco Endereco = mapper.readValue(EnderecoRequest, Endereco.class);
           return EnderecoDeletada = this.excluir(Endereco);
        } catch (JsonProcessingException ex) {
           throw new ErroNegocioException("Erro ao deletar Endereco. Motivo: " + ex.getMessage(), ex, Response.Status.BAD_REQUEST);
        }      

    } 
    
     public Endereco excluir(Endereco Endereco) throws ErroNegocioException {
        if (Endereco.getId() == null || Endereco.getId().toString().equals("")) {
            throw new ErroNegocioException("Endereco informada não encontrada",Response.Status.BAD_REQUEST);
        }
        
       
       Endereco EnderecoADeletar = EnderecoDao.findById(Endereco.getId());
       return EnderecoDao.delete(EnderecoADeletar);
    }
}
