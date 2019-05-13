package RBTree;
/**
 * RBTree的节点类
 * @author HUSTHuangKai
 */
public class RBTreeNode {
	/**
	 * 节点颜色的枚举类型
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
