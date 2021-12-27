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
 * Classe que implementa o padrão DAO para a entidade produto
 */
public class ProdutoDAO {

	String driver = "org.postgresql.Driver";
	String url = "jdbc:postgresql://localhost:5432/db_ecommerce";
	String user = "postgres";
	String password = "1234";	
	
    /**
     * Método utilizado para obter um produto pelo seu identificador
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Produto obter(int id) throws Exception {
        Produto produto = null;
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, quantidade, preco, foto FROM produto WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            produto = new Produto();
            produto.setId(resultSet.getInt("id"));
            produto.setDescricao(resultSet.getString("descricao"));
            produto.setQuantidade(resultSet.getInt("quantidade"));
            produto.setPreco(resultSet.getDouble("preco"));
            produto.setFoto(resultSet.getString("foto"));
            if (resultSet.wasNull()) {
                produto.setFoto(null);
            }
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        if (produto == null) {
            throw new Exception("Produto não encontrado");
        }
        return produto;
    }

    /**
     * Método utilizado para obter uma lista de produtos disponíveis em estoque
     *
     * @return
     * @throws Exception
     */
    public List<Produto> obterProdutosEmEstoque() throws Exception {
        List<Produto> produtos = new ArrayList<>();
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, quantidade, preco, foto FROM produto WHERE quantidade > 0");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Produto produto = new Produto();
            produto.setId(resultSet.getInt("id"));
            produto.setDescricao(resultSet.getString("descricao"));
            produto.setQuantidade(resultSet.getInt("quantidade"));
            produto.setPreco(resultSet.getDouble("preco"));
            produto.setFoto(resultSet.getString("foto"));
            if (resultSet.wasNull()) {
                produto.setFoto(null);
            }
            produtos.add(produto);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return produtos;
    }

}
