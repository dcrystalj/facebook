package code;
import code.Helper;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import code.Servlet.LoginHandlerServlet;

public class UserValidate {
	public final static Pattern emailValidate = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
	public final static Pattern nameValidate = Pattern.compile("[A-Z][a-z]+( [A-Z][a-z]+)?",Pattern.CASE_INSENSITIVE);  
	public final static Pattern yearValidate = Pattern.compile("^(19|20)\\d{2}$",Pattern.CASE_INSENSITIVE); 
    @Inject 
    private static Helper helperBean; 
   
	//Metoda, ki validira pravilno vnešene podatke uporabnika
	public static ArrayList<String>  validate(User usr){
		ArrayList<String> napake=new ArrayList<String>();

		
		String ime=usr.getIme();
		String priimek=usr.getPriimek();
		String mail=usr.getEmail();
		String spol=usr.getSpol();
		String naslov=usr.getNaslov();
		String letnikDip=usr.getLetnikDiplomiranja()+"";
		String geslo=usr.getGeslo();
		String smer=usr.getSmer();
		String studij=usr.getStopnja();
		String vrsta=usr.getVrsta();
		String usrname=usr.getUpIme();
		if(usrname.equals("") || !nameValidate.matcher(usrname).find()){
			napake.add("Napacen username");
		}
		else if(usrname.length()<5)
			napake.add("Uporabnisko ime mora imeti vsaj 5 znakov");
		if(ime.equals("") || !nameValidate.matcher(ime).find()){
			napake.add("Napacen username");
		}
		if(priimek.equals("") || !nameValidate.matcher(priimek).find()){
			napake.add("Slabo vnešen priimek.");
		}
		if(mail.equals("") || !emailValidate.matcher(mail).find()){
			napake.add("Nepravilna oblika e-mail naslova.");
		}
		if(spol.trim().equals("")){
			napake.add("Manjka vnos spola.");
		}
		if(naslov.length()<5){
			napake.add("Napacen vnos naslova.");
		}
		if(letnikDip.equals("")|| !yearValidate.matcher(letnikDip).find() || !(Integer.parseInt(letnikDip)>1900 && Integer.parseInt(letnikDip)<2100)){
			napake.add("Letnik Diplomiranja mora biti stevilka visja od 1900 in nižja od 2100.");	
		}
		if(geslo.length()<5)
			napake.add("Geslo mora biti dolgo vsaj 5 znakov.");
		if(vrsta.length()<1)
			napake.add("Izberite vrsto studija.");
		if(smer.length()<1)
			napake.add("Izberite smer studija.");
		if(studij.length()<1)
			napake.add("Izberite studijsko smer.");
	
		return napake;
	}

}
