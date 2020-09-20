/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.ImovelDAO;
import Modelo.Imovel;

/**
 *
 * @author Diego
 */
public class ImovelListarPorID implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            ImovelDAO dao = new ImovelDAO();

            Imovel imovel = dao.listarPorId(id);

            request.setAttribute("imovel", imovel);
            
            return "Usuario/FormImovel.jsp";

        } catch (SQLException | NumberFormatException ex) {
            Logger.getLogger(ImovelListarPorID.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "index.jsp";
        }
    }

}
