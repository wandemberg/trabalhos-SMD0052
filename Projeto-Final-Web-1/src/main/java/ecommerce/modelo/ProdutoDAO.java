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
	public Produto obter(int id, boolean fecharConexao) throws Exception {
		Produto produto = null;
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, quantidade, preco, foto, nome, ativo FROM produto WHERE id = ?");
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			produto = new Produto();
			produto.setId(resultSet.getInt("id"));
			produto.setDescricao(resultSet.getString("descricao"));
			produto.setQuantidade(resultSet.getInt("quantidade"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setFoto(resultSet.getString("foto"));
			produto.setNome(resultSet.getString("nome"));
			produto.setAtivo(resultSet.getBoolean("ativo"));

			if (resultSet.wasNull()) {
				produto.setFoto(null);
			}
		}
		if (fecharConexao) {
			resultSet.close();
			preparedStatement.close();
			connection.close();
		}
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
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, descricao, quantidade, preco, foto, ativo FROM produto WHERE quantidade > 0");
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Produto produto = new Produto();
			produto.setId(resultSet.getInt("id"));
			produto.setDescricao(resultSet.getString("descricao"));
			produto.setQuantidade(resultSet.getInt("quantidade"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setFoto(resultSet.getString("foto"));
			produto.setNome(resultSet.getString("nome"));
			produto.setAtivo(resultSet.getBoolean("ativo"));

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
	
	/**
	 * Método utilizado para obter uma lista de produtos disponíveis em estoque
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Produto> obterProdutosFaltandoEmEstoque() throws Exception {
		List<Produto> produtos = new ArrayList<>();
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT id, nome, descricao, quantidade, preco, foto, ativo "
				+ " FROM produto "
				+ " WHERE quantidade < 1 "
				+ " ORDER BY descricao");
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Produto produto = new Produto();
			produto.setId(resultSet.getInt("id"));
			produto.setDescricao(resultSet.getString("descricao"));
			produto.setQuantidade(resultSet.getInt("quantidade"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setFoto(resultSet.getString("foto"));
			produto.setNome(resultSet.getString("nome"));
			produto.setAtivo(resultSet.getBoolean("ativo"));

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

	/**
	 * Método utilizado para obter uma lista de produtos pesquisado
	 *
	 * @return
	 * @throws Exception
	 */
	public List<Produto> obterProdutosPesquisados(String nome, String codigo) throws Exception {
		List<Produto> produtos = new ArrayList<>();
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		PreparedStatement preparedStatement = null;

		if ((nome==null || nome.equals(""))&&(codigo==null || codigo.equals(""))) {
			preparedStatement = connection.prepareStatement("SELECT id, descricao, quantidade, preco, foto, nome, ativo FROM produto WHERE  ativo = TRUE ");
		} else if ((nome==null || nome.equals(""))&&!(codigo==null || codigo.equals(""))) {
			preparedStatement = connection.prepareStatement("SELECT id, descricao, quantidade, preco, foto, nome, ativo FROM produto WHERE id = ?  AND ativo = TRUE ");
			preparedStatement.setInt(1, Integer.parseInt(codigo));
		} else if (!(nome==null || nome.equals(""))&&(codigo==null || codigo.equals(""))) {
			preparedStatement = connection.prepareStatement("SELECT id, descricao, quantidade, preco, foto, nome, ativo FROM produto "
					+ " WHERE descricao ILIKE ? AND ativo = TRUE ");
			preparedStatement.setString(1, nome);
		} else {
			preparedStatement = connection.prepareStatement("SELECT id, descricao, quantidade, preco, foto, nome, ativo FROM produto WHERE id = ? "
					+ " AND descricao ILIKE ?  AND ativo = TRUE ");
			preparedStatement.setInt(1, Integer.parseInt(codigo));
			preparedStatement.setString(2, nome);
		}

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			Produto produto = new Produto();
			produto.setId(resultSet.getInt("id"));
			produto.setDescricao(resultSet.getString("descricao"));
			produto.setQuantidade(resultSet.getInt("quantidade"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setFoto(resultSet.getString("foto"));
			produto.setNome(resultSet.getString("nome"));
			produto.setAtivo(resultSet.getBoolean("ativo"));

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

	/**
	 * Método utilizado para armazenar o caminho da foto de um produto
	 *
	 * @param id
	 * @param caminhoFoto
	 * @throws Exception
	 */
	public void atualizarFoto(int id, String caminhoFoto) throws Exception {
		boolean sucesso = false;
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produto SET foto = ? WHERE id = ?");
		preparedStatement.setString(1, caminhoFoto);
		preparedStatement.setInt(2, id);
		sucesso = (preparedStatement.executeUpdate() == 1);
		preparedStatement.close();
		connection.close();
		if (!sucesso) {
			throw new Exception("Não foi possível atualizar a foto do produto");
		}
	}

	/**
	 * Método utilizado para inserir um novo produto
	 *
	 * @param nome
	 * @param endereco
	 * @param email
	 * @param login
	 * @param senha
	 * @param administrador
	 * @throws Exception
	 */
	public void inserir(String nome, int codigo, String foto, double preco, String descricao, int quantidade, boolean fecharConexao) throws Exception {
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto (nome, id, foto, preco, descricao, quantidade) VALUES (?, ?, ?, ?, ?, ?)");
		preparedStatement.setString(1, nome);
		preparedStatement.setInt(2, codigo);
		preparedStatement.setString(3, foto);
		preparedStatement.setDouble(4, preco);
		preparedStatement.setString(5, descricao);
		preparedStatement.setInt(6, quantidade);
		int resultado = preparedStatement.executeUpdate();
		if (fecharConexao) {
			preparedStatement.close();
			connection.close();
		}
		if (resultado != 1) {
			throw new Exception("Não foi possível inserir o produto");
		}
	}

	/**
	 * Método utilizado para inserir uma nova categoria do produto
	 *
	 */
	public void inserirCategoriaProduto(int idProduto, int idCategoria, boolean fecharConexao) throws Exception {
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto_categoria (id_produto, id_categoria) VALUES (?, ?)");
		preparedStatement.setInt(1, idProduto);
		preparedStatement.setInt(2, idCategoria);
		int resultado = preparedStatement.executeUpdate();
		if (fecharConexao) {

			preparedStatement.close();
			connection.close();}
		if (resultado != 1) {
			throw new Exception("Não foi possível inserir a categoria do produto");
		}
	}

	/**
	 * Método utilizado para verificar se tem uma categoria o produto
	 *
	 */
	public Integer categoriaProduto(int idProduto, boolean fecharConexao) throws Exception {
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT id_categoria FROM produto_categoria WHERE id_produto = ? ");
		preparedStatement.setInt(1, idProduto);
		ResultSet resultSet  = preparedStatement.executeQuery();
		if (fecharConexao) {

			preparedStatement.close();
			connection.close();
		}
		if (!resultSet.next()) {
			return null;
		} else {
			return resultSet.getInt("id_categoria");
		}        
	}

	/**
	 * Método utilizado para remover uma categoria do produto novo produto
	 *
	 */
	public void removerCategoriaProduto(int idProduto, int idCategoria, boolean fecharConexao) throws Exception {
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produto_categoria WHERE id_produto = ? AND id_categoria = ? ");
		preparedStatement.setInt(1, idProduto);
		preparedStatement.setInt(2, idCategoria);
		int resultado = preparedStatement.executeUpdate();
		if (fecharConexao) {

			preparedStatement.close();
			connection.close();
		}
		if (resultado != 1) {
			throw new Exception("Não foi possível remover a categoria do produto");
		}
	}


	/**
	 * Método utilizado para atualizar um produto
	 *
	 */
	public void atualizar(String nome, int codigo, String foto, double preco, String descricao, int quantidade, boolean fecharConexao) throws Exception {
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		String sql = "UPDATE produto SET nome = ? , foto = ? , preco = ? , descricao = ? , quantidade = ?  WHERE id = ? ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, nome);
		preparedStatement.setString(2, foto);
		preparedStatement.setDouble(3, preco);
		preparedStatement.setString(4, descricao);
		preparedStatement.setInt(5, quantidade);
		preparedStatement.setInt(6, codigo);

		int resultado = preparedStatement.executeUpdate();
		if (fecharConexao) {
			preparedStatement.close();
			connection.close();
		}
		if (resultado != 1) {
			throw new Exception("Não foi possível atualizar o produto");
		}              
	}

	/**
	 * Método utilizado para desativar um produto
	 *
	 */
	public void desativar(int codigo) throws Exception {
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		String sql = "UPDATE produto SET ativo = ? WHERE id = ? ";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setBoolean(1, false);
		preparedStatement.setInt(2, codigo);

		int resultado = preparedStatement.executeUpdate();
		preparedStatement.close();
		connection.close();

		if (resultado != 1) {
			throw new Exception("Não foi possível remover produto");
		}

	}
}
