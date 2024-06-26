package hw1;
//-----------------------------------------------------
//Title: Bag Class
//Author: Oğuzhan Altın
//ID: 15052066386
//Section: 1
//Assignment: 1
//Description: This class includes attributes and methods for Bag Class, which is used in Graph Class.
//-----------------------------------------------------

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {
	
	private Node<Item> first;
	private int size;
	
	 private static class Node<Item> {
	        private Item item;
	        private Node<Item> next;
	    }
	 
	 public Bag() {
	        first = null;
	        size = 0;
	    }
	 
	 public boolean isEmpty() {
	        return first == null;
	    }
	 
	 public int getSize() {
	        return size;
	    }
	 
	 public void add(Item item) {
	        Node<Item> oldfirst = first;
	        first = new Node<Item>();
	        first.item = item;
	        first.next = oldfirst;
	        size++;
	    }
	 
	 public Iterator<Item> iterator()  {
	        return new LinkedIterator(first);
	    }

	    private class LinkedIterator implements Iterator<Item> {
	        private Node<Item> current;

	        public LinkedIterator(Node<Item> first) {
	            current = first;
	        }

	        public boolean hasNext()  {
	            return current != null;
	        }

	        public Item next() {
	            if (!hasNext()) throw new NoSuchElementException();
	            Item item = current.item;
	            current = current.next;
	            return item;
	        }
	 

	    }
	    
}
