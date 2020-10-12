package Filtro;

import Modelo.Perfil;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "AcessoFunc", urlPatterns = {"/Corretor/*", "/Controle/ImovelAprovar", "/Controle/ImovelEmAnalise", "/Controle/ImovelReprovar"})
public class AcessoFunc implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Map<String, Object> session = (Map) ((HttpServletRequest) request).getSession().getAttribute("usuarioLogado");

        if (session != null && (session.get("nivel").equals(Perfil.CORRETOR) || session.get("nivel").equals(Perfil.ADMINISTRADOR))) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/acessonegado.jsp");
        }
    }

    @Override
    public void destroy() {

    }

}
