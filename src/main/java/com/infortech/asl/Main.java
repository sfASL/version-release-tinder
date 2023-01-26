/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.infortech.asl;

import com.infortech.asl.model.Combinacao;
import com.infortech.asl.model.Like;
import com.infortech.asl.model.Mensagem;
import com.infortech.asl.model.Sexo;
import com.infortech.asl.model.Usuario;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Peu Souza
 */
public class Main {
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("contatinhosPU");
    
    public static void main(String[] args) {
//        persistirUsuario();
        gerarCombinacao();
    }
    
    public static void persistirUsuario(){
        Usuario user = new Usuario();
        
        user.setNome("Pedro");
        user.setLatitude(202203);
        user.setLongitude(222020);
        user.setLogin("pd0023");
        user.setSenha("pdp2p30");
        user.setSexo(Sexo.MASCULINO);
        user.setrEmail("pd0023@gmail.com");
       
        
        EntityManager em = null;
        EntityTransaction et;
        
        try {
            em = emf.createEntityManager();
            et = em.getTransaction();
            et.begin();
            em.persist(user);
            et.commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        
    } 
    
    public static void gerarCombinacao(){
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        
        //Users
        Usuario user1 = new Usuario();
        user1.setNome("Pedro");
        user1.setSexo(Sexo.MASCULINO);
        user1.setLogin("pedro01");
        
        Usuario user2 = new Usuario();
        user2.setNome("Maria");
        user2.setSexo(Sexo.FEMININO);
        user2.setLogin("maria01");
        
        //Mensagem
        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(user1);
        mensagem.setDestinatario(user2);
        
        //Likes
        Like like1 = new Like();
        like1.setQuemCurtiu(user1);
        like1.setQuemCurtiu(user2);
         
        Like like2 = new Like();
        like2.setQuemCurtiu(user2);
        like2.setQuemCurtiu(user1);
        
        List<Like> listOfLikes = new ArrayList<>();
        
        listOfLikes.add(like1);
        listOfLikes.add(like2);
        
        //Combinação
        Combinacao combinacao = new Combinacao();
        
        combinacao.setUsuario(user1);
        combinacao.setUsuarioCombinado(user2);
        combinacao.setMensagem(mensagem);
        combinacao.setLikes(listOfLikes);
        combinacao.setIsCombinado(true);
        
        try {
            et.begin();
            em.persist(user1);
            em.persist(user2);
            em.persist(mensagem);
            em.persist(combinacao);
            et.commit();
        } catch(Exception e) {
            
        }finally {
            em.close();
        }        
    }
}
