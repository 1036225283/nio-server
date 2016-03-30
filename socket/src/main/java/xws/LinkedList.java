package xws;

/**
 * 单向链表(替代list，可重复，可查找，可统计)
 * 
 * @author 1036225283
 *
 */
public class LinkedList<T> {

	private Node<T> first;// 第一个节点
	private Node<T> last;// 最后一个节点

	public Node<T> removeFirst() {
		return null;
	}

	public void insert(Node<T> node) {
		if (first == null) {
			first = node;
		} else {
			Node<T> thisNode = first;
			while (thisNode.getNext() != null) {
				thisNode = thisNode.getNext();

			}
		}
	}

}
