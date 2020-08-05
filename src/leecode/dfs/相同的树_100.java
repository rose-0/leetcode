package leecode.dfs;
/*
判断两个树是相同的：使用两个指针先比较根，相同走左边，再走右边，所以是中序遍历
注意边界的处理：即指针为空的情况，两个都为空和有一个为空的判断，值相等情况，递归判断左右
（这些都是对节点处理的逻辑，走到一个节点具体对节点怎么处理，而怎么走节点是先序，中序，后序的选择）
 */
public class 相同的树_100 {

        public boolean isSameTree(TreeNode p, TreeNode q) {
            if(p==null&&q==null){
                return true;
            }

            if((p!=null&&q==null)||(p==null&&q!=null)){
                return false;
            }
            // if(p==null&&q==null){
            //     return true;
            // }
            if(p.val==q.val){
                return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
            }else{
                return false;
            }
            // if(p.val!=q.val){
            //     return false;
            // }

            // return isSameTree(p.left,q.left)&&isSameTree(p.right,q.right);
        }
    }

