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
 * Servlet implementation class ShowConfirmedFriends
 */
@WebServlet("/ShowConfirmedFriends")
public class ShowConfirmedFriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@EJB
	UpravljalecPrijateljstevBeanLocal upb;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowConfirmedFriendsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		User u = (User)req.getSession().getAttribute("uporabnik");
		System.err.println(u.getId());
		req.setAttribute("friends", upb.vrniVsePrijatelje(u.getId()));
		req.getRequestDispatcher("index.jsp?page=showFriends").forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
