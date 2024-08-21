package Controllers;

import java.util.HashMap;
import java.util.List;

import Model.TrieNode;

public class SuggestionController {
    private TrieNode head=new TrieNode();
    private HashMap<String,Integer> map=new HashMap<String,Integer>();

    public void add(String s){
        head.insert(s,map);
    }
    public void addAll(List<String> list){
        for(String s:list)
                head.insert(s,map);
    }

    public List<String> suggest(String s){
        return head.suggest(s);
    }
}
