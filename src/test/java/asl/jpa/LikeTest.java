/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asl.jpa;

import com.infortech.asl.model.Like;
import com.infortech.asl.model.Sexo;
import com.infortech.asl.model.Usuario;
import jakarta.persistence.CacheRetrieveMode;
import jakarta.persistence.TypedQuery;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author bea
 *  Classe de Testes - CRUD
 *  Classes de atualização estão separadas
 * 
 * 1 - Contém 2 Atualizações
 * 2 - Contém 1 Remoção - Remoção em cima de um item que está no dataset
 */
public class LikeTest extends Teste{
    
    @Test
    public void persistirLike(){
        Like like;
        like = criarLike();
        em.persist(like);
        em.flush();
        
        assertNotNull(like.getId());
        assertNotNull(like.getQuemCurtiu().getId());
        assertNotNull(like.getQuemFoiCurtido().getId());
    }
    
    
    @Test
    public void consultarLike(){
       Like like = em.find(Like.class, 2);
       assertNotNull(like);
       
       assertEquals(true, like.isCurtidaEnviada());
       assertEquals(true, like.isCurtidaRespondida());
       
       
       Usuario user1 = like.getQuemCurtiu();
       assertNotNull(user1);
       assertEquals("Bea", user1.getNome());
       
       Usuario user2 = like.getQuemFoiCurtido();
       assertNotNull(user2);
       assertEquals("José", user2.getNome());
       
    }
    
    
    private Like criarLike(){
       Like like = new Like();
       like.setCurtidaEnviada(true);
       like.setCurtidaRespondida(true);
       like.setQuemCurtiu(criarUsuario());
       like.setQuemFoiCurtido(criarUsuario2());
   
       return like;
    }
    
    
    private Usuario criarUsuario(){
        Usuario user = new Usuario();
        user.setNome("Caua");
        user.setLatitude(5000F);
        user.setLongitude(300F);
        Calendar c = Calendar.getInstance();
        c.set(1998, Calendar.OCTOBER, 15, 0, 0, 0);
        user.setDataNascimento(c.getTime());
        user.setLogin("cauaxx");
        user.setSenha("233231");
        user.setrEmail("caua@gmail.com");
        user.setSexo(Sexo.MASCULINO);
        user.addTelefone("2312223121");
        user.addTelefone("8123195040");
        System.out.println(user.getId());
        return user;
    }
    
    
    private Usuario criarUsuario2(){
      Usuario user = new Usuario();
      user.setNome("Gabi");
      user.setLatitude(5000F);
      user.setLongitude(300F);
      Calendar c = Calendar.getInstance();
      c.set(1998, Calendar.OCTOBER, 15, 0, 0, 0);
      user.setDataNascimento(c.getTime());
      user.setLogin("gabixx");
      user.setSenha("233231");
      user.setrEmail("gabi@gmail.com");
      user.setSexo(Sexo.FLUIDO);
      user.addTelefone("125698");
      user.addTelefone("01586");
       System.out.println(user.getId());
      return user;
    }
      
    @Test
    public void atualizarLike(){
        logger.info("Executanto atualizarLike()");
        String comentarioNaFoto = "oi, gostei da camiseta";
        boolean novoStatusDeCurtida = true;
        
        int id = 2;
        Like like = em.find(Like.class, id);
        like.setComentarioNaFoto(comentarioNaFoto);
        like.setCurtidaEnviada(novoStatusDeCurtida);
        em.flush();
        
        String jpql = "SELECT l FROM Like l WHERE l.id = ?2";
        TypedQuery<Like> query = em.createQuery(jpql, Like.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(2, id);
        like = query.getSingleResult();
        assertEquals(comentarioNaFoto, like.getComentarioNaFoto());
        assertEquals(novoStatusDeCurtida, like.isCurtidaEnviada());
        
    }
    
    @Test
    public void atualizarLikeComMerge(){
        logger.info("Executanto atualizarLikeComMerge()");
        String comentarioNaFoto = "podemos conversar? gostei de você";
        boolean novoStatusDeCurtida = true;
        
        int id = 3;
        Like like = em.find(Like.class, id);
        like.setComentarioNaFoto(comentarioNaFoto);
        like.setCurtidaEnviada(novoStatusDeCurtida);
        em.clear();
        em.merge(like);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        like = em.find(Like.class, id, properties);
        assertEquals(comentarioNaFoto, like.getComentarioNaFoto());
        assertEquals(novoStatusDeCurtida, like.isCurtidaEnviada());
        
    }
    
    @Test
    public void removerLike(){
        logger.info("Executando removerLike()");
        Like like = em.find(Like.class, 1);
        em.remove(like);
    }
    
    
    
    
}
