package com.example.alg_lib.cache;

import java.util.HashMap;
import java.util.Map;

public class LruCache {

    private Node head;
    private Node end;
    private int limit;
    public Map<String, Node> hashMap;

    public LruCache(int limit) {
        this.limit = limit;
        hashMap = new HashMap<>();
    }

    public Node get(String key) {
        Node node = hashMap.get(key);
        if (node == null) return null;
        refreshNode(node);
        return node;
    }

    /**
     * 更新node 就是删除node的旧位置，添加到尾部 这里的尾部就是最近访问的 这个是自己设计的 尾部更加方便添加数据和更改指针
     */
    private void refreshNode(Node node) {
        if (node == null) return;
        removeNode(node);
        addNode(node);
    }

    //添加节点，注意end是否为空
    private void addNode(Node node) {
        if (end != null) {
            node.next = null;
            end.next = node;
            node.pre = end;
        }
        end = node;
        if (head == null) {
            head = node;
        }
    }

    //hashmap的作用就是快速定位某个node，然后refresh就是调整该node的指针
    public void put(String key, String value) {
        Node node = hashMap.get(key);
        if (node == null) {
            if (hashMap.size() >= limit) {
                // hashmap 满了，删除队头的元素
                String oldKey = removeNode(head);
                hashMap.remove(oldKey);
            }
            node = new Node(key, value);
            addNode(node);
            hashMap.put(key, node);
        } else {
            node.value = value;
            refreshNode(node);
        }
    }

    //做链表调整 没有从hashmap中移除 仅仅是移除掉了前后指针
    private String removeNode(Node node) {
        if (node == head) {
            head = head.next;
        } else if (node == end) {
            end = end.pre;
        } else {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }
        return node.key;
    }

    public void remove(String key) {
        Node node = hashMap.get(key);
        removeNode(node);
        hashMap.remove(node);
    }

    public void printNode() {
        Node cur = head;
        while (cur != null) {
            System.out.println(cur.key + ":" + cur.value);
            cur = cur.next;
        }
    }


    public class Node {
        Node pre;
        Node next;
        String key;
        String value;

        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }


}
