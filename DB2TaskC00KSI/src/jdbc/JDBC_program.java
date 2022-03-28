package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC_program {
	

	static Connection conn = null;
	static Statement s = null;
	static PreparedStatement ps = null;
	static Scanner sc = new Scanner(System.in);
	static ResultSet rs;
	static CallableStatement cs;
	private static String url = "jdbc:oracle:thin:@193.6.5.58:1521:XE";
	private static String user = "H22_C00KSI";
	private static String pwd = "C00KSI";
	static CallableStatement registerOutParameter(int i, int f) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args) {
		Connect();
		StatikusTablaLetrehozas();
		//StatikusTablaModosiTas();
		//StatikusAdatfelvetel_1();
		//StatikusAdatfelvetel_2();
		//StatikusAdatfelvetel_3();
		DinamikusAdatfelvetel();
		//StatikusTablaTorles();
		//Lekapcs();
	}
	
	
	public static void Connect() {
		
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Sikeres kapcsolodasás\n");
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}


	
	public static void StatikusTablaLetrehozas() {
		String sqlp_versenyzok="create table versenyzok(rajtszam number(2) primary key, nev char(30) not null, csapat char(30), futamok number(3) check(futamok>0), gyozelmek number(3), debutalas date)";
		String sqlp_palyak="create table palyak (id number(3) primary key, nev char(60) not null, cim char(20), szuldatum date)";
		String sqlp_csapat="create table csapat (nev char(30) primary key, futamokszama number(4) NOT NULL, gyozelmek number(3), podiumok number(3))";
		
		
		if(conn!=null) {
			try {
				s=conn.createStatement();
				s.executeUpdate(sqlp_csapat);
				System.out.println("Csapat tábla létrehozva\n");
				s.executeUpdate(sqlp_versenyzok);
				System.out.println("Versenyzők tábla létrehozva\n");
				s.executeUpdate(sqlp_palyak);
				System.out.println("Pályák tábla létrehozva\n");
				s.close();
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	/*public static void StatikusTablaModosiTas() {
		if(conn != null) {}
		try {
			String sqlp="alter table auto add(tulaj_id references tulaj)";
			s=conn.createStatement();
			s.executeUpdate(sqlp);
			System.out.println("Autó tábla módosítva!\n");
			s.close();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	*/
	public static void StatikusTablaTorles() {
		if(conn != null) {}
		try {
			String sqlp_versenyzok="DROP TABLE versenyzok";
			String sqlp_palyak="DROP TABLE palyak";
			String sqlp_csapat="DROP TABLE csapat";
			s=conn.createStatement();
			s.executeUpdate(sqlp_versenyzok);
			System.out.println("Versenyzők tábla törölve!\n");
			s.executeUpdate(sqlp_palyak);
			System.out.println("Pályák tábla törölve!\n");
			s.executeUpdate(sqlp_csapat);
			System.out.println("Csapat tábla törölve\n");
			s.close();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public static void StatikusAdatfelvetel_1() {
		if(conn != null) {
			//String sqlp_tul="insert into tulaj values (1, 'Tóth Máté', " + "'Miskolc', to_date('1980.05.12', 'yyyy.mm.dd'))";
			String sqlp[]= {
					"insert into palyak values(1,'Bahrain International Circuit, Sakhir','Bahrein',to_date('2022.03.20', 'yyyy.mm.dd'))",
					"insert into palyak values(2,'Saudi Arabia Jeddah Corniche Circuit, Jeddah','Szaúd-arábia',to_date('2022.03.27', 'yyyy.mm.dd'))",
					"insert into palyak values(3,'Australia Albert Park Circuit, Melbourne','Ausztrália', to_date('2022.04.10', 'yyyy.mm.dd'))",
					"insert into palyak values(4,'Italy Imola Circuit, Imola','Olaszország', to_date('2022.04.24', 'yyyy.mm.dd'))",
					"insert into palyak values(5,'United States Miami International Autodrome, Miami Gardens','Egyesült Államok', to_date('2022.05.08', 'yyyy.mm.dd'))",
					"insert into palyak values(6,'Spain Circuit de Barcelona-Catalunya, Montmeló','Spanyolország', to_date('2022.05.22', 'yyyy.mm.dd'))",
					"insert into palyak values(7,'Monaco Circuit de Monaco, Monaco','Monacó', to_date('2022.05.29', 'yyyy.mm.dd'))",
					"insert into palyak values(8,'Azerbaijan Baku City Circuit, Baku','Azerbajdzsán', to_date('2022.06.12', 'yyyy.mm.dd'))",
					"insert into palyak values(9,'Canada Circuit Gilles Villeneuve, Montréal','Kanada', to_date('2022.06.19', 'yyyy.mm.dd'))",
					"insert into palyak values(10,'United Kingdom Silverstone Circuit, Silverstone','Nagy-Britannia', to_date('2022.07.03', 'yyyy.mm.dd'))",
					"insert into palyak values(11,'Austria Red Bull Ring, Spielberg','Ausztria', to_date('2022.07.10', 'yyyy.mm.dd'))",
					"insert into palyak values(12,'France Circuit Paul Ricard, Le Castellet','Franciaország', to_date('2022.07.24', 'yyyy.mm.dd'))",
					"insert into palyak values(13,'Hungary Hungaroring, Mogyoród','Magyarország', to_date('2022.07.31', 'yyyy.mm.dd'))",
					"insert into palyak values(14,'Belgium Circuit de Spa-Francorchamps, Stavelot','Belgium', to_date('2022.08.28', 'yyyy.mm.dd'))",
					"insert into palyak values(15,'Netherlands Circuit Zandvoort, Zandvoort','Hollandia', to_date('2022.09.09', 'yyyy.mm.dd'))",
					"insert into palyak values(16,'Italy Monza Circuit, Monza','Olaszország', to_date('2022.09.11', 'yyyy.mm.dd'))",
					"insert into palyak values(17,'Singapore Marina Bay Street Circuit, Singapore','Szingapúr', to_date('2022.10.02', 'yyyy.mm.dd'))",
					"insert into palyak values(18,'Japan Suzuka International Racing Course, Suzuka','Japán ', to_date('2022.10.09', 'yyyy.mm.dd'))",
					"insert into palyak values(19,'United States Circuit of the Americas, Austin, Texas','Egyesült Államok', to_date('2022.10.23', 'yyyy.mm.dd'))",
					"insert into palyak values(20,'Mexico Autódromo Hermanos Rodríguez, Mexico City','Mexikó', to_date('2022.10.30', 'yyyy.mm.dd'))",
					"insert into palyak values(21,'Brazil Interlagos Circuit, São Paulo','Brazilía', to_date('2022.11.13', 'yyyy.mm.dd'))",
					"insert into palyak values(22,'United Arab Emirates Yas Marina Circuit, Abu Dhabi','Abu-Dzabi ', to_date('2022.11.20', 'yyyy.mm.dd'))",



			};
			
			/*try {
				s=conn.createStatement();
				s.executeUpdate(sqlp);
				System.out.println("Tulaj felvéve\n");
				s.close();
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
			*/
			for(int i = 0;i < sqlp.length; i++) {
				try {
					s=conn.createStatement();
					s.executeUpdate(sqlp[i]);
					System.out.println("Pálya felvéve\n");
					s.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
			}
		}
	}
	
	public static void StatikusAdatfelvetel_3() {
		if(conn != null) {
		//	String sqlp_tul="insert into tulaj values (1, 'Tóth Máté', " + "'Miskolc', to_date('1980.05.12', 'yyyy.mm.dd'))";
			String sqlp[]= {
					"insert into csapat values('Mercedes',251,124,265)",
			"insert into csapat values('Red Bull',328,76,207)",
			"insert into csapat values('Ferrari',1034,238,777)",
			"insert into csapat values('McLaren-Mercedes',908,183,493)",
			"insert into csapat values('Alpine-Renault',24,1,2)",
			"insert into csapat values('AlphaTauri-Red Bull',41,1,2)",
			"insert into csapat values('Aston Martin-Mercedes',30,0,1)",
			"insert into csapat values('Williams-Mercedes',772,114,313)",
			"insert into csapat values('Alfa Romeo-Ferrari',172,10,26)",
			"insert into csapat values('Haas-Ferrari',124,0,0)",

			};
		
			
			for(int i = 0;i < sqlp.length; i++) {
				try {
					s=conn.createStatement();
					s.executeUpdate(sqlp[i]);
					System.out.println("csapat felvéve\n");
					s.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
			}
		}
	}
	
	public static void StatikusAdatfelvetel_2() {
		if(conn != null) {
			//String sqlp_tul="insert into tulaj values (1, 'Tóth Máté', " + "'Miskolc', to_date('1980.05.12', 'yyyy.mm.dd'))";
			String sqlp[]= {
					"insert into versenyzok values(44,'Lewis Hamilton','Mercedes',290,103,to_date('2007.03.18', 'yyyy.mm.dd'))",
			"insert into versenyzok values(63,' George Russell','Mercedes',62,0,to_date('2019.03.17', 'yyyy.mm.dd'))",
			"insert into versenyzok values(1,'Max Verstappen','Red Bull',143,20,to_date('2015.03.15', 'yyyy.mm.dd'))",
			"insert into versenyzok values(11,'Sergio Pérez','Red Bull',219,2,to_date('2011.03.27', 'yyyy.mm.dd'))",
			"insert into versenyzok values(16,'Charles Leclerc','Ferrari',83,3,to_date('2018.03.25', 'yyyy.mm.dd'))",
			"insert into versenyzok values(55,'Carlos Sainz Jr.','Ferrari',143,0,to_date('2015.03.15', 'yyyy.mm.dd'))",
			"insert into versenyzok values(3,'Daniel Ricciardo','McLaren-Mercedes',212,8,to_date('2011.07.11', 'yyyy.mm.dd'))",
			"insert into versenyzok values(4,'Lando Norris','McLaren-Mercedes',62,0,to_date('2019.03.17', 'yyyy.mm.dd'))",
			"insert into versenyzok values(14,'Fernando Alonso','Alpine-Renault',338,32,to_date('2001.03.04', 'yyyy.mm.dd'))",
			"insert into versenyzok values(31,'Esteban Ocon','Alpine-Renault',91,1,to_date('2016.08.28', 'yyyy.mm.dd'))",
			"insert into versenyzok values(10,'Pierre Gasly','AlphaTauri-Red Bull',88,1,to_date('2017.10.01', 'yyyy.mm.dd'))",
			"insert into versenyzok values(22,'Cunoda Júki','AlphaTauri-Red Bull',24,0,to_date('2021.03.28', 'yyyy.mm.dd'))",
			"insert into versenyzok values(5,'Sebastian Vettel','Aston Martin-Mercedes',280,53,to_date('2007.06.17', 'yyyy.mm.dd'))",
			"insert into versenyzok values(18,'Lance Stroll','Aston Martin-Mercedes',103,0,to_date('2017.03.27', 'yyyy.mm.dd'))",
			"insert into versenyzok values(6,'Nicholas Latifi','Williams-Mercedes',41,0,to_date('2020.07.05', 'yyyy.mm.dd'))",
			"insert into versenyzok values(23,'Alexander Albon','Williams-Mercedes',40,0,to_date('2019.03.19', 'yyyy.mm.dd'))",
			"insert into versenyzok values(24,'Csou Kuan-jü','Alfa Romeo-Ferrari',2,0,to_date('2022.03.20', 'yyyy.mm.dd'))",
			"insert into versenyzok values(77,'Valtteri Bottas','Alfa Romeo-Ferrari',181,10,to_date('2013.03.17', 'yyyy.mm.dd'))",
			"insert into versenyzok values(47,'Mick Schumacher','Haas-Ferrari',24,0,to_date('2021.03.28', 'yyyy.mm.dd'))",
			"insert into versenyzok values(20,'Kevin Magnussen','Haas-Ferrari',122,0,to_date('2014.03.16', 'yyyy.mm.dd'))",



			};
	
			
			for(int i = 0;i < sqlp.length; i++) {
				try {
					s=conn.createStatement();
					s.executeUpdate(sqlp[i]);
					System.out.println("Versenyző felvéve\n");
					s.close();
				}catch(Exception ex) {
					System.err.println(ex.getMessage());
				}
			}
		}
	}
	
	
	public static void DinamikusAdatfelvetel() {
		if(conn != null) {
			String sqlp="insert into versenyzok(rajtszam, nev, csapat, futamok, gyozelmek, debutalas)" + "values(?,?,?,?,?,?)";
			
			System.out.println("Kérem a versenyző rajtszámát: ");
			int rajtszam = sc.nextInt();
			System.out.println("Kérem a nevét: ");
			String nev = sc.next().trim();
			System.out.println("Kérem a csapata nevét: ");
			String csapat = sc.next().trim();
			System.out.println("Hány futamot teljesített már?: ");
			int futamok = sc.nextInt();
			System.out.println("Hány futamot nyert meg?: ");
			int gyozelmek = sc.nextInt();
			System.out.println("Mikor debütált?: ");
			String debutalas = sc.next().trim();
			
			try {
				ps=conn.prepareStatement(sqlp);
				ps.setInt(1, rajtszam);
				ps.setString(2, nev);
				ps.setString(3, csapat);	
				ps.setInt(4, futamok);
				ps.setInt(5, gyozelmek);
				ps.setString(6, debutalas);
				ps.executeUpdate();
				ps.close();
				System.out.println("Pilóta felvéve\n");
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public void DinamikusAdattorles() {
		System.out.println("Törlendö auto: ");
		String rsz = sc.next();
		//String user = null;
		String sqlp = "delete from " +user+ ".AUTO" + "where rsz=?";
		if (conn != null) {
			try {
				ps = conn.prepareStatement(sqlp);
				ps.setString(1, rsz);
				ps.executeUpdate();
				ps.close();
				System.out.println(rsz + " rendszamú autó törölve\n");
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	public void StatikusAdattorles() {
		System.out.println("Torlendo auto: ");
		String rsz = sc.next();
		String sqlp = "delete from auto where rsz like '"+rsz+"'";
		if (conn != null) {
			try {
				s = conn.createStatement();
				s.executeUpdate(sqlp);
				s.close();
				System.out.println(rsz + " rendszamu auto torolve\n");
			}catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	
	}
	
	public void StatikusLekerdezes() {
		if (conn != null) {
			String sqlp = "select * from versenyzok";
			System.out.println("RajtSzam   Nev    Csapat    Futamok    Gyozelem     Debutalas");
			System.out.println("-----------------------------------------------------");
			try {
				s = conn.createStatement();
				s.executeQuery(sqlp);
				rs = s.getResultSet();
				while(rs.next()) {
					String rsz = rs.getString("rsz");
					String tipus = rs.getString("tipus");
					String szin = rs.getString("szin");
					int evjarat = rs.getInt("evjarat");
					int ar = rs.getInt("ar");
					int tulaj_id = rs.getInt("Tulaj_id");
					System.out.println(rsz+"\t\t"+tipus+"\t"+szin+"\t"+evjarat+"\t"+ar+"t"+tulaj_id);
				}
				rs.close();
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	public void ModosithatoKurzor() {
		System.out.println("Szin: ");
		String szin = sc.next().trim();
		String sqlp = "select ar from auto where szin = '"+ szin +"'";
		if (conn != null) {
			try {
				s = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				rs = s.executeQuery(sqlp);
				while (rs.next()) {
					int regiar = rs.getInt("ar");
					rs.updateInt("ar", (regiar*2));
					rs.updateRow();
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	public void InEljarasHivas() {
		if (conn != null) {
			try {
				String sqlp = "Create or replace procedure arcsokkentes" +
								"(kor IN number) is " +
								"begin " + 
								"update auto set ar=ar0.9 where" +
								"to_char(sysdate, 'yyyy')-evjarat > kor; " +
								"end;";
				System.out.println("Kor: ");
				int kor = sc.nextInt();
				s = conn.createStatement();
				s.executeUpdate(sqlp);
				System.out.println("Fuggveny Letrejott\n");
				cs = conn.prepareCall("{call arcsokkent(?)}");
				cs.setInt (1, kor);
				cs.execute();
			}catch (Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	public void OutEljarasHivas() {
		if (conn != null) {
			try {
				String sqlp = "create or replace procedure atlagar" +
								"(sz IN char, atl OUT number) is" +
								"begin "+
								"select avg(ar) into all from auto where szin=sz; " +
								"end;";
				System.out.println("Szin: ");
				String szin = sc.next();
				s = conn.createStatement();
				s.executeUpdate(sqlp);
				System.out.println("Eljaras letrejött \n");
				cs = conn.prepareCall("{call atlagfv(?)}");
				cs.setString(1, szin);
				cs = registerOutParameter(1, java.sql.Types.FLOAT);
				cs.execute();
				float atlag = cs.getFloat(2);
				System.out.println(szin + "autok atlagara: " + atlag + "\n");
				
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}


	public void FuggvenyHivas() {
		if (conn != null) {
			try {
				String sqlp = "create or replace function atlagarfv" +
								"(sz IN char) return number is" +
								"atl number (10,2);" +
								"begin "+
								"select avg(ar) into all from auto where szin=sz; " +
								"end;";
				System.out.println("Szin: ");
				String szin = sc.next();
				s = conn.createStatement();
				s.executeUpdate(sqlp);
				System.out.println("Fuggveny letrejött \n");
				cs = conn.prepareCall("{? = call atlagfv(?)}");
				cs = registerOutParameter(1, java.sql.Types.FLOAT);
				cs.setString(2, szin);
				cs.execute();
				float atlag = cs.getFloat(1);
				System.out.println(szin + "autok atlagara: " + atlag + "\n");
				
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	
	public void DinamikusTablaTorles() {
		String sqlp = "create or replace procedure tablatorles(nev IN char) is" +
						"begin" +
						"execute immediate 'drop table ' || nev;" +
						"end;";
		System.out.println("Törlendő tábla: ");
		String name = sc.next().trim();
		if (conn != null) {
			try {
				s = conn.createStatement();
				s.executeUpdate(sqlp);
				cs = conn.prepareCall("{call tablatorles(?)");
				cs.setString(1, name);
				cs.execute();
				System.out.println(" tábla törlve\n");
			} catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
	
	public void DinamikusModositas() {
		if (conn != null) {
			String sqlp = "update auto1 set ar=ar-?";
			System.out.println("Mennyivel csökkentsük az árat?");
			int arcsokk= sc.nextInt();
			try {
				conn.setAutoCommit(false);
				try {
					ps = conn.prepareStatement(sqlp);
					ps.setInt(1, arcsokk);
					ps.executeUpdate();
					conn.commit();
					System.out.println("Módosítás megtörtént\n");
				}catch(Exception e) {
					System.err.println(e.getMessage());
					conn.rollback();
					System.out.println("Módosítás visszavonva\n");
				}
				conn.setAutoCommit(true);
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}

	public static void Lekapcs() {
	if (conn != null) {
		try {
			conn.close();
			System.out.println("Sikeres lekapcsolodas \n");
			}catch (Exception e) {
			System.err.println(e.getMessage());
			}
		}
	}
	
	

}
