package zuoshen.Tree;

import java.util.LinkedList;
import java.util.Queue;

public class 二叉树的序列化 {
    public String preOrder(treeNode head){
        if(head==null){
            return "#!";
        }
        String res=head.value+"!";
        res+=preOrder(head.left);
        res+=preOrder(head.right);
        return res;
    }
    public treeNode preOrderIn(String str){
        String[]values=str.split("!");
        Queue<String>queue=new LinkedList<>();
        for (int i = 0; i <values.length ; i++) {
            queue.offer(values[i]);
        }
        return parse(queue);
    }
    public treeNode parse(Queue<String>queue){
        String cur=queue.poll();
        if(cur=="#"){
            return null;
        }
        treeNode head=new treeNode(Integer.valueOf(cur));
        head.left=parse(queue);
        head.right=parse(queue);
        return head;
    }
    public String levelOrder(treeNode head){
        if(head==null){
            return "#!";
        }
        Queue<treeNode>queue=new LinkedList<>();
        queue.offer(head);
        String res=head.value+"!";
        while (!queue.isEmpty()){
            treeNode temp=queue.poll();

            if(temp.left!=null){
                res+=temp.left.value+"!";
                queue.offer(temp.left);
            }else {
                res+="#!";
            }
            if(temp.right!=null){
                res+=temp.right.value+"!";
                queue.offer(temp.right);
            }else {
                res+="#!";
            }
        }
        return res;
    }
    public treeNode parLevel(String str){
        if(str=="#!"){
            return null;
        }
        String[]values=str.split("!");
        int index=0;
        treeNode head=gene(values[index++]);
        if(head==null){
            return null;
        }
        Queue<treeNode>queue=new LinkedList<>();
        queue.offer(head);
        treeNode node=null;
        while (!queue.isEmpty()){
            node=queue.poll();
            node.left=gene(values[index++]);
            node.right=gene(values[index++]);
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        return head;
    }
    public treeNode gene(String res){
        if(res=="#"){
            return null;
        }
        return new treeNode(Integer.valueOf(res));

    }
}
