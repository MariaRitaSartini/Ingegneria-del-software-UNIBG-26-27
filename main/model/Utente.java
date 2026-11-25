package model;

public abstract class Utente {
	public int id;
	String nome;
	String cognome;
	String email;
	String indirizzo;
	String telefono;
	
	public Utente(String nome, String cognome, String email, String indirizzo, String telefono, int id) {
		this.nome=nome;
		this.cognome=cognome;
		this.email=email;
		this.indirizzo=indirizzo;
		this.telefono=telefono;
		this.id=id;
	}
}
