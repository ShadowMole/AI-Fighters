public class Status {

    private int health;
    private int enemyHealth;
    private double attack;
    private double enemyAttack;
    private double defense;
    private double enemyDefense;
    private double enemyMoveAtk;
    private double enemyMoveDef;
    private int time;

    public Status(double[] info){
        health = (int) info[0] / 25;
        enemyHealth = (int) info[1] / 25;
        attack = info[2];
        enemyAttack = info[3];
        defense = info[4];
        enemyDefense = info[5];
        enemyMoveAtk = info[6];
        enemyMoveDef = info[7];
        time = (int) info[8] / 10000;
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
    
    public int getTime(){
        return time;
    }

    public boolean equals(Status s){
        if(health == s.getHealth() && enemyHealth == s.getEnemyHealth() && attack == s.getAttack() && enemyAttack == s.getEnemyAttack() && defense == s.getDefense() && enemyMoveAtk == s.getEnemyMoveAtk() && enemyMoveDef == s.getEnemyMoveDef() && time == s.getTime()){
            return true;
        }
        return false;
    }
}