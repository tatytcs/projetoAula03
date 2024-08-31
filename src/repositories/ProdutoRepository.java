package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import entities.Produto;
import factories.ConnectionFactory;

public class ProdutoRepository {

	public void inserir(Produto produto) {

		try {

			// Caminho e dados do banco de dados
			var connection = ConnectionFactory.getConnection();

			// gravar os dados do produto na tabela
			var statement = connection
					.prepareStatement("insert into produto(id,nome,preco,quantidade) values(?,?,?,?)");
			statement.setObject(1, produto.getId());
			statement.setString(2, produto.getNome());
			statement.setDouble(3, produto.getPreco());
			statement.setInt(4, produto.getQuantidade());

			// executar o insert
			statement.execute();

			connection.close();

			System.out.println("\nPRODUTO CADASTRADO COM SUCESSO!");

		} catch (Exception e) {
			System.out.println("\nFalha ao inserir produto!");
			System.out.println(e.getMessage());
		}
	}

	public void atualizar(Produto produto) {

		try {

			var connection = ConnectionFactory.getConnection();

			var statement = connection
					.prepareStatement("update produto set nome =?, preco =?, quantidade=? where id=?");
			statement.setString(1, produto.getNome());
			statement.setDouble(2, produto.getPreco());
			statement.setInt(3, produto.getQuantidade());
			statement.setObject(4, produto.getId());

			statement.execute();

			connection.close();
			System.out.println("\nPRODUTO ATUALIZADO COM SUCESSO!");

		} catch (Exception e) {
			System.out.println("\nFalha ao atualizar produto.");
			System.out.println(e.getMessage());
		}
	}

	public void excluir(UUID id) {

		try {

			var connection = ConnectionFactory.getConnection();

			var statement = connection.prepareStatement("delete from produto where id=?");
			statement.setObject(1, id);

			statement.execute();

			connection.close();
			
			System.out.println("\nPRODUTO EXCLUÍDO COM SUCESSO!");

		} catch (Exception e) {
			System.out.println("\nFalha ao excluir o produto.");
			System.out.println(e.getMessage());
		}

	}

	public List<Produto> consultar() {

		var lista = new ArrayList<Produto>();

		try {

			var connection = ConnectionFactory.getConnection();

			var statement = connection
					.prepareStatement("select id, nome, preco, quantidade from produto order by nome");
			var result = statement.executeQuery();

			while (result.next()) {
				var produto = new Produto();

				// lendo o dado do banco e incluindo no java
				produto.setId(UUID.fromString(result.getString("id")));
				produto.setNome(result.getString("nome"));
				produto.setPreco(result.getDouble("preco"));
				produto.setQuantidade(result.getInt("quantidade"));

				// adcionando a lista
				lista.add(produto);

				connection.close();
			}

		} catch (Exception e) {
			System.out.println("\nFalha ao consultar produtos.");
			System.out.println(e.getMessage());
		}

		return lista;
	}

	public Produto obterPorId(UUID id) {

		// Neste momento a variável não esta alocado memória.
		Produto produto = null;

		try {

			var connection = ConnectionFactory.getConnection();
			var statement = connection.prepareStatement("select id, nome, preco, quantidade from produto where id = ?");
			statement.setObject(1, id);
			var result = statement.executeQuery();

			if (result.next()) {

				produto = new Produto();

				produto.setId(UUID.fromString(result.getString("id")));
				produto.setNome(result.getString("nome"));
				produto.setPreco(result.getDouble("preco"));
				produto.setQuantidade(result.getInt("quantidade"));
			}
			connection.close();

		} catch (Exception e) {
			System.out.println("\nFalha ao consultar por id.");
			System.out.println(e.getMessage());
		}

		return produto;

	}
}
