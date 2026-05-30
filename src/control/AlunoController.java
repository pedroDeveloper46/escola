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
	
	public Boolean validarCadastroAluno(Aluno aluno) throws SQLException {
		
		if (!validarCamposAluno(aluno) || !validarEmail(aluno.getEmail())) {
			return false;
		}
		
		cadastrarAluno(aluno);
		
		return true;
	}
	
	public Boolean validarAtualizarCadastro(Aluno aluno, String nome, String email, String senha) {
		
		Aluno a1 = this.buscarAlunoPorEmail(email);
		
		if (a1 != null) {
			
			if (a1.getId_aluno() != aluno.getId_aluno()) {
				System.out.println("E-mail já cadastrado");
				return false;
			}				
			
		}
		
		Aluno a2 = new Aluno();
		a2.setNome(nome);
		a2.setEmail(email);
		a2.setSenha(senha);
		
		if(!validarCamposAluno(a2)) {
			return false;
		}
		
		atualizarAluno(aluno, nome, email, senha);
		
		return true;

		
	}
	
	private void cadastrarAluno(Aluno aluno) throws SQLException {
		
		aluno.criptoSenha(aluno.getSenha());
		
		alunoDAO.cadastrarAluno(aluno);
		
	}
	
	private void atualizarAluno(Aluno aluno, String nome, String email, String senha) {
		
		senha = aluno.criptoSenha(senha);
		
		alunoDAO.atualizarAluno(aluno, nome, email, senha);
		
		
		
		
	}
	
	public boolean verificarLogin(String email, String senha) {
		
		
		if (!email.isEmpty() || !senha.isEmpty()) {
			
			if (alunoDAO.existeAluno(email)) {
				
				if (!alunoDAO.verificarLogin(email, senha)) {
					System.out.println("Senha incorreta! Tente novamente!");
					return false;
				}
				
				return true;
				
			}else {
				
				System.out.println("Email não encontrado");
				return false;
				
			}
			
		}else {
			System.out.println("As crendenciais não foram preenchidas");
			return false;
		}
		
		
		
		
		
	}
	
	private boolean validarCamposAluno(Aluno aluno) {
		
		if(aluno.getEmail().isEmpty() || aluno.getNome().isEmpty() || aluno.getSenha().isEmpty()) {
			
			System.out.println("Nome, E-mail ou Senha não podem ser vazios!");
			return false;
			
		}
		
		if(!aluno.getEmail().contains("@") || !aluno.getEmail().matches(("^[A-Za-z0-9+_.-]+@(.+)$"))) {
			System.out.println("E-mail inválido!");
			return false;
		}
		
		if(aluno.getSenha().length() < 6) {
			System.out.println("A senha deve ter no mínimo 6 caracteres");
			return false;
		}
		
		if(aluno.getSenha().contains(" ")) {
			System.out.println("A senha não deve ter espaço");
			return false;
		}
		
		return true;
		
	}
	
	private boolean validarEmail(String email) {
		
		if (alunoDAO.existeAluno(email)) {
			System.out.println("Já existe um aluno cadastrado com esse e-mail!");
			return false;
		}
		
		return true;
		
	}
	
	
	
	public Aluno buscarAlunoPorId(int id) {
		return alunoDAO.buscarAlunoPorId(id);
	}
	
	public Aluno buscarAlunoPorEmail(String email) {
		return alunoDAO.buscarAlunoPorEmail(email);
	}
	
	

}
