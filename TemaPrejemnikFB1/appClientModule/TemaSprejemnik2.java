
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;

public class TemaSprejemnik2 implements MessageListener {
	private ConnectionFactory tovarnaPovezav;
	private Topic tema;
	
	public void pridobiVire(){
		try{
			InitialContext context = new InitialContext();
			tovarnaPovezav = (ConnectionFactory) context.lookup("jms/FacebookCF");
			tema = (Topic)context.lookup("jms/NotificationsTopic");
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	public void prejemajSporocila(){
		pridobiVire();

		Connection con=null;
		try {
			con = tovarnaPovezav.createConnection();
			Session seja = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageConsumer prejemnik = seja.createConsumer(tema);
			prejemnik.setMessageListener(this);
			con.start();
			System.out.println("zacetek prejemanja");
			
			
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onMessage(Message message) {
		try {
			ObjectMessage om = (ObjectMessage)message;
			Sporocilo o = (Sporocilo) om.getObject();
			System.out.println("ID sporocila: "+o.getId()+"\n Vsebina:"+o.getVsebina()+"\n --------");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public void onException(JMSException exception)
    {
       System.err.println("something bad happended: " + exception);
    }
}
