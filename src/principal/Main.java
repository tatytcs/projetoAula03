package principal;

import java.util.Scanner;

import controllers.ProdutoController;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("\nSistema de produtos\n");

		System.out.println("(1) Cadastrar produtos");
		System.out.println("(2) Consultar produtos");
		System.out.println("(3) Atualizar produtos");
		System.out.println("(4) Excluir produtos");

		System.out.print("\nInforme a opção desejada: ");
		var opcao = scanner.nextLine();

		var produtoController = new ProdutoController();

		switch (opcao) {
		case "1":
			produtoController.cadastrarProduto();
			break;
		case "2":
			produtoController.consultarProduto();
			break;
		case "3":
			produtoController.atualizarProduto();
			break;
		case "4":
			produtoController.excluirProduto();
			break;
		}

		System.out.println("Deseja continuar? (S, N): ");
		var continuar = scanner.nextLine();

		if (continuar.equalsIgnoreCase("S")) {
			// Recursividade (retornar para o início do método main
			main(args);
		} else {
			System.out.println("\nFim do programa!");
		}
	}
}
