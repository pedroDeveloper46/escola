package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import db.DbConfig;
import db.DbException;

public class AlunoDAO {
	
	private DbConfig db = new DbConfig();
	
	private boolean isClosedDb = false;
		
	
	public void cadastrarAluno(Aluno aluno) throws SQLException {
		
		
		
		String sql = "INSERT INTO ALUNO (nome, email, senha) VALUES (?,?,?)";
		
		try{
			
			PreparedStatement st = db.retornaPreparedStatement(sql);
			
			st.setString(1, aluno.getNome());
			st.setString(2, aluno.getEmail());
			st.setString(3, aluno.getSenha());
			st.executeUpdate();
			
			
			
		}catch(SQLException e) {
			throw new DbException("Erro ao tentar cadastrar o aluno" + e.getMessage());
		}
		
		
		
	}
	
	public List<Aluno> listarAlunos(){
		
	
		
		List<Aluno> lista = new ArrayList<>();
		
		String sql = "select * from aluno";
		
		try {
			
			PreparedStatement st = db.retornaPreparedStatement(sql);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				Aluno aluno = new Aluno();
				
				aluno = aluno.criarAluno(rs.getInt("id_aluno"), rs.getString("nome"), rs.getString("email"));
				
				lista.add(aluno);
				
			}
			
			
			
			
		}catch(SQLException e) {
			throw new DbException("Erro ao tentar consultar os alunos" + e.getMessage());
		}
		
		return lista;
		
	}
	
	public boolean existeAluno(String email) {
		
		
		 
		String sql = "select * from aluno where email = ?";
		
		try {
			
			PreparedStatement st = db.retornaPreparedStatement(sql);
			
			st.setString(1, email);
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
			
			return false;
			
			
		} catch(SQLException e) {
			throw new DbException("Erro ao buscar aluno por e-mail" + e.getMessage());
						
		}
		
		
		
		
		
	}
	
	private void verificarAbreConexaoDb() {
		
		if (isClosedDb) {
			db = new DbConfig(); 
			isClosedDb = false;
		}
		
	}
	
	private void fechaConexao() {
		db.closeConnection();
		isClosedDb = true;
		
	}
	
	
	
	

}
