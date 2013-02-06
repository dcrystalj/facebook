package code.beanEJB;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import code.Obvestilo;
import code.User;
import code.beanEJB.view.PosiljanjeObvestilBeanLocal;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class PosiljanjeObvestilBean
 */
@Stateless
@Local(PosiljanjeObvestilBeanLocal.class)
@LocalBean
public class PosiljanjeObvestilBean implements PosiljanjeObvestilBeanLocal {

	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private Session seja;
	
    public PosiljanjeObvestilBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void razposljiObvestilo(Obvestilo o) {
        em.persist(o);
	
        Query q = em.createQuery("SELECT u FROM User u");
        
        @SuppressWarnings("unchecked")
		List<User> users = (List<User>)q.getResultList();
        
        for(User u: users ) {
                posljiObvestilo(u.getEmail(), o.getNaziv(), o.getVsebina());
        }		
	}

	@Override
	public void posljiObvestilo(String email, String naziv, String vsebina) {
		try{

			Transport bus = seja.getTransport("smtp");
			bus.connect();
			Message msg = new MimeMessage(seja);
			String FROM_ADDRESS = "dcrystalj@gmail.com";
			
			msg.setFrom(new InternetAddress(FROM_ADDRESS));
			InternetAddress[] address = {new InternetAddress(email)};
			msg.setRecipients(Message.RecipientType.TO,address);
			
			msg.setSubject(naziv);
			msg.setSentDate(new Date());
			
			msg.setText(vsebina);
			msg.setContent(vsebina, "text/plain");
			
			msg.saveChanges();
			
			bus.sendMessage(msg, address);
			
			bus.close();

			System.out.println("Poslano obvestilo "+naziv +" "+email+" "+vsebina);
		}
		catch(MessagingException mex){
			mex.printStackTrace();

			System.out.println("Mail ni bil poslan na naslov: "+email+"; "+naziv +" "+vsebina);
		}

	}

}
