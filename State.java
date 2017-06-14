public class State {

    private double distance;
    private int health;
    private int enemyHealth;
    private double attack;
    private double enemyAttack;
    private double defense;
    private double enemyDefense;
    private double enemyMoveAtk;
    private double enemyMoveDef;
    private double dir;
    private double enemyDir;
    private double time;

    public State(double[] info){
        distance = info[0];
        health = (int) info[1] / 10;
        enemyHealth = (int) info[2] / 10;
        attack = info[3];
        enemyAttack = info[4];
        defense = info[5];
        enemyDefense = info[6];
        enemyMoveAtk = info[7];
        enemyMoveDef = info[8];
        dir = info[9];
        enemyDir = info[10];
        time = info[11];
    }

    public double getDistance(){
        return distance;
    }
    
    public int getHealth(){
        return health;
    }
    
    public int getEnemyHealth(){
        return enemyHealth;
    }

    public double getAttack(){
        return attack;
    }

    public double getEnemyAttack(){
        return enemyAttack;
    }

    public double getDefense(){
        return defense;
    }

    public double getEnemyDefense(){
        return enemyDefense;
    }

    public double getEnemyMoveAtk(){
        return enemyMoveAtk;
    }

    public double getEnemyMoveDef(){
        return enemyMoveDef;
    }

    public double getDir(){
        return dir;
    }

    public double getEnemyDir(){
        return enemyDir;
    }
    
    public double getTime(){
        return time;
    }

    public boolean equals(State s){
        if(distance == s.getDistance() && health == s.getHealth() && enemyHealth == s.getEnemyHealth() && attack == s.getAttack() && enemyAttack == s.getEnemyAttack() && defense == s.getDefense() && enemyMoveAtk == s.getEnemyMoveAtk() && enemyMoveDef == s.getEnemyMoveDef() && dir == s.getDir() && enemyDir == s.getEnemyDir() && time == s.getTime()){
            return true;
        }
        return false;
    }
}