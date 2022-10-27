/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuradatos.Proyecto1P;

import Classes.Album;
import Classes.Photo;
import static Data.AlbumesData.eliminarAlbumArchivo;
import static Data.AlbumesData.leerAlbumes;
import static Data.fotosData.leerFotos;
import TDAs.DoubleCircularLinkedList;
import TDAs.NodeList;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ERWIN AURIA
 */
public class Ventana_AlbumController implements Initializable {

    @FXML
    private TreeView<String> list_Album;
    @FXML
    private TreeView<String> list_Album1;
    @FXML
    private HBox hBoxImgeneral;
    @FXML
    private Label nombreAlbum;
    @FXML
    private Label descricpcionAlbum;
    @FXML
    private HBox hBoxImgeneral1;
    @FXML
    private Label nombreAlbum1;
    @FXML
    private Label descricpcionAlbum1;
    @FXML
    private ScrollPane scrollpaneBiblio;
    @FXML
    private ScrollPane scrollpaneEdi;
    @FXML
    private FlowPane panel;
    @FXML
    private FlowPane panelEdicion;
    @FXML
    private Tab biblioteca;
    @FXML
    private Tab edicion;
    
    public static Photo foto;
    public static Album album;
    public static Album albumfiltrado;
    public static int index;
    @FXML
    private Button btnFiltrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            DoubleCircularLinkedList<Photo> fotos = leerFotos("recursos/textos/fotosUsuario.txt");
            DoubleCircularLinkedList<Album> albumes = leerAlbumes("recursos/textos/albumesUsuario.txt", fotos);

            int c = albumes.size();

            TreeItem rootItem = new TreeItem("Albumes " + " (" + c + ")");
            if (c == 1) {
                TreeItem item = new TreeItem(albumes.getFirst().getContent().getNombre());
                rootItem.getChildren().add(item);
            }
            for (Album a : albumes) {
                TreeItem item = new TreeItem(a.getNombre());
                rootItem.getChildren().add(item);
            }

            list_Album.setRoot(rootItem);
            list_Album1.setRoot(rootItem);

            nombreAlbum.setText(albumes.getLast().getContent().getNombre());
            nombreAlbum1.setText(albumes.getLast().getContent().getNombre());
            descricpcionAlbum.setText(albumes.getLast().getContent().getDescripcion());
            descricpcionAlbum1.setText(albumes.getLast().getContent().getDescripcion());
            mostrarImagenesXAlbum(albumes.getLast().getContent().getNombre());

            for (Photo p : albumes.getLast().getContent().getFotos()) {
                VBox vboxFoto = new VBox();
                vboxFoto.setAlignment(Pos.CENTER);
                Image i = new Image("file:recursos/fotos/" + p.getRuta());
                ImageView imgv = new ImageView(i);
                imgv.setFitHeight(100);
                imgv.setFitWidth(100);
                //System.out.println(imgv);
                vboxFoto.getChildren().add(imgv);
                //nombre
                Label lnombre = new Label(p.getInfo());
                vboxFoto.getChildren().add(lnombre);
                //anio
                Label lprecio = new Label(String.valueOf(p.getFecha()));
                vboxFoto.getChildren().add(lprecio);
                // vboxmenu.setPadding(new Insets(2, 3, 3, 4));
                System.out.println("***");
                
                panelEdicion.getChildren().add(vboxFoto);
                //scrollpaneEdi.setContent(vboxFoto);
                EventHandler eventHandler = (event) -> {
                    Node node = (Node) event.getSource();
                    Stage stage = (Stage) node.getScene().getWindow();
                    stage.close();
                    FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/VentanaVisualizacion.fxml"));
                    Parent root;
                    try {
                        Ventana_AlbumController.foto = p;
                        root = f.load();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                        root.autosize();
                    } catch (IOException ex) {
                       
                    }
                };

                vboxFoto.setOnMouseClicked(eventHandler);
            }
            
            //AGREGUE
            scrollpaneEdi.setFitToWidth(true);
            scrollpaneEdi.setFitToHeight(true);
            scrollpaneEdi.setContent(panelEdicion);

        } catch (IOException ex) {
            System.out.println(ex);
        }

        /*CheckBox cb0 = new CheckBox("Personas");  
        CustomMenuItem item0 = new CustomMenuItem(cb0);  
        CheckBox cb1 = new CheckBox("Lugar");  
        CustomMenuItem item1 = new CustomMenuItem(cb1);  
        item0.setHideOnClick(false);  
        item1.setHideOnClick(false);  
        comboBox_Filtro.getItems().setAll(item0,item1);*/
    }

    @FXML
    private void listaAlbumes(MouseEvent event) throws IOException {

        TreeItem<String> item_Seleccionado = list_Album.getSelectionModel().getSelectedItem();
        TreeItem<String> item_Root = list_Album.getTreeItem(0);
        try {
            if (item_Seleccionado != item_Root) {
                panel.getChildren().clear();
                nombreAlbum.setText(item_Seleccionado.getValue());
                mostrarDescricpion(item_Seleccionado.getValue());
                mostrarImagenesXAlbum(item_Seleccionado.getValue());
            }
        } catch (NullPointerException ex) {
            System.out.println("Seleccione un album");
        }

    }
    
    
    /*
    METODO BOTON FILTRAR 
    boton event{
        Ventana_a...albumfiltrado=Ventana....albumfiltrado.filtrarPorLugar(tfLugar.getContent());
        String[] personas=tfPersonas.split(",");
        for(String s:personas){
        Ventana.....albumfiltrado=Venta.....albumfiltrado.filtrarPorPersona(s);
        }
        cargarFotos(ventana....albumFiltrado); 
    }
    
    */

    @FXML
    private void listaAlbumesEdicion(MouseEvent event) throws IOException {
        TreeItem<String> item_Seleccionado = list_Album1.getSelectionModel().getSelectedItem();
        TreeItem<String> item_Root = list_Album1.getTreeItem(0);
        try {
            if (item_Seleccionado != item_Root) {
                panelEdicion.getChildren().clear();
                nombreAlbum1.setText(item_Seleccionado.getValue());
                mostrarDescricpion(item_Seleccionado.getValue());
                mostrarImagenesXAlbum(item_Seleccionado.getValue());
            }
        } catch (NullPointerException ex) {
            System.out.println("Seleccione un album");
        }
    }

    @FXML
    private void crearAlbum(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/Ventana_CrearAlbum.fxml"));
        Parent root = f.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        root.autosize();

    }

    @FXML
    private void editarNombre_Album(MouseEvent event) {
    }

    @FXML
    private void editarDescrip_Album(MouseEvent event) {
    }

    @FXML
    private void eliminarAlbum(MouseEvent event) throws IOException {
        eliminarAlbumArchivo(album);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Se eliminó "+album.getNombre());
        alert.show();
    }

    @FXML
    private void importarFoto(ActionEvent event) throws IOException {
        FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/FileChooser.fxml"));
        Parent root = f.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        root.autosize();
    }

    private void mostrarDescricpion(String itemSeleccionado) throws IOException {

        DoubleCircularLinkedList<Photo> fotos = leerFotos("recursos/textos/fotosUsuario.txt");
        DoubleCircularLinkedList<Album> albumes = leerAlbumes("recursos/textos/albumesUsuario.txt", fotos);
        int c = albumes.size();
        if (c == 1) {
            if (itemSeleccionado.compareTo(albumes.getFirst().getContent().getNombre()) == 0) {
                albumes.getFirst().getContent().getFotos();
            }
        }

        for (Album a : albumes) {
            if (itemSeleccionado.compareTo(a.getNombre()) == 0) {
                if (biblioteca.isSelected()) {
                    descricpcionAlbum.setText(a.getDescripcion());
                } else if (edicion.isSelected()) {
                    descricpcionAlbum1.setText(a.getDescripcion());
                }
            }
        }
    }

    private void mostrarImagenesXAlbum(String itemSeleccionado) throws IOException {

        DoubleCircularLinkedList<Photo> fotos = leerFotos("recursos/textos/fotosUsuario.txt");
        DoubleCircularLinkedList<Album> albumes = leerAlbumes("recursos/textos/albumesUsuario.txt", fotos);

        /*ScrollBar s = new ScrollBar();
        s.setMin(0);
        s.setMax(400);
        s.setValue(400);
        s.setOrientation(Orientation.VERTICAL);
        s.setUnitIncrement(20);
        s.setBlockIncrement(20);*/

        int c = albumes.size();
        int i=c-1;

        if (c == 1) {
            if (itemSeleccionado.equals(albumes.getFirst().getContent().getNombre())) {
                albumes.getFirst().getContent().getFotos();
            }
        }

        for (Album a : albumes) {
            if (itemSeleccionado.compareTo(a.getNombre()) == 0) {
                Ventana_AlbumController.index = i;
                System.out.println(i);
                Ventana_AlbumController.album = a;
                //INICIALIZO ALBUMFILTRADO
                albumfiltrado=a;
                int contFotos = a.getFotos().size();
                if (contFotos == 1) {
                    Photo p = a.getFotos().getFirst().getContent();
                    mostrarFotosPanel(p);
                }
                for (Photo p : a.getFotos()) {
                    mostrarFotosPanel(p);
                }
            }
            i--;
        }
    }
    
    private void cargarFotos(Album albumseleccionado){
        panel.getChildren().clear();
        if(albumseleccionado.getFotos().size()==1){
            mostrarFotosPanel(albumseleccionado.getFotos().getFirst().getContent());
        }else if(albumseleccionado.getFotos().size()==2){
            mostrarFotosPanel(albumseleccionado.getFotos().getFirst().getContent());
            mostrarFotosPanel(albumseleccionado.getFotos().getLast().getContent());
        }else{
            for(Photo p:albumseleccionado.getFotos()){
                mostrarFotosPanel(p);
            }
        }
        /*scrollpaneEdi.setFitToWidth(true);
        scrollpaneEdi.setFitToHeight(true);
        scrollpaneEdi.setContent(panel);*/
    }
    
    /*
    filtraste GYE
    Album albumSeleccionadofiltrado
    albumSeleccionadoFiltrado=albumSeleccionado.filtradoLugar(GYE)
    panel.getChildren().clear();
    cargarFotos(albumSelecionadoFiltrado);
    */

    private void mostrarFotosPanel(Photo p) {
        VBox vboxFoto = new VBox();
        vboxFoto.setAlignment(Pos.CENTER);
        Image i = new Image("file:recursos/fotos/" + p.getRuta());
        ImageView imgv = new ImageView(i);
        imgv.setFitHeight(100);
        imgv.setFitWidth(100);
        //System.out.println(imgv);
        vboxFoto.getChildren().add(imgv);
        //nombre
        Label lnombre = new Label(p.getInfo());
        vboxFoto.getChildren().add(lnombre);
        //anio
        Label lprecio = new Label(String.valueOf(p.getFecha()));
        vboxFoto.getChildren().add(lprecio);
        // vboxmenu.setPadding(new Insets(2, 3, 3, 4));
        System.out.println("***");

        if (biblioteca.isSelected()) {
            panel.getChildren().add(vboxFoto);
            /* 
            scrollpaneEdi.setFitToWidth(true);
            scrollpaneEdi.setFitToHeight(true);
            scrollpaneEdi.setContent(panelEdicion);
            */
            scrollpaneBiblio.setFitToWidth(true);
            scrollpaneBiblio.setFitToHeight(true);
            scrollpaneBiblio.setContent(panel);
            
        } else if (edicion.isSelected()) {
            panelEdicion.getChildren().add(vboxFoto);
            scrollpaneEdi.setFitToWidth(true);
            scrollpaneEdi.setFitToHeight(true);
            scrollpaneEdi.setContent(panelEdicion);
            //scrollpaneEdi.setContent(vboxFoto);
        }

        EventHandler eventHandler = (event) -> {
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/VentanaVisualizacion.fxml"));
            Parent root;
            try {
                Ventana_AlbumController.foto = p;
                root = f.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                root.autosize();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        };

        vboxFoto.setOnMouseClicked(eventHandler);
    }

    @FXML
    private void filtrar(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/VentanaFiltrar.fxml"));
            Parent root;
            root = f.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            root.autosize();

    }

    @FXML
    private void aplicarFiltro(ActionEvent event) {
        albumfiltrado.setFotos(VentanaFiltrarController.fotosFiltradas);
        System.out.println(albumfiltrado);
        cargarFotos(albumfiltrado);
    }

    @FXML
    private void limpiarFiltro(ActionEvent event) throws IOException {
        albumfiltrado=album;
        //cargarFotos(albumfiltrado);
        //mostrarImagenesXAlbum(itemSeleccionado);
        Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            FXMLLoader f = new FXMLLoader(App.class.getResource("/fxml/Ventana_Album.fxml"));
            Parent root;
            root = f.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            root.autosize();
    }
}
