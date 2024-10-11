import java.util.HashMap;

public class Id_Passwords {
	static HashMap<String,String> logininfo = new HashMap<String,String>();
	Id_Passwords() {
	}
	public static void addUser(String username,String password) {
		logininfo.put(username, password);
	}
	public static void removeUser(String username) {
		logininfo.remove(username);
	}
	public static HashMap getLoginInfo(){
		return logininfo;
	}
}
