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
import modelo.Imovel;
import modelo.Sessao;
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
    private final String CONSULTAIMOVEL = "SELECT * FROM Imovel I "
            + " INNER JOIN Endereco E ON E.id_endereco = I.id_endereco"
            + " INNER JOIN Usuario U ON U.id_usuario = I.id_usuario"
            + " WHERE I.id_imovel = ?";

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
            Sessao sessao = (Sessao) usuarioLogado.getAttribute("usuarioLogado");
            sessao.getId_usuario();

            //imovel
            smt = connection.prepareStatement(INSERTIMOVEL);
            smt.setString(1, imovel.getTitulo());
            smt.setString(2, imovel.getDescricao());
            smt.setString(3, "Em Análise");
            smt.setDouble(4, imovel.getValor());
            smt.setDouble(5, imovel.getArea_total());
            smt.setDouble(6, imovel.getArea_edificada());
            smt.setInt(7, imovel.getComodos());
            smt.setInt(8, imovel.getVagas_garagem());
            smt.setInt(9, imovel.getBanheiros());
            smt.setTimestamp(10, timestamp);
            smt.setString(11, imovel.getDiretorioimg());
            smt.setString(12, imovel.getTipo_imovel());
            smt.setInt(13, sessao.getId_usuario());
            smt.setInt(14, rs.getInt(1));
            smt.execute();

            smt.close();
            connection.close();
            msg = "Seu imóvel foi cadastrado e passará por uma análise, fique de olho no seu email :')!";
            sucesso = true;

        }

        request.setAttribute("msg", msg);
        return sucesso;
    }

    private final String CONSULTAIMOVELUSUARIO = "SELECT * FROM Imovel I "
            + " INNER JOIN Endereco E ON E.id_endereco = I.id_endereco"
            + " INNER JOIN Usuario U ON U.id_usuario = I.id_usuario"
            + " WHERE I.id_usuario = ?";

    public List<Imovel> listarPorID(int id) throws SQLException {

        List<Imovel> ArrImovel;

        try (Connection connection = DataAccess.getConexao()) {

            smt = connection.prepareStatement(CONSULTAIMOVELUSUARIO);
            smt.setInt(1, id);
            rs = smt.executeQuery();

            ArrImovel = new ArrayList<>();
            while (rs.next()) {

                //classe generica, esse e o modelo padrao pra pegar tudo;
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
                imovel.getUsuario().setId_usuario(rs.getInt("id_usuario"));
                imovel.getEndereco().setBairro(rs.getString("bairro"));
                imovel.getEndereco().setCep(rs.getString("cep"));
                imovel.getEndereco().setCidade(rs.getString("cidade"));
                imovel.getEndereco().setComplemento(rs.getString("complemento"));
                imovel.getEndereco().setEstado(rs.getString("estado"));
                imovel.getEndereco().setId_endereco(rs.getInt("id_endereco"));
                imovel.getEndereco().setLogradouro(rs.getString("logradouro"));
                imovel.getEndereco().setNumero(rs.getInt("numero"));

                ArrImovel.add(imovel);
                connection.close();
            }
        }

        return ArrImovel;
    }

    private final String SELECT_DADOSIMOVEL = "SELECT * FROM Imovel I "
            + "INNER JOIN Usuario U ON U.id_usuario = I.id_usuario "
            + "LEFT JOIN Endereco E ON E.id_endereco = I.id_endereco "
            + "WHERE I.id_imovel = ?";

    public Imovel getImovel(int id) throws SQLException {
        Imovel imovel = null;
        try (Connection connection = DataAccess.getConexao()) {

            smt = connection.prepareStatement(SELECT_DADOSIMOVEL);
            smt.setInt(1, id);

            rs = smt.executeQuery();
            if (rs.next()) {
                imovel = new Imovel();
                imovel.setArea_edificada(rs.getDouble("area_edificada"));
                imovel.setArea_total(rs.getDouble("area_total"));
                imovel.setBanheiros(rs.getInt("banheiros"));
                imovel.setComodos(rs.getInt("comodos"));
                imovel.setDescricao(rs.getString("descricao"));
                imovel.setDiretorioimg(rs.getString("imagemdir"));
                imovel.setId_imovel(rs.getInt("id_imovel"));
                imovel.setStatus(rs.getString("status"));
                imovel.setTipo_imovel(rs.getString("tipo_imovel"));
                imovel.setTitulo(rs.getString("titulo"));
                imovel.setVagas_garagem(rs.getInt("vagas_garagem"));
                imovel.setValor(rs.getDouble("valor"));

                imovel.getEndereco().setBairro(rs.getString("bairro"));
                imovel.getEndereco().setCep(rs.getString("cep"));
                imovel.getEndereco().setCidade(rs.getString("cidade"));
                imovel.getEndereco().setComplemento(rs.getString("complemento"));
                imovel.getEndereco().setEstado(rs.getString("estado"));
                imovel.getEndereco().setId_endereco(rs.getInt("id_endereco"));
                imovel.getEndereco().setLogradouro(rs.getString("logradouro"));
                imovel.getEndereco().setNumero(rs.getInt("numero"));

                connection.close();

            }
        }
        return imovel;
    }

    private final String UPDATE_ENDERECO = "UPDATE Endereco SET logradouro = ?, complemento = ?,  numero = ?,"
            + " cidade = ?, cep = ?, bairro = ?, estado = ?"
            + " WHERE id_endereco = ?";

    private final String UPDATE_DADOS = "UPDATE Imovel SET Titulo = ?, descricao =?,"
            + " valor = ?, area_total = ?, area_edificada = ?, comodos = ?, vagas_garagem = ?, banheiros = ?, tipo_imovel = ?"
            + " WHERE id_imovel = ?";

    public boolean alterar(Imovel imovel) throws SQLException {
        Connection connection = DataAccess.getConexao();

        smt = connection.prepareStatement(UPDATE_DADOS);

        smt.setString(1, imovel.getTitulo());
        smt.setString(2, imovel.getDescricao());
        smt.setDouble(3, imovel.getValor());
        smt.setDouble(4, imovel.getArea_total());
        smt.setDouble(5, imovel.getArea_edificada());
        smt.setInt(6, imovel.getComodos());
        smt.setInt(7, imovel.getVagas_garagem());
        smt.setInt(8, imovel.getBanheiros());
        smt.setString(9, imovel.getTipo_imovel());
        smt.setInt(10, imovel.getId_imovel());

        boolean rowUpdate = smt.executeUpdate() > 0;

        smt = connection.prepareStatement(UPDATE_ENDERECO);

        smt.setString(1, imovel.getEndereco().getLogradouro());
        smt.setString(2, imovel.getEndereco().getComplemento());
        smt.setInt(3, imovel.getEndereco().getNumero());
        smt.setString(4, imovel.getEndereco().getCidade());
        smt.setString(5, imovel.getEndereco().getCep());
        smt.setString(6, imovel.getEndereco().getBairro());
        smt.setString(7, imovel.getEndereco().getEstado());
        smt.setInt(8, imovel.getEndereco().getId_endereco());

        rowUpdate = smt.executeUpdate() > 0;

        return rowUpdate;
    }

    private final String SELECT_APROVARIMOVEIS = "SELECT * FROM Imovel I "
            + "WHERE Status = 'Em Análise'";

    public List<Imovel> imoveisEmAnalise() throws SQLException {
        List<Imovel> listAprovarImovel;
        try (Connection connection = DataAccess.getConexao()) {
            listAprovarImovel = new ArrayList<>();
            smt = connection.prepareStatement(SELECT_APROVARIMOVEIS);
            rs = smt.executeQuery();
            while (rs.next()) {
                Imovel imovel = new Imovel();
                imovel.setId_imovel(rs.getInt("id_imovel"));
                imovel.setTitulo(rs.getString("titulo"));
                imovel.setValor(rs.getDouble("valor"));
                imovel.setStatus(rs.getString("status"));

                listAprovarImovel.add(imovel);
            }
            smt.close();
            connection.close();
        }
        return listAprovarImovel;
    }

    private final String UPDATE_APROVARIMOVEIS = "UPDATE Imovel I "
            + "SET Status = 'Disponivel' "
            + "WHERE id_imovel = ?";

    public boolean aprovarImovel(int id) throws SQLException {
        Connection connection = DataAccess.getConexao();

        smt = connection.prepareStatement(UPDATE_APROVARIMOVEIS);

        smt.setInt(1, id);
        boolean rowUpdate = smt.executeUpdate() > 0;

        smt.close();
        connection.close();
        
        return rowUpdate;
    }
}
