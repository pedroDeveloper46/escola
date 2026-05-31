package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbConfig;
import db.DbException;

public class CursoDAO {
	
	
	public List<Curso> listarCurso(){
		
		List<Curso> cursos = new ArrayList<>();
		
		String sql = "select * from curso";
		
		try (PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)) {
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				Curso curso = new Curso();
				curso.setId_curso(rs.getInt("id_curso"));
				curso.setDescricao(rs.getString("descricao"));
				
				cursos.add(curso);
				
			}
			
		} catch (SQLException e) {
			throw new DbException("Erro ao listar os cursos: " +e.getMessage());
		}
		
		
		
		return cursos;
	}
	
	public Curso buscarCursoPorId(int id) {
		
		Curso curso = new Curso();
		
		String sql = "select * from curso where id_curso = " + id;
		
		try(PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)){
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				curso.setId_curso(rs.getInt("id_curso"));
				curso.setDescricao(rs.getString("descricao"));
			}
			
		}catch (SQLException e) {
			// TODO: handle exception
			throw new DbException("Erro ao listar o curso pelo código" + e.getMessage());
		}
		
		return curso;
		
	}

}
