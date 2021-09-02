/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedlisted;

/**
 *
 * @author Ayeni O. Femi
 */
public class LinkedListed {

    public Node head;
    public Node tail;

    private class Node {

        private Node next;
        private int num;

        private Node(int num) {
            this.num = num;
            this.next = null;
        }
    }

    private boolean isEmpty() {
        return null == head;
    }

    public int size() {
        Node current = head;
        int size = 0;
        while (null != current) {
            size++;
            current = current.next;
        }

        return size;
    }

    private int getIndexOfNode(Node node) {
        int index = -1;
        Node current = head;
        while (current != null) {
            index++;
            if (current == node) {
                return index;
            }
        }
        return index;
    }
    
    public int getIndexOfElement(int element){
        Node node = new Node(element);
        
        return getIndexOfNode(node);
        
    }

    private boolean isFirst(Node node) {
        return node == head;
    }

    private boolean isLast(Node node) {
        return node == tail;
    }

    private boolean hasNext(Node node) {
        if (isEmpty()) {
            return false;
        }        boolean isLast = isLast(node);
        boolean sized = size() == 1;

        return !(isLast || sized);
    }

    private boolean hasPrevious(Node node) {
        if (isEmpty()) {
            return false;
        }
        return !isFirst(node);
    }

    public int getElementAtIndex(int index) {
        Node indexedNode = getNodeAtIndex(index);
        return indexedNode.num;
    }

    public void addToHead(int num) {
        Node newHead = new Node(num);
        if (!isEmpty()) {
            newHead.next = this.head;
        }
        head = newHead;
    }

    public void addToTail(int num) {
        Node newNode = new Node(num);
        if (isEmpty()) {
            head = newNode;
        } else if (size() == 1) {
            addToSizeOne(num);
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalArgumentException("List is currently empty");
        }
        return this.getElementAtIndex(0);
    }

    public int prune() {
        if (isEmpty()) {
            throw new IllegalArgumentException("List is currently empty");
        }
        return this.getElementAtIndex(size() - 1);
    }

    private void addToSizeOne(int num) {
        Node newNode = new Node(num);
        this.tail = newNode;
        this.head.next = tail;
    }

    private Node getNodeAtIndex(int index) {
        boolean isEmpty = isEmpty();
        boolean isOverSize = index >= size();
        boolean isUnderSize = index < 0;
        if (isEmpty || isOverSize || isUnderSize) {
            throw new IllegalArgumentException("index " + index + " out of bounds");
        }
        int counter = 0;
        Node current = head;
        while (counter < index) {
            counter++;
            current = current.next;
        }

        return current;
    }

    public void addToIndex(int index, int num) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException("index " + index + " out of bounds");
        }
        if (size() == 1) {
            this.addToSizeOne(num);
        } else if (index == size() - 1) {
            Node newNode = new Node(num);
            Node old_tail = this.tail;
            head.next = newNode;
            newNode.next = old_tail;

        } else {
            Node newNode = new Node(num);

            Node b4 = this.getNodeAtIndex(index - 1);
            Node actual = this.getNodeAtIndex(index);
//            Node after = this.getNodeAtIndex(index + 1);

            b4.next = newNode;
            newNode.next = actual;
        }
    }

    public void removeFromIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IllegalArgumentException("index " + index + " out of bounds");
        }
        int size = size();
        if (size == 1) {
            head = tail = null;
        } else if (index == size - 1) {
            tail = this.getNodeAtIndex(size - 2);

        } else {

            Node b4 = this.getNodeAtIndex(index - 1);
            Node actual = this.getNodeAtIndex(index);
            Node after = this.getNodeAtIndex(index + 1);

            actual = null;
            b4.next = after;
        }
    }

    public boolean contains(int num) {
        if (isEmpty()) {
            return false;
        }
        Node current = head;
        while (null != current) {
            int nodeNum = current.num;
            if (nodeNum == num) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        String stringed = "[";
        Node current = head;
        while (current != null) {
            int n = current.num;
            stringed += ("" + n + ",");
            current = current.next;
        }
        if (stringed.endsWith(",")) {
            stringed = stringed.substring(0, stringed.length() - 1);
            stringed += "]";
        }
        return stringed;
    }

    public LinkedListed reveserList() {
        if (isEmpty()) {
            throw new IllegalArgumentException("empty list irrversible");
        }
        LinkedListed reversed = new LinkedListed();
        if (this.size() == 1) {
            reversed.head = head;
        } else {

            int backer = this.size() - 1;
            Node back = tail;

            while (backer >= 0) {
                int n = back.num;
                reversed.addToTail(n);
                backer--;
                if (backer != -1) {
                    back = this.getNodeAtIndex(backer);
                }
            }
        }
        return reversed;
    }
    
    public void rotateList(int k){
        if (isEmpty()) {
            throw new IllegalArgumentException("empty list not rotatble");
        }
        if(this.size() == 1){
            return;
        }
        for(int i=0; i<k; i++){
            Node old_tail = this.tail;
            Node new_tail = this.getNodeAtIndex(this.size()-2);
            Node old_head = this.head;
            
            this.head = old_tail;
            this.head.next = old_head;
            new_tail.next = null;
            this.tail = new_tail;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        browserHistory();
        rotatedList(4);
    }
    
    /**
     * solves quetion @ https://leetcode.com/problems/design-browser-history/
     */
    private static void browserHistory(){
        /**
         * 1=> leetcode.com
         * 2=> google.com
         * 3=> facebook.com
         * 4=> linkdln.com
         */
        BrowserHistory browser = new BrowserHistory(1);
        
        browser.visit(2);
        browser.visit(3);
        browser.visit(4);
        
        System.out.println(browser.forward(2));
        System.out.println(browser.back(2));
        System.out.println(browser.back(7));
    }
    
    
    /**
     * solves question @ https://leetcode.com/problems/reverse-linked-list/
     */
    private static void reversedLinkList(){
        
        LinkedListed mylist = new LinkedListed();
        mylist.addToTail(1);
        mylist.addToTail(2);
        mylist.addToTail(3);
        mylist.addToTail(4);
        mylist.addToTail(5);

        System.out.println(mylist);
        LinkedListed reversedList = mylist.reveserList();
        System.out.println(reversedList);
    }
    
    /**
     * solves question @ https://leetcode.com/problems/rotate-list/
     * @param k how many times you want the list to be rotated
     */
    public static void rotatedList(int k){
        LinkedListed list = new LinkedListed();
        
        list.addToHead(0);
        list.addToTail(1);
        list.addToTail(2);
//        list.addToTail(4);
//        list.addToTail(5);
        list.rotateList(k);
        System.out.println(list.toString());
    }
    
    /**
     * Assignment: https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/
     */
    
    public static void removeConsecutivePairThatSumsToZero(){
        //iterate over the list from head
        //for each iteration, hold reference to its next node
        //Find the sum of data at the current node and its next node
        //if the sum is zero, remove both node from the list and reassign head to next node of the next node(smiles)
        //else leave the current node intact and re-initialize the current node to its next node
        //continue with your iteration until the node before the tail and the tail are examined
        
        //And all shall be well
    }

}
