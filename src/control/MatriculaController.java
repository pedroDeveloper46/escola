package control;

import model.Aluno;
import model.Curso;
import model.MatriculaDAO;

public class MatriculaController {
	
	MatriculaDAO matriculaDAO = new MatriculaDAO();
	
	AlunoController alunoController = new AlunoController();
	
	CursoController cursoController = new CursoController();
	
	private void cadastrarMatricula(Aluno aluno, Curso curso) {
		
		matriculaDAO.cadastrarMatricula(aluno, curso);
		
	}
	
	public boolean validarMatricula(Aluno aluno, Curso curso) {
		
		if(alunoController.buscarAlunoPorId(aluno.getId_aluno()) == null) {
			
			System.out.println("Esse aluno não existe!");
			
			return false;
			
		}
		
		if (cursoController.listarCurso(curso.getId_curso()) == null) {
			
			System.out.println("Esse curso não existe");
			return false;
			
		}
		
		cadastrarMatricula(aluno, curso);
		
		return true;
	}

}
