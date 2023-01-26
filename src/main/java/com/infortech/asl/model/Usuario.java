/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infortech.asl.model;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author neto-
 */

@Entity
@Table(name = "TB_USUARIO")
@DiscriminatorValue(value = "U")
@PrimaryKeyJoinColumn(name = "ID_PESSOA", referencedColumnName = "ID")


@NamedQueries(
        {
            @NamedQuery(
                    name = "Usuario.PorNome",
                    query = "SELECT u FROM Usuario u WHERE u.nome LIKE :nome ORDER BY u.id"
            )
        })

public class Usuario extends Pessoa{
    
    @Column(name = "SHORT_DISTANCIA_DE_ALCANCE")
    private short distanciaDeAlcance;
   
  
    @ElementCollection
    @CollectionTable(name = "TB_TELEFONE",
        joinColumns = @JoinColumn(name = "ID_USUARIO", nullable = false))
    @Column(name = "TXT_NUM_TELEFONE", nullable = false, length = 20)
    private Collection<String> telefones;
    
    
    @OneToMany(mappedBy = "quemCurtiu", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> pessoasQueCurtiram;
    //No perfil do usuario tem uma lista de pessoas que curtiram a foto dele
    
    
    
    @OneToMany(mappedBy = "quemFoiCurtido", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> pessoasQueEuCurti;
    //Lista de pessoas que ele curtiu
    
    
    
    public void adicionarPessoasQueCurtiram(Like curtiu){
        pessoasQueCurtiram.add(curtiu);
    }


    public void adicionarQuemEuCurti(Like euCurti){
        pessoasQueEuCurti.add(euCurti);
        
    }
    
    public void addTelefone(String contato) {
        if (telefones == null) {
            telefones = new HashSet<>();
        }
        telefones.add(contato);
    }
   
    
    public List<Like> getPessoasQueCurtiram() {
        return pessoasQueCurtiram;
    }

    public void setPessoasQueCurtiram(List<Like> pessoasQueCurtiram) {
        this.pessoasQueCurtiram = pessoasQueCurtiram;
    }

    public List<Like> getPessoasQueEuCurti() {
        return pessoasQueEuCurti;
    }

    public void setPessoasQueEuCurti(List<Like> pessoasQueEuCurti) {
        this.pessoasQueEuCurti = pessoasQueEuCurti;
    }
  

    public Collection<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Collection<String> telefones) {
        this.telefones = telefones;
    }

  
     
     
    
    public short getDistanciaDeAlcance() {
        return distanciaDeAlcance;
    }

    public void setDistanciaDeAlcance(short distanciaDeAlcance) {
        this.distanciaDeAlcance = distanciaDeAlcance;
    }

    public Usuario() {}
    
    
    
    
}       
