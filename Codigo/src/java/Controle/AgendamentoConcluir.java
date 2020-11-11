/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.AgendamentoDAO;
import Modelo.Agendamento;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diego
 */
public class AgendamentoConcluir implements ICommand {
    
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {

            Agendamento agendamento = new Agendamento();
            AgendamentoDAO dao = new AgendamentoDAO();

            agendamento.setId_agendamento(Integer.parseInt(request.getParameter("id")));
            agendamento.setStatus("Concluido");
            dao.concluir(agendamento);

            request.setAttribute("msg", "Agendamento concluido com sucesso");
            return "index.jsp";

        } catch (SQLException | NumberFormatException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }

    }
    
}
