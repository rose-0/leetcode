package leecode.string;
//左神248
public class 字符串转换整数_8 {
    public int myAtoi(String str) {
        return 0;//这个题目没意义
    }
    public int invert(String str){
        char[]chars=str.toCharArray();
        int minq=Integer.MIN_VALUE/10;
        int minr=Integer.MIN_VALUE%10;
        boolean flag=chars[0]=='-'?true:false;
        int cur=0;
        int res=0;
        for (int i = flag?1:0; i <chars.length ; i++) {
            cur='0'-chars[i];
            if(res<minq||res==minq&&cur<minr){
                return 0;
            }
            res=res*10+cur;
        }
        if(flag&&res==Integer.MIN_VALUE){
            return 0;
        }
        return flag?-res:res;
    }
    public boolean isvalid(String str){
        str.trim();
        char[]chars=str.toCharArray();
        if(chars[0]!='-'&&(chars[0]<'0'||chars[0]>'9')){
            return false;
        }
        if(chars[0]=='-'&&(chars.length==1||chars[1]=='0')){
            return false;
        }
        int end=0;
        for (int i = 1; i <chars.length ; i++) {
            if(chars[i]<'0'||chars[i]>'9'){
                end=i;
                break;
            }
        }
        str.substring(0,end);
        return true;
    }
}
