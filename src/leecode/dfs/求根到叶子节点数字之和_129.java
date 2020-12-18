package leecode.dfs;

import java.util.ArrayList;
import java.util.List;

public class 求根到叶子节点数字之和_129 {
    public int sumNumbers(TreeNode root) {
        if(root==null){
            return 0;
        }
        return childsum(root,0);
    }
    public int childsum(TreeNode root,int sum){
        /*
        如    4
             /
            1
           4右边节点为空应该返回0，而不是40(return 10*sum;)
         */
        if(root==null){
            return 0;
        }
        //遍历到当前层时，上面层累加和为sum
        //如果当前为叶子节点，则没有左右子树，则sum*10加上当前节点的值，并返回！！
        if(root.left==null&&root.right==null){
            return 10*sum+root.val;
        }
        //不是叶子节点，则对左右子树处理，
        return childsum(root.left,10*sum+root.val)+childsum(root.right,10*sum+root.val);
    }
    /*
    这个题目就是递归+回溯的题目（也可以说是树相关的回溯题目）
    https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/solution/129-qiu-gen-dao-xie-zi-jie-dian-shu-zi-zhi-he-di-4/
    类似的题目还有：112 路径总和 113路径总和II 257二叉树的所有路径 类似
     */
    public static int sumNumbers2(TreeNode root) {
        List<Integer>res=new ArrayList<>();
        dfssum(res,new String(),root);

        int sum=0;
        for(Integer num:res){
//            System.out.println(num);
            sum+=num;
        }
        return sum;
    }
    //借用 lee257二叉树的所有路径的写法
    public static void dfssum(List<Integer>res,String path,TreeNode root){
        if(root==null){
            return;
        }
        path=path+root.val;
        if(root.left==null&&root.right==null){
            System.out.println(path);
            char[]chars=path.toCharArray();
            int num=0;
            for (int i = 0; i <chars.length ; i++) {
                num=num*10+chars[i]-'0';//不能使用Integet.valueof(chars[i])
            }
            res.add(num);
            return;
        }
        dfssum(res,path,root.left);
        dfssum(res,path,root.right);
    }
    int sum=0;
    public int sumNumbers3(TreeNode root) {
        List<Integer>path=new ArrayList<>();
         path.add(root.val);
        dfs(path,root);
        return sum;
    }

    //使用清晰的回溯思路 https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/solution/129-qiu-gen-dao-xie-zi-jie-dian-shu-zi-zhi-he-di-4/
    public void dfs(List<Integer>path,TreeNode root){
//        path.add(root.val); 这句话一定要加在调用这个函数的前面才可以
        if(root.left==null&&root.right==null){
            int pathSum=0;
            for(Integer num:path){
                pathSum=pathSum*10+num;
            }
            sum=sum+pathSum;
        }
        if(root.left!=null){
            path.add(root.left.val);
            dfs(path,root.left);
            path.remove(path.size()-1);
        }
        if(root.right!=null){
            path.add(root.right.val);
            dfs(path,root.right);
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        TreeNode root=new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
//        sumNumbers2(root);
        String path="12";
        char[]chars=path.toCharArray();
        int num=0;
        for (int i = 0; i <chars.length ; i++) {
            num=num*10+chars[i]-'0';
        }
        System.out.println(num);
    }

}
