package leecode.string;
//看的seu的讲解
public class 字符串相乘_43 {
    public static String multiply(String num1, String num2) {
        /*
        索引从0开始，从左到右

        123的第一位2 和 45的第0位4 乘积 08  两个数相乘结果补成 2位数 所以08

        在结果 05535  的第[1,2]位置上  结果的长度设置为 两个乘数的长度之和  不可多1

        1  2  3
           4  5

           1  5
        1  0

         */
        int m1=num1.length()-1;
        int m2=num2.length()-1;
        if(m1<0||m2<0)return "";
        int[]arr=new int[m1+m2+2];
        for (int i = m1; i >=0 ; i--) {//这个和加法不同，不能用while
            //因为 num1的一位要和num2的每一位相乘，所以要使用嵌套的for循环
            for (int j = m2; j >=0 ; j--) {
                int mul=(num1.charAt(i)-'0')*(num2.charAt(j)-'0');

                //这里加上 是为了计算arr[i+j]，因为可能进位
                mul=mul+arr[i+j+1];//mul是应该在arr[i+j]和arr[i+j+1]位置上，arr[i+j+1]之前可能有数，也要加上
                
                arr[i+j]=arr[i+j]+mul/10;//注意这里是累加
                arr[i+j+1]=mul%10;//上面mul=mul+arr[i+j+1]已经加上了（为了进位），所以这里不需要累加

            }
        }
        StringBuilder str=new StringBuilder();
        int i=0;
        for (int j = 0; j <arr.length ; j++) {
            System.out.print(arr[j]+" ");
        }
        while (i<arr.length&&arr[i]==0){
            //因为之前结果都会补成2位数，不够2位用0补充
            i++;
        }
        //这是正序的
        while (i<arr.length) {
            str.append(arr[i++]);
        }
        return new String(str);
    }

    public static void main(String[] args) {
        String s1="123";
        String s2="45";
        System.out.println(multiply(s1,s2));
    }
}
