package db;

public class Program {
	
	static DbMethods dbm = new DbMethods();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		dbm.Reg();
		dbm.Connect();
		dbm.ReadAllData();
		dbm.DisConnect();
	}

}
