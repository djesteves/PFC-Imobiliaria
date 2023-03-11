package controle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Deslogar implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().invalidate();
        //request.getSession().removeAttribute("usuarioLogado");
        request.setAttribute("msg", "Deslogado com sucesso!");
        return "index.jsp";
    }

}
