package code.beanEJB.view;
import javax.ejb.Local;
import code.Obvestilo;


@Local
public interface PosiljanjeObvestilBeanLocal { 
	public void razposljiObvestilo(Obvestilo o);
	public void posljiObvestilo(String email, String naziv, String vsebina);
}
