/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import modelo.Imovel;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Diieg
 */
public class ImovelDAO {

    java.util.Date data = new java.util.Date();
    java.sql.Timestamp timestamp = new java.sql.Timestamp(data.getTime());

    PreparedStatement smt;
    ResultSet rs;
    Connection connection = ConnectionFactory.getConexao();

    public void cadastrar(Imovel imovel) throws SQLException {


        //Endereço
        String INSERTIMOVEL = "INSERT INTO Imovel VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
        int idEndereco = rs.getInt(1);

        //imovel
        smt = connection.prepareStatement(INSERTIMOVEL);
        smt.setString(1, imovel.getTitulo());
        smt.setString(2, imovel.getDescricao());
        smt.setString(3, imovel.getStatus());
        smt.setString(4, imovel.getSituacao());
        smt.setDouble(5, imovel.getValor());
        smt.setDouble(6, imovel.getArea_total());
        smt.setDouble(7, imovel.getArea_edificada());
        smt.setInt(8, imovel.getComodos());
        smt.setInt(9, imovel.getVagas_garagem());
        smt.setInt(10, imovel.getBanheiros());
        smt.setTimestamp(11, timestamp);
        smt.setString(12, imovel.getDiretorio_imagem());
        smt.setString(13, imovel.getTipo_imovel());
        smt.setInt(14, imovel.getUsuario().getId_usuario());
        smt.setInt(15, idEndereco);
        smt.setString(16, imovel.getModalidade_imovel());
        smt.setDouble(17, imovel.getIptu());
        smt.setDouble(18, imovel.getCondominio());
        smt.execute();

        connection.close();
    }

    public List<Imovel> listarPorIDAtivos(int id) throws SQLException {

        String CONSULTAIMOVELUSUARIO = "SELECT * FROM Imovel I "
                + " INNER JOIN Endereco E ON E.id_endereco = I.id_endereco"
                + " INNER JOIN usuario U ON U.id_usuario = I.id_usuario"
                + " WHERE I.id_usuario = ? and i.Situacao = 'Ativo'";

        List<Imovel> ArrImovel;

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
            imovel.setModalidade_imovel(rs.getString("modalidade_imovel"));
            imovel.setIptu(rs.getDouble("iptu"));
            imovel.setCondominio(rs.getDouble("condominio"));
            imovel.setDiretorio_imagem(rs.getString("diretorio_imagem"));
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
        }

        connection.close();

        return ArrImovel;
    }

    public Imovel listarPorId(int id) throws SQLException {

        String SELECT_DADOSIMOVEL = "SELECT * FROM Imovel I "
                + "INNER JOIN usuario U ON U.id_usuario = I.id_usuario "
                + "LEFT JOIN Endereco E ON E.id_endereco = I.id_endereco "
                + "WHERE I.id_imovel = ?";

        Imovel imovel = null;

        smt = connection.prepareStatement(SELECT_DADOSIMOVEL);
        smt.setInt(1, id);

        rs = smt.executeQuery();
        if (rs.next()) {
            imovel = new Imovel();
            imovel.setArea_edificada(rs.getDouble("area_edificada"));
            imovel.setArea_total(rs.getDouble("area_total"));
            imovel.setBanheiros(rs.getInt("banheiros"));
            imovel.setData_cadastro(rs.getDate("data_cadastro"));
            imovel.setComodos(rs.getInt("comodos"));
            imovel.setDescricao(rs.getString("descricao"));
            imovel.setDiretorio_imagem(rs.getString("diretorio_imagem"));
            imovel.setId_imovel(rs.getInt("id_imovel"));
            imovel.setStatus(rs.getString("status"));
            imovel.setTipo_imovel(rs.getString("tipo_imovel"));
            imovel.setTitulo(rs.getString("titulo"));
            imovel.setVagas_garagem(rs.getInt("vagas_garagem"));
            imovel.setValor(rs.getDouble("valor"));
            imovel.setModalidade_imovel(rs.getString("modalidade_imovel"));
            imovel.setIptu(rs.getDouble("iptu"));
            imovel.setCondominio(rs.getDouble("condominio"));
            imovel.getUsuario().setId_usuario(rs.getInt("id_usuario"));
            imovel.getUsuario().setEmail(rs.getString("email"));
            imovel.getEndereco().setBairro(rs.getString("bairro"));
            imovel.getEndereco().setCep(rs.getString("cep"));
            imovel.getEndereco().setCidade(rs.getString("cidade"));
            imovel.getEndereco().setComplemento(rs.getString("complemento"));
            imovel.getEndereco().setEstado(rs.getString("estado"));
            imovel.getEndereco().setId_endereco(rs.getInt("id_endereco"));
            imovel.getEndereco().setLogradouro(rs.getString("logradouro"));
            imovel.getEndereco().setNumero(rs.getInt("numero"));
        }

        connection.close();
        return imovel;
    }

    public void alterar(Imovel imovel) throws SQLException {

        String UPDATE_ENDERECO = "UPDATE Endereco SET logradouro = ?, complemento = ?,  numero = ?,"
                + " cidade = ?, cep = ?, bairro = ?, estado = ?"
                + " WHERE id_endereco = ?";

        String UPDATE_DADOS = "UPDATE Imovel SET Titulo = ?, descricao =?,"
                + " valor = ?, area_total = ?, area_edificada = ?, comodos = ?, vagas_garagem = ?, banheiros = ?,"
                + " iptu = ?, condominio = ?"
                + " WHERE id_imovel = ?";

        smt = connection.prepareStatement(UPDATE_DADOS);

        smt.setString(1, imovel.getTitulo());
        smt.setString(2, imovel.getDescricao());
        smt.setDouble(3, imovel.getValor());
        smt.setDouble(4, imovel.getArea_total());
        smt.setDouble(5, imovel.getArea_edificada());
        smt.setInt(6, imovel.getComodos());
        smt.setInt(7, imovel.getVagas_garagem());
        smt.setInt(8, imovel.getBanheiros());
        smt.setDouble(9, imovel.getIptu());
        smt.setDouble(10, imovel.getCondominio());
        smt.setInt(11, imovel.getId_imovel());

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

        connection.close();

    }

    public List<Imovel> emAnalise() throws SQLException {

        String SELECT_APROVARIMOVEIS = "SELECT I.id_imovel, I.data_cadastro,"
                + " I.valor, U.Nome, U.id_usuario, U.email "
                + " FROM Imovel I "
                + "INNER JOIN usuario U ON U.id_usuario = I.id_usuario "
                + "WHERE Status = 'Em Análise' "
                + "  AND I.Situacao = 'Ativo' "
                + "ORDER BY I.data_cadastro";

        List<Imovel> listAprovarImovel;
        listAprovarImovel = new ArrayList<>();
        smt = connection.prepareStatement(SELECT_APROVARIMOVEIS);
        rs = smt.executeQuery();
        while (rs.next()) {
            Imovel imovel = new Imovel();
            imovel.setId_imovel(rs.getInt("id_imovel"));
            imovel.setData_cadastro(rs.getDate("data_cadastro"));
            imovel.setValor(rs.getDouble("valor"));
            imovel.getUsuario().setId_usuario(rs.getInt("id_usuario"));
            imovel.getUsuario().setNome(rs.getString("nome"));
            imovel.getUsuario().setEmail(rs.getString("email"));

            listAprovarImovel.add(imovel);
        }

        connection.close();
        return listAprovarImovel;
    }

    public void aprovar(int id) throws SQLException {

        String UPDATE_APROVARIMOVEIS = "UPDATE Imovel I "
                + "SET Status = 'Disponivel', data_validacao = ? "
                + "WHERE id_imovel = ?";

        smt = connection.prepareStatement(UPDATE_APROVARIMOVEIS);


        smt.setTimestamp(1, timestamp);
        smt.setInt(2, id);

        System.out.println(smt);

        smt.executeUpdate();

        connection.close();
    }

    public void excluir(Imovel imovel) throws SQLException {

        String UPDATE_REPROVARIMOVEIS = "UPDATE Imovel I "
                + "SET Situacao = ? "
                + "WHERE id_imovel = ?";

        smt = connection.prepareStatement(UPDATE_REPROVARIMOVEIS);

        smt.setString(1, imovel.getSituacao());
        smt.setInt(2, imovel.getId_imovel());
        smt.executeUpdate();

        connection.close();

    }

    public void vendido(Imovel imovel) throws SQLException {

        String UPDATE_IMOVELVENDIDO = "UPDATE Imovel  "
                + "SET Status = ? "
                + "WHERE id_imovel = ?";

        smt = connection.prepareStatement(UPDATE_IMOVELVENDIDO);

        smt.setString(1, imovel.getStatus());
        smt.setInt(2, imovel.getId_imovel());
        smt.executeUpdate();

        connection.close();

    }

    public void reprovar(Imovel imovel) throws SQLException {

        String UPDATE_REPROVARIMOVEIS = "UPDATE Imovel I "
                + "SET Status = 'Reprovado', Obs = ?, data_validacao = ?"
                + "WHERE id_imovel = ?";

        smt = connection.prepareStatement(UPDATE_REPROVARIMOVEIS);

        smt.setString(1, imovel.getObs());
        smt.setTimestamp(2, timestamp);
        smt.setInt(3, imovel.getId_imovel());
        smt.executeUpdate();

        connection.close();

    }

    public List<Imovel> listarAprovados(Map<String, String> filtro) throws Exception {

        String SELECT_IMOVEIS = "SELECT * FROM Imovel I "
                + "INNER JOIN usuario U ON U.id_usuario = I.id_usuario "
                + "LEFT JOIN Endereco E ON E.id_endereco = I.id_endereco "
                + "WHERE I.status = 'Disponivel'"
                + "  AND I.situacao = 'Ativo'";

        if ("*".equalsIgnoreCase(String.valueOf(filtro.get("quartos")))) {
            SELECT_IMOVEIS += " AND comodos > 5 ";
        } else if (!"".equalsIgnoreCase(String.valueOf(filtro.get("quartos")))) {
            SELECT_IMOVEIS += " AND comodos = " + filtro.get("quartos");
        }

        if ("*".equalsIgnoreCase(String.valueOf(filtro.get("valor")))) {
            SELECT_IMOVEIS += " AND valor > 50000000";
        } else if (!"".equalsIgnoreCase(String.valueOf(filtro.get("valor")))) {
            SELECT_IMOVEIS += " AND valor <= " + filtro.get("valor");
        }

        if (!"".equalsIgnoreCase(String.valueOf(filtro.get("tpvenda")))) {
            SELECT_IMOVEIS += " AND modalidade_imovel = '" + filtro.get("tpvenda") + "'";
        }

        Imovel imovel;
        List<Imovel> listImoveis;
        listImoveis = new ArrayList<>();

        smt = connection.prepareStatement(SELECT_IMOVEIS);

        rs = smt.executeQuery();

        while (rs.next()) {
            imovel = new Imovel();
            imovel.setArea_edificada(rs.getDouble("area_edificada"));
            imovel.setArea_total(rs.getDouble("area_total"));
            imovel.setBanheiros(rs.getInt("banheiros"));
            imovel.setComodos(rs.getInt("comodos"));
            imovel.setDescricao(rs.getString("descricao"));
            imovel.setDiretorio_imagem(rs.getString("diretorio_imagem"));
            imovel.setId_imovel(rs.getInt("id_imovel"));
            imovel.setStatus(rs.getString("status"));
            imovel.setTipo_imovel(rs.getString("tipo_imovel"));
            imovel.setTitulo(rs.getString("titulo"));
            imovel.setVagas_garagem(rs.getInt("vagas_garagem"));
            imovel.setValor(rs.getDouble("valor"));
            imovel.setModalidade_imovel(rs.getString("modalidade_imovel"));
            imovel.setIptu(rs.getDouble("iptu"));
            imovel.setCondominio(rs.getDouble("condominio"));
            imovel.getUsuario().setId_usuario(rs.getInt("id_usuario"));
            imovel.getEndereco().setBairro(rs.getString("bairro"));
            imovel.getEndereco().setCep(rs.getString("cep"));
            imovel.getEndereco().setCidade(rs.getString("cidade"));
            imovel.getEndereco().setComplemento(rs.getString("complemento"));
            imovel.getEndereco().setEstado(rs.getString("estado"));
            imovel.getEndereco().setId_endereco(rs.getInt("id_endereco"));
            imovel.getEndereco().setLogradouro(rs.getString("logradouro"));
            imovel.getEndereco().setNumero(rs.getInt("numero"));

            listImoveis.add(imovel);

        }

        connection.close();
        return listImoveis;
    }
}
