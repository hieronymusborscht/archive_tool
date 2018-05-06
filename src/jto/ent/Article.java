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
		d = jto.util.DateUtil.makeSqlDate("1970-01-20");
			//SimpleDateFormat("yyyy-MM-dd");
		date_values = new TreeMap<String, Date>();
		string_values = new TreeMap<String, String>();
		is_printed = false;
		int_values = new  TreeMap<String, Integer> ();
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
	public void setIntValue(String key, int value) {
		if(int_values.containsKey(key)) {
			int_values.put(key, value);
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
		//private TreeMap<String, String> string_values;
		//private TreeMap<String, Integer> int_values;
	
	
	
	
}
