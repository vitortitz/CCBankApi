/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.unifor.ccbankapi.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author Vitor
 */
@Entity
@Table(name="conta")
public class Conta implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numero")
    private String numero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "saldo")
    private double saldo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "limite")
    private double limite;
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa pessoaId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contaId",fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Historico> historicoList;

    public Conta() {
    }

    public Conta(Integer id, String numero, double saldo, double limite, Pessoa pessoaId, List<Historico> historicoList) {
        this.id = id;
        this.numero = numero;
        this.saldo = saldo;
        this.limite = limite;
        this.pessoaId = pessoaId;
        this.historicoList = historicoList;
    }

 

    public Integer getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public Pessoa getPessoaId() {
        return pessoaId;
    }

    public List<Historico> getHistoricoList() {
        return historicoList;
    }
    
    
}
