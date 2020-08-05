package leecode.other;

//https://leetcode-cn.com/problems/gas-station/solution/onfu-za-du-javaxiang-xi-zhu-shi-by-gsmplaysnswithn/
public class 加油站_134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //先做差值，差值和大于等于0，可以走完，差值为正，可以作为起点
        int start=0;
        int total=0;
        int rank=0;
        for (int i = 0; i <gas.length ; i++) {
            rank=rank+gas[i]-cost[i];//能否从当前节点走到下一个节点 gas[i] 当前节点的油， cost[i] 当前节点到下一个节点的花费
            total=total+gas[i]-cost[i];//判断能否走一圈
            if(rank<0){//差值为负，就重新开始
                start=i+1;
                rank=0;
            }
        }
        return total>=0?start:-1;//total是保证可以跑完一圈。如果一定可以跑完一圈，则必定有个起点
    }
}
