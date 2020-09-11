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
import Modelo.Sessao;

public class UsuarioDAO {

    Date data = new Date();
    Timestamp timestamp = new Timestamp(data.getTime());

    PreparedStatement smt;
    ResultSet rs;

    public boolean cadastrar(Usuario usuario, String modo) throws SQLException {

        String INSERTUSUARIO = "INSERT INTO Usuario VALUES (DEFAULT,?,?,?,?,?,?,?,?)";
        String INSERTLOGIN = "INSERT INTO Login VALUES (?,?,?,?,?)";
        String INSERTENDERECO = "INSERT INTO Endereco VALUES (DEFAULT,?,?,?,?,?,?,?)";
        String INSERTFUNCIONARIO = "INSERT INTO Funcionario VALUES (DEFAULT,?)";

        String SELECT_VERIFICACAO = "SELECT email, cpf_cnpj, rg FROM Login L LEFT JOIN Usuario U "
                + "ON U.id_usuario = l.id_usuario WHERE Email = ? OR cpf_cnpj = ?";

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(SELECT_VERIFICACAO);
        smt.setString(1, usuario.getLogin().getEmail());
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
            smt = connection.prepareStatement(INSERTUSUARIO, Statement.RETURN_GENERATED_KEYS);

            smt.setString(1, usuario.getNome());
            smt.setTimestamp(2, timestamp);
            smt.setString(3, usuario.getTel_celular());
            smt.setString(4, usuario.getTel_residencial());
            smt.setString(5, usuario.getCpfcnpj());
            smt.setString(6, usuario.getRg());
            smt.setString(7, usuario.getTipoPessoa());
            smt.setInt(8, idEndereco);
            smt.executeUpdate();

            rs = smt.getGeneratedKeys();
            rs.next();
            int idUsuario = rs.getInt(1);

            //Login
            smt = connection.prepareStatement(INSERTLOGIN);

            smt.setString(1, usuario.getLogin().getEmail());
            smt.setString(2, usuario.getLogin().getSenha());
            smt.setString(3, usuario.getLogin().getNivel().toString());
            smt.setString(4, "Ativo");
            smt.setInt(5, idUsuario);

            boolean rowInserted = smt.executeUpdate() > 0;

            //Funcionario
            if (modo.equalsIgnoreCase("funcionario")) {
                smt = connection.prepareStatement(INSERTFUNCIONARIO);
                smt.setInt(1, idUsuario);
                smt.executeUpdate();
            }

            smt.close();
            connection.close();

            return rowInserted;
        }

    }

    public Sessao logar(Usuario usuario) throws SQLException {

        String SELECT_LOGIN = "SELECT * FROM Usuario U "
                + "LEFT JOIN Login L ON L.id_usuario = U.id_Usuario "
                + "LEFT JOIN Endereco E ON E.id_endereco = U.id_endereco "
                + "WHERE Email = ? AND Senha = ? AND L.Situacao = 'Ativo'";

        Sessao sessao = null;
        Connection connection = ConnectionFactory.getConexao();
        smt = connection.prepareStatement(SELECT_LOGIN);
        smt.setString(1, usuario.getLogin().getEmail());
        smt.setString(2, usuario.getLogin().getSenha());
        ResultSet resultado = smt.executeQuery();
        if (resultado.next()) {
            sessao = new Sessao(resultado.getString("nome"),
                    resultado.getInt("id_usuario"),
                    Perfil.valueOf(resultado.getString("nivel_acesso")),
                    resultado.getString("email"));
        }

        smt.close();
        connection.close();
        return sessao;

    }

    public Usuario listarPorID(int id) throws SQLException {

        String SELECT_DADOS = "SELECT * FROM Usuario U "
                + "LEFT JOIN Login L ON L.id_usuario = U.id_Usuario "
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

            usuario.getLogin().setEmail(rs.getString("email"));

        }

        smt.close();
        rs.close();
        connection.close();
        return usuario;
    }

    public boolean alterar(Usuario usuario) throws SQLException {

        String UPDATE_DADOS = "UPDATE Usuario SET Nome = ?, tel_celular =?,"
                + " tel_residencial = ?"
                + " WHERE id_usuario = ?";

        String UPDATE_USUARIOEMAIL = "UPDATE Login SET email = ? WHERE id_usuario = ?";

        String UPDATE_ENDERECO = "UPDATE Endereco SET logradouro = ?, complemento = ?,  numero = ?,"
                + " cidade = ?, cep = ?, bairro = ?, estado = ?"
                + " WHERE id_endereco = ?";

        boolean rowUpdate = false;
        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(UPDATE_USUARIOEMAIL);

        smt.setString(1, usuario.getLogin().getEmail());
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

        rowUpdate = smt.executeUpdate() > 0;

        smt.close();
        connection.close();
        return rowUpdate;
    }

    public boolean alterarSenha(int id, Usuario usuario) throws SQLException {

        String UPDATE_USUARIOSENHA = "UPDATE Login SET senha = ? WHERE id_usuario = ?";

        Connection connection = ConnectionFactory.getConexao();

        smt = connection.prepareStatement(UPDATE_USUARIOSENHA);
        smt.setString(1, usuario.getLogin().getSenha());
        smt.setInt(2, id);

        boolean rowUpdate = smt.executeUpdate() > 0;

        smt.close();
        connection.close();
        return rowUpdate;
    }

    public List<Usuario> listarAtivos() throws SQLException {

        String SELECT_USUARIOS = "SELECT U.id_usuario, nome, cpf_cnpj, nivel_acesso, l.situacao FROM "
                + "Login L LEFT JOIN Usuario U ON U.id_usuario = l.id_usuario "
                + "  WHERE l.situacao = 'Ativo'"
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
            usuario.getLogin().setNivel(Perfil.valueOf(resultSet.getString("nivel_acesso")));
            usuario.getLogin().setSituacao(resultSet.getString("situacao"));
            listUsuario.add(usuario);
        }

        resultSet.close();
        smt.close();
        connection.close();

        return listUsuario;

    }

    public boolean excluir(int id) throws SQLException {
        String UPDATE_SITUACAO = "UPDATE Login SET situacao = ?"
                + " WHERE id_usuario = ?";

        Connection connection = ConnectionFactory.getConexao();

        PreparedStatement smt = connection.prepareStatement(UPDATE_SITUACAO);

        smt.setString(1, "Inativo");
        smt.setInt(2, id);

        boolean rowUpdate = smt.executeUpdate() > 0;

        smt.close();
        connection.close();
        return rowUpdate;

    }
}
