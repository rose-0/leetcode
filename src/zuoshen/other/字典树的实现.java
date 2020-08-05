package zuoshen.other;
//https://blog.csdn.net/pcwl1206/article/details/88902629
/*
开始都从根节点开始遍历
对word的每个字母进行遍历
index是节点下一个节点的index，即trieNode[26]的index，找到word每个位置对应的trieNode[index]
如果trieNode[index]为空，返回
否则遍历这个节点
最后word所有字母遍历完成之后，看是返回node的passNum还是endNum

 */
public class 字典树的实现 {
    public static class trieNode{
        private int passNum;
        private int endNum;
        private trieNode[]paths;
        public trieNode(){
            passNum=0;
            endNum=0;
            paths=new trieNode[26];
        }
    }
    private trieNode root;
    public 字典树的实现(){
        root=new trieNode();
    }
    public void insert(String word){
        if(word==null){
            return;
        }
        char[]chars=word.toCharArray();
        trieNode node = root;
        int index=0;
        for (int i = 0; i <chars.length ; i++) {
            index=chars[i]-'a';
            if(node.paths[index]==null){
                node.paths[index]=new trieNode();
            }
            node=node.paths[index];
            node.passNum++;
        }
        node.endNum++;
    }
    public void delete(String word){
        if(search(word)==0){
            return;
        }
        int index=0;
        char[]chars=word.toCharArray();
        trieNode node=root;
        for (int i = 0; i <chars.length ; i++) {
            index=chars[i]-'a';
            if(--node.paths[index].passNum==0){
                node.paths[index]=null;
                return;
            }
            node=node.paths[index];
        }
        node.endNum--;
    }
    public int search(String word){
        if(word==null){
            return 0;
        }
        char[]chars=word.toCharArray();
        int index=0;
        trieNode node=root;
        for (int i = 0; i <chars.length ; i++) {
            index=chars[i]-'a';
            if(node.paths[index]==null){
                return 0;
            }
            node=node.paths[index];
        }
        return node.endNum;
    }
    public int prefixNumber(String pre){
        if(pre==null){
            return 0;
        }
        char[]chars=pre.toCharArray();
        trieNode node=root;
        int index=0;
        for (int i = 0; i <chars.length ; i++) {
            index=chars[i]-'a';
            if(node.paths[index]==null){
                return 0;
            }
            node=node.paths[i];
        }
        return node.passNum;
    }
}
