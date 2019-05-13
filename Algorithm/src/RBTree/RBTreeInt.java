package RBTree;

import RBTree.RBTreeNode.NodeColor;

/**
 * ʵ�ֺ���������ݽṹ���������롢���ҡ�ɾ������
 * 
 * @author HUSTHuangKai
 *
 */
/*
 * ��������������� 1.�ڵ�Ǻڼ��� 2.���ڵ�Ϊ�� 3.��ɫ�ڵ���ӽڵ�Ϊ�ڣ��սڵ�������ɫ��
 * 4.��һ�ڵ�������null���κ�·����·���ϣ��ڽڵ������ͬ��
 */
public class RBTreeInt {
	public RBTreeNode root;

	/**
	 * ����
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
	 * ��ĳ�ڵ㼰���²��� ʹ�ô������Ĳ��뺯�����ڲ���������������ĳ���ڵ�X�������ӽڵ㶼Ϊ��ɫ���Ͱ������ӽڵ�
	 * ��ɺ�ɫ��X��ɺ�ɫ�������ɫ����ָ��ӽڵ㶼Ϊ��ɫ�����������һ����ת��
	 * 
	 * @param fromNode
	 * @param value
	 */
	private void insertBlowNode(RBTreeNode fromNode, int value) {
		// ���˽ڵ�����һ�ε���
		insertAdjust(fromNode);

		// ��������
		if (value < fromNode.value) {
			if (fromNode.leftNode == null) {
				fromNode.leftNode = new RBTreeNode(NodeColor.RED, value);
				fromNode.leftNode.parentNode = fromNode;
				// �������е�����ʹ������������Ҫ��
				rotateAdjust(fromNode.leftNode);
			} else {
				insertBlowNode(fromNode.leftNode, value);
			}
		} else {
			if (fromNode.rightNode == null) {
				fromNode.rightNode = new RBTreeNode(NodeColor.RED, value);
				fromNode.rightNode.parentNode = fromNode;
				// ����
				rotateAdjust(fromNode.rightNode);
			} else {
				insertBlowNode(fromNode.rightNode, value);
			}
		}
	}

	/**
	 * ����
	 * 
	 * @param target
	 * @return ����Ŀ��ڵ㣬���û�ҵ�����null
	 */
	public RBTreeNode find(int target) {
		return find(root,target);
	}

	/**
	 * �ݹ����
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
	 * ɾ��
	 * 
	 * @param target
	 * @return ����ɾ����������ʧ�ܷ���false
	 */
	public boolean erase(int target) {
		return true;
	}

	/**
	 * ������̵ĵ�������
	 * 
	 * @param insertNode
	 */
	private void insertAdjust(RBTreeNode insertNode) {
		// ����˽ڵ��Ǹ��ڵ㣬�Ͱ����������ӽڵ㶼��ɺ�ɫ�����ƻ����������
		if (insertNode == root) {
			if (insertNode.leftNode != null) {
				insertNode.leftNode.nodeColor = NodeColor.BLACK;
			}
			if (insertNode.rightNode != null) {
				insertNode.rightNode.nodeColor = NodeColor.BLACK;
			}
			// ����˽ڵ����ҽڵ㶼�Ǻ�ɫ���Ͷ������Ҷ���ɺ�ɫ���Լ���ɺ�ɫ
		} else if (insertNode.rightNode != null && insertNode.rightNode.nodeColor == NodeColor.RED
				&& insertNode.leftNode != null && insertNode.leftNode.nodeColor == NodeColor.RED) {
			insertNode.rightNode.nodeColor = NodeColor.BLACK;
			insertNode.leftNode.nodeColor = NodeColor.BLACK;
			insertNode.nodeColor = NodeColor.RED;

			// ��ת����
			rotateAdjust(insertNode);
		}
	}

	/**
	 * ��ת����
	 */
	private void rotateAdjust(RBTreeNode insertNode) {
		// ����˽ڵ�ĸ��ڵ��Ǹ��ڵ㣬�Ͱ�����Ϊ��ɫ
		if (insertNode.parentNode == root) {
			insertNode.nodeColor = NodeColor.BLACK;
		}
		// ����˽ڵ�ĸ��ڵ���Ϊ��ɫ����Ҫ����һ����ת
		if (insertNode.parentNode.nodeColor == NodeColor.RED) {
			RBTreeNode parentNode = insertNode.parentNode;
			RBTreeNode grandNode = parentNode.parentNode;

			// �����
			if (parentNode == grandNode.leftNode && insertNode == parentNode.leftNode) {
				rightRotate(grandNode);

				parentNode.nodeColor = NodeColor.BLACK;
				grandNode.nodeColor = NodeColor.RED;
				// �����
			} else if (parentNode == grandNode.rightNode && insertNode == parentNode.rightNode) {
				leftRotate(grandNode);

				parentNode.nodeColor = NodeColor.BLACK;
				grandNode.nodeColor = NodeColor.RED;
				// ���ڲ�
			} else if (parentNode == grandNode.leftNode && insertNode == parentNode.rightNode) {
				// �ȶ�parentNode�������ٶ�grandParent����
				leftRotate(parentNode);
				rightRotate(grandNode);

				insertNode.nodeColor = NodeColor.BLACK;
				grandNode.nodeColor = NodeColor.RED;
				// ���ڲ�
			} else {
				// �ȶ�parentNode�������ٶ�grandParent����
				rightRotate(parentNode);
				leftRotate(grandNode);

				insertNode.nodeColor = NodeColor.BLACK;
				grandNode.nodeColor = NodeColor.RED;
			}
		}
	}

	/**
	 * �ҵ���
	 * 
	 * @param rotateNode
	 */
	private void rightRotate(RBTreeNode rotateNode) {
		// ����Ҫ��ת�ڵ�ĸ��ڵ㣬�����ӽڵ�
		RBTreeNode parentNode = rotateNode.parentNode;
		RBTreeNode leftNode = rotateNode.leftNode;
		RBTreeNode leftRightNode = leftNode.rightNode;

		// ��ת
		leftNode.rightNode = rotateNode;
		rotateNode.parentNode = leftNode;

		rotateNode.leftNode = leftRightNode;
		if (leftRightNode != null) {
			leftRightNode.parentNode = rotateNode;
		}

		// �ı丸�ڵ��ָ��
		if (parentNode != null) {
			if (parentNode.leftNode == rotateNode) {
				parentNode.leftNode = leftNode;
				leftNode.parentNode = parentNode;
			} else {
				parentNode.rightNode = leftNode;
				leftNode.parentNode = parentNode;
			}
		} else {// ���ڵ�Ϊ��˵�����ڵ㷢���˸ı�
			this.root = leftNode;
		}
	}

	/**
	 * ����
	 */
	private void leftRotate(RBTreeNode rotateNode) {
		// ����Ҫ��ת�ڵ�ĸ��ڵ㣬�����ӽڵ�
		RBTreeNode parentNode = rotateNode.parentNode;
		RBTreeNode rightNode = rotateNode.rightNode;
		RBTreeNode rightLeftNode = rightNode.leftNode;

		// ��ת
		rightNode.leftNode = rotateNode;
		rotateNode.parentNode = rightNode;

		rotateNode.rightNode = rightLeftNode;
		if (rightLeftNode != null) {
			rightLeftNode.parentNode = rotateNode;
		}

		// �ı丸�ڵ��ָ��
		if (parentNode != null) {
			if (parentNode.leftNode == rotateNode) {
				parentNode.leftNode = rightNode;
				rightNode.parentNode = parentNode;
			} else {
				parentNode.rightNode = rightNode;
				rightNode.parentNode = parentNode;
			}
		} else {// ���ڵ�Ϊ��˵�����ڵ㷢���˸ı�
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
