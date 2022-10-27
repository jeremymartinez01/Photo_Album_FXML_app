/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Classes.Album;
import Classes.Photo;
import TDAs.ArrayList;
import TDAs.DoubleCircularLinkedList;
import estructuradatos.Proyecto1P.App;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 *
 * @author Javier
 */
public class AlbumesData {

    public static DoubleCircularLinkedList<Album> leerAlbumes(String ruta, DoubleCircularLinkedList<Photo> fotosCompletas) throws IOException {
        DoubleCircularLinkedList<Album> albumes = new DoubleCircularLinkedList<Album>();
        //SIRVE EN TARGET

        try (InputStream input = new URL("file:" + ruta).openStream();
                BufferedReader bf = new BufferedReader(
                        new InputStreamReader(input, "UTF-8"))) {
            //GENERA EL URL RELATIVO AL ARCHIVO QUE VAMOS A LEER
            String linea;
            //leemos linea a linea hasta llegar la final del archivo
            while ((linea = bf.readLine()) != null) {
                String[] partes = linea.split(";");

                DoubleCircularLinkedList<Photo> fotosAlbum = new DoubleCircularLinkedList<>();
                if (partes[2].equals("null")) {
                    System.out.println("Album vacio");
                    //personas=null;
                } else {
                    String[] partesFotos = partes[2].split("-");
                    for (String s : partesFotos) {
                        for (Photo f : fotosCompletas) {
                            if (s.equals(f.getRuta())) {
                                fotosAlbum.addFirst(f);
                            }
                        }

                    }
                }
                
                if (partes[3].equals("null")) {
                    Album albumAñadir = new Album(partes[0], fotosAlbum, LocalDate.now(), partes[1]);
                    albumes.addFirst(albumAñadir);
                } else {
                    albumes.addFirst(new Album(partes[0], fotosAlbum, LocalDate.parse(partes[3]),partes[1]));
                }

            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
        return albumes;
    }

    public static void escribirAlbumes(Album album) throws MalformedURLException {
        //INTENTO PERMANENTE
        String ruta = "recursos/textos/AlbumesUsuario.txt";
        File file = new File(new URL("file:" + ruta).getFile());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            //bw.newLine();
            String fotos = "";
            int size = album.getFotos().size();
            if(size == 1){
                fotos += album.getFotos().getFirst().getContent().getRuta();
            }
            for (Photo f : album.getFotos()) {
                fotos += f.getRuta() + "-";
            }
            //int i = 0;
            
            String linea = album.getNombre() + ";" + album.getDescripcion() + ";" + fotos + ";" + album.getFechaCreacion();
            bw.write(linea);
            bw.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();

        }
    }

    //LEE ARCHIVO COMPLETO Y RETORNO EL STRING DE TODO
    public static String leerArchivoCompleto(String ruta) throws FileNotFoundException, IOException {
        //FUNCIONA TARGET
        //InputStream input = App.class.getResource(ruta).openStream();
        //INTENTO PERMANENTE
        InputStream input = new URL("file:" + ruta).openStream();
        Scanner s = new Scanner(input).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        return result;

    }

    //SOBREESCRIBIR FAVORITOS
    public static void ingresarNuevoImagen(Photo nuevaImagen, int a) throws IOException {
        // a =0 ->Todas
        //a =1  ->Favoritos
        // si se conoce el numero del album tambien funcionaria con el resto
        //INTENTO PERMANENTE
        InputStream input = new URL("file:recursos/textos/albumesUsuario.txt").openStream();

        //FUNCIONA TARGET
        //INTENTO PERMANENTE
        String completo = leerArchivoCompleto("recursos/textos/albumesUsuario.txt");
        String lineas[] = completo.split("\r?\n|\r");
        String[] editarLinea = lineas[a].split(";");
        String nuevaLinea = editarLinea[0] + ";" + editarLinea[1] + ";" + editarLinea[2] + "-" + nuevaImagen.getRuta() + ";" + editarLinea[3];

        //FUNCIONA TARGET
        //String ruta = App.class.getResource("/archivos/albumesUsuario.txt").getFile();
        //INTENTO PERMANENTE
        String ruta = new URL("file:recursos/textos/albumesUsuario.txt").getFile();
        int posicion = a;
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try {
            fichero = new FileWriter(ruta);
            escritor = new PrintWriter(fichero);
            escritor.flush();
            System.out.println(nuevaLinea);
            lineas[posicion] = nuevaLinea;

            for (int x = 0; x < lineas.length; x++) {
                escritor.write(lineas[x]);
                escritor.println();
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println(e);
            // JOptionPane.showMessageDialog(null, "Error al escribir en el archivo de texto: "+e.getMessage());
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    System.out.println(e);
                    // JOptionPane.showMessageDialog(null, "Error al cerrar archivo de texto: "+e.getMessage());
                }
            }
        }
    }

    //DEBERIA SOBREESCRIBIR EN LA LINEA DESEADA LA NUEVA LINEA
    public static void ingresar(String ruta, String nuevaLinea, int posicion) {
        FileWriter fichero = null;
        PrintWriter escritor = null;
        try {
            fichero = new FileWriter(ruta);
            escritor = new PrintWriter(fichero);
            escritor.flush();
            String split[] = leerArchivoCompleto(ruta).split("\n");
            split[posicion] = nuevaLinea;
            for (int x = 0; x < split.length; x++) {
                escritor.write(split[x]);
                escritor.println();
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println(e);
            // JOptionPane.showMessageDialog(null, "Error al escribir en el archivo de texto: "+e.getMessage());
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    System.out.println(e);
                    // JOptionPane.showMessageDialog(null, "Error al cerrar archivo de texto: "+e.getMessage());
                }
            }
        }
    }

    //NO PROBADO
    public static int getLineadeNombreAlbum(String ruta, String elementoBuscado) throws IOException {
        int l = 0;
        //INTENTO TARGET
        //String completo=leerArchivoCompleto("/archivos/albumesUsuario.txt");
        //INTENTO PERMANENTE
        String completo = leerArchivoCompleto("recursos/textos/albumesUsuario.txt");
        String lineas[] = completo.split("\r?\n|\r");

        //String split[] = leerArchivoCompleto(ruta).split("\n");
        for (String s : lineas) {
            String lineaSeparada[] = s.split(";");
            if (lineaSeparada[0].equals(elementoBuscado)) {
                return l;
            }
            l++;
        }
        return l;
    }

    public static void sobreescribirAlbum(String ruta, Album album) throws IOException {
        int numLinea = getLineadeNombreAlbum(ruta, album.getNombre());
        String nuevaLinea = album.getNombre() + ";" + album.getDescripcion() + ";" + album.getFotos().toString().substring(1, album.getFotos().toString().length() - 1).replaceAll(",", "-") + ";" + album.getFechaCreacion().toString();
        ingresar(ruta, nuevaLinea, numLinea);
    }

    public static boolean imagenExiste(Photo foto) throws IOException {
        //FUNCIONA TARGET
        //String completo=leerArchivoCompleto("/archivos/albumesUsuario.txt");
        //INTENTO PERMANENTE
        String completo = leerArchivoCompleto("recursos/textos/albumesUsuario.txt");
        String lineas[] = completo.split("\r?\n|\r");
        String fotosCompletas[] = lineas[0].split("-");
        for (String f : fotosCompletas) {
            if (f.equals(foto.getRuta())) {
                return true;
            }
        }
        return false;
    }
    
    public static String lineaAlbum(String ruta, String elementoBuscado) throws IOException {
        //int l = 0;
        String lineaObtenida = "";
        //INTENTO TARGET
        //String completo=leerArchivoCompleto("/archivos/albumesUsuario.txt");
        //INTENTO PERMANENTE
        String completo = leerArchivoCompleto("recursos/textos/albumesUsuario.txt");
        String lineas[] = completo.split("\r?\n|\r");

        //String split[] = leerArchivoCompleto(ruta).split("\n");
        for (String s : lineas) {
            String lineaSeparada[] = s.split(";");
            if (lineaSeparada[0].equals(elementoBuscado)) {
                lineaObtenida += s;
            }
        }
        return lineaObtenida;
    }
    
    
    public static void eliminarAlbumArchivo(Album albumSeleccionado) throws MalformedURLException, IOException{
        

        ArrayList<String> nuevaslineastxt =new ArrayList<>();

        String ruta = new URL("file:recursos/textos/albumesUsuario.txt").getFile();
        String lineaEliminar = lineaAlbum(ruta, albumSeleccionado.getNombre());

        try(BufferedReader bf= new BufferedReader(new FileReader(ruta))){
            String linea;
            //leer la linea hasta llegar al final
            while((linea=bf.readLine()) !=null){
                if(!linea.equals(lineaEliminar)){
                     nuevaslineastxt.addFirst(linea);
                }
   
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
