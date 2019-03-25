public class Move{

    private Command command;
    private String name;
    private double attack;
    private double defense;
    private int time;

    public Move(String name){
        this.name = name;
        if(name.equals("Uppercut")){
            command = Command.UPPERCUT;
            attack = 100;
            defense = 0;
            time = 1200;
        }
        if(name.equals("Cross")){
            command = Command.CROSS;
            attack = 50;
            defense = 0;
            time = 400;
        }
        if(name.equals("Shielded Cross")){
            command = Command.SHIELDEDCROSS;
            attack = 50;
            defense = 25;
            time = 800;
        }
        if(name.equals("Quick Block")){
            command = Command.QUICKBLOCK;
            attack = 0;
            defense = 75;
            time = 200;
        }
        if(name.equals("Full Block")){
            command = Command.FULLBLOCK;
            attack = 0;
            defense = 100;
            time = 1000;
        }
        if(name.equals("Jab")){
            command = Command.JAB;
            attack = 10;
            defense = 0;
            time = 100;
        }
        if(name.equals("Shielded Jab")){
            command = Command.SHIELDEDJAB;
            attack = 20;
            defense = 50;
            time = 600;
        }
    }

    public Command getCommand(){
        return command;
    }

    public int getTime(){
        return time;
    }

    public double getAttack(){
        return attack;
    }

    public String getName(){
        return name;
    }

    public double getDefense(){
        return defense;
    }

    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        
        if(obj == null || obj.getClass()!= this.getClass()) {
            return false; 
        }
        
        Move m = (Move) obj;
        
        
        return (name.equals(m.getName()));
    }
    
    public int hashCode(){
        return name.length() + time + (int) attack + (int) defense;
    }
}