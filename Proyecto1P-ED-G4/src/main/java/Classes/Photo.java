/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import TDAs.DoubleCircularLinkedList;
import java.time.LocalDate;
//import TDAs.NodeList;

/**
 *
 * @author Javier
 */
public class Photo {
    String ruta;
    DoubleCircularLinkedList<String> personas;
    String lugar;
    Boolean favorita= false;
    LocalDate fecha;
    String info;
    //DoubleCircularLinkedList<String> palabrasClaves;
    
    
    //getters y setters
    public String getRuta() {
        return ruta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public DoubleCircularLinkedList<String> getPersonas() {
        return personas;
    }

    public void setPersonas(DoubleCircularLinkedList<String> personas) {
        this.personas = personas;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Boolean getFavorita() {
        return favorita;
    }

    public void setFavorita(Boolean favorita) {
        this.favorita = favorita;
    }
    /*
    public DoubleCircularLinkedList<String> getPalabrasClave() {
        return palabrasClaves;
    }

    public void setPalabrasClave(DoubleCircularLinkedList<String> palabrasClaves) {
        this.palabrasClaves = palabrasClaves;
    }
    */
    
    
    //constructor

    public Photo(String ruta, DoubleCircularLinkedList<String> personas, String lugar, String info,int i) {
        this.ruta = ruta;
        this.personas = personas;
        this.lugar = lugar;
        this.fecha=LocalDate.now();
        this.info=info;
        if(i==1){
            this.favorita=true;
        }
    }
    /*
    palabrasClaves
    public Photo(String ruta, DoubleCircularLinkedList<String> personas, String lugar, String info,int i,DoubleCircularLinkedList<String> palabrasClaves) {
        this.ruta = ruta;
        this.personas = personas;
        this.lugar = lugar;
        this.fecha=LocalDate.now();
        this.info=info;
        if(i==1){
            this.favorita=true;
        }
        this.palabrasClaves=palabrasClaves
    }
    */
    
    
    //metodos
    public void agregarPersona(String nuevaPersona){
        personas.addLast(nuevaPersona);
    }
    
    public boolean eliminarPersona(String eliminado){
        for(String p:personas){    //tengo que revisar este error con el iterable
            if(p.equals(eliminado)){
                int i = personas.getIndex(p);
                personas.remove(i);
                return true;
        }
    }
            return false;  //no se encontro dicha persona
     //  return false; 
    }
    
    public void agregarFavoritos(){
        setFavorita(true);
    }
    
    @Override
    public String toString() {
        String s = "";
        
        return this.ruta.substring(0,ruta.length()-4);
    }
    
    public boolean equals(Photo foto2){
            return this.ruta.substring(0,ruta.length()-4).equals(foto2.ruta.substring(0,ruta.length()-4));
        
    }
}
