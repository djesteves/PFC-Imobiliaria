/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.pfc.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;
import java.util.Random;

import org.example.pfc.util.ConnectionFactory;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Diego
 */
public class RelatorioDAO {

    Connection conexao = ConnectionFactory.getConexao();

    public String geraPdf(InputStream jrxml,
            Map<String, Object> parametros) throws Exception {

        // Compila o relatório diretamente do InputStream
        JasperDesign design = JRXmlLoader.load(jrxml);
        JasperReport jasper = JasperCompileManager.compileReport(design);

        // preenche relatorio
        JasperPrint print = JasperFillManager.fillReport(jasper, parametros, conexao);

        // gerar nome random para o relatorio
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 15;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString().toUpperCase();

        // exporta para pdf e executa
        JasperExportManager.exportReportToPdfFile(print, parametros.get("path") + generatedString + ".pdf");
        // Runtime.getRuntime().exec("cmd /c start C:\\relatorio.pdf");

        conexao.close();
        return generatedString + ".pdf";
    }
}
