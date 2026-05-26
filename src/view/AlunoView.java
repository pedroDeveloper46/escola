package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import control.AlunoController;
import model.Aluno;

public class AlunoView {
	
	private AlunoController alunoController = new AlunoController();
	
	private Scanner s = new Scanner(System.in);
	
	private int op;
	
	public AlunoView() throws SQLException {
		
		this.executarViewAluno();
		
	}
	
	private void executarViewAluno() throws SQLException {
		
		System.out.println("BEM VINDO!");
		
		System.out.println("FAÇA O LOGIN OU CADASTRO! DIGITE A OPÇÃO DESEJADA");
		System.out.println("1 - LOGIN");
		System.out.println("2 - CADASTRO");
		
		op = s.nextInt();
		
		while (op < 0 || op > 2 ) {
			
			System.out.println("1 - LOGIN");
			System.out.println("2 - CADASTRO");
			
			op = s.nextInt();
			
		}
		
		if(op == 1) {
			//login
		}
		
		if(op == 2) {
			this.cadastrarAluno();
		}
		
	}
	
	private void listarAlunos() throws SQLException {
		
		System.out.println("LISTANDO TODOS OS ALUNOS \n");
		
		List<Aluno> lista = alunoController.listarAlunos();
		
		for (Aluno aluno : lista) {
			aluno.mostrarAluno();
		}
		
	}
	
	private void cadastrarAluno() throws SQLException {
		
		System.out.println("INICIANDO O SEU CADASTRO COMO ALUNO...");
		
		Aluno aluno = new Aluno();
		
		Scanner s = new Scanner(System.in);
		
		Boolean bool = false;
		
		while (!bool) {
			
			System.out.println("DIGITE O SEU NOME ");
			aluno.setNome(s.next());
			
			System.out.println("DIGITE O SEU EMAIL ");
			aluno.setEmail(s.next());
			
			System.out.println("DIGITE A SUA SENHA ");
			aluno.setSenha(s.next());
			
			bool = alunoController.validarAluno(aluno);
		}
				
		System.out.println("CADASTRO REALIZADO COM SUCESSO");
		
	}
	
	private void alunoLogado() throws SQLException {
		
		System.out.println("OLÁ ALUNO, AQUI VOCÊ PODERÁ REALIZAR MÁTRICULAS, CONSULTAR OS SEUS COLEGAS, ATUALIZAR SEUS DADOS e MANTER INATIVO O CADASTRO NA ESCOLA!");
		
		System.out.println("OPÇÕES ABAIXO");
		System.out.println("1 - LISTAR ALUNOS");
		System.out.println("2 - CADASTRAR ALUNO");	
		
		
		System.out.println("DIGITE A OPÇÃO DESEJADA");
		
		op = s.nextInt();
		
		while (op < 0) {
			
			System.out.println("DIGITE A OPÇÃO DESEJADA");
			
			op = s.nextInt();
		}
		
		if(op == 1) {
			this.listarAlunos();
		}
	}

}
