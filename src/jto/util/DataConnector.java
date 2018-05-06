package jto.util;





import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class DataConnector {



	private static java.sql.Connection connection;
	private static DataConnector pgc = new DataConnector( );
	private DataConnector(){ }
	public static DataConnector getInstance( ) {      return pgc;}
	public static java.sql.Connection getConnection(){ 
		try {
			if(connection==null || connection.isClosed()){ connection=init();}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return connection;
	}
	
	private static java.sql.Connection init(){
		connection = null;
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException "+e+"<br />");
			System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!<br />");
		}
		try {	
			connection = DriverManager.getConnection(
		
					"jdbc:postgresql://127.0.0.1:5432/lh_article", 
					"postgres",
					"Saturn5");
			
		
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console<br />");
			e.printStackTrace();
		}
		if (connection == null) {
			System.out.println("Failed to make connection!\n");
		}
		return connection;
	}
	 public static boolean checkConnection() {
		 boolean did_succeed = false;
		 //int count_records = 0;
		 StringBuilder sb = new StringBuilder();
		 sb.append("select count(a_id) how_many from article");
		 try {
			connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(sb.toString());
			ResultSet  rs = stmt.executeQuery();
		
			while(rs.next()) {
				did_succeed=true;
				rs.getInt("how_many");
			}
				connection.close();
				connection=null;
			} catch(Exception e) { 			
				e.printStackTrace();
			}
			
		 
		 return did_succeed;
	 }
	 
	 public static boolean checkIfArticleAlreadySaved(String article_url) {
		 boolean already_exist = false;

		 StringBuilder sb = new StringBuilder();
		 sb.append("select a_id from article where url in (?)");
		 try {
			connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(sb.toString());
			stmt.setString(1, article_url);
			ResultSet  rs = stmt.executeQuery();
		
			while(rs.next()) {
				already_exist=true;
				rs.getInt("a_id");
			}
				connection.close();
				connection=null;
			} catch(Exception e) { 			
				e.printStackTrace();
			}
			
		 
		 return already_exist;
	 }
	
	 
	 public static boolean saveArticle(String uri, String author, java.sql.Date d, String file_name) {
		 boolean b = false;
		 try {
			 /*
			  * create table article (	a_id serial primary key,
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
			StringBuffer sb = new StringBuffer();
			sb.append("insert into article (URL,saved_file_name,author_1,date_written, date_saved) values (?,?,?,?, current_date)");
			connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(sb.toString());
			stmt.setString(1, uri);
			stmt.setString(2, file_name);
			stmt.setString(3, author);
			stmt.setDate(4, d);
			stmt.execute();
			b = (stmt.getUpdateCount()>0);
			
				connection.close();
				connection=null;
			} catch(Exception e) { 			
				b=false;
				e.printStackTrace();
				
			}
		 
		 return b;
	 }
	
	
	/*
	public static java.util.LinkedHashMap<String , rgr.ent.Realtor> LoadActiveRealtors(){
		java.util.LinkedHashMap<String , rgr.ent.Realtor> realtors = new java.util.LinkedHashMap<String , rgr.ent.Realtor>();
		try {
			StringBuffer sb = new StringBuffer();
				
			sb.append("SELECT m_id, first_name, last_name, email, role, admin_level, ");
			sb.append(" CASE WHEN assigned_rgr_visitor.real_id_fk is NULL ");
			sb.append(" THEN ");
			sb.append(" '0' ");
			sb.append(" ELSE ");
			sb.append(" assigned_rgr_visitor.real_id_fk ");
			sb.append(" END as realtor_id ");
			sb.append(" FROM member ");
			sb.append(" LEFT JOIN assigned_rgr_visitor  ON m_ID = vis_id_fk ");
			sb.append(" where admin_level in (3,2,1) and visible='t' ");
			sb.append(" order by realtor_id desc ");
			
			
			connection = getConnection();
			PreparedStatement stmt = connection.prepareStatement(sb.toString());
			ResultSet  rs = stmt.executeQuery();
			rgr.ent.Realtor realt = null;
			while(rs.next()) {
				realt = new rgr.ent.Realtor();
				realt.setId(rs.getInt("m_id"));
				realt.setString_field("first_name",rs.getString("first_name")); 
				realt.setString_field("last_name",rs.getString("last_name")); 
				realt.setString_field("user_email",rs.getString("email")); 
				realt.setInt_field("admin_level",rs.getInt("admin_level")); 
				realtors.put(realt.getStringValue("user_email"), realt);
			}
			connection.close();
			connection=null;
		} catch(Exception e) { 			
			e.printStackTrace();
		}
		return realtors;
	}
	*/

/*
	
	public static void saveSearchCriteria(rgr.util.SearchCriteria criteria, int member_id, String refer_address){
		try{
			connection = getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("insert into visitor_search (");
			sb.append("visitor_id,listed_days_max, listed_days_min, ");
			sb.append("min_bed,max_bed,min_bath,max_bath,min_sqft_inside,");  
			sb.append("min_prc,max_prc, ne_lat,ne_long,sw_lat,sw_long, ");  
			sb.append("refer_address, cities, neighborhoods, property_class");  
			sb.append(") values (");
	
			

			
			sb.append("?,?,?,");   // integer 3
			
			sb.append("?,?,?,?,?,");  // integer 5
			sb.append("?,?,?,?,?,?,");  //Decimals-6
			sb.append("?,?,?");  //Strings-5
			sb.append(")");
			PreparedStatement stmt = connection.prepareStatement(sb.toString());
			
	
			stmt.setInt(1,member_id); 
			stmt.setInt(2,criteria.getIntValue("listed_days_max"));
			stmt.setInt(3,criteria.getIntValue("listed_days_mmin"));
			
			stmt.setInt(4,criteria.getIntValue("min_bed"));  // int 5
			stmt.setInt(5,criteria.getIntValue("max_bed"));
			stmt.setInt(6,criteria.getIntValue("min_bath"));
			stmt.setInt(7,criteria.getIntValue("max_bath"));
			stmt.setInt(8,criteria.getIntValue("min_sqft_inside"));
			
			stmt.setBigDecimal(9,criteria.getNumericValue("min_prc"));  // decimal 6
			stmt.setBigDecimal(10,criteria.getNumericValue("max_prc"));
			stmt.setBigDecimal(11,criteria.getNumericValue("ne_lat"));
			stmt.setBigDecimal(12,criteria.getNumericValue("ne_long"));
			stmt.setBigDecimal(13,criteria.getNumericValue("sw_lat"));
			stmt.setBigDecimal(14,criteria.getNumericValue("sw_long")); 
			
			stmt.setString(15, refer_address);
			stmt.setString(16, criteria.getArrayToStore(criteria.getArea_selectors()));	// area_arr
			stmt.setString(17, criteria.getArrayToStore(criteria.getKeyword_Selectors())); //	 keywords
			stmt.setString(18, criteria.getArrayToStore(criteria.getPropClassSelectors())); 
		// date_time

	
			stmt.executeUpdate(); 
	
			connection.close();
			connection=null;
		} catch(Exception e) { 			
			e.printStackTrace();
		}
	
	}
	*/
	/*
	
	public static void updateMemberPass(rgr.ent.NewUser u){
		try{
			String q = "update Member set pass_hash=? where m_id=? ";  
			connection = getConnection();		
			PreparedStatement prepstmt = connection.prepareStatement(q); 	
			prepstmt.setString(1, u.getStringValue("pass_hash"));	
			prepstmt.setInt(2, u.getId());
			prepstmt.executeUpdate();   
			connection.close();
			connection=null;
		} catch(Exception e) { 			
			e.printStackTrace();
		}
	}
*/

	
}

