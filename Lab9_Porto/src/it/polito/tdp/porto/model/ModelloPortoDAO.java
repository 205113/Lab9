package it.polito.tdp.porto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ModelloPortoDAO {

	public List<Autore> autori() {
		List<Autore> autori= new ArrayList<Autore>();
		// si connette a Db e prende gli autori
		Connessione c= new Connessione("jdbc:mysql://localhost/porto?user=root");
		Connection conn= c.connetti();
		String sql="";
		try {
				sql="SELECT * FROM creator";
				PreparedStatement s= conn.prepareStatement(sql);
				ResultSet rs= s.executeQuery();
				while(rs.next()){
					Autore a= new Autore(rs.getInt("id_creator"),rs.getString("family_name"),rs.getString("given_name"));
					autori.add(a);
				}
				
			conn.close();
			rs.close();
			return autori;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return null;
	}

	public List<Articolo> articoli() {
		List<Articolo> articoli= new ArrayList<Articolo>();
		// si connette a Db e prende gli autori
		Connessione c= new Connessione("jdbc:mysql://localhost/porto?user=root");
		Connection conn= c.connetti();
		String sql="";
		try {
				sql="SELECT * FROM article";
				PreparedStatement s= conn.prepareStatement(sql);
				ResultSet rs= s.executeQuery();
				while(rs.next()){
					Articolo a= new Articolo(rs.getLong("eprintid"),rs.getInt("year"),rs.getString("title"));
					articoli.add(a);
				}
				
			conn.close();
			rs.close();
			return articoli;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return null;
	}
		
	

	public boolean coautori(Articolo a, Autore a1, Autore a2) {
		boolean trovato= false;
		// si connette a Db e controlla se sono coautori
		Connessione c= new Connessione("jdbc:mysql://localhost/porto?user=root");
		Connection conn= c.connetti();
		String sql="";
		List<Integer> id= new ArrayList<Integer>();
		try {
				sql="SELECT id_creator FROM authorship WHERE eprintid = ?";
				PreparedStatement s= conn.prepareStatement(sql);
				s.setLong(1, a.getEprintid());
				ResultSet rs= s.executeQuery();
				while(rs.next()){
					id.add(rs.getInt("id_creator"));
				}
				if(id.contains(a1.getId_creator())&&id.contains(a2.getId_creator()))
					trovato=true;
				
			conn.close();
			rs.close();
			return trovato;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
