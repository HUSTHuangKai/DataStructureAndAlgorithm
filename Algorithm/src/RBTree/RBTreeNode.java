package RBTree;
/**
 * RBTree�Ľڵ���
 * @author HUSTHuangKai
 */
public class RBTreeNode {
	/**
	 * �ڵ���ɫ��ö������
	 * @author HUSTHuangKai
	 */
	public static enum NodeColor {
		RED,BLACK
	}
	
	public RBTreeNode parentNode;
	public RBTreeNode leftNode;
	public RBTreeNode rightNode;
	
	public NodeColor nodeColor = NodeColor.BLACK;
	public int value = 0;
	
	public RBTreeNode() {
		// TODO Auto-generated constructor stub
	}
	public RBTreeNode(NodeColor nodeColor, int value) {
		this.nodeColor = nodeColor;
		this.value = value;
	}
}
