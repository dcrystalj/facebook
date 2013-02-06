package code.DAO;

import java.sql.Connection;
import java.util.List;

import code.Entiteta;

public interface BaseDAO {
	public Connection getConnection();
	public Entiteta vrni(int id);
	public void vstavi(Entiteta ent);
	public void odstrani(int id);
	public void posodobi(Entiteta ent);
	public List<Entiteta> vrniVse();
}
