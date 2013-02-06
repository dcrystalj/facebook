package code.Servlet;

import code.Helper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import code.User;
import code.UserValidate;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet(urlPatterns={"/EditProfileServlet", "/editprofile" })
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public final static Pattern emailValidate = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
	public final static Pattern nameValidate = Pattern.compile("[A-Z][a-z]+( [A-Z][a-z]+)?",Pattern.CASE_INSENSITIVE);   
	public static ArrayList<String> napake;
	
	@Inject 
	private Helper helperBean; 
	/**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String izvoz=req.getParameter("izvoz");
		if(izvoz.equals("0")){
			res.setContentType("text/html");
			req.getRequestDispatcher("index.jsp?page=printUser").include(req, res);
		}
		else if(izvoz.equals("1")){
			res.setContentType("application/vnd.ms-excel");
			res.setHeader("Content-disposition", "attachment; filename=\"user.xls");
			req.getRequestDispatcher("printUser.jsp").include(req, res);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		napake=new ArrayList<String>();
		//Popravimo na "" èe dobimo null parameter.
		String ime= (req.getParameter("name")!=null)?req.getParameter("name"):"";
		String priimek= (req.getParameter("lastname")!=null)?req.getParameter("lastname"):"";
		String mail= (req.getParameter("email")!=null)?req.getParameter("email"):"";
		String spol=(req.getParameter("spol")!=null)?req.getParameter("spol"):"x";
		String naslov=(req.getParameter("naslov")!=null)?req.getParameter("naslov"):"";
		String letnik=(req.getParameter("letnik")!=null)?req.getParameter("letnik"):"";
		String geslo=(req.getParameter("geslo")!=null)?req.getParameter("geslo"):"";
		String gesloNovo=req.getParameter("gesloNovo");
		String stil=req.getParameter("radio"); //Kateri stil
		String smer=(req.getParameter("smer")!=null)?req.getParameter("smer"):"";
		String stopnja=(req.getParameter("stopnja")!=null)?req.getParameter("stopnja"):"";
		String vrsta=(req.getParameter("vrsta")!=null)?req.getParameter("vrsta"):"";
		String letnikDip=(req.getParameter("letnikDip")!=null)?req.getParameter("letnikDip"):"";
		String upIme=(req.getParameter("username")!=null)?req.getParameter("username"):"";

		
		int letRoj=Integer.parseInt(letnik);
		int letDip=Integer.parseInt(letnikDip);
		User user=new User(ime, priimek, geslo, letRoj, letDip, naslov, mail, smer, spol, stopnja, vrsta, upIme, vrsta);
		napake=UserValidate.validate(user);
		
		
		HttpSession s = req.getSession(true);
		User usr= (User)s.getAttribute("uporabnik");
		user.setId(usr.getId());
		if(!helperBean.validirajUporabnika(usr)){
			napake.add("Ne pripada vam seja");
		}
		geslo=helperBean.kodirajGeslo(geslo, usr.getSol());
		if(!geslo.equals(usr.getGeslo()))
			napake.add("Vase staro geslo se ne ujema");
		else{
			//preverjanje novega gesla
			if(!geslo.equals("")){
				//if(up.getGeslo().equals(helperBean.kodirajGeslo(geslo, helperBean.generirajSalt())))
					if(gesloNovo.length()<5){
						napake.add("Novo geslo mora biti vsaj 5 znakov dolg");
					}
					else {
						String sol=helperBean.generirajSalt();
						user.setSol(sol);
						gesloNovo=helperBean.kodirajGeslo(gesloNovo, sol);
					}
			}
		}
		//Cookie na stil prestavimo
		Cookie piskot=new Cookie("style", stil);
		piskot.setMaxAge(60*60*24*365);
		res.addCookie(piskot);
		//Sejo prenastavimo
		s.setAttribute("uporabnik", user);
		
		//Èe ni napak lahko uporabnika shranimo
		if(napake.size()==0){
			user.setGeslo(gesloNovo);
			helperBean.posodobiUporabnika(user);
			
		}
		//V vsakem primeru pa ga preusmerimo nazaj na stran za urejanje profila
		req.getRequestDispatcher("index.jsp?page=editprofile").forward(req, res);
		
	}
	

}
