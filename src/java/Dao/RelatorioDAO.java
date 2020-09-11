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
import net.sf.jasperreports.engine.JasperExportManager;
import Util.ConnectionFactory;

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

        //imprimi utilizando a engine do jasperviewer (só em modo dev)
        //JasperViewer.viewReport(print);
        //não usar, sempre que fecha o jasperviewer ele invoca o metodo de destroy da servlet
        
        // exporta para pdf executando na maquina - cliente
        JasperExportManager.exportReportToPdfFile(print, "C:\\relatorio.pdf");
        Runtime.getRuntime().exec("cmd /c start C:\\relatorio.pdf");

        conexao.close();

    }

}
