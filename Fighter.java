import java.util.ArrayList;

public class Fighter{

    private double totalHealth;  //When health reaches zero, the Fighter loses
    private double currentHealth;
    private double attack;  //Damage done by Fighter
    private double defense;  //Damage negated by Fighter
    private int speed;  //How fast the Fighter takes actions and moves
    private Location location;  //Location of Fighter
    private ArrayList<Ability> abilities;  //Powers and Equipment of Fighter
    private ArrayList<Move> moves;  //Moves available to Fighter
    private ArrayList<Command> commands;  //Commands to use moves
    private Network brain;
    private Fighter enemy;
    private String name;
    private Move currentMove;
    private double direction;
    private boolean inMove;
    private int moveTime;

    public Fighter(String name, int x){
        this.name = name;
        abilities = new ArrayList<>();
        moves = new ArrayList<>();
        commands = new ArrayList<>();

        totalHealth = 500;
        currentHealth = totalHealth;
        attack = 40;
        defense = 25;
        speed = 100;
        inMove = false;
        moveTime = 0;

        location = new Location(x,0);
        if(x == 100){
            direction = 1;
        }else{
            direction = 2;
        }

        abilities.add(new Ability("Week"));

        abilities.forEach(a -> {
                a.getMoveSet().forEach(m -> moves.add(m));
            });

        moves.forEach(m -> commands.add(m.getCommand()));
        brain = new Network(11, commands.size());
    }

    public void act(){
        if(moveTime > 0){
            moveTime--;
            if(moveTime == 0){
                endMove();
            }
        }
        if(Battle.getTime() % speed == 0 && !inMove){
            double[] info = {Math.sqrt(((location.getCol() - enemy.getLocation().getCol())*(location.getCol() - enemy.getLocation().getCol()))/((location.getRow() - enemy.getLocation().getRow()) * (location.getRow() - enemy.getLocation().getRow()))), currentHealth, enemy.getHealth(), attack, enemy.getAttack(),defense, enemy.getDefense(), getCurrentMove(), enemy.getCurrentMove(), direction, enemy.getDirection()};
            int decision = brain.makeDecision(info);
            newCommand(decision);
        }
    }

    public Location getLocation(){
        return location;
    }

    public double getHealth(){
        return currentHealth;
    }

    public double getAttack(){
        return attack;
    }

    public double getDefense(){
        return defense;
    }

    public double getCurrentMove(){
        if(currentMove == null){
            return 0;
        }else{
            return currentMove.getAttack();
        }
    }

    public double getDirection(){
        return direction;
    }

    public void setEnemy(Fighter f){
        enemy = f;
    }

    public void newCommand(int x){
        Command c = commands.get(x);
        moves.forEach(m -> {
                if(m.getCommand() == c){
                    currentMove = m;
                    moveTime = currentMove.getTime();
                    inMove = true;
                    return;
                }
            });
    }

    public void endMove(){
        enemy.changeHealth(currentMove.getAttack(), attack, currentMove.getName());
        inMove = false;
    }

    public void changeHealth(double moveDamage, double enemyAttack, String move){
        double damage = moveDamage + enemyAttack - defense;
        currentHealth -= damage;
        System.out.println(enemy.getName() + " has damaged " + name + " by " + damage + " points with " + move + " Attack. \n");
    }
    
    public String getName(){
        return name;
    }
}