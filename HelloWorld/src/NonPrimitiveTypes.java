public class NonPrimitiveTypes {
    public static void main(String[] args) {
        String phrase = "String is always initialized with double quotes";
        System.out.println(phrase);

        String my = "my";
        String nameIs = " name is ";
        String name = "Debora";
        String concatOne = my + nameIs + name;
        System.out.println(concatOne);

        StringBuilder stringBuilder = new StringBuilder();
        String concatTwo = stringBuilder.append(my)
            .append(nameIs)
            .append(name)
            .toString();
        System.out.println(concatTwo);
    }
}
