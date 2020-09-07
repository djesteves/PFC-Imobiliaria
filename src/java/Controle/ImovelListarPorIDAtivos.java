/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Dao.ImovelDAO;
import Modelo.Imovel;
import Modelo.Sessao;

/**
 *
 * @author Diego
 */
public class ImovelListarPorIDAtivos implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession usuarioLogado = request.getSession();
            Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");

            ImovelDAO dao = new ImovelDAO();

            List<Imovel> listaImovel = dao.listarPorIDAtivos(sessao.getId_usuario());

            request.setAttribute("listaImovel", listaImovel);
            return "Usuario/GerenciarImoveis.jsp";

        } catch (Exception ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }
    }
}
