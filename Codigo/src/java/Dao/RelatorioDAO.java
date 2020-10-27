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
import java.io.File;
import java.nio.charset.Charset;
import java.util.Random;
import net.sf.jasperreports.engine.JasperExportManager;

/**
 *
 * @author Diego
 */
public class RelatorioDAO {

    Connection conexao = ConnectionFactory.getConexao();

    public String geraPdf(String jrxml,
            Map<String, Object> parametros) throws IOException, JRException, SQLException {

        // compila jrxml em memoria
        JasperReport jasper = JasperCompileManager.compileReport(jrxml);

        // preenche relatorio
        JasperPrint print = JasperFillManager.fillReport(jasper, parametros, this.conexao);

        //gerar nome random para o relatorio
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
        //Runtime.getRuntime().exec("cmd /c start C:\\relatorio.pdf");

        conexao.close();
        return generatedString + ".pdf";
    }
}
