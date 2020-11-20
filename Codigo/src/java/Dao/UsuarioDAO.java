package Dao;

import Modelo.Usuario;
import Util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Modelo.Perfil;

public class UsuarioDAO {

    Date data = new Date();
    Timestamp timestamp = new Timestamp(data.getTime());

    PreparedStatement smt;
    ResultSet rs;

    public Boolean cadastrar(Usuario usuario) throws SQLException {
        String INSERTUSUARIO = "INSERT INTO Usuario VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?,?,?)";
        String INSERTENDERECO = "INSERT INTO Endereco VALUES (DEFAULT,?,?,?,?,?,?,?)";

        String SELECT_VERIFICACAO = "SELECT email, cpf_cnpj, rg FROM Usuario "
                + "WHERE Email = ? OR cpf_cnpj = ?";

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(SELECT_VERIFICACAO);
        smt.setString(1, usuario.getEmail());
        smt.setString(2, usuario.getCpfcnpj());
        rs = smt.executeQuery();

        if (rs.next()) {
            return false;
        } else {
            //Endereço
            smt = connection.prepareStatement(INSERTENDERECO, Statement.RETURN_GENERATED_KEYS);

            smt.setString(1, usuario.getEndereco().getLogradouro());
            smt.setString(2, usuario.getEndereco().getComplemento());
            smt.setInt(3, usuario.getEndereco().getNumero());
            smt.setString(4, usuario.getEndereco().getCidade());
            smt.setString(5, usuario.getEndereco().getCep());
            smt.setString(6, usuario.getEndereco().getBairro());
            smt.setString(7, usuario.getEndereco().getEstado());
            smt.executeUpdate();

            rs = smt.getGeneratedKeys();
            rs.next();
            int idEndereco = rs.getInt(1);

            //Usuario
            smt = connection.prepareStatement(INSERTUSUARIO);

            smt.setString(1, usuario.getNome());
            smt.setTimestamp(2, timestamp);
            smt.setString(3, usuario.getTel_celular());
            smt.setString(4, usuario.getTel_residencial());
            smt.setString(5, usuario.getCpfcnpj());
            smt.setString(6, usuario.getRg());
            smt.setString(7, usuario.getTipoPessoa());
            smt.setInt(8, idEndereco);
            smt.setString(9, usuario.getEmail());
            smt.setString(10, usuario.getSenha());
            smt.setString(11, usuario.getNivel().toString());
            smt.setString(12, usuario.getSituacao());

            smt.executeUpdate();

            connection.close();

            return true;

        }

    }

    public Boolean logar(Usuario usuario) throws SQLException {

        String SELECT_LOGIN = "SELECT id_usuario, nivel_acesso, email, situacao, nome FROM Usuario "
                + "WHERE Email = ? AND Senha = ? AND Situacao = 'Ativo'";

        Boolean logado = false;
        Connection connection = ConnectionFactory.getConexao();
        smt = connection.prepareStatement(SELECT_LOGIN);
        smt.setString(1, usuario.getEmail());
        smt.setString(2, usuario.getSenha());
        rs = smt.executeQuery();

        if (rs.next()) {
            usuario.setId_usuario(rs.getInt("id_usuario"));
            usuario.setEmail(rs.getString("email"));
            usuario.setNivel(Perfil.valueOf(rs.getString("nivel_acesso")));
            usuario.setSituacao(rs.getString("situacao"));
            usuario.setNome(rs.getString("nome"));
            logado = true;
        }

        connection.close();
        return logado;

    }

    public Usuario listarPorID(int id) throws SQLException {

        String SELECT_DADOS = "SELECT * FROM Usuario U "
                + "LEFT JOIN Endereco E ON E.id_endereco = U.id_endereco "
                + "WHERE U.id_usuario = ?";

        Usuario usuario = null;
        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(SELECT_DADOS);
        smt.setInt(1, id);

        rs = smt.executeQuery();
        if (rs.next()) {
            usuario = new Usuario();
            usuario.setNome(rs.getString("nome"));
            usuario.setCpfcnpj(rs.getString("cpf_cnpj"));
            usuario.setDataCadastro(rs.getDate("data_cadastro"));
            usuario.setId_usuario(rs.getInt("id_usuario"));
            usuario.setRg(rs.getString("rg"));
            usuario.setTel_celular(rs.getString("tel_celular"));
            usuario.setTel_residencial(rs.getString("tel_residencial"));
            usuario.setTipoPessoa(rs.getString("tipo_pessoa"));

            usuario.getEndereco().setId_endereco(rs.getInt("id_endereco"));
            usuario.getEndereco().setBairro(rs.getString("bairro"));
            usuario.getEndereco().setCep(rs.getString("cep"));
            usuario.getEndereco().setCidade(rs.getString("cidade"));
            usuario.getEndereco().setComplemento(rs.getString("complemento"));
            usuario.getEndereco().setEstado(rs.getString("estado"));
            usuario.getEndereco().setId_endereco(rs.getInt("id_endereco"));
            usuario.getEndereco().setLogradouro(rs.getString("logradouro"));
            usuario.getEndereco().setNumero(rs.getInt("numero"));

            usuario.setEmail(rs.getString("email"));
            usuario.setNivel(Perfil.valueOf(rs.getString("nivel_acesso")));

        }

        connection.close();
        return usuario;
    }

    public void alterar(Usuario usuario) throws SQLException {

        String UPDATE_DADOS = "UPDATE Usuario SET Nome = ?, tel_celular =?,"
                + " tel_residencial = ?"
                + " WHERE id_usuario = ?";

        String UPDATE_USUARIOEMAIL = "UPDATE Usuario SET email = ? WHERE id_usuario = ?";

        String UPDATE_ENDERECO = "UPDATE Endereco SET logradouro = ?, complemento = ?,  numero = ?,"
                + " cidade = ?, cep = ?, bairro = ?, estado = ?"
                + " WHERE id_endereco = ?";

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(UPDATE_USUARIOEMAIL);

        smt.setString(1, usuario.getEmail());
        smt.setInt(2, usuario.getId_usuario());

        smt.executeUpdate();

        smt = connection.prepareStatement(UPDATE_DADOS);

        smt.setString(1, usuario.getNome());
        smt.setString(2, usuario.getTel_celular());
        smt.setString(3, usuario.getTel_residencial());
        smt.setInt(4, usuario.getId_usuario());

        smt.executeUpdate();

        smt = connection.prepareStatement(UPDATE_ENDERECO);

        smt.setString(1, usuario.getEndereco().getLogradouro());
        smt.setString(2, usuario.getEndereco().getComplemento());
        smt.setInt(3, usuario.getEndereco().getNumero());
        smt.setString(4, usuario.getEndereco().getCidade());
        smt.setString(5, usuario.getEndereco().getCep());
        smt.setString(6, usuario.getEndereco().getBairro());
        smt.setString(7, usuario.getEndereco().getEstado());
        smt.setInt(8, usuario.getEndereco().getId_endereco());

        smt.executeUpdate();

        connection.close();

    }

    public void alterarSenha(Usuario usuario) throws SQLException {

        String UPDATE_USUARIOSENHA = "UPDATE Usuario SET senha = ? WHERE id_usuario = ?";

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(UPDATE_USUARIOSENHA);
        smt.setString(1, usuario.getSenha());
        smt.setInt(2, usuario.getId_usuario());

        smt.executeUpdate();

        connection.close();
    }

    public List<Usuario> listarAtivos() throws SQLException {

        String SELECT_USUARIOS = "SELECT id_usuario, nome, cpf_cnpj, nivel_acesso, situacao from Usuario "
                + "  WHERE situacao = 'Ativo'"
                + " ORDER BY nivel_acesso";

        Connection connection = ConnectionFactory.getConexao();
        List<Usuario> listUsuario = new ArrayList<>();
        PreparedStatement smt = connection.prepareStatement(SELECT_USUARIOS);
        ResultSet resultSet = smt.executeQuery();

        while (resultSet.next()) {
            Usuario usuario = new Usuario();
            usuario.setId_usuario(resultSet.getInt("id_usuario"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setCpfcnpj(resultSet.getString("cpf_cnpj"));
            usuario.setNivel(Perfil.valueOf(resultSet.getString("nivel_acesso")));
            usuario.setSituacao(resultSet.getString("situacao"));
            listUsuario.add(usuario);
        }

        connection.close();

        return listUsuario;

    }

    public void excluir(Usuario usuario) throws SQLException {
        String UPDATE_SITUACAO = "UPDATE Usuario SET situacao = ?"
                + " WHERE id_usuario = ?";

        Connection connection = ConnectionFactory.getConexao();

        PreparedStatement smt = connection.prepareStatement(UPDATE_SITUACAO);

        smt.setString(1, usuario.getSituacao());
        smt.setInt(2, usuario.getId_usuario());

        smt.executeUpdate();

        connection.close();
    }
}
