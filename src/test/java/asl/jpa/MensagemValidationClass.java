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
import org.junit.Test;

/**
 *
 * @author neto-
 * 
 *  * Classe de Testes - CRUD
 *  Classes de atualização estão separadas
 * 
 * 1 - Contém 2 Atualizações
 * 2 - Contém 1 Remoção - Remoção em cima de um item que está no dataset
 */
public class MensagemValidationClass extends Teste{
    
    
    @Test
    public void persistirMensagem(){
        Calendar c = Calendar.getInstance();
        c.set(1998, Calendar.OCTOBER, 15, 0, 0, 0);
        
        Usuario firstUser = new Usuario();
        firstUser.setNome("Joaozinho");
        firstUser.setSexo(Sexo.FLUIDO);
        firstUser.setLogin("jjt");
        firstUser.setDataNascimento(c.getTime());
                
        //Cria usuário 2
        Usuario secondUser = new Usuario();
        secondUser.setNome("Maria");
        secondUser.setLogin("maria02");
        secondUser.setSexo(Sexo.FEMININO);
        secondUser.setDataNascimento(c.getTime());
        
        
        Mensagem mensagem = new Mensagem();
        mensagem.setRemetente(secondUser);
        mensagem.setDestinatario(firstUser);
  
        
        //Cria combinação
        Combinacao comb = new Combinacao();
        comb.setIsCombinado(true);
        comb.setUsuario(firstUser);
        comb.setUsuarioCombinado(secondUser);
        comb.setMensagem(mensagem);
        
        Dialogo dialogo = new Dialogo();
        
        dialogo.setMensagem(mensagem);
        dialogo.setConteudoMensagem("ola");
        dialogo.setAutor(firstUser);
        
        dialogo.setHorarioMensagem(c.getTime());
        mensagem.adicionar(dialogo);
        
        em.persist(mensagem);
        em.flush();
        assertNotNull(mensagem.getId());
        
        
    }
    
    
    
    @Test
    public void consultarMensagem(){
        Mensagem mensagem = em.find(Mensagem.class, 2);
        assertNotNull(mensagem);
        assertEquals("MORGAN", mensagem.getRemetente().getNome());
        assertEquals("José", mensagem.getDestinatario().getNome());
    }
    
    
    @Test
    public void atualizarMensagem(){
        logger.info("Executanto atualizarMensagem()");
        String novaMensagem = "Oi, ta disponivel para jogar?";
        String editarMensagem = "Te mandei meu usuario do twitter";
        
        int id = 2;
        Mensagem mensagem = em.find(Mensagem.class, id);
        mensagem.setEnviarOutraMensagem(novaMensagem);
        mensagem.setEditarMensagem(editarMensagem);
        em.flush();
        
        String jpql = "SELECT m FROM Mensagem m WHERE m.id = ?1";
        TypedQuery<Mensagem> query = em.createQuery(jpql, Mensagem.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        mensagem = query.getSingleResult();
        assertEquals(novaMensagem, mensagem.getEnviarOutraMensagem());
        assertEquals(editarMensagem, mensagem.getEditarMensagem());
        
    }
    
    
    
      @Test
    public void atualizarMensagemMerge(){
        logger.info("Executanto atualizarMensagemMerge()");
        String novaMensagem = "foi bom jogar valorant";
        String editarMensagem = "foi bom jogar valorant e lol";
        
        int id = 3;
        Mensagem mensagem = em.find(Mensagem.class, id);
        mensagem.setEnviarOutraMensagem(novaMensagem);
        mensagem.setEditarMensagem(editarMensagem);
        
        em.clear();
        em.merge(mensagem);
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        mensagem = em.find(Mensagem.class, id, properties);
        assertEquals(novaMensagem, mensagem.getEnviarOutraMensagem());
        assertEquals(editarMensagem, mensagem.getEditarMensagem());
    }
    
    
    @Test
    public void removerMensagem(){
        logger.info("Executando removerMensagem()");
        Mensagem mensagem = em.find(Mensagem.class, 4);
        em.remove(mensagem);
        
        
        mensagem = em.find(Mensagem.class, 4);
        assertEquals(null, mensagem);
    }
    
    
    
    
}
