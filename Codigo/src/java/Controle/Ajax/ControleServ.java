/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.Ajax;

import Dao.AgendamentoDAO;
import Dao.ImovelDAO;
import Dao.RelatorioDAO;
import Modelo.Imovel;
import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
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
@WebServlet(name = "ControleServ", urlPatterns = {"/ImovelListarAprovados", "/VisualizarImovel", "/ListarCorretores", "/EmitirRelatorio"})
@MultipartConfig
public class ControleServ extends HttpServlet {

    NumberFormat nf = NumberFormat.getCurrencyInstance();

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
            } else if (uri.equals(request.getContextPath() + "/ListarCorretores")) {
                ListarCorretores(request, response);
            } else if (uri.equals(request.getContextPath() + "/EmitirRelatorio")) {
                EmitirRelatorio(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(ControleServ.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());

        }
    }

    public void ListarCorretores(HttpServletRequest request, HttpServletResponse response) throws Exception {
        AgendamentoDAO dao = new AgendamentoDAO();

        List<Usuario> corretores = dao.listarCorretores();

        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<option value=\"\">Escolha </option>");
            out.println("<option value=\"AutoAlocar\">Alocar um corretor automaticamente/Não tenho preferência</option>");
            for (Usuario u : corretores) {
                out.println("<option value=\"" + u.getId_usuario() + "\">" + u.getNome() + "</option>");
            }
        }

    }

    public void CarregarModalImovel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        ImovelDAO dao = new ImovelDAO();

        Imovel im = dao.listarPorId(id);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<div class=\"modal-header\">\n"
                    + " <h5 class=\"modal-title\" id=\"modalImovelLabel\"> " + im.getTitulo() + " - Número do Anúncio: " + im.getId_imovel() + "</h5>\n"
                    + " <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                    + "     <span aria-hidden=\"true\">&times;</span>\n"
                    + " </button>\n"
                    + "</div>");

            String tpvenda = "";

            if ("VENDA".equalsIgnoreCase(im.getModalidade_imovel())) {
                tpvenda = "de Venda";
            } else if ("ALUGUEL".equalsIgnoreCase(im.getModalidade_imovel())) {
                tpvenda = "do Aluguel/Mês";
            }

            out.println("<div class=\"modal-body\">"
                    + " <div class=\"row\">\n "
                    + "   <div class=\"col-lg-6 mb-4\">\n"
                    + "       <img style=\"cursor: zoom-in\" class=\"img-fluid rounded\" src=\"" + request.getContextPath() + "/Resources/upload/" + im.getDiretorio_imagem() + "\"> </img>\n"
                    + "   </div>\n"
                    + "   <div class=\"col-lg-6\">\n"
                    + "<div class=\"card \">\n"
                    + "<div class=\"card-body \">\n"
                    + "  <p style=\"font-weight: bold;\"class=\"lead\"> Sobre esse imóvel </p>\n"
                    + "  <p  class=\"lead\">" + im.getDescricao() + "</p>\n"
                    + "  <hr class=\"my-4\">\n"
                    + "  <p style=\"font-weight: bold;\"class=\"lead\"> Caracteristicas </p>\n"
                    + "  <p><i class=\"fas fa-vector-square\"></i> " + im.getArea_total() + "m² Área Total</p>\n"
                    + "  <p><i class=\"fas fa-bed\"></i> " + im.getComodos() + " quarto(s)</p>\n"
                    + "  <p><i class=\"fas fa-restroom\"> </i> " + im.getBanheiros() + " banheiro(s)</p>\n"
                    + "  <hr class=\"my-4\">\n"
                    + "  <p style=\"font-weight: bold;\"class=\"lead\"> Valores </p>\n"
                    + "  <p><i class=\"fas fa-money-check-alt\"></i> " + nf.format(im.getValor()) + " Valor " + tpvenda + "</p>\n"
                    + "  <p><i class=\"fas fa-file-invoice-dollar\"></i> " + nf.format(im.getIptu()) + " Valor do IPTU/Anual</p>\n"
                    + "  <p><i class=\"fas fa-file-invoice-dollar\"> </i> " + nf.format(im.getCondominio()) + " Valor do Condominio</p>\n"
                    + "  <hr class=\"my-4\">\n"
                    + "<div class=\"text-center \">\n"
                    + " <form action=\"" + request.getContextPath() + "/Usuario/AgendamentoSolicitar.jsp\" method=\"post\">"
                    + " <input type=\"hidden\" id=\"id_imovel\" name=\"id_imovel\" value=\"" + im.getId_imovel() + "\" >"
                    + " <input type=\"hidden\" id=\"emailanunciante\" name=\"emailanunciante\" value=\"" + im.getUsuario().getEmail() + "\" >"
                    + "  <button type=\"submit\" class=\"btn btn-primary btn-block\" href=\"index.jsp\" role=\"button\">Agendar uma Visita</button>\n"
                    + " </form>"
                    + "</div>"
                    + "</div>"
                    + "</div>"
                    + "   </div>\n"
                    + " </div> "
                    + "</div>");

        }

    }

    public void EmitirRelatorio(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String relatorio = request.getParameter("nomerel");

        // acha jrxml dentro da aplicação
        ServletContext contexto = request.getServletContext();
        String jrxml = contexto.getRealPath("Resources/relatorios/" + relatorio + ".jrxml");
        String path = contexto.getRealPath("/Resources/resultados/");

        Map<String, Object> parametros = new HashMap<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        parametros.put("logo", contexto.getRealPath("Resources/img/icon_imob.png"));
        parametros.put("path", path);

        if (relatorio.equalsIgnoreCase("ImoveisCadastradosPeriodo") || relatorio.equalsIgnoreCase("UsuariosCadastradosPeriodo")) {
            parametros.put("datainicio", formatter.parse(request.getParameter("datainicio")));
            parametros.put("datafinal", formatter.parse(request.getParameter("datafinal")));
            parametros.put("situacao", request.getParameter("situacao"));
        } else if (relatorio.equalsIgnoreCase("FichaAgendamento")) {
            parametros.put("id_agendamento", Integer.parseInt(request.getParameter("id_agendamento")));
        }

        RelatorioDAO gerador = new RelatorioDAO();

        String caminho = gerador.geraPdf(jrxml, parametros);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(caminho);
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
            Logger.getLogger(ControleServ.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            System.err.println(ex.getMessage());
        }
    }

    public void ImovelListarAprovados(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ImovelDAO dao = new ImovelDAO();

        Map<String, String> filtro = new HashMap<>();
        filtro.put("quartos", request.getParameter("quartos"));
        filtro.put("valor", request.getParameter("valor"));
        filtro.put("tpvenda", request.getParameter("tpvenda"));

        List<Imovel> imoveis = dao.listarAprovados(filtro);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            if (imoveis.isEmpty()) {
                out.println("<div class=\"col-12 text-center\">");
                out.println("<p><strong> Nenhum Imóvel Cadastrado </strong></p>");
                out.println("</div>");
            } else {
                for (Imovel im : imoveis) {
                    out.println("<div class=\"col-sm-6 col-xl-4 mb-3 align-items-center\">");
                    out.println("<div class=\"card\">");
                    out.println("<img class=\"card-img-top\" src=\"" + request.getContextPath() + "/Resources/upload/" + im.getDiretorio_imagem() + "\" alt=\"Imagem do Imóvel\" height=\"225\" width=\"210\">");
                    out.println("<div class=\"card-body\">");
                    out.println("<h2 class=\"card-title\">" + im.getTitulo() + "</h2>");

                    if ("VENDA".equalsIgnoreCase(im.getModalidade_imovel())) {
                        out.println("<span class=\"badge badge-primary\"> " + im.getModalidade_imovel() + "</span>");
                    } else if ("ALUGUEL".equalsIgnoreCase(im.getModalidade_imovel())) {
                        out.println("<span class=\"badge badge-success\"> " + im.getModalidade_imovel() + "</span>");
                    }

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
