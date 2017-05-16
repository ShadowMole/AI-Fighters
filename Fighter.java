import java.util.ArrayList;

public class Fighter{
    
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private Location location;
    private ArrayList<Ability> abilities;
    private ArrayList<Move> moves;
    private ArrayList<Command> commands;
    
    public Fighter(){
        abilities = new ArrayList<>();
        moves = new ArrayList<>();
        commands = new ArrayList<>();
        
        abilities.forEach(a -> {
            a.getMoveSet().forEach(m -> moves.add(m));
        });
        
        moves.forEach(m -> commands.add(m.getCommand()));
    }
}