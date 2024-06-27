package skiplist;
public class Test{
    public static void main(String[] args) {
        SkipList<String> skipList = new SkipList<>();
        skipList.add("60");
        skipList.add("30");
        skipList.add("70");
        skipList.add("90");
        skipList.add("12");
        skipList.add("19");
        skipList.add("17");
        skipList.add("26");
        skipList.add("21");
        skipList.add("25");

        System.out.println(skipList);
        System.out.println(skipList.size());

        System.out.println("Buscar 19: " + skipList.search("19")); 
        System.out.println("Buscar 15: " + skipList.search("15"));
        System.out.println("Buscar 60: " + skipList.search("60"));
        
        skipList.remove("19");
        System.out.println("Buscar 19 después de eliminar: " + skipList.search("19")); 
        System.out.println(skipList.size());
        System.out.println(skipList);
    }
}

