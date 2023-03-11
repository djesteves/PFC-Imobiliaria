package controle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ICommand {

    String executar(HttpServletRequest request, HttpServletResponse response);
}
