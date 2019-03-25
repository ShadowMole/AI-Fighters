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
    private int enemyMoveTime;

    public Status(double[] info){
        health = (int) info[0] / 5;
        enemyHealth = (int) info[1] / 5;
        attack = info[2];
        enemyAttack = info[3];
        defense = info[4];
        enemyDefense = info[5];
        enemyMoveAtk = info[6];
        enemyMoveDef = info[7];
        time = (int) info[8] / 10000;
        enemyMoveTime = (int) info[9] / 100;
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

    public int getEnemyMoveTime(){
        return enemyMoveTime;
    }
    
    public int getTime(){
        return time;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        
        if(obj == null || obj.getClass()!= this.getClass()) {
            return false; 
        }
        
        Status s = (Status) obj;
        
        return (health == s.getHealth() && enemyHealth == s.getEnemyHealth() && attack == s.getAttack() && enemyAttack == s.getEnemyAttack() && defense == s.getDefense() && enemyMoveAtk == s.getEnemyMoveAtk() && enemyMoveDef == s.getEnemyMoveDef() && time == s.getTime() && enemyMoveTime == s.getEnemyMoveTime());
    }
    
    @Override
    public int hashCode(){
        return (int) health + (int) enemyHealth + (int) attack + (int) enemyAttack + (int) defense + (int) enemyDefense + (int) enemyMoveAtk + (int) enemyMoveDef + enemyMoveTime + time;
    }
}