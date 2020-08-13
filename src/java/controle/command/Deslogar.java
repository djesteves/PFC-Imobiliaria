package controle.command;

import controle.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Deslogar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().removeAttribute("usuarioLogado");
        request.setAttribute("msg", "Deslogado com sucesso!");
        return "index.jsp";
    }

}
