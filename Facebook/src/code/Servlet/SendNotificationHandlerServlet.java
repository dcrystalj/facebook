package code.Servlet;

import java.io.IOException;
import java.sql.Date;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import code.Helper;
import code.Obvestilo;
import code.User;
import code.beanEJB.UpravljalecObvestilBean;
import code.beanEJB.view.UpravljalecObvestilBeanLocal;

import javax.ejb.*;
/**
 * Servlet implementation class SendNotificationHandlerServlet
 */
@WebServlet(urlPatterns={"/SendNotificationHandlerServlet","/sendnotification"})
public class SendNotificationHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject 
	private Helper helperBean;  
	
    @EJB
    UpravljalecObvestilBeanLocal uob;
	
    public static String status;
    
    public SendNotificationHandlerServlet() {
        super();
               
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String content = req.getParameter("content");
		String name = req.getParameter("name");
		
		HttpSession s = req.getSession(true);
		User usr= (User)s.getAttribute("uporabnik");
		
		if(helperBean.validirajUporabnika(usr) && usr.getTipUporabnika()>=2){ //user has rights
			try{
				Obvestilo o = new Obvestilo();
				o.setNaziv(name);
				o.setVsebina(content);
				o.setIdAvtorja(usr);
				o.setDatum(new Date(new java.util.Date().getTime()));
				
				uob.vstaviObvestiloVVrsto(o);
				status = "Uspesno poslano";
			}
			catch (Exception e) {
				status = "Neuspesno poslano";
			}
		}
		req.getRequestDispatcher("index.jsp?page=insertNotification").include(req, res);
		

	}

}
