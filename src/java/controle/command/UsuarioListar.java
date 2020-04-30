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
import modelo.DAO.AdminDAO;
import modelo.Usuario;

/**
 *
 * @author Diieg
 */
public class UsuarioListar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        
        AdminDAO AdminDAO = new AdminDAO();

        List<Usuario> listUsuario = AdminDAO.listarUsuarios();

        request.setAttribute("listUsuario", listUsuario);
        return "Admin/GerenciarUsuarios.jsp";
        
    }

}
