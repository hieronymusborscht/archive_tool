package jto.ent;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ArticleSaver {
	
	private java.util.LinkedHashMap<Integer, Article> article_list;
	private java.util.TreeMap<String, String> string_variables;
	private java.util.TreeMap<String, java.sql.Date> date_variables;
	
	public ArticleSaver(){
		string_variables = new java.util.TreeMap<String, String> ();
		//date_variables = new java.util.TreeMap<String, java.sql.Date>();
	}
	
	public boolean articlesLoaded() {
		return (article_list!=null && article_list.size()>0);
	}
	
	
	public boolean checkIfSavedAlready(String uri_t) {
		boolean does_exist_already = false;
		does_exist_already = jto.util.DataConnector.checkIfArticleAlreadySaved(removeTrailingSlash(uri_t));
		return does_exist_already;
	}
	
	public int saveArticle(String uri, String author, String date, String file_name) {
		int a_id = 0;
		java.sql.Date s_d =  setDate(date);
		if(s_d!=null && uri!=null && author!=null && file_name!=null) {
			a_id = jto.util.DataConnector.saveArticle(removeTrailingSlash(uri), author, s_d, file_name);
		}
		System.out.println("ArticleSaver.savearticle "+a_id);
		return a_id;
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
	
	public static String removeTrailingSlash(String url) {
		int length_l = url.length();
		//System.out.println(length_l);
		
		--length_l;
		char buff = url.charAt(length_l);
		if(buff == '/')
		{	url = url.substring(0, length_l);
			//System.out.println("ends with a trailing slash" );
		}
		return url;
	}
	
	
	public void loadAllArticles() {
		article_list = jto.util.DataConnector.loadAllArticles();
	}
	public String makeSpreadSheet() {
		StringBuffer sb = new StringBuffer();
		java.util.Set<Integer> article_keys = article_list.keySet();
		java.util.Iterator<Integer> keys_it = article_keys.iterator();
		Article a = null;  int counter_l = 0;
		while(keys_it.hasNext()) {
			
			a =article_list.get(keys_it.next());
			if(counter_l==0) {
				sb.append(a.getCSVHeader());
			}
			sb.append(a.getCSVRow());
			counter_l = counter_l+1;
		}
		return sb.toString();
	}
	
	
}
