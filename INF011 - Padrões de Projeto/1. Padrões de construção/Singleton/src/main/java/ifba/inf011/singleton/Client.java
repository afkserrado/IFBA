package ifba.inf011.singleton;

public class Client {

    public static void main(String[] args) {

        // ── LazySingleton ──────────────────────────────────────────────────
        LazySingleton lazy1 = LazySingleton.getInstance();
        LazySingleton lazy2 = LazySingleton.getInstance();
        System.out.println("LazySingleton        | mesma instância: " + (lazy1 == lazy2));

        // ── ThreadSafeSingleton ────────────────────────────────────────────
        ThreadSafeSingleton ts1 = ThreadSafeSingleton.getInstance();
        ThreadSafeSingleton ts2 = ThreadSafeSingleton.getInstance();
        System.out.println("ThreadSafeSingleton  | mesma instância: " + (ts1 == ts2));

        // ── DoubleCheckedSingleton ─────────────────────────────────────────
        DoubleCheckedSingleton dc1 = DoubleCheckedSingleton.getInstance();
        DoubleCheckedSingleton dc2 = DoubleCheckedSingleton.getInstance();
        System.out.println("DoubleChecked        | mesma instância: " + (dc1 == dc2));

        // ── EagerSingleton ─────────────────────────────────────────────────
        EagerSingleton eager1 = EagerSingleton.getInstance();
        EagerSingleton eager2 = EagerSingleton.getInstance();
        System.out.println("EagerSingleton       | mesma instância: " + (eager1 == eager2));

        // ── StaticBlockSingleton ───────────────────────────────────────────
        StaticBlockSingleton sb1 = StaticBlockSingleton.getInstance();
        StaticBlockSingleton sb2 = StaticBlockSingleton.getInstance();
        System.out.println("StaticBlock          | mesma instância: " + (sb1 == sb2));

        // ── BillPughSingleton ──────────────────────────────────────────────
        BillPughSingleton bp1 = BillPughSingleton.getInstance();
        BillPughSingleton bp2 = BillPughSingleton.getInstance();
        System.out.println("BillPugh             | mesma instância: " + (bp1 == bp2));

        // ── EnumSingleton ──────────────────────────────────────────────────
        EnumSingleton enum1 = EnumSingleton.INSTANCE;
        EnumSingleton enum2 = EnumSingleton.INSTANCE;
        System.out.println("EnumSingleton        | mesma instância: " + (enum1 == enum2));
    }
}