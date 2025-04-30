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
	/*public String get(int i) {
		
		return null;
	}*/
    public String get(int index) {
        // Check if the index is valid (not negative or out of bounds)
        if (index < 0 || index >= size) {
            return null;  // Return null if the index is invalid
        }
        
        // Start from the top of the stack
        Node<T> current = top;
        
        // Traverse the stack to reach the specified index
        for (int i = 0; i < index; i++) {
            current = current.getNext();  // Move to the next node
        }
        
        
        return current.getValue().toString();  // Return the value of the node at the given index
    }

    public Object[] getAll() {
        // Create an array to hold all the elements in the stack
        Object[] elements = new Object[size];
        
        // Start from the top of the stack
        Node<T> current = top;
        
        // Traverse through the stack and store each element in the array
        int index = 0;
        while (current != null) {
            elements[index++] = current.getValue();  
            current = current.getNext();  // Move to the next node in the stack
        }
        
        // Return the array containing all elements in the stack
        return elements;  
    }
}
