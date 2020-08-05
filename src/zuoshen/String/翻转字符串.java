package zuoshen.String;

public class 翻转字符串 {
    public static void reverse(String str){
        char[]chars=str.toCharArray();
        fan(chars,0,str.length()-1);//一定要传入 str.toCharArray()进去，
        // 如果传入str，交换时再将str转为数组，这样str是没有变化的！！
        for (int i = 0; i <chars.length ; i++) {
            System.out.print(chars[i]+" ");
        }
        System.out.println();
        int left=-1;
        int right=-1;
        int res=0;
        for (int i = 0; i <str.length() ; i++) {
//            if(chars[i]!=' '){ //这个注释掉好像也可以
                left=i==0 || chars[i-1]==' '?i:left;
                right=i==chars.length-1||chars[i+1]==' '?i:right;
//            }
            if(left!=-1&&right!=-1){
                fan(chars,left,right);
                left=-1;
                right=-1;
            }
        }
        System.out.println(new String(chars));
    }
    public static void fan(char[]chars,int left,int right){
//        char[]chars=str.toCharArray();
        Character temp=0;
        while (left<right){
            temp=chars[left];
            chars[left]=chars[right];
            chars[right]=temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        String s="dog loves pig";
        reverse(s);
        System.out.println(s);
    }
}
