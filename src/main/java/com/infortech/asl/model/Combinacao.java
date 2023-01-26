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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "TB_COMBINACAO")
public class Combinacao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO")
    private Usuario usuario;
    
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO_COMBINADO")
    private Usuario usuarioCombinado;
    
   
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MENSAGEM", referencedColumnName = "ID")
    private Mensagem mensagem;
    
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "TB_COMBINACOES_LIKES", joinColumns = {
        @JoinColumn(name = "ID_COMBINACAO")}, inverseJoinColumns = {
            @JoinColumn(name = "ID_LIKE")})
    private List<Like> likes;
    

    @Column(name = "BOOL_IS_COMBINADO")
    private boolean isCombinado;
    
    
    
     public void adicionar(Like like){
        if(this.likes == null){
            this.likes = new ArrayList<>();
        }
        
        
            likes.add(like);
            
            
       
             
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
    
 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioCombinado() {
        return usuarioCombinado;
    }

    public void setUsuarioCombinado(Usuario usuarioCombinado) {
        this.usuarioCombinado = usuarioCombinado;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
        this.mensagem.setCombinacao(this);
    }

    public boolean isIsCombinado() {
        return isCombinado;
    }

    public void setIsCombinado(boolean isCombinado) {
        this.isCombinado = isCombinado;
    }
    
    
    
}
