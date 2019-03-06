import java.util.ArrayList;
public class Ability{

    private ArrayList<Move> moveSet;
    private String type;

    public Ability(String type){
        this.type = type;
        moveSet = new ArrayList<>();
        if(type.equals("Moves")){
            moveSet.add(new Move("Uppercut"));
            moveSet.add(new Move("Cross"));
            moveSet.add(new Move("Shielded Cross"));
            moveSet.add(new Move("Quick Block"));
            moveSet.add(new Move("Full Block"));
            moveSet.add(new Move("Jab"));
            moveSet.add(new Move("Shielded Jab"));
        }
    }

    public ArrayList<Move> getMoveSet(){
        return moveSet;
    }
}
