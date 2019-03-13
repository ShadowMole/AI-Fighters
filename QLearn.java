import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import java.util.HashMap;

public class QLearn{

    private double learn;
    private double discount;
    private ConcurrentHashMap<Status,ConcurrentHashMap<Move,Double>> lastValues;
    private ConcurrentHashMap<Status,ConcurrentHashMap<Move,Double>> maxValues;
    private double reward;
    private double qValue;
    private double maxReward;

    public QLearn(double l, double d){
        lastValues = new ConcurrentHashMap<>();
        maxValues = new ConcurrentHashMap<>();
        learn = l;
        discount = d;
        maxReward = 0;
    }

    public void checkState(Status s, Move m){
        Iterator i = lastValues.keySet().iterator();
        while(i.hasNext()){
            Status x = (Status) i.next();
            //for(State x : lastValues.keySet()){
            //   for(State y : maxValues.keySet()){
            if(s.equals(x)/* && s.equals(y)*/){
                if(!checkAction(m, lastValues.get(x), maxValues.get(x))){
                    lastValues.get(x).put(m, getQValue(0,0));
                    maxValues.get(x).put(m, getQValue(0,0));
                }
                return;
                //      }
            }
        }
        lastValues.put(s, new ConcurrentHashMap<Move,Double>());
        maxValues.put(s, new ConcurrentHashMap<Move,Double>());
        lastValues.get(s).put(m, getQValue(0,0));
        maxValues.get(s).put(m, getQValue(0,0));
    }

    public boolean checkAction(Move m, ConcurrentHashMap<Move,Double> last, ConcurrentHashMap<Move,Double> max){
        for(Move x : last.keySet()){
            //    for(Move y : max.keySet()){
            if(m.equals(x)/* && m.equals(y)*/){
                double value = getQValue(last.get(x), max.get(x));
                last.replace(x, last.get(x), value);
                if(qValue > max.get(x)){
                    max.replace(x, max.get(x), value);
                }
                return true;
                //      }
            }
        }
        return false;
    }

    public double getQValue(double last, double max){
        qValue = ((1 - learn) * last) + (learn * (reward + discount * max));
        return qValue;
    }

    public void calcReward(double health, double enemy, double knew, double newEnemy, double winRate){
        double top = (enemy - newEnemy);
        double bottom = (health - knew);
        if(bottom == 0){
            bottom = 1;
        }
        reward = (top / bottom) + winRate;
        if(reward > maxReward){
            maxReward = reward;
        }
    }

    public void newQValue(Status s, Move m, double newHealth, double enemyHealth, double winrate){
        calcReward(s.getHealth(), s.getEnemyHealth(), newHealth, enemyHealth, winrate);
        checkState(s,m);
    }

    public double getReward(){
        return reward;
    }

    public double getValue(){
        return qValue;
    }

    public HashMap<Move,Double> findState(Status s){
        for(Status x : maxValues.keySet()){
            //  for(State y : lastValues.keySet()){
            if(s.equals(x)/* && s.equals(y)*/){
                HashMap<Move,Double> state = new HashMap<>();
                for(Move m : maxValues.get(x).keySet()){
                    for(Move n : lastValues.get(x).keySet()){
                        if(n.equals(m)){
                            state.put(m,((1 - learn) * lastValues.get(x).get(n)) + (learn * (reward + discount * maxValues.get(x).get(m))));
                        }
                    }
                }
                return state;
                //  }
            }
        }
        return null;
    }

    public double getError(){
        return maxReward - reward;
    }
}