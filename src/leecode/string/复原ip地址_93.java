package leecode.string;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
//英文版leecode 第一个答案下面的评论 davidluoyes的解法
//每一步是要判断每一段由几位数组成，1?2?3?进行回溯
public class 复原ip地址_93 {
    public static List<String> restoreIpAddresses(String s) {
        List<String> list = new LinkedList<>();
        backtrack(s,list,new StringBuilder(),0,0);
        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        return list;
    }
    private static void backtrack(String s, List<String> list, StringBuilder sb, int index, int level){
//        if(index > s.length() || level > 4) return;//这儿不要忘记，不写这个也没有关系，因为后面进不去，会走到最后结束
         if(index == s.length() && level == 4){//ip地址分为4段，level为4说明已经造成了4段ip，进行第五段构造
            list.add(sb.toString());
            return;
        }
        for(int i = 1;i <= 3;i++){//每段由3位数字组成，一位或者2位（大于10小于99）或者三位（大于100小于255）
            if(index+i > s.length()) break;//这儿不要忘记，不然会越界
            int num = Integer.valueOf(s.substring(index,index+i));//i不从0开始是因为要截取
            if(i == 1 || i==2 && num >=10 && num <=99 || i == 3 && num >= 100 && num <= 255){
                sb.append(num);
                if(level < 3) sb.append(".");//level是从0开始的，level为3时不需要加.直接递归到下一层(level+1为4)加入结果
                backtrack(s,list,sb,index+i,level+1);
                if(level < 3) sb.deleteCharAt(sb.length()-1);//去掉后面的点
                sb.delete(sb.length()-i,sb.length());//这儿不要写成sb.delete(index,index+i)；因为index是s的下标，而不是sb的下标，他们的i是相同的
            }
        }
    }

    public static List<String> method(String s){
        List<String>list=new LinkedList<>();
        StringBuilder str=new StringBuilder();
        method2(list,s,str,0,0);
        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        return list;
    }
    public static void method2(List<String>list,String s,StringBuilder str,int index,int level){
        if(index>s.length()||level>4){
            return;
        }
        if(index==s.length()&&level==4){
            String s2=String.valueOf(str);
            list.add(s2);
            return;
        }
        for (int i = 1; i <4 ; i++) {
            if(index+i>s.length()) break;
            String s1=s.substring(index,index+i);
            int m=Integer.valueOf(s1);
            if(i==1||i==2&&(m>=10&&m<=99)||i==3&&(m>=100&&m<=255)){
                str.append(s1);
                if(level<3){
                    str.append('.');
                }
                method2(list,s,str,index+i,level+1);
                if(level<3) {
                    str.deleteCharAt(str.length() - 1);
                }
                str.delete(str.length()-i,str.length());
            }
        }
    }

    public static List<String> restoreIpAddresses22(String s) {
        List<String>res=new ArrayList<>();
        StringBuilder ip=new StringBuilder();
        restoreip(res,s,0,ip,0);
        return res;
    }
    public static void restoreip(List<String>res,String s,int index,StringBuilder ip,int level){
        if(index==s.length()&&level==4){
            res.add(new String(ip));
        }
        for (int i = 1; i <=3 ; i++) {
            String sub=s.substring(index,index+i);
            int num=Integer.valueOf(sub);
            if(i==1||(i==2&&num>9&&num<100)||(i==3&&num>99&&num<256)){//i==1也要写上，i==1也要加上
                ip.append(num);
                if(level<3){
                    ip.append(".");
                }
                restoreip(res,s,index+i,ip,level+1);
                if(level<3){
                    ip.deleteCharAt(ip.length()-1);
                }
                ip.delete(index,index+i);
            }
        }
    }



    public static void main(String[] args) {
        String str="25525511135";
        restoreIpAddresses(str);
        System.out.println("-------");
//        method(str);
    }
}
//测试代理使用类
