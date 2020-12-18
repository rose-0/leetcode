package leecode.dfs;

import java.util.ArrayList;
import java.util.List;

public class 二叉树的所有路径_257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String>res=new ArrayList<>();
//        dfs(res,new StringBuilder(),root);
        return res;
    }
    public void dfs1(List<String>res,StringBuilder path,TreeNode node){
        /*
        node==null的时候添加路径是不对的，因为当上一层dfs的是叶子节点时
        dfs(叶子节点左 为空)
        dfs(叶子节点右 为空)
        将根到叶子节点路径添加了两次
         */
        if(node==null){
            res.add(new String(path));
            return;
        }
        path.append(node.val);
        dfs1(res,path,node.left);
        dfs1(res,path,node.right);
        path.deleteCharAt(path.length()-1);
    }

    public void dfs2(List<String>res,StringBuilder path,TreeNode node){
        if(node==null){
            return;
        }
        /*
        应该到达叶子节点处进行路径的添加
         */
        path.append(node.val);//这个放到前面，在path加到res时候，保证叶子节点也在path里面
        if(node.left==null&&node.right==null){
            res.add(new String(path));
            return;
        }
        dfs2(res,path.append("->"),node.left);//使用StringBuilder 加-> 会造成混乱
        dfs2(res,path.append("->"),node.right);
        path.deleteCharAt(path.length()-1);
    }

    public void dfs3(List<String>res,String path,TreeNode node){
        if(node==null){
            return;
        }
        /*
        应该到达叶子节点处进行路径的添加
         */
        path=path+node.val;//这个放到前面，在path加到res时候，保证叶子节点也在path里面
        if(node.left==null&&node.right==null){
            res.add(new String(path));
            return;
        }
        dfs3(res,path+"->",node.left);//使用String
        dfs3(res,path+"->",node.right);
//        path.deleteCharAt(path.length()-1);//使用String不需要删除了
    }
    //如果要使用递归 撤销的方式
    public void dfs4(List<String>res,StringBuilder path,TreeNode node){
        if(node==null){
            return;
        }
        /*
        应该到达叶子节点处进行路径的添加
         */
        path=path.append(node.val);//这个放到前面，在path加到res时候，保证叶子节点也在path里面
        if(node.left==null&&node.right==null){
            res.add(new String(path));
            return;
        }
        if(node.left!=null){//写成两个分支
            path=path.append("->");
            dfs4(res,path,node.left);
            path.deleteCharAt(path.length()-1);//删除两个
            path.deleteCharAt(path.length()-1);
        }
        if(node.right!=null){//写成两个分支
            path=path.append("->");
            dfs4(res,path,node.right);
            path.deleteCharAt(path.length()-1);//删除两个
            path.deleteCharAt(path.length()-1);
        }
    }
}
