package ecommerce.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 *
 * Classe que implementa o padrão DAO para a entidade produto
 */
public class VendaDAO {

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

	public List<Venda> obterTodasVendasUsuario(int idUsuario, boolean fecharConexao) throws Exception {

		VendaProduto vendaProduto = null;
		Venda venda = new Venda();
		List<Venda> vendasUsuario = new ArrayList<Venda>();

		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);

		PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, data_hora FROM venda WHERE id_usuario = ?");
		preparedStatement.setInt(1, idUsuario);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			venda = new Venda();
			venda.setId(resultSet.getInt("id"));
			venda.setData(resultSet.getTimestamp("data_hora"));
			venda.setIdUsuario(idUsuario);

			vendasUsuario.add(venda);		
		}

		for (Venda vendaEncontradaUsuario : vendasUsuario) {

			preparedStatement = connection.prepareStatement("SELECT vp.quantidade, vp.id_produto, p.preco, p.nome, p.descricao "
					+ " FROM venda_produto vp INNER JOIN produto p ON p.id=vp.id_produto WHERE vp.id_venda = ?");
			preparedStatement.setInt(1, vendaEncontradaUsuario.getId());
			resultSet = preparedStatement.executeQuery();
			
			vendaEncontradaUsuario.setProdutosVendidos(new ArrayList<VendaProduto>());;

			double precoTotal = 0;
			
			while (resultSet.next()) {
				vendaProduto = new VendaProduto();
				vendaProduto.setIdVenda(vendaEncontradaUsuario.getId());
				vendaProduto.setQuantidade(resultSet.getInt("quantidade"));
				vendaProduto.setIdProduto(resultSet.getInt("id_produto"));
				vendaProduto.setPreco(resultSet.getDouble("preco"));
				vendaProduto.setNome(resultSet.getString("nome"));
				vendaProduto.setDescricao(resultSet.getString("descricao"));
				precoTotal = precoTotal + vendaProduto.getQuantidade()*vendaProduto.getPreco();
				
				vendaEncontradaUsuario.getProdutosVendidos().add(vendaProduto);
			}
			
			vendaEncontradaUsuario.setTotalVenda(precoTotal);
		}

		if (fecharConexao) {
			resultSet.close();
			preparedStatement.close();
			connection.close();
		}

		/*if (vendaProduto == null) {
			throw new Exception("Venda não encontrada");
		}*/

		return vendasUsuario;
	}


/*	public List<VendaProduto> obterVendasUsuario(int idUsuario, boolean fecharConexao) throws Exception {

		VendaProduto vendaProduto = null;
		Venda venda = new Venda();
		List<VendaProduto> produtosVenda = new ArrayList<VendaProduto>();
		List<Venda> vendas = new ArrayList<Venda>();

		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);

		PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, descricao, quantidade, preco, foto, nome, ativo FROM produto WHERE id = ?");
		preparedStatement.setInt(1, idUsuario);
		ResultSet resultSet = preparedStatement.executeQuery();


		while (resultSet.next()) {
			vendaProduto = new Produto();
			vendaProduto.setId(resultSet.getInt("id"));
			vendaProduto.setDescricao(resultSet.getString("descricao"));
			vendaProduto.setQuantidade(resultSet.getInt("quantidade"));
			vendaProduto.setPreco(resultSet.getDouble("preco"));
			vendaProduto.setFoto(resultSet.getString("foto"));
			vendaProduto.setNome(resultSet.getString("nome"));
			vendaProduto.setAtivo(resultSet.getBoolean("ativo"));

			if (resultSet.wasNull()) {
				vendaProduto.setFoto(null);
			}
		}
		if (fecharConexao) {
			resultSet.close();
			preparedStatement.close();
			connection.close();
		}
		if (vendaProduto == null) {
			throw new Exception("Produto não encontrado");
		}
		return vendaProduto;
	}
*/
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
	public void inserir(int id, Timestamp data, int idUsuario, boolean fecharConexao) throws Exception {
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO venda (id, data_hora, id_usuario) VALUES (?, ?, ?)");
		preparedStatement.setInt(1, id);
		preparedStatement.setTimestamp(2,  data);
		preparedStatement.setInt(3, idUsuario);

		int resultado = preparedStatement.executeUpdate();
		if (fecharConexao) {
			preparedStatement.close();
			connection.close();
		}
		if (resultado != 1) {
			throw new Exception("Não foi possível inserir a venda");
		}
	}

	/**
	 * Método utilizado para inserir uma nova categoria do produto
	 *
	 */
	public void inserirVendaProduto(int idProduto, int idVenda, int quantidade, boolean fecharConexao) throws Exception {
		Class.forName(driver);
		Connection connection = DriverManager.getConnection(url, user, password);
		PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO venda_produto (id_produto, id_venda, quantidade) VALUES (?, ?, ?)");
		preparedStatement.setInt(1, idProduto);
		preparedStatement.setInt(2, idVenda);
		preparedStatement.setInt(3, quantidade);

		int resultado = preparedStatement.executeUpdate();
		if (fecharConexao) {

			preparedStatement.close();
			connection.close();}
		if (resultado != 1) {
			throw new Exception("Não foi possível inserir a venda do produto");
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
