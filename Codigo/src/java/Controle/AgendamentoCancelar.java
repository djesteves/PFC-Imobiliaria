/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.AgendamentoDAO;
import Modelo.Agendamento;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diego
 */
public class AgendamentoCancelar implements ICommand {

    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> session = (Map) request.getSession().getAttribute("usuarioLogado");

            String msgErro = "";
            Agendamento agendamento = new Agendamento();
            AgendamentoDAO dao = new AgendamentoDAO();

            agendamento.setDataAgendamento(fmt.parse(request.getParameter("data")));

            GregorianCalendar gc = new GregorianCalendar();

            gc.setTime(agendamento.getDataAgendamento());
            gc.add(Calendar.HOUR, 12);

            Date dataAgendamento = gc.getTime();

            if (new Date().compareTo(dataAgendamento) > 0 && !session.get("nivel").toString().equalsIgnoreCase("ADMINISTRADOR")) {
                msgErro = "Não é possivel cancelar o agendamento 12 (doze) horas antes da visita, entre em contato com o Administrador";
            }

            if (msgErro.equals("")) {
                agendamento.setId_agendamento(Integer.parseInt(request.getParameter("id")));
                agendamento.setSituacao("Inativo");
                dao.cancelar(agendamento);
                request.setAttribute("msg", "Agendamento foi cancelado com sucesso");
                return "index.jsp";
            } else {
                throw new Exception(msgErro);
            }
        } catch (Exception ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }
    }

}
