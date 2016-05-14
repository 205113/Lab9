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
			//for(Autore a1:autori){
				List<Integer>coautori= p.coautori(a);
				for(int i=0;i<coautori.size();i++){
					Autore a1=trovaAutore(coautori.get(i),autori);
					for(int j=0;j<coautori.size();j++){
						Autore a2=trovaAutore(coautori.get(j),autori);
						if(!a1.equals(a2))
							grafo.addEdge(a1, a2, a);
					}
						
				}
				/*for(Autore a2:autori){
					if(p.coautori(a,a1,a2)&& !a1.equals(a2))
						grafo.addEdge(a1, a2,a);
				}
			}*/
		}
	}

	private Autore trovaAutore(Integer id, List<Autore> autori) {
		Autore a=null;
		for(Autore temp:autori){
			if(temp.getId_creator()==id)
				a=temp;
		}
		return a;
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
		//Autori+Articoli scritti da loro?
	}
	
	public List<Autore> autori(){
		ModelloPortoDAO p = new ModelloPortoDAO();
		return p.autori();
	}
	
}
