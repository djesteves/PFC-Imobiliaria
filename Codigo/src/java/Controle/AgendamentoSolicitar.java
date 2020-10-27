/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import Dao.AgendamentoDAO;
import Modelo.Agendamento;
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
public class AgendamentoSolicitar implements ICommand {

    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> session = (Map) request.getSession().getAttribute("usuarioLogado");

            Agendamento agendamento = new Agendamento();

            int id_corretor = 0;
            String msgErro = "";

            agendamento.setDataAgendamento(formatter.parse(request.getParameter("dataagendamento")));
            agendamento.getImovel().setId_imovel(Integer.parseInt(request.getParameter("idimovel")));
            agendamento.getUsuario().setId_usuario(Integer.parseInt(session.get("id").toString()));
            agendamento.setSituacao("Ativo");
            agendamento.setStatus("Confirmado");

            AgendamentoDAO dao = new AgendamentoDAO();

            if ("AutoAlocar".equalsIgnoreCase(request.getParameter("corretores"))) {
                id_corretor = dao.alocaCorretor(agendamento);
                agendamento.getUsuarioCorretor().setId_usuario(id_corretor);

                if (id_corretor == 0) {
                    msgErro = "Nenhum corretor disponivel para a data e hora desejada!";
                }
            } else {
                id_corretor = Integer.parseInt(request.getParameter("corretores"));
                agendamento.getUsuarioCorretor().setId_usuario(id_corretor);

                if (dao.disponibilidadeCorretor(agendamento)) {
                    msgErro = "O corretor selecionado não está disponivel para a data e hora desejada!";
                }
            }

            if (id_corretor != 0 && msgErro == "") {
                dao.Agendar(agendamento);
            }

            if (msgErro != "") {
                request.setAttribute("msgerro", msgErro);
                return "index.jsp";
            } else {
                request.setAttribute("msg", "O Agendamento foi realizado com sucesso, as informações foram enviadas por email");
                return "index.jsp";
            }
        } catch (ParseException | SQLException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "AgendamentoSolicitar.jsp";
        }

    }

}
