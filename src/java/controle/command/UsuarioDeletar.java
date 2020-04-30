/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DAO.AdminDAO;
import modelo.Perfil;
import modelo.Sessao;

/**
 *
 * @author Diieg
 */
public class UsuarioDeletar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        HttpSession usuarioLogado = request.getSession();
        Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");
        int id = Integer.parseInt(request.getParameter("id"));

        boolean autorizado = false;

        if (sessao.getNivel().equals(Perfil.ADMINISTRADOR)) {
            autorizado = true;
        }
        if (!sessao.getNivel().equals(Perfil.ADMINISTRADOR) && sessao.getId_usuario() == id) {
            autorizado = true;
        }

        if (!autorizado) {
            request.setAttribute("msgerro", "Você não tem permissão para excluir este Usuário");
            return "index.jsp";
        } else {
            AdminDAO dao = new AdminDAO();

            if (dao.excluirUsuario(id)) {
                request.setAttribute("msg", "Usuário removido com sucesso!");
                return "index.jsp";
            } else {
                request.setAttribute("msgerro", "Não foi possivel a excluir o usuário");
                return "index.jsp";
            }
        }
    }

}
