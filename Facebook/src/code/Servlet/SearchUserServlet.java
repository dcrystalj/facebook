package code.Servlet;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import code.Helper;
import code.User;

/**
 * Servlet implementation class SearchUserServlet
 */
@WebServlet({ "/SearchUserServlet", "/searchUser" })
public class SearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject 
	private Helper helperBean;    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String name=req.getParameter("name");
		String sname=req.getParameter("sname");
		List<User> usrs=helperBean.searchUsers(name, sname);
		req.setAttribute("result", usrs);
		req.getRequestDispatcher("index.jsp?page=search").forward(req, res);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
