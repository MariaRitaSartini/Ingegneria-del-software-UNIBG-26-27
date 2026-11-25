package logic;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Categoria;
import model.Cliente;
import model.Commerciante;
//ref. Utente Service
//Si attiva quando un utente effettua una richiesta di registrazione, svolge 
//la creazione di un nuovo oggetto di tipo Utente e passa gli attributi al db
//per aggiunta di una riga.

//creazione con SINGLETON

public class UtenteLogic {
	//questi sono gli attributi che dovranno essere assegnati all'oggetto utente, la 
	//struttura potrebbe cambiare in seguito all'impelmentazione della GUI e al collegamento 
	//di essa con la logica. 
	private int id=0;
	//parametri del negozio
	public String nomeNegozio;
	public Categoria cat;
	public String emailNegozio;
	public String telNegozio;
			

	public Cliente c1;
	public Commerciante c2;
	public NegozioLogic nl;
	
	// Unica istanza della classe
    private static UtenteLogic instance;
    
  //INDIRIZZO DB
  	String DB_REL_FILE = "database.db3";
  	String DB_URL = "jdbc:sqlite:" + DB_REL_FILE;

    // Costruttore privato
    private UtenteLogic() {
    	String query = "SELECT MAX(ID) FROM UTENTI";

	    try (Connection conn = DriverManager.getConnection(DB_URL);
	         Statement st = conn.createStatement();
	         ResultSet rs = st.executeQuery(query)) {

	        if (rs.next()) {
	            this.id=rs.getInt(1);
	            System.out.println("IL MASSIMO ID UTENTE è: "+ this.id);
	        }else {
	        	this.id=0;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
    	nl=NegozioLogic.getInstance();
    }

    // Metodo per ottenere l'istanza
    public static UtenteLogic getInstance() {

        if (instance == null) {
            instance = new UtenteLogic();
        }

        return instance;
    }
	
	
	
	//-----------------------------------------------------------------------------------------
	//------------------------METODO DI CREAZIONE DEL CLIENTE----------------------------------
	//-----------------------------------------------------------------------------------------
	public void createCliente(String name, String surname, String email, String address, String telefono, String username, String pw) {
		//creazione dell'utente e impostazione dei suoi campi di username e pw
		c1=new Cliente(name, surname, email, address, telefono, id);
		c1.setUsername(username);
		c1.setPw(pw);
		
		//AGGIUNTA NEL DATABASE
		try {
				Connection conn = DriverManager.getConnection(DB_URL); 
				
				//query
				String query= "INSERT INTO UTENTI "+"(NOME, COGNOME, EMAIL, DOMICILIO, TELEFONO, USERNAME, PASSWORD, ID) " +
			            "VALUES ('" +
			            name + "', '" +
			            surname + "', '" +
			            email + "', '" +
			            address + "', '" +
			            telefono + "', '" +
			            username + "', '" +
			            pw + "', '" +
			            id + "')";
				Statement st = conn.createStatement();
				st.executeUpdate(query);
				 
				//chiusura connessione 
				conn.close();
				System.out.println("Il tuo ID è: = "+ c1.id);
				} catch (SQLException e) {
				  System.out.println(e.getMessage());
				}
		id++;
	}
	
	
	
	//-----------------------------------------------------------------------------------------
	//------------------------METODO DI CREAZIONE DEL COMMERCIANTE-----------------------------
	//-----------------------------------------------------------------------------------------
	public void createCommerciante(String name, String surname, String email, String address, String telefono, String username, String pw) {
		//creazione del commerciante e impostazione dei suoi campi
		c2=new Commerciante(name, surname, email, address, telefono, id);
		
		//AGGIUNTA NEL DATABASE
				try {
						Connection conn = DriverManager.getConnection(DB_URL); 
						
						//query
						String query= "INSERT INTO UTENTI "+"(NOME, COGNOME, EMAIL, DOMICILIO, TELEFONO, USERNAME, PASSWORD, ID) " +
					            "VALUES ('" +
					            name + "', '" +
					            surname + "', '" +
					            email + "', '" +
					            address + "', '" +
					            telefono + "', '" +
					            username + "', '" +
					            pw + "', '" +
					            id + "')";
						Statement st = conn.createStatement();
						st.executeUpdate(query);
						
						System.out.println("Il tuo ID è: "+c2.id);
						
						//chiusura connessione 
						conn.close();
						} catch (SQLException e) {
						  System.out.println(e.getMessage());
						}
				
		c2.myNegozio= nl.createNegozio(c2, nomeNegozio, cat, emailNegozio, telNegozio);
		id++;
		
	}
	
	//-----------------------------------------------------------------------------------------
	//------------------------METODO DI ELIMINAZIONE DELL'UTENTE-------------------------------
	//-----------------------------------------------------------------------------------------
		public void eliminaUtente(int idUtente) {
			
			try {
				String query = "DELETE FROM UTENTI WHERE ID = " + idUtente;

				Connection conn = DriverManager.getConnection(DB_URL); 
			
				Statement st = conn.createStatement();
				st.executeUpdate(query);
				
				nl.eliminaProprietario(idUtente);
				
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
			 res=s.executeQuery("SELECT * FROM UTENTI");
			 System.out.println("Utenti presenti nel DB: ");
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
	
	//-----------------------------------------------------------------------------------------
	//------------------------------------STAMPA NEGOZI----------------------------------------
	//-----------------------------------------------------------------------------------------
		public void printNegozi() {
			nl.print();
		} 

}
