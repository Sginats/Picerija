/*/
 * ## ✅ TODO
- [ ]Izveidot galveno **Picerija** klasi (nodrošina Picas izveidi, saglabāšanu un darbību izsaukšanu)
- [ ]Izveidot Pica klasi ar atribūtiem (lielums, piedevas, mērce, cena, adrese, tālrunis, vārds)
- [ ]Realizēt picas izveidi (izmērs, piedevas, mērce)
- [ ]Ieviest piegādes informācijas ievadi (klienta vārds, adrese, tālrunis)
- [ ]Nodrošināt ievades datu pārbaudi (teksts, skaitļi, tukšas vērtības)
- [ ]Ieviest pasūtījumu labošanas funkcionalitāti
- [ ]Aprēķināt pasūtījuma beigu summu
- [ ]Izveidot DarbsArFailu klasi pasūtījumu saglabāšanai un nolasīšanai
- [ ]Nodrošināt aktīvo un nodoto pasūtījumu apskati
- [ ]Izveidot lietotāja saskarni ar JFrame
- [ ]Apstrādāt kļūdas un nepareizas lietotāja darbības
 */


import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Picerija {
	static ArrayList<Pica> pasutijumi = new ArrayList<>();
	public static void main(String[] args) {

		pasutijumi = DarbsArFailu.nolasit();

		String izvelne;
		String[] darbibas = {
		"Picas izveide",
		"Piegādes informācijas izveide",
		"Piegādes informācijas labošana",
		"Pasūtījuma summas aprēķināšana",
		"Pasūtījumu apskate",
		"Apturēt"
		};
		
		do {
			izvelne = (String) JOptionPane.showInputDialog(
                null,
                "Izvēlies darbību",
                "Darbību saraksts",
                JOptionPane.QUESTION_MESSAGE,
                null,
                darbibas,
                darbibas[0]
            );
			if (izvelne == null)
            izvelne = "Apturēt";
			switch (izvelne) {
			    case "Picas izveide":
			    
			        break;
			    case "Piegādes informācijas izveide":

                    break;
                 case "Piegādes informācijas labošana":
                	                         
                        break;
                  case "Pasūtījuma summas aprēķināšana":
                	  
                	  break;
                  case "Pasūtījumu apskate":
                	  
                	  break;
                  case "Apturēt":
                	  JOptionPane.showMessageDialog(
                          null,
                          "Picerija tiek slēgta!",
                          "Paziņojums",
                          JOptionPane.INFORMATION_MESSAGE
                      );
                	  break;
			}
		
		
	}while (!izvelne.equals("Apturēt") );
	}
}
