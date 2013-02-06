package code.beanEJB.view;
import javax.ejb.Local;
import code.Obvestilo;

@Local
public interface UpravljalecObvestilBeanLocal { 
	public void vstaviObvestiloVVrsto(Obvestilo o);
}