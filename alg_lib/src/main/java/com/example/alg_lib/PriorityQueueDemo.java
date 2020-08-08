package com.example.alg_lib;

import java.util.PriorityQueue;

public class PriorityQueueDemo {

    PriorityQueue<Integer> queue = new PriorityQueue<>();

    public void testPriority(){
        queue.add(10);
        queue.add(15);
        queue.add(30);
        queue.add(20);
        queue.add(5);

        // Displaying the PriorityQueue
        System.out.println("Initial PriorityQueue: " + queue);

        // Removing elements using remove() method
        queue.remove(30);
        System.out.println("queue--" + queue);
        queue.remove(5);
        System.out.println("queue--" + queue);
    }
}
