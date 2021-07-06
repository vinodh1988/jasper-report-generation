package com.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {
	
	private final static Connection connection=SqlServerConnection.getConnection();
	public static boolean insert(Person p) {
		try {
			
			PreparedStatement preparedStatement=connection.prepareStatement("insert into person values(?,?,?)");
			preparedStatement.setInt(1, p.getSno());
			preparedStatement.setString(2, p.getName());
			preparedStatement.setString(3, p.getCity());
			preparedStatement.execute();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	} 
	
	public static List<Person> getPeople(){
		
try      {
	        List<Person> l=new ArrayList<Person>();
		    ResultSet rs=connection.createStatement().executeQuery("select * from person");
		    while(rs.next()) {
		    	Person p=new Person();
		    	p.setSno(rs.getInt(1));
		    	p.setName(rs.getString(2));
		    	p.setCity(rs.getString(3));
		    	l.add(p);
		    }
		    
		    return l;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
