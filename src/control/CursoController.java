package control;

import java.util.List;

import model.Curso;
import model.CursoDAO;

public class CursoController {
	
	private CursoDAO cursoDAO = new CursoDAO();
	
	public List<Curso> listarCursos(){
		return cursoDAO.listarCurso();
	}
	
	public Curso listarCurso(int id) {
		
		Curso curso = new Curso();
		
		curso = cursoDAO.buscarCursoPorId(id);
		
		if (curso == null) {
			System.out.println("Código inválido! Curso não encontrado");
		}
		
		return curso;
	}

}
