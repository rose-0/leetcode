package zuoshen.DP;

import java.util.HashSet;

public class 打印一个字符串的非重复排列 {

    public void print_all(char[]chars,int index){
        if(index==chars.length){
            String res=String.valueOf(chars);


                System.out.println(res);

            return;
        }
        HashSet<Character>hashSet=new HashSet<>();
        for (int j=index;j<chars.length;j++){
            if(!hashSet.contains(chars[j])) {
                hashSet.add(chars[j]);
                swap(index, j, chars);
                print_all(chars, index + 1);
                swap(index, j, chars);
            }
        }
    }
    public void swap(int i,int j,char[]chars){
        char temp=chars[i];
        chars[i]=chars[j];
        chars[j]=temp;
    }
}
