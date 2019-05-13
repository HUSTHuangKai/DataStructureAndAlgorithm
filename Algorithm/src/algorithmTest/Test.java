package algorithmTest;

import RBTree.RBTreeInt;

public class Test {
	public static void main(String[] args) {
		RBTreeInt rbTreeInt=new RBTreeInt();
		int[] nums = new int[] {20,15,10,30,5,60,50,40,55,35,70,65,85,80,90};
		for (int i : nums) {
			rbTreeInt.insert(i);
		}
		
		System.out.println(rbTreeInt);
		rbTreeInt.find(60);
		rbTreeInt.find(1);
	}
}
