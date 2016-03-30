package xws;

/**
 * 链表节点
 * 
 * @author 1036225283
 *
 */
public class Node<T> {

	private Node<T> next;
	private Node<T> prev;
	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node<T> getPrev() {
		return prev;
	}

	public void setPrev(Node<T> prev) {
		this.prev = prev;
	}

}
