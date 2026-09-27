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
        class Pair {
            E value;
            int index;

            Pair(E value, int index) {
                this.value = value;
                this.index = index;
            }
        }

        List<Pair> valueIndexPair = new ArrayList<>();
        Node<E> currentNode = this.head;

        for (int i = 0; i < size; i++) {
            valueIndexPair.add(new Pair(currentNode.getElement(), i));
            currentNode = currentNode.getNext();
        }

        valueIndexPair.sort(Comparator.comparing(p -> p.value));
        
        int small = 0;
        int big = valueIndexPair.size() - 1;

        for (int i = 0; i < valueIndexPair.size() / 2; i++) {
            int tempBigIndex = valueIndexPair.get(big).index;
            valueIndexPair.get(big).index = valueIndexPair.get(small).index;
            valueIndexPair.get(small).index = tempBigIndex;

            small += 1;
            big -= 1;
        }
        valueIndexPair.sort(Comparator.comparingInt(p -> p.index));

        currentNode = this.head;
        for (int i = 0; i < size; i++) {
            currentNode.element = valueIndexPair.get(i).value;
            currentNode = currentNode.getNext();
        }
    }
}

