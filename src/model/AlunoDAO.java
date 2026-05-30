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
		
		try(PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)){
			
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
		
		try (PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)) {
			
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
		
		try(PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)) {
			
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
	
	public Aluno buscarAlunoPorEmail(String email) {
		
		Aluno aluno = new Aluno();
		
		String sql = "select * from aluno where email = ?";
		
		try(PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)) {
			
			st.setString(1, email);
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				
				aluno.setId_aluno(rs.getInt("id_aluno"));
				aluno.setNome(rs.getString("nome"));
				aluno.setEmail(rs.getString("email"));
				aluno.setSenha(rs.getString("senha"));
				
			}
			
			
			return aluno;
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			throw new DbException("Erro ao buscar aluno por E-mail" + e.getMessage());
		}
		
		
		
		
	}
	
	public Aluno buscarAlunoPorId(int id) {
		
		Aluno aluno = new Aluno();
		
		String sql = "select * from aluno where id_aluno = ?";
		
		try (PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)) {
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				
				aluno.setId_aluno(rs.getInt("id_aluno"));
				aluno.setNome(rs.getString("nome"));
				aluno.setEmail(rs.getString("email"));
				aluno.setSenha(rs.getString("senha"));
				
			}
				
			return aluno;
		
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException("Erro ao buscar aluno por Id: " +e.getMessage());
		}
		
	}
	
	public void atualizarAluno(Aluno aluno, String nome, String email, String senha) {
		
		String sql = "update aluno set nome = ?, email = ?, senha = ? where id_aluno = ?";
		try(PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)){
			
			st.setString(1, nome);
			
			st.setString(2, email);
			
			st.setString(3, senha);
			
			st.setInt(4, aluno.getId_aluno());
			
			st.executeUpdate();
			
		}catch (SQLException e) {
			// TODO: handle exception
			
			throw new DbException("Erro ao tentar atualizar o aluno" + e.getMessage());
		}
		
	}
	
	public boolean verificarLogin(String email, String senha) {
		
		Aluno aluno = buscarAlunoPorEmail(email);
		
		if (aluno.verificarSenha(senha)) {
			return true;
		}
		
		return false;
		
	}
	
		
	

}
