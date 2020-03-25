/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Endereco;
import modelo.Imovel;
import modelo.Usuario;
import util.DataAccess;

/**
 *
 * @author Diieg
 */
public class ImovelDAO {

    java.util.Date data = new java.util.Date();
    java.sql.Timestamp timestamp = new java.sql.Timestamp(data.getTime());

    PreparedStatement smt;
    ResultSet rs;

    //INSERTS
    private final String INSERTIMOVEL = "INSERT INTO Imovel VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String INSERTENDERECO = "INSERT INTO Endereco VALUES (DEFAULT,?,?,?,?,?,?,?)";

    //SELECTS
    private final String SELECTIMOVEL = "SELECT * FROM Imovel I "
            + " INNER JOIN Endereco E ON E.id_endereco = I.id_endereco"
            + " INNER JOIN Usuario U ON U.id_usuario = I.id_usuario"
            + " WHERE I.id_usuario = ?";
    
    private final String CONSULTAIMOVEL = "SELECT *, logradouro||', '||numero ||' - ' ||bairro||' - '||cidade|| ' - '||cep as enderecocompleto from imovel i "
            + " INNER JOIN endereco e on e.id_endereco = i.id_endereco"
            + " WHERE i.id_imovel = ?";

    public boolean cadastrar(Imovel imovel, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        boolean sucesso = false;
        String msg = "";
        try (Connection connection = DataAccess.getConexao()) {

           
            //Endereço
            smt = connection.prepareStatement(INSERTENDERECO, Statement.RETURN_GENERATED_KEYS);

            smt.setString(1, imovel.getEndereco().getLogradouro());
            smt.setString(2, imovel.getEndereco().getComplemento());
            smt.setInt(3, imovel.getEndereco().getNumero());
            smt.setString(4, imovel.getEndereco().getCidade());
            smt.setString(5, imovel.getEndereco().getCep());
            smt.setString(6, imovel.getEndereco().getBairro());
            smt.setString(7, imovel.getEndereco().getEstado());
            smt.execute();

            rs = smt.getGeneratedKeys();
            rs.next();

            //Usuario
            HttpSession usuarioLogado = request.getSession();
            Usuario usuarioid = (Usuario) usuarioLogado.getAttribute("usuarioLogado");
            usuarioid.getId_usuario();
            
            
            //imovel
            smt = connection.prepareStatement(INSERTIMOVEL);
            smt.setString(1, imovel.getTitulo());
            smt.setString(2, imovel.getDescricao());
            smt.setString(3, "Ativo");
            smt.setDouble(4, imovel.getValor());
            smt.setDouble(5, imovel.getArea_total());
            smt.setDouble(6, imovel.getArea_edificada());
            smt.setInt(7, imovel.getComodos());
            smt.setInt(8, imovel.getVagas_garagem());
            smt.setInt(9, imovel.getBanheiros());
            smt.setTimestamp(10, timestamp);
            smt.setString(11, imovel.getDiretorioimg());
            smt.setString(12, imovel.getTipo_imovel());
            smt.setInt(13, usuarioid.getId_usuario());
            smt.setInt(14, rs.getInt(1));
            smt.execute();

            smt.close();
            connection.close();
            msg = "Imóvel cadastrado com sucesso!";
            sucesso = true;

        } catch (Exception ex) {
            Logger.getLogger(ImovelDAO.class.getName()).log(Level.SEVERE, null, ex);
            msg = ex.getMessage();
        }

        request.setAttribute("msg", msg);
        return sucesso;
    }

    public List<Imovel> listar(int id) throws SQLException, Exception {
        

        try (Connection connection = DataAccess.getConexao()) {

            PreparedStatement smt = connection.prepareStatement(SELECTIMOVEL);
            smt.setInt(1, id);
            rs = smt.executeQuery();
            
            List<Imovel> ArrImovelUsr = new ArrayList<Imovel>();
            while (rs.next()) {
                
                //obviamente pra listar n vai ser tudo isso, esse e o modelo padrao pra pegar tudo;
                Imovel imovel = new Imovel();
                imovel.setId_imovel(rs.getInt("id_imovel"));
                imovel.setComodos(rs.getInt("comodos"));
                imovel.setBanheiros(rs.getInt("banheiros"));
                imovel.setVagas_garagem(rs.getInt("vagas_garagem"));
                imovel.setTipo_imovel(rs.getString("tipo_imovel"));
                imovel.setDescricao(rs.getString("descricao"));
                imovel.setStatus(rs.getString("status"));
                imovel.setTitulo(rs.getString("titulo"));
                imovel.setArea_total(rs.getDouble("area_total"));
                imovel.setArea_edificada(rs.getDouble("area_edificada"));
                imovel.setValor(rs.getDouble("valor"));
                imovel.setDiretorioimg(rs.getString("imagemdir"));
                
                Endereco endereco = new Endereco();
                endereco.setId_endereco(rs.getInt("id_endereco"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCep(rs.getString("cep"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getInt("numero"));
                
                imovel.setEndereco(endereco);
                ArrImovelUsr.add(imovel);
            }

            connection.close();
            return ArrImovelUsr;
        } catch (Exception ex) {
            Logger.getLogger(ImovelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    public ResultSet consultar(int id) throws SQLException, Exception {
        ResultSet rs;

        try (Connection connection = DataAccess.getConexao()) {

            PreparedStatement smt = connection.prepareStatement(CONSULTAIMOVEL);
            smt.setInt(1, id);
            rs = smt.executeQuery();
            if (rs.next()) {
                connection.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(ImovelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return rs;
    }

}
