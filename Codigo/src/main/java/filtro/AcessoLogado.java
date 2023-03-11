package filtro;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName = "AcessoLogado", urlPatterns = {"/usuario/GerenciarImoveis.jsp", "/usuario/FormImovel.jsp",
        "/usuario/AlterarSenhaUsuario.jsp", "/usuario/AgendamentoSolicitar.jsp", "/usuario/Agendamentos.jsp",
        "/controle/UsuarioListarPorID"})
public class AcessoLogado implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Map<String, Object> session = (Map) ((HttpServletRequest) request).getSession().getAttribute("usuarioLogado");

        if (session != null) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }

}
