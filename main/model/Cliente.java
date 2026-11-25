package model;

public class Cliente extends Utente{
	
	String username;
	String pw;

	public Cliente(String nome, String cognome, String email, String indirizzo, String telefono, int id) {
		super(nome, cognome, email, indirizzo, telefono, id);	
	}
	
	public void setUsername(String username){
		this.username=username;
	}
	
	public void setPw(String pw) {
		this.pw=pw;
	}

}
