package code.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import code.Prijateljstvo;
import code.User;
import code.beanEJB.view.UpravljalecPrijateljstevBeanLocal;

/**
 * Servlet implementation class ShowFriendsRequestServlet
 */
@WebServlet(urlPatterns={"/ShowFriendsRequest","/ShowFriendsRequestServlet"})
public class ShowFriendsRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	@EJB
	private UpravljalecPrijateljstevBeanLocal upb;
	
    public ShowFriendsRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<User> friends = new ArrayList<User>();
		User me = (User)req.getSession().getAttribute("uporabnik");
		List<Prijateljstvo> toConfirm=upb.vrniOdprteProsnjeZaPrijateljstvo(me.getId());
		for(Prijateljstvo p : toConfirm){
			friends.add(p.getIdUporabnikaPobudnik());
		}
		req.setAttribute("prosnjePrijatelji", friends);
		req.getRequestDispatcher("index.jsp?page=friendsRequests").forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
