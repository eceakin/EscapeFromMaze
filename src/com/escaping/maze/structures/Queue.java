package com.escaping.maze.structures;

import java.util.ArrayList;
import java.util.List;

public class Queue<T> {
    private Node<T> front, rear;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) {
            this.data = data;
        }
    }

    public void enqueue(T data) {
        Node<T> node = new Node<>(data);
        if (rear != null) {
            rear.next = node;
        }
        rear = node;
        if (front == null) front = node;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
        	return null;
        }
        T data = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return data;
    }

    public T peek() {
        return front != null ? front.data : null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }public List<T> getElements() {
        List<T> elements = new ArrayList<>();
        Node<T> current = front;
        while (current != null) {
            elements.add(current.data);
            current = current.next;
        }
        return elements;
    }
}