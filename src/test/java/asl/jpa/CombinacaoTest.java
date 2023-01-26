///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package asl.jpa;
//
//import com.infortech.asl.model.Combinacao;
//import com.infortech.asl.model.Like;
//import com.infortech.asl.model.Mensagem;
//import com.infortech.asl.model.Sexo;
//import com.infortech.asl.model.Usuario;
//import java.util.Calendar;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import org.junit.Test;
//
///**
// *
// * @author bea
// */
//public class CombinacaoTest extends Teste{
//    
//    
//    
//    @Test
//    public void persistirCombinacao(){
//        Combinacao combinacao;
//        combinacao = criarCombinacao();
//        
//        em.persist(combinacao);
//        em.flush();
//        
//        assertNotNull(combinacao);
//        assertNotNull(combinacao.getUsuario().getId());
//        assertNotNull(combinacao.getUsuarioCombinado().getId());
//        
//        assertNotNull(combinacao.getMensagem().getId());
//        
//        
//    }
//    
//    
//    @Test
//    public void consultarCombinacao(){
//       Combinacao comb = em.find(Combinacao.class, 2L);
//       assertNotNull(comb);
//       
//       Usuario user1 = comb.getUsuario();
//       assertNotNull(user1);
//       assertEquals(2, user1.getId());
//       
//       
//       Usuario user2 = comb.getUsuarioCombinado();
//       assertNotNull(user2);
//       assertEquals(3, user2.getId());
//       
//       
//       assertEquals(true, comb.isIsCombinado());
//
//       
//        
//    }
//    
//    
//    private Combinacao criarCombinacao(){
//      Combinacao combinacao = new Combinacao();
//      combinacao.setIsCombinado(true);
//      combinacao.adicionar(criarLike());
//      
//      
//      combinacao.setMensagem(criarMensagem());
//        return combinacao;
//    }
//    
//    
//    private Mensagem criarMensagem(){
//      Mensagem mensagem = new Mensagem();
//      mensagem.setRemetente(criarUsuario());
//      mensagem.setDestinatario(criarUsuario2());
// 
//      return mensagem;
//    }
//    
//    private Like criarLike(){
//       Like like = new Like();
//       like.setCurtidaEnviada(true);
//       like.setCurtidaRespondida(true);
//       like.setQuemCurtiu(criarUsuario());
//       like.setQuemFoiCurtido(criarUsuario2());
//   
//       return like;
//    }
//    
//    
//    private Usuario criarUsuario(){
//        Usuario user = new Usuario();
//        user.setNome("Caua");
//        user.setLatitude(5000F);
//        user.setLongitude(300F);
//        Calendar c = Calendar.getInstance();
//        c.set(1998, Calendar.OCTOBER, 15, 0, 0, 0);
//        user.setDataNascimento(c.getTime());
//        user.setLogin("loginqualquer");
//        user.setSenha("233231");
//        user.setrEmail("caua@gmail.com");
//        user.setSexo(Sexo.MASCULINO);
//        user.addTelefone("2312223121");
//        user.addTelefone("8123195040");
//        return user;
//    }
//    
//    
//    private Usuario criarUsuario2(){
//        Usuario user = new Usuario();
//        user.setNome("Gabi");
//        user.setLatitude(5000F);
//        user.setLongitude(300F);
//        Calendar c = Calendar.getInstance();
//        c.set(1998, Calendar.OCTOBER, 15, 0, 0, 0);
//        user.setDataNascimento(c.getTime());
//        user.setLogin("loginqualquer");
//        user.setSenha("233231");
//        user.setrEmail("gabi@gmail.com");
//        user.setSexo(Sexo.FLUIDO);
//        user.addTelefone("2312223121");
//        user.addTelefone("8123195040");
//        return user;
//    }
//    
//    
//}
