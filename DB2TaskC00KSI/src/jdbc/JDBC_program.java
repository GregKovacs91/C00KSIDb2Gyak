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
		Connect(); // 48 sor
		//Menu(); // 66 sor
		
		//Lekapcs(); //650 sor
		//StatikusTablaTorles();  //234 sor
		//StatikusTablaLetrehozas();//107 sor
		//StatikusTablaModosiTas(); //130 sor
		//StatikusAdatfelvetel_1(); //261 sor
		//StatikusAdatfelvetel_3(); //334 sor
		//StatikusAdatfelvetel_2(); //304 sor

		
		//	DinamikusAdatfelvetel(); //143 sor
		//	DinamikusLekerdezes(); //380 sor
		//	DinamikusAdattorles();//478 sor
		//	ModosithatoKurzor(); //626 sor
		//	StatikusLekerdezes(); // 546 sor	
		//		DinamikusAdatlekerdezes2(); //666 sor
	}
	
	
	public static void Connect() {
		
		try {
			System.out.println("Kérem a felhasználót a bejelentkezéshez");
			String felhasznalo = sc.next().trim();
			if(felhasznalo.equals("H22_C00KSI")) {
				System.out.println("Kérem a jelszót");
				String password = sc.next().trim();
				if(password.equals("C00KSI")){
					conn = DriverManager.getConnection(url,user,pwd);
					System.out.println("Sikeres kapcsolodasás\n"); Menu();
					}else { System.out.println("A jelszó nem megfelelő\n \n"); Connect();}
				}else { System.out.println("A felhasználó nem megfelelő nem megfelelő\n \n"); Connect();} 
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void Menu() {
		
		System.out.println("\n\n\nFőmenü!\nVálaszd ki az alábbi lehetőségeket");
		System.out.println("\nDefault - Feltölti az eredi táblákat, adatokat és kötéseket");
		System.out.println("\nDBTorles - Törli az adatbázist");
		System.out.println("\nAdatFelvisz - Adatfelvitel a megadott táblába");
		System.out.println("\nAdattorles - Törli a rekordot az adatbázisból");
		System.out.println("\nLekerdez - Dinamikus lekérdezés a tábla mezőiről");
		System.out.println("\nSorLekerdez - Dinamikus lekérdezés az adott tábla egy adott soráról");
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
			}else if(parancs.equals("AdatFelvisz")) {
				DinamikusAdatfelvetel();
			}else if(parancs.equals("SorLekerdez")) {
				DinamikusAdatlekerdezes2();
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
		String sqlp_csapat="create table csapat (csapatnev char(23) primary key, futamokszama number(4) NOT NULL, gyozelmek number(4), podiumok number(4), bajnoksagok number(3), debutalas date)";
		
		
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

		public static void DinamikusAdatfelvetel() {
			if(conn != null) {
		System.out.println("Add meg a táblát, ahova szeretnél új adatokat berakni (versenyzok, csapat vagy palyak):");
		String tabla = sc.next().trim();
		
		if(tabla.equals("palyak")) {
		
		System.out.println("Kérem a pálya azonosítóját: ");
		int id= sc.nextInt();
		System.out.println("Mi a pálya neve?: ");
		String helyszin = sc.next().trim();
		System.out.println("Melyik országban található a pálya?: ");
		String orszag = sc.next().trim();
		System.out.println("Melyik napon lesz a futam?: ");
		String futamnapja = sc.next().trim();
		System.out.println("Milyen hosszú a pálya?: ");
		int palyahossz = sc.nextInt();
		
		String sqlp="insert into palyak values("+id+",'"+helyszin+"','"+orszag+"', to_date('"+futamnapja+"', 'yyyy.mm.dd'), "+palyahossz+")";
		//System.out.println(sqlp);
		try {
			
			s=conn.createStatement();
			s.executeUpdate(sqlp);
			
			System.out.println("Pálya felvéve\n");
			Menu();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}
		}
		
		if(tabla.equals("versenyzok")) {
			
			
			System.out.println("Kérem a rajtszámát: ");
			int rajtszam= sc.nextInt();
			System.out.println("Hogy hívják a pilótát?: ");
			String nev = sc.next().trim();
			System.out.println("Mennyi futamot teljesített már (nem lehet nulla) ?: ");
			int futamok = sc.nextInt();
			System.out.println("Hány futamot nyert meg?: ");
			int gyozelmek = sc.nextInt();
			System.out.println("Mikor debütált?: ");
			String debutalas = sc.next().trim();
			System.out.println("Melyik csapatban versenyzik?: ");
			String csapatnev = sc.next().trim();
			
			String sqlp="insert into versenyzok values("+rajtszam+",' "+nev+"', "+futamok+", "+gyozelmek+", to_date('"+debutalas+"', 'yyyy.mm.dd'), '"+csapatnev+"')";
			
			//System.out.println(sqlp);
			try {
				
				s=conn.createStatement();
				s.executeUpdate(sqlp);
				
				System.out.println("Versenyző felvéve\n");
				Menu();
			}catch(Exception ex) {
				System.err.println(ex.getMessage());
			}
		}
		
		if(tabla.equals("csapat")) {
		
		System.out.println("Kérem a csapat nevét: ");
		String csapatnev = sc.next().trim();
		System.out.println("Hány futamon vettek részt??: ");
		int futamokszama = sc.nextInt();
		System.out.println("Hány futamot nyertek meg?: ");
		int gyozelmek = sc.nextInt();
		System.out.println("Hányszor álltak a csapat versenyzői a pódiumon?: ");
		int podiumok = sc.nextInt();
		System.out.println("Hányszor nyert a csapat világbajnoki címet?: ");
		int bajnoksagok = sc.nextInt();
		System.out.println("Mikor debütált a csapat?: ");
		String debutalas = sc.next().trim();
		
		
		String sqlp="insert into csapat values('"+csapatnev+"', "+futamokszama+", "+gyozelmek+", "+podiumok+", "+bajnoksagok+", to_date('"+debutalas+"', 'yyyy.mm.dd'))";
	//	System.out.println(sqlp);
		try {
			
			s=conn.createStatement();
			s.executeUpdate(sqlp);
			
			System.out.println("Csapat felvéve\n");
			Menu();
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
		}}} 
		
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
					"insert into csapat values('Mercedes',251,124,265,8,to_date('1954.07.04', 'yyyy.mm.dd'))",
					"insert into csapat values('Red_Bull',328,76,207,4,to_date('2005.03.06', 'yyyy.mm.dd'))",
					"insert into csapat values('Ferrari',1034,238,777,16,to_date('1950.05.21', 'yyyy.mm.dd'))",
					"insert into csapat values('McLaren',908,183,493,8,to_date('1966.05.22', 'yyyy.mm.dd'))",
					"insert into csapat values('Alpine',24,1,2,0,to_date('2021.03.28', 'yyyy.mm.dd'))",
					"insert into csapat values('Alpha_Tauri',41,1,2,0,to_date('2020.07.05', 'yyyy.mm.dd'))",
					"insert into csapat values('Aston_Martin',30,0,1,0,to_date('1959.05.31', 'yyyy.mm.dd'))",
					"insert into csapat values('Williams',772,114,313,9,to_date('1977.05.08', 'yyyy.mm.dd'))",
					"insert into csapat values('Alfa_Romeo',172,10,26,0,to_date('1950.05.13', 'yyyy.mm.dd'))",
					"insert into csapat values('Haas',124,0,0,0,to_date('2016.03.20', 'yyyy.mm.dd'))",

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
			"insert into versenyzok values(1,'Max Verstappen',143,20,to_date('2015.03.15', 'yyyy.mm.dd'),'Red_Bull')",
			"insert into versenyzok values(11,'Sergio Pérez',219,2,to_date('2011.03.27', 'yyyy.mm.dd'),'Red_Bull')",
			"insert into versenyzok values(16,'Charles Leclerc',83,3,to_date('2018.03.25', 'yyyy.mm.dd'),'Ferrari')",
			"insert into versenyzok values(55,'Carlos Sainz Jr.',143,0,to_date('2015.03.15', 'yyyy.mm.dd'),'Ferrari')",
			"insert into versenyzok values(3,'Daniel Ricciardo',212,8,to_date('2011.07.11', 'yyyy.mm.dd'),'McLaren')",
			"insert into versenyzok values(4,'Lando Norris',62,0,to_date('2019.03.17', 'yyyy.mm.dd'),'McLaren')",
			"insert into versenyzok values(14,'Fernando Alonso',338,32,to_date('2001.03.04', 'yyyy.mm.dd'),'Alpine')",
			"insert into versenyzok values(31,'Esteban Ocon',91,1,to_date('2016.08.28', 'yyyy.mm.dd'),'Alpine')",
			"insert into versenyzok values(10,'Pierre Gasly',88,1,to_date('2017.10.01', 'yyyy.mm.dd'),'Alpha_Tauri')",
			"insert into versenyzok values(22,'Cunoda Júki',24,0,to_date('2021.03.28', 'yyyy.mm.dd'),'Alpha_Tauri')",
			"insert into versenyzok values(5,'Sebastian Vettel',280,53,to_date('2007.06.17', 'yyyy.mm.dd'),'Aston_Martin')",
			"insert into versenyzok values(18,'Lance Stroll',103,0,to_date('2017.03.27', 'yyyy.mm.dd'),'Aston_Martin')",
			"insert into versenyzok values(6,'Nicholas Latifi',41,0,to_date('2020.07.05', 'yyyy.mm.dd'),'Williams')",
			"insert into versenyzok values(23,'Alexander Albon',40,0,to_date('2019.03.19', 'yyyy.mm.dd'),'Williams')",
			"insert into versenyzok values(24,'Csou Kuan-jü',2,0,to_date('2022.03.20', 'yyyy.mm.dd'),'Alfa_Romeo')",
			"insert into versenyzok values(77,'Valtteri Bottas',181,10,to_date('2013.03.17', 'yyyy.mm.dd'),'Alfa_Romeo')",
			"insert into versenyzok values(47,'Mick Schumacher',24,0,to_date('2021.03.28', 'yyyy.mm.dd'),'Haas')",
			"insert into versenyzok values(20,'Kevin Magnussen',122,0,to_date('2014.03.16', 'yyyy.mm.dd'),'Haas')",



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
		System.out.println("Adja meg melyik táblából szeretne lekérdezni: (versenyzok, csapat vagy palyak) ");
		String tabla = sc.next().trim();
		
		if(tabla.equals("versenyzok")) {
		System.out.println("Adja meg melyik mezőt szeretné lekérdezni: (rajtszam, nev, futamok, gyozelmek, debutalas vagy csapatnev");
		String mezo = sc.next().trim();
	
		String sqlp = "select "+mezo+" from versenyzok";
		if(conn != null) {
			try {
				s=conn.createStatement();
				s.executeQuery(sqlp);
				rs=s.getResultSet();
				while(rs.next()) {
					
					if(mezo.equals("rajtszam")) {String nev = rs.getString("rajtszam");
						System.out.println("Adatok: "+nev);} 
					if(mezo.equals("nev")) {String nev = rs.getString("nev");
					System.out.println("Adatok: "+nev);} 
					if(mezo.equals("futamok")) {String nev = rs.getString("futamok");
					System.out.println("Adatok: "+nev);} 
					if(mezo.equals("gyozelmek")) {String nev = rs.getString("gyozelmek");
					System.out.println("Adatok: "+nev);} 
					if(mezo.equals("debutalas")) {String nev = rs.getString("debutalas");
					System.out.println("Adatok: "+nev);} 
					if(mezo.equals("csapatnev")) {String nev = rs.getString("csapatnev");
					System.out.println("Adatok: "+nev);} 
				
				}
				rs.close();
				Menu();
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}}	
		
		if(tabla.equals("palyak")) {
			System.out.println("Adja meg melyik mezőt szeretné lekérdezni: (id, helyszin, orszag, futamnapja, palyahossz");
			String mezo = sc.next().trim();
		
			String sqlp = "select "+mezo+" from palyak";
			if(conn != null) {
				try {
					s=conn.createStatement();
					s.executeQuery(sqlp);
					rs=s.getResultSet();
					while(rs.next()) {
						if(mezo.equals("id")) {String nev = rs.getString("id");
						System.out.println("Adatok: "+nev);} 
						if(mezo.equals("helyszin")) {String nev = rs.getString("helyszin");
						System.out.println("Adatok: "+nev);} 
						if(mezo.equals("orszag")) {String nev = rs.getString("orszag");
						System.out.println("Adatok: "+nev);} 
						if(mezo.equals("futamnapja")) {String nev = rs.getString("futamnapja");
						System.out.println("Adatok: "+nev);} 
						if(mezo.equals("palyahossz")) {String nev = rs.getString("palyahossz");
						System.out.println("Adatok: "+nev);}
					}
					rs.close();
					Menu();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}}	
		
		if(tabla.equals("csapat")) {
			System.out.println("Adja meg melyik mezőt szeretné lekérdezni: (csapatnev, futamokszama, gyozelmek, podiumok, bajnoksagok vagy debutalas");
			String mezo = sc.next().trim();
		
			String sqlp = "select "+mezo+" from csapat";
			System.out.println(sqlp);
			if(conn != null) {
				try {
					s=conn.createStatement();
					s.executeQuery(sqlp);
					rs=s.getResultSet();
					while(rs.next()) {
						if(mezo.equals("csapatnev")) {String nev = rs.getString("csapatnev");
						System.out.println("Adatok: "+nev);} 
						if(mezo.equals("futamokszama")) {String nev = rs.getString("futamokszama");
						System.out.println("Adatok: "+nev);} 
						if(mezo.equals("gyozelmek")) {String nev = rs.getString("gyozelmek");
						System.out.println("Adatok: "+nev);} 
						if(mezo.equals("podiumok")) {String nev = rs.getString("podiumok");
						System.out.println("Adatok: "+nev);}
						if(mezo.equals("bajnoksagok")) {String nev = rs.getString("bajnoksagok");
						System.out.println("Adatok: "+nev);}
						if(mezo.equals("debutalas")) {String nev = rs.getString("debutalas");
						System.out.println("Adatok: "+nev);}
					}
					rs.close();
					Menu();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}}	
	}
	
	

	public static void DinamikusAdattorles() {
		System.out.println("Melyik táblából töröljük ki a sorokat? (versenyzok, csapat vagy palyak)");
		String torles = sc.next().trim();
		
		if(torles.equals("versenyzok")) {
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
				Menu();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
				DinamikusAdattorles();
			}
		}}
		
		if(torles.equals("palyak")) {
			System.out.println("Melyik pályát töröljük az adatbázisból?: (Alap esetben az azonosítók száma: 1-22 ");
			String id = sc.next();
			String user = null;
			String sqlp = "delete from palyak where id=?";
			if (conn != null) {
				try {
					ps = conn.prepareStatement(sqlp);
					ps.setString(1, id);
					ps.executeUpdate();
					ps.close();
					System.out.println(id + " azonosítójú pálya törölve\n");
					Menu();
				} catch (Exception ex) {
					System.err.println(ex.getMessage());
					DinamikusAdattorles();
				}
			}}
		
		if(torles.equals("csapat")) {
			System.out.println("Melyik csapatot töröljük az adatbázisból? (A csapat kitörlése a versenyzők táblára is hatni fog) : ");
			String csapatnev = sc.next();
			String user = null;
			String sqlp2 = "delete from csapat where csapatnev='"+csapatnev+"'";
			String sqlp1 = "delete from versenyzok where csapatnev='"+csapatnev+"'";
			if (conn != null) {
				try {
				/*	ps = conn.prepareStatement(sqlp);
					ps.setString(1, csapatnev);
					ps.executeUpdate();*/
					
					s=conn.createStatement();
					s.executeUpdate(sqlp1);
					s.executeUpdate(sqlp2);
					
					ps.close();
					System.out.println(csapatnev + " nevű csapat törölve\n");
					Menu();
				} catch (Exception ex) {
					System.err.println(ex.getMessage());
				//	DinamikusAdattorles();
					Menu();
				}
			}}
	}
	
	
	
	
	
	public static void StatikusLekerdezes() {
		System.out.println("Add meg a táblát, amelynek szeretnéd az adatait látni? (versenyzok, csapat vagy palyak):");
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
					Menu();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
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
					Menu();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}else if(tabla.equals("csapat")){
			if(conn != null) {
				String sqlp="select * from csapat";
				System.out.println("Csapatnev,                  FutamokSzama, Gyozelmek, Podiumok, Bajnoki Címek, Debutálás");
				System.out.println("--------------------------------------------------------------");
				try {
					s=conn.createStatement();
					s.executeQuery(sqlp);
					rs=s.getResultSet();
					while(rs.next()) {
							String csapatNev=rs.getString("csapatNev");
							int futamokSzama = rs.getInt("futamokszama");
							int gyozelmek = rs.getInt("gyozelmek");
							int podiumok = rs.getInt("podiumok");
							int bajnoksagok = rs.getInt("bajnoksagok");
							String debutalas = rs.getString("debutalas");
							
							System.out.println(csapatNev+"      "+futamokSzama+"             "+gyozelmek+"       "+podiumok+"   "+bajnoksagok+ "   "+debutalas);
					}
					rs.close();
					Menu();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}else {
			System.out.println("Nem megfelelő táblát adott meg. Kérem próbálja újra");
			StatikusLekerdezes();
		}
	}
	
	
	public static void ModosithatoKurzor() {
		System.out.println("Melyik csapat adatait duplázzuk meg? ");
		String csapatnev = sc.next().trim();
		System.out.println("A "+csapatnev+ " csapat melyik adatát duplázzuk? (futamokszama, gyozelmek, podiumok, bajnoksagok) ");
		String adat = sc.next().trim();
		String sqlp = "select "+adat+" from csapat where csapatnev = '"+ csapatnev +"'";
		if (conn != null) {
			try {
				s = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
				rs = s.executeQuery(sqlp);
				while (rs.next()) {
					int regiadatok = rs.getInt(adat);
					rs.updateInt(adat, (regiadatok*2));
					rs.updateRow();
					System.out.printf("Az futamok száma a módosítás után: %d \n",regiadatok*2);
					
				}Menu();
			} catch (Exception ex) {
				System.err.println(ex.getMessage());
				ModosithatoKurzor();
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
	
	
	public static void DinamikusAdatlekerdezes2() {
		System.out.println("Add meg a táblát, amelynek szeretnéd az adatait látni? (versenyzok, csapat vagy palyak):");
		String tabla = sc.next().trim();
		
		if(tabla.equals("versenyzok")) {
			System.out.println("Add meg a pilóta azonsítóját");
			String adat = sc.next().trim();
			if(conn != null) {
				String sqlp="select * from versenyzok where rajtszam="+adat;
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
					Menu();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
					DinamikusAdatlekerdezes2();
				}
			}
		}else if(tabla.equals("palyak")) {
			System.out.println("Add meg a pálya azonsítóját");
			String adat = sc.next().trim();
			if(conn != null) {
				String sqlp="select * from palyak where id="+adat;
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
					Menu();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
					DinamikusAdatlekerdezes2();
				}
			}
		}else if(tabla.equals("csapat")){
			System.out.println("Add meg a csapat nevét");
			String adat = sc.next().trim();
			if(conn != null) {
				String sqlp="select * from csapat where csapatnev ='"+adat+"'";
				System.out.println("Csapatnev,                  FutamokSzama, Gyozelmek, Podiumok, Bajnoki Címek, Debutálás");
				System.out.println("--------------------------------------------------------------");
				try {
					s=conn.createStatement();
					s.executeQuery(sqlp);
					rs=s.getResultSet();
					while(rs.next()) {
							String csapatNev=rs.getString("csapatNev");
							int futamokSzama = rs.getInt("futamokszama");
							int gyozelmek = rs.getInt("gyozelmek");
							int podiumok = rs.getInt("podiumok");
							int bajnoksagok = rs.getInt("bajnoksagok");
							String debutalas = rs.getString("debutalas");
							
							System.out.println(csapatNev+"      "+futamokSzama+"             "+gyozelmek+"       "+podiumok+"   "+bajnoksagok+ "   "+debutalas);
					}
					Menu();
					rs.close();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
					DinamikusAdatlekerdezes2();
				}
			}
		}else {
			System.out.println("Nem megfelelő táblát adott meg. Kérem próbálja újra");
			DinamikusAdatlekerdezes2();
		}
	}
}