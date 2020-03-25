/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.DAO.ImovelDAO;
import modelo.Imovel;
import modelo.Usuario;

/**
 *
 * @author Diego
 */
public class ImovelListar implements Command {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {  
        try {
            HttpSession usuarioLogado = request.getSession();
            Usuario usuario = (Usuario) usuarioLogado.getAttribute("usuarioLogado");
            
            int id = usuario.getId_usuario();
            
            ImovelDAO dao = new ImovelDAO();

            List<Imovel> arrImovel = dao.listar(id);

            request.setAttribute("listaImovel", arrImovel);
            return "Usuario/GerenciarImovel.jsp";
        } catch (Exception ex) {
            Logger.getLogger(ImovelListar.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "erro.jsp";
        }
    }
    
}
