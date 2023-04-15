/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.crudagendamento;

import dominio.Agendamento_DAO;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class AgendamentoBean implements Serializable {
    private String nome;
    private String cpf;
    private String observacoes;
    private String data;
    
    
    public void adicionarMensagem(String s1, String s2) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, s1, s2);
            FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void adicionar() throws ParseException {
        Agendamento_DAO dao = new Agendamento_DAO();
        SimpleDateFormat readFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date d = readFormat.parse(data);
        Timestamp dataSQL = new Timestamp(d.getTime());
        LocalDateTime local = dataSQL.toLocalDateTime();
        Timestamp atual = new java.sql.Timestamp(System.currentTimeMillis());
        
        if(local.getHour() > 19 || local.getHour() < 7) {
            adicionarMensagem("Erro!", "Hora inválida, horário de funcionamento da clínica é de 7hrs às 19hrs");
        }
        else if(local.getMinute() != 0 && local.getMinute() != 30) {
            adicionarMensagem("Erro!", "Hora inválida, consultas devem ser marcadas a cada meia hora, iniciando às 7");
        }
        else if(local.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            adicionarMensagem("Erro!", "Data inválida, clínica não funciona aos domingos");
        }
        else if(dataSQL.before(atual)) {
            adicionarMensagem("Erro!", "Data inválida, somente permitimos agendamentos futuros");
        }
        else if(dao.consultaTodosTimestamps().contains(dataSQL.getTime())) {
            adicionarMensagem("Erro!", "Já existe agendamento nesta data, escolha outra");
        }
        else if(!(dao.consultaPorCPF(cpf).isEmpty())) {
            adicionarMensagem("Erro!", "Paciente já possui consulta agendada");
        }        
        else if(dao.adicionar(cpf, nome, observacoes, dataSQL)){
            adicionarMensagem("Sucesso!", "Agendamento adicionado com sucesso");
        } else {
            adicionarMensagem("Erro!", "Ocorreu um erro ao adicionar seu agendamento");
        }
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    
    
    
}
