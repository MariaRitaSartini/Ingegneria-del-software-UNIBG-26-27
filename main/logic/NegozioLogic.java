package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Categoria;
import model.Commerciante;
import model.Negozio;
import model.Utente;


public class NegozioLogic {
	public int id=0;
	
	
	//INDIRIZZO DB
	String DB_REL_FILE = "database.db3";
	String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;
	
	// Unica istanza della classe
    private static NegozioLogic instance;
	
	private NegozioLogic() {
		String query = "SELECT MAX(ID_NEGOZIO) FROM NEGOZI";

	    try (Connection conn = DriverManager.getConnection(DB_URL);
	         Statement st = conn.createStatement();
	         ResultSet rs = st.executeQuery(query)) {

	        if (rs.next()) {
	            this.id=rs.getInt(1);
	            System.out.println("IL MASSIMO ID NEGOZIO è: "+ this.id);
	        }else {
	        	this.id=0;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static NegozioLogic getInstance() {

        if (instance == null) {
            instance = new NegozioLogic();
        }

        return instance;
    }
	
	//-----------------------------------------------------------------------------------------
	//------------------------METODO DI CREAZIONE DEL NEGOZIO----------------------------------
	//-----------------------------------------------------------------------------------------
	public Negozio createNegozio(Commerciante c, String nome, Categoria categoria, String email, String telefono) {
		Negozio n= new Negozio(nome, categoria, email, telefono, c);
		try {
			Connection conn = DriverManager.getConnection(DB_URL); 
			
			//query
			String query= "INSERT INTO NEGOZI "+"(ID_NEGOZIO, NOME, CATEGORIA, EMAIL, TELEFONO, ID_PROPRIETARIO) " +
		            "VALUES ('" +
		            this.id + "', '" +
		            nome + "', '" +
		            categoria.toString() + "', '" +
		            email + "', '" +
		            telefono + "', '" +
		            c.id  + "')";
		            
			Statement st = conn.createStatement();
			st.executeUpdate(query);
			
			System.out.println("L'ID del tuo negozio è: "+ this.id);
			
			//chiusura connessione 
			conn.close();
			} catch (SQLException e) {
			  System.out.println(e.getMessage());
			}
		this.id++;
		return n;
	}
	
	
	//-----------------------------------------------------------------------------------------
	//------------------------METODO DI ELIMINAZIONE DEL NEGOZIO-------------------------------
	//-----------------------------------------------------------------------------------------
	public void eliminaNegozio(int id) {
		try {
			String query = "DELETE FROM NEGOZI WHERE ID_NEGOZIO = " + id;

			Connection conn = DriverManager.getConnection(DB_URL); 
		
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		 
			//chiusura connessione 
			conn.close();
			System.out.println("negozio eliminato");
			
		}catch (SQLException e) {
		  System.out.println(e.getMessage());
		}
	}
	
	
	//-----------------------------------------------------------------------------------------
	//---------------METODO DI ELIMINAZIONE DEL NEGOZIO DI UN PROPRIETARIO ELIMINATO-----------	
	//-----------------------------------------------------------------------------------------
	public void eliminaProprietario(int id) {
		try {
			String query = "DELETE FROM NEGOZI WHERE ID_PROPRIETARIO = " + id;

			Connection conn = DriverManager.getConnection(DB_URL); 
		
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		 
			//chiusura connessione 
			conn.close();
			System.out.println("utente eliminato");
			
		}catch (SQLException e) {
		  System.out.println(e.getMessage());
		}
	}
	
	//-----------------------------------------------------------------------------------------
	//------------------------------------STAMPA DATABASE--------------------------------------
	//-----------------------------------------------------------------------------------------
		public void print() {

			try {			
				 Connection conn = DriverManager.getConnection(DB_URL);
				 System.out.println("Connessione stabilita");
				 
				 //query
				 ResultSet res;
				 Statement s=conn.createStatement();
				 res=s.executeQuery("SELECT * FROM NEGOZI");
				 System.out.println("Negozi presenti nel DB: ");
				 while (res.next()) {
					    for (int i = 1; i <= 2; i++) {
					        System.out.print(res.getString(i)+ " ");
					    }
					    System.out.println();
					}
				 
				 //chiusura connessione 
				 conn.close();
				} catch (SQLException e) {
				  System.out.println(e.getMessage());
				}
		} 
	
	
	
}
