package db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbMethods {
	private Statement s = null;
	private java.sql.Connection conn=null;
	private ResultSet rs = null;
	
	public void ReadAllData() {
		int round=0;
		String standing="", sitting="", vip="", x="\t";
		String sqlp="select Round,Standing Price,Sitting Price,VIP Price from Prices;";
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sqlp);
			while(rs.next()) {
				round = rs.getInt("Round");
				standing = rs.getString("Standing Price");
				sitting = rs.getString("Sitting Price");
				vip = rs.getString("VIP Price");
				SM(round+x+standing+x+sitting+x+vip);
			}
			rs.close();
		} catch (SQLException e) {SM(e.getMessage());}
	}
	
	public void Connect() {
		try {
			String url = "jdbc:sqlite:C:/SQLite/database.db";
			conn = DriverManager.getConnection(url);
			SM("Connection OK!");
		}catch (SQLException e) {
			SM("JDBC Connect: "+e.getMessage());
		}
	}
	
	public void DisConnect() {
		try {
			conn.close();
			SM("Disconnection OK!");
		}catch (SQLException e) {SM(e.getMessage());
		}
	}

	public void Reg() {
		try {
			Class.forName("org.sqlite.JDBC");
			SM("Sikeres driver regisztráció!");
		}catch (ClassNotFoundException e){
			SM("Hibás driver regisztráció!"+e.getMessage());
		}
	}
	
	public void SM(String msg) {
		System.out.println(msg);
	}
}
