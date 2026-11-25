import java.util.Scanner;

import logic.UtenteLogic;
import model.Categoria;

public class App {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		UtenteLogic u = UtenteLogic.getInstance();
		int i=0, j=0, k=0;
		String name, surname, email, address, telefono, username, pw;
		while(true) {
			System.out.println("inserisci 1 per creare un nuovo profilo, 2 per eliminarne uno");
			i=new Scanner(System.in).nextInt();
			if(i==1) {
				System.out.println("inserisci 1 per creare un profilo utente, 2 per creare un profilo negozio");
				j=new Scanner(System.in).nextInt();
				
				System.out.println("inserisci nome");
				name = new Scanner(System.in).nextLine();
				
				System.out.println("inserisci cognome");
				surname = new Scanner(System.in).nextLine();
				
				System.out.println("inserisci email");
				email = new Scanner(System.in).nextLine();
				
				System.out.println("inserisci indirizzo");
				address = new Scanner(System.in).nextLine();
				
				System.out.println("inserisci telefono");
				telefono = new Scanner(System.in).nextLine();
				
				System.out.println("inserisci username");
				username = new Scanner(System.in).nextLine();
				
				System.out.println("inserisci password");
				pw = new Scanner(System.in).nextLine();
				if(j==1) {
					u.createCliente(name,surname,email,address,telefono,username,pw);
					
				}else if(j==2) {
					System.out.println("inserisci nome negozio");
					u.nomeNegozio = new Scanner(System.in).nextLine();
					
					System.out.println("SELEZIONA 1-ESTETICA, 2-ABBIGLIAMENTO, 3-FARMACEUTICA");
					k = new Scanner(System.in).nextInt();
					if(k==1) {
						u.cat=Categoria.ESTETICA;
					}else if(k==2) {
						u.cat=Categoria.ABBIGLIAMENTO;
					}else if(k==3) {
						u.cat=Categoria.FARMACEUTICA;
					}
					
					System.out.println("inserisci email negozio");
					u.emailNegozio = new Scanner(System.in).nextLine();
					
					System.out.println("inserisci telefono negozio");
					u.telNegozio = new Scanner(System.in).nextLine();
				
					u.createCommerciante(name,surname,email,address,telefono,username,pw);
					u.printNegozi();
				}
			}else if(i==2){
				System.out.println("inserisci id utente");
				int id = new Scanner(System.in).nextInt();
				u.eliminaUtente(id);
			}
			
			u.print();
		}
		
	}
}
