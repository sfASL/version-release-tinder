/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asl.jpa;


/**
 *
 * @author neto-
 */





import com.infortech.asl.model.Sexo;
import com.infortech.asl.model.Usuario;
import jakarta.persistence.CacheRetrieveMode;
import jakarta.persistence.TypedQuery;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;





import org.junit.Test;

public class UsuarioTest extends Teste{
    
    @Test
    public void persistirUsuario(){
      
        Usuario user1 = criarUsuario();
        Usuario user2 = criarUsuario2();
        
     
        em.persist(user1);
        em.persist(user2);
        em.flush();
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
        
    }
    
    private Usuario criarUsuario(){
        Usuario user = new Usuario();
        user.setNome("Pedro");
        user.setLatitude(5000F);
        user.setLongitude(300F);
        Calendar c = Calendar.getInstance();
        c.set(1998, Calendar.OCTOBER, 15, 0, 0, 0);
        user.setDataNascimento(c.getTime());
        user.setLogin("loginqualquer");
        user.setSenha("233231");
        user.setrEmail("petra@gmail.com");
        user.setSexo(Sexo.FLUIDO);
        user.addTelefone("2312223121");
        user.addTelefone("8123195040");
        
        
        return user;
    }
    
    
    @Test
    public void consultarUsuario(){
     
        Usuario user =  em.find(Usuario.class, 1);
        assertNotNull(user);
        assertEquals("MORGAN", user.getNome());
        assertEquals("MGV@GMAIL.COM", user.getrEmail());
              
      
        }
    
    
    public Usuario criarUsuario2(){
        Usuario user2 = new Usuario();
        
        user2.setNome("maria eduarda");
        user2.setLatitude(5000F);
        user2.setLongitude(300F);
        Calendar c2 = Calendar.getInstance();
        c2.set(1998, Calendar.OCTOBER, 15, 0, 0, 0);
        user2.setDataNascimento(c2.getTime());
        user2.setLogin("maria");
        user2.setSenha("233231");
        user2.setrEmail("bea@gmail.com");
        user2.setSexo(Sexo.FLUIDO);
        user2.addTelefone("2312223121");
        user2.addTelefone("8123195040");
        return user2;
        
        
    }
    
    @Test
    public void atualizarUsuario(){
        logger.info("Executando atualizarUsuario()");
        String atualizarEmail = "usuarioAtualiza@gmail.com";
        String atualizaLogin = "usuarioLoginNovo";
        String novoTelefone = "(81) 99054-7851";
        int id = 1;
        Usuario user = em.find(Usuario.class, id);
        user.setrEmail(atualizarEmail);
        user.setLogin(atualizaLogin);
        user.addTelefone(novoTelefone);
        
        em.flush();
        
        String jpql = "SELECT u FROM Usuario u WHERE u.id = ?1";
        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        query.getSingleResult();
        assertEquals(atualizarEmail, user.getrEmail());
        assertEquals(atualizaLogin, user.getLogin());
        assertTrue(user.getTelefones().contains(novoTelefone));
    }
    
    
    @Test
    public void atualizarUsuarioComMerge(){
       logger.info("Executando atualizarUsuario2()");
       String atualizarEmail = "usuarioNovoEmai@gmail.com";
       String telefone = "(81) 8546-4521";
       int id = 2;
       
       Usuario user = em.find(Usuario.class, id);
       user.setrEmail(atualizarEmail);
       user.addTelefone(telefone);
       em.clear();
       em.merge(user);
       Map<String, Object> propriedades = new HashMap<>();
       propriedades.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
       user = em.find(Usuario.class, id, propriedades);
       assertEquals(atualizarEmail, user.getrEmail());
       assertTrue(user.getTelefones().contains(telefone));
    }
    
    
    @Test
    public void removerUsuario(){
        logger.info("Executando removerUsuario()");
        Usuario user = em.find(Usuario.class, 4);
        assertNotNull(user);
        em.remove(user);
       
    }
    
    
    
    
    
 

   
    
    
    
    }

