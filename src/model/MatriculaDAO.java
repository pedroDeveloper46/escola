package model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
}
