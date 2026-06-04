package view;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import control.AlunoController;
import control.CursoController;
import control.MatriculaController;
import model.Aluno;
import model.Curso;
import model.Matricula;

public class AlunoView {
	
	private AlunoController alunoController = new AlunoController();
	
	private CursoController cursoController = new CursoController();
	
	private MatriculaController matriculaController = new MatriculaController();
	
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
		System.out.println("1 - LISTAR MEUS COLEGAS, 2 - ATUALIZAR TODOS OS MEUS DADOS, 3 - REALIZAR MATRÍCULA, 4 - CONSULTAR MINHA MATRICULAS, 5 - TRANCAR MATRÍCULA, 6 - LOGOFF");
			
		System.out.println("DIGITE A OPÇÃO DESEJADA");
		
		op = s.nextInt();
		
		while (op < 0 || op > 7) {
			
			System.out.println("OPÇÃO INVÁLIDA! DIGITE A OPÇÃO DESEJADA");
			
			op = s.nextInt();
		}
		
		if(op == 1) {
			this.listarAlunos();
		}else if(op == 2) {
			
			this.atualizarAluno(aluno);
			
		}else if (op == 3) {
			//implementar matricula / usar objeto aluno
			
			boolean bool2 = false;
			boolean bool = false;
			
			Curso curso = new Curso();
			
			while(!bool2 || !bool) {
				
				System.out.println("ABAIXO ESTARÃO OS CURSOS E SEUS RESPECTIVOS CÓDIGOS \n");
				
				this.listarCursos();
				
				//implementar método que busque o curso através de um id digitado
				
				curso = buscarCursoPorId();
				
				
				bool2 = matriculaController.buscarMatriculaPorIdAlunoCurso(aluno, curso);
				
				bool = matriculaController.validarMatricula(aluno, curso);
			}
			
			//implementar um método que receba (aluno, curso) para matricula
			
			
			System.out.println(aluno.getNome() + ", sua matricula no curso " + curso.getDescricao() + " foi realizada com sucesso!");
			
			
			
		}else if(op == 4) {
			
			listarCursosMatriculados(aluno);
			
		}else if(op == 5) {
			
			//implementar trancamento da matricula / usa objeto aluno
			
			listarMatriculasPorAluno(aluno);
			
			trancarMatricula(aluno);
			
			
			
			
		}else if(op == 6) {
			
			//implementar a consulta dos alunos que estão matriculados em cada curso
			
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
	
	private Curso buscarCursoPorId() {
		
		Scanner in = new Scanner(System.in);
		
		Curso curso = null;
		
		while (curso == null) {
			
			System.out.println("DIGITE O CÓDIGO DO CURSO QUE DESEJA SE MATRICULAR");
			
			int cod = in.nextInt();
			
			curso = cursoController.listarCurso(cod);
		}
		
		return curso;
		
		
	}
	
	private void listarCursos() {
		
		List<Curso> cursos = cursoController.listarCursos();
		
		for (Curso curso : cursos) {
			curso.mostrarCurso();
			System.out.println();
		}
		
	}
	
	private void listarCursosMatriculados(Aluno aluno) {
			
		System.out.println(aluno.getNome() + ", ESSES SÃO OS CURSOS QUE VOCÊ ESTÁ MATRICULADO \n");
		
		List<Curso> cursos = matriculaController.listarMatriculas(aluno);
		
		if(cursos.isEmpty()) {
			System.out.println("SEM MATRÍCULAS");
		}else {
			
			for (Curso curso : cursos) {
				System.out.println("CÓD Curso:"+curso.getId_curso());
				System.out.println("Curso: " +curso.getDescricao());
			}
		}
		
		
			
	}
	
	private void listarMatriculasPorAluno(Aluno aluno) {
		
		System.out.println(aluno.getNome() + ", ESSAS SÃO AS SUAS MATRICULAS");
		
		List<Matricula> matriculas = matriculaController.listarMatriculaPorAluno(aluno);
		
		if(matriculas.isEmpty()) {
			System.out.println("VOCÊ NÃO ESTÁ MATRICULADO EM NENHUM CURSO");
		}else {
			for (Matricula matricula : matriculas) {
				System.out.println("CÓD DA MATRICULA:" +matricula.getId_matricula());
				System.out.println("DESCRIÇÃO DO CURSO: " + matricula.getCurso().getDescricao());
				System.out.println();
			}
		}
		
		
	}
	
	private void trancarMatricula(Aluno aluno) {
		
		
		boolean bool = false;
		
		while (!bool) {
			
			System.out.println("DIGITE O CÓDIGO DA MATRÍCULA QUE VOCÊ DESEJA FECHAR A MATRÍCULA");
			
			op = s.nextInt();
			
			bool = matriculaController.validarExcluirMatricula(aluno, op);
		}
		
		Matricula matricula = matriculaController.buscarMatriculaPorId(aluno, op);
		
		System.out.println("FECHAMENTO DE MATRÍCULA NO CURSO" + matricula.getCurso().getDescricao()+ " FOI REALIZADA COM SUCESSO");
		
		
		
	}
	
	private void limparAluno(Aluno aluno) {
		aluno.setNome("");
		aluno.setEmail("");
		aluno.setSenha("");
	}

}
