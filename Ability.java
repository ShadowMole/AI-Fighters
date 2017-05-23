import java.util.ArrayList;
public class Ability{

    private ArrayList<Move> moveSet;
    private String type;

    public Ability(String type){
        this.type = type;
        moveSet = new ArrayList<>();
        if(type.equals("Week")){
            moveSet.add(new Move("Monday"));
            moveSet.add(new Move("Tuesday"));
            moveSet.add(new Move("Wednesday"));
            moveSet.add(new Move("Thursday"));
            moveSet.add(new Move("Friday"));
            moveSet.add(new Move("Saturday"));
            moveSet.add(new Move("Sunday"));
        }
    }

    public ArrayList<Move> getMoveSet(){
        return moveSet;
    }
}
