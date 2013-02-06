package code.beanEJB;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.ibm.ws.http.Logger;

import code.Prijateljstvo;
import code.User;
import code.beanEJB.view.UpravljalecPrijateljstevBeanLocal;

/**
 * Session Bean implementation class UpravljalecPrijateljstevBean
 */
@Stateless
@Local(UpravljalecPrijateljstevBeanLocal.class)
@LocalBean
//@TransactionManagement(TransactionManagementType.BEAN)
public class UpravljalecPrijateljstevBean implements UpravljalecPrijateljstevBeanLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
	
    public UpravljalecPrijateljstevBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void posljiProsnjoZaPrijateljstvo(int idUpPobudnik, int idUpPrejemnik) {
		//TODO èe je že naredi niz napak in vrni
		User UsrPobud=em.find(User.class, idUpPobudnik);
		User UsrPrej=em.find(User.class, idUpPrejemnik);
		boolean friends=false;
		//Èe imamo razlièna uporabnika, èe ne je brez veze pošiljati prošnjo za prjateljstvo
		if(idUpPobudnik!=idUpPrejemnik){
			try {
				Query q = em.createQuery("SELECT p FROM Prijateljstvo p " +
						"WHERE (p.idUporabnikaPobudnik = :pobudnik AND p.idUporabnikaPrejemnik = :prejemnik)" +
						"OR (p.idUporabnikaPobudnik = :prejemnik AND p.idUporabnikaPrejemnik = :pobudnik)");
				q.setParameter("pobudnik",UsrPobud);
				q.setParameter("prejemnik",UsrPrej);
				//Ce obstaja povabilo, ga ne mores povabiti
				if(q.getResultList().size()>0){
					friends = true;
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(!friends){
			//Nastavimo novo prijateljstvo, ker smo ugotovili, da še nista prijatelja
			Prijateljstvo prij = new Prijateljstvo();
			prij.setIdUporabnikaPobudnik(UsrPobud);
			prij.setIdUporabnikaPrejemnik(UsrPrej);
			prij.setStatus(0);
			em.persist(prij); //Shranimo v bazo
		}
		
		
	}

	@Override
	public List<Prijateljstvo> vrniOdprteProsnjeZaPrijateljstvo(int idUporabnika) {
//		Poišèemo danega uporabnika
		User usr = em.find(User.class, idUporabnika);
		//Kreiramo list prijateljstev
		List<Prijateljstvo> listPrij = new ArrayList<Prijateljstvo>();
		
		for (Prijateljstvo p : usr.getPrijateljstvoPrejemnikList()){
			//Dodamo vsa prijateljstva, kjer nas èaka prošnja
			if(p.getStatus()==0)
				listPrij.add(p);
		}
		return listPrij;
	}

	@Override
	public List<User> vrniVsePrijatelje(int idUporabnika) {
		User u = em.find(User.class, idUporabnika);
		List<User> lu=new ArrayList<User>();
		for(Prijateljstvo p: u.getPrijateljstvoPobudnikList()){
			if(p.getStatus()==1)
				lu.add(p.getIdUporabnikaPrejemnik()); 	//Prijateljstvo kjer sem jaz pobudnik
		}
		for(Prijateljstvo p: u.getPrijateljstvoPrejemnikList()){
			if(p.getStatus()==1)
				lu.add(p.getIdUporabnikaPobudnik()); 	//Prijateljstvo kjer sem jaz prejemnik
		}
		return lu;
	}

	@Override
	public void spremeniStatusPrijateljstvu(int idPrijateljstva, int noviStatus) {
		Prijateljstvo p = em.find(Prijateljstvo.class, idPrijateljstva);
		p.setStatus(noviStatus);
		em.persist(p);
		
	}

	@Override
	public Prijateljstvo vrniPrijateljstvo(int idUpPrejemnik, int idUpPobudnik) {
		Prijateljstvo moje=null;
		try{
			User uPrej=em.find(User.class, idUpPrejemnik);
			User uPob=em.find(User.class, idUpPobudnik);
			Query q = em.createQuery("SELECT p FROM Prijateljstvo p " +
					"WHERE (p.idUporabnikaPobudnik = :pobudnik AND p.idUporabnikaPrejemnik = :prejemnik)");
			q.setParameter("pobudnik", uPob);
			q.setParameter("prejemnik", uPrej);
			moje=(Prijateljstvo)q.getSingleResult();	//Èe smo prav vnašali v bazo mora biti samo en tak rezultat
			
		}catch(Exception e){
			Logger.log(0, "Napaka pri pridobivanju prijateljstva"+e.getMessage());}
		return moje;
	}

}
