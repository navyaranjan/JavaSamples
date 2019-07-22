package lambdas;

import java.util.*;
import java.util.Collections;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {

        List<Player> footballTeam = new ArrayList<>();
        Player player1 = new Player(59, "John", 20);
        Player player2 = new Player(67, "Roger", 22);
        Player player3 = new Player(45, "Steven", 24);
        Player player4 = new Player(50, "Sam", 24);
        footballTeam.add(player1);
        footballTeam.add(player2);
        footballTeam.add(player3);
        footballTeam.add(player4);
        System.out.println("Before Sorting : " + footballTeam);

        // Comparator<Player> byRanking = (Player player4, Player player5) ->
        // player1.getRanking() - player2.getRanking();

        // Using Comparator.comparing()

        Comparator<Player> byRanking = Comparator.comparing(Player::getRanking);

        Collections.sort(footballTeam, byRanking);
        System.out.println("After Sorting : " + footballTeam);

        System.out.println("Print all players with first name beginning with S");
        footballTeam.stream().filter(player -> player.getName().startsWith("S")).collect(Collectors.toList())
                .forEach(System.out::println);
        ;
    }

}

class Player {
    private int ranking;
    private String name;
    private int age;

    public Player(int ranking, String name, int age) {
        this.ranking = ranking;
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRanking() {
        return ranking;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}
