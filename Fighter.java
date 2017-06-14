import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService; 
import java.util.HashMap;

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
    private QLearn brain2;
    private Fighter enemy;
    private String name;
    private Move currentMove;
    private double direction;
    private boolean inMove;
    private int moveTime;
    private double regen;
    private double lastHealth;
    private double lastOpponent;
    private int sim;
    private int decide;
    private int wins;
    private double[] picks;
    private State lastState;
    private ExecutorService executor;
    private HashMap<Move, Double> values;

    public Fighter(String name, int x, double regen, double strength){
        executor = Executors.newFixedThreadPool(20);
        this.name = name;
        abilities = new ArrayList<>();
        moves = new ArrayList<>();
        commands = new ArrayList<>();
        totalHealth = 500;
        currentHealth = totalHealth;
        attack = 50 + strength;
        defense = 25;
        speed = 100;
        inMove = false;
        moveTime = 0;
        wins = 0;
        this.regen = regen;
        picks = new double[7];
        for(int i = 0; i < picks.length; i++){
            picks[i] = 0;
        }

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
        brain2 = new QLearn();
    }

    public void act(){
        if(moveTime > 0){
            moveTime--;
            if(moveTime == 0){
                endMove();
            }
        }
        if(Battle.getTime() % 500 == 0 && currentHealth < totalHealth){
            currentHealth += regen;
        }
        if(Battle.getTime() % speed == 0 && !inMove){
            double[] info = {Math.sqrt(((location.getCol() - enemy.getLocation().getCol())*(location.getCol() - enemy.getLocation().getCol()))/((location.getRow() - enemy.getLocation().getRow()) * (location.getRow() - enemy.getLocation().getRow()))), currentHealth, enemy.getHealth(), attack, enemy.getAttack(),defense, enemy.getDefense(), enemy.getCurrentMoveAttack(), enemy.getCurrentMoveDefense(), direction, enemy.getDirection(), Battle.getTime()};
            lastState = new State(info);
            Runnable thread = new VThread(this, brain2, lastState);
            executor.execute(thread);
            if(sim < 1000000 && name.equals("Fighter 1") && values != null){
                int decision = brain.makeDecision(info, values);
                newCommand(decision);
                picks[decision]++;
                decide = 0;
            }else{
                newCommand(Randomizer.getRgen(commands.size()));
                decide = 1;
            } 
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

    public double getCurrentMoveAttack(){
        if(currentMove == null){
            return 0;
        }else{
            return currentMove.getAttack();
        }
    }

    public double getCurrentMoveDefense(){
        if(currentMove == null){
            return 0;
        }else{
            return currentMove.getDefense();
        }
    }

    public double getDirection(){
        return direction;
    }

    public void setEnemy(Fighter f){
        enemy = f;
        lastHealth = currentHealth;
        lastOpponent = enemy.getHealth();
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
        if(name.equals("Fighter 1")){
            Runnable thread = new QThread(brain2, lastState, currentMove, currentHealth, enemy.getHealth());
            executor.execute(thread);
            // brain2.newQValue(lastState, currentMove, currentHealth, enemy.getHealth());
        }
        if(decide == 0){
            brain.learn(brain2.getError());
        }
        lastHealth = currentHealth;
        lastOpponent = enemy.getHealth();
    }

    public void changeHealth(double moveDamage, double enemyAttack, String move){
        double damage = moveDamage + enemyAttack - defense - getCurrentMoveDefense();
        if(damage >= 0){
            currentHealth -= damage;
            if(sim <= 25){
                if(enemy.getDecide() == 1){
                    System.out.println("Randomly " + enemy.getName() + " has damaged " + name + " by " + damage + " points with " + move + " Attack at " + Battle.getTime() / 1000.0 + " seconds. \n");
                }else{
                    System.out.println(enemy.getName() + " has damaged " + name + " by " + damage + " points with " + move + " Attack at " + Battle.getTime() / 1000.0 + " seconds. \n");
                }
            }
            double[] outputs = enemy.getBrain().getOutputs();
            /*if(enemy.getDecide() == 0){
            for(int i = 0; i < outputs.length; i++){
            System.out.println("Output " + i + ": " + outputs[i]);
            }
            System.out.println("");
            }*/
        }
    }

    public String getName(){
        return name;
    }

    public Network getBrain(){
        return brain;
    }

    public void reset(){
        currentHealth = totalHealth;
        inMove = false;
        currentMove = null;
        moveTime = 0;
    }

    public void setSim(int i){
        sim = i;
    }

    public double getTotalHealth(){
        return totalHealth;
    }

    public int getDecide(){
        return decide;
    }

    public void incWins(){
        wins++;
    }

    public int getWins(){
        return wins;
    }

    public double[] getPicks(){
        return picks;
    }

    public void setValues(HashMap<Move, Double> values){
        this.values = values;
    }
}