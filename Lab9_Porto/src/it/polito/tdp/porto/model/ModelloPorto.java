package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

public class ModelloPorto {
	private UndirectedGraph<Autore,Articolo> grafo;
	
	public void genera(){
		grafo= new Multigraph<Autore,Articolo>(Articolo.class);
		ModelloPortoDAO p= new ModelloPortoDAO();
		List<Autore> autori= p.autori();
		Graphs.addAllVertices(grafo, autori);
		List<Articolo> articoli=p.articoli();
		for(Articolo a:articoli){
			for(Autore a1:autori){
				for(Autore a2:autori){
					if(p.coautori(a,a1,a2))
						grafo.addEdge(a1, a2,a);
				}
			}
		}
	}

	public List<Autore> coautori(Autore a) {
		List<Autore>coautori= new ArrayList<Autore>();
		ModelloPortoDAO p= new ModelloPortoDAO();
		List<Autore> autori=p.autori();
		for(Autore aut:autori){
			if(grafo.containsEdge(a, aut)&& !a.equals(aut))
				coautori.add(aut);
		}
		return coautori;
	}

	public List<Articolo> articoli(Autore a1, Autore a2) {
		Set<Articolo> articoli=grafo.getAllEdges(a1, a2);
		List<Articolo> art= new ArrayList<Articolo>();
		art.addAll(articoli);
		return art;
	}
	
	public void cluster(){
		//Autori+Articoli scritti da loro
	}
	
	
}