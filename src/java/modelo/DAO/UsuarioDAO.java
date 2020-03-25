package modelo.DAO;

import modelo.Usuario;
import util.DataAccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Perfil;

public class UsuarioDAO {

    java.util.Date data = new java.util.Date();
    java.sql.Timestamp timestamp = new java.sql.Timestamp(data.getTime());

    //INSERTS
    private final String INSERTUSUARIO = "INSERT INTO Usuario VALUES (DEFAULT,?,?,?,?,?,?,?,?)";
    private final String INSERTLOGIN = "INSERT INTO Login VALUES (?,?,?,?,?)";
    private final String INSERTENDERECO = "INSERT INTO Endereco VALUES (DEFAULT,?,?,?,?,?,?,?)";
    //SELECTS
    private final String SELECT_LOGIN = "SELECT U.id_usuario, email, senha, nivel_acesso, nome, U.id_endereco FROM "
            + "Login L LEFT JOIN Usuario U ON U.id_usuario = l.id_usuario "
            + "WHERE Email = ? AND Senha = ? AND L.Situacao = 'Ativo'";
    
    private final String SELECT_DADOSUSUARIOS = "SELECT * FROM Usuario U "
            + "LEFT JOIN Login L ON L.id_usuario = U.id_Usuario "
            + "LEFT JOIN Endereco E ON E.id_endereco = U.id_endereco "
            + "WHERE U.id_usuario = ?";
    
    private final String SELECT_VERIFICACAO = "SELECT email, cpf_cnpj, rg FROM Login L LEFT JOIN Usuario U "
            + "ON U.id_usuario = l.id_usuario WHERE Email = ? OR cpf_cnpj = ?";
    //UPDATES
    private final String UPDATE_USUARIODADOS = "UPDATE Usuario SET Nome = ?, tel_celular =?,"
            + " tel_residencial = ?"
            + " WHERE id_usuario = ?";
    
    private final String UPDATE_USUARIOENDERECO = "UPDATE Endereco SET logradouro = ?, complemento = ?,  numero = ?,"
            + " cidade = ?, cep = ?, bairro = ?, estado = ?"
            + " WHERE id_endereco = ?";
    
    private final String UPDATE_USUARIOSENHA = "UPDATE Login SET senha = ? WHERE id_usuario = ?";

    PreparedStatement smt;
    ResultSet rs;

    public boolean cadastrar(Usuario Usuario, HttpServletRequest request, HttpServletResponse response) throws SQLException {

        boolean sucesso = false;
        String msg = "";
        try (Connection connection = DataAccess.getConexao()) {

            smt = connection.prepareStatement(SELECT_VERIFICACAO);
            smt.setString(1, Usuario.getLogin().getEmail());
            smt.setString(2, Usuario.getCpfcnpj());
            rs = smt.executeQuery();

            if (rs.next()) {
                msg = "EMAIL ou CPF/CNPJ já cadastrado em nosso sistema, utilize o botão 'Recuperar senha'";
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
                connection.close();
                msg = "Cadastrado com sucesso!";
                sucesso = true;
            }

        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            msg = ex.getMessage();
        }

        request.setAttribute("msg", msg);
        return sucesso;
    }

    public boolean logar(Usuario Usuario) throws SQLException, Exception {
        boolean Autenticado = false;
        try (Connection connection = DataAccess.getConexao()) {
            PreparedStatement smt = connection.prepareStatement(SELECT_LOGIN);
            smt.setString(1, Usuario.getLogin().getEmail());
            smt.setString(2, Usuario.getLogin().getSenha());
            ResultSet resultado = smt.executeQuery();
            if (resultado.next()) {

                //da um substring no primeiro espaço que encontrar para pegar somente o primeiro nome
                String nome = "";
                String linha = resultado.getString("nome");
                String pattern = "\\p{L}+";

                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(linha);
                if (m.find()) {
                    nome = m.group(0);
                }

                Usuario.setNome(nome);
                Usuario.setId_usuario(resultado.getInt("id_usuario"));
                Usuario.getLogin().setEmail(resultado.getString("email"));
                Usuario.getLogin().setSenha(resultado.getString("senha"));
                Usuario.getEndereco().setId_endereco(resultado.getInt("id_endereco"));
                Usuario.getLogin().setNivel(Perfil.valueOf(resultado.getString("nivel_acesso")));

                connection.close();
                Autenticado = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Autenticado;
    }

    public ResultSet listar(int id) throws SQLException, Exception {
        ResultSet rs;

        try (Connection connection = DataAccess.getConexao()) {

            PreparedStatement smt = connection.prepareStatement(SELECT_DADOSUSUARIOS);
            smt.setInt(1, id);
            rs = smt.executeQuery();
            if (rs.next()) {
                connection.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return rs;
    }

    public boolean alterar(Usuario Usuario, HttpServletRequest request, HttpServletResponse response) throws SQLException, Exception {
        boolean Atualizado = false;
        String msg = "";
        String acao = (String) request.getAttribute("acao");
        try (Connection connection = DataAccess.getConexao()) {

            if (acao.equalsIgnoreCase("userdados")) {
                PreparedStatement smt = connection.prepareStatement(UPDATE_USUARIODADOS);

                smt.setString(1, Usuario.getNome());
                smt.setString(2, Usuario.getTel_celular());
                smt.setString(3, Usuario.getTel_residencial());
                smt.setInt(4, Usuario.getId_usuario());

                smt.executeUpdate();
                msg = "Dados de cadastro atualizados com sucesso!";
            } else if (acao.equalsIgnoreCase("userendereco")) {
                PreparedStatement smt = connection.prepareStatement(UPDATE_USUARIOENDERECO);

                smt.setString(1, Usuario.getEndereco().getLogradouro());
                smt.setString(2, Usuario.getEndereco().getComplemento());
                smt.setInt(3, Usuario.getEndereco().getNumero());
                smt.setString(4, Usuario.getEndereco().getCidade());
                smt.setString(5, Usuario.getEndereco().getCep());
                smt.setString(6, Usuario.getEndereco().getBairro());
                smt.setString(7, Usuario.getEndereco().getEstado());
                smt.setInt(8, Usuario.getEndereco().getId_endereco());

                smt.executeUpdate();
                msg = "Dados de endereço atualizados com sucesso!";

            } else {
                PreparedStatement smt = connection.prepareStatement(UPDATE_USUARIOSENHA);

                smt.setString(1, Usuario.getLogin().getSenha());
                smt.setInt(2, Usuario.getId_usuario());

                smt.executeUpdate();

                msg = "Senha alterada com sucesso, por favor logue novamente!";
            }
            Atualizado = true;
            connection.close();
            request.setAttribute("msg", msg);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("msgerro", ex.getMessage());
        }
        return Atualizado;
    }
}
