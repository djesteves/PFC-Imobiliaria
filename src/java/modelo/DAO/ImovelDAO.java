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
import modelo.Imovel;
import util.ConnectionFactory;

/**
 *
 * @author Diieg
 */
public class ImovelDAO {

    java.util.Date data = new java.util.Date();
    java.sql.Timestamp timestamp = new java.sql.Timestamp(data.getTime());

    PreparedStatement smt;
    ResultSet rs;

    public boolean cadastrar(Imovel imovel) {

        try (Connection connection = ConnectionFactory.getConexao()) {

            //Endereço
            String INSERTIMOVEL = "INSERT INTO Imovel VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            String INSERTENDERECO = "INSERT INTO Endereco VALUES (DEFAULT,?,?,?,?,?,?,?)";

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
            smt.setInt(13, imovel.getUsuario().getId_usuario());
            smt.setInt(14, rs.getInt(1));
            smt.execute();

            smt.close();
            connection.close();
            return true;

        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
    }

    public List<Imovel> listarPorID(int id) {

        String CONSULTAIMOVELUSUARIO = "SELECT * FROM Imovel I "
                + " INNER JOIN Endereco E ON E.id_endereco = I.id_endereco"
                + " INNER JOIN Usuario U ON U.id_usuario = I.id_usuario"
                + " WHERE I.id_usuario = ?";

        List<Imovel> ArrImovel = null;

        try (Connection connection = ConnectionFactory.getConexao()) {

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
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        return ArrImovel;
    }

    public Imovel getImovel(int id) {

        String SELECT_DADOSIMOVEL = "SELECT * FROM Imovel I "
                + "INNER JOIN Usuario U ON U.id_usuario = I.id_usuario "
                + "LEFT JOIN Endereco E ON E.id_endereco = I.id_endereco "
                + "WHERE I.id_imovel = ?";

        Imovel imovel = null;
        try (Connection connection = ConnectionFactory.getConexao()) {

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

                imovel.getUsuario().setId_usuario(rs.getInt("id_usuario"));

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
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        return imovel;
    }

    public boolean alterar(Imovel imovel) {

        String UPDATE_ENDERECO = "UPDATE Endereco SET logradouro = ?, complemento = ?,  numero = ?,"
                + " cidade = ?, cep = ?, bairro = ?, estado = ?"
                + " WHERE id_endereco = ?";

        String UPDATE_DADOS = "UPDATE Imovel SET Titulo = ?, descricao =?,"
                + " valor = ?, area_total = ?, area_edificada = ?, comodos = ?, vagas_garagem = ?, banheiros = ?, tipo_imovel = ?"
                + " WHERE id_imovel = ?";

        try (Connection connection = ConnectionFactory.getConexao()) {

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

            smt.executeUpdate();

            smt = connection.prepareStatement(UPDATE_ENDERECO);

            smt.setString(1, imovel.getEndereco().getLogradouro());
            smt.setString(2, imovel.getEndereco().getComplemento());
            smt.setInt(3, imovel.getEndereco().getNumero());
            smt.setString(4, imovel.getEndereco().getCidade());
            smt.setString(5, imovel.getEndereco().getCep());
            smt.setString(6, imovel.getEndereco().getBairro());
            smt.setString(7, imovel.getEndereco().getEstado());
            smt.setInt(8, imovel.getEndereco().getId_endereco());

            smt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
    }

    public List<Imovel> imoveisEmAnalise() {

        String SELECT_APROVARIMOVEIS = "SELECT * FROM Imovel I "
                + "INNER JOIN Usuario U ON U.id_usuario = I.id_usuario "
                + "WHERE Status = 'Em Análise'";

        List<Imovel> listAprovarImovel = null;
        try (Connection connection = ConnectionFactory.getConexao()) {
            listAprovarImovel = new ArrayList<>();
            smt = connection.prepareStatement(SELECT_APROVARIMOVEIS);
            rs = smt.executeQuery();
            while (rs.next()) {
                Imovel imovel = new Imovel();
                imovel.setId_imovel(rs.getInt("id_imovel"));
                imovel.setTitulo(rs.getString("titulo"));
                imovel.setValor(rs.getDouble("valor"));
                imovel.setStatus(rs.getString("status"));
                imovel.getUsuario().setId_usuario(rs.getInt("id_usuario"));

                listAprovarImovel.add(imovel);
            }
            smt.close();
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
        }
        return listAprovarImovel;
    }

    public boolean aprovarImovel(int id) {

        String UPDATE_APROVARIMOVEIS = "UPDATE Imovel I "
                + "SET Status = 'Disponivel' "
                + "WHERE id_imovel = ?";

        try (Connection connection = ConnectionFactory.getConexao()) {

            smt = connection.prepareStatement(UPDATE_APROVARIMOVEIS);

            smt.setInt(1, id);
            boolean rowUpdate = smt.executeUpdate() > 0;

            smt.close();
            connection.close();

            return rowUpdate;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
    }

    public boolean reprovarImovel(int id) throws SQLException {

        String UPDATE_REPROVARIMOVEIS = "UPDATE Imovel I "
                + "SET Status = 'Reprovado' "
                + "WHERE id_imovel = ?";

        try (Connection connection = ConnectionFactory.getConexao()) {

            smt = connection.prepareStatement(UPDATE_REPROVARIMOVEIS);

            smt.setInt(1, id);
            boolean rowUpdate = smt.executeUpdate() > 0;

            smt.close();
            connection.close();
            return rowUpdate;
        } catch (SQLException ex) {
            System.err.println("Erro: " + ex);
            return false;
        }
    }
}
