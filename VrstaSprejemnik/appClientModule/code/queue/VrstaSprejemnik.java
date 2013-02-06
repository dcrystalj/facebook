package code.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.InitialContext;

public class VrstaSprejemnik {
	private ConnectionFactory tovarnaPovezav;
	private Destination vrsta;
	
	public void pridobiVire(){
		try{
			InitialContext context = new InitialContext();
			tovarnaPovezav = (ConnectionFactory) context.lookup("jms/FacebookCF");
			vrsta = (Queue)context.lookup("jms/NotificationsQueue");
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	public void prejemajSporocila(){
		pridobiVire();

		Connection con=null;
		try {
			con = tovarnaPovezav.createConnection();
			//Kreiramo sejo
			Session seja = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			//Kreiramo iz seje prejemnika, in ga ve�emo na na�o vrsto

			MessageConsumer prejemnik = seja.createConsumer(vrsta);
			//Neskon�na zanka kjer gledamo vrsto, �e smo dobili sporo�ilo
			con.start();
			while(true){
				//Spodnja metoda �aka, dokler ne prejmemo sporo�ila, sinhron na�in
				ObjectMessage msg = (ObjectMessage)prejemnik.receive();
				Sporocilo sporocilo = (Sporocilo) msg.getObject();
				//Prejeto sporocilo izpisemo v konzolo
				System.out.println("ID sporocila: "+sporocilo.getId()+"\n Vsebina:"+sporocilo.getVsebina()+"\n --------");
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
