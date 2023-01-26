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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author neto-
 */
@Entity
@Table(name = "TB_MENSAGEM")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Mensagem.PorUserDestinatario",
                    query = "SELECT m FROM Mensagem m LEFT JOIN m.destinatario md WHERE md.id = 3" +
""
            )
        }
)
public class Mensagem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @OneToOne(mappedBy = "mensagem", optional = true, 
            fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Combinacao combinacao;
    
    @JoinColumn( name = "USER_REMETENTE")
    private Usuario remetente;
    
    @JoinColumn(name = "USER_DESTINATARIO")
    private Usuario destinatario;
    
    @Column (name = "TXT_OUTRA_MENSAGEM")
    private String enviarOutraMensagem;
    
    @Column (name = "TXT_EDITAR_MENSAGEM")
    private String editarMensagem;
    
    @OneToMany(mappedBy = "mensagem", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dialogo> dialogos;
    

    public List<Dialogo> getDialogos() {
        return dialogos;
    }

    
    public void adicionar(Dialogo dialogo){
        if(this.dialogos == null){
            this.dialogos = new ArrayList<>();
        }
        
        this.dialogos.add(dialogo);
        dialogo.setMensagem(this);
    }
    
    
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Combinacao getCombinacao() {
        return combinacao;
    }

    public void setCombinacao(Combinacao combinacao) {
        this.combinacao = combinacao;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public String getEnviarOutraMensagem() {
        return enviarOutraMensagem;
    }

    public void setEnviarOutraMensagem(String enviarOutraMensagem) {
        this.enviarOutraMensagem = enviarOutraMensagem;
    }

    public String getEditarMensagem() {
        return editarMensagem;
    }

    public void setEditarMensagem(String editarMensagem) {
        this.editarMensagem = editarMensagem;
    }

   
    
    
}
