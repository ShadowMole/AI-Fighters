import java.util.ArrayList;

public class Fighter{
    
    private int health;  //When health reaches zero, the Fighter loses
    private int attack;  //Damage done by Fighter
    private int defense;  //Damage negated by Fighter
    private int speed;  //How fast the Fighter takes actions and moves
    private Location location;  //Location of Fighter
    private ArrayList<Ability> abilities;  //Powers and Equipment of Fighter
    private ArrayList<Move> moves;  //Moves available to Fighter
    private ArrayList<Command> commands;  //Commands to use moves
    private Network brain;
    
    public Fighter(){
        abilities = new ArrayList<>();
        moves = new ArrayList<>();
        commands = new ArrayList<>();
        
        abilities.forEach(a -> {
            a.getMoveSet().forEach(m -> moves.add(m));
        });
        
        moves.forEach(m -> commands.add(m.getCommand()));
        brain = new Network(11, commands.size());
    }
}