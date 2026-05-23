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

}
