/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.DAO.ImovelDAO;

/**
 *
 * @author Diego
 */
public class ImovelConsultar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("imovel"));

        ResultSet rs;
        try {

            ImovelDAO dao = new ImovelDAO();

            rs = dao.consultar(id);

            request.setAttribute("rsImovel", rs);
            return "visualizarimovel.jsp";
        } catch (Exception ex) {
            Logger.getLogger(ImovelConsultar.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "erro.jsp";
        }

    }

}
