package RBTree;

import RBTree.RBTreeNode.NodeColor;

/**
 * 实现红黑树的数据结构，包括插入、查找、删除操作
 * 
 * @author HUSTHuangKai
 *
 */
/*
 * 红黑树的四条规则 1.节点非黑即红 2.根节点为黑 3.红色节点的子节点为黑（空节点视作黑色）
 * 4.任一节点向下至null的任何路径上路径上，黑节点个数相同。
 */
public class RBTreeInt {
	public RBTreeNode root;

	/**
	 * 插入
	 * 
	 * @param value
	 * @return
	 */
	public void insert(int value) {
		if (root == null) {
			root = new RBTreeNode(NodeColor.BLACK, value);
		} else {
			insertBlowNode(root, value);
		}
	}

	/**
	 * 从某节点及以下插入 使用带调整的插入函数，在插入过程中如果发现某个节点X的两个子节点都为红色，就把两个子节点
	 * 变成黑色，X变成红色。如果变色后出现父子节点都为红色的情况，就做一次旋转。
	 * 
	 * @param fromNode
	 * @param value
	 */
	private void insertBlowNode(RBTreeNode fromNode, int value) {
		// 到此节点先做一次调整
		insertAdjust(fromNode);

		// 继续插入
		if (value < fromNode.value) {
			if (fromNode.leftNode == null) {
				fromNode.leftNode = new RBTreeNode(NodeColor.RED, value);
				fromNode.leftNode.parentNode = fromNode;
				// 插入后进行调整，使其满足红黑树的要求
				rotateAdjust(fromNode.leftNode);
			} else {
				insertBlowNode(fromNode.leftNode, value);
			}
		} else {
			if (fromNode.rightNode == null) {
				fromNode.rightNode = new RBTreeNode(NodeColor.RED, value);
				fromNode.rightNode.parentNode = fromNode;
				// 调整
				rotateAdjust(fromNode.rightNode);
			} else {
				insertBlowNode(fromNode.rightNode, value);
			}
		}
	}

	/**
	 * 查找
	 * 
	 * @param target
	 * @return 返回目标节点，如果没找到返回null
	 */
	public RBTreeNode find(int target) {
		return find(root,target);
	}

	/**
	 * 递归查找
	 */
	private RBTreeNode find(RBTreeNode fromNode, int target) {
		if (fromNode == null) {
			return null;
		} else if (fromNode.value == target) {
			return fromNode;
		} else if (fromNode.value > target) {
			return find(fromNode.leftNode, target);
		} else {
			return find(fromNode.rightNode, target);
		}
	}

	/**
	 * 删除
	 * 
	 * @param target
	 * @return 返回删除结果，如果失败返回false
	 */
	public boolean erase(int target) {
		return true;
	}

	/**
	 * 插入过程的调整函数
	 * 
	 * @param insertNode
	 */
	private void insertAdjust(RBTreeNode insertNode) {
		// 如果此节点是根节点，就把他的两个子节点都变成黑色，不破坏红黑树规则
		if (insertNode == root) {
			if (insertNode.leftNode != null) {
				insertNode.leftNode.nodeColor = NodeColor.BLACK;
			}
			if (insertNode.rightNode != null) {
				insertNode.rightNode.nodeColor = NodeColor.BLACK;
			}
			// 如果此节点左右节点都是红色，就都把左右都变成黑色，自己变成红色
		} else if (insertNode.rightNode != null && insertNode.rightNode.nodeColor == NodeColor.RED
				&& insertNode.leftNode != null && insertNode.leftNode.nodeColor == NodeColor.RED) {
			insertNode.rightNode.nodeColor = NodeColor.BLACK;
			insertNode.leftNode.nodeColor = NodeColor.BLACK;
			insertNode.nodeColor = NodeColor.RED;

			// 旋转调整
			rotateAdjust(insertNode);
		}
	}

	/**
	 * 旋转调整
	 */
	private void rotateAdjust(RBTreeNode insertNode) {
		// 如果此节点的父节点是根节点，就把它设为黑色
		if (insertNode.parentNode == root) {
			insertNode.nodeColor = NodeColor.BLACK;
		}
		// 如果此节点的父节点亦为红色，就要进行一次旋转
		if (insertNode.parentNode.nodeColor == NodeColor.RED) {
			RBTreeNode parentNode = insertNode.parentNode;
			RBTreeNode grandNode = parentNode.parentNode;

			// 左外侧
			if (parentNode == grandNode.leftNode && insertNode == parentNode.leftNode) {
				rightRotate(grandNode);

				parentNode.nodeColor = NodeColor.BLACK;
				grandNode.nodeColor = NodeColor.RED;
				// 右外侧
			} else if (parentNode == grandNode.rightNode && insertNode == parentNode.rightNode) {
				leftRotate(grandNode);

				parentNode.nodeColor = NodeColor.BLACK;
				grandNode.nodeColor = NodeColor.RED;
				// 左内侧
			} else if (parentNode == grandNode.leftNode && insertNode == parentNode.rightNode) {
				// 先对parentNode左旋，再对grandParent右旋
				leftRotate(parentNode);
				rightRotate(grandNode);

				insertNode.nodeColor = NodeColor.BLACK;
				grandNode.nodeColor = NodeColor.RED;
				// 右内侧
			} else {
				// 先对parentNode右旋，再对grandParent左旋
				rightRotate(parentNode);
				leftRotate(grandNode);

				insertNode.nodeColor = NodeColor.BLACK;
				grandNode.nodeColor = NodeColor.RED;
			}
		}
	}

	/**
	 * 右单旋
	 * 
	 * @param rotateNode
	 */
	private void rightRotate(RBTreeNode rotateNode) {
		// 保存要旋转节点的父节点，左右子节点
		RBTreeNode parentNode = rotateNode.parentNode;
		RBTreeNode leftNode = rotateNode.leftNode;
		RBTreeNode leftRightNode = leftNode.rightNode;

		// 旋转
		leftNode.rightNode = rotateNode;
		rotateNode.parentNode = leftNode;

		rotateNode.leftNode = leftRightNode;
		if (leftRightNode != null) {
			leftRightNode.parentNode = rotateNode;
		}

		// 改变父节点的指针
		if (parentNode != null) {
			if (parentNode.leftNode == rotateNode) {
				parentNode.leftNode = leftNode;
				leftNode.parentNode = parentNode;
			} else {
				parentNode.rightNode = leftNode;
				leftNode.parentNode = parentNode;
			}
		} else {// 父节点为空说明根节点发生了改变
			this.root = leftNode;
		}
	}

	/**
	 * 左单旋
	 */
	private void leftRotate(RBTreeNode rotateNode) {
		// 保存要旋转节点的父节点，左右子节点
		RBTreeNode parentNode = rotateNode.parentNode;
		RBTreeNode rightNode = rotateNode.rightNode;
		RBTreeNode rightLeftNode = rightNode.leftNode;

		// 旋转
		rightNode.leftNode = rotateNode;
		rotateNode.parentNode = rightNode;

		rotateNode.rightNode = rightLeftNode;
		if (rightLeftNode != null) {
			rightLeftNode.parentNode = rotateNode;
		}

		// 改变父节点的指针
		if (parentNode != null) {
			if (parentNode.leftNode == rotateNode) {
				parentNode.leftNode = rightNode;
				rightNode.parentNode = parentNode;
			} else {
				parentNode.rightNode = rightNode;
				rightNode.parentNode = parentNode;
			}
		} else {// 父节点为空说明根节点发生了改变
			this.root = rightNode;
		}
	}

	@Override
	public String toString() {
		return "[" + toString(root) + "]";
	}

	private String toString(RBTreeNode node) {
		if (node == null) {
			return "";
		} else {
			return toString(node.leftNode) + "," + node.value + toString(node.rightNode);
		}
	}
}
