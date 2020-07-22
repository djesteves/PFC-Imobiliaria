/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import java.io.IOException;
import java.sql.Connection;

import java.util.Map;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperFillManager;

import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import util.ConnectionFactory;

/**
 *
 * @author Diego
 */
public class GeradorDeRelatoriosDAO {

    Connection conexao = ConnectionFactory.getConexao();
    
    public void geraPdf(String jrxml,
            Map<String, Object> parametros) throws IOException {

        try {

            // compila jrxml em memoria
            JasperReport jasper = JasperCompileManager.compileReport(jrxml);

            // preenche relatorio
            JasperPrint print = JasperFillManager.fillReport(jasper, parametros, this.conexao);

            // exporta para pdf
            //JasperViewer.viewReport(print);
            JasperExportManager.exportReportToPdfFile(print, "C:\\relatorio.pdf");
            Runtime.getRuntime().exec("cmd /c start C:\\relatorio.pdf");

            ConnectionFactory.FecharConexao();

        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar relatório", e);
        }
    }

}
