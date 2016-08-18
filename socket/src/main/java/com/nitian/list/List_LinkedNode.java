package com.nitian.list;

/**
 * 链表的节点
 * 
 * @author 1036225283
 *
 */
public class List_LinkedNode {

	private int value;

	private List_LinkedNode next;
	private List_LinkedNode prev;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List_LinkedNode getNext() {
		return next;
	}

	public void setNext(List_LinkedNode next) {
		this.next = next;
	}

	public List_LinkedNode getPrev() {
		return prev;
	}

	public void setPrev(List_LinkedNode prev) {
		this.prev = prev;
	}

}
