/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infortech.asl.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author neto-
 */
@Entity
@Table(name = "TB_DIALOGO")

@NamedQueries(
        {
            @NamedQuery(
                    name = "Dialogo.PorNomeDoAutor",
                    query = "SELECT d FROM Dialogo d LEFT JOIN d.autor a WHERE a.nome = :nome" +
""
            )
        }
)

public class Dialogo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JoinColumn(name = "USER_AUTOR", referencedColumnName = "ID")
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Usuario autor;
    
    @Column(name = "TXT_CONTEUDO_MENSAGEM")
    private String conteudoMensagem;
    
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MENSAGEM_DIALOGO", referencedColumnName = "ID")
    private Mensagem mensagem;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP_HORARIO_MENSAGEM")
    private Date horarioMensagem;
    
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DT_ENVIO")
    protected Date dataEnvio;

    public Date getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
    

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public String getConteudoMensagem() {
        return conteudoMensagem;
    }

    public void setConteudoMensagem(String conteudoMensagem) {
        this.conteudoMensagem = conteudoMensagem;
    }

    public Date getHorarioMensagem() {
        return horarioMensagem;
    }

    public void setHorarioMensagem(Date horarioMensagem) {
        this.horarioMensagem = horarioMensagem;
    }

    
    
}
