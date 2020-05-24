/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.ConnectionFactory;
import util.GeradorDeRelatorios;

/**
 *
 * @author Diego
 */
public class EmitirRelatorio implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        try {
            String rel = request.getParameter("nomerel");
            // acha jrxml dentro da aplicação
            ServletContext contexto = request.getServletContext();
            String jrxml = contexto.getRealPath("Resources/relatorios/"+rel+".jrxml");
            // prepara parâmetros
            Map<String, Object> parametros = new HashMap<>();
            
            if("RelAprovadosImoveis".equalsIgnoreCase(rel)){
                parametros.put("datainicio", request.getParameter("datainicio"));
                parametros.put("datafinal", request.getParameter("datafinal"));
            }
            
            // abre conexão com o banco
            Connection conexao = ConnectionFactory.getConexao();
            // gera relatório
            GeradorDeRelatorios gerador = new GeradorDeRelatorios(conexao);
            gerador.geraPdf(jrxml, parametros);
            ConnectionFactory.FecharConexao();
            return "Admin/Dashboard.jsp";
        } catch (IOException ex) {
            request.setAttribute("msgerro", ex.getMessage());
            return "index.jsp";
        }

    }

}
