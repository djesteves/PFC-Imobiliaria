/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Dao.RelatorioDAO;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author Diego
 */
public class EmitirRelatorio implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        try {
            String relatorio = request.getParameter("nomerel");
            // acha jrxml dentro da aplicação
            ServletContext contexto = request.getServletContext();
            String jrxml = contexto.getRealPath("Resources/relatorios/" + relatorio + ".jrxml");
            // prepara parâmetros
            Map<String, Object> parametros = new HashMap<>();

            if ("RelAprovadosImoveis".equalsIgnoreCase(relatorio)) {
                parametros.put("datainicio", request.getParameter("datainicio"));
                parametros.put("datafinal", request.getParameter("datafinal"));
            }

            // gera relatório
            RelatorioDAO gerador = new RelatorioDAO();
            gerador.geraPdf(jrxml, parametros);
            return "Admin/Dashboard.jsp";
        } catch (JRException | IOException | SQLException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
            return "index.jsp";
        }

    }

}
