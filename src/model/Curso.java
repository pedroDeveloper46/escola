package model;

public class Curso {
	
	private Integer id_curso;
	
	private String descricao;

	public Integer getId_curso() {
		return id_curso;
	}

	public void setId_curso(Integer id_curso) {
		this.id_curso = id_curso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void mostrarCurso() {
		
		System.out.println("CÓDIGO DO CURSO: " + this.getId_curso());
		System.out.println("CURSO: " + this.getDescricao());
		
	}
	
	

}
