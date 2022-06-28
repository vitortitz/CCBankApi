/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unifor.ccbankapi.service;

import br.com.unifor.ccbankapi.dao.ContaDao;
import br.com.unifor.ccbankapi.domain.Conta;
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
public class ContaService {
    
    private ContaDao ContaDao;
    
    public ContaService() {
        // TODO: Construtor padrão
        ContaDao = new ContaDao();
    }
    
    public Conta inserir(Conta Conta) throws ErroNegocioException {
        if(Conta.getNumero() == null){
           throw new ErroNegocioException("Numero informado inválido!");
        }
        if(String.valueOf(Conta.getSaldo()) == null){
            throw new ErroNegocioException("Saldo informado inválido!");
        }
        if(String.valueOf(Conta.getLimite()) == null){
            throw new ErroNegocioException("Limite informado inválido!");
        }   
        return ContaDao.insert(Conta);
    }
    
    public Conta inserir(String ContaJson) throws ErroNegocioException {
        Conta ContaInserida = new Conta();
        try {
            ObjectMapper mapper = new ObjectMapper(); 
            Conta Conta = mapper.readValue(ContaJson, Conta.class);
            ContaInserida = this.inserir(Conta);       //coloquei o this     
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ContaService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ErroNegocioException("Erro ao conveter Conta. Motivo: " + ex.getMessage(), ex, Response.Status.BAD_REQUEST);
        }
        return ContaInserida;
    }
    
    public Conta editar(String ContaRequest) throws ErroNegocioException {
        Conta ContaEditada = new Conta();
        ObjectMapper mapper = new ObjectMapper(); 
        try {
           Conta Conta = mapper.readValue(ContaRequest, Conta.class);
           return ContaEditada = this.editar(Conta);
        } catch (JsonProcessingException ex) {
           throw new ErroNegocioException("Erro ao conveter Conta. Motivo: " + ex.getMessage(), ex, Response.Status.BAD_REQUEST);
        }      
    }
    
    
    public Conta editar( Conta Conta) throws ErroNegocioException {
        if (Conta.getId() == null || Conta.getId().toString().equals("")) {
            throw new ErroNegocioException("Conta informada não encontrada",Response.Status.BAD_REQUEST);
        }
        if(Conta.getNumero() == null){
           throw new ErroNegocioException("Numero informado inválido!");
        }
        if(String.valueOf(Conta.getSaldo()) == null){
            throw new ErroNegocioException("Saldo informado inválido!");
        }
        if(String.valueOf(Conta.getLimite()) == null){
            throw new ErroNegocioException("Limite informado inválido!");
        } 
        
        return ContaDao.update(Conta);
        
    }
    
    public List<Conta> getTodasContas() {
        return ContaDao.findAll();
    }
    
    public Conta excluir(String ContaRequest) throws ErroNegocioException {
        Conta ContaDeletada = new Conta();
        ObjectMapper mapper = new ObjectMapper(); 
        try {
           Conta Conta = mapper.readValue(ContaRequest, Conta.class);
           return ContaDeletada = this.excluir(Conta);
        } catch (JsonProcessingException ex) {
           throw new ErroNegocioException("Erro ao deletar Conta. Motivo: " + ex.getMessage(), ex, Response.Status.BAD_REQUEST);
        }      

    } 
    
     public Conta excluir(Conta Conta) throws ErroNegocioException {
        if (Conta.getId() == null || Conta.getId().toString().equals("")) {
            throw new ErroNegocioException("Conta informada não encontrada",Response.Status.BAD_REQUEST);
        }      
       Conta ContaADeletar = ContaDao.findById(Conta.getId());
       return ContaDao.delete(ContaADeletar);
    }
}
