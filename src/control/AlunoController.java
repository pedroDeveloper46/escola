package control;

import java.sql.SQLException;
import java.util.List;

import model.Aluno;
import model.AlunoDAO;

public class AlunoController {
	
	private AlunoDAO alunoDAO = new AlunoDAO();
	
	
	public List<Aluno> listarAlunos() throws SQLException{
		return alunoDAO.listarAlunos();
	}
	
	public Boolean validarAluno(Aluno aluno) throws SQLException {
		
		if(aluno.getEmail().isEmpty() || aluno.getNome().isEmpty() || aluno.getSenha().isBlank()) {
			
			System.out.println("Nome, E-mail ou Senha não podem ser vazios!");
			return false;
			
		}
		
		if (alunoDAO.existeAluno(aluno.getEmail())) {
			System.out.println("Já existe um aluno cadastrado com esse e-mail");
			return false;
		}
		
		cadastrarAluno(aluno);
		
		return true;
	}
	
	public void cadastrarAluno(Aluno aluno) throws SQLException {
		
		aluno.criptoSenha(aluno.getSenha());
		
		alunoDAO.cadastrarAluno(aluno);
		
	}

}
