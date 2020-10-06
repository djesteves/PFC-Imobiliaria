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
@WebServlet(name = "ServletAjax", urlPatterns = {"/ImovelListarAprovados", "/VisualizarImovel"})
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
            if (uri.equals(request.getContextPath() + "/VisualizarImovel")) {
                CarregarModalImovel(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(ControleAjax.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());

        }
    }

    public void CarregarModalImovel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        ImovelDAO dao = new ImovelDAO();

        Imovel im = dao.listarPorId(id);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class=\"modal-header\">\n"
                    + " <h5 class=\"modal-title\" id=\"modalImovelLabel\"> " + im.getTitulo() + "</h5>\n"
                    + " <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                    + "     <span aria-hidden=\"true\">&times;</span>\n"
                    + " </button>\n"
                    + "</div>");

            out.println("<div class=\"modal-body\">"
                    + " <div class=\"row\">\n "
                    + "   <div class=\"col-lg-6 mb-4\">\n"
                    + "       <img style=\"cursor: zoom-in\" class=\"img-fluid rounded\" src=\"" + request.getContextPath() + "/Resources/upload/" + im.getDiretorio_imagem() + "\"> </img>\n"
                    + "   </div>\n"
                    + "   <div class=\"col-lg-6\">\n"
                    + "<div class=\"card \">\n"
                    + "<div class=\"card-body \">\n"
                    + "  <h1 class=\"display-4\">Olá, mundo!</h1>\n"
                    + "  <p class=\"lead\">Este é um simples componente jumbotron para chamar mais atenção a um determinado conteúdo ou informação.</p>\n"
                    + "  <hr class=\"my-4\">\n"
                    + "  <p>Ele usa classes utilitárias para tipografia e espaçamento de conteúdo, dentro do maior container.</p>\n"
                    + "  <a class=\"btn btn-primary btn-lg\" href=\"#\" role=\"button\">Leia mais</a>\n"
                    + "</div>"
                    + "</div>"
                    + "   </div>\n"
                    + " </div> "
                    + "</div>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String uri = request.getRequestURI();

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
                out.println("<p><strong> Nenhum Imóvel Cadastrado </strong></p>");
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
                    out.println("<button type=\"button\" class=\"btn btn-primary\" onClick=\"carregarImovel('" + im.getId_imovel() + "')\" data-toggle=\"modal\" data-target=\"#modalImovel\">\n"
                            + "        Detalhes\n"
                            + "    </button>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</div>");
                }
            }
        }
    }

}
