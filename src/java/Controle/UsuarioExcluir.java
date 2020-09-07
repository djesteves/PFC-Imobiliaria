/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Dao.UsuarioDAO;
import Modelo.Perfil;
import Modelo.Sessao;

/**
 *
 * @author Diieg
 */
public class UsuarioExcluir implements ICommand {

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
            UsuarioDAO dao = new UsuarioDAO();

            try {
                if (dao.excluir(id)) {
                    request.setAttribute("msg", "Usuário removido com sucesso!");
                    return "index.jsp";
                } else {
                    request.setAttribute("msgerro", "Não foi possivel a excluir o usuário");
                    return "index.jsp";
                }
            } catch (SQLException ex) {
                request.setAttribute("msgerro", ex.getMessage());
                System.err.println(ex.getMessage());
                return "index.jsp";
            }
        }
    }

}
