package zuoshen.Tree;

public class 判断t1树是否包含t2树全部的拓扑结构 {
    public boolean contain(treeNode t1,treeNode t2){
        return contain_method(t1,t2)||contain_method(t1.left,t2)||contain_method(t1.right,t2);
    }
    public boolean contain_method(treeNode t1,treeNode t2){
        if(t2==null){
            return true;
        }
        if(t1==null||t1.value!=t2.value){
            return false;
        }
        return contain_method(t1.left,t2.left)&&contain_method(t2.right,t2.right);
    }
}
