package cai.peter.algorithm.array;

import org.junit.Test;

import java.util.Arrays;

/*
Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

Example:

Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
 */
public class TwoSum {
    public int[] twoSum(int[] num, int target){
        int len = num.length;
        for(int i=0;i<len;i++){
            for(int j=i+1;j<len;j++){
                if( num[i]+num[j]==target) return new int[]{i,j};
            }
        }
        return null;
    }

    @Test
    public void test(){
        int[] result = twoSum(new int[]{2, 7, 11, 15}, 5);
        System.out.println(Arrays.toString(result));
    }
}
