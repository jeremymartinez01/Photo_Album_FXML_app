/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatos.Proyecto1P;

import Classes.Album;
import Classes.Photo;
import static Data.AlbumesData.escribirAlbumes;
import static Data.fotosData.leerFotos;
import TDAs.DoubleCircularLinkedList;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ERWIN AURIA
 */
public class Ventana_CrearAlbumController implements Initializable {

    @FXML
    private TextField nombre_AlbumCrear;
    @FXML
    private TextField descripcion_AlbumCrear;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void anadirFoto(MouseEvent event) throws IOException {
        FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/FileChooser.fxml"));
        Parent root = f.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        root.autosize();
    }

    @FXML
    private void crearAlbum(ActionEvent event) {
        String nombre_Album = nombre_AlbumCrear.getText();
        String descripcion_Album = descripcion_AlbumCrear.getText();
        if (!nombre_Album.equals("") && !descripcion_Album.equals("")) {
            //Comparador de album por nombre
            Album nuevo = new Album(nombre_Album, descripcion_Album);
            nuevo.setFotos(FileChooserController.fotoSeleccionadaFileChooser);

            System.out.println("" + nuevo);
            try {
                System.out.println("" + nuevo);
                escribirAlbumes(nuevo);
                //System.out.println(""+nombre_Album);
            } catch (MalformedURLException ex) {
                System.out.println(ex);

            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Por favor, ingrese un nombre y una descricpcion");
            alert.show();
        }
    }

    @FXML
    private void cancelar(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/Ventana_Album.fxml"));
        Parent root = f.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        root.autosize();
    }

}
