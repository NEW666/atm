package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.omg.CORBA.PUBLIC_MEMBER;

public class conDatabase {

	private final String DBDRIVER = "com.mysql.jdbc.Driver";
	private final String DBURL = "jdbc:mysql://localhost:3306/atmdb";
	private final String DBUSER = "root";
	private final String DBPAWD = "960620";

	private Connection conn = null;

	public conDatabase(){

		try{
    		Class.forName(DBDRIVER);
    		this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPAWD);

    	}catch(Exception e){

    		System.out.println("加载驱动失败");

    	}
    }

    public Connection getConnection()
    {
    	return conn;
    }
    public void close()
    {
    	try{
    	    conn.close();
    	}catch(Exception e)
    	{
    		System.out.println("数据库连接失败");

    	}
    }


	}

