package main;

import java.sql.Connection;

import db.DbConfig;

public class Escola {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

		
	}
	
	private static void testarConexaoDB() {
		
		Connection conn = DbConfig.getConnection();
		
		if (conn != null) {
			System.out.println("Conexão com o banco de dados realizada!");
		}
		
	}

}
