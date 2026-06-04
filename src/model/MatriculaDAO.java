package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DbConfig;
import db.DbException;

public class MatriculaDAO {

	public void cadastrarMatricula(Aluno aluno, Curso curso) {
		
		String sql = "insert into matricula (id_aluno_FK, id_curso_FK) values (" + aluno.getId_aluno() + ", " + curso.getId_curso() + ")";
		
		try (PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)) {
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException("Erro ao tentar realizar uma matricula: "+ e.getMessage());
		}
		
		
	}
	
	public List<Curso> listarMatriculas(Aluno aluno){
		
		List<Curso> cursosMatriculados = new ArrayList<>();
		
		String sql = "select descricao, id_curso from curso \r\n"
				+ "inner join matricula on curso.id_curso = matricula.id_curso_FK \r\n"
				+ "inner join aluno on aluno.id_aluno = matricula.id_aluno_FK\r\n"
				+ "where matricula.id_aluno_FK =" + aluno.getId_aluno();
		
		try(PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)) {
			
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Curso curso = new Curso();
				
				curso.setId_curso(rs.getInt("id_curso"));
				curso.setDescricao(rs.getString("descricao"));
				
				cursosMatriculados.add(curso);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			throw new DbException("Erro ao buscar os cursos matriculados do " + aluno.getNome() + ":" +e.getMessage());
		}
		
		return cursosMatriculados;
		
	}
	
	public void trancarMatricula(Aluno aluno, Matricula matricula) {
		
		String sql = "delete from matricula where id_aluno_FK = " + aluno.getId_aluno() + " and id_matricula =" + matricula.getId_matricula();
		
		try(PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)){
			
			st.executeUpdate();
			
		}catch (SQLException e) {
			// TODO: handle exception
			
			throw new DbException("Erro ao tentar excluir uma matricula:" +e.getMessage());
		}
		
	}
	
	public Matricula buscarMatriculaPorId(Aluno aluno, int id) {
		
		Matricula matriculaDb = new Matricula();
		
		String sql = "select id_matricula, descricao from matricula \r\n"
				+ "inner join curso on matricula.id_curso_FK = curso.id_curso where id_aluno_FK = " +aluno.getId_aluno() +" and matricula.id_matricula = " +id;
		
		try(PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)) {
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				Curso curso = new Curso();
				curso.setDescricao(rs.getString("descricao"));
				matriculaDb.setId_matricula(rs.getInt("id_matricula"));
				matriculaDb.setCurso(curso);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new DbException("Erro ao buscar matricula:" +e.getMessage());
		}
		
		return matriculaDb;
		
	}
	
	public Matricula buscarMatriculaPorIdAlunoCurso(Aluno aluno, Curso curso) {
		
		Matricula matricula = new Matricula();
		
		String sql = "select * from matricula where id_curso_FK = " +curso.getId_curso() + " and id_aluno_FK = " + aluno.getId_aluno();
		
		try(PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)) {
			
			ResultSet rs = st.executeQuery();
			
			if (rs.next()) {
				matricula.setId_matricula(rs.getInt("id_matricula"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			throw new DbException("Erro ao buscar a matricula com Id do Aluno e Id do Curso:" + e.getMessage());
		
		}
		
		return matricula;
		
	}
	
	public List<Matricula> listarMatriculaPorAluno(Aluno aluno){
		
		List<Matricula> matriculas = new ArrayList<>();
		
		String sql = "select id_matricula, descricao from matricula "
				+ "inner join curso on matricula.id_curso_FK = curso.id_curso where id_aluno_FK =" +aluno.getId_aluno();
		
		try(PreparedStatement st = DbConfig.getConnection().prepareStatement(sql)) {
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				Matricula matricula = new Matricula();
				Curso curso = new Curso();
				matricula.setId_matricula(rs.getInt("id_matricula"));
				curso.setDescricao(rs.getString("descricao"));
				
				matricula.setCurso(curso);
				
				matriculas.add(matricula);
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException("Erro ao buscar as matriculas: "+e.getMessage());
		}
		
		return matriculas;
	}
	
}
