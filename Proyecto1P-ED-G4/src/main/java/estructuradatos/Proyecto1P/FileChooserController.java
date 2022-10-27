/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatos.Proyecto1P;

import Classes.Photo;
import static Data.AlbumesData.imagenExiste;
import static Data.AlbumesData.ingresarNuevoImagen;
import static Data.fotosData.escribirFotos;
import TDAs.DoubleCircularLinkedList;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Javier
 */
public class FileChooserController implements Initializable {

    @FXML
    private Button btnBuscar;
    @FXML
    private ImageView ivImagen;
    @FXML
    private TextField tfLugar;
    @FXML
    private TextField tfInfo;
    @FXML
    private CheckBox checkBoxFavoritos;
    @FXML
    private TextField tfPersona1;
    @FXML
    private Button btnAgregarImagen;
    @FXML
    private Label lbMensajeError;

    private Photo imagenSeleccionada;
    private String rutaAnterior;
    private File fileSeleccionado;
    private int i;
    public static DoubleCircularLinkedList<Photo> fotoSeleccionadaFileChooser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbMensajeError.setText("");
        // TODO
        btnBuscar.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Buscar Imagen");

            // Agregar filtros para facilitar la busqueda
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Images", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );

            // Obtener la imagen seleccionada
            File imgFile = fileChooser.showOpenDialog(null);
            fileSeleccionado = imgFile;

            // Mostar la imagen
            if (imgFile != null) {
                Image image = new Image("file:" + imgFile.getAbsolutePath());
                ivImagen.setImage(image);
                rutaAnterior = imgFile.getAbsolutePath();
            }
        });

    }

    @FXML
    private void CrearImagen(ActionEvent event) throws IOException {
        try {
            String[] direccion = fileSeleccionado.getAbsolutePath().split(Pattern.quote(File.separator));
            //System.out.println(direccion[0]);
            //fileSeleccionado.copy(fileSeleccionado.getAbsolutePath(),"/Proyecto1P-ED-G4/target/classes/fotss");
            //System.out.println(fileSeleccionado.renameTo(new File("*/Proyecto1P-ED-G4/target/classes/fotoss/"+direccion[direccion.length-1]+".jpg")));
            //C:\Users\Javier\Documents\NetBeansProjects\Proyecto1P-ED-G4\Proyecto1P-ED-G4\target\classes\fotoss

            //ESTA FUNCIONA
            //System.out.println(fileSeleccionado.renameTo(new File("C:\\Users\\Javier\\Documents\\NetBeansProjects\\Proyecto1P-ED-G4\\Proyecto1P-ED-G4\\target\\classes\\fotoss\\"+LocalDate.now().toString()+".jpg")));
            //System.out.println(fileSeleccionado.renameTo(new File("C:\\Users\\Javier\\Documents\\NetBeansProjects\\Proyecto1P-ED-G4\\Proyecto1P-ED-G4\\target\\classes\\fotoss\\"+direccion[direccion.length-1]+".jpg")));
            //
            //System.out.println(App.class.getResource("/fotoss/").getFile());
            System.out.println("Si imprime True, se logro");
            //FUNCIONA EN TARGET
            //System.out.println(fileSeleccionado.renameTo(new File(App.class.getResource("/fotoss/").getFile()+direccion[direccion.length-1])));
            //INTENTO RUTA PERMANENTE
            System.out.println(fileSeleccionado.renameTo(new File(new URL("file:recursos/fotos/").getFile() + direccion[direccion.length - 1])));

            //File f= new File(App.class.getResource("/fotoss/mariposa.jpg"));
            //fileSeleccionado.delete();
            //System.out.println(fileSeleccionado.getAbsolutePath());
            //fileSeleccionado.
            String[] personas = tfPersona1.getText().split(",");
            DoubleCircularLinkedList<String> personasDcl = new DoubleCircularLinkedList<>();
            for (String p : personas) {
                personasDcl.addLast(p);
            }
            System.out.println(personasDcl);
            //String[] direccion=fileSeleccionado.getAbsolutePath().split("\\");
            Photo fotoSeleccionada = new Photo(direccion[direccion.length - 1], personasDcl, tfLugar.getText(), tfInfo.getText(), i);
            escribirFotos(fotoSeleccionada);
            System.out.println("Foto seleccionada: " + fotoSeleccionada);
            DoubleCircularLinkedList<Photo> fotoSeleccionadaNuevoAlbum = new DoubleCircularLinkedList<>();
            fotoSeleccionadaNuevoAlbum.addFirst(fotoSeleccionada);
            fotoSeleccionadaFileChooser = fotoSeleccionadaNuevoAlbum;
            

            //SI LA IMAGEN NO ESTA EN EL ALBUM DE TODAS LAS IMAGENES
            if (!imagenExiste(fotoSeleccionada)) {
                i = 0;
                System.out.println("Agrege a album de todas");
                ingresarNuevoImagen(fotoSeleccionada, 0); //LA AÑADO A TODAS
                i = Ventana_AlbumController.index;
                if (i!=0) {
                    System.out.println("Agrege a album q no sea todas");
                    ingresarNuevoImagen(fotoSeleccionada, i);
                    if (checkBoxFavoritos.isSelected()) {   //SI EL CHECKBOX ESTA ENCENDIDA, AGREGO IMAGEN EN LA LINEA 1, QUE ES EL ALBUM DE FAVORITOS
                        System.out.println("Agrege album favoritos");
                        ingresarNuevoImagen(fotoSeleccionada, 1);
                    }
                }

            }

            ivImagen.setImage(null);
            tfLugar.setText("");
            tfInfo.setText("");
            tfPersona1.setText("");

        } catch (NullPointerException ex) {
            lbMensajeError.setText("Porfavor seleccione una imagen");
            //System.out.println("Seleccione un album");
        }

    }

    @FXML
    private void cambiarFavorito(ActionEvent event) {
        if (i == 0) {
            i = 1;
        } else {
            i = 0;
        }
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        lbMensajeError.setText("");
        //App.setRoot("/fxml/Ventana_Album.fxml");
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        //string = ventana anterior
        /*FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/Ventana_Album.fxml"));
        //sendData();
        Parent root = f.load();
        //stage.setUserData(albumSeleccionado);
        Scene scene = new Scene(root);
        //Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        root.autosize();*/
    }

}
