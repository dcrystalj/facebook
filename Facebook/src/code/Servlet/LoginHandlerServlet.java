package code.Servlet;

import code.Helper;
import code.Prijateljstvo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import code.User;
import code.beanEJB.view.UpravljalecPrijateljstevBeanLocal;

/**
 * Servlet implementation class PrijavaHandlerServlet
 */

@WebServlet(urlPatterns={"/LoginHandlerServlet","/login"})
public class LoginHandlerServlet extends HttpServlet {

	@Inject 
	private Helper helperBean;
	@EJB
	private UpravljalecPrijateljstevBeanLocal upb;	   
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public static ArrayList<String> napake;

	public LoginHandlerServlet() {
		super();

		napake=new ArrayList<String>();
	}
	public static void ini(){
		new LoginHandlerServlet();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		napake=new ArrayList<String>();
		String upIme=req.getParameter("upIme");
		String geslo=req.getParameter("geslo");
		HttpSession session = req.getSession(true);

		//Validacija
		if(upIme.length()<5){ //Geslo potrebuje vsaj 5 znakov
			napake.add("Napacno uporabnisko ime, vsaj 5 zankov");
		}
		else if(geslo.length()<5){ //Geslo potrebuje vsaj 5 znakov
			napake.add("Napacno geslo, vsaj 5 znakov");
		}
		else{
			User up = helperBean.avtenticirajUporabnika(upIme, geslo); 

			if(up == null){
				napake.add("Napacno uporabnisko ime ali geslo");
			}
			else{ //Shranimo ga v sejo
				session.setAttribute("uporabnik", up);
				//Pogledamo še za prošnje prijateljstev uporabnika
				List<Prijateljstvo> p = upb.vrniOdprteProsnjeZaPrijateljstvo(up.getId());
				int stProsenj=(p==null)?0:p.size();
				//Število prošenj shranimo v sejo
				session.setAttribute("stProsenj", stProsenj);
				//Uporabnika preusmerimo na glavno stran
				req.getRequestDispatcher("index.jsp").include(req, res);
				return;
			}
		}
		req.getRequestDispatcher("login.jsp").include(req, res);

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
