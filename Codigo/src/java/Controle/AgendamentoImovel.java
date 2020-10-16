/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.AgendaDAO;
import Modelo.Agenda;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diego
 */
public class AgendamentoImovel implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        try {
            AgendaDAO dao = new AgendaDAO();

            int id = Integer.parseInt(request.getParameter("id"));

            List<Agenda> listaAgenda = dao.listarAgendaImovel(id);

            request.setAttribute("listaAgenda", listaAgenda);
            return "Usuario/AgendamentoImovel.jsp";
        } catch (SQLException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }

    }

}
