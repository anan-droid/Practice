package com.example.demo;

import java.util.HashMap;
import java.util.Map;

class Node {
	Node prev;
	final String key;
	Node next;		
	
	public Node(String key) {
		this.key = key;
	}
}
class CacheItem {
	String data;
	Node node;
	public CacheItem(String data, Node node) {
		this.data = data;
		this.node = node;
	}
}
public class LRUCache {
	private Map<String, CacheItem> cache = new HashMap();
	private Node head = null;
	private Node tail = null;	
	private int capacity;
	
	public LRUCache(int capacity) {
		this.capacity = capacity;
	}
	private void updateList(Node node) {		
		if(node==head) {
			return;
		}
		Node tempprev = node.prev;
		Node tempnxt = node.next;
		tempprev.next = tempnxt;
		if(tempnxt!=null) tempnxt.prev = tempprev;
		head.prev = node;
		node.next = head;
		node.prev = null;
		head = node;
	}
	public void put(String key, String value) {
		if(cache.containsKey(key)) {
			CacheItem cacheItem = cache.get(key);
			updateList(cacheItem.node);
			return;
		}
		if(this.capacity==cache.size()) {
			cache.remove(tail.key);
			tail = tail.prev;
			tail.next = null;						
		}
		Node newNode = new Node(key);		
		newNode.prev = null;
		newNode.next = head;
		if(head==null) {
			head = newNode;
			tail = newNode;			
			cache.put(key, new CacheItem(value, head));		
			return;
		}		
		head.prev = newNode;		
		head = newNode;		
		cache.put(key, new CacheItem(value, head));		
		return;			
	}
	public String get(String key) {
		if(!cache.containsKey(key)) {
			return null;
		}
		String value = cache.get(key).data;	
		updateList(cache.get(key).node);
		return value;
	}
	
	public void printCache() {
		for(String key:cache.keySet()) {
			System.out.println("key:"+key+"value:"+cache.get(key).data);
		}
	}
	
	public static void main(String[] args ) {
		LRUCache lru = new LRUCache(5);
		lru.put("1", "one");
		lru.put("2", "two");
		lru.put("3", "three");
		lru.put("4", "four");
		lru.put("5", "five");
		lru.put("6", "six");
		lru.printCache();
		System.out.println(lru.get("1"));
		System.out.println(lru.get("2"));
		
	}
}

