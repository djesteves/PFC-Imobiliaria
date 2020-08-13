/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DAO.ImovelDAO;
import modelo.Perfil;
import modelo.Sessao;

/**
 *
 * @author Diego
 */
public class ImovelDeletar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpSession usuarioLogado = request.getSession();
            Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");
            boolean autorizado = false;

            if (sessao.getNivel().equals(Perfil.ADMINISTRADOR)) {
                autorizado = true;
            }
            if (!sessao.getNivel().equals(Perfil.ADMINISTRADOR) && sessao.getId_usuario() == Integer.parseInt(request.getParameter("idu"))) {
                autorizado = true;
            }

            if (!autorizado) {
                request.setAttribute("msgerro", "Você não tem permissão para excluir este Imóvel");
                return "index.jsp";
            } else {

                int id = Integer.parseInt(request.getParameter("id"));
                ImovelDAO dao = new ImovelDAO();
                boolean deletado = dao.deletarImovel(id);

                if (deletado) {
                    request.setAttribute("msg", "Imóvel deletado com sucesso!");
                    return "index.jsp";
                } else {
                    request.setAttribute("msgerro", "Ocorreu um erro ao tentar deletar o imóvel!");
                    return "index.jsp";
                }
            }
        } catch (SQLException | NumberFormatException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }
    }
}
