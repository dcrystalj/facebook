package code.Servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tools.ant.types.CommandlineJava.SysProperties;

import code.DAO.NoviceDAOImpl;
import code.User;
import code.Helper;
/**
 * Servlet implementation class EditNews
 */
@WebServlet(urlPatterns={"/EditNews", "/editnews" })
public class EditNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Inject 
	private Helper helperBean;    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditNews() {
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
	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		String content = req.getParameter("content");
		int id = Integer.parseInt(req.getParameter("id").trim());

		HttpSession s = req.getSession(true);
		User usr= (User)s.getAttribute("uporabnik");
		if(helperBean.validirajUporabnika(usr)){
			try{
				code.DAO.NoviceDAOImpl d = new code.DAO.NoviceDAOImpl();
				code.Novice n = (code.Novice)d.vrni(id); //Pridobivanje novice
				n.setVsebina(content);
				d.posodobi(n);	//posodabljanje novice z novo vsebino
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		response.sendRedirect("ShowNewsInitServlet");
		
	}

}
