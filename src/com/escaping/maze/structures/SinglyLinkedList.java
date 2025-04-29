package com.escaping.maze.structures;
public class SinglyLinkedList<T> {
    private Node<T> head;
    private int size;
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }
    // Method to add a new node with data to the linked list
    public void add(T data) {
        Node<T> node = new Node<>(data); // Create a new node with the given data
        if (head == null) {
            head = node; // If the list is empty, make the new node the head
        } else {
            Node<T> current = head;
            // Traverse the list until the last node
            while (current.next != null)
                current = current.next;
            current.next = node;  // Link the last node to the new node
        }
        size++; // Increase the size of the list
    }
 // Method to get the data at a specific index in the linked list
    public T get(int index) {
        if (index < 0 || index >= size) return null;  // Return null if index is out of bounds
        Node<T> current = head;
     // Traverse the list until the node at the given index
        for (int i = 0; i < index; i++)
            current = current.next;
        return current.data;  // Return the data at the specified index
    }
 // Method to get the size of the list
    public int size() {
        return size;
    }
 // Method to check if the list is empty
    public boolean isEmpty() {
        return head == null;  // Return true if the list is empty, false otherwise
    }
}
