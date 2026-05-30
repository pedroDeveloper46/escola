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
		
		Aluno alunoLogado = new Aluno();
		
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
		
		s.nextLine();
		
		if(op == 1) {
			
			String email = "";
			String senha = "";
			
			boolean bool = false;
			
			while (!bool) {
				
				System.out.println("LOGIN");
				
				System.out.println("DIGITE O SEU E-MAIL:");
				
				email = s.nextLine();
				
				System.out.println("DIGITE A SUA SENHA:");
				
				senha = s.nextLine();
				
				bool = alunoController.verificarLogin(email, senha);
				
			}
			
			alunoLogado = alunoController.buscarAlunoPorEmail(email);
			System.out.println("LOGIN REALIZADO COM SUCESSO!");
			
			System.out.println();
			
			System.out.println("BEM VINDO, " +alunoLogado.getNome() + "!");
			
			viewAlunoHome(alunoLogado);
												
				
		
			
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
			
			System.out.println("DIGITE O SEU NOME");
			aluno.setNome(s.nextLine());
			
			System.out.println("DIGITE O SEU EMAIL");
			aluno.setEmail(s.nextLine());
			
			System.out.println("DIGITE A SUA SENHA");
			aluno.setSenha(s.nextLine());
			
			bool = alunoController.validarCadastroAluno(aluno);
			
			limparAluno(aluno);
		}
				
		System.out.println("CADASTRO REALIZADO COM SUCESSO");
		
	}
	
	private void viewAlunoHome(Aluno aluno) throws SQLException {
		
		System.out.println();
		
		System.out.println("OLÁ " + aluno.getNome() + ", AQUI VOCÊ PODERÁ REALIZAR MÁTRICULAS, CONSULTAR OS SEUS COLEGAS, ATUALIZAR SEUS DADOS e MANTER INATIVO O CADASTRO NA ESCOLA!");
		
		System.out.println("OPÇÕES ABAIXO");
		System.out.println("1 - LISTAR MEUS COLEGAS, 2 - ATUALIZAR TODOS OS MEUS DADOS, 3 - REALIZAR MATRÍCULA, 4 - TRANCAR MATRÍCULA, 5 - LOGOFF");
			
		System.out.println("DIGITE A OPÇÃO DESEJADA");
		
		op = s.nextInt();
		
		while (op < 0 || op > 5) {
			
			System.out.println("OPÇÃO INVÁLIDA! DIGITE A OPÇÃO DESEJADA");
			
			op = s.nextInt();
		}
		
		if(op == 1) {
			this.listarAlunos();
		}else if(op == 2) {
			this.atualizarAluno(aluno);
			
		}else if (op == 3) {
			//implementar matricula / usar objeto aluno
		}else if(op == 4) {
			//implementar trancamento da matricula / usa objeto aluno
		}else {
			this.executarViewAluno();
		}
		
		this.viewAlunoHome(aluno);
	}
	
	private void atualizarAluno(Aluno aluno) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println(aluno.getNome() + ", INICIANDO ATUALIZAÇÃO DOS SEUS DADOS");
		
	    boolean bool = false;
	    
	    while (!bool) {
			
	    	System.out.println("DIGITE O SEU NOME");
			String nome = in.nextLine();
			
			System.out.println("DIGITE O SEU E-MAIL");
			String email = in.nextLine();
			
			System.out.println("DIGITE A SUA SENHA");
			String senha = in.nextLine();
			
			bool = alunoController.validarAtualizarCadastro(aluno, nome, email, senha);
			
		}
	    
	  
		System.out.println(aluno.getNome() + ", O SEU CADASTRO FOI ATUALIZADO COM SUCESSO!");

		
		
	}
	
	private void limparAluno(Aluno aluno) {
		aluno.setNome("");
		aluno.setEmail("");
		aluno.setSenha("");
	}

}
