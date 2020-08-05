package leecode.string;
//https://leetcode-cn.com/problems/add-strings/solution/add-strings-shuang-zhi-zhen-fa-by-jyd/
public class 字符串相加_415 {
    //手动模拟加法近位置
    //字符串加法，链表加法（lee 2），二进制加法（lee 67）都可以这么写
    public String addStrings(String num1, String num2) {
        StringBuilder res =new StringBuilder();
        int i=num1.length()-1;
        int j=num2.length()-1;
        int carry=0;//代表进位
        while (i>=0||j>=0){//从后向前！！不是从前向后，这儿是 || 的关系，所以解决了长度不一致问题
            /*
            流程：
                拿到各自最后一位
                和进位相加 temp
                更新进位 temp/10
                append到末尾(temp%10!!!)
                i--,j--
                跳出循环要判断进位，且要反转字符串！！
             */
            int n1=i>=0?num1.charAt(i)-'0':0;//要判断j>=0 !!，因为上面是或的关系
            int n2=j>=0?num2.charAt(j)-'0':0;
            int temp=n1+n2+carry;//这里把进位加上啦   记得这个！！
            carry=temp/10;       //二进制加法把这个10 变成 2 ，不是carry=temp/10>0?1:0不是这个
            res.append(temp%10); //二进制加法把这个10 变成 2
            i--;
            j--;
        }
        if(carry==1){
            res.append(1);
        }
        return res.reverse().toString();
    }
}
