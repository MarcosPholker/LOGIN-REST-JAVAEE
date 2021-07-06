package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;

	
	public static Connection getConnection() {
		return connection;
	}
	
	static {// faz a conexao sempre que a classe for chama
		
		conectar();
	}
	
	public SingleConnectionBanco () {//quando ouver uma instancia a classe de conexao ira se conectar
		conectar();
	}
	
	
	private static void conectar() {
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver"); //carrega driver de conexao
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false);//para nao efetuar alteracoes no banco sem nosso comando
 			}
		} catch (Exception e) {
			e.printStackTrace(); //mostrar erros de conexao
		}
	}
	
}
