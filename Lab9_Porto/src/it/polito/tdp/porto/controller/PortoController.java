package it.polito.tdp.porto.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Articolo;
import it.polito.tdp.porto.model.Autore;
import it.polito.tdp.porto.model.ModelloPorto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	private ModelloPorto model;
	private boolean generato=false;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Autore> PrimoAutore;

    @FXML
    private ComboBox<Autore> SecondoAutore;

    @FXML
    private Button Coautori;

    @FXML
    private Button Cluster;

    @FXML
    private Button Articoli;

    @FXML
    private TextArea Risultato;



    @FXML
    void TrovaArticoli(ActionEvent event) {
    	if(!generato){
    		model.genera();
    		generato=true;
    	}
    	if((PrimoAutore.getValue().getGiven_name().compareTo("Nessun")==0)||(SecondoAutore.getValue().getGiven_name().compareTo("Nessun")==0))
    		Risultato.setText("Devi selezionare due autori");
    		
    		else{
    	List<Articolo> articoli= model.articoli(PrimoAutore.getValue(),SecondoAutore.getValue());
    	VisualizzaArticoli(articoli);
    		}

    }

    @FXML
    void TrovaCluster(ActionEvent event) {
    	if((PrimoAutore.getValue().getGiven_name().compareTo("Nessun")==0)&&(SecondoAutore.getValue().getGiven_name().compareTo("Nessun")==0))
    	{   if(!generato){
    			model.genera();
    			generato=true;
    	}
    		//manca parte, cos'è cluster?
    		Risultato.setText("Non implementato");
    	}
    	else
    		Risultato.setText("Non devi selezionare autori");
    	 
    }
    
    private void VisualizzaArticoli(List<Articolo> elementi){
    	String s="";
    	for(Articolo elemento : elementi)
    		s+=elemento.toString()+"\n";
    	Risultato.setText(s);
    }
    
    private void VisualizzaAutori(List<Autore> elementi){
    	String s="";
    	for(Autore elemento : elementi)
    		s+=elemento.toString()+"\n";
    	Risultato.setText(s);
    }

    @FXML
    void TrovaCoautori(ActionEvent event) {
    	if(!generato){
    		model.genera();
    		generato=true;
    	}
    	Autore a;
    	if(PrimoAutore.getValue().getGiven_name().compareTo("Nessun")==0){
    		if(SecondoAutore.getValue().getGiven_name().compareTo("Nessun")==0){
    			Risultato.setText("Non hai selezionato alcun autore");
    			return;
    		}else
    			a=SecondoAutore.getValue();
    	}else
    		a=PrimoAutore.getValue();
    	List<Autore> coautori=model.coautori(a);
    	VisualizzaAutori(coautori);
    }

    @FXML
    void initialize() {
        assert PrimoAutore != null : "fx:id=\"PrimoAutore\" was not injected: check your FXML file 'Porto.fxml'.";
        assert SecondoAutore != null : "fx:id=\"SecondoAutore\" was not injected: check your FXML file 'Porto.fxml'.";
        assert Coautori != null : "fx:id=\"Coautori\" was not injected: check your FXML file 'Porto.fxml'.";
        assert Cluster != null : "fx:id=\"Cluster\" was not injected: check your FXML file 'Porto.fxml'.";
        assert Articoli != null : "fx:id=\"Articoli\" was not injected: check your FXML file 'Porto.fxml'.";
        assert Risultato != null : "fx:id=\"Risultato\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setModel(ModelloPorto m) {
		this.model=m;
		Autore primo= new Autore(0,"Autore","Nessun");
		PrimoAutore.getItems().add(primo);
		SecondoAutore.getItems().add(primo);
		PrimoAutore.setValue(primo);
		SecondoAutore.setValue(primo);
		PrimoAutore.getItems().addAll(model.autori());
		SecondoAutore.getItems().addAll(model.autori());
	}
}