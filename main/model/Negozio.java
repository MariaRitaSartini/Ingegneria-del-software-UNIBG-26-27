package model;

public class Negozio {
	int id=0;
	String nome;
	Categoria cateogria;
	String email;
	String telefono;
	Commerciante myProprietario;
	
	public Negozio(String nome, Categoria categoria, String email, String telefono, Commerciante proprietario){
		this.nome=nome;
		this.cateogria=categoria;
		this.email=email;
		this.telefono=telefono;
		this.myProprietario=proprietario;
	}

}
