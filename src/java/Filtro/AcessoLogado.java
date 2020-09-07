package Filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Modelo.Sessao;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "AcessoLogado", urlPatterns = {"/Usuario/GerenciarImoveis.jsp", "/Usuario/FormImovel.jsp", "/Usuario/AlterarSenhaUsuario.jsp" })
public class AcessoLogado implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession usuarioLogado = ((HttpServletRequest) request).getSession();
        Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");

        if (sessao != null) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/acessologado.jsp");
        }
    }

    @Override
    public void destroy() {

    }

}
