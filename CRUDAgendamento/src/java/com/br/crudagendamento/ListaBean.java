package com.br.crudagendamento;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dominio.Agendamento;
import dominio.Agendamento_DAO;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ListaBean implements Serializable{
    
    private int id;
    private String nome;
    private String cpf;
    private String observacoes;
    private String parametro;
    private String tipoPesquisa;
    
    public List<Agendamento> retornaLista() {
        Agendamento_DAO dao = new Agendamento_DAO();
        
        List<Agendamento> ag = dao.consultaCompleta();
        
        return ag;
    }
    
    public List<Agendamento> retornaCpfNome() {
        Agendamento_DAO dao = new Agendamento_DAO();
        List<Agendamento> ag = new ArrayList<>();
        
        if(tipoPesquisa == null) {
            return ag;
        }
        else if(tipoPesquisa.equals("cpf")) {
            ag = dao.consultaPorCPF(parametro);
        }
        else {
            ag = dao.consultaPorNome(parametro);
        }
        
        return ag;
    }
    
    public void remover() {
        Agendamento_DAO dao = new Agendamento_DAO();
        if(dao.remover(id));
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }

    public String getTipoPesquisa() {
        return tipoPesquisa;
    }

    public void setTipoPesquisa(String tipoPesquisa) {
        this.tipoPesquisa = tipoPesquisa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }   
    
    
}
