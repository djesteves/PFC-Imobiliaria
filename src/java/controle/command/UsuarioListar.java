/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DAO.AdminDAO;
import modelo.Usuario;

/**
 *
 * @author Diieg
 */
public class UsuarioListar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        try {
            AdminDAO AdminDAO = new AdminDAO();

            List<Usuario> listUsuario = AdminDAO.listarUsuarios();

            request.setAttribute("listUsuario", listUsuario);
            return "Admin/GerenciarUsuarios.jsp";
        } catch (SQLException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }

    }

}
