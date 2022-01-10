package ecommerce.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Classe que implementa o padrão DAO para a entidade categoria
 */
public class CategoriaDAO {

	String driver = "org.postgresql.Driver";
	String url = "jdbc:postgresql://localhost:5432/db_ecommerce";
	String user = "postgres";
	String password = "1234";	
	
    /**
     * Método utilizado para obter uma categoria pelo seu identificador
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Categoria obter(int id) throws Exception {
        Categoria categoria = null;
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, ativo FROM categoria WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            categoria = new Categoria();
            categoria.setId(resultSet.getInt("id"));
            categoria.setDescricao(resultSet.getString("descricao"));
            categoria.setAtivo(resultSet.getBoolean("ativo"));  
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (categoria == null) {
            throw new Exception("Categoria não encontrado");
        }
        return categoria;
    }

    /**
     * Método utilizado para obter uma lista de categorias disponíveis
     *
     * @return
     * @throws Exception
     */
    public List<Categoria> obterCategoriasEmEstoque() throws Exception {
        List<Categoria> categorias = new ArrayList<>();
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, ativo FROM categoria ");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Categoria categoria = new Categoria();
            categoria.setId(resultSet.getInt("id"));
            categoria.setDescricao(resultSet.getString("descricao"));
            categoria.setAtivo(resultSet.getBoolean("ativo"));
        
            categorias.add(categoria);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return categorias;
    }

    /**
     * Método utilizado para obter uma lista de categorias pesquisadas
     *
     * @return
     * @throws Exception
     */
    public List<Categoria> obterCategoriasPesquisadas(String nome, String codigo) throws Exception {
        List<Categoria> categorias = new ArrayList<>();
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = null;
        
        if ((nome==null || nome.equals(""))&&(codigo==null || codigo.equals(""))) {
	        preparedStatement = connection.prepareStatement("SELECT id, descricao, ativo FROM categoria WHERE  ativo = TRUE ");
        } else if ((nome==null || nome.equals(""))&&!(codigo==null || codigo.equals(""))) {
	        preparedStatement = connection.prepareStatement("SELECT id, descricao, ativo FROM categoria WHERE id = ?  AND ativo = TRUE ");
	        preparedStatement.setInt(1, Integer.parseInt(codigo));
        } else if (!(nome==null || nome.equals(""))&&(codigo==null || codigo.equals(""))) {
	        preparedStatement = connection.prepareStatement("SELECT id, descricao, ativo FROM categoria "
	        		+ " WHERE descricao ILIKE ? AND ativo = TRUE ");
	        preparedStatement.setString(1, nome);
        } else {
	        preparedStatement = connection.prepareStatement("SELECT id, descricao, ativo FROM categoria WHERE id = ? "
	        		+ " AND descricao ILIKE ?  AND ativo = TRUE ");
	        preparedStatement.setInt(1, Integer.parseInt(codigo));
	        preparedStatement.setString(2, nome);
        }
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Categoria categoria = new Categoria();
            categoria.setId(resultSet.getInt("id"));
            categoria.setDescricao(resultSet.getString("descricao"));
                        

            categorias.add(categoria);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return categorias;
    }
    
    /**
     * Método utilizado para armazenar o caminho da foto de uma categoria
     *
     * @param id
     * @param caminhoFoto
     * @throws Exception
     */
    public void atualizarFoto(int id, String caminhoFoto) throws Exception {
        boolean sucesso = false;
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categoria SET foto = ? WHERE id = ?");
        preparedStatement.setString(1, caminhoFoto);
        preparedStatement.setInt(2, id);
        sucesso = (preparedStatement.executeUpdate() == 1);
        preparedStatement.close();
        connection.close();
        if (!sucesso) {
            throw new Exception("Não foi possível atualizar a foto da categoria");
        }
    }
    
    /**
     * Método utilizado para inserir uma nova categoria
     *
     * @param nome
     * @param endereco
     * @param email
     * @param login
     * @param senha
     * @param administrador
     * @throws Exception
     */
    public void inserir(int codigo, String descricao) throws Exception {
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categoria (id, descricao) VALUES (?, ?)");
        preparedStatement.setInt(1, codigo);
        preparedStatement.setString(2, descricao);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir a categoria");
        }
    }
    
    /**
     * Método utilizado para atualizar uma categoria
     *
     */
    public void atualizar( int codigo, String descricao) throws Exception {
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "UPDATE categoria SET descricao = ? WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, descricao);
        preparedStatement.setInt(2, codigo);

        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        if (resultado != 1) {
            throw new Exception("Não foi possível atualizar a categoria");
        }              
    }
    
    /**
     * Método utilizado para desativar uma categoria
     *
     */
    public void desativar(int codigo) throws Exception {
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        String sql = "UPDATE categoria SET ativo = ? WHERE id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setBoolean(1, false);
        preparedStatement.setInt(2, codigo);

        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        
        if (resultado != 1) {
            throw new Exception("Não foi possível remover categoria");
        }
              
    }
}
