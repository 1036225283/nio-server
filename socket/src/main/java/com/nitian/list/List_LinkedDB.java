package com.nitian.list;

/**
 * 有序双向链表
 * 
 * @author 1036225283
 *
 */
public class List_LinkedDB implements ListXws {

	/**
	 * 第一个节点
	 */
	private List_LinkedNode firstNode = null;

	/**
	 * 最后一个节点
	 */
	private List_LinkedNode lastNode = null;

	/**
	 * 节点的数量
	 */
	private int size = 0;

	public void insert(int value) {
		// 初始化工作
		if (firstNode == null) {
			firstNode = new List_LinkedNode();
			firstNode.setValue(value);
			lastNode = firstNode;
			size = 1;
			return;
		}
		// 如果小于firstNode，直接插入第一个
		if (value <= firstNode.getValue()) {
			// 节点操作
			List_LinkedNode newNode = new List_LinkedNode();
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
			List_LinkedNode newNode = new List_LinkedNode();
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
		List_LinkedNode thisNode = firstNode;// 当前节点
		List_LinkedNode prevNode = null;// 上一个节点
		List_LinkedNode newNode = new List_LinkedNode();
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
		List_LinkedNode tmpNode = firstNode;
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

	public List_LinkedNode getFirstNode() {
		return firstNode;
	}

	public void setFirstNode(List_LinkedNode firstNode) {
		this.firstNode = firstNode;
	}

	public List_LinkedNode getLastNode() {
		return lastNode;
	}

	public void setLastNode(List_LinkedNode lastNode) {
		this.lastNode = lastNode;
	}

	public static void main(String[] args) {
		List_LinkedDB linkedList = new List_LinkedDB();
		linkedList.insert(5);
		linkedList.insert(1);
		linkedList.insert(6);
		linkedList.insert(7);
		linkedList.insert(8);
		linkedList.show();
	}

	@Override
	public void add(int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object last() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void each(Callback_Each callback_Each) {
		// TODO Auto-generated method stub

	}

	@Override
	public ListXws filter(Callback_Filter callback_Filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int indexOf(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
}
