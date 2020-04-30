/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DAO.ImovelDAO;
import modelo.Imovel;
import modelo.Sessao;

/**
 *
 * @author Diego
 */
public class ImovelListarPorID implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession usuarioLogado = request.getSession();
            Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");

            ImovelDAO dao = new ImovelDAO();

            List<Imovel> listaImovel = dao.listarPorID(sessao.getId_usuario());

            request.setAttribute("listaImovel", listaImovel);
            return "Usuario/GerenciarImoveis.jsp";

        } catch (Exception ex) {
            request.setAttribute("msgerro", ex.getMessage());
            return "index.jsp";
        }
    }
}
