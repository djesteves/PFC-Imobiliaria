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
import java.util.logging.Level;
import java.util.logging.Logger;
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
            // acha jrxml dentro da aplicação
            ServletContext contexto = request.getServletContext();
            String jrxml = contexto.getRealPath("Resources/relatorios/teste.jrxml");
           
            // prepara parâmetros
            Map<String, Object> parametros = new HashMap<>();
            //parametros.put("curso", request.getParameter("curso_id"));

            // abre conexão com o banco
            Connection conexao = ConnectionFactory.getConexao();

            // gera relatório
            GeradorDeRelatorios gerador = new GeradorDeRelatorios(conexao);
            gerador.geraPdf(jrxml, parametros, response.getOutputStream());

            ConnectionFactory.FecharConexao();

        } catch (IOException ex) {
            Logger.getLogger(EmitirRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
