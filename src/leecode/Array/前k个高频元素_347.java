package leecode.Array;

import java.util.*;
//https://leetcode-cn.com/problems/top-k-frequent-elements/solution/leetcode-di-347-hao-wen-ti-qian-k-ge-gao-pin-yuan-/
public class 前k个高频元素_347 {
    public static int[] topKFrequent(int[] nums, int k) {
            Map<Integer,Integer>map=new HashMap<>();
            for (int i = 0; i <nums.length ; i++) {
                map.put(nums[i],map.getOrDefault(nums[i],0)+1);
            }
            PriorityQueue<Integer> queue=new PriorityQueue<>( new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return map.get(o1)-map.get(o2);//注意这里！！，比较频率，最小堆，堆里面放的是数，按照各自频率排序
                }
            });

            for(int key:map.keySet()){
                if(queue.size()<k){
                    queue.add(key);
                }
                //最小堆弹出的是频率最小的元素，考虑替换它
                //比较频率！
                else if(map.get(key)>map.get(queue.peek())){//不加else 就错了，不加else的话，上面判断完之后，还要判断下面的if
                    queue.remove();
                    queue.add(key);
                }
            }
            int[]res=new int[k];
            int index=k-1;
        System.out.println(queue.size());
            while (!queue.isEmpty()){
                res[index--]=queue.remove();
            }
            return res;
    }

    //也可以使用大根堆 nlogn 上面是nlogk
    public static int[] topKFrequent2(int[] nums, int k) {
        Map<Integer,Integer>map=new HashMap<>();
        for (int i = 0; i <nums.length ; i++) {
            map.put(nums[i],map.getOrDefault(nums[i],0)+1);
        }
        PriorityQueue<Integer> queue=new PriorityQueue<>( new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o2)-map.get(o1);//大根堆！！
            }
        });
        //全加进去
        for(int key:map.keySet()){
            queue.add(key);
        }
        int[]res=new int[k];
        for (int i = 0; i <k ; i++) {
            res[i]=queue.poll();
        }
        return res;
    }
    public static void main(String[] args) {
        int[]num={3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6};
        int[]res=topKFrequent(num,10);

        for(int i:res){
            System.out.println(i);
        }
    }
}
