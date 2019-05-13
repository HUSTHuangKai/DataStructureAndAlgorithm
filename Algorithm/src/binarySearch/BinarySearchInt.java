package binarySearch;

public class BinarySearchInt {
	public static final int binarySearchInt(int[] nums, int target) {
		int size = nums.length;
		if (size == 0) {
			return -1;
		}
		
		return searchInt(nums, 0, size-1, target);
	}
	
	private static final int searchInt(int[] nums, int begin, int end, int target) {
		if (begin > end) {
			return -1;
		}
		
		int mid = begin + (end - begin) / 2;
		if (nums[mid] == target) {
			return mid;
		} else if (nums[mid] > target) {
			return searchInt(nums, begin, mid - 1 , target);
		} else {
			return searchInt(nums, mid + 1, end, target);
		}
	}
}
