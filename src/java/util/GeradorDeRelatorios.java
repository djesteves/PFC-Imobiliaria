/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import java.util.Map;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperFillManager;

import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;

/**
 *
 * @author Diego
 */
public class GeradorDeRelatorios {

    private final Connection conexao;

    public GeradorDeRelatorios(Connection conexao) {
        this.conexao = conexao;
    }

    public void geraPdf(String jrxml,
            Map<String, Object> parametros) throws IOException {

        try {

            // compila jrxml em memoria
            JasperReport jasper = JasperCompileManager.compileReport(jrxml);

            // preenche relatorio
            JasperPrint print = JasperFillManager.fillReport(jasper, parametros, this.conexao);

            // exporta para pdf
            //JasperViewer.viewReport(print);
            JasperExportManager.exportReportToPdfFile(print, "C:/relatorio.pdf");
            Runtime.getRuntime().exec("cmd /c start C:/relatorio.pdf");

            File file = new File("C:/relatorio.pdf");
            file.deleteOnExit();

        } catch (JRException e) {
            throw new RuntimeException("Erro ao gerar relatório", e);
        }
    }

}
