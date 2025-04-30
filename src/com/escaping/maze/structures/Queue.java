package com.escaping.maze.structures;
import java.util.ArrayList;
import java.util.List;

//Generic implementation of a Queue using a singly linked list
public class Queue<T> {
    private Node<T> front, rear; // Pointers to the front and rear of the queue
    private int size;  // Number of elements in the queue
    // Node class representing an element in the queue
    private static class Node<T> {
        T data; // Value stored in the node
        Node<T> next;  // Reference to the next node
        Node(T data) {
            this.data = data;
        }
    }
 // Adds an element to the end of the queue
    public void enqueue(T data) {
        Node<T> node = new Node<>(data);
        if (rear != null) {
            rear.next = node; // Link the current rear to the new node
        }
        rear = node;
        if (front == null) front = node; // If the queue was empty, set front too
        size++;
    }
 // Removes and returns the front element of the queue
    public T dequeue() {
        if (isEmpty()) {
        	return null;// Return null if queue is empty
        }

        
        T data = front.data;  // Get data from front
        front = front.next; // Move front pointer to the next node
        if (front == null) rear = null;
        size--;
        return data;
    }
    public T peek() {
        return front != null ? front.data : null;
    }
 // Checks if the queue is empty
    public boolean isEmpty() {
        return front == null;
    }
    // Returns the number of elements in the queue
    public int size() {
        return size;
        
    }
    // Returns a list containing all elements in the queue (in order)
    public List<T> getElements() {
        List<T> elements = new ArrayList<>();
        Node<T> current = front;
        while (current != null) {
            elements.add(current.data); // Add each node's data to the list
            current = current.next;
        }
        return elements;
    }
}