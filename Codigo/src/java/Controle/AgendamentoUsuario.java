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
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diego
 */
public class AgendamentoUsuario implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        try {
            AgendaDAO dao = new AgendaDAO();
            Map<String, Object> session = (Map) request.getSession().getAttribute("usuarioLogado");

            int id = Integer.parseInt(session.get("id").toString());

            List<Agenda> listaAgenda = dao.listarAgendaUsuario(id);

            request.setAttribute("listaAgenda", listaAgenda);
            return "Usuario/AgendamentoUsuario.jsp";
        } catch (SQLException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }

    }

}
