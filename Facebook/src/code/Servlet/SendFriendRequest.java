package code.Servlet;


import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import code.User;
import code.beanEJB.view.UpravljalecPrijateljstevBeanLocal;
/**
 * Servlet implementation class SendFriendRequest
 */
@WebServlet("/SendFriendRequest")
public class SendFriendRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	@EJB
	private UpravljalecPrijateljstevBeanLocal upb;
	
    public SendFriendRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int idPrejemnik=Integer.parseInt(req.getParameter("id"));	//Uporabnik kateremu pošiljamo prošnjo pa je shranjen v querystringu
		User u = (User)req.getSession().getAttribute("uporabnik"); 	//Trenutni uporabnik je shranjen v sejo
		int idPobudnik=u.getId();
		upb.posljiProsnjoZaPrijateljstvo(idPobudnik, idPrejemnik);
		req.getRequestDispatcher("index.jsp?page=search").forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
