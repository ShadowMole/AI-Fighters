public class Move{

    private Command command;
    private String name;
    private double attack;
    private int time;

    public Move(String name){
        this.name = name;
        if(name.equals("Monday")){
            command = Command.MONDAY;
            attack = 150;
            time = 2000;
        }
        if(name.equals("Tuesday")){
            command = Command.TUESDAY;
            attack = 50;
            time = 700;
        }
        if(name.equals("Wednesday")){
            command = Command.WEDNESDAY;
            attack = 60;
            time = 800;
        }
        if(name.equals("Thursday")){
            command = Command.THURSDAY;
            attack = 20;
            time = 400;
        }
        if(name.equals("Friday")){
            command = Command.FRIDAY;
            attack = 15;
            time = 200;
        }
        if(name.equals("Saturday")){
            command = Command.SATURDAY;
            attack = 10;
            time = 100;
        }
        if(name.equals("Sunday")){
            command = Command.SUNDAY;
            attack = 20;
            time = 500;
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
}
