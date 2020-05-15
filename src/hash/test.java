package hash;

public class test {
    public static void main(String[] args) throws Exception {
//        myHashMap<String, Integer> test = new myHashMap<>();
//        test.put("a", 1);
//        test.put("b", 2);
//        test.put("c", 3);
//        System.out.println(test.get("b"));
//        test.put("b", 4);
//        System.out.println(test.get("b"));

        myHashMap2<String, Integer> test = new myHashMap2<>();
        test.put("a", 1);
        test.put("b", 2);
        test.put("c", 3);
        System.out.println(test.get("b"));
        test.put("b", 4);
        System.out.println(test.get("b"));
    }
}
