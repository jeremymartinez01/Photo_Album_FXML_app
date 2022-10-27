package TDAs;

import TDAs.List;
import TDAs.NodeList;
import java.util.Iterator;


// estudiante Javier Vergara

public class DoubleCircularLinkedList<E> implements Iterable<E>, List<E>  {

    private NodeList<E> first;
    private NodeList<E> last;

    public DoubleCircularLinkedList() {
        this.first = null;
        this.last = null;
    }
    //contructor de prueba
    public DoubleCircularLinkedList(E e) {
        //this.first.setContent(e);
        this.first=new NodeList<E>(e);
        //this.last.setContent(e);
        this.last=new NodeList<E>(e);
    }

    public NodeList<E> getFirst() {
        return first;
    }

    public void setFirst(NodeList<E> first) {
        this.first = first;
    }

    public NodeList<E> getLast() {
        return last;
    }

    public void setLast(NodeList<E> last) {
        this.last = last;
    }

    //PROBADO Y FUNCIONA BIEN
    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        }
        NodeList<E> nuevo = new NodeList<>(e);
        
        if(this.isEmpty()){
            //System.out.println("Añadi primer elemento");
            this.setLast(nuevo);
            this.setFirst(nuevo);
            return true;
        }else if(this.getFirst().getNext()==null){
            //System.out.println("Añadi segundo elemento");
            this.getFirst().setNext(nuevo);
            this.getFirst().setPrevious(nuevo);

            nuevo.setNext(this.getFirst());

            nuevo.setPrevious(this.getLast());
            this.setFirst(nuevo);
            return true;
        }else{
            nuevo.setNext(this.getFirst());
            nuevo.setPrevious(this.getLast());
            this.getFirst().setPrevious(nuevo);
            this.getLast().setNext(nuevo);
            this.setFirst(nuevo);
            return true;
        }

    }
    
    //PROBADO Y FUNCIONA BIEN
    @Override
    public boolean addLast(E e) {
        
        if (e == null) {
            return false;
        }
        NodeList<E> nuevo = new NodeList<>(e);
        
        if(this.isEmpty()){
            this.setLast(nuevo);
            this.setFirst(nuevo);
            return true;
        }else if(this.getFirst().getNext()==null){
           this.getFirst().setNext(nuevo);
           this.getFirst().setPrevious(nuevo);
           nuevo.setNext(this.getFirst());
            nuevo.setPrevious(this.getFirst());
            this.setLast(nuevo);
            return true;
        }else{
           nuevo.setNext(this.getFirst());
           
            
            nuevo.setPrevious(this.getLast());
           this.getFirst().setPrevious(nuevo);
            this.getLast().setNext(nuevo);
            this.setLast(nuevo);

            return true;
        }
        
    }
    
    //PROBADO Y FUNCIONA BIEN
    @Override
    public E removeFirst() {
        E e;
        if(isEmpty()){
            return null;
        }else if(this.size()==1){
            e=this.getFirst().getContent();
            this.setFirst(null);
            this.setLast(null);
            return e;
        }else if(this.size()==2){
            e=this.getFirst().getContent();
            this.getLast().setNext(null);
            this.getLast().setPrevious(null);
            this.setFirst(this.getLast());
            return e;
        }else {
            NodeList<E> n=this.getFirst();
            e=n.getContent();
            this.getLast().setNext(n.getNext());
            n.getNext().setPrevious(this.getLast());
            this.setFirst(n.getNext());
            return e;
        }
        }
        
    
    //PROBADO Y FUNCIONA BIEN
    @Override
    public E removeLast() {
        
        E e;
        if(isEmpty()){
            return null;
        }else if(this.size()==1){
            e=this.getLast().getContent();
            this.setFirst(null);
            this.setLast(null);
            return e;
        }else if(this.size()==2){
            e=this.getLast().getContent();
            this.getFirst().setNext(null);
            this.getFirst().setPrevious(null);
            this.setLast(this.getFirst());
            return e;
        }else {
            NodeList<E> n=this.getLast();
            e=n.getContent();
            this.getFirst().setPrevious(n.getPrevious());
            n.getPrevious().setNext(this.getFirst());
            this.setLast(n.getPrevious());
            return e;
        }
        
    }
    
    //PROBADO Y FUNCIONA BIEN
    @Override
    public int size() {
        int cont = 1;
        NodeList<E> t;
        if(this.getFirst()==null){
            return 0;
        }else if(this.getFirst().equals(this.getLast())){
            return 1;
        }else if(this.getFirst().getNext().equals(this.getLast())){
            return 2;
        }else{
        for (t = this.getFirst(); !t.getNext().equals(this.getFirst()); t = t.getNext()) {
            cont++;
        }
        return cont;
        }
    }
    
    //En teoria funciona bien
    @Override
    public boolean isEmpty() {
        return this.first == null && this.last == null;
    }
    
    //PROBADO Y FUNCIONA BIEN
    @Override
    public void clear() {
        if(isEmpty()){
            System.out.println("Linked List vacia");
        }
        while(size()>=0){
            removeFirst();
        }
    }
    
    //PROBADO Y FUNCIONA BIEN
    @Override
    public void add(int index, E element) {
        int size =size();
        if (element==null || index<0 || index>size)
            System.out.println("No se puede realizar dicha acción");
        else if (index==0){
            addFirst(element);
        }
        else if(index==size){
            addLast(element);
        }else{
        
        int i=0;
        NodeList<E> nuevo =  new NodeList<>(element);
        
        for (NodeList<E> n=this.getFirst();!n.getNext().equals(this.getFirst()); n = n.getNext()) {
            //System.out.println("Recorro: "+i+" "+index);
            if(i==index-1){
              //  System.out.println("Entre");
                NodeList<E> n1= n.getNext();  //donde añado
                nuevo.setNext(n1);        //seteo como siguiente n1 en nuevo
                nuevo.setPrevious(n);      //seteo como anterior n en nuevo
                n1.setPrevious(nuevo);         //seteo como anterior de n1 el nuevo
                n.setNext(nuevo);          //seteo como el siguiente de n el nuevo
                
            }
            i++;
        }
        }
    }

    //PROBADO Y FUNCIONA BIEN
    @Override
    public E remove(int index) {
        if(size()==0) {
    		System.out.println("Lista vacia");
    		return null;
    	}else if(index<size()) {
    		if(index==0) {
                    return removeFirst();
    		}
                else if(index==size()-1) {
                    return removeLast();
    		}
                
                else{
                        int i =0;
                        for(NodeList<E> n=this.getFirst();!n.getNext().equals(this.getFirst()); n = n.getNext()){
                   // for (NodeList<E> n=this.getFirst(); n != null; n = n.getNext()) {
                        if(i==index-1){
                        E e =n.getNext().getContent();    //guardo el contenido del eliminado
                        NodeList<E> eliminado = n.getNext();   //guardo el eliminado ennodo
                        n.setNext(eliminado.getNext());         //seteo como siguiente el siguiente del eliminado
                        eliminado.getNext().setPrevious(n);    //seteo como el anterior del sgt del eliminado a n

                        
                        return e;
    		}
                        i++;
    		
    	}}
    }else{
            System.out.println("Indice fuera del margen");
        }
        return null;   //si esta fuera del margen retorno null
}

    
    //PROBADO Y FUNCIONA BIEN
    @Override
    public E get(int index) {
        int size=size();
        int i =0;
        
        if(isEmpty()){
            System.out.println("Lista vacia");
            return null;
        }
        else if(index>=size){
            System.out.println("Valor fuera de los limites");
            return null;
        }
        else if(index==0){
           // System.out.println("Entre a index 0");
            return this.getFirst().getContent();
        }else if(index==size()-1){
           // System.out.println("Entre a index=size-1");
            return this.getLast().getContent();
        }else{
           // System.out.println("Entre al else");
            //NodeList<E> n=this.getFirst();!n.getNext().equals(this.getFirst()); n = n.getNext()
        for(NodeList<E> n=this.getFirst();!n.getNext().equals(this.getFirst());n=n.getNext()){
            //.out.println("valor i: "+i+" Valor index: "+index);
            if(i==index){
               // System.out.println("entre a index coincide ");
                return n.getContent();
            }
            i++;
        }
        
    }return null;
    }
    
    //PROBADO Y FUNCIONA BIEN
    @Override
    public E set(int index, E element) {
        
        if(index<0){
            System.out.println("Indice no permitido");
            return null;       
        }
        else if(index>=size()){
            System.out.println("Indice fuera del limite");
            return null; 
        }else if(index==0){
            //NodeList<E> n =this.getFirst();
            E e =this.getFirst().getContent();
            this.getFirst().setContent(element);
            return e;
        }else if(index==this.size()-1){
            E e =this.getLast().getContent();
            this.getLast().setContent(element);
            return e;
        }
        else{
        int i =0;
        for(NodeList<E> n=this.getFirst();!n.getNext().equals(this.getFirst());n=n.getNext()){
            if(i==index){
                E e =n.getContent();
                n.setContent(element);
                return e;   // elemento reemplazado
            }
            i++;
        }
        return null;  
    }}
    
    //PROBADO Y FUNCIONA BIEN
    public int getIndex(E e){
        int i=-1;
        
        if(e.equals(this.getFirst().getContent())){
            return 0;
        }else if(e.equals(this.getLast().getContent())){
            return this.size()-1;
        }else{
        for(NodeList<E> n=this.getFirst();!n.getNext().equals(this.getFirst());n=n.getNext()){
            if(n.getContent().equals(e)){
                i++;
                return i;
            }
            i++;
        }
        return i;
    }}

    //PROBADO Y FUNCIONA BIEN
    @Override
    public String toString() {
        String s = "";
        NodeList<E> t;
        if(this.isEmpty()){
            return "[]";
        }else if(this.size()==1){
            //System.out.println("Entre al to string con un elemento");
            return "["+this.getFirst().getContent()+"]";
        }else if (this.size()==2){
            //System.out.println("Entre al to string con dos elemento");
            return "["+this.getFirst().getContent()+","+this.getLast().getContent()+"]";
        }else if(this.size()==3){
            return "["+this.getFirst().getContent()+","+this.getFirst().getNext().getContent()+","+this.getLast().getContent()+"]";
        }
        else{
        for (t = this.getFirst(); !t.getNext().equals(this.getFirst()); t = t.getNext()) {
            //System.out.println("Entre al to string con"+t.getContent());
            s += t.getContent() + ",";
        }
        return "["+s+this.getLast().getContent()+"]";
        }
        //return "algo salio mal";
    }

    @Override
    public  Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            NodeList<E> cursor=first;
            int i=0;
            @Override
            public boolean hasNext() {
                if(size()==1){
                    return false;
                }else if(cursor.getContent().equals(first.getContent())&&i==0){
                    i++;
                    //System.out.println("entre if");
                    return cursor.getContent()!=null;
                }else if(cursor.getContent().equals(last.getContent())){
                    return true;
                }
                /*else if(i>0){
                    //System.out.println("Entre else if");
                    return false; //si ya estoy en el ultimo, quiero que se detenga
                }*/else{
                    //System.out.println("entre else");
                    return !cursor.getContent().equals(first.getContent());
                }
                //return cursor!=last;
            }

            @Override
            public E next() {
                E e =cursor.getContent();
                cursor =cursor.getNext();
                return e;  //retorno el anterior
            }

        };
        return it;      
    };

}
