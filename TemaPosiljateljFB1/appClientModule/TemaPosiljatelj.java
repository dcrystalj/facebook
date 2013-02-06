import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import javax.jms.Session;
import javax.naming.InitialContext;

public class TemaPosiljatelj {
	private ConnectionFactory cf;
	private Topic tema;
	
	public void pridobiVire() {
		try {
			InitialContext ic = new InitialContext();
			cf = (ConnectionFactory)ic.lookup("jms/FacebookCF");
			tema = (Topic)ic.lookup("jms/NotificationsTopic");
		} catch (Exception e) {
			System.out.println("Napaka-TemaPosiljatelj: " + e);
		}
	}
	
	public void vstaviSporocilo(Sporocilo msg) {
		pridobiVire();
		Connection povezava = null;
		
		try {
			povezava = cf.createConnection();
			Session seja = povezava.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageProducer posiljatelj = seja.createProducer(tema);

			ObjectMessage objectMsg = seja.createObjectMessage();
			objectMsg.setObject(msg);
	
			posiljatelj.send(objectMsg);
			
			System.out.println("Pošiljanje "+msg.getId()+": "+msg.getVsebina());
		} catch(Exception e) {
			System.out.println("Napaka-posljiSporocilo: " + e);
		} finally {
			try {
				if (povezava != null) { povezava.close(); }
			} catch(Exception e) { 
				System.out.println("Napaka-poslji: " + e);
			}
		}
	}
}
