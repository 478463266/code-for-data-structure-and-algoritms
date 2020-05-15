package hash;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class app_API {
    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("a", 1);
        hm.put("b", 5);
        hm.put("c", 3);
        hm.put("d", 2);
        System.out.println(hm.get("c"));
        hm.remove("b");
        hm.put("c", 7);
        System.out.println(hm.get("c"));
        System.out.println(hm.getOrDefault("e", -1));
        System.out.println(hm.containsKey("e"));
        System.out.println(hm.containsKey("a"));
        System.out.println(hm.containsValue(1));

        //遍历
        Iterator iterator1 = hm.entrySet().iterator();
        while (iterator1.hasNext())
        {
            Map.Entry entry = (Map.Entry) iterator1.next();
            String key = (String) entry.getKey();
            Integer value =  (Integer) entry.getValue();
            System.out.print(key + "=" + value + " ");
        }







    }
}
