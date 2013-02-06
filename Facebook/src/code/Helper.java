package code;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import javax.inject.Named;
import javax.persistence.PersistenceUnit;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import com.ibm.ws.sib.queue.migration.logger.Logger;

import java.util.Formatter;


@Named 
@RequestScoped 
public class Helper { 

	@PersistenceUnit( unitName="Vaja4JPA" ) 
	private EntityManagerFactory emf; 

	@Resource 
	private UserTransaction ut; 

	public User avtenticirajUporabnika(String upIme, String geslo) {

		User up = null; 
		EntityManager em = emf.createEntityManager(); 
		try { 
			//Query query = em.createNamedQuery("getUserkByupIme");
			//query.setParameter(("upIme"), upIme);
			//up = (User)query.getSingleResult();
			Query query = em.createQuery("SELECT u.sol FROM User u where u.upIme = :upIme");
			query.setParameter("upIme",upIme);
			String sol = (String)query.getSingleResult();
			geslo=kodirajGeslo(geslo,sol);
			
			query = em.createQuery("SELECT u FROM User u where u.upIme = :upIme and u.geslo = :geslo");
			query.setParameter("upIme",upIme);
			query.setParameter("geslo", geslo);//Helper.kodirajGeslo(geslo, up.getSol()));
			up = (User)query.getSingleResult();

		} 
		catch (Exception e) { 
			Logger.log("Slaba prijava uporabnika",e.getCause());
		} 
		finally { 
			em.close(); 
		} 

		return up; 
	} 
	public List<User> searchUsers(String ime, String priimek) {

		List<User> up = null; 
		EntityManager em = emf.createEntityManager(); 
		try { 
			Query query = em.createQuery("SELECT u FROM User u WHERE u.ime LIKE '%"+ime+"%' AND u.priimek LIKE '%"+priimek+"%'");
			up = (List<User>)query.getResultList();

		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
		finally { 
			em.close(); 
		} 

		return up; 
	} 
	
	public boolean validirajUporabnika(User us) {

		EntityManager em = emf.createEntityManager(); 
		try { 
			Query query = em.createQuery("SELECT u FROM User u where u.upIme = :upIme and u.geslo = :geslo");
			query.setParameter("upIme",us.getUpIme());
			query.setParameter("geslo", us.getGeslo());//Helper.kodirajGeslo(geslo, up.getSol()));
			if((User)query.getSingleResult()!=null)
				return true;
			else
				return false;
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		} 
		finally { 
			em.close(); 
		} 

		return false; 
	} 

	public static String kodirajGeslo(String password, String salt) {
         return DigestUtils.sha512Hex(salt+password);
    }
    
	public static String generirajSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        return Hex.encodeHexString(salt);
    }
    
	public void posodobiUporabnika(User up) { 

		EntityManager em = null; 

		try { 
			
			ut.begin(); 
			em = emf.createEntityManager(); 
			em.merge(up);

			ut.commit(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
			try { 
				ut.rollback(); 
			} catch (Exception e1) { 
				e1.printStackTrace(); 
			} 
		} 
		finally { 
			em.close(); 
		} 
	} 
	public void registracijaUporabnika(User up) { 

		EntityManager em = null; 

		try { 
			ut.begin(); 
			em = emf.createEntityManager(); 

			User usr=new User();
			usr=up;
			em.persist(usr);
			
			ut.commit(); 
		} catch (Exception e) { 
			e.printStackTrace(); 
			try { 
				ut.rollback(); 
			} catch (Exception e1) { 
				e1.printStackTrace(); 
			} 
		} 
		finally { 
			em.close(); 
		} 
	} 
}