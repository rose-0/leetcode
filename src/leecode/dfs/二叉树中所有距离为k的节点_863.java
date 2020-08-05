package leecode.dfs;

import sun.reflect.generics.tree.Tree;


import java.util.*;

//https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree/comments/
//finley   的答案
public class 二叉树中所有距离为k的节点_863 {
    private Map<TreeNode,TreeNode> parents=new HashMap<>();//构建子节点指向父节点

    private Set<TreeNode>used=new HashSet<>();//标记已经访问

    public List<Integer> distanceK(TreeNode root,TreeNode target,int K){
        findparent(root,null);
        List<Integer>res=new LinkedList<>();
        dfs(target,res,K);
        return new LinkedList<>();

    }

    //树做dfs时需要转化为图，也就是可以各个方向游走，需要指向parent的指针
    public void findparent(TreeNode root,TreeNode parent){
        if(root==null){
            return;
        }
        parents.put(root,parent);
        findparent(root.left,root);
        findparent(root.right,root);
    }

    private void dfs(TreeNode root,List<Integer>res,int distance){
        if(root!=null&&!used.contains(root)){
            used.add(root);
            if(distance==0){
                res.add(root.val);
                return;
            }
            dfs(root.left,res,distance-1);
            dfs(root.right,res,distance-1);
            dfs(parents.get(root),res,distance-1);
        }
    }


    List<Integer> res=new ArrayList<Integer>();
    public List<Integer> distanceK2(TreeNode root, TreeNode target, int K) {
        Map<TreeNode,TreeNode> map=new HashMap<TreeNode, TreeNode>();
        findParent(root,map);
        HashSet<TreeNode> used=new HashSet<TreeNode>();
//      findTarget(root,target,K,map);
        dfs(target,K,map,used);
        return res;
    }
    public  void findParent(TreeNode root,Map<TreeNode,TreeNode>map){
        if(root==null){
            return;
        }
        if(root.left!=null){
            map.put(root.left,root);
        }
        if(root.right!=null){
            map.put(root.right,root);
        }
        findParent(root.left,map);
        findParent(root.right,map);
    }
    public  void dfs(TreeNode target, int K, Map<TreeNode, TreeNode>map,HashSet<TreeNode>used){
        if(target==null){
            return;
        }
        if(K==0){
            res.add(target.val);
            return;
        }
        used.add(target);
        if (!used.contains(target.left)) {
            dfs(target.left, K - 1, map, used);
        }
        if (!used.contains(target.right)) {
            dfs(target.right, K - 1, map, used);
        }
        if (!used.contains(map.get(target))) {
            dfs(map.get(target), K - 1, map, used);
        }
    }


    //寻找两个节点的公共祖先
    public TreeNode find(TreeNode head,TreeNode node1,TreeNode node2){
        if(head==null||head==node1||head==node2){
            return head;
        }
        TreeNode left=find(head.left,node1,node2);
        TreeNode right=find(head.right,node1,node2);
        if(left!=null&&right!=null){
            return head;
        }
        return left==null?right:left;
    }
}
