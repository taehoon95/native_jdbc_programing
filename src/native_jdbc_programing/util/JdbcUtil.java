package native_jdbc_programing.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
	
	// resources 안의 db.properties내용을 받아온다.
	public static Connection getConnection() {
		String propertiesPath = "db.properties";
		Connection con = null;
		try(InputStream in = ClassLoader.getSystemResourceAsStream(propertiesPath)){
			Properties	prop = new Properties();
			prop.load(in);
			con = DriverManager.getConnection(prop.getProperty("url"), prop);
			
			/*
			System.out.println("prop > " + prop);
			for(Entry<Object, Object> e : prop.entrySet()){
				System.out.printf("%s -> %s%n",e.getKey(),e.getValue());
			}
			System.out.println();
			for(Object key : prop.keySet()) {
				System.out.print(key + "->");
				System.out.println(prop.get(key));
			} // hashtable과 관련 주소를 가지고 계산해서 해당하는 value를 가져온다.
			*/
			
		}catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
		
	}
}
