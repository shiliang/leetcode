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
}