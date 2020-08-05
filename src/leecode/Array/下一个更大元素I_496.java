package leecode.Array;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;
import java.util.Stack;

//https://github.com/labuladong/fucking-algorithm/blob/master/%E6%95%B0%E6%8D%AE%E7%BB%93%E6%9E%84%E7%B3%BB%E5%88%97/%E5%8D%95%E8%B0%83%E6%A0%88.md
//单调栈结构
public class 下一个更大元素I_496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[]ans=new int[nums2.length];//存放nums2中下一个大的元素
        int[]ans1=new int[nums1.length];//存放nums1中下一个大的元素
        //为了找nums1 使用map
        HashMap<Integer,Integer>map=new HashMap<>();
        Stack<Integer>stack=new Stack<>();
        for (int i = nums2.length-1; i >=0 ; i--) {
            while (!stack.isEmpty()&&stack.peek()<=nums2[i]){//因为是倒着放进去的，
                // 所以peek出来是i后面正序排列的元素，其实就是num2[i]后面的元素，
                // 如果比自己小，则从栈中弹出，即剔除掉
                // 继续比较下一个后面的元素，知道找到比它大的元素放进ans中
                stack.pop();
            }
            ans[i]= stack.empty()?-1:stack.peek();//这个肯定是后面比自己大的第一个元素
            map.put(nums2[i],ans[i]);
            stack.push(nums2[i]);//倒着把每个元素都放进去
        }
        for (int i = 0; i <nums1.length ; i++) {
            ans1[i]=map.get(nums1[i]);
        }
        return ans1;
    }
}
