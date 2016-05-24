package com.nitian.linked;

/**
 * 链表的节点
 * 
 * @author 1036225283
 *
 */
public class NodeLinked {

	private int value;

	private NodeLinked next;
	private NodeLinked prev;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public NodeLinked getNext() {
		return next;
	}

	public void setNext(NodeLinked next) {
		this.next = next;
	}

	public NodeLinked getPrev() {
		return prev;
	}

	public void setPrev(NodeLinked prev) {
		this.prev = prev;
	}

}
