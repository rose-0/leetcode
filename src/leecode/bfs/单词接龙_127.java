package leecode.bfs;

import java.util.*;

public class 单词接龙_127 {
    /*
    如果一开始就建立图的话，那么每一个单词都需要和除它以外的另外的单词的字母进行一一比较，复杂度(n*len)
    转换：对每个单词的每个字母进行26次转换，复杂度(26*len)
    https://leetcode-cn.com/problems/word-ladder/solution/yan-du-you-xian-bian-li-shuang-xiang-yan-du-you-2/
    优化：双向遍历
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String>wordSet=new HashSet<>(wordList);
        if(wordSet.size()==0||!wordSet.contains(endWord)){
            return 0;
        }
        wordSet.remove(beginWord);

        Queue<String>queue=new LinkedList<>();
        queue.offer(beginWord);
        Set<String>visit=new HashSet<>();
        visit.add(beginWord);

        int step=1;
        int wordlen=beginWord.length();
        while (!queue.isEmpty()){
            int size=queue.size();
            for (int i = 0; i <size ; i++) {
                String curWord=queue.poll();

                char[]curChars=curWord.toCharArray();
                for (int j = 0; j <wordlen ; j++) {//题目条件：所有单词具有相同的长度。

                    char originChar=curChars[j];//要暂存原来的字母
                    //在bfs的过程中 替换字母 寻找邻边（即建立图）
                    for (char k = 'a'; k <='z' ; k++) {//题目条件：所有单词只由小写字母组成。
                        if(k==originChar){
                            continue;
                        }
                        curChars[j]=k;
                        String nextWord=String.valueOf(curChars);

                        if(wordSet.contains(nextWord)){//找到邻边
                            if(nextWord.equals(endWord)){
                                return step+1;
                            }
                            if(!visit.contains(nextWord)){
                                queue.add(nextWord);
                                /*一定在入队的时候就标记已经访问过，如果在出队的时候再标记 那么图中有环的话 就会造成死循环
                                如下 a出队后 将 b c入队，b出队后标记访问，又将c入队
                                         / b \
                                        a------c
                                 */
                                visit.add(nextWord);
                            }
                        }
                    }
                    curChars[j]=originChar;
                }
            }
            step++;//每次队列元素全部处理完毕 再step++
        }
        return 0;
    }
}
