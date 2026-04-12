package ifba.inf011;

class DisplayNameFormatter {
    public String formatDisplayName(String name) {
        if(name == null) return null;
        
        String formattedName = name.strip().replaceAll("\\s+", " ");

        if(formattedName.isEmpty()) return "";

        formattedName = formattedName.substring(0, 1).toUpperCase() + formattedName.substring(1);
        return formattedName;
    }
}


public class Main {
    public static void main(String[] args) {
        DisplayNameFormatter formatter = new DisplayNameFormatter();
        System.out.println(formatter.formatDisplayName("  john doe  "));
        System.out.println(formatter.formatDisplayName("ALICE"));
        System.out.println(formatter.formatDisplayName("  bob  "));
    }
}