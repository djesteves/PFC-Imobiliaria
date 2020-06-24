package modelo.DAO;

import modelo.Usuario;
import util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import modelo.Perfil;
import modelo.Sessao;

public class UsuarioDAO {

    Date data = new Date();
    Timestamp timestamp = new Timestamp(data.getTime());

    PreparedStatement smt;
    ResultSet rs;

    public boolean cadastrar(Usuario usuario) {

        String INSERTUSUARIO = "INSERT INTO Usuario VALUES (DEFAULT,?,?,?,?,?,?,?,?)";
        String INSERTLOGIN = "INSERT INTO Login VALUES (?,?,?,?,?)";
        String INSERTENDERECO = "INSERT INTO Endereco VALUES (DEFAULT,?,?,?,?,?,?,?)";

        String SELECT_VERIFICACAO = "SELECT email, cpf_cnpj, rg FROM Login L LEFT JOIN Usuario U "
                + "ON U.id_usuario = l.id_usuario WHERE Email = ? OR cpf_cnpj = ?";

        try (Connection connection = ConnectionFactory.getConexao()) {

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
                smt.execute();

                rs = smt.getGeneratedKeys();
                rs.next();

                //Usuario
                smt = connection.prepareStatement(INSERTUSUARIO, Statement.RETURN_GENERATED_KEYS);

                smt.setString(1, usuario.getNome());
                smt.setTimestamp(2, timestamp);
                smt.setString(3, usuario.getTel_celular());
                smt.setString(4, usuario.getTel_residencial());
                smt.setString(5, usuario.getCpfcnpj());
                smt.setString(6, usuario.getRg());
                smt.setString(7, usuario.getTipoPessoa());
                smt.setInt(8, rs.getInt(1));
                smt.execute();

                rs = smt.getGeneratedKeys();
                rs.next();

                //Login
                smt = connection.prepareStatement(INSERTLOGIN);

                smt.setString(1, usuario.getLogin().getEmail());
                smt.setString(2, usuario.getLogin().getSenha());
                smt.setString(3, usuario.getLogin().getNivel().toString());
                smt.setString(4, "Ativo");
                smt.setInt(5, rs.getInt(1));

                smt.execute();

                smt.close();
                rs.close();
                connection.close();

                return true;
            }

        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
            return false;
        }
    }

    public Sessao logar(Usuario usuario) {

        String SELECT_LOGIN = "SELECT * FROM Usuario U "
                + "LEFT JOIN Login L ON L.id_usuario = U.id_Usuario "
                + "LEFT JOIN Endereco E ON E.id_endereco = U.id_endereco "
                + "WHERE Email = ? AND Senha = ? AND L.Situacao = 'Ativo'";

        Sessao sessao = null;
        try (Connection connection = ConnectionFactory.getConexao()) {
            smt = connection.prepareStatement(SELECT_LOGIN);
            smt.setString(1, usuario.getLogin().getEmail());
            smt.setString(2, usuario.getLogin().getSenha());
            ResultSet resultado = smt.executeQuery();
            if (resultado.next()) {
                sessao = new Sessao(resultado.getString("email"), resultado.getString("nome"), resultado.getInt("id_usuario"), Perfil.valueOf(resultado.getString("nivel_acesso")));
                connection.close();
                resultado.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        }
        return sessao;

    }

    public Usuario getUsuario(int id) {

        String SELECT_DADOS = "SELECT * FROM Usuario U "
                + "LEFT JOIN Login L ON L.id_usuario = U.id_Usuario "
                + "LEFT JOIN Endereco E ON E.id_endereco = U.id_endereco "
                + "WHERE U.id_usuario = ?";

        Usuario usuario = null;
        try (Connection connection = ConnectionFactory.getConexao()) {

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

                connection.close();

            }
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        }
        return usuario;
    }

    public boolean alterar(Usuario usuario) {

        String UPDATE_DADOS = "UPDATE Usuario SET Nome = ?, tel_celular =?,"
                + " tel_residencial = ?"
                + " WHERE id_usuario = ?";

        String UPDATE_USUARIOEMAIL = "UPDATE Login SET email = ? WHERE id_usuario = ?";

        String UPDATE_ENDERECO = "UPDATE Endereco SET logradouro = ?, complemento = ?,  numero = ?,"
                + " cidade = ?, cep = ?, bairro = ?, estado = ?"
                + " WHERE id_endereco = ?";

        boolean rowUpdate = false;
        try (Connection connection = ConnectionFactory.getConexao()) {

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
            
            connection.close();
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
        }
        return rowUpdate;
    }

    public boolean AlterarSenha(int id, Usuario usuario) {

        String UPDATE_USUARIOSENHA = "UPDATE Login SET senha = ? WHERE id_usuario = ?";
        boolean rowUpdate = false;

        try (Connection connection = ConnectionFactory.getConexao()) {

            smt = connection.prepareStatement(UPDATE_USUARIOSENHA);
            smt.setString(1, usuario.getLogin().getSenha());
            smt.setInt(2, id);

            rowUpdate = smt.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Erro:" + ex);
            return false;
        }
        return rowUpdate;
    }
}
