package code.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import code.Helper;
import code.User;
import code.UserValidate;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet(urlPatterns={"/RegistrationServlet","/registration"})
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static ArrayList<String> napake;
	@Inject 
	private Helper helperBean; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		napake=new ArrayList<String>();
		String ime= (req.getParameter("name")!=null)?req.getParameter("name"):"";
		String priimek= (req.getParameter("lastname")!=null)?req.getParameter("lastname"):"";
		String mail= (req.getParameter("email")!=null)?req.getParameter("email"):"";
		String spol=(req.getParameter("spol")!=null)?req.getParameter("spol"):"x";
		String naslov=(req.getParameter("naslov")!=null)?req.getParameter("naslov"):"";
		String letnik=(req.getParameter("letnik")!=null)?req.getParameter("letnik"):"";
		String geslo=(req.getParameter("geslo")!=null)?req.getParameter("geslo"):"";
		String smer=(req.getParameter("smer")!=null)?req.getParameter("smer"):"";
		String stopnja=(req.getParameter("stopnja")!=null)?req.getParameter("stopnja"):"";
		String vrsta=(req.getParameter("vrsta")!=null)?req.getParameter("vrsta"):"";
		String letnikDip=(req.getParameter("letnikDip")!=null)?req.getParameter("letnikDip"):"";
		String gesloponovljeno=req.getParameter("gesloPonovljeno");
		String upIme=(req.getParameter("username")!=null)?req.getParameter("username"):"";
		//Preparsamo letnike v integerje
		int letRoj=Integer.parseInt((letnik.equals(""))?"-1":letnik);
		int letDip=Integer.parseInt((letnikDip.equals(""))?"-1":letnikDip);
		User user=new User(ime, priimek, geslo, letRoj, letDip, naslov,  mail, smer, spol, stopnja, smer, upIme, vrsta);
		
		napake=UserValidate.validate(user);
		
		HttpSession s = req.getSession();

		if(!geslo.equals("")){
			if(!gesloponovljeno.equals(geslo)){
				napake.add("Novo geslo mora biti vsaj 5 znakov dolg");
			}
			else{ //Geslo še zakodiramo s soljo
				String sol=helperBean.generirajSalt();
				user.setSol(sol);
				geslo=helperBean.kodirajGeslo(geslo, sol);
				user.setGeslo(geslo);
			}
		}
		
		if(napake.size()==0){
			s.setAttribute("uporabnik", user);
			helperBean.registracijaUporabnika(user);
//			printMessage(res, "<a href='index.jsp'><font color='green'>Registracija novega uporabnika je uspela.</font><br />" +
//					"<h4>Klikni za preusmeritev</h4>.</a>");
			req.getRequestDispatcher("index.jsp").forward(req, res);
			return;
		}
		//Spremenimo še sejo
		s.setAttribute("uporabnik", user);
		//TODO: LoginHandlerServlet.al.add(user);//dodamo userja v "bazo"
//		res.sendRedirect("registration.jsp");
		req.getRequestDispatcher("registration.jsp").forward(req, res);
	}
	
	public void printMessage(HttpServletResponse res, String s) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Pozdrav!</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>"+s+"</h1>");
		out.println("</body>");
		out.println("</html>");
	}

}
