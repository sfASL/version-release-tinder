/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asl.jpa;

import static asl.jpa.Teste.logger;
import com.infortech.asl.model.Dialogo;
import com.infortech.asl.model.Sexo;
import com.infortech.asl.model.Usuario;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author bea
 */
public class UsuarioTestJPQL extends Teste{
    
    @Test
    public void usuarioPorNome(){
    logger.info("Executando usuarioPorSexo()");
    TypedQuery<Usuario> query = em.createQuery(
        "SELECT u FROM Usuario u WHERE u.nome LIKE :nome",
                Usuario.class);
        query.setParameter("nome", "Lucas");
        List<Usuario> usuarios = query.getResultList();
        usuarios.forEach(usuario -> {
            assertTrue(usuario.getNome().startsWith("Lucas"));
        });
        assertEquals(1, usuarios.size());
        
    }
    
    @Test
    public void usuarioPorNomeNamedQuery(){
       logger.info("Executando usuarioPorNomeNamedQuery()");
       TypedQuery<Usuario> query = em.createNamedQuery("Usuario.PorNome", Usuario.class);
       query.setParameter("nome", "Paulo");
       List<Usuario> usuarios = query.getResultList();
       
         usuarios.forEach(usuario -> {
            assertTrue(usuario.getNome().startsWith("Paulo"));
        });
         
        assertEquals(1, usuarios.size());
    }
    
    @Test
    public void maximaEMinimaDataNascimento() {
        logger.info("Executando maximaEMinimaDataNascimento()");
        Query query = em.createQuery(
                "SELECT MAX(u.dataNascimento), MIN(u.dataNascimento) FROM Usuario u");
        Object[] resultado = (Object[]) query.getSingleResult();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String maiorData = dateFormat.format((Date) resultado[0]);
        String menorData = dateFormat.format((Date) resultado[1]);
        assertEquals("23-08-2001", maiorData);
        assertEquals("28-09-1999", menorData);
    }
    
    
    @Test
    public void selecionarUsuariosComLimite(){
        logger.info("Executando selecionarUsuariosComLimite()");
        TypedQuery<Usuario> query = em.createQuery(
             "SELECT u FROM Usuario u", Usuario.class);
        query.setMaxResults(2);
        List<Usuario> usuarios = query.getResultList();
        usuarios.forEach(usuario -> {
          assertEquals(2, usuarios.size());

     });
    }
    
    
    @Test 
    public void selecionarTodosOsUsuarios(){
        logger.info("Executando selecionarTodosOsUsuarios()");
        TypedQuery<Usuario> query = em.createQuery(
            "SELECT u FROM Usuario u", Usuario.class);
        List<Usuario> usuarios = query.getResultList();
        usuarios.forEach(usuario -> {
          
          assertEquals(10, usuarios.size());
        });
    }
    
    
    
    @Test
    public void usuariosPorIntervaloDeDataDeNascimento(){
        logger.info("Executando usuariosPorIntervaloDeDataDeNascimento()");
        TypedQuery<Usuario> query;
        query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.dataNascimento BETWEEN ?1 AND ?2",
                Usuario.class);
        query.setParameter(1, getData(01, Calendar.JANUARY, 1998));
        query.setParameter(2, getData(01, Calendar.JANUARY, 2001));
        List<Usuario> usuariosPorDataDeNascimento = query.getResultList();
        assertEquals(7, usuariosPorDataDeNascimento.size());
    }
    
    
    @Test
    public void ordencaoNome(){
        logger.info("Executando ordenacaoNome()");
        TypedQuery<Usuario> query;
        query = em.createQuery("SELECT u FROM Usuario u WHERE u.distanciaDeAlcance = 20 "
                + "ORDER BY u.nome ASC", Usuario.class);
        List<Usuario> nomesUsuarios = query.getResultList();
        assertEquals(6, nomesUsuarios.size());
        assertEquals("Elena", nomesUsuarios.get(0).getNome());
        assertEquals("Lara", nomesUsuarios.get(1).getNome());
        assertEquals("Lucas", nomesUsuarios.get(2).getNome());
        assertEquals("Luis", nomesUsuarios.get(3).getNome());
        assertEquals("MORGAN", nomesUsuarios.get(4).getNome());
        assertEquals("Paulo", nomesUsuarios.get(5).getNome());
    }
    
    
    @Test
    public void usuariosPorOrientacaoSexual(){
        logger.info("Executando usuariosPorOrientacaoSexual()");
        TypedQuery<Usuario> query;
        query = em.createQuery("SELECT u FROM Usuario u WHERE u.sexo = :sexo ", 
                Usuario.class);
        query.setParameter("sexo", Sexo.FEMININO);
        List<Usuario> usuarios = query.getResultList();
        System.out.println(query);
        assertEquals(4, usuarios.size());
    
        }
    
    
    @Test
    public void usuarioJOINFETCH(){
        logger.info("Executando usuarioJOINFETCH()");
        TypedQuery<Usuario> query;
        query = em.createQuery("Select u FROM Usuario u JOIN FETCH u.quemFoiCurtido", 
                Usuario.class);
        List<Usuario> usuarios = query.getResultList();
        assertEquals(4, usuarios.size());
       
        }
    
    
    @Test
    public void quantidadeDePessoasQueEuCurti(){
        logger.info("Executando quantidadeDePessoasQueEuCurti()");
        TypedQuery<Usuario> query;
        query = em.createQuery(
                "SELECT u FROM Usuario u WHERE SIZE(u.pessoasQueEuCurti) >= ?1",
                Usuario.class);
        query.setParameter(1, 2);
        List<Usuario> usuarios = query.getResultList();
        assertEquals("José", usuarios.get(0).getNome());
        
       
    }
    
    
    


    
}
