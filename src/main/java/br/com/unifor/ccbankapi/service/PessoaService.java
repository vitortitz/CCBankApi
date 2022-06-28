/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unifor.ccbankapi.service;

import br.com.unifor.ccbankapi.dao.PessoaDao;
import br.com.unifor.ccbankapi.dao.PessoaDao;
import br.com.unifor.ccbankapi.domain.Pessoa;
import br.com.unifor.ccbankapi.domain.Pessoa;
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
public class PessoaService {

    private PessoaDao PessoaDao;

    public PessoaService() {
        // TODO: Construtor padrão
        PessoaDao = new PessoaDao();
    }

    public Pessoa inserir(Pessoa Pessoa) throws ErroNegocioException {
        if (Pessoa == null) {
            throw new ErroNegocioException("Pessoa informada é nulo!");
        }
        if (Pessoa.getNome().length() < 3 ) {
            throw new ErroNegocioException("Nome deve ser maior que 3 Digitos");
        }
        if (Pessoa.getCpf() != null ) {
            if(Pessoa.getCpf().length() < 11 && Pessoa.getCpf().length() > 11 )
            throw new ErroNegocioException("CPF deve conter 11 digitos");
        }
        return PessoaDao.insert(Pessoa);

    }

    public Pessoa inserir(String PessoaJson) throws ErroNegocioException {
        Pessoa PessoaInserida = new Pessoa();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Pessoa Pessoa = mapper.readValue(PessoaJson, Pessoa.class);
            PessoaInserida = this.inserir(Pessoa); // coloquei o this
        } catch (JsonProcessingException ex) {
            Logger.getLogger(PessoaService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ErroNegocioException("Erro ao conveter Pessoa. Motivo: " + ex.getMessage(), ex,
                    Response.Status.BAD_REQUEST);
        }
        return PessoaInserida;
    }

    public Pessoa editar(String PessoaRequest) throws ErroNegocioException {
        Pessoa PessoaEditada = new Pessoa();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Pessoa Pessoa = mapper.readValue(PessoaRequest, Pessoa.class);
            return PessoaEditada = this.editar(Pessoa);
        } catch (JsonProcessingException ex) {
            throw new ErroNegocioException("Erro ao conveter Pessoa. Motivo: " + ex.getMessage(), ex,
                    Response.Status.BAD_REQUEST);
        }
    }

    public Pessoa editar(Pessoa Pessoa) throws ErroNegocioException {
        if (Pessoa == null) {
            throw new ErroNegocioException("Pessoa informada é nulo!");
        }
        if (Pessoa.getNome().length() < 3 ) {
            throw new ErroNegocioException("Nome deve ser maior que 3 Digitos");
        }
        if (Pessoa.getCpf() != null ) {
            if(Pessoa.getCpf().length() < 11 && Pessoa.getCpf().length() > 11 )
            throw new ErroNegocioException("CPF deve conter 11 digitos");
        }
        

        return PessoaDao.update(Pessoa);

    }

    public Pessoa excluir(String PessoaRequest) throws ErroNegocioException {
        Pessoa PessoaDeletada = new Pessoa();
        ObjectMapper mapper = new ObjectMapper();
        try {
            Pessoa Pessoa = mapper.readValue(PessoaRequest, Pessoa.class);
            return PessoaDeletada = this.excluir(Pessoa);
        } catch (JsonProcessingException ex) {
            throw new ErroNegocioException("Erro ao deletar Pessoa. Motivo: " + ex.getMessage(), ex,
                    Response.Status.BAD_REQUEST);
        }

    }

    public Pessoa excluir(Pessoa Pessoa) throws ErroNegocioException {
        if (Pessoa.getId() == null || Pessoa.getId().toString().equals("")) {
            throw new ErroNegocioException("Pessoa informada não encontrada", Response.Status.BAD_REQUEST);
        }
        Pessoa PessoaADeletar = PessoaDao.findById(Pessoa.getId());
        return PessoaDao.delete(PessoaADeletar);
    }
}
