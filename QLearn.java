import java.util.HashMap;

public class QLearn{

    private double learn;
    private double discount;
    private double lastEnemy;
    private double lastHealth;
    private HashMap<State,HashMap<Move,Double>> lastValues;
    private HashMap<State,HashMap<Move,Double>> maxValues;
    private double reward;
    private double qValue;
    private double maxQValue;
    private double maxReward;

    public QLearn(){
        lastEnemy = 500;
        lastHealth = 500;
        lastValues = new HashMap<>();
        maxValues = new HashMap<>();
        learn = .01;
        discount = .01;
        maxReward = 0;
    }

    public boolean checkState(State s, Move m){
        for(State x : lastValues.keySet()){
            if(s.equals(x)){
                if(checkAction(m, lastValues.get(x), maxValues.get(x))){
                }else{
                    lastValues.get(x).put(m, getQValue(0,0));
                    maxValues.get(x).put(m, getQValue(0,0));
                }
                return true;
            }
        }
        return false;
    }

    public boolean checkAction(Move m, HashMap<Move,Double> last, HashMap<Move,Double> max){
        for(Move x : last.keySet()){
            if(m.equals(x)){
                double qValue = getQValue(last.get(x), max.get(x));
                last.replace(x, last.get(x), qValue);
                if(qValue > max.get(x)){
                    max.replace(x, max.get(x), qValue);
                }
                return true;
            }
        }
        return false;
    }

    public double getQValue(double last, double max){
        qValue = ((1 - learn) * last) + (learn * (reward + discount * max));
        maxQValue = max;
        return ((1 - learn) * last) + (learn * (reward + discount * max));
    }

    public void calcReward(double health, double enemy){
        double top = (lastEnemy - enemy);
        double bottom = (lastHealth - health);
        if(bottom == 0){
            bottom = 1;
        }
        reward = top / bottom;
        if(reward > maxReward){
            maxReward = reward;
        }
        lastEnemy = enemy;
        lastHealth = health;
    }

    public void newQValue(State s, Move m){
        calcReward(s.getHealth(), s.getEnemyHealth());
        checkState(s,m);
    }

    public void reset(){
        lastEnemy = 500;
        lastHealth = 500;
    }

    public double getReward(){
        return reward;
    }

    public double getValue(){
        return qValue;
    }

    public double getMax(){
        return maxQValue;
    }

    public double getMaxReward(){
        return maxReward;
    }
}