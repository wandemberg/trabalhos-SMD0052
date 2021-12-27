package ecommerce.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * 
 * @author wandemberg
 *
 *Classe que implementa o padrão DAO para a entidade usuário
 */
public class UsuarioDAO {
	
	String driver = "org.postgresql.Driver";
	String url = "jdbc:postgresql://localhost:5432/db_ecommerce";
	String user = "postgres";
	String password = "1234";
    
	/**
     * Método utilizado para obter um usuário pelo seu identificador
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Usuario obter(int id) throws Exception {
        Usuario usuario = null;
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, endereco, email, login, senha, administrador FROM usuario WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usuario = new Usuario();
            usuario.setId(resultSet.getInt("id"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setEndereco(resultSet.getString("endereco"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setLogin(resultSet.getString("login"));
            usuario.setSenha(resultSet.getString("senha"));
            usuario.setAdministrador(resultSet.getBoolean("administrador"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (usuario == null) {
            throw new Exception("Usuário não encontrado");
        }
        return usuario;
    }

    /**
     * Método utilizado para obter um usuário pelo seu login
     *
     * @param login
     * @return
     * @throws Exception
     */
    public Usuario obter(String login) throws Exception {
        Usuario usuario = null;
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, endereco, email, login, senha, administrador FROM usuario WHERE login = ?");
        preparedStatement.setString(1, login);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            usuario = new Usuario();
            usuario.setId(resultSet.getInt("id"));
            usuario.setNome(resultSet.getString("nome"));
            usuario.setEndereco(resultSet.getString("endereco"));
            usuario.setEmail(resultSet.getString("email"));
            usuario.setLogin(resultSet.getString("login"));
            usuario.setSenha(resultSet.getString("senha"));
            usuario.setAdministrador(resultSet.getBoolean("administrador"));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (usuario == null) {
            throw new Exception("Usuário não encontrado");
        }
        return usuario;
    }

    /**
     * Método utilizado para inserir um novo usuário
     *
     * @param nome
     * @param endereco
     * @param email
     * @param login
     * @param senha
     * @param administrador
     * @throws Exception
     */
    public void inserir(String nome, String endereco, String email, String login, String senha, boolean administrador) throws Exception {
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usuario (nome, endereco, email, login, senha, administrador) VALUES (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, nome);
        preparedStatement.setString(2, endereco);
        preparedStatement.setString(3, email);
        preparedStatement.setString(4, login);
        preparedStatement.setString(5, senha);
        preparedStatement.setBoolean(6, administrador);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir o usuário");
        }
    }
}
