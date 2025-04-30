package com.escaping.maze.structures;

public class Stack<T> {
	
    private Node<T> top; // The top of the stack
    private int size; // The current size of the stack

 // A simple class to represent the node in the stack
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) {
            this.data = data;
        }
     // Getter method to access the value of the node
        public T getValue() {
            return data;
        }
     // Getter method to access the next node
        public Node<T> getNext() {
            return next;
        }
    }

    public void push(T data) {
        Node<T> node = new Node<>(data); // Create a new node with the given data
        node.next = top; // Link the new node to the current top
        top = node; // Update the top to the new node
        size++; 
    }
    public Object pop() {
    	// Check if the stack is empty
    	if (top == null) {
    	return null;// Return null if the stack is empty
    	} else {
    		// Retrieve the value stored in the top node
    	Object value = top.getValue();
    	// Move the top pointer to the next node in the stack
    	top = top.getNext();
    	// Decrease the size of the stack
    	size--;
    	// Return the value of the popped node
    	return value;
    	}
    	
    	}
    /*public T pop() {
        if (isEmpty()) {
        	return null;
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }*/
    //bu bir yorum satırı
    
    public Object peek() {
    	if (top == null) {
    	return null; // If the stack is empty, return null
    	} else {
    	return top.getValue();
    	}
    	}

    /*public T peek() {
        return isEmpty() ? null : top.data;
    }*/

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }
}
