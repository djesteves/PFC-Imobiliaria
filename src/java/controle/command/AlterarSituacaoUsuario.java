/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DAO.AdminDAO;

/**
 *
 * @author Diieg
 */
public class AlterarSituacaoUsuario implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        
        boolean alterado = false;
        String msg = "";
        int id = Integer.parseInt(request.getParameter("id"));
        
        AdminDAO dao = new AdminDAO();
        alterado = dao.AlterarSituacaoUsuario(id, request, response);
        msg = (String) request.getAttribute("msg");

        if (alterado) {
            request.setAttribute("msg", msg);
            return "index.jsp";
        } else {
            request.setAttribute("msgerro", request.getAttribute("msgerro"));
            return "erro.jsp";
        }
    }
    
}
