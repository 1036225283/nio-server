package com.nitian.linked;

import org.apache.poi.ss.formula.functions.Now;

/**
 * 有序单链表
 * 
 * @author 1036225283
 *
 */
public class LinkedList {

	/**
	 * 第一个节点
	 */
	private NodeLinked firstNode = null;

	/**
	 * 最后一个节点
	 */
	private NodeLinked lastNode = null;

	/**
	 * 节点的数量
	 */
	private int size = 0;

	public void insert(int value) {
		if (firstNode == null) {
			firstNode = new NodeLinked();
			firstNode.setValue(value);
			return;
		}

		NodeLinked tmpNode = new NodeLinked();
		tmpNode.setValue(value);

		if (value <= firstNode.getValue()) {
			tmpNode.setNext(firstNode);
			firstNode = tmpNode;
			return;
		}

		if (value >= lastNode.getValue()) {
			lastNode.setNext(tmpNode);
		}

	}

	/**
	 * 查找插入的节点（firstNode）
	 * 
	 * @param value
	 * @return
	 */
	public NodeLinked findIndex(int value) {
		NodeLinked tmpNode = firstNode;
		NodeLinked parentNode = null;
		NodeLinked newNode = new NodeLinked();
		newNode.setValue(value);
		boolean go = true;
		while (go) {
			if (tmpNode.getNext() == null) {
				go = false;
			}
			if (value < tmpNode.getValue() && parentNode == null) {
				firstNode = newNode;
				newNode.setNext(tmpNode);
			} else {

			}
		}
		return null;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public NodeLinked getFirstNode() {
		return firstNode;
	}

	public void setFirstNode(NodeLinked firstNode) {
		this.firstNode = firstNode;
	}

	public NodeLinked getLastNode() {
		return lastNode;
	}

	public void setLastNode(NodeLinked lastNode) {
		this.lastNode = lastNode;
	}

}
