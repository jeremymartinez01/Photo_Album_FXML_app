/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Classes.Photo;
import TDAs.DoubleCircularLinkedList;
import estructuradatos.Proyecto1P.App;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author Javier
 */
public class fotosData {
    
    public static DoubleCircularLinkedList<Photo> leerFotos(String ruta) throws IOException{
        DoubleCircularLinkedList<Photo> fotos = new DoubleCircularLinkedList<Photo>();
        //ESTA FUNCIONA
        //try(InputStream input = App.class.getResource(ruta).openStream();
        try(InputStream input = new URL("file:"+ruta).openStream();
                
                BufferedReader bf = new BufferedReader(
                                    new InputStreamReader(input,"UTF-8"))){
            //GENERA EL URL RELATIVO AL ARCHIVO QUE VAMOS A LEER
           
            /*URL u = App.class.getResource(ruta);
            File file = new File(u.toURI());
            try(BufferedReader bf = new BufferedReader(new FileReader(file))){*/
                String linea;
                //leemos linea a linea hasta llegar la final del archivo
                while( (linea=bf.readLine())!=null ){
                    //System.out.println(linea);
                    //System.out.println("tets");
                    //System.out.println(linea);
                    //dividir la en partes 
                    String[] partes = linea.split(";");
                    DoubleCircularLinkedList<String> personas= new DoubleCircularLinkedList<>();
                    if(partes[1].equals("null")){
                        System.out.println("Imagen sin personas");
                        //personas=null;
                    }else{
                    String[] partesPersonas=partes[1].split("-");
                    for(String s:partesPersonas){
                        personas.addFirst(s);
                    }
                    }
                    if(partes[2].equals("null")){
                        fotos.addFirst(new Photo(partes[0],personas,null,partes[3],Integer.valueOf(partes[4])));
                    }else{
                    //Photo(String ruta, DoubleCircularLinkedList<String> personas, String lugar, String info,int i)
                    //Photo("girasol.jpg",null,"Es un girasol"); ejemplo constructo Ruta,ListaPersonas,Descripcion
                    fotos.addFirst(new Photo(partes[0],personas,partes[2],partes[3],Integer.valueOf(partes[4])));
                }}
                //System.out.println(fotos);
                //System.out.println("Termine el archivo");
            } catch (FileNotFoundException ex) {
                //System.out.println("No se encontro archivo");
                System.out.println(ex.getMessage());
                throw ex;
            } catch (IOException ex) {
               // System.out.println("Paso algo al leer archivo");
                System.out.println(ex.getMessage());
                throw ex;
            } 
        //System.out.println("Antes del return");
        return fotos;
}
    
    public static void escribirFotos(Photo foto) throws MalformedURLException {
        //FUNCIONA EN EL TARGET
        //String ruta = "/archivos/fotosUsuario.txt";
        //List<Comida> comidas = new ArrayList<>();
        //File file = new File(App.class.getResource(ruta).getFile());
        //INTENTO PERMANENTE
        String ruta="recursos/textos/fotosUsuario.txt";
        File file = new File(new URL("file:"+ruta).getFile());
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
            bw.newLine();
            //String linea = comida.getNombre()+";"+ comida.getPrecio()+";"+comida.getRutaImagen()+";"+comida.getTipoComida();
            String personas ="";
            for(String s:foto.getPersonas()){
                personas+=s+"-";
            }
            int i=0;
            if(foto.getFavorita()==true){
                i=1;
            }
            String linea =foto.getRuta()+";"+personas+";"+foto.getLugar()+";"+foto.getInfo()+";"+i;
            bw.write(linea);
            bw.close();
            
        }  catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            
        }
        //return comidas;
    }
    
}