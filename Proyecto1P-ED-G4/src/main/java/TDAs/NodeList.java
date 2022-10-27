package TDAs;

public class NodeList<E> {
    public E content;
    public NodeList<E> next;
    public NodeList<E> previous;

    public NodeList(E content) {
        this.content = content;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public NodeList<E> getNext() {
        return next;
    }

    public void setNext(NodeList<E> next) {
        this.next = next;
    }

    public NodeList<E> getPrevious() {
        return previous;
    }

    public void setPrevious(NodeList<E> previous) {
        this.previous = previous;
    }
    
    public boolean equals(NodeList<E> nl){
            return this.getContent().equals(nl.getContent());
        
    }
    
}
