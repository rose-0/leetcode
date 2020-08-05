package leecode.Array;

import org.omg.PortableInterceptor.INACTIVE;

public class 接雨水_42 {
    //i位置可以接的雨水为 min{max(height[0..i]),max(height[i,end])}-height[i]
    //暴力解法
    public static int trapwithbaoli(int[]height){
        int ans=0;
        for (int i = 0; i <height.length ; i++) {
            int leftmax=0;
            int rightmax=0;
            for (int j = 0; j <=i ; j++) {
                leftmax=Math.max(leftmax,height[j]);
            }
            for (int j = i; j <height.length ; j++) {
                rightmax=Math.max(height[j],rightmax);
            }
            ans+=Math.min(leftmax,rightmax)-height[i];
        }
        return ans;
    }

    //备忘录解法
    //两个数组 lmax，rmax
    public static int trapwithmemo(int[]height){
        int[]lmax=new int[height.length];
        int[]rmax=new int[height.length];
        lmax[0]=height[0];//0位置左侧最大值为自己
        /*
        for (int i = 0; i <leftmax.length ; i++) {
            for (int j = 0; j <i ; j++) {
                leftmax[i]=Math.max(leftmax[i],height[j]);
            }
        }
         */
        //记得可以这样求左右侧最大值，而不是和上面那么麻烦
        for (int i = 1; i <height.length ; i++) {
            lmax[i]=Math.max(lmax[i-1],height[i]);
        }
        rmax[height.length-1]=height[height.length-1];
        for (int i = height.length-2; i >=0 ; i--) {
            rmax[i]=Math.max(rmax[i+1],height[i]);
        }
        int res=0;
        for (int i = 0; i <height.length ; i++) {
            res+=Math.min(lmax[i],rmax[i])-height[i];//记得减去height[i]
        }
        return res;
    }

    //双指针技巧
    public static int trapwithzhizhen(int[]height){
        int left=0;
        int right=height.length-1;
        int lmax=height[0];
        int rmax=height[height.length-1];
        int ans=0;
        while (left<right){
            lmax=Math.max(lmax,height[left]);//     lmax指[0,left], rmax指[right,end]
            rmax=Math.max(rmax,height[right]);//上面 lmax指[0,i],   rmax指[i,end];
                                        //相当于这里 lmax指[0,left] 找到区间[right,end]比lmax大的，就不需要找更大的了！！此时直接更新ans
            if(lmax<rmax){
                ans+=lmax-height[left];
                left++;//在这里移动指针
            }else {
                ans+=rmax-height[right];
                right--;
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        int[]height=new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trapwithbaoli(height));
        System.out.println(trapwithmemo(height));
        System.out.println(trapwithzhizhen(height));
    }
}
