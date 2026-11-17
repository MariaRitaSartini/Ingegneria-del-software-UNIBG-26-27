package model;

public class Commerciante extends Utente{
	
	public Negozio myNegozio;

	public Commerciante(String nome, String cognome, String email, String indirizzo, String telefono) {
		super(nome, cognome, email, indirizzo, telefono);
	}
	
	public void setNegozio(String nomeNegozio, Categoria categoria, String emailNegozio, String telefonoNegozio) {
		this.myNegozio= new Negozio(nomeNegozio, categoria, emailNegozio, telefonoNegozio, this);
	}

}
