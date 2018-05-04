package jto.ent;

public class User {
	
	private java.util.TreeMap<String, String> string_variables;
	private java.util.TreeMap<String, Integer> int_variables;
	
	public User() {
		
		string_variables = new java.util.TreeMap<String, String> ();
		int_variables = new java.util.TreeMap<String, Integer> ();
	}
	
	
	public void setIntValue(String key , int i) {
		if(int_variables.containsKey(key)) {
			int_variables.put(key, i);
		}
	}
	public void setStringValue(String key, String value) {
		if(string_variables.containsKey(key)) {
			string_variables.put(key, value);
		}
	}

	
	public String checkConnection(){
		return " connection :"+ jto.util.DataConnector.checkConnection() ;
	}
}
