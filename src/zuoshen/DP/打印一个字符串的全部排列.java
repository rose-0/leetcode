package zuoshen.DP;
//https://www.cnblogs.com/gjmhome/p/11370930.html
public class 打印一个字符串的全部排列 {
    public void print_pailie(int index,char[]chars){
        if(index==chars.length){
            System.out.println(chars);
        //不写return也可以，这个后面没有函数了，没有可以执行的语句了
        }
        for(int j=index;j<chars.length;j++){
            swap(index,j,chars);
            print_pailie(index+1,chars);
            swap(j,index,chars);
        }
    }
    public void swap(int i,int j,char[]chars){
        char temp=chars[i];
        chars[i]=chars[j];
        chars[j]=temp;
    }
}
