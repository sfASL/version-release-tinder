/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asl.jpa;

import static asl.jpa.Teste.logger;
import com.infortech.asl.model.Dialogo;
import com.infortech.asl.model.Like;
import jakarta.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author bea
 * 
 *  Classe de busca de Dialogo
 *  Todas as buscas estao no dataset
 *  Trabalhando com listas
 *  Buscas em JPQL
 * 
 *
 * 2 - Busca com parametros numerados e nomeados - dialogoPorNomeDoAutor()
 * 3 - Busca com limite de query - selecionarDialogosComLimite()
 * 4 - Seleciona uma entidade a partir de outra - dialogoPorNomeDoAutor()
 * 5 - LeftJoin - dialogoPorNomeDoAutor()
 *  
 * 
 */
 
public class DialogoTesteJPQL extends Teste{
    
    
    
    @Test
    public void dialogoPorNomeDoAutor(){
        logger.info("Executando dialogoPorNomeDoAutor()");
        TypedQuery<Dialogo> query = em.createQuery(
                "SELECT d FROM Dialogo d"
                        + " LEFT JOIN d.autor u WHERE u.nome = :nome",
        Dialogo.class);
        query.setParameter("nome", "MORGAN");
        List<Dialogo> autoresDoDialogo = query.getResultList();
        autoresDoDialogo.forEach(autores -> {
            assertEquals("MORGAN", autores.getAutor().getNome());
           
        });
        assertEquals(3, autoresDoDialogo.size());
        

    }
    
    @Test
    public void selecionarDialogosComLimite(){
        logger.info("Executando qtdMensagensEnviadas()");
         TypedQuery<Dialogo> query = em.createQuery(
                "SELECT d FROM Dialogo d", Dialogo.class);
        query.setMaxResults(2);
        List<Dialogo> dialogos = query.getResultList();
        dialogos.forEach(autores -> {
             assertEquals(2, dialogos.size());
            
        });
       
    }
    
    @Test
    public void selecionarDialogosSemLimite(){
        logger.info("Executando selecionarDialogosSemLimite()");
        TypedQuery<Dialogo> query = em.createQuery(
             "SELECT d FROM Dialogo d", Dialogo.class);
        List<Dialogo> dialogos = query.getResultList();
        dialogos.forEach(dialogo -> {
          assertEquals(3, dialogos.size());

     });
    }
    
    
    @Test
    public void dialogoPorAutorNamedQuery(){
       logger.info("Executando dialogoPorAutorNamedQuery()");
       TypedQuery<Dialogo> query = em.createNamedQuery("Dialogo.PorNomeDoAutor", Dialogo.class);
       query.setParameter("nome", "MORGAN");
       List<Dialogo> dialogosDoAutor = query.getResultList();
       dialogosDoAutor.forEach(dialogos -> {
            assertEquals("MORGAN", dialogos.getAutor().getNome());
        });
       
        assertEquals(4, dialogosDoAutor.size());
    }
    
    
    @Test
    public void dialogoPorDataDeEnvio(){
        logger.info("Executando dialogoPorDataDeEnvio()");
        TypedQuery<Dialogo> query;
        query = em.createQuery(
                "SELECT d FROM Dialogo d WHERE d.dataEnvio BETWEEN ?1 AND ?2",
                Dialogo.class);
        query.setParameter(1, getData(01, Calendar.OCTOBER, 2022));
        query.setParameter(2, getData(11, Calendar.NOVEMBER, 2022));
        List<Dialogo> dialogosPorData = query.getResultList();
        assertEquals(2, dialogosPorData.size());
    }
    
    @Test
    public void mensagemEAutorDeDialogoJoinFetch(){
        logger.info("Executando quemFoiCurtidoJoinFetch()");
        TypedQuery<Dialogo> query = em.createQuery(
            "Select DISTINCT d FROM Dialogo d JOIN FETCH d.autor JOIN FETCH d.mensagem"
                    + " WHERE d.autor.nome = :autor", Dialogo.class);
        query.setParameter("autor", "MORGAN");
        query.setMaxResults(2);
        List<Dialogo> mensagensDialogos = query.getResultList();

        assertEquals("MORGAN", mensagensDialogos.get(0).getAutor().getNome());
        assertEquals("Te mandei meu user do twitter", mensagensDialogos.get(0).getMensagem()
                .getEnviarOutraMensagem());
        assertEquals("voce tem instagram?", mensagensDialogos.get(1).getMensagem()
                .getEnviarOutraMensagem());

    }

}
