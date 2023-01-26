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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author neto-
 */
@Entity
@Table(name = "TB_LIKE")
@NamedQueries(
        {
            @NamedQuery(
                    name = "Like.PorQuemCurtiu",
                    query = "SELECT l FROM Like l LEFT JOIN l.quemCurtiu uc WHERE uc.nome = :nome" +
""
            ),
                
            @NamedQuery(
                    name = "Like.PorQuemFoiCurtido",
                    query = "SELECT l FROM Like l LEFT JOIN l.quemFoiCurtido uc WHERE uc.nome = :nome" +
""
            )
        }
)




public class Like implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ID_CURTIDOR", referencedColumnName = "ID",  nullable = false)
    private Usuario quemCurtiu;
    
    @Column(nullable = false)
    private boolean curtidaEnviada;
    
    @Column(nullable = false)
    private boolean curtidaRespondida;
    
    
    private String comentarioNaFoto;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ID_CURTIDO", referencedColumnName = "ID")
    private Usuario quemFoiCurtido;
    
    @ManyToMany(mappedBy = "likes", cascade = CascadeType.ALL, 
            fetch = FetchType.LAZY)
    private List<Combinacao> combinacoes;
    
    
    
   
    
    
    //Se as curtidas forem verdadeiras, adiciona os dois usuarios a uma combinacao
    // permitindo a troca de mensagens 
    public void adicionar(Combinacao combinacao){
        if(this.combinacoes == null){
            this.combinacoes = new ArrayList<>();
        }
        
        if(curtidaEnviada && curtidaRespondida){
            combinacoes.add(combinacao);
            
            
        }
             
    }

    public boolean isCurtidaEnviada() {
        return curtidaEnviada;
    }

    public void setCurtidaEnviada(boolean curtidaEnviada) {
        this.curtidaEnviada = curtidaEnviada;
    }

    public boolean isCurtidaRespondida() {
        return curtidaRespondida;
    }

    public void setCurtidaRespondida(boolean curtidaRespondida) {
        this.curtidaRespondida = curtidaRespondida;
    }

    public List<Combinacao> getCombinacoes() {
        return combinacoes;
    }

    public void setCombinacoes(List<Combinacao> combinacoes) {
        this.combinacoes = combinacoes;
    }

   
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getQuemCurtiu() {
        return quemCurtiu;
    }

    public void setQuemCurtiu(Usuario quemCurtiu) {
        this.quemCurtiu = quemCurtiu;
    
    }

    public Usuario getQuemFoiCurtido() {
        return quemFoiCurtido;
    }

    public void setQuemFoiCurtido(Usuario quemFoiCurtido) {
        this.quemFoiCurtido = quemFoiCurtido;
    }

    void setLikes(Like like) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getComentarioNaFoto() {
        return comentarioNaFoto;
    }

    public void setComentarioNaFoto(String comentarioNaFoto) {
        this.comentarioNaFoto = comentarioNaFoto;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.quemCurtiu);
        hash = 79 * hash + Objects.hashCode(this.quemFoiCurtido);
        hash = 79 * hash + Objects.hashCode(this.combinacoes);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Like other = (Like) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.quemFoiCurtido, other.quemFoiCurtido)) {
            return false;
        }
        return Objects.equals(this.combinacoes, other.combinacoes);
    }

    @Override
    public String toString() {
        return "Like{" + "id=" + id + ","
                + " quemCurtiu=" + quemCurtiu + ", "
                + "curtidaEnviada=" + curtidaEnviada + ", "
                + "curtidaRespondida=" + curtidaRespondida + ", "
                + "quemFoiCurtido=" + quemFoiCurtido + ", "
                + "combinacoes=" + combinacoes + '}';
    }
    
    
    
       
    
}
