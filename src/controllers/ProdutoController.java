package controllers;

import java.util.Scanner;
import java.util.UUID;

import entities.Produto;
import repositories.ProdutoRepository;

public class ProdutoController {

	// atributo
	private Scanner scanner = new Scanner(System.in);

	public void cadastrarProduto() {

		try {

			System.out.println("\nCADASTRO DE PRODUTOS:\n");

			System.out.print("NOME DO PRODUTO......:");
			var nome = scanner.nextLine();

			System.out.print("PREÇO......:");
			var preco = Double.parseDouble(scanner.nextLine());

			System.out.print("QUANTIDADE......:");
			var quantidade = Integer.parseInt(scanner.nextLine());

			var produto = new Produto(UUID.randomUUID(), nome, preco, quantidade);

			// chamada para a camada do repositories (ProdutoRepository)
			var produtoRepository = new ProdutoRepository();
			produtoRepository.inserir(produto);
			

		} catch (Exception e) {

			System.out.println("\nFalha ao cadastrar o produto!");
			System.out.println(e.getMessage());
		}

	}

	public void atualizarProduto() {

		try {

			System.out.println("\nAtualização do produto:\n");
			System.out.println("Informe o ID do produto");
			var id = UUID.fromString(scanner.nextLine());

			var produtoRepository = new ProdutoRepository();
			var produto = produtoRepository.obterPorId(id);

			// Verifica se o produto foi encontrado
			if (produto != null) {

				System.out.println("\nDados do produto: ");
				System.out.println("ID........ " + produto.getId());
				System.out.println("Nome......." + produto.getNome());
				System.out.println("Preço......" + produto.getPreco());
				System.out.println("Quantidade." + produto.getQuantidade());
				System.out.println("");

				System.out.print("ALTERE O NOME.........: ");
				produto.setNome(scanner.nextLine());

				System.out.print("ALTERE O PREÇO........: ");
				produto.setPreco(Double.parseDouble(scanner.nextLine()));

				System.out.print("ALTERE A QUANTIDADE...: ");
				produto.setQuantidade(Integer.parseInt(scanner.nextLine()));

				produtoRepository.atualizar(produto);
			

			} else {
				System.out.println("\nProduto não encontrado.Verifique o ID informado.");
			}
		}

		catch (Exception e) {
			System.out.println("\nFalha ao atualizar o produto.");
			System.out.println(e.getMessage());

		}
	}

	public void excluirProduto() {

		try {

			System.out.println("\nExclusão do produto:\n");
			System.out.println("Informe o ID do produto");
			var id = UUID.fromString(scanner.nextLine());

			var produtoRepository = new ProdutoRepository();
			var produto = produtoRepository.obterPorId(id);

			// Verifica se o produto foi encontrado
			if (produto != null) {

				System.out.println("\nDados do produto: ");
				System.out.println("ID........ " + produto.getId());
				System.out.println("Nome......." + produto.getNome());
				System.out.println("Preço......" + produto.getPreco());
				System.out.println("Quantidade." + produto.getQuantidade());
				System.out.println("");

				produtoRepository.excluir(produto.getId());
				
			} else {
				System.out.println("\nProduto não encontrado.Verifique o ID informado.");
			}
		}

		catch (Exception e) {
			System.out.println("\nFalha ao excluir o produto.");
			System.out.println(e.getMessage());

		}
	}

	public void consultarProduto() {

		try {

			System.out.println("\nConsulta de produtos:\n");

			var produtoRepository = new ProdutoRepository();
			var lista = produtoRepository.consultar();

			for (Produto produto : lista) {
				System.out.println("\nDados do produto: ");
				System.out.println("ID........... " + produto.getId());
				System.out.println("Nome.........." + produto.getNome());
				System.out.println("Preço........." + produto.getPreco());
				System.out.println("Quantidade...." + produto.getQuantidade());
				System.out.println("");
			}

		}

		catch (Exception e) {
			System.out.println("\nFalha ao consultar o produto.");
			System.out.println(e.getMessage());

		}
	}

}
