import java.util.ArrayList;
public class Ability{
    
    private ArrayList<Move> moveSet;
    
    public Ability(){
       moveSet = new ArrayList<>();
    }

    public ArrayList<Move> getMoveSet(){
        return moveSet;
    }
}
