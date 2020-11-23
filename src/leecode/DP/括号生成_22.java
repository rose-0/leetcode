package leecode.DP;

import java.util.LinkedList;
import java.util.List;

public class 括号生成_22 {
    public static List<String> generateParenthesis(int n) {
        List<String>res=new LinkedList<>();
        if(n==0){
            return res;
        }
        int length=n*2;
        char[]ch=new char[2];
        ch[0]='(';
        ch[1]=')';
        StringBuilder str=new StringBuilder();
        gene(res,length,str,ch);
//       genne(res,new StringBuilder(),n,n,n);
        return res;
    }
    public static void gene(List<String>res,int length,StringBuilder str,char[]ch){
        if(str.length()==length){
            String s=new String(str);
            if(isvalid(s)) {
                res.add(s);
            }
            return;
        }
        else {
            for (int i = 0; i <=1 ; i++) {
                str.append(ch[i]);
                gene(res, length, str, ch);
                str.deleteCharAt(str.length()-1);
            }
        }
        return;
    }
    public static boolean isvalid(String str){
        if(str==null||str.length()==0){
            return false;
        }
        int num=0;
        char[]chars=str.toCharArray();
        if(chars[0]==')'){
            return false;
        }
        for (int i = 0; i <chars.length ; i++) {

            if(chars[i]!='('&&chars[i]!=')'){
                return false;
            }
            if(chars[i]==')'&&--num<0){
                return false;
            }
            if(chars[i]=='('){
                ++num;
            }
        }
        return num==0;
    }

    //方法二
    // https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
    public void genne(List<String>res,StringBuilder s,int leftnum,int rightnum,int n){
        if(s.length()==n*2){
            res.add(new String(s));
            return;
        }

        if(leftnum>0){
            s.append('(');
            genne(res,s,leftnum-1,rightnum,n);
            s.deleteCharAt(s.length()-1);
        }
        if(leftnum<rightnum){//还未使用的 ( 的数量 小于 ）括号的数量 ，说明此时组成的字符串 左括号 多，需要添加右括号
            // if leftnum>rightnum  说明此时组成的字符串 右括号 多，肯定是不合法的，进行了剪枝
            s.append(')');
            genne(res,s,leftnum,rightnum-1,n);
            s.deleteCharAt(s.length()-1);
        }
    }


    public static void main(String[] args) {
        List<String>res=new LinkedList<>();
        res=generateParenthesis(3);
        for (String s:res
             ) {
            System.out.println(s);
        }
    }
}
