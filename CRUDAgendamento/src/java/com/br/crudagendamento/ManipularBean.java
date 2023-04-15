package com.br.crudagendamento;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dominio.Agendamento;
import dominio.Agendamento_DAO;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ManipularBean {
    private int id;
    private String cpf;
    private String nome;
    private String observacoes;
    
    public void adicionarMensagem(String s1, String s2) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, s1, s2);
            FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void remover() {
        Agendamento_DAO dao = new Agendamento_DAO();
        if(dao.remover(id)){
            adicionarMensagem("Sucesso!", "Operação realizada.");
        }
        else {
            adicionarMensagem("Operação fracassou", "ID não encontrado");
        }
    }
    
    public void alterar() {
        Agendamento_DAO dao = new Agendamento_DAO();
        Agendamento a = dao.consultaPorId(id);
        
        if(cpf != null && !cpf.equals("")) {
            a.setCpf(cpf);
        }
        if(nome != null && !nome.equals("")) {
            a.setNome(nome);
        }
        if(observacoes != null && !observacoes.equals("")) {
            a.setObservacao(observacoes);
        }
        
        if(dao.atualizar(id, a)) {
            adicionarMensagem("Sucesso!", "Operação realizada com sucesso.");
        }
        else {
            adicionarMensagem("Operação fracassou", "ID inválido");
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    
    
    
}
