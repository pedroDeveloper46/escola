package main;

import java.sql.Connection;
import java.sql.SQLException;

import db.DbConfig;
import view.AlunoView;

public class Escola {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		AlunoView alunoView = new AlunoView();
		
		alunoView.listarAlunos();

		
	}
	
	private static void testarConexaoDB() {
		
		Connection conn = DbConfig.getConnection();
		
		if (conn != null) {
			System.out.println("Conexão com o banco de dados realizada!");
		}
		
	}

}
