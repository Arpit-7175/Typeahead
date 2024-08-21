package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class TrieNode {

    private HashMap<Character,TrieNode> next=new HashMap<>();
    private ArrayList<String> suggestions=new ArrayList<>();

    public void insert(String str,HashMap<String,Integer> map){
        str.toLowerCase();

        //updating frequency in  frequncy map
        if(map.containsKey(str))
            map.put(str,map.get(str)+1);
        else map.put(str,0);

        int frequency=map.get(str);
        TrieNode head=this;
        for(int i=0;i<str.length();++i){
            char ch=str.charAt(i);
            if(!head.next.containsKey(ch)){
                TrieNode temp=new TrieNode();
                head.next.put(ch,temp);
            }
            head=head.next.get(ch);
            if(!head.suggestions.contains(str)) {
                if (head.suggestions.size() < 5)
                    head.suggestions.add(str);
                else {
                    int freq = map.get(head.suggestions.getLast());
                    if (freq < frequency)
                        head.suggestions.removeLast();
                    head.suggestions.add(str);
                    head.suggestions.sort((String s1, String s2) -> map.get(s2) - map.get(s1));
                }
            }
        }
        System.out.println("Added: "+str);
    }

    public ArrayList<String> suggest(String str){
        //throws null if no suggestion found
        str.toLowerCase();
        try
        {
            TrieNode head=this;
            for(int i=0;i<str.length();++i){
                char ch=str.charAt(i);
                head=head.next.get(ch);
            }
            return head.suggestions;
        }
        catch (NullPointerException e){
            System.out.println("No sugggestion found..");
            return null;
        }
    }
}
