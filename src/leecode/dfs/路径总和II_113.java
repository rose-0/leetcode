package leecode.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//对递归里面一个参数做不断的修改，则递归时不需要返回值的！！
public class 路径总和II_113 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<Integer>list=new LinkedList<>();
        List<List<Integer>>lists=new LinkedList<>();

        pathSum2(root,sum,list,lists);//穿入对象引用的拷贝，这个函数会改变对象的内容，而不会改变他们的拷贝
        return lists;
    }
    //这个递归其实是没有返回值的，而是在递归的过程里面对list不断的进行修改，满足条件则加到lists里面
    public void pathSum2(TreeNode root, int sum,List<Integer>list,List<List<Integer>>lists){
        if(root==null){
            return;
        }
        list.add(root.val);//先把根加到list里面，因为记录的是路径，只有到叶子才可以判断，判断叶子节点且根值为sum，则这个list
        //路径记录的是符合条件的，应该加到lists里面，如果不用new的方式加，则在递归
        //的时候回将list拷贝到下个函数，指向同样的地址，这样一直在一个list列表里面修改
        //而不是创建了多个list
        if(root.left==null&&root.right==null){
            if(sum==root.val){
                lists.add(new LinkedList<>(list));
            }
        }else {//这儿递归函数就想成有一个左孩子和一个右孩子
            pathSum2(root.left,sum-root.val,list,lists);
            pathSum2(root.right,sum-root.val,list,lists);
        }//走向当前节点的左右子树，所以sum减去当前根节点的值
        // 当把左右节点递归的处理完后，此时sum还是sum，移除掉最后一个节点
        list.remove(list.size()-1);//这里可以这样想，假设有根一个左右孩子
        //第28行代码走向左子树，把左孩子加上去了，第28行函数返回前要移除左孩子,左子树递归就是指针移到左孩子，递归结束就是又回到根
        //因为左孩子已经不再路径上面了，所以这儿要有32行
    }


    List<List<Integer>> res=new ArrayList<>();
    public List<List<Integer>> pathSumErroe(TreeNode root, int sum) {
        pathSumhe(root,sum,new ArrayList<>());
        return res;
    }
    /*
    下面是错的
    处理root时，root不为空，就要加到list中！！
    root为叶子节点时判断list要不要加到res里面
    叶子节点时，target-root.val！=0 也要移除节点！
     */
    public void pathSumhe(TreeNode root,int target,List<Integer>list){
        if(root==null){
            return;
        }
//        list.add(root.val); 加上这个
        if(root.left==null&&root.right==null){
            if(target-root.val==0){
                list.add(root.val);
                res.add(new ArrayList<>(list));
//                list.remove(list.size()-1);放在这里就不对了！！，这个if外面也要有！，
//                      即target-root.val！=0也要移出去
            }
            //list.remove(list.size()-1);  加上这个,
            return;//这个可以加，因为叶子节点，左右为空，不用递归
        }
        list.add(root.val);
        pathSumhe(root.left,target-root.val,list);
        pathSumhe(root.right,target-root.val,list);
        list.remove(list.size()-1);
    }
}
