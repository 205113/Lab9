package it.polito.tdp.porto.model;

import org.jgrapht.graph.DefaultEdge;

public class Articolo extends DefaultEdge{

	private long eprintid;
	private int year;
	private String title;
	
	public Articolo(long enprintid, int year, String title) {
		this.eprintid = enprintid;
		this.year = year;
		this.title = title;
	}

	public long getEprintid() {
		return eprintid;
	}

	public void setEnprintid(int enprintid) {
		this.eprintid = enprintid;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (eprintid ^ (eprintid >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articolo other = (Articolo) obj;
		if (eprintid != other.eprintid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.title;
	}
	
}
