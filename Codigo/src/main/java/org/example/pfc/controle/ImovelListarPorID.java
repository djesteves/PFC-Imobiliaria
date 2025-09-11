/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.pfc.controle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.example.pfc.dao.ImovelDAO;
import org.example.pfc.modelo.Imovel;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            
            return "usuario/FormImovel.jsp";

        } catch (SQLException | NumberFormatException ex) {
            Logger.getLogger(ImovelListarPorID.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "index.jsp";
        }
    }
    
}
