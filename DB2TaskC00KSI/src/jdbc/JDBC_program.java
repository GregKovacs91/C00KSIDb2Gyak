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
		Menu();
		Lekapcs();
	/*	StatikusTablaTorles(); //pipa
		StatikusTablaLetrehozas();//pipa
		StatikusTablaModosiTas(); //pipa
		StatikusAdatfelvetel_1(); //pipa
		StatikusAdatfelvetel_3(); //pipa
		StatikusAdatfelvetel_2(); //pipa
		
		DinamikusLekerdezes(); //pipa
		//DinamikusAdatfelvetel();
		StatikusLekerdezes(); //pipa
		DinamikusAdattorles();//pipa
		ModosithatoKurzor(); //pipa*/
		
	}
	
	
	public static void Connect() {
		
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Sikeres kapcsolodasás\n");
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void Menu() {
		
		System.out.println("\n\n\nFőmenü!\nVálaszd ki az alábbi lehetőségeket");
		System.out.println("\nDefault - Feltölti az eredi táblákat, adatokat és kötéseket");
		System.out.println("\nDBTorles - Törli az adatbázist");
		System.out.println("\nAdattorles - Törli a pilótát az adatbázisból");
		System.out.println("\nLekerdez - Dinamikus lekérdezés a pilótákról");
		System.out.println("\nEllenorzes - Megadja egy tábla össze adatát");
		System.out.println("\nKurzor - Módosítja egy csapat részvételének mennyiségét");
		System.out.println("\nLekapcs - Kilép a programból\n");
		String parancs = sc.next();
		if(conn != null) {
			if(parancs.equals("Lekerdez")) {
				DinamikusLekerdezes();
			}else if(parancs.equals("Ellenorzes")) {
				StatikusLekerdezes();
			}else if(parancs.equals("Adattorles")) {
				DinamikusAdattorles();
			}else if(parancs.equals("Kurzor")) {
				ModosithatoKurzor();
			}else if(parancs.equals("Lekapcs")) {
				Lekapcs();
			}else if(parancs.equals("DBTorles")) {
				StatikusTablaTorles();
			}else if(parancs.equals("Default")) {
				StatikusTablaLetrehozas();
				StatikusTablaModosiTas(); 
				StatikusAdatfelvetel_1(); 
				StatikusAdatfelvetel_3(); 
				StatikusAdatfelvetel_2(); 
			}else {
				System.out.println("A parancs nem megfelelő! Kérlek add meg a megfelelő parancsot");
				Menu();
			}
		}
	}

	
	public static void StatikusTablaLetrehozas() {
		String sqlp_versenyzok="create table versenyzok(rajtszam number(2) primary key, nev char(20) not null, futamok number(3) check(futamok>0), gyozelmek number(3), debutalas date)";
		String sqlp_palyak="create table palyak (id number(3) primary key, helyszin char(80) not null, orszag char(40), futamnapja date, palyahossz number(4))";
		String sqlp_csapat="create table csapat (csapatnev char(23) primary key, futamokszama number(4) NOT NULL, gyozelmek number(3), podiumok number(3))";
		
		
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
	
	
	public static void StatikusTablaModosiTas() {
		if(conn != null) {}
		try {
			String sqlp="alter table versenyzok add(csapatnev references csapat)";
			s=conn.createStatement();
			s.executeUpdate(sqlp);
			System.out.println("Versenyzők táblához csapatnév hozzáadva!\n");
			s.close();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public static void StatikusTablaTorles() {
		if(conn != null) {}
		try {
			
			String sqlp_palyak="DROP TABLE palyak";
			String sqlp_csapat="DROP TABLE csapat";
			String sqlp_versenyzok="DROP TABLE versenyzok";
			
			
			s=conn.createStatement();
			
			s.executeUpdate(sqlp_versenyzok);
			System.out.println("Versenyzők tábla törölve!\n");
			
			s.executeUpdate(sqlp_csapat);
			System.out.println("Csapat tábla törölve\n");
			
			s.executeUpdate(sqlp_palyak);
			System.out.println("Pályák tábla törölve!\n");
			
			s.close();
			Menu();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public static void StatikusAdatfelvetel_1() {
		if(conn != null) {
		
			String sqlp[]= {
					"insert into palyak values(1,'Bahrain International Circuit, Sakhir','Bahrein',to_date('2022.03.20', 'yyyy.mm.dd'), 5412)",
					"insert into palyak values(2,'Saudi Arabia Jeddah Corniche Circuit, Jeddah','Szaúd-arábia',to_date('2022.03.27', 'yyyy.mm.dd'), 3257)",
					"insert into palyak values(3,'Australia Albert Park Circuit, Melbourne','Ausztrália', to_date('2022.04.10', 'yyyy.mm.dd'), 4120)",
					"insert into palyak values(4,'Italy Imola Circuit, Imola','Olaszország', to_date('2022.04.24', 'yyyy.mm.dd'), 3895)",
					"insert into palyak values(5,'United States Miami International Autodrome, Miami Gardens','Egyesült Államok', to_date('2022.05.08', 'yyyy.mm.dd'), 3147)",
					"insert into palyak values(6,'Spain Circuit de Barcelona-Catalunya, Montmeló','Spanyolország', to_date('2022.05.22', 'yyyy.mm.dd'), 3965)",
					"insert into palyak values(7,'Monaco Circuit de Monaco, Monaco','Monacó', to_date('2022.05.29', 'yyyy.mm.dd'), 2485)",
					"insert into palyak values(8,'Azerbaijan Baku City Circuit, Baku','Azerbajdzsán', to_date('2022.06.12', 'yyyy.mm.dd'), 4712)",
					"insert into palyak values(9,'Canada Circuit Gilles Villeneuve, Montréal','Kanada', to_date('2022.06.19', 'yyyy.mm.dd'), 5121)",
					"insert into palyak values(10,'United Kingdom Silverstone Circuit, Silverstone','Nagy-Britannia', to_date('2022.07.03', 'yyyy.mm.dd'), 4632)",
					"insert into palyak values(11,'Austria Red Bull Ring, Spielberg','Ausztria', to_date('2022.07.10', 'yyyy.mm.dd'), 3749)",
					"insert into palyak values(12,'France Circuit Paul Ricard, Le Castellet','Franciaország', to_date('2022.07.24', 'yyyy.mm.dd'), 4008)",
					"insert into palyak values(13,'Hungary Hungaroring, Mogyoród','Magyarország', to_date('2022.07.31', 'yyyy.mm.dd'), 4381)",
					"insert into palyak values(14,'Belgium Circuit de Spa-Francorchamps, Stavelot','Belgium', to_date('2022.08.28', 'yyyy.mm.dd'), 7004)",
					"insert into palyak values(15,'Netherlands Circuit Zandvoort, Zandvoort','Hollandia', to_date('2022.09.09', 'yyyy.mm.dd'), 3896)",
					"insert into palyak values(16,'Italy Monza Circuit, Monza','Olaszország', to_date('2022.09.11', 'yyyy.mm.dd'), 4294)",
					"insert into palyak values(17,'Singapore Marina Bay Street Circuit, Singapore','Szingapúr', to_date('2022.10.02', 'yyyy.mm.dd'), 3986)",
					"insert into palyak values(18,'Japan Suzuka International Racing Course, Suzuka','Japán ', to_date('2022.10.09', 'yyyy.mm.dd'), 3745)",
					"insert into palyak values(19,'United States Circuit of the Americas, Austin, Texas','Egyesült Államok', to_date('2022.10.23', 'yyyy.mm.dd'), 4208)",
					"insert into palyak values(20,'Mexico Autódromo Hermanos Rodríguez, Mexico City','Mexikó', to_date('2022.10.30', 'yyyy.mm.dd'), 4398)",
					"insert into palyak values(21,'Brazil Interlagos Circuit, São Paulo','Brazilía', to_date('2022.11.13', 'yyyy.mm.dd'), 3625)",
					"insert into palyak values(22,'United Arab Emirates Yas Marina Circuit, Abu Dhabi','Abu-Dzabi ', to_date('2022.11.20', 'yyyy.mm.dd'), 3958)",



			};
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
			String sqlp[]= {
					"insert into csapat values('Mercedes',251,124,265)",
			"insert into csapat values('Red Bull',328,76,207)",
			"insert into csapat values('Ferrari',1034,238,777)",
			"insert into csapat values('McLaren',908,183,493)",
			"insert into csapat values('Alpine',24,1,2)",
			"insert into csapat values('AlphaTauri',41,1,2)",
			"insert into csapat values('Aston Martin',30,0,1)",
			"insert into csapat values('Williams',772,114,313)",
			"insert into csapat values('Alfa Romeo',172,10,26)",
			"insert into csapat values('Haas',124,0,0)",

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
		
			String sqlp[]= {
					"insert into versenyzok values(44,'Lewis Hamilton',290,103,to_date('2007.03.18', 'yyyy.mm.dd'),'Mercedes')",
			"insert into versenyzok values(63,' George Russell',62,0,to_date('2019.03.17', 'yyyy.mm.dd'),'Mercedes')",
			"insert into versenyzok values(1,'Max Verstappen',143,20,to_date('2015.03.15', 'yyyy.mm.dd'),'Red Bull')",
			"insert into versenyzok values(11,'Sergio Pérez',219,2,to_date('2011.03.27', 'yyyy.mm.dd'),'Red Bull')",
			"insert into versenyzok values(16,'Charles Leclerc',83,3,to_date('2018.03.25', 'yyyy.mm.dd'),'Ferrari')",
			"insert into versenyzok values(55,'Carlos Sainz Jr.',143,0,to_date('2015.03.15', 'yyyy.mm.dd'),'Ferrari')",
			"insert into versenyzok values(3,'Daniel Ricciardo',212,8,to_date('2011.07.11', 'yyyy.mm.dd'),'McLaren-Mercedes')",
			"insert into versenyzok values(4,'Lando Norris',62,0,to_date('2019.03.17', 'yyyy.mm.dd'),'McLaren-Mercedes')",
			"insert into versenyzok values(14,'Fernando Alonso',338,32,to_date('2001.03.04', 'yyyy.mm.dd'),'Alpine-Renault')",
			"insert into versenyzok values(31,'Esteban Ocon',91,1,to_date('2016.08.28', 'yyyy.mm.dd'),'Alpine-Renault')",
			"insert into versenyzok values(10,'Pierre Gasly',88,1,to_date('2017.10.01', 'yyyy.mm.dd'),'AlphaTauri-Red Bull')",
			"insert into versenyzok values(22,'Cunoda Júki',24,0,to_date('2021.03.28', 'yyyy.mm.dd'),'AlphaTauri-Red Bull')",
			"insert into versenyzok values(5,'Sebastian Vettel',280,53,to_date('2007.06.17', 'yyyy.mm.dd'),'Aston Martin-Mercedes')",
			"insert into versenyzok values(18,'Lance Stroll',103,0,to_date('2017.03.27', 'yyyy.mm.dd'),'Aston Martin-Mercedes')",
			"insert into versenyzok values(6,'Nicholas Latifi',41,0,to_date('2020.07.05', 'yyyy.mm.dd'),'Williams-Mercedes')",
			"insert into versenyzok values(23,'Alexander Albon',40,0,to_date('2019.03.19', 'yyyy.mm.dd'),'Williams-Mercedes')",
			"insert into versenyzok values(24,'Csou Kuan-jü',2,0,to_date('2022.03.20', 'yyyy.mm.dd'),'Alfa Romeo-Ferrari')",
			"insert into versenyzok values(77,'Valtteri Bottas',181,10,to_date('2013.03.17', 'yyyy.mm.dd'),'Alfa Romeo-Ferrari')",
			"insert into versenyzok values(47,'Mick Schumacher',24,0,to_date('2021.03.28', 'yyyy.mm.dd'),'Haas-Ferrari')",
			"insert into versenyzok values(20,'Kevin Magnussen',122,0,to_date('2014.03.16', 'yyyy.mm.dd'),'Haas-Ferrari')",



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
			
			System.out.println("\nMinden adat bekerült az adatbázisba!\n\n");
			Menu();
		}
	}
	
	public static void DinamikusLekerdezes() {
		System.out.println("Adja meg a futamok számát: ");
		String futamok = sc.next().trim();
		String sqlp = "select nev from versenyzok where gyozelmek>0 AND  futamok >= '"+futamok+"'";
		if(conn != null) {
			try {
				s=conn.createStatement();
				s.executeQuery(sqlp);
				rs=s.getResultSet();
				while(rs.next()) {
						String nev = rs.getString("nev");
						
						System.out.println("Versenyzők: "+nev);
				}
				rs.close();
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}	Menu();
	}
	
	
/*	public static void DinamikusAdatfelvetel() {
		if(conn != null) {
			String sqlp="insert into csapat(csapatnev, futamokszama, gyozelmek, podiumok)" + "values(?,?,?,?)";
			
			
			System.out.println("Kérem a csapat nevét: ");
			String nev = sc.next().trim();
			System.out.println("Hány futamot teljesített már?: ");
			int futamok = sc.nextInt();
			System.out.println("Hány futamot nyert meg?: ");
			int gyozelmek = sc.nextInt();
			System.out.println("Mikor debütált?: ");
			String debutalas = sc.next().trim();

			
			try {
				ps=conn.prepareStatement(sqlp);
				
				ps.setString(1, nev);
				ps.setInt(2, futamok);
				ps.setInt(3, gyozelmek);
				ps.setString(4, debutalas);
	
				ps.executeUpdate(sqlp);
				ps.close();
				System.out.println("Pilóta felvéve\n");
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
	}
*/
	public static void DinamikusAdattorles() {
		System.out.println("Melyik pilótát töröljük az adatbázisból?: ");
		String rajtszam = sc.next();
		String user = null;
		String sqlp = "delete from versenyzok where rajtszam=?";
		if (conn != null) {
			try {
				ps = conn.prepareStatement(sqlp);
				ps.setString(1, rajtszam);
				ps.executeUpdate();
				ps.close();
				System.out.println(rajtszam + " rajtszámú pilóta törölve\n");
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
				DinamikusAdattorles();
			}
		} Menu();
	}
	
	
	
	
	
	public static void StatikusLekerdezes() {
		System.out.println("Add meg a táblát, amelynek szeretnéd az adatait látni:");
		String tabla = sc.next().trim();
		if(tabla.equals("versenyzok")) {
			if(conn != null) {
				String sqlp="select * from versenyzok";
				System.out.println("Rajtszam          Nev          Csapat                  Futamok           Gyozelem     Debutalas");
				System.out.println("-----------------------------------------------------------------------------------------------");
				try {
					s=conn.createStatement();
					s.executeQuery(sqlp);
					rs=s.getResultSet();
					while(rs.next()) {
							int rajtszam = rs.getInt("rajtszam");
							String nev=rs.getString("nev");
							String csapatnev = rs.getString("csapatnev");
							int futamok = rs.getInt("futamok");
							int gyozelmek = rs.getInt("gyozelmek");
							String debutalas = rs.getString("debutalas");
							
							System.out.println(rajtszam+"        "+nev+"  "+csapatnev+"     "+futamok+"            "+gyozelmek+"        "+debutalas);
					}
					rs.close();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				} Menu();
			}
		}else if(tabla.equals("palyak")) {
			if(conn != null) {
				String sqlp="select * from palyak";
				System.out.println("ID, Helyszín,                                                                    Ország,                                     Futamnapja           Palyahossz");
				System.out.println("----------------------------------------------------------------------------------------------------------------------------------------");
				try {
					s=conn.createStatement();
					s.executeQuery(sqlp);
					rs=s.getResultSet();
					while(rs.next()) {
							int id=rs.getInt("id");
							String helyszin = rs.getString("helyszin");
							String orszag = rs.getString("orszag");
							String futamNapja = rs.getString("futamnapja");
							int palyahossz=rs.getInt("palyahossz");
							System.out.println(id+" "+helyszin+" "+orszag+" "+futamNapja+"       "+palyahossz);
					}
					rs.close();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}Menu();
			}
		}else if(tabla.equals("csapat")){
			if(conn != null) {
				String sqlp="select * from csapat";
				System.out.println("Csapatnev,                     FutamokSzama, Gyozelmek, Podiumok");
				System.out.println("----------------------------------------------------------------");
				try {
					s=conn.createStatement();
					s.executeQuery(sqlp);
					rs=s.getResultSet();
					while(rs.next()) {
							String csapatNev=rs.getString("csapatNev");
							int futamokSzama = rs.getInt("futamokszama");
							int gyozelmek = rs.getInt("gyozelmek");
							int podiumok = rs.getInt("podiumok");
							
							System.out.println(csapatNev+"      "+futamokSzama+"             "+gyozelmek+"       "+podiumok);
					}
					rs.close();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}Menu();
		}else {
			System.out.println("Nem megfelelő táblát adott meg. Kérem próbálja újra");
			StatikusLekerdezes();
		}
	}
	
	
	public static void ModosithatoKurzor() {
		System.out.println("Csapat név megadása: ");
		String csapatnev = sc.next().trim();
		String sqlp = "select futamokszama from csapat where csapatnev = '"+ csapatnev +"'";
		if (conn != null) {
			try {
				s = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				rs = s.executeQuery(sqlp);
				while (rs.next()) {
					int regifutamokszama = rs.getInt("futamokszama");
					rs.updateInt("futamokszama", (regifutamokszama*2));
					rs.updateRow();
					System.out.printf("Az futamok száma a módosítás után: %d",regifutamokszama*2);
				}
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
				ModosithatoKurzor();
			}
		}Menu();
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
