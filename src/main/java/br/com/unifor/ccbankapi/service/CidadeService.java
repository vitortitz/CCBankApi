/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unifor.ccbankapi.service;

import br.com.unifor.ccbankapi.dao.CidadeDao;
import br.com.unifor.ccbankapi.domain.Cidade;
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
public class CidadeService {
    
    private CidadeDao cidadeDao;
    
    public CidadeService() {
        // TODO: Construtor padrão
        cidadeDao = new CidadeDao();
    }
    
    public Cidade inserir(Cidade cidade) throws ErroNegocioException {
        String uf = String.valueOf(cidade.getUf()).toUpperCase();
        String[] listaUFs = {"AC","AL","AP","AM","BA","CE","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO","DF"};
        boolean achou = false;
        int posicao = 0;
        do {
            achou = uf.equals(listaUFs[posicao]);
            posicao++;
        } while ((!achou) &&(posicao < listaUFs.length));
        if (!achou) {
           throw new ErroNegocioException("Estado informado inválido!");
        }          
        return cidadeDao.insert(cidade);
    }
    
    public Cidade inserir(String cidadeJson) throws ErroNegocioException {
        Cidade cidadeInserida = new Cidade();
        try {
            ObjectMapper mapper = new ObjectMapper(); 
            Cidade cidade = mapper.readValue(cidadeJson, Cidade.class);
            cidadeInserida = this.inserir(cidade);       //coloquei o this     
        } catch (JsonProcessingException ex) {
            Logger.getLogger(CidadeService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ErroNegocioException("Erro ao conveter Cidade. Motivo: " + ex.getMessage(), ex, Response.Status.BAD_REQUEST);
        }
        return cidadeInserida;
    }
    
    public Cidade editar(String cidadeRequest) throws ErroNegocioException {
        Cidade cidadeEditada = new Cidade();
        ObjectMapper mapper = new ObjectMapper(); 
        try {
           Cidade cidade = mapper.readValue(cidadeRequest, Cidade.class);
           return cidadeEditada = this.editar(cidade);
        } catch (JsonProcessingException ex) {
           throw new ErroNegocioException("Erro ao conveter Cidade. Motivo: " + ex.getMessage(), ex, Response.Status.BAD_REQUEST);
        }      
    }
    
    
    public Cidade editar( Cidade cidade) throws ErroNegocioException {
        if (cidade.getId() == null || cidade.getId().toString().equals("")) {
            throw new ErroNegocioException("Cidade informada não encontrada",Response.Status.BAD_REQUEST);
        }
        
        if (cidade.getNome() == null || cidade.getNome().equals("")) {
            throw new ErroNegocioException("Cidade com nome vazio",Response.Status.BAD_REQUEST);
        }
        
        return cidadeDao.update(cidade);
        
    }
    
    public List<Cidade> getTodasCidades() {
        return cidadeDao.findAll();
    }
    
    public Cidade excluir(String cidadeRequest) throws ErroNegocioException {
        Cidade cidadeDeletada = new Cidade();
        ObjectMapper mapper = new ObjectMapper(); 
        try {
           Cidade cidade = mapper.readValue(cidadeRequest, Cidade.class);
           return cidadeDeletada = this.excluir(cidade);
        } catch (JsonProcessingException ex) {
           throw new ErroNegocioException("Erro ao deletar Cidade. Motivo: " + ex.getMessage(), ex, Response.Status.BAD_REQUEST);
        }      

    } 
    
     public Cidade excluir(Cidade cidade) throws ErroNegocioException {
        if (cidade.getId() == null || cidade.getId().toString().equals("")) {
            throw new ErroNegocioException("Cidade informada não encontrada",Response.Status.BAD_REQUEST);
        }
        
        if (cidade.getNome() == null || cidade.getNome().equals("")) {
            throw new ErroNegocioException("Cidade com nome vazio",Response.Status.BAD_REQUEST);
        }
       Cidade cidadeADeletar = cidadeDao.findById(cidade.getId());
       return cidadeDao.delete(cidadeADeletar);
    }
}
