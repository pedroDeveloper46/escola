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
		
	
	public void cadastrarAluno(Aluno aluno) throws SQLException {
		
		String sql = "INSERT INTO ALUNO (nome, email, senha) VALUES (?,?,?)";
		
		try(Connection conn = DbConfig.getConnection();
				PreparedStatement st = conn.prepareStatement(sql)){
			
			st.setString(1, aluno.getNome());
			st.setString(2, aluno.getEmail());
			st.setString(3, aluno.getSenha());
			st.executeUpdate();
			
		}catch(SQLException e) {
			throw new DbException("Erro ao tentar consultar os alunos" + e.getMessage());
		}
		
		
		
	}
	
	public List<Aluno> listarAlunos(){
		
		List<Aluno> lista = new ArrayList<>();
		
		String sql = "select * from aluno";
		
		try (Connection conn = DbConfig.getConnection();
				PreparedStatement st = conn.prepareStatement(sql)){
			
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
	
	
	
	

}
