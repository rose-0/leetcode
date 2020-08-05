package leecode.string;

public class 翻转字符串里的单词_151 {
    public static String reverseWords(String s) {
        if(s.length()==0){
            return "";
        }
        if(s==null){
            return null;
        }
        if(s==" "){
            return " ";
        }
//        reverse(s.toCharArray(), 0, s.length() - 1);
        char[] chars = s.toCharArray();
        reverse(chars,0,chars.length-1);
        int l = -1;
        int r = -1;
        String str1=new String(chars);
        StringBuilder str2=new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != ' ') {
                l = i == 0 || chars[i - 1] == ' ' ? i : l;
                r = i == chars.length - 1 || chars[i + 1] == ' ' ? i : r;
            }
            if (l != -1 && r != -1) {
                reverse(chars, l, r);
                while (l<=r){
                    str2.append(chars[l++]);
                }

                    str2.append(' ');


                l = -1;
                r = -1;
            }
        }
//        int start=0;
//        int end=chars.length-1;
//        while (start<end){
//            if(chars[start]==' '){
//                start++;
//            }
//            if(chars[end]==' '){
//                end--;
//            }
//            if(chars[start]!=' '&&chars[end]!=' '){
//                break;
//            }
//        }
//        String str=new String(chars);
//        return str.substring(start,end+1);
        if(str2.length()>0) {
            str2.deleteCharAt(str2.length() - 1);//最后一个单词多加了空格
        }
        return new String(str2);
    }
    public static void reverse(char[]chars,int begin,int end){
        while (begin<end){
            Character temp=chars[begin];
            chars[begin]=chars[end];
            chars[end]=temp;
            begin++;
            end--;
        }
    }

    public static void main(String[] args) {
        String s=" ";
        System.out.println(reverseWords(s));
    }
}
