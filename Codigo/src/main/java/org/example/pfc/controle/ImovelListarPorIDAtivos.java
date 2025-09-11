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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Diego
 */
public class ImovelListarPorIDAtivos implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        Map<String, Object> session = (Map<String, Object>) request.getSession().getAttribute("usuarioLogado");

        ImovelDAO dao = new ImovelDAO();

        List<Imovel> listaImovel = null;

        try {
            listaImovel = dao.listarPorIDAtivos((int) session.get("id"));
        } catch (SQLException ex) {
            Logger.getLogger(ImovelListarPorIDAtivos.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "index.jsp";
        }

        request.setAttribute("listaImovel", listaImovel);
        return "usuario/GerenciarImoveis.jsp";

    }
}
