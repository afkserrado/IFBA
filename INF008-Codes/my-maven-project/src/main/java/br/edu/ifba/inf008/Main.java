package br.edu.ifba.inf008;

public class Main
{
    public static void main(String[] args) {
        Player joao = new Player("João", 10, 4.5f);
        Player maria = new Player("Maria", 20, 5.5f);
        Player jose = new Player("JOSE");
        Team team = new Team();
        Boss boss = new Boss();
        Weapon weapon = new Weapon();
        maria.runFinalMatch(team, boss, 10, weapon);
//        Player jose = new Player();
//        maria.name = "Maria";
        joao.jump();
        maria.fightWith(joao);
    }
}
