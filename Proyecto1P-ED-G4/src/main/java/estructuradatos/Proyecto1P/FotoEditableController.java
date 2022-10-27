/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package estructuradatos.Proyecto1P;

import Classes.Album;
import Classes.Photo;
import TDAs.ArrayList;
import TDAs.DoubleCircularLinkedList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jeremy
 */
public class FotoEditableController implements Initializable {

    @FXML
    private VBox VboxFoto;
    @FXML
    private ImageView ImgviewFoto;
    @FXML
    private HBox hboxGeneralPersonas;
    @FXML
    private VBox VboxPersonas;
    @FXML
    private TextField personanueva;
        
    DoubleCircularLinkedList<String> personas ;

    @FXML
    private ComboBox<String> personasCB;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        Image i= new Image("file:recursos/fotos/"+VentanaVisualizacionController.fotoControlador.getRuta());
        ImgviewFoto.setImage(i);
        personas = VentanaVisualizacionController.fotoControlador.getPersonas();
        int tamanio = personas.size();
        if(!personas.isEmpty()){
            for(String s:personas){
                personasCB.getItems().add(s);
            }
        }
        
    }    

    @FXML
    private void AgregarPersona(MouseEvent event) {
        String nueva = personanueva.getText();
        if(nueva.equals("")){
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setContentText("La persona no puede tener un nombre en blanco");
            alerta.show();
        //si la persona no esta en la lista la agrego
        }else if(!personasCB.getItems().contains(nueva)){
           personasCB.getItems().add(nueva);

        }
        
    }

    @FXML
    private void ModificarPersona(MouseEvent event) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        int posicion=0;
        String persona2= personanueva.getText();
        if(persona2.equals("")){
            alerta.setContentText("La persona no puede tener un nombre en blanco");
            alerta.show();
        }else if(!personasCB.getItems().contains(persona2)){
            for(int i =0;i<personasCB.getItems().size();i++){
                if(personasCB.getItems().get(i).equals(personasCB.getSelectionModel().getSelectedItem())){
                    posicion=i;
                    personasCB.getItems().set(posicion, persona2);
                }
            }
        }
        
    }

    @FXML
    private void EliminarPersona(MouseEvent event) {
        String eliminar_persona= personasCB.getSelectionModel().getSelectedItem();
        Alert alerta = new Alert(AlertType.INFORMATION);
        if(eliminar_persona==null){
            alerta.setContentText("Por favor seleccione una persona para eliminar");
            alerta.show();
        }
        if(!personasCB.getItems().isEmpty()){
            personasCB.getItems().remove(eliminar_persona);

        }
        
    }

    @FXML
    private void EliminarFoto(MouseEvent event) {
        
        try {
            DeletePhoto();
            cargarVentanaAnterior(event);
        } catch (IOException ex) {
            Logger.getLogger(FotoEditableController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void GuardarCambios(MouseEvent event) {
        try {
            GuardarCambiosFoto();
            cargarVentanaAnterior(event);
        } catch (IOException ex) {
            Logger.getLogger(FotoEditableController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarVentanaAnterior(MouseEvent event){
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.close();
        FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/Ventana_Album.fxml"));
            Parent root;
            try {
                root = f.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                root.autosize();
            } catch (IOException ex) {
                Logger.getLogger(Ventana_AlbumController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void GuardarCambiosFoto() throws IOException{
        DoubleCircularLinkedList<String> nuevas_personas = new DoubleCircularLinkedList<>();
        if(!personasCB.getItems().isEmpty()){
            for(String s: personasCB.getItems()){
                nuevas_personas.addFirst(s);
            }
        }
        String nombrefoto = VentanaVisualizacionController.fotoControlador.getRuta();
        ArrayList<String> nuevaslineastxt =new ArrayList<>();
        String nuevosnom="";
        for(String s:nuevas_personas){
            nuevosnom+=s+"-";
        }
        String ruta = new URL("file:recursos/textos/fotosUsuario.txt").getFile();

        try(BufferedReader bf= new BufferedReader(new FileReader(ruta))){
            String linea;
            //leer la linea hasta llegar al final
            while((linea=bf.readLine()) !=null){
                String linea2="";
                String[] partes = linea.split(";");
                if(partes[0].equals(nombrefoto)){
                    partes[1]=nuevosnom.substring(0,nuevosnom.length() -1);
                }
                for(int i=0;i<partes.length;i++){

                    linea2+= partes[i]+";";

                }
                nuevaslineastxt.addFirst(linea2.substring(0, linea2.length()-1));
                
            }
            bf.close();
        }catch(FileNotFoundException ex){
                    System.out.println(ex.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
        
        try(BufferedWriter bf2= new BufferedWriter(new FileWriter(ruta))){
            for(String s: nuevaslineastxt){
                bf2.write(s);
                bf2.newLine();
            }
            bf2.close();
        }
    }
    
    public void DeletePhoto() throws IOException{
        String nombrefoto = VentanaVisualizacionController.fotoControlador.getRuta();
        ArrayList<String> nuevaslineastxt =new ArrayList<>();
        String ruta = new URL("file:recursos/textos/fotosUsuario.txt").getFile();

        try(BufferedReader bf= new BufferedReader(new FileReader(ruta))){
            String linea;
            while((linea=bf.readLine()) !=null){
                String linea2="";
                String[] partes = linea.split(";");
                if(partes[0].equals(nombrefoto)){
                    continue;
                }
                for(int i=0;i<partes.length;i++){

                    linea2+= partes[i]+";";

                }
                nuevaslineastxt.addFirst(linea2.substring(0, linea2.length()-1));
                
            }
            bf.close();
        }catch(FileNotFoundException ex){
                    System.out.println(ex.getMessage());
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        
        
        try(BufferedWriter bf2= new BufferedWriter(new FileWriter(ruta))){
            for(String s: nuevaslineastxt){
                bf2.write(s);
                bf2.newLine();
            }
            bf2.close();
        }
        
    }
    
}
