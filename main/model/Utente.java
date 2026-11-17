package model;

abstract class Utente {
	int id=0;
	String nome;
	String cognome;
	String email;
	String indirizzo;
	String telefono;
	
	public Utente(String nome, String cognome, String email, String indirizzo, String telefono) {
		this.nome=nome;
		this.cognome=cognome;
		this.email=email;
		this.indirizzo=indirizzo;
		this.telefono=telefono;
		this.id=id++;
		id++;
	}
}
