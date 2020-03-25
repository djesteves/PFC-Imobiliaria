/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DAO.AdminDAO;

/**
 *
 * @author Diieg
 */
public class UsuariosListar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        ResultSet rs = null;
        try {

            AdminDAO dao = new AdminDAO();

            rs = dao.listarUsuarios();

            request.setAttribute("rsUsuarios", rs);
            return "Admin/GerenciarUsuarios.jsp";
        } catch (Exception ex) {
            Logger.getLogger(UsuariosListar.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "erro.jsp";
        }

    }

}
