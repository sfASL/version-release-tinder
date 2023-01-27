/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asl.jpa;

import static asl.jpa.Teste.logger;
import com.infortech.asl.model.Dialogo;
import com.infortech.asl.model.Like;
import com.infortech.asl.model.Mensagem;
import com.infortech.asl.model.Usuario;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author bea
 * 
 * Classe de busca de Mensagem
 *  Todas as buscas estao no dataset
 *  Trabalhando com listas
 *  Buscas em JPQL
 * 
 * 1 - Busca simples (Sem limite de query)
 * 2 - Busca com parametros numerados e nomeados - mensagemPorRemetente()
 * 3 - Busca com limite de query - destinatarioAtravesDeMensagem()
 * 4 - Seleciona uma entidade a partir de outra - mensagemPorRemetente()
 * 5 - LeftJoin - mensagemRemetLeftJoin()
 *  
 * 
 */
public class MensagemTesteJPQL extends Teste{
    
   // 5, 1
    @Test
    public void mensagemRemetLeftJoin(){
        logger.info("Executando mensagemRemetLeftJoin()");
        TypedQuery<Object[]> query;
        query = em.createQuery(
        "SELECT m.enviarOutraMensagem, mr.nome FROM Mensagem m LEFT JOIN "
                + "m.remetente mr ORDER BY m.id",
        Object[].class);
        List<Object[]> mensagemRemetente = query.getResultList();
        assertEquals(3, mensagemRemetente.size());
    }
    
    // 4, 1
    @Test
    public void mensagemPorRemetente(){
        logger.info("Executando mensagemPorRemetente()");
        TypedQuery<Mensagem> query;
        query = em.createQuery(
        "SELECT m FROM Mensagem m LEFT JOIN m.remetente mr WHERE mr.nome = :nome",
        Mensagem.class);
        query.setParameter("nome", "MORGAN");
        List<Mensagem> mensagens =  query.getResultList();
        mensagens.forEach(mensagem -> {
            assertEquals("MORGAN", mensagem.getRemetente().getNome());
        });
        assertEquals(3, mensagens.size());
        
    }
    
    // 3
    
    @Test
    public void destinatarioAtravesDeMensagem(){
        logger.info("Executando destinatarioAtravesDeMensagem()");
        TypedQuery<Usuario> query;
        query = em.createQuery(
        "SELECT m.destinatario FROM Mensagem m ORDER BY m.id ASC",
        Usuario.class);
        query.setMaxResults(2);
        List<Usuario> usuarios =  query.getResultList();
        usuarios.forEach(usuario -> {
            assertEquals("José", usuario.getNome());
        });
        assertEquals(2, usuarios.size());
                
    }
    
    @Test
    public void selecionarTodasAsMensagens(){
        logger.info("Executando selecionarTodasAsMensagens()");
        TypedQuery<Mensagem> query = em.createQuery(
            "SELECT l FROM Mensagem l", Mensagem.class);
        List<Mensagem> mensagens = query.getResultList();
        mensagens.forEach(like -> {
          assertEquals(3, mensagens.size());
        });
    }
    
    @Test
    public void mensagemPorDestinatarioNamedQuery(){
        logger.info("Executando mensagemPorDestinatarioNamedQuery()");
        TypedQuery<Mensagem> query = em.createNamedQuery("Mensagem.PorUserDestinatario", 
                Mensagem.class);
        List<Mensagem> mensagens = query.getResultList();
        mensagens.forEach(mensagem -> {
            assertEquals(3, mensagem.getDestinatario().getId());
        });
        assertEquals(3, mensagens.size());
    }
    
    @Test
    public void categoriaQuantidadeItens() {
        logger.info("Executando categoriaQuantidadeItens()");
        Dialogo dialogo = em.find(Dialogo.class, 2);
        TypedQuery<Mensagem> query;
        query = em.createQuery("SELECT m FROM Mensagem m "
                + "WHERE :dialogo MEMBER OF m.dialogos", Mensagem.class);
        query.setParameter("dialogo", dialogo);
        Mensagem mensagem = query.getSingleResult();
        
        assertEquals("MORGAN", mensagem.getDialogos().get(0).getAutor().getNome());
        assertEquals("tudo bem?", mensagem.getDialogos().get(0).getConteudoMensagem());
        
    }
}

    

