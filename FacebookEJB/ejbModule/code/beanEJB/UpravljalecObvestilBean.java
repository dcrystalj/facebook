package code.beanEJB;

import code.Obvestilo;
import code.beanEJB.view.UpravljalecObvestilBeanLocal;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
/**
 * Session Bean implementation class UpravljalecObvestilBean
 */


@Stateless
@Local(UpravljalecObvestilBeanLocal.class)
@LocalBean
public class UpravljalecObvestilBean implements UpravljalecObvestilBeanLocal {

	@Resource
	private ConnectionFactory tovarnaPovezav;
	
	@Resource
	private Queue vrsta;
	
    public UpravljalecObvestilBean() {
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void vstaviObvestiloVVrsto(Obvestilo o) {
		Connection povezava = null; 
		
		try{
			povezava = tovarnaPovezav.createConnection();
			Session seja = povezava.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			MessageProducer posiljatelj = seja.createProducer(vrsta);
			
			ObjectMessage om = seja.createObjectMessage();
			om.setObject(o);
			
			posiljatelj.send(om);
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
