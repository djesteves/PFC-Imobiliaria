/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.UsuarioDAO;
import Modelo.Usuario;

/**
 *
 * @author Diieg
 */
public class UsuarioListarAtivos implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        try {
            UsuarioDAO dao = new UsuarioDAO();

            List<Usuario> listUsuario = dao.listarAtivos();

            request.setAttribute("listUsuario", listUsuario);
            return "Admin/GerenciarUsuarios.jsp";
        } catch (SQLException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }

    }

}
