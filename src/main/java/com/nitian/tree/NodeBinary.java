package com.nitian.tree;

/**
 * 二叉节点
 * 
 * @author 1036225283
 *
 */
public class NodeBinary {

	private NodeBinary parent = null;

	private NodeBinary left = null;

	private NodeBinary right = null;

	public NodeBinary getLeft() {
		return left;
	}

	public void setLeft(NodeBinary left) {
		this.left = left;
	}

	public NodeBinary getRight() {
		return right;
	}

	public void setRight(NodeBinary right) {
		this.right = right;
	}

	public NodeBinary getParent() {
		return parent;
	}

	public void setParent(NodeBinary parent) {
		this.parent = parent;
	}

}
