package zuoshen.Tree;

import java.util.ArrayList;
import java.util.HashMap;

public class 二叉树中找到累加和为给定值的最长路径长度 {
    public int max_path(treeNode head,int sum){
        HashMap<Integer,Integer>map=new HashMap<>();
        map.put(0,0);//求路径和使用map是都要先记录第一个节点，
        // map.put(0,0)的意思是和为0的层数是0，因为层数是从1开始的，
        // map初始化时就是放入什么也不做时(和为0)，的层数（初始层数-1）
        return find_path(head,sum,0,1,map,0);
    }
    //这个题与下面那个不一样，这个是需要返回值的，
    // 因为这个不是对一个传入的参数做不受递归影响的修改，
    //而且这个不需要对叶子节点做判断，因为题目没有要求叶子节点
    //这个是要对每个节点做判断，每个节点更新cursum，
    // 再判断要不要更新map和max。（下面是更新list，和sum，再判断）
    // 然后左右分别递归求max，最后回溯时要恢复
    public int find_path(treeNode head,int sum,int presum,
                         int level,HashMap<Integer,Integer>map,int max){
        if(head==null){
            return max;
        }
        int cursum=presum+head.value;
        if(!map.containsKey(cursum)){
            map.put(cursum,level);//这儿是做了修改
        }
        if(map.containsKey(cursum-sum)){
            max=Math.max(max,level-map.get(cursum-sum));
        }
        max=find_path(head.left,sum,cursum,level+1,map,max);
        max=find_path(head.right,sum,cursum,level+1,map,max);
        if(level==map.get(cursum)){//回溯的时候要将修改的操作恢复
            map.remove(cursum);
        }
        return max;
    }
    /*public ArrayList<ArrayList<Integer>>listAll=new ArrayList<>();
    public ArrayList<Integer>list=new ArrayList<>();
    public ArrayList<ArrayList<Integer>>findPath(treeNode head,int sum){
        if(head==null)return listAll;
        list.add(head.value);
        sum-=head.value;
        if(sum==0&&head.left==null&&head.right==null){
            listAll.add(new ArrayList<Integer>(list));
        }
        findPath(head.left,sum);
        findPath(head.right,sum);
        list.remove(list.size()-1);
        return listAll;
    }*/
    //这里是更新list，和sum，再判断
    public ArrayList<ArrayList<Integer>>listall=new ArrayList<>();
    public ArrayList<Integer>list=new ArrayList<>();
    public ArrayList<ArrayList<Integer>> found_path(treeNode head,int sum){
        if(head==null){
            return listall;
        }
        list.add(head.value);
        int cursum=sum-head.value;
        if(cursum==0&&head.left==null&&head.right==null){
            listall.add(new ArrayList<Integer>(list));//不new的话，listall一直添加的时同一个list，最终listall里面也是只有一个list
        }
        found_path(head.left,cursum);//这里应该是cursum，不是sum
        found_path(head.right,cursum);
        list.remove(list.size()-1);
        return listall;
    }




    public int getmaxlength(treeNode root,int sum){
        HashMap<Integer,Integer>map=new HashMap<>();
        map.put(0,0);
        return findsum(root,0,sum,map,1,0);
    }
    public int findsum(treeNode root,int cursum,int sum,HashMap<Integer,Integer>map,int level,int max){
        if(root==null){
            return max;
        }
        cursum=cursum+root.value;
        if(!map.containsKey(cursum)){
            map.put(cursum,level);
        }
        if(map.containsKey(cursum-sum)){
            max=Math.max(level-map.get(cursum-sum),max);
        }
        max=findsum(root.left,cursum,sum,map,level+1,max);
        max=findsum(root.right,cursum,sum,map,level+1,max);
        if(level==map.get(cursum)){
            map.remove(cursum);
        }
        return max;
    }
}
