/* // 1º caso: superclasse e subclasse sem construtor explícito
class Superclasse {
    // Construtor default
    // Superclasse() { super(); }
}

class Subclasse extends Superclasse {
    // Construtor default
    // Subclasse() { super(); }
}

public class teste {
    public static void main(String[] args) {
        new Subclasse();
    }
} */

/* // 2º caso: superclasse sem construtor explícito e subclasse com construtor
class Superclasse {
    // Construtor default
    // Superclasse() { super(); }
}

class Subclasse extends Superclasse {
    // Construtor
    Subclasse(String texto) {
        // super(); implícito
        System.out.println(texto);
    }
}

public class teste {
    public static void main(String[] args) {
        new Subclasse("Texto");
    }
} */

/* // 3º caso: superclasse com construtor sem argumentos e subclasse com construtor com argumentos
class Superclasse {
    Superclasse() {
        System.out.println("Construtor da superclasse sem argumentos.");
    }
}

class Subclasse extends Superclasse {
    // Construtor
    Subclasse(String texto) {
        // super(); implícito
        System.out.println(texto);
    }
}

public class teste {
    public static void main(String[] args) {
        new Subclasse("Texto");
    }
} */

/* // 4º caso: superclasse e subclasse com construtor com argumentos
class Superclasse {
    Superclasse(int num) {
        System.out.println("Construtor da superclasse com argumentos: " + num);
    }
}

class Subclasse extends Superclasse {
    // Construtor
    Subclasse(String texto) {
        super(0); // subclasse deve chamar explicitamente o construtor da superclasse
        System.out.println(texto);
    }
}

public class teste {
    public static void main(String[] args) {
        new Subclasse("Texto");
    }
} */

/* // 5º caso: superclasse sem construtor explícito e subclasse com construtor com argumentos em cadeia
class Superclasse {
    // Construtor default
    // Superclasse() { super(); }
}

class Subclasse extends Superclasse {
    // Construtor
    Subclasse(String texto) {
        // super(); implícito no fim da cadeia
        System.out.println(texto);
    }

    Subclasse(String texto, int num) {
        this(texto);
    }
}

public class teste {
    public static void main(String[] args) {
        new Subclasse("Texto");
    }
} */
