package leecode.Array;

import zuoshen.list.Node;

//看到数组插入删除直接考虑尾部操作
//对于数组来说，在尾部插入、删除元素是比较高效的，所以对于一般处理数组的算法问题，我们要尽可能只对数组尾部的元素进行操作，以避免额外的时间复杂度
//https://github.com/labuladong/fucking-algorithm/blob/master/%E9%AB%98%E9%A2%91%E9%9D%A2%E8%AF%95%E7%B3%BB%E5%88%97/%E5%A6%82%E4%BD%95%E5%8E%BB%E9%99%A4%E6%9C%89%E5%BA%8F%E6%95%B0%E7%BB%84%E7%9A%84%E9%87%8D%E5%A4%8D%E5%85%83%E7%B4%A0.md
//对于数组相关的算法问题，有一个通用的技巧：要尽量避免在中间删除元素，那我就想先办法把这个元素换到最后去。
public class 删除排序数组中的重复项_26 {
    public int removeDuplicates(int[] nums) {
        //注意这个数组是有序的，所以快慢指针才可以这样
        int slow=0;
        int fast=1;//初始化时，fast要比slow快1  ！！！
        while (fast<nums.length){
            if(nums[slow]!=nums[fast]){// 1 3 3 4 slow指着第一个3，fast指着4
                slow++;//slow要先移动！！！
                nums[slow]=nums[fast];
            }
            fast++;
        }
        return slow+1;//长度为slow+1,不是返回下标
    }
    //扩展：删除排序链表的重复元素
    public Node deleteDuplicates(Node head){
        if(head==null){
            return null;
        }
        Node slow=head;
        Node fast=head.next;
        while (fast!=null){
            if(slow.value!=fast.value){//1 3 3 4 slow指着第一个3，fast指着4
                //num[slow]=num[fast]//顺序和上面不一样
                slow.next=fast;
                //slow++
                slow=slow.next;
            }
            fast=fast.next;
        }
        slow.next=null;//和后面重复元素断开
        return head;
    }
}
