package leecode.bfs;

import java.util.*;

public class 单词接龙II_126 {
    /*https://leetcode-cn.com/problems/word-ladder-ii/solution/yan-du-you-xian-bian-li-shuang-xiang-yan-du-you--2/
    使用visited的原因:
    1、同一层的关系不能记录下来，在上一层出队时，将下一层所有节点加入到visit，如果同一层之间有边，因为这个visit不会加到map里

    使用levelVisit的原因:
    2、当同一层两个节点都指向下一层的某一个节点时，只使用visit话，只能记录其中的一条边（因为把下一个节点加到visit里面），
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        Set<String>wordSet=new HashSet<>(wordList);
        List<List<String>>res=new ArrayList<>();
        if(wordSet.size()==0||!wordSet.contains(endWord)){
            return res;
        }

        Queue<String>queue=new LinkedList<>();
        Set<String>visit=new HashSet<>();


        queue.add(beginWord);
        visit.add(beginWord);

        //bfs建图 可以封装为一个函数
        int wordlen=beginWord.length();
        Map<String,Set<String>>successors=new HashMap<>();
        Set<String>levelVisit=new HashSet<>();
        boolean found=false;
        while (!queue.isEmpty()){
            int size=queue.size();
            for (int i = 0; i <size ; i++) {
                String curWord=queue.poll();
                char[] curChars=curWord.toCharArray();
                for (int j = 0; j <wordlen ; j++) {
                    char originChar=curChars[j];
                    for(char k='a';k<='z';k++){
                        if(originChar==k){
                            continue;
                        }
                        curChars[j]=k;
                        String nextWord=String.valueOf(curChars);
                        if(wordSet.contains(nextWord)){
                            if(!visit.contains(nextWord)){//用visit来check
                                if(nextWord.equals(endWord)){
                                    found=true;
                                }
                                if(!levelVisit.contains(nextWord)){
                                    queue.add(nextWord);
                                    levelVisit.add(nextWord);//加到levelVisit里面
                                }

                                successors.computeIfAbsent(curWord,a->new HashSet<>());
                                successors.get(curWord).add(nextWord);
                                /*等价于
                                    if (successors.containsKey(currentWord)) {
                                        successors.get(currentWord).add(nextWord);
                                    } else {
                                        Set<String> newSet = new HashSet<>();
                                        newSet.add(nextWord);
                                        successors.put(currentWord, newSet);
                                    }
                                 */
                            }
                        }
                    }
                    curChars[j]=originChar;
                }
            }
            if(found){
                break;
            }
            visit.addAll(levelVisit);
            levelVisit.clear();
        }
        List<String>list=new ArrayList<>();
        list.add(beginWord);
        dfs(res,list,beginWord,endWord,successors);
        return res;

    }
    private void dfs(List<List<String>>res,List<String>list,String beginWord, String endWord,Map<String,Set<String>>successors){
        if(beginWord.equals(endWord)){
            res.add(new ArrayList<>(list));
            return;
        }
        if(!successors.containsKey(beginWord)){
            return;
        }
        for(String nextWord:successors.get(beginWord)){
            list.add(nextWord);
            dfs(res,list,nextWord,endWord,successors);
            list.remove(list.size()-1);
        }
    }
}
