public class Move{

    private Command command;
    private String name;
    private double attack;
    private double defense;
    private int time;

    public Move(String name){
        this.name = name;
        if(name.equals("Uppercut")){
            command = Command.MONDAY;
            attack = 100;
            defense = 0;
            time = 1200;
        }
        if(name.equals("Cross")){
            command = Command.TUESDAY;
            attack = 50;
            defense = 0;
            time = 400;
        }
        if(name.equals("Shielded Cross")){
            command = Command.WEDNESDAY;
            attack = 50;
            defense = 25;
            time = 800;
        }
        if(name.equals("Quick Block")){
            command = Command.THURSDAY;
            attack = 0;
            defense = 75;
            time = 200;
        }
        if(name.equals("Full Block")){
            command = Command.FRIDAY;
            attack = 0;
            defense = 100;
            time = 1000;
        }
        if(name.equals("Jab")){
            command = Command.SATURDAY;
            attack = 10;
            defense = 0;
            time = 100;
        }
        if(name.equals("Shielded Jab")){
            command = Command.SUNDAY;
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

    public boolean equals(Move m){
        if(name.equals(m.getName())){
            return true;
        }
        return false;
    }
}