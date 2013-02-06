package code.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;

public class VrstaPosiljatelj {
	private ConnectionFactory tovarnaPovezav;
	private Destination vrsta;
	public int stSporocil=10;
	
	public void pridobiVire(){
		try{
			InitialContext context = new InitialContext();
			tovarnaPovezav = (ConnectionFactory) context.lookup("jms/FacebookCF");
			vrsta = (Queue)context.lookup("jms/NotificationsQueue");
		}catch(Exception e){
			e.printStackTrace();
		}
			
	}	
	public void vstaviSporocila(){
		pridobiVire();
		Connection povezava=null;
		try	{
			povezava  = tovarnaPovezav.createConnection();
			//Ustvarimo samo potrjevalno sejo
			Session seja = povezava.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			MessageProducer posiljatelj = seja.createProducer(vrsta);
			//Posljemo toliko sporocil, kot jih potrebujemo poslati, privzeto 10
			for (int i = 0; i < stSporocil; i++) {
				Sporocilo o = new Sporocilo(i+1, "Sporocilo stevilka "+(i+1));
				ObjectMessage sporocilo = seja.createObjectMessage();   //textovna za stringe, objetna za objekte(serializacija), binarna, xml sporoèila
				sporocilo.setObject(o);
				System.out.println("send meessage");
				posiljatelj.send(sporocilo);	
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}	
		finally{
			try {
				povezava.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
