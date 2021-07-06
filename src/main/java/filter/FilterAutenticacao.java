package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


import jakarta.servlet.Filter;
import Connection.SingleConnectionBanco;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/principal/*" }) // intercepta todas as requisicoes que vierem do projeto ou mapeamento
public class FilterAutenticacao implements Filter {

	private static Connection connection;

	public FilterAutenticacao() {
	}

	/* encerra os processos quando o servidor é parado */
	// mataria os processos de conexao com o banco de dados
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* intercepta todas as requisicoes e as respostas do sistema */
	// tudo que fizer no sistema ira passar pelo metodo dofilter
	// validação de autenticação
	// dar commit e rolback de transações no banco
	// fazer redirecionamento ou valir paginas
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpSession httpSession = httpServletRequest.getSession();

		String usuarioLogado = (String) httpSession.getAttribute("usuario");

		String urlParaAutenticar = httpServletRequest.getServletPath();/* url que esta sendo acessada */

		/* validar se esta logado se nao redireciona para a tela de login */
		try {
			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {// nao esta
																											// logado

				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "por favor realize o login!");
				redireciona.forward(request, response);

				return;// para a execucao e redireciona para tela de login
			} else {
				chain.doFilter(request, response);
			}
			connection.commit();// deu tudo certo entao commita as auteraçoes no banco

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	/* ele inicia os processos ou recursos quando o servidor sobe o projeto */
	// iniciar a ocnexao com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}
}
