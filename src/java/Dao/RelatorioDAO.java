/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import Util.ConnectionFactory;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Diego
 */
public class RelatorioDAO {

    Connection conexao = ConnectionFactory.getConexao();

    public void geraPdf(String jrxml,
            Map<String, Object> parametros) throws IOException, JRException, SQLException {

        // compila jrxml em memoria
        JasperReport jasper = JasperCompileManager.compileReport(jrxml);

        // preenche relatorio
        JasperPrint print = JasperFillManager.fillReport(jasper, parametros, this.conexao);

        //visualiar relatorio
        //JasperViewer.viewReport(print, false);

        // exporta para pdf e executa (metodo ruim pois o bom seria gerar no servidor)
        JasperExportManager.exportReportToPdfFile(print, "C:\\relatorio.pdf");
        Runtime.getRuntime().exec("cmd /c start C:\\relatorio.pdf");

        conexao.close();
    }
}
