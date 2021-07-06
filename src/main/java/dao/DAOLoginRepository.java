package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {
	
	private Connection connection;
	
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public boolean validarAutenticacao(ModelLogin modelLogin) throws Exception {
		String sql = "select * from model_login where upper(login) = upper(?) and upper(senha) = upper(?)";
		
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setString(1, modelLogin.getLogin());
		preparedStatement.setString(2, modelLogin.getSenha());
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			return true; //autenticado
		}else {
			return	false;//nao atenticado
		}
	}
	
}
