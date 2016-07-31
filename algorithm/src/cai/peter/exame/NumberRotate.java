package cai.peter.exame;

import java.util.Arrays;

public class NumberRotate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] d ={1,2,3,4,5,6,7};
		System.out.println(Arrays.toString(d));
		new NumberRotate().rotate(d,3);
		System.out.println(Arrays.toString(d));
	}
	
	public void rotate(int[] nums, int k)
	{
		if( k>nums.length) k = k%nums.length;
		
		
		int[] result = new int[nums.length];
		for(int i=0;i<k;i++)
		{
			result[i] = nums[nums.length-k+i];
		}

		for(int i=k;i<nums.length;i++)
		{
			result[i] = nums[i-k];
		}
		
		System.arraycopy(result, 0, nums, 0, nums.length);
	}

}
