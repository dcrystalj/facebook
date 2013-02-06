package code.beanEJB;

import javax.ejb.EJB;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import code.Obvestilo;
import code.beanEJB.view.PosiljanjeObvestilBeanLocal;


@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destinationType",
				propertyValue = "javax.jms.Queue"
		) }, 
		mappedName = "jms/NotificationsQueue")

@TransactionManagement(TransactionManagementType.BEAN)
public class NovoObvestiloMDB implements MessageListener {

    @EJB
    PosiljanjeObvestilBeanLocal sendNotification;
    
    public NovoObvestiloMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	ObjectMessage om = (ObjectMessage)message;
		try {
			Obvestilo o = (Obvestilo) om.getObject();
			sendNotification.razposljiObvestilo(o);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    } 

}
