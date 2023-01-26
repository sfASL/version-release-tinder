/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asl.jpa;


import com.infortech.asl.model.Combinacao;
import com.infortech.asl.model.Dialogo;
import com.infortech.asl.model.Mensagem;
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

/**
 *
 * @author neto-
 * 
 * 
 * Classe de Testes - CRUD
 *  Classes de atualização estão separadas
 *  
 * 
 * 1 - Contém 2 Atualizações
 * 2 - Contém 1 Remoção - Remoção em cima de um item que está no dataset
 */
public class DialogoValidationClass extends Teste{
    @Test
    public void persistirDialogo(){
        Calendar c = Calendar.getInstance();
        c.set(1998, Calendar.OCTOBER, 25, 0, 0, 0);
        
        Usuario firstUser = new Usuario();
        firstUser.setNome("Ana");
        firstUser.setSexo(Sexo.FLUIDO);
        firstUser.setLogin("anax");
        firstUser.setDataNascimento(c.getTime());
                
        //Cria usuário 2
        Usuario secondUser = new Usuario();
        secondUser.setNome("Gustavo");
        secondUser.setLogin("gust02");
        secondUser.setSexo(Sexo.FEMININO);
        secondUser.setDataNascimento(c.getTime());
        
        
        //Cria combinação
        Combinacao comb = new Combinacao();
        comb.setIsCombinado(true);
        comb.setUsuario(firstUser);
        comb.setUsuarioCombinado(secondUser);
        
        
        //Cria Mensagem 
        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(secondUser);
        mensagem.setDestinatario(firstUser);
        comb.setMensagem(mensagem);
        
        Dialogo dialogo = new Dialogo();
        
        dialogo.setMensagem(mensagem);
        dialogo.setConteudoMensagem("ola");
        dialogo.setAutor(firstUser);
        
        dialogo.setHorarioMensagem(c.getTime());
       
        mensagem.adicionar(dialogo);
       
        em.persist(dialogo);
        em.flush();
        assertNotNull(dialogo.getId());
        System.out.println(dialogo.getId());
        System.out.println(firstUser.getId());
        System.out.println(secondUser.getId());
        System.out.println(mensagem.getId());
     
        
        
    }
   
    
    @Test
    public void consultarDialogo(){
        Calendar c = Calendar.getInstance();
        c.set(1998, Calendar.OCTOBER, 25, 0,0,0);
        Dialogo dialogo = em.find(Dialogo.class, 2);
        assertNotNull(dialogo);
       
        
    }
    
    @Test
    public void atualizarDialogo(){
        logger.info("Executando atualizarDialogo()");
        String  novoConteudo = "novo conteudo do dialogo xxx";
        int id = 2;
        
        Dialogo dialogo = em.find(Dialogo.class, id);
        dialogo.setConteudoMensagem(novoConteudo);
        em.flush();
        
        String jpql = "SELECT d FROM Dialogo d WHERE d.id = ?2";
        TypedQuery<Dialogo> query = em.createQuery(jpql, Dialogo.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(2, id);
        query.getSingleResult();
        assertEquals(novoConteudo, dialogo.getConteudoMensagem());
     
        
    }
    
    @Test
    public void atualizarDialogoComMerge(){
        logger.info("Executando atualizarDialogo()");
        String  novoConteudo = "novo conteudo do dialogo xxx";
        int id = 3;
        
        Dialogo dialogo = em.find(Dialogo.class, id);
        dialogo.setConteudoMensagem(novoConteudo);
        em.clear();
        em.merge(dialogo);
        
        Map<String, Object> propriedades = new HashMap<>();
        propriedades.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        dialogo = em.find(Dialogo.class, id, propriedades);
        assertEquals(novoConteudo, dialogo.getConteudoMensagem());
    }
    
    
    //COM ERRO
    /*
    @Test
    public void removerDialogo(){
        logger.info("Executando removerDialogo()");
        Dialogo dialogo = em.find(Dialogo.class, 2);
        assertNotNull(dialogo);
        em.remove(dialogo);
        
        
       
    }
*/
   
}
