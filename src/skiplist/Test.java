package skiplist;
public class Test{
    public static void main(String[] args) {
        SkipList<String> skipList = new SkipList<>();
        skipList.add("50");
        skipList.add("30");
        skipList.add("70");
        skipList.add("90");
        skipList.add("20");
        skipList.add("40");
        skipList.add("60");
        skipList.add("80");
        skipList.add("10");
        skipList.add("25");

        System.out.println("Skip List después de insertar elementos:");
        System.out.println(skipList);
        System.out.println("Tamaño de la Skip List: " + skipList.size());

        System.out.println("Buscar 40: " + skipList.search("40"));
        System.out.println("Buscar 15: " + skipList.search("15"));
        System.out.println("Buscar 90: " + skipList.search("90"));

        skipList.remove("40");
        System.out.println("Buscar 40 después de eliminarlo: " + skipList.search("40"));
        System.out.println("Tamaño de la Skip List después de eliminar: " + skipList.size());
        System.out.println("Skip List final:");
        System.out.println(skipList);
    }
}

