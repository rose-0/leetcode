package leecode.dfs;

import java.util.*;

public class 二叉树的序列化与反序列化_297 {
    // Encodes a tree to a single string.
    //一个函数进行序列化 不知道算不算先序
    // 选择前序遍历，是因为 根|左|右根∣左∣右 的打印顺序，在反序列化时更容易定位出根节点的值。
    //https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/solution/shou-hui-tu-jie-gei-chu-dfshe-bfsliang-chong-jie-f/
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "null,";//不是 return null
        }
        String left = serialize(root.left);
        String right = serialize(root.right);
        return root.val + "," + left + right;  //不是这样写 root+","+left+","+right; 返回的left right里面已经有 ,
    }


    public StringBuilder helperSer(TreeNode root, StringBuilder str) {
        if (root == null) {
            str.append("null,");
            return str;
        }
        str.append(root.val);
        str.append(",");
        str = helperSer(root.left, str);
        str = helperSer(root.right, str);
        return str;
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        String[] str = data.split(",");
        Queue<String> list = new ArrayDeque<>();
        for (int i = 0; i < str.length; i++) {
            list.add(str[i]);
        }
        return deserHelp(list);
    }

    public static TreeNode deserHelp(Queue<String> str) {

//        String cur = str.get(0);//这个不对 如果List 为空，那么这儿就会报错
        String cur = str.poll(); //str应该改成队列的形式 这样即使队列为空 poll也不会报错
        if (cur.equals("null")) {
//            str.remove(0);//改成队列 就不需要 remove
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(cur));
//        str.remove(0);
        node.left = deserHelp(str);
        node.right = deserHelp(str);
        return node;
    }

    //通过bfs 进行序列化和反序列化
    public static String serialize2(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        String res=new String();
        while (!queue.isEmpty()){
            TreeNode cur=queue.poll();
            if(cur!=null){
                res=res+cur.val+",";
                queue.add(cur.left);
                queue.add(cur.right);
            }else {//注意这里
                res=res+"null,";
            }
        }
        return res;
    }
    //通过bfs 反序列化
    public TreeNode deserialize2(String data) {
        String[]values=data.substring(0,data.length()-1).split(",");
        Queue<TreeNode>queue=new LinkedList<>();
        TreeNode root=new TreeNode(Integer.parseInt(values[0]));
        //这个cur的作用可以看这个题解画的图
        //https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/solution/shou-hui-tu-jie-gei-chu-dfshe-bfsliang-chong-jie-f/
        int cur=1;
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode curNode=queue.poll();
            String left=values[cur];
            if(!left.equals("null")){
                curNode.left=new TreeNode(Integer.parseInt(left));
                queue.offer(curNode.left);
            }
            cur++;
            String right=values[cur];
            if(!right.equals("null")){
                curNode.right=new TreeNode(Integer.parseInt(right));
                queue.offer(curNode.right);
            }
            cur++;
        }
        return root;
    }

    public static void main(String[] args) {
//        TreeNode root=new TreeNode(1);
//        root.left=new TreeNode(2);
//        root.right=new TreeNode(3);
//        System.out.println(serialize2(root));
//        System.out.println(serialize(root));
        String str="1,2,3";
        deserialize(str);
    }
}