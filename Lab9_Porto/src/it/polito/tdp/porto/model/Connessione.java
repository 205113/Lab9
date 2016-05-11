package it.polito.tdp.porto.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Connessione {
	private String url;
	private static ComboPooledDataSource pool;

	public Connessione(String url) {
		this.url = url;
	}
	
	public Connection connetti(){
		try {
			if(pool==null){
				pool= new ComboPooledDataSource();
				pool.setJdbcUrl(url);
			}
			return pool.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void chiudi(){
		pool.close();
		pool=null;
	}
	
}
