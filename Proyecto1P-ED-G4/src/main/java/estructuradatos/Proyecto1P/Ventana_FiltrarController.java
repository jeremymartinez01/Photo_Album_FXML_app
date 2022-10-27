/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package estructuradatos.Proyecto1P;

import Classes.Album;
import Classes.Photo;
import static Data.AlbumesData.leerAlbumes;
import static Data.fotosData.leerFotos;
import TDAs.DoubleCircularLinkedList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;

/**
 * FXML Controller class
 *
 * @author lolaguaman
 */
public class Ventana_FiltrarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO            
            //DoubleCircularLinkedList<Photo> fotos = leerFotos("recursos/textos/fotosUsuario.txt");
            //DoubleCircularLinkedList<Album> albumes = leerAlbumes("recursos/textos/albumesUsuario.txt", fotos);
            Album albumSeleccionado = Ventana_AlbumController.album;
              
            int c = albumSeleccionado.getFotos().size();
            TreeItem rootItem = new TreeItem("Filtrar fotos" + " (" + c + ")");
            
            //if (c == 1) {
                //TreeItem item = new TreeItem(albumes.getFirst().getContent().getNombre());
                //rootItem.getChildren().add(item);
            //}
            //for (Album a : albumes) {
                //TreeItem item = new TreeItem(a.getNombre());
                //rootItem.getChildren().add(item);
            //}

            
    }    
    
    
}
