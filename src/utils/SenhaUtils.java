package utils;

import org.mindrot.jbcrypt.BCrypt;

public class SenhaUtils {

	public static String gerarHash(String senha) {
		return BCrypt.hashpw(senha, BCrypt.gensalt());
	}
	
	public static boolean validarSenha(String senhaDigitada, String hashArmazenada) {
		return BCrypt.checkpw(senhaDigitada, hashArmazenada);
	}
	
}
