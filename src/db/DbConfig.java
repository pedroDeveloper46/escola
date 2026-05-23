package db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConfig {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		
		if (conn == null) {
			try {
				
				Properties prop = loadProperties();
				
				String url = prop.getProperty("dburl");
				
				conn = DriverManager.getConnection(url, prop);
				
			} catch (Exception e) {
				// TODO: handle exception
				
				throw new DbException("Erro ao tentar conectar com o banco");
			}
		}
		
		return conn;
		
	}
	
	private static Properties loadProperties() {
		
		try (FileInputStream file = new FileInputStream("db.properties")) {
			
			Properties prop = new Properties();
			
			prop.load(file);
			
			return prop;
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new DbException("Erro ao carregar o arquivo de configuração de conexão com o banco");
			
		}
		
	}
	
	public static void closeConnection() {
		
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				throw new DbException("Erro ao fechar a conexão com o banco!" + e.getMessage());
			}
		}
		
	}

}