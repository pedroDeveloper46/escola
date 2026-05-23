package view;

import java.sql.SQLException;
import java.util.List;

import control.AlunoController;
import model.Aluno;

public class AlunoView {
	
	private AlunoController alunoController = new AlunoController();
	
	public void listarAlunos() throws SQLException {
		
		System.out.println("LISTANDO TODOS OS ALUNOS \n");
		
		List<Aluno> lista = alunoController.listarAlunos();
		
		for (Aluno aluno : lista) {
			aluno.mostrarAluno();
		}
		
	}

}
