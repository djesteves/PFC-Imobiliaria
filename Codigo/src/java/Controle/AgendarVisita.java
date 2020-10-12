/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.AgendamentoDAO;
import Modelo.Agenda;
import Modelo.Agendamento;
import Modelo.Imovel;
import Modelo.Usuario;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diego
 */
public class AgendarVisita implements ICommand {

    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> session = (Map) request.getSession().getAttribute("usuarioLogado");

            Agenda agenda = new Agenda();

            agenda.getAgendamento().setDataAgendamento(formatter.parse(request.getParameter("dataagendamento")));
            agenda.getAgendamento().getImovel().setId_imovel(Integer.parseInt(request.getParameter("idimovel")));
            agenda.getAgendamento().getUsuario().setId_usuario(Integer.parseInt(session.get("id").toString()));
            agenda.getAgendamento().setSituacao("Ativo");
            agenda.getAgendamento().setStatus("Solicitado");
            agenda.getUsuarioCorretor().setId_usuario(Integer.parseInt(request.getParameter("corretores")));

            AgendamentoDAO dao = new AgendamentoDAO();

            dao.Agendar(agenda);

            request.setAttribute("msg", "O Agendamento foi realizado com sucesso, as informações foram enviadas por email");
            return "index.jsp";
        } catch (ParseException | SQLException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }

    }

}
