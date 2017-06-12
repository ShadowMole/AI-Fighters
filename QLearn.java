import java.util.HashMap;

public class QLearn{

    private double learn;
    private double discount;
    private HashMap<State,HashMap<Move,Double>> lastValues;
    private HashMap<State,HashMap<Move,Double>> maxValues;
    private double reward;
    private double qValue;
    private double maxReward;

    public QLearn(){
        lastValues = new HashMap<>();
        maxValues = new HashMap<>();
        learn = .01;
        discount = .01;
        maxReward = 0;
    }

    public void checkState(State s, Move m){
        for(State x : lastValues.keySet()){
            for(State y : maxValues.keySet()){
                if(s.equals(x) && s.equals(y)){
                    if(!checkAction(m, lastValues.get(x), maxValues.get(y))){
                        lastValues.get(x).put(m, getQValue(0,0));
                        maxValues.get(y).put(m, getQValue(0,0));
                    }
                    return;
                }
            }
        }
        lastValues.put(s, new HashMap<Move,Double>());
        maxValues.put(s, new HashMap<Move,Double>());
        lastValues.get(s).put(m, getQValue(0,0));
        maxValues.get(s).put(m, getQValue(0,0));
    }

    public boolean checkAction(Move m, HashMap<Move,Double> last, HashMap<Move,Double> max){
        for(Move x : last.keySet()){
            for(Move y : max.keySet()){
                if(m.equals(x) && m.equals(y)){
                    double qValue = getQValue(last.get(x), max.get(y));
                    last.replace(x, last.get(x), qValue);
                    if(qValue > max.get(y)){
                        max.replace(y, max.get(y), qValue);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public double getQValue(double last, double max){
        qValue = ((1 - learn) * last) + (learn * (reward + discount * max));
        return ((1 - learn) * last) + (learn * (reward + discount * max));
    }

    public void calcReward(double health, double enemy, double knew, double newEnemy){
        double top = (enemy - newEnemy);
        double bottom = (health - knew);
        if(bottom == 0){
            bottom = 1;
        }
        reward = top / bottom;
        if(reward > maxReward){
            maxReward = reward;
        }
    }

    public void newQValue(State s, Move m, double newHealth, double enemyHealth){
        calcReward(s.getHealth(), s.getEnemyHealth(), newHealth, enemyHealth);
        checkState(s,m);
    }

    public double getReward(){
        return reward;
    }

    public double getValue(){
        return qValue;
    }

    public HashMap<Move,Double> findState(State s){
        for(State x : maxValues.keySet()){
            for(State y : lastValues.keySet()){
                if(s.equals(x) && s.equals(y)){
                    HashMap<Move,Double> state = new HashMap<>();
                    for(Move m : maxValues.get(x).keySet()){
                        for(Move n : lastValues.get(y).keySet()){
                            if(n.equals(m)){
                                state.put(m,((1 - learn) * lastValues.get(y).get(n)) + (learn * (reward + discount * maxValues.get(x).get(m))));
                            }
                        }
                    }
                    return state;
                }
            }
        }
        return null;
    }

    public double getError(){
        return maxReward - reward;
    }
}