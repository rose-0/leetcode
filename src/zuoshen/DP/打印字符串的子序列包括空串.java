package zuoshen.DP;
//写递归时注意写终止条件和return语句（知道在哪里return）
public class 打印字符串的子序列包括空串 {
    public static void print_sub(int index,String sub,char[] res){
        if(index==res.length){
//            System.out.println(sub);
            return;//return一定要有
        }
        System.out.println(sub);
        print_sub(index+1,sub,res);//index处字串取空从index+1处开始所有的子串
//        System.out.println(sub);  在最后才进行输出，而不是中间输出
        print_sub(index+1,sub+res[index],res);//index处字串取res[index]从index+1处开始所有的子串
//        System.out.println(sub);
    }

    //错误写法
    public static void subString(String str,int index,StringBuilder temp){//temp使用
        //stringBuilder不能还原，不能自动new，使用String可以自己生产新的，因为String时不变的
        if(index==str.length()){
            System.out.println(temp);
            temp=new StringBuilder();
            //没有写return！！
        }
        subString(str,index+1,temp);
        subString(str,index+1,temp.append(str.charAt(index)));
    }


    public static void main(String[] args) {
        String s="abc";
        print_sub(0,"",s.toCharArray());
    }
}
