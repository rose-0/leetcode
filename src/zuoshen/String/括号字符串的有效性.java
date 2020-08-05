package zuoshen.String;

public class 括号字符串的有效性 {
    public static boolean isValid(String str){
        if(str==null||str.length()==0){
            return false;
        }
        char[]chars=str.toCharArray();
        int num=0;
        for (int i = 0; i <chars.length ; i++) {
            if(chars[i]!='('||chars[i]!=')'){
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

    public static void main(String[] args) {
        System.out.println(isValid("()"));
    }
}
