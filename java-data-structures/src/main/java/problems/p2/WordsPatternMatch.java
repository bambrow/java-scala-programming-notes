package problems.p2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordsPatternMatch {
    public static void main(String[] args) {
        System.out.println(match("abba", "dog cat cat dog"));
        System.out.println(match("abba", "dog cat cat fish"));
        System.out.println(match("aaaa", "dog cat cat dog"));
        System.out.println(match("abba", "dog dog dog dog"));
    }

    public static boolean match(String pattern, String words) {
        if (pattern == null || words == null) {
            return false;
        }
        String[] strArr = words.split("\\s+");
        if (pattern.length() != strArr.length) {
            return false;
        }
        Map<Character, String> patternMap = new HashMap<>();
        Set<String> wordSet = new HashSet<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (patternMap.containsKey(c)) {
                if (!patternMap.get(c).equals(strArr[i])) {
                    return false;
                }
            } else {
                if (wordSet.contains(strArr[i])) {
                    return false;
                } else {
                    patternMap.put(c, strArr[i]);
                    wordSet.add(strArr[i]);
                }
            }
        }
        return true;
    }
}
