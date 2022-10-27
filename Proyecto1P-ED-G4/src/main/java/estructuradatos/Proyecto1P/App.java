package estructuradatos.Proyecto1P;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;



/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
       /* var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();*/
        
        
        try {
           // FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("FileChooser.fxml"));  
           
           //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/FileChooser.fxml"));
           
           //PRUBEAS FILE CHOOSER
           //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/FileChooser.fxml"));
           
           FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/Ventana_Album.fxml"));
            
           
           //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/fxml/PantallaAlbum.fxml"));
            //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/"+getClass().getCanonicalName().replaceAll(".", "/").replaceAll("Controller", "")+"fxml"));
            //fxmlLoader.setLocation(App.class.getResource("/src/fxml/FileChooser.fxml"));
            //System.out.println(fxmlLoader.getLocation());
            //System.out.println(App.class.getResource("FileChooserController.class"));
            //System.out.println(App.class.getResource(""));
            //System.out.println(getClass().getResource("/fxml/FileChooser.fxml"));
            //System.out.println("location:"+fxmlLoader.getLocation());
            Parent root = fxmlLoader.load();
            //fxmlLoader.
            //System.out.println("Esto no se completo");
            //System.out.println(root);
            //cree el scene y fije como nodo raiz el objeto que cargo con el fxml
            scene = new Scene(root);
        } catch (IOException ex) {
            //si llegamos a este punto es porque no se pudo cargar del archivo
            //reporte.fxml el scenegraph
            //creamos con programacion un nuevo roort y lo fijamos a la scena
            VBox v = new VBox(new Label("No se cargo el archivo PantallaUsuario.fxml"));
            scene = new Scene(v);
            System.out.println(ex);
            //System.out.println(root);
        }
            
        stage.setScene(scene);
        
        //muestre la aplicacion
        //stage.setResizable(false);
        stage.show();
    }
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(App.class.getResource(fxml + ".fxml"));
        return loader.load();
    }
    
    public static void initialize() throws IOException{
        
    }
    

    public static void main(String[] args) {
        try {
            
            initialize();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        launch();
    }

}