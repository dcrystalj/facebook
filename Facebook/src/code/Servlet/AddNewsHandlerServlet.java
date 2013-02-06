package code.Servlet;

import code.Helper;
import code.Novice;

import java.io.IOException;
import java.util.ArrayList;
import java.sql.Date;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import code.User;

/**
 * Servlet implementation class AddNewsHandlerServlet
 */
@WebServlet(urlPatterns={"/AddNewsHandlerServlet", "/insertnews" })
public class AddNewsHandlerServlet extends HttpServlet {
	
	public static ArrayList<String> napake;
	private static final long serialVersionUID = 1L;
	@Inject 
	private Helper helperBean;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNewsHandlerServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		// TODO Auto-generated method stub
		napake=new ArrayList<String>();
		String name = req.getParameter("name");
		String content = req.getParameter("content");
		//Validacija
		if(content.length()<4){
			napake.add("Ime novice je obvezno");
		}
		else if(content.length()<2){ 
			napake.add("Vsebina novice je obvezna");
		}
		else{
			//Avtentikacija
			HttpSession s = req.getSession(true);
			code.DAO.NoviceDAOImpl d = new code.DAO.NoviceDAOImpl();
			User usr= (User)s.getAttribute("uporabnik");

			if(helperBean.validirajUporabnika(usr)){
				java.util.Date today = new java.util.Date();
				Novice n = new Novice(name, usr.getId(), content, new java.sql.Date(today.getTime()));
				d.vstavi(n);
				res.sendRedirect("ShowNewsInitServlet");
//				req.getRequestDispatcher("shownews").forward(req, res);
				return;
			}
			else
				req.getRequestDispatcher("notloggedin.jsp").forward(req, res);
			
		}
//		ServletContext context = getServletContext();
//		RequestDispatcher dispatcher = context.getRequestDispatcher("shownews");
//		dispatcher.forward(req,res);
		res.sendRedirect("ShowNewsInitServlet");
	}

}
