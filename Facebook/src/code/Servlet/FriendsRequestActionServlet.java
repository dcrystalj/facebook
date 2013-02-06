package code.Servlet;

import java.io.IOException;
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
 * Servlet implementation class FriendsRequestActionServlet
 */
@WebServlet(urlPatterns={"/FriendsRequestActionServlet","/FriendsRequestAction"})
public class FriendsRequestActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private UpravljalecPrijateljstevBeanLocal upb;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FriendsRequestActionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//Prejmemo akcijo in id prijateljstva
		int statusRequest=Integer.parseInt((String)req.getParameter("action"));
		int idFriend=Integer.parseInt((String)req.getParameter("id"));
		User me = (User)req.getSession().getAttribute("uporabnik");
		Prijateljstvo p = upb.vrniPrijateljstvo(me.getId(), idFriend);
		upb.spremeniStatusPrijateljstvu(p.getId(), statusRequest);
		//Pogledamo še za prošnje prijateljstev uporabnika
		List<Prijateljstvo> pr = upb.vrniOdprteProsnjeZaPrijateljstvo(me.getId());
		int stProsenj=(pr==null)?0:pr.size();
		//Število prošenj shranimo v sejo
		req.getSession().setAttribute("stProsenj", stProsenj);
		req.getRequestDispatcher("index.jsp").forward(req, res);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
