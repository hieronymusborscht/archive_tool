package jto.ent;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ArticleSaver {
	
	private java.util.TreeMap<Integer, Article> article_list;
	private java.util.TreeMap<String, String> string_variables;
	private java.util.TreeMap<String, java.sql.Date> date_variables;
	
	public ArticleSaver(){
		string_variables = new java.util.TreeMap<String, String> ();
		//date_variables = new java.util.TreeMap<String, java.sql.Date>();
	}
	
	public boolean checkIfSavedAlready(String uri_t) {
		boolean does_exist_already = false;
		does_exist_already = jto.util.DataConnector.checkIfArticleAlreadySaved(uri_t);
		return does_exist_already;
	}
	
	public boolean saveArticle(String uri, String author, String date, String file_name) {
		boolean did_it_save = false;
		java.sql.Date s_d =  setDate(date);
		if(s_d!=null && uri!=null && author!=null && file_name!=null) {
			did_it_save = jto.util.DataConnector.saveArticle(uri, author, s_d, file_name);
		}
		return did_it_save;
	}
	private java.sql.Date setDate(String s) {
		Date d = null;
		java.sql.Date sqlDate=null;
		try {
			d = new SimpleDateFormat("dd-MMM-yyyy").parse(s);
			sqlDate = new java.sql.Date(d.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
	public void setString(String key, String value) {
		if(string_variables.containsKey(key)){
			string_variables.put(key, value);
		}
	}
	
}
