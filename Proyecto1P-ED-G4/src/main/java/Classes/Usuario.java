/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import TDAs.DoubleCircularLinkedList;

/**
 *
 * @author Javier
 */
public class Usuario {
    private String correo;
    private String contraseña;
    private DoubleCircularLinkedList<Album> albumes;
    private DoubleCircularLinkedList<Photo> albumFavoritos;
    
    //Constructores

    public Usuario(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }
    //Getters and Setters

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public DoubleCircularLinkedList<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(DoubleCircularLinkedList<Album> albumes) {
        this.albumes = albumes;
    }

    public DoubleCircularLinkedList<Photo> getAlbumFavoritos() {
        return albumFavoritos;
    }

    public void setAlbumFavoritos(DoubleCircularLinkedList<Photo> albumFavoritos) {
        this.albumFavoritos = albumFavoritos;
    }
    
    
    
    //Metodos
    public String toString(){
        return "Usuario: "+getCorreo()+", Contraseña: "+getContraseña();
    }
}
