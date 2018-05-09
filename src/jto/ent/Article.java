package jto.ent;

import java.sql.Date;

import java.util.TreeMap;

public class Article {
	private TreeMap<String, java.sql.Date> date_values;
	private TreeMap<String, String> string_values;
	private TreeMap<String, Integer> int_values;
	private boolean is_printed;
	private Date d;
	/*
create table article (	a_id serial primary key,
	URL varchar(256) not null default '',
	saved_file_name varchar(256) not null default '',
	date_written date not null default '1970-01-20',
	date_saved date not null default '1970-01-20',
	author_1 varchar(256) not null default '',
	author_2 varchar(256) not null default '',
	author_3 varchar(256) not null default '',
	case_name varchar(256) not null default '',
	is_printed boolean not null default false,
	case_id integer not null default '0')
*/
//	private java.sql.Date date_written
	
	
	public Article() {
		d = jto.util.DateUtil.makeSqlDate("20-Jan-1970");
			//SimpleDateFormat("dd-MMM-yyyy");
		date_values = new TreeMap<String, Date>();
		string_values = new TreeMap<String, String>();
		is_printed = false;
		int_values = new  TreeMap<String, Integer> ();
	
		
		int_values.put("a_id",0); 
		string_values.put("url", "");
		string_values.put("saved_file_name","");
		
		string_values.put("author_1","");
		date_values.put("date_written",d);
		
		
	}
	public void setDateValue(String key , Date value) {
		if(date_values.containsKey(key)){
			date_values.put(key, value);
		}
	}

	
	
	public void setStringValue(String key, String value) {
		if(string_values.containsKey(key)){
			string_values.put(key, value);
		}
	}
	public String setStringValue(String key) {
		if(string_values.containsKey(key)){
			return string_values.get(key);
		}else {
			return "";
		}
	}
	
	
	public void setIntValue(String key, int value) {
		if(int_values.containsKey(key)) {
			int_values.put(key, value);
		}
	}
	public int getIntValue(String key) {
		if(int_values.containsKey(key)) {
			return int_values.get(key);
		}else {
			return 0;
		}
	}
	public boolean getIs_printed() {
		return is_printed;
	}
	public void setIs_printed(boolean is_printed) {
		this.is_printed = is_printed;
	}
	
	 public Date getDateValue(String key) {
		 if(date_values.containsKey(key)) {
			 return date_values.get(key);
		 }else {
			 return d;
		 }
	 }
	public String getDateValueFormatted(String key) {
			if(date_values.containsKey(key)){
				return jto.util.DateUtil.makeString(date_values.get(key));
			}else {
				return jto.util.DateUtil.makeString(d);
			}
	}
	 
		//private TreeMap<String, String> string_values;
		//private TreeMap<String, Integer> int_values;
	
	public String getCSVRow() {
		StringBuilder sb = new StringBuilder();
		sb.append("\"");
		sb.append(int_values.get("a_id"));
		sb.append("\",\"");
		sb.append(string_values.get("url"));
		sb.append("\",\"");
		sb.append(string_values.get("saved_file_name"));
		sb.append("\",\"");
		sb.append(getDateValueFormatted("date_written"));
		sb.append("\",\"");
		sb.append(string_values.get("author_1"));
		sb.append("\"\n");
		
		
		return sb.toString();
	}
	public String getCSVHeader() {
		
				StringBuilder sb = new StringBuilder();
				sb.append("\"");
				sb.append("a_id");
				sb.append("\",\"");
				sb.append("url");
				sb.append("\",\"");
				sb.append("saved_file_name");
				sb.append("\",\"");
				sb.append("date_written");
				sb.append("\",\"");
				sb.append("author_1");
				sb.append("\"\n");
				
				
				return sb.toString();
			}

	
}
