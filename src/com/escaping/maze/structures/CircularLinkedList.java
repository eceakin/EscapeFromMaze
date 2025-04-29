package com.escaping.maze.structures;
public class CircularLinkedList<T> {
    private Node<T> tail; // Tail node of the circular linked list
    private int size; // Size of the list
    
    // Node class representing each element in the list
    private static class Node<T> {
        T data;
        Node<T> next;
     // Constructor for creating a node with given data
        Node(T data) {
            this.data = data;
        }
    }
 // Method to add a new node with data to the circular linked list
    public void add(T data) {
        Node<T> node = new Node<>(data);
        if (tail == null) {
            tail = node;
            node.next = node;
        } else {
            node.next = tail.next;
            tail.next = node;
            tail = node;
        }
        size++;
    }
 // Method to rotate the list, moving the tail pointer to the next node
    public void rotate() {
        if (tail != null) {
            tail = tail.next;
        }
    }
 // Method to convert the circular linked list to an array
    public T[] toArray() {
        T[] arr = (T[]) new Object[size]; // Create a new array of the same size as the list
        if (tail == null) return arr; // If the list is empty, return an empty array

        Node<T> current = tail.next; // Start at the head of the list
        for (int i = 0; i < size; i++) {
            arr[i] = current.data;  // Add each node's data to the array
            current = current.next; // Move to the next node
        }
        return arr;
    }
 // Getter for the tail node
    public Node<T> getTail() {
        return tail;
    }
 // Method to get the size of the list
    public int size() {
        return size;
    }
}