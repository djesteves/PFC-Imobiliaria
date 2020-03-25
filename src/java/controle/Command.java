package controle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    public String executar(HttpServletRequest request, HttpServletResponse response);
}
