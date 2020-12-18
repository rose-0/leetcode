package leecode.dfs;

import java.util.ArrayList;
import java.util.List;

public class 单词搜索II_212 {
    //可以枚举单词 借用单词搜索I的方法来查找
    //也可以使用字典树，一次建树 多次查找
    //https://leetcode-cn.com/problems/word-search-ii/solution/c-jian-dan-qing-xi-de-trieshu-ti-jie-by-talanto_li/
    public List<String> findWords(char[][]board,String[]words){
        List<String>ans=new ArrayList<>();
        Trie trie=new Trie();
        /*
        单词构成字典树，board在搜索的过程中，检查路径上的串是否在字典树中
         */
        for(String word:words){
            trie.insert(word);
        }
        for (int i = 0; i <board.length ; i++) {
            for (int j = 0; j <board[0].length ; j++) {
                dfs(board,ans,trie,i,j,"");
            }
        }
        return ans;
    }
    public void dfs(char[][]board,List<String>ans,Trie trie,int x,int y,String str){
        if(!isBound(board,x,y)||trie==null){
            return;
        }
        char ch=board[x][y];
        if(ch=='.'){
            return;
        }
        str=str+ch;
        trie=trie.arr[ch-'a'];
        if(trie!=null&&trie.end){
            ans.add(str);
            //防止单词重复添加
            trie.end=false;
        }
        board[x][y]='.';
        dfs(board,ans,trie,x+1,y,str);
        dfs(board,ans,trie,x-1,y,str);
        dfs(board,ans,trie,x,y+1,str);
        dfs(board,ans,trie,x,y-1,str);
        board[x][y]=ch;
    }
    public boolean isBound(char[][]grid,int x,int y){
        return x>=0&&x<grid.length&&y>=0&&y<grid[0].length;
    }

    class Trie{
        private Trie[] arr;
        private boolean end;
        Trie(){
            arr=new Trie[26];
        }
        public void insert(String word){
            Trie trie =this;
            for (int i = 0; i <word.length() ; i++) {
                int index=word.charAt(i)-'a';
                if(trie.arr[index]==null){
                    trie.arr[index]=new Trie();
                }
                trie=trie.arr[index];
            }
            trie.end=true;
        }
        public boolean getEnd(){
            return end;
        }
        public Trie[] getArr(){
            return arr;
        }
        public void setEnd(){
            this.end=end;
        }
    }
}
