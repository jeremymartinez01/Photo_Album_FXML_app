/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatos.Proyecto1P;

import Classes.Album;
import Classes.Photo;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class VentanaModificarAlbumEditableController implements Initializable {
    
    @FXML
    public AnchorPane raiz;
    @FXML
    private TextField tfNombreAlbum;
    @FXML
    private ComboBox<String> cbFotos;
    @FXML
    private ImageView ivFotoComboBox;
    @FXML
    private Label lbFechaCreacion;
    @FXML
    private Button btnCargarDatos;
    
    private Album album;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
            /*    /*Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            Album a= (Album) stage.getUserData();
            
            // TODO
            try {
            //aqui me imprime null
            Stage stage = (Stage)this.raiz.getScene().getWindow();
            
            Album a =(Album)stage.getUserData();
            tfNombreAlbum.setText(a.getNombre());
            for(Photo f:a.getFotos()){
            cbFotos.getItems().add(f.toString());
            }
            String nombreFotoSeleccionada = cbFotos.getValue();
            Photo fotoSeleccionada = null;
            for(Photo f:a.getFotos()){
            if(cbFotos.getValue().equals(f.toString())){
            fotoSeleccionada=f;
            }
            }
            InputStream inputImg;
            
            inputImg = App.class.getResource("/fotoss/"+fotoSeleccionada.getRuta()).openStream();
            ivFotoComboBox.setImage(new Image(inputImg));
            } catch (IOException ex) {
            Logger.getLogger(VentanaModificarAlbumEditableController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //ImageView iv = new ImageView(inputImg);
            
            //ivFotoComboBox.setImage(image);*/
        
   // }    
    }
    public void recuperarDatos() throws IOException{
        System.out.println("Entre a recuperar datos");
        Stage stage = (Stage)this.raiz.getScene().getWindow();
        Album a =(Album)stage.getUserData();
        tfNombreAlbum.setText(a.getNombre());
        for(Photo f:a.getFotos()){
            cbFotos.getItems().add(f.toString());
        }
        lbFechaCreacion.setText(a.getFechaCreacion().toString());
        /*String nombreFotoSeleccionada = cbFotos.getValue();
        Photo fotoSeleccionada = null;
        for(Photo f:a.getFotos()){
            if(cbFotos.getValue().equals(f.toString())){
                fotoSeleccionada=f;
            }
        }
        InputStream inputImg= App.class.getResource("/fotoss/"+fotoSeleccionada.getRuta()).openStream();
        //ImageView iv = new ImageView(inputImg);
        ivFotoComboBox.setImage(new Image(inputImg));*/
        //ivFotoComboBox.setImage(image);
        album=a;
    }

    @FXML
    private void cargarDatos(ActionEvent event) throws IOException {
        recuperarDatos();
    }

    @FXML
    private void setImage(ActionEvent event) throws IOException {
        String nombreFotoSeleccionada = cbFotos.getValue();
        Photo fotoSeleccionada = null;
        for(Photo f:album.getFotos()){
            if(cbFotos.getValue().equals(f.toString())){
                fotoSeleccionada=f;
            }
        }
        InputStream inputImg= App.class.getResource("/fotoss/"+fotoSeleccionada.getRuta()).openStream();
        //ImageView iv = new ImageView(inputImg);
        ivFotoComboBox.setImage(new Image(inputImg));
    }
}
