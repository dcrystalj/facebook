package code.Servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import code.Helper;
import code.User;

/**
 * Servlet implementation class DeleteNews
 */
@WebServlet(urlPatterns={"/DeleteNews","/deletenews"})
public class DeleteNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject 
	private Helper helperBean;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteNewsServlet() {
        super();
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
		int id = Integer.parseInt(req.getParameter("id").trim());
		HttpSession s = req.getSession(true);
		User usr= (User)s.getAttribute("uporabnik");
		if(helperBean.validirajUporabnika(usr)){
			code.DAO.NoviceDAOImpl d = new code.DAO.NoviceDAOImpl();
			d.odstrani(id);
		}

		res.sendRedirect("shownews?delete=1");
	}

}
