/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatos.Proyecto1P;

import Classes.Album;
import static Data.AlbumesData.leerAlbumes;
import static Data.fotosData.leerFotos;
import TDAs.DoubleCircularLinkedList;
import TDAs.List;
import TDAs.NodeList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class PantallaUsuarioController implements Initializable {

    @FXML
    private Label lbCantidadAlbumes;
    @FXML
    private Button BtnTodasImagenes;
    @FXML
    private Button BtnAlbum2;
    @FXML
    private Button BtnAlbum3;
    @FXML
    private ComboBox<String> cbAlbumes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            DoubleCircularLinkedList<Album> albumes = leerAlbumes("/archivos/albumesUsuario.txt",leerFotos("/archivos/fotosUsuario.txt"));
            lbCantidadAlbumes.setText(String.valueOf(albumes.size()));
            System.out.println(albumes);
            //List<String> nombresAlbumes = null;
            if(albumes.isEmpty()){
                System.out.println("Albumes esta vacia");
            }else if(albumes.size()==1){
                //System.out.println("Entre a un album");
            //System.out.println("Entre al to string con un elemento");
                /*System.out.println(albumes.getFirst().getContent());
                System.out.println(albumes.getFirst().getContent().getNombre());*/
                
            /*    System.out.println("PRUEBA");
                System.out.println(albumes.getFirst().getContent());
                System.out.println(albumes.getFirst().getContent().getFotos().toString().substring(1, albumes.getFirst().getContent().getFotos().toString().length()-1).replaceAll(",", "-"));*/
                //System.out.println(albumes.getFirst().getContent().toString().substring(1, -1).replaceAll(",", "-"));
                
                cbAlbumes.getItems().add(albumes.getFirst().getContent().getNombre());
            }else if (albumes.size()==2){
            //System.out.println("Entre al to string con dos elemento");
                cbAlbumes.getItems().add(albumes.getFirst().getContent().getNombre());
                cbAlbumes.getItems().add(albumes.getLast().getContent().getNombre());
            }else{
                for (NodeList<Album> t = albumes.getFirst(); !t.getNext().equals(albumes.getFirst()); t = t.getNext()) {
                //System.out.println("Entre al to string con"+t.getContent());
                    System.out.println("PRUEBA");
                    System.out.println(t.getContent());
                    System.out.println(t.getContent().toString().substring(1, -1).replaceAll(",", "-"));
                    cbAlbumes.getItems().add(t.getContent().getNombre());
                }
            //return "["+s+this.getLast().getContent()+"]";
            }
            //cbComida.getItems().addAll(tipos); 
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }    

    @FXML
    private void moveToAlbumTodas(ActionEvent event) throws IOException {
        App.setRoot("/fxml/PantallaAlbum");
        //App.setRoot(fxml);
    }

    private void modificarAlbum(ActionEvent event) throws IOException {
        //aqui deberia abrir ventana segun el album seleccionado
        //SECUENCIA PARA NUEVA VENTANA
        //cbAlbumes.get
        DoubleCircularLinkedList<Album> albumes = leerAlbumes("/archivos/albumesUsuario.txt",leerFotos("/archivos/fotosUsuario.txt"));
        
        /*final Album albumSeleccionado = new Album();
        cbAlbumes.setOnAction((ActionEvent event1) -> {
            System.out.println("Se activo accion de comboBox");
            //final Album a1 = null;
            for(Album a:albumes){
                System.out.println(a+" album de la cb: "+cbAlbumes.getValue());
                if(a.getNombre().equals(cbAlbumes.getValue())){
                    System.out.println("Hice un match");
                    albumSeleccionado.setNombre(a.getNombre());
                    albumSeleccionado.setFotos(a.getFotos());
                    albumSeleccionado.setFechaCreacion(a.getFechaCreacion());
                    //return a;
                    //return()->{Album a1 = a;};
                }
            }         });
        
        System.out.println("Album seleccionado: "+albumSeleccionado);
        //Album album =albumes;
        //cbAlbumes.getValue();
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
        FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/VentanaModificarAlbumEditable.fxml"));
        //sendData();
        Parent root = f.load();
        stage.setUserData(albumSeleccionado);
        Scene scene = new Scene(root);
        //Stage stage = new Stage();
        stage.setScene(scene);
        stage.setWidth(700);
        stage.setHeight(400);
        stage.show();
        root.autosize();
        //stage.setScene(  ) -> scene = new Scene(Parent root) -> 
        //Parent root = fxmlLoader.load(); -> FXMLLoader f = new FXMLLoader(App.class.getResource("ruta")
        //cbAlbumes.getTypeSelector()*/
    }
/*
    @FXML
    private void hacerMatchAlbumSeleccionado(ActionEvent event) throws IOException {
        DoubleCircularLinkedList<Album> albumes = leerAlbumes("/archivos/albumesUsuario.txt",leerFotos("/archivos/fotosUsuario.txt"));
        Album albumSeleccionado = new Album();
        for(Album a:albumes){
                System.out.println(a+" album de la cb: "+cbAlbumes.getValue());
                if(a.getNombre().equals(cbAlbumes.getValue())){
                    System.out.println("Hice un match");
                    albumSeleccionado.setNombre(a.getNombre());
                    albumSeleccionado.setFotos(a.getFotos());
                    albumSeleccionado.setFechaCreacion(a.getFechaCreacion());
                    //return a;
                    //return()->{Album a1 = a;};
                }
            }
        
        
    }*/

    @FXML
    private void abrirVentanaEditarAlbum(ActionEvent event) throws IOException {
        
        DoubleCircularLinkedList<Album> albumes = leerAlbumes("/archivos/albumesUsuario.txt",leerFotos("/archivos/fotosUsuario.txt"));
        
        /*Album albumSeleccionado = new Album();
        //cbAlbumes.setOnAction((ActionEvent event1) -> {
            System.out.println("Se activo accion de comboBox");
            //final Album a1 = null;
            System.out.println(albumes);
            for(Album a:albumes){
                System.out.println(a+" album de la cb: "+cbAlbumes.getValue());
                if(a.getNombre().equals(cbAlbumes.getValue())){
                    System.out.println("Hice un match");
                    albumSeleccionado.setNombre(a.getNombre());
                    albumSeleccionado.setFotos(a.getFotos());
                    albumSeleccionado.setFechaCreacion(a.getFechaCreacion());
                    //return a;
                    //return()->{Album a1 = a;};
                }
            }        // });
        
            //SI ES PRIMER ALBUM
         /*   if(albumSeleccionado.getNombre().equals(albumes.getFirst().getContent().getNombre())){
                System.out.println("Es album de todas imagenes");
                //DEBERIA LANZAR EL MODIFICAR ALBUM NO EDITABLE
            }else if(albumSeleccionado.getNombre().equals(albumes.getLast().getContent().getNombre())){
                System.out.println("Es el album de favoritos");
                //DEBERIA LANZAR EL MODIFICAR ALBUM NO EDITABLE
            }else{*/
            
       // System.out.println("Album seleccionado: "+albumSeleccionado);
        //Album album =albumes;
        //cbAlbumes.getValue();
       /* Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
        FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/VentanaModificarAlbumEditable.fxml"));
        //sendData();
        Parent root = f.load();
        stage.setUserData(albumSeleccionado);
        Scene scene = new Scene(root);
        //Stage stage = new Stage();
        stage.setScene(scene);
        stage.setWidth(700);
        stage.setHeight(400);
        stage.show();
        root.autosize();*/
    //}
    }
    
}
