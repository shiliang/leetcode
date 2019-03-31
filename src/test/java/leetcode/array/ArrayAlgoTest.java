package leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ArrayAlgoTest {

    @Test
    public void maxSubArray() {
        ArrayAlgo algo = new ArrayAlgo();
        int result = 0;
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        result = algo.maxSubArray(nums);
        System.out.println(result);
    }

    @Test
    public void nextPermutation() {
        ArrayAlgo algo = new ArrayAlgo();
        int[] nums = {1,2,3};
        algo.nextPermutation(nums);

    }

    @Test
    public void Permu() {
        ArrayAlgo algo = new ArrayAlgo();
        List<List<Integer>> list = new ArrayList<>();
        int[] nums = {1,2,3};
        list = algo.permute(nums);
        
    }

    int[] input = {1,2,3};

    @Test
    public void partition() {
        ArrayAlgo algo = new ArrayAlgo();
        algo.quickSort(input,0,input.length - 1);
        //algo.partition(input,0,input.length -1);

        for (int e: input
             ) {
            System.out.print(e);
        }
    }

    @Test
    public void coinChange() {
        ArrayAlgo algo = new ArrayAlgo();
        algo.coinChange(input,11);
    }

    @Test
    public void subsets() {
        ArrayAlgo algo = new ArrayAlgo();
        algo.subsets(input);
    }
}