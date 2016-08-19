package com.nitian.list;

/**
 * 有序单向链表， 
 * 主要有一下功能：
 * 1.pub(value)
 * 2.get(index)
 * 3.callback(function())
 * 4.size()
 * 5.remove()#index/object#
 * 6.indexOf(value)
 * 7.lastIndexOf(value)
 * 8.map(callback());
 * 9.each();
 * 10.first()
 * 11.last();
 * 12.filter();
 * 
 * @author 1036225283
 *
 */
public class List_Linked {

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

	/**
	 * 向单向列表添加值
	 * @param value
	 */
	public void add(int value) {
		// 初始化工作
		if (firstNode == null) {
			firstNode = new List_LinkedNode();
			firstNode.setValue(value);
			lastNode = firstNode;
			size = 1;
			return;
		}
		List_LinkedNode tmpNode = null;
		// 如果小于firstNode，直接插入第一个
		if (value <= firstNode.getValue()) {
			tmpNode = firstNode;
			List_LinkedNode newNode = new List_LinkedNode();
			newNode.setValue(value);
			firstNode = newNode;
			firstNode.setNext(tmpNode);
			size = size + 1;
			return;
		}

		if (value >= lastNode.getValue()) {
			List_LinkedNode newNode = new List_LinkedNode();
			newNode.setValue(value);
			lastNode.setNext(newNode);
			lastNode = newNode;
			size = size + 1;
			return;
		}
		// 如果大于lastNode，直接插入最后一个
		// 开始查找
		List_LinkedNode thisNode = firstNode;// 当前节点
		List_LinkedNode prevNode = firstNode;// 上一个节点
		List_LinkedNode newNode = new List_LinkedNode();
		newNode.setValue(value);
		while (value < lastNode.getValue()) {
			if (value < thisNode.getValue()) {
				prevNode.setNext(newNode);
				newNode.setNext(thisNode);
				return;
			} else {
				prevNode = thisNode;
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

	/**
	 * 根据索引获取值
	 * @param index
	 * @return
	 */
	public String get(int index) {
		if (index >= size) {
			throw new RuntimeException("index is error,because size : " + size + "must > index : " + index);
		}
		int tmpIndex = 0;
		List_LinkedNode node = firstNode;
		while (tmpIndex < index) {
			node = node.getNext();
			tmpIndex = tmpIndex + 1;
		}
		return node.getValue() + "";
	}

	/**
	 * 返回第一个元素
	 * @return
	 */
	public Object first() {
		return firstNode.getValue();
	}

	/**
	 * 获取最后一个值
	 * @return
	 */
	public Object last() {
		return lastNode.getValue();
	}

	/**
	 * 遍历
	 * @param callback_Each
	 */
	public void each(Callback_Each callback_Each) {
		int index = 0;
		List_LinkedNode node = firstNode;
		while (index < size) {
			callback_Each.callback(index, node.getValue());
			node = node.getNext();
			index = index + 1;
		}
	}

	/**
	 * filter
	 * @param callback_Filter
	 * @return
	 */
	public List_Linked filter(Callback_Filter callback_Filter) {
		List_Linked list = new List_Linked();
		int index = 0;
		List_LinkedNode node = firstNode;
		while (index < size) {
			boolean result = callback_Filter.callback(index, node.getValue());
			if (result == true) {
				list.add(node.getValue());
			}
			node = node.getNext();
			index = index + 1;
		}
		return list;
	}

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

	public int size() {
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
		List_Linked linkedList = new List_Linked();
		linkedList.add(5);
		linkedList.add(1);
		linkedList.add(6);
		linkedList.add(7);
		linkedList.add(8);
		linkedList.show();
		System.out.println("index : " + linkedList.get(0));
		System.out.println("last:" + linkedList.last());
		System.out.println("first:" + linkedList.first());
		linkedList.each(new Callback_Each() {

			@Override
			public void callback(int index, Object value) {
				// TODO Auto-generated method stub
				System.out.println("index = " + index + " and value = " + value);
			}
		});

		System.out.println("this is filter result : ");
		linkedList.filter(new Callback_Filter() {

			@Override
			public boolean callback(int index, Object value) {
				// TODO Auto-generated method stub
				int value_ = (int) value;
				if (value_ % 2 == 0) {
					return true;
				} else {
					return false;
				}
			}
		}).show();
		;

	}
}
