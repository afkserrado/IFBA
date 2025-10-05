package br.edu.ifba.inf008;

class Player
{
    private String name;
    private int energy;
    private float strength;

    public Player() {
        System.out.println("Ola mundo legal!");
    }
    public Player(String name) {
        this(); // Chama o construtor sem parametros
        this.name = name;
    }
    public Player(String name, int energy, float strength) {
        this(name);
        this.energy = energy;
        this.strength = strength;
    }
    public void runFinalMatch(Boss boss) {
        // Code to fight with boss
        System.out.println("runFinalMatch(Boss boss)");
    }
    public void runFinalMatch(Boss boss, int initialEnergy) {
        // Code to set up initial energy
        System.out.println("runFinalMatch(Boss boss, int initialEnergy)");
        runFinalMatch(boss);
    }
    public void runFinalMatch(Player player, int initialEnergy, Weapon weapon) {}
    public void runFinalMatch(Team team, Boss boss, int initialEnergy, Weapon weapon) {
        System.out.println("runFinalMatch(Team team, Boss boss, int initialEnergy, Weapon weapon)");
    }
    public void m1(float y, float z) {}
    public void jump() { // Assinatura: jump
        System.out.println(name + " is jumping!");
    }

    public void fightWith(Player enemy) { // Assinatura: fightWith - Player
        System.out.println(name + " is fighting with " + enemy.name);
    }
}
