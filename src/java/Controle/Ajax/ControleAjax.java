/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.Ajax;

import Dao.ImovelDAO;
import Modelo.Imovel;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diieg
 */
@WebServlet(name = "ServletAjax", urlPatterns = {"/ImovelListarAprovados"})
@MultipartConfig
public class ControleAjax extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        try {

        } catch (Exception ex) {
            Logger.getLogger(ControleAjax.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();
        PrintWriter out = response.getWriter();

        try {
            if (uri.equals(request.getContextPath() + "/ImovelListarAprovados")) {

                ImovelListarAprovados(request, response);

            }
        } catch (Exception ex) {
            Logger.getLogger(ControleAjax.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
        }
    }

    public void ImovelListarAprovados(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ImovelDAO dao = new ImovelDAO();

        NumberFormat nf = NumberFormat.getCurrencyInstance();

        Map<String, String> filtro = new HashMap<>();
        filtro.put("quartos", request.getParameter("quartos"));
        filtro.put("valor", request.getParameter("valor"));
        filtro.put("tpvenda", request.getParameter("tpvenda"));

        List<Imovel> imoveis = dao.listarAprovados(filtro);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (imoveis.isEmpty()) {
                out.println("<div class=\"text-center\">");
                out.println("<p><strong> Nenhum Imóvel Cadastrado </strong></p>");
                out.println("</div>");
            } else {
                for (Imovel im : imoveis) {
                    out.println("<div class=\"col-sm-6 col-xl-4 mb-3 align-items-center\">");
                    out.println("<div class=\"card\">");
                    out.println("<img class=\"card-img-top\" src=\"" + request.getContextPath() + "/Resources/upload/" + im.getDiretorio_imagem() + "\" alt=\"Imagem do Imóvel\" height=\"225\" width=\"210\">");
                    out.println("<div class=\"card-body\">");
                    out.println("<h2 class=\"card-title\">" + im.getTitulo() + "</h2>");
                    out.println("<p class=\"card-text\">" + im.getDescricao() + "</p>");
                    out.println("</div>");
                    out.println("<div class=\"card-footer\">");
                    out.println("<p class=\"card-text float-right\">" + nf.format(im.getValor()) + "</p>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");
                }
            }

        }
    }

}
