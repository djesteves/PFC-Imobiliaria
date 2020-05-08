/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import java.util.List;
import modelo.Imovel;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Diego
 */
public class UsuarioDAOTest {

    public UsuarioDAOTest() {
    }

    @Ignore
    @Test
    public void cadastrar() {

        Imovel imovel = new Imovel();

        imovel.setDiretorioimg("Teste");

        imovel.setTitulo("TesteInserção");
        imovel.setDescricao("TesteDescrição");
        imovel.setComodos(1);
        imovel.setBanheiros(1);
        imovel.setVagas_garagem(1);
        imovel.setValor(200.00);
        imovel.setArea_total(15.00);
        imovel.setArea_edificada(15.00);
        imovel.setTipo_imovel("Apartamento");
        imovel.getUsuario().setId_usuario(1);
        imovel.getEndereco().setLogradouro("Teste");
        imovel.getEndereco().setNumero(1);
        imovel.getEndereco().setComplemento("Teste");
        imovel.getEndereco().setCidade("Teste");
        imovel.getEndereco().setEstado("Teste");
        imovel.getEndereco().setCep("07400000");
        imovel.getEndereco().setBairro("Teste");

        ImovelDAO dao = new ImovelDAO();

        if (dao.cadastrar(imovel)) {
            System.out.println("Imóvel cadastrado");
        } else {
            System.out.println("Ocorreu um erro ao cadastrar");
        }
    }
    
    @Ignore
    @Test
    public void alterar() {

        Imovel imovel = new Imovel();

        imovel.setDiretorioimg("TesteAlteração");

        imovel.setTitulo("TesteAlteração");
        imovel.setDescricao("TesteAlteração");
        imovel.setComodos(1);
        imovel.setBanheiros(1);
        imovel.setVagas_garagem(1);
        imovel.setValor(200.00);
        imovel.setArea_total(15.00);
        imovel.setArea_edificada(15.00);
        imovel.setTipo_imovel("Apartamento");
        imovel.setId_imovel(14);
        imovel.getEndereco().setId_endereco(18);
        imovel.getEndereco().setLogradouro("Teste");
        imovel.getEndereco().setNumero(1);
        imovel.getEndereco().setComplemento("Teste");
        imovel.getEndereco().setCidade("Teste");
        imovel.getEndereco().setEstado("Teste");
        imovel.getEndereco().setCep("07400000");
        imovel.getEndereco().setBairro("Teste");

        ImovelDAO dao = new ImovelDAO();

        if (dao.alterar(imovel)) {
            System.out.println("Imóvel alterado com sucesso");
        } else {
            System.out.println("Ocorreu um erro ao tentar atualizar o imóvel");
        }
    }
    
    @Test
    public void listar(){
        ImovelDAO dao = new ImovelDAO();
        
        List<Imovel> listaImovel = dao.listarPorID(1);
        
        for (Imovel i : listaImovel){
            System.out.println("Imovel: "+ i.getTitulo());
        }
    }
}
