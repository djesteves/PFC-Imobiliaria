/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.AgendamentoDAO;
import Modelo.Agendamento;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Diego
 */
public class AgendamentoLista implements ICommand {

    //DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            AgendamentoDAO dao = new AgendamentoDAO();
            Map<String, Object> session = (Map) request.getSession().getAttribute("usuarioLogado");

            int id = 0;

            if (request.getParameter("id") == null) {
                id = (int) session.get("id");
            } else {
                id = Integer.parseInt(request.getParameter("id"));
            }

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("id", id);
            parametros.put("modo", request.getParameter("modo"));

            List<Agendamento> listaAgendamento = dao.listarAgendamentos(parametros);

            request.setAttribute("listaAgendamento", listaAgendamento);
            return "Usuario/Agendamentos.jsp";
        } catch (SQLException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }

    }

}
