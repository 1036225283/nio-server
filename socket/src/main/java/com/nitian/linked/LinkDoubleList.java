package com.nitian.linked;

/**
 * 有序双向链表
 * 
 * @author 1036225283
 *
 */
public class LinkDoubleList {

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
		// 初始化工作
		if (firstNode == null) {
			firstNode = new NodeLinked();
			firstNode.setValue(value);
			lastNode = firstNode;
			size = 1;
			return;
		}
		// 如果小于firstNode，直接插入第一个
		if (value <= firstNode.getValue()) {
			// 节点操作
			NodeLinked newNode = new NodeLinked();
			newNode.setValue(value);
			newNode.setNext(firstNode);
			firstNode.setPrev(newNode);
			// 更新操作
			firstNode.setPrev(newNode);
			lastNode = firstNode;
			firstNode = newNode;
			size = size + 1;
			return;
		}

		if (value >= lastNode.getValue()) {
			// 节点操作
			NodeLinked newNode = new NodeLinked();
			newNode.setValue(value);
			newNode.setPrev(lastNode);
			// 更新操作
			lastNode.setNext(newNode);
			lastNode = newNode;
			size = size + 1;
			return;
		}
		// 如果大于lastNode，直接插入最后一个
		// 开始查找
		NodeLinked thisNode = firstNode;// 当前节点
		NodeLinked prevNode = null;// 上一个节点
		NodeLinked newNode = new NodeLinked();
		newNode.setValue(value);
		while (value < lastNode.getValue()) {
			if (value < thisNode.getValue()) {
				prevNode = thisNode.getPrev();// 取得上一个节点
				prevNode.setNext(newNode);// 上一个节点指向新建节点
				newNode.setPrev(thisNode.getPrev());// 新建节点指向上一个节点
				newNode.setNext(thisNode);// 新建节点指向下一个节点
				thisNode.setPrev(newNode);// 原节点prev指向newNode
				return;
			} else {
				thisNode = thisNode.getNext();
			}
		}

	}

	/**
	 * 查找插入的节点（firstNode）
	 * 
	 * @param value
	 * @return
	 */
	// public NodeLinked findIndex(int value) {
	// NodeLinked tmpNode = firstNode;
	// NodeLinked tmpFirst = firstNode;
	// NodeLinked parentNode = null;
	// NodeLinked newNode = new NodeLinked();
	// newNode.setValue(value);
	// while (value < lastNode.getValue()) {
	// if (value < tmpFirst.getValue()) {
	// firstNode = newNode;
	// newNode.setNext(tmpNode);
	// } else {
	// tmpFirst = tmpFirst.getNext();
	// }
	// }
	// return null;
	// }

	public void show() {
		NodeLinked tmpNode = firstNode;
		if (firstNode == null) {
			System.out.println("is empty");
			return;
		}
		System.out.print(firstNode.getValue() + ",");
		while (tmpNode.getNext() != null) {
			tmpNode = tmpNode.getNext();
			System.out.print(tmpNode.getValue() + ",");
		}
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

	public static void main(String[] args) {
		LinkDoubleList linkedList = new LinkDoubleList();
		linkedList.insert(5);
		linkedList.insert(1);
		linkedList.insert(6);
		linkedList.insert(7);
		linkedList.insert(8);
		linkedList.show();
	}
}
