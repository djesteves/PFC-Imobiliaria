package Filtro;

import Modelo.Perfil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "AcessoAdmin", urlPatterns = {"/Controle/UsuarioListarAtivos"})
public class AcessoAdmin implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Map<String, Object> session = (Map) ((HttpServletRequest) request).getSession().getAttribute("usuarioLogado");

        if (session != null && session.get("nivel").equals(Perfil.ADMINISTRADOR)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/acessonegado.jsp");
        }

    }

    @Override
    public void destroy() {

    }
}
