public class Move{

    private Command command;
    private String name;
    private double attack;

    public Move(String name){
        this.name = name;
        if(name.equals("Monday")){
            command = Command.MONDAY;
            attack = 100;
        }
        if(name.equals("Tuesday")){
            command = Command.TUESDAY;
            attack = 50;
        }
        if(name.equals("Wednesday")){
            command = Command.WEDNESDAY;
            attack = 60;
        }
        if(name.equals("Thursday")){
            command = Command.THURSDAY;
            attack = 20;
        }
        if(name.equals("Friday")){
            command = Command.FRIDAY;
            attack = 15;
        }
        if(name.equals("Saturday")){
            command = Command.SATURDAY;
            attack = 10;
        }
        if(name.equals("Sunday")){
            command = Command.SUNDAY;
            attack = 20;
        }
    }

    public Command getCommand(){
        return command;
    }
    
    public double getAttack(){
        return attack;
    }
}
