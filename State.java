public class State {

    private double distance;
    private double health;
    private double enemyHealth;
    private double attack;
    private double enemyAttack;
    private double defense;
    private double enemyDefense;
    private double enemyMoveAtk;
    private double enemyMoveDef;
    private double dir;
    private double enemyDir;

    public State(double[] info){
        distance = info[0];
        health = info[1];
        enemyHealth = info[2];
        attack = info[3];
        enemyAttack = info[4];
        defense = info[5];
        enemyDefense = info[6];
        enemyMoveAtk = info[7];
        enemyMoveDef = info[8];
        dir = info[9];
        enemyDir = info[10];
    }

    public double getDistance(){
        return distance;
    }
    
    public double getHealth(){
        return health;
    }
    
    public double getEnemyHealth(){
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

    public boolean equals(State s){
        if(distance == s.getDistance() && health == s.getHealth() && enemyHealth == s.getEnemyHealth() && attack == s.getAttack() && enemyAttack == s.getEnemyAttack() && defense == s.getDefense() && enemyMoveAtk == s.getEnemyMoveAtk() && enemyMoveDef == s.getEnemyMoveDef() && dir == s.getDir() && enemyDir == s.getEnemyDir()){
            return true;
        }
        return false;
    }
}