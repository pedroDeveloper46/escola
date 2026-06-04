package control;

import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Curso;
import model.Matricula;
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
	
	public List<Curso> listarMatriculas(Aluno aluno){
		
		List<Curso> cursosMatriculados = new ArrayList<>();
		
		cursosMatriculados = matriculaDAO.listarMatriculas(aluno);
		
		return cursosMatriculados;
		
	}
	
	public boolean validarExcluirMatricula(Aluno aluno, int id) {
		
		Matricula matricula = buscarMatriculaPorId(aluno, id);
		
		if (matricula == null) {
			return false;
		}
		
		excluirMatricula(aluno, matricula);
		
		return true;
		
		
	}
	
	public Matricula buscarMatriculaPorId(Aluno aluno, int id) {
		
		Matricula matricula = new Matricula();
		
		matricula = matriculaDAO.buscarMatriculaPorId(aluno, id);
		
		if (matricula == null) {
			System.out.println("Matricula não encontrada!");
			return null;
		}
		
		return matricula;
		
		 
	}
	
	private void excluirMatricula(Aluno aluno, Matricula matricula) {
		matriculaDAO.trancarMatricula(aluno, matricula);
	}
	
	public boolean buscarMatriculaPorIdAlunoCurso(Aluno aluno, Curso curso) {
		
		if(matriculaDAO.buscarMatriculaPorIdAlunoCurso(aluno, curso) != null) {
			
			System.out.println("Você já está matriculado no curso");
			
			return false;
		}
		
		return true;
		
	}
	
	public List<Matricula> listarMatriculaPorAluno(Aluno aluno){
		return matriculaDAO.listarMatriculaPorAluno(aluno);
	}

}
