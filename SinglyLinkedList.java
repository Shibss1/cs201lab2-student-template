import java.util.*;
public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap(){
        List<Node<E>> nodes = new ArrayList<>();

        Node<E> currentNode = this.head;
        // Each indiviual node added in here is still referencing the original SLL
        for (int i = 0; i < size; i++) {
            nodes.add(currentNode);
            currentNode = currentNode.getNext();
        }

        nodes.sort(Comparator.comparing(node -> node.getElement()));

        int smaller = 0;
        int bigger = this.size - 1;

        // Modify the node directly, they are the same entity from the OG list despite being in an ArrayList
        // The memory play tech bro
        for (int i = 0; i < this.size / 2; i++) {
            E tempValue = nodes.get(smaller).element;
            nodes.get(smaller).element = nodes.get(bigger).element;
            nodes.get(bigger).element = tempValue;

            smaller += 1;
            bigger -= 1;
        }
    }
}

