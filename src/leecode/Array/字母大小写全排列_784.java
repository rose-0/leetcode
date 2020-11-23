package leecode.Array;

import java.util.ArrayList;
import java.util.List;

public class 字母大小写全排列_784 {
    //图 https://leetcode-cn.com/problems/letter-case-permutation/solution/hui-su-suan-fa-by-martinlwx/
    public List<String> letterCasePermutation(String S) {
        List<String>res = new ArrayList<>();
        dfs(res,new StringBuilder(),0,S);
        return res;
    }

    /*
    这个是记录了一个变量
     */
    public void dfs(List<String>res,StringBuilder str,int depth,String S){
        if(depth==S.length()){
            res.add(new String(str));
            return;
        }
        Character c=S.charAt(depth);
        if(c>='0'&&c<='9'){//是数字
            str.append(c);
            dfs(res,str,depth+1,S);
            str.deleteCharAt(str.length()-1);
        }else {
            //字母 则 dfs两次 相当于产生两个分支
            str.append(Character.toLowerCase(c));
            dfs(res,str,depth+1,S);
            str.deleteCharAt(str.length()-1);

            str.append(Character.toUpperCase(c));
            dfs(res,str,depth+1,S);
            str.deleteCharAt(str.length()-1);

        }
    }
    /*
    或者直接在原来的S上操作 https://leetcode-cn.com/problems/letter-case-permutation/solution/shen-du-you-xian-bian-li-hui-su-suan-fa-python-dai/
     */
}
