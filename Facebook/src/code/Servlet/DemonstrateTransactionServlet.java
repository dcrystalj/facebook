package code.Servlet;

import code.Entiteta;
import code.Novice;
import code.DAO.NoviceDAOImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemonstrateTransactionServlet
 */
@WebServlet({"/DemonstrateTransactionServlet","/dts"})
public class DemonstrateTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DemonstrateTransactionServlet() {	//demonstracija se izvaja kar v inicializaciji servleta, torej ko klicemo na link
        super();
        NoviceDAOImpl ndi=new NoviceDAOImpl();
        ndi.odstrani(1);
        java.sql.Date date= new java.sql.Date(new java.util.Date().getTime());
        List<Entiteta> list = new ArrayList<Entiteta>();
        Novice n=new Novice("Prva novica", -1, "Prva novica pri demonstraciji z id avtorja -1", date);
        list.add(n);
        n=new Novice("Druga novica", -2, "Druga novica pri demonstraciji z id avtorja -2", date);
        list.add(n);
        ndi.vstaviDEMO(list);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
