/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.command;

import controle.Command;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modelo.DAO.ImovelDAO;
import modelo.Imovel;
import modelo.Sessao;

/**
 *
 * @author Diieg
 */
public class ImovelCadastrar implements Command {

    Imovel imovel = new Imovel();

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession usuarioLogado = request.getSession();
            Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");
            sessao.getId_usuario();

            Part filePart = request.getPart("uploadFile"); // 
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); //
            InputStream fileContent = filePart.getInputStream();

            imovel.setDiretorioimg(sessao.getId_usuario() + File.separator + fileName);

            ByteArrayOutputStream os = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len;

            while ((len = fileContent.read(buffer)) != -1) {

                os.write(buffer, 0, len);
            }

            byte[] bytes = os.toByteArray();

            // cria o diretorio de upload
            // esse caminho e relativo ao diretorio da aplicacao
            String uploadPath = "C:\\Users\\tr0j4nh4x\\Desktop\\PFC_Imobiliaria_Postgres\\web\\Resources\\upload" + File.separator + sessao.getId_usuario();

            /* 
            String uploadPath = request.getServletContext().getRealPath("")
                    + File.separator + UPLOAD_DIRETORIO + File.separator + usuarioid.getId_usuario();
             */
            // caso o diretorio nao exista o bloco abaixo cria o mesmo
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            //converte o array de bytes em file e grava no diretorio
            File f = new File(uploadPath + File.separator + fileName);
            try (FileOutputStream fos = new FileOutputStream(f)) {
                fos.write(bytes);
            }

            //Imovel Requests
            String titulo = request.getParameter("titulo");
            String descricao = request.getParameter("descricao");
            int comodos = Integer.parseInt(request.getParameter("comodos"));
            int banheiro = Integer.parseInt(request.getParameter("banheiro"));
            int garagem = Integer.parseInt(request.getParameter("garagem"));
            Double valor = Double.parseDouble(request.getParameter("valorimovel"));
            Double areatotal = Double.parseDouble(request.getParameter("areatotal"));
            Double areaedificada = Double.parseDouble(request.getParameter("areaedificada"));
            String tpimovel = request.getParameter("tpimovel");


            imovel.setTitulo(titulo);
            imovel.setDescricao(descricao);
            imovel.setComodos(comodos);
            imovel.setBanheiros(banheiro);
            imovel.setVagas_garagem(garagem);
            imovel.setValor(valor);
            imovel.setArea_total(areatotal);
            imovel.setArea_edificada(areaedificada);
            imovel.setTipo_imovel(tpimovel);

            //Endereço Requests
            String logradouro = request.getParameter("logradouro");
            int numero = Integer.parseInt(request.getParameter("numero"));
            String complemento = request.getParameter("complemento");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("estado");
            String cep = request.getParameter("cep");
            String bairro = request.getParameter("bairro");

            //Endereço Set's
            imovel.getEndereco().setLogradouro(logradouro);
            imovel.getEndereco().setNumero(numero);
            imovel.getEndereco().setComplemento(complemento);
            imovel.getEndereco().setCidade(cidade);
            imovel.getEndereco().setEstado(estado);
            imovel.getEndereco().setCep(cep);
            imovel.getEndereco().setBairro(bairro);

            ImovelDAO dao = new ImovelDAO();

            boolean cadastro = dao.cadastrar(imovel, request, response);

            String msg = (String) request.getAttribute("msg");

            if (cadastro) {
                request.setAttribute("msg", msg);
                return "index.jsp";
            } else {
                request.setAttribute("msgerro", msg);
                return "erro.jsp";
            }

        } catch (NumberFormatException | IOException | ServletException ex) {
            Logger.getLogger(ImovelCadastrar.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
            return "erro.jsp";
        }
    }
}
