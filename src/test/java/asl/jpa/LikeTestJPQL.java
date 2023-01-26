/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asl.jpa;

import static asl.jpa.Teste.logger;
import com.infortech.asl.model.Like;
import com.infortech.asl.model.Usuario;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import org.hamcrest.CoreMatchers;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author bea
 * 
 */
public class LikeTestJPQL extends Teste{
    
    @Test
    public void likeUsuarioCurtidor(){
        logger.info("Executando likeUsuarioCurtidor()");
        TypedQuery<Like> query;
        query = em.createQuery(
        "SELECT l FROM Like l LEFT JOIN l.quemCurtiu uc WHERE uc.quemCurtiu = ?5",
        Like.class);
        List<Like> curtidasDoUusario = query.getResultList();
        curtidasDoUusario.forEach(usuario -> {
            assertEquals(5, usuario.getQuemCurtiu().getId());
        });
        assertEquals(2, curtidasDoUusario.size());
        
    }
    
    
    @Test
    public void selecionarLikesComLimite(){
        logger.info("Executando selecionarLikesComLimite()");
        TypedQuery<Like> query = em.createQuery(
             "SELECT l FROM Like l", Like.class);
        query.setMaxResults(2);
        List<Like> likes = query.getResultList();
        likes.forEach(like -> {
          assertEquals(2, likes.size());

     });
    }
    
    @Test
    public void selecionarTodosOsLikes(){
        logger.info("Executando selecionarTodosOsLikes()");
        TypedQuery<Like> query = em.createQuery(
            "SELECT l FROM Like l", Like.class);
        List<Like> likes = query.getResultList();
        likes.forEach(like -> {
          assertEquals(4, likes.size());
        });
    }
    
    
    @Test
    public void likesPorQuemCurtiu(){
       logger.info("Executando likesPorQuemCurtiu()");
       TypedQuery<Like> query = em.createNamedQuery("Like.PorQuemCurtiu", Like.class);
       query.setParameter("nome", "Bea");
       List<Like> likesDoUsuario = query.getResultList();
       likesDoUsuario.forEach(likes -> {
            assertTrue(likes.getQuemCurtiu().getNome().startsWith("Bea"));
        });
       
        assertEquals(2, likesDoUsuario.size());
    }
    
    
    @Test
    public void likesPorQuemFoiCurtido(){
       logger.info("Executando likesPorQuemFoiCurtido()");
       TypedQuery<Like> query = em.createNamedQuery("Like.PorQuemFoiCurtido", Like.class);
       query.setParameter("nome", "José");
       List<Like> likesDoUsuario = query.getResultList();
       likesDoUsuario.forEach(likes -> {
            assertTrue(likes.getQuemFoiCurtido().getNome().startsWith("José"));
        });
       
        assertEquals(2, likesDoUsuario.size());
    }
    
    
    @Test
    public void likesCurtidasRespondidas(){
       logger.info("Executando likesCurtidasRespondidas()");
       TypedQuery<Like> query;
        query = em.createQuery(
                "SELECT l FROM Like l "
                + "WHERE l.curtidaRespondida IN ('TRUE')",
                Like.class);
        List<Like> likesRespondidos = query.getResultList();
        likesRespondidos.forEach(likeRespondido -> {
            assertTrue(likeRespondido.isCurtidaRespondida());
        });
        
        assertEquals(2, likesRespondidos.size());
    }
    
    
    
    @Test
    public void usuariosQueCurtiram(){
        logger.info("Executando usuariosQueCurtiram()");
         TypedQuery<Long> query = em.createQuery(
                        "SELECT COUNT(u) FROM Usuario u WHERE u.pessoasQueCurtiram IS NOT EMPTY",
                        Long.class);
        Long resultado = query.getSingleResult();
        assertEquals(Long.valueOf(2), resultado); 
    }
    
    
    @Test
    public void usuariosQueForamCurtido(){
        logger.info("Executando usuariosQueForamCurtidos()");
         TypedQuery<Long> query = em.createQuery(
                        "SELECT COUNT(u) FROM Usuario u WHERE u.pessoasQueEuCurti IS NOT EMPTY",
                        Long.class);
        Long resultado = query.getSingleResult();
        assertEquals(Long.valueOf(3), resultado); 
    }
    
    
    @Test
    public void quemFoiCurtidoJoinFetch(){
        logger.info("Executando quemFoiCurtidoJoinFetch()");
        TypedQuery<Like> query = em.createQuery(
            "Select l FROM Like l JOIN FETCH l.quemFoiCurtido", Like.class);
        List<Like> likes = query.getResultList();
        System.out.println(likes);
        assertEquals("José", likes.get(0).getQuemFoiCurtido().getNome());
    }
    
    
    
    
  
}
