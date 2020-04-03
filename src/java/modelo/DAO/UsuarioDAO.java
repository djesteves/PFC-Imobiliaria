package modelo.DAO;

import modelo.Usuario;
import util.DataAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Perfil;
import modelo.Sessao;

public class UsuarioDAO {

    Date data = new Date();
    Timestamp timestamp = new Timestamp(data.getTime());

    PreparedStatement smt;
    ResultSet rs;

    private final String INSERTUSUARIO = "INSERT INTO Usuario VALUES (DEFAULT,?,?,?,?,?,?,?,?)";
    private final String INSERTLOGIN = "INSERT INTO Login VALUES (?,?,?,?,?)";
    private final String INSERTENDERECO = "INSERT INTO Endereco VALUES (DEFAULT,?,?,?,?,?,?,?)";

    private final String SELECT_VERIFICACAO = "SELECT email, cpf_cnpj, rg FROM Login L LEFT JOIN Usuario U "
            + "ON U.id_usuario = l.id_usuario WHERE Email = ? OR cpf_cnpj = ?";

    public boolean cadastrar(Usuario Usuario) {

        boolean sucesso = false;
        try (Connection connection = DataAccess.getConexao()) {

            smt = connection.prepareStatement(SELECT_VERIFICACAO);
            smt.setString(1, Usuario.getLogin().getEmail());
            smt.setString(2, Usuario.getCpfcnpj());
            rs = smt.executeQuery();

            if (rs.next()) {
                sucesso = false;
            } else {
                //Endereço
                smt = connection.prepareStatement(INSERTENDERECO, Statement.RETURN_GENERATED_KEYS);

                smt.setString(1, Usuario.getEndereco().getLogradouro());
                smt.setString(2, Usuario.getEndereco().getComplemento());
                smt.setInt(3, Usuario.getEndereco().getNumero());
                smt.setString(4, Usuario.getEndereco().getCidade());
                smt.setString(5, Usuario.getEndereco().getCep());
                smt.setString(6, Usuario.getEndereco().getBairro());
                smt.setString(7, Usuario.getEndereco().getEstado());
                smt.execute();

                rs = smt.getGeneratedKeys();
                rs.next();

                //Usuario
                smt = connection.prepareStatement(INSERTUSUARIO, Statement.RETURN_GENERATED_KEYS);

                smt.setString(1, Usuario.getNome());
                smt.setTimestamp(2, timestamp);
                smt.setString(3, Usuario.getTel_celular());
                smt.setString(4, Usuario.getTel_residencial());
                smt.setString(5, Usuario.getCpfcnpj());
                smt.setString(6, Usuario.getRg());
                smt.setString(7, Usuario.getTipoPessoa());
                smt.setInt(8, rs.getInt(1));
                smt.execute();

                rs = smt.getGeneratedKeys();
                rs.next();

                //Login
                smt = connection.prepareStatement(INSERTLOGIN);

                smt.setString(1, Usuario.getLogin().getEmail());
                smt.setString(2, Usuario.getLogin().getSenha());
                smt.setString(3, Usuario.getLogin().getNivel().toString());
                smt.setString(4, "Ativo");
                smt.setInt(5, rs.getInt(1));

                smt.execute();

                smt.close();
                rs.close();
                connection.close();

                sucesso = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return sucesso;
    }

    private final String SELECT_LOGIN = "SELECT * FROM Usuario U "
            + "LEFT JOIN Login L ON L.id_usuario = U.id_Usuario "
            + "LEFT JOIN Endereco E ON E.id_endereco = U.id_endereco "
            + "WHERE Email = ? AND Senha = ? AND L.Situacao = 'Ativo'";

    public Sessao logar(Usuario usuario) {
        Sessao sessao = null;
        try (Connection connection = DataAccess.getConexao()) {
            smt = connection.prepareStatement(SELECT_LOGIN);
            smt.setString(1, usuario.getLogin().getEmail());
            smt.setString(2, usuario.getLogin().getSenha());
            ResultSet resultado = smt.executeQuery();
            if (resultado.next()) {
                sessao = new Sessao(resultado.getString("nome"), resultado.getInt("id_usuario"), Perfil.valueOf(resultado.getString("nivel_acesso")));
                connection.close();
                resultado.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sessao;

    }

    private final String SELECT_DADOS = "SELECT * FROM Usuario U "
            + "LEFT JOIN Login L ON L.id_usuario = U.id_Usuario "
            + "LEFT JOIN Endereco E ON E.id_endereco = U.id_endereco "
            + "WHERE U.id_usuario = ?";

    public Usuario getUsuario(int id) {
        Usuario usuario = null;
        try (Connection connection = DataAccess.getConexao()) {

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

                connection.close();

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return usuario;
    }

    private final String UPDATE_DADOS = "UPDATE Usuario SET Nome = ?, tel_celular =?,"
            + " tel_residencial = ?"
            + " WHERE id_usuario = ?";

    private final String UPDATE_ENDERECO = "UPDATE Endereco SET logradouro = ?, complemento = ?,  numero = ?,"
            + " cidade = ?, cep = ?, bairro = ?, estado = ?"
            + " WHERE id_endereco = ?";

    public boolean alterar(Usuario Usuario) {
        boolean Atualizado = false;
        try (Connection connection = DataAccess.getConexao()) {

            smt = connection.prepareStatement(UPDATE_DADOS);

            smt.setString(1, Usuario.getNome());
            smt.setString(2, Usuario.getTel_celular());
            smt.setString(3, Usuario.getTel_residencial());
            smt.setInt(4, Usuario.getId_usuario());

            smt.executeUpdate();

            smt = connection.prepareStatement(UPDATE_ENDERECO);

            smt.setString(1, Usuario.getEndereco().getLogradouro());
            smt.setString(2, Usuario.getEndereco().getComplemento());
            smt.setInt(3, Usuario.getEndereco().getNumero());
            smt.setString(4, Usuario.getEndereco().getCidade());
            smt.setString(5, Usuario.getEndereco().getCep());
            smt.setString(6, Usuario.getEndereco().getBairro());
            smt.setString(7, Usuario.getEndereco().getEstado());
            smt.setInt(8, Usuario.getEndereco().getId_endereco());

            smt.executeUpdate();

            Atualizado = true;
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return Atualizado;
    }

    private final String UPDATE_USUARIOSENHA = "UPDATE Login SET senha = ? WHERE id_usuario = ?";

    public boolean AlterarSenha(int id) {

        try (Connection connection = DataAccess.getConexao()) {
            Usuario usuario = new Usuario();

            smt = connection.prepareStatement(UPDATE_USUARIOSENHA);
            smt.setString(1, usuario.getLogin().getSenha());
            smt.setInt(2, id);

            boolean rowUpdate = smt.executeUpdate() > 0;

            return rowUpdate;
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
