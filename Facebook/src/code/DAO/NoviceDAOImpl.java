package code.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import code.Entiteta;
import code.Novice;

public class NoviceDAOImpl implements BaseDAO {
	private Connection con;	//Povezava
	
	private static final Logger logger=Logger.getLogger("log"); 
	
	
	public Connection getConnection(){
		try	{
			 Context initCtx = new InitialContext();
			 DataSource ds = (DataSource)initCtx.lookup("jdbc/FacebookDS");
			 con=ds.getConnection();
		} catch(Exception e){e.printStackTrace();}
		return con;
	}
	
	public void closeConnection(){
		try {
			con.close();
		} catch (SQLException e) {
			logger.log(Level.SEVERE,"Napaka pri zapiranju povezave: ",e);
		}
	}
	
	public List<Entiteta> vrniVse(){	//Vrne vse vnose v tabeli Novice
		List<Entiteta> le=new ArrayList<Entiteta>();
		Statement st=null;	//Uporabljamo navaden statement
		try{
			if(con==null)
				con=getConnection();
			
			st=con.createStatement();
			String sql="SELECT * FROM novice";
			ResultSet rs=st.executeQuery(sql);	//pridobimo rezultate v resultsetu
			while(rs.next()){
				String naziv=rs.getString("naziv");
				int id_avtorja=rs.getInt("id_avtorja");
				String vsebina=rs.getString("vsebina");
				Date datum_objave=rs.getDate("datum_objave");
				int id = rs.getInt("id");
				Novice nov=new Novice(id, naziv, id_avtorja,vsebina,datum_objave);
				le.add(nov);	
			}
		}catch(SQLException e){
			logger.log(Level.SEVERE,"SQLNapaka pri vrniVse(): ",e);
		}
		finally{ //Moramo še zapreti povezavo
			if(st!=null){
				try {
					st.close();
				} catch (SQLException e) {
					logger.log(Level.SEVERE,"SQL ne more zapreti statementa: ", e);
				}
			}
		}
		return le;
	}
	@Override
	public Entiteta vrni(int id) {
		PreparedStatement ps=null;
		try{
			if(con==null)
				con=getConnection();
			
			String sql="SELECT * FROM novice WHERE id = ?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String naziv=rs.getString("naziv");
				int id_avtorja=rs.getInt("id_avtorja");
				String vsebina=rs.getString("vsebina");
				Date datum_objave=rs.getDate("datum_objave");
				Novice nov=new Novice(id, naziv, id_avtorja,vsebina,datum_objave);
				return nov;	
			}
		}catch(SQLException e){
			logger.log(Level.SEVERE,"SQLNapaka: ",e);
		}
		finally{ //Moramo še zapreti povezavo
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					logger.log(Level.SEVERE,"SQL ne more zapreti statementa: ", e);
				}
			}
		}
		return null;
	}
	@Override
	public void vstavi(Entiteta ent) {
		PreparedStatement ps=null;
		Novice nov=(Novice)ent;
		try{
			if(con==null)
				getConnection();	//pridobimo povezavo èe je še nimamo
			String sql="INSERT INTO novice VALUES ('', ?, ?, ?, ?)";	//id je prazen element, saj je auto_increment, se nastavi avtomatsko
			ps=con.prepareStatement(sql);
			ps.setString(1, nov.getNaziv());
			ps.setInt(2,nov.getId_avtorja());
			ps.setString(3, nov.getVsebina());
			Date utilDate=new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
			ps.setDate(4, sqlDate); //Navaden Date ne dela.
			ps.executeUpdate();	//Metodo klièemo nad INSERT, UPDATE ali DELETE
		}catch(SQLException e){
			logger.log(Level.SEVERE,"SQLNapaka pri vstavljanju: ", e);
		}
		finally{ //Moramo še zapreti povezavo
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					logger.log(Level.SEVERE,"SQL ne morem zapreti statementa: ", e);
				}
			}
		}
		
	}
	public void vstaviDEMO(List<Entiteta> ent) {	//Metoda, ki lahko vnaša veè novic hkrati, za potrebe demonstracije transakcije, napake ter rollback na JDBC
		PreparedStatement ps=null;	//Uporabimo preparedStatement, ki je boljša rešitev od Statementa
		
		try{
			if(con==null)
				getConnection();	//èe še nimamo si pridobimo povezavo
			con.setAutoCommit(false);	//Za potrebe demonstracije nastavimo roèno potrditev transakcije
			for(Entiteta e : ent){	//gremo èez vse novice, v tem pimeru bosta 2
				Novice nov=(Novice)e;
				String sql="INSERT INTO novice VALUES ('', ?, ?, ?, ?)";
				ps=con.prepareStatement(sql);
				ps.setString(1, nov.getNaziv());
				ps.setInt(2,nov.getId_avtorja());
				ps.setString(3, nov.getVsebina());
				Date utilDate=new java.util.Date();
				java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
				ps.setDate(4, sqlDate); //Navaden Date ne dela.
				ps.executeUpdate();	//Metodo klièemo nad INSERT, UPDATE ali DELETE
			}
			throw new SQLException();	//Simulacija SQL izjeme
//			con.commit();
			
		}catch(SQLException e){
			try{
				con.rollback();	//Èe gre kaj narobe, transakcijo razveljavimo
			}catch(SQLException er){
				logger.log(Level.SEVERE,"SQL napaka pri razveljavitvi (rollback) transakcije.",er);
			}
			logger.log(Level.SEVERE,"SQLNapaka pri vstavljanju, izvajam rollback: ", e);
		}
		finally{ //Moramo še zapreti povezavo
			if(ps!=null){
				try {
					con.commit();	//Ob koncu izvedemo potrditev nad transakcijo
					ps.close();
				} catch (SQLException e) {
					logger.log(Level.SEVERE,"SQL ne morem zapreti statementa: ", e);
				}
			}
		}
		
	}
	@Override
	public void odstrani(int id) {
		PreparedStatement ps=null;
		try{
			if(con==null)
				con=getConnection();
			
			String sql="DELETE FROM novice WHERE id = ?";
			ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			
		}catch(SQLException e){
			logger.log(Level.SEVERE,"SQLNapaka pri DELETE: ",e);
		}
		finally{ //Moramo še zapreti povezavo
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					logger.log(Level.SEVERE,"SQL ne more zapreti statementa: ", e);
				}
			}
		}
		
	}
	@Override
	public void posodobi(Entiteta ent) {
		PreparedStatement ps=null;
		try{
			if(con==null)
				con=getConnection();
			
			String sql="UPDATE novice SET naziv=?, id_avtorja=?, vsebina=?, datum_objave=?  WHERE id=?";
			ps=con.prepareStatement(sql);
			Novice n = (Novice)ent;
			ps.setString(1, n.getNaziv());
			ps.setInt(2,n.getId_avtorja());
			ps.setString(3, n.getVsebina());
			java.sql.Date sqlDate=new java.sql.Date(n.getDatum_objave().getTime());
			ps.setDate(4, sqlDate); //Navaden Date ne dela.
			ps.setInt(5, n.getId());
			ps.executeUpdate();
			
		}catch(SQLException e){
			logger.log(Level.SEVERE,"SQLNapaka pri posodabljanju: ",e);
		}
		finally{ //Moramo še zapreti povezavo
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					logger.log(Level.SEVERE,"SQL ne more zapreti statementa: ", e);
				}
			}
		}		
	}
	
}
