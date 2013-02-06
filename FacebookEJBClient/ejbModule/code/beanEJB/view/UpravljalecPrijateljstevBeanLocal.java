package code.beanEJB.view;
import java.util.List;
import javax.ejb.Local;
import code.Prijateljstvo;
import code.User;


@Local
public interface UpravljalecPrijateljstevBeanLocal {     
	public void posljiProsnjoZaPrijateljstvo(int idUpPobudnik, int idUpPrejemnik);
	public List<Prijateljstvo> vrniOdprteProsnjeZaPrijateljstvo(int idUporabnika);
	public List<User> vrniVsePrijatelje(int idUporabnika);
	public void spremeniStatusPrijateljstvu(int idPrijateljstva, int noviStatus);
	public Prijateljstvo vrniPrijateljstvo(int idUpPrejemnik, int idUpPobudnik);

}