package leecode.dfs;

public class 监控二叉树_968 {
    /*
    https://leetcode-cn.com/problems/binary-tree-cameras/solution/binary-tree-cameras-by-ikaruga/
    比较好理解
    优化：dp
     */
    int sum=0;
    public int minCameraCover(TreeNode root) {
        //根节点的距离是 2 时,需要再装一个摄像头
        if(findLen(root)==2){
            sum++;
        }
        return sum;
    }
    /*

    后序遍历的返回值定义为与摄像头的距离

    如果这里安装了摄像头，就返回 0

     */
    public int findLen(TreeNode root){
        if(root==null){
            //注意这里是返回1 不是返回0， 0代表和摄像头的距离为0 表示此处安装了摄像头
            //设想只有一个节点时，这个函数应该返回2
            return 1;
        }
        int left=findLen(root.left);
        int right=findLen(root.right);
        //自身与摄像头的距离: 跟节点距离摄像头的距离 就是 左右子树 节点的值+1
        int res=Math.min(left,right)+1;
        //左右子节点 有一个 和 摄像头的距离是 2 那么摄像头就监控不到了，就要在该节点处安装摄像头
        if(Math.max(left,right)==2){
            sum++;
            //这里安装了摄像头，就返回 0
            res=0;
        }
        return res;
    }
    /*
    方法二
    https://leetcode-cn.com/problems/binary-tree-cameras/solution/hou-xu-bian-li-jian-dan-yi-dong-by-shi-di-zi-007/
     */
    public int minCameraCover2(TreeNode root) {
        //需要再装一个摄像头
        if(dfs(root)==0){
            sum++;
        }
        return sum;
    }
    /*
    0代表没有相机但是可以被监控，
    1代表不能被监控到且需要(等它父结点或者它自己)相机，
    2代表它那个结点有相机。
     */
    public int dfs(TreeNode root){
        //0：未被覆盖(当前节点未被照到)
        //1：已被覆盖(摄像头已经照到这个节点)
        //2：需放置摄像头

        //到根节点，
        if(root == null) return 1;
        //遍历左节点
        int left = dfs(root.left);
        //遍历右节点
        int right = dfs(root.right);
        //一个节点左右确定后，判断左右节点情况
        //所有情况00,01,02,11,12,22
        //左右孩子中有一个未被覆盖，则当前节点需要放置摄像头，当前节点标志为2
        if(left ==0 || right==0)
        {
            sum++;
            return 2;
        }
        //左右孩子均为已覆盖状态,则当前节点未被覆盖，标志为0
        if(left == 1 && right == 1)
        {
            return 0;
        }
        //若左右孩子为一个覆盖一个放置了摄像头，则当前节点为已被覆盖，标志为1
        if(left+right >= 3)
        {
            return 1;
        }
        //此时已经组合完了根节点所有情况，随便返回一个整数即可
        return 0;
    }
}
