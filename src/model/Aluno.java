package model;

public class Aluno {

	private Integer id_aluno;
	
	private String nome;
	
	private String email;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String senha;

	public Integer getId_aluno() {
		return id_aluno;
	}

	public void setId_aluno(Integer id_aluno) {
		this.id_aluno = id_aluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Aluno: [id_aluno =" + id_aluno + ", Nome =" + nome + "]";
	}
	
	public Aluno criarAluno(Integer id, String nome, String email) {
		
		Aluno a = new Aluno();
		
		a.setId_aluno(id);
		a.setNome(nome);
		a.setEmail(email);
		
		return a;
		
		
	}
	
	public void mostrarAluno() {
		System.out.println("ID: " + this.getId_aluno());
		System.out.println("Nome: " + this.getNome());
		System.out.println("Email " + this.getEmail());
		System.out.println();
	}
	
	
	
	
	
}
