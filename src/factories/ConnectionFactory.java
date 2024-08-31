package factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	// Método que retorna a conexão
	public static Connection getConnection() {

		Connection connection = null;

		try {
			// Aciona o JAR adicionado do projeto e abre a conexão com o banco de dados
			Class.forName("org.postgresql.Driver");

			// Caminho e dados do banco de dados
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bd_aula02", "postgres", "coti");
		} catch (Exception e) {
			System.out.println("\nFalha ao abrir conexão com o banco de dados!");
			System.out.println(e.getMessage());
		}
		return connection;
	}

}
