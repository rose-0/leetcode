package leecode.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class 序列化和反序列化二叉搜索树_449 {
    /*
    二叉树可以通过前序序列或后序序列和中序序列构造
    二叉搜索树能只够通过前序序列或后序序列构造，因为知道了前序序列或后序序列相当于我们也知道了中序序列，可以通过排序获得。

    还有个序列化 和 反序列化 n叉树 lee428
     */
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder str = new StringBuilder();
        help(root, str);
        return str.toString();
    }

    public void help(TreeNode root, StringBuilder str) {
        if (root == null) {
            return;
        }
        str.append(root.val).append(",");
        help(root.left, str);
        help(root.right, str);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] str = data.split(",");
        return builder(str, 0, str.length - 1);
    }

    public TreeNode builder(String[] str, int left, int right) {
        if (left > right) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(str[left]));
        int index = right + 1;//让没有右子树的情况下，也能正确进行下面的递归。
        //index为找到第一个大于根节点的值
        for (int i = left + 1; i <= right; i++) {
            if (Integer.valueOf(str[i]) > root.val) {
                index = i;
                break;
            }
        }
        root.left = builder(str, left + 1, index - 1);
        root.right = builder(str, index, right);
        return root;
    }

    //使用list 参考lee297
    public TreeNode deserialize2(String data) {
        String[] str = data.split(",");
        Queue<String>list=new ArrayDeque<>();
        for (int i = 0; i <str.length ; i++) {
            list.add(str[i]);
        }
        return help2(list);
    }
    public TreeNode help2(Queue<String> list){
        String cur = list.poll(); //str应该改成队列的形式 这样即使队列为空 poll也不会报错
        if (cur.equals("null")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(cur));
        node.left = help2(list);
        node.right = help2(list);
        return node;
    }
}