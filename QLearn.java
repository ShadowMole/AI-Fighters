//import java.util.concurrent.ConcurrentHashMap;
import java.util.Iterator;
import java.util.HashMap;

public class QLearn{

    private double learn;
    private double discount;
    private HashMap<Status,HashMap<Move,Double>> lastValues;
    private HashMap<Status,HashMap<Move,Double>> maxValues;
    private double reward;
    private double qValue;
    private double maxReward;

    public QLearn(double l, double d){
        lastValues = new HashMap<>();
        maxValues = new HashMap<>();
        learn = l;
        discount = d;
        maxReward = 0;
    }

    public void checkState(Status s, Move m){
        //Iterator i = lastValues.keySet().iterator();
       // while(i.hasNext()){
          //  Status x = (Status) i.next();
       if(lastValues.containsKey(s)){
            //for(State x : lastValues.keySet()){
            //   for(State y : maxValues.keySet()){
            //if(s.equals(x)/* && s.equals(y)*/){
            if(!checkAction(m, lastValues.get(s), maxValues.get(s))){
                lastValues.get(s).put(m, getQValue(0,0));
                maxValues.get(s).put(m, getQValue(0,0));
            }
                //return;
                //      }
            //}
       }else{
            lastValues.put(s, new HashMap<Move,Double>());
            maxValues.put(s, new HashMap<Move,Double>());
            lastValues.get(s).put(m, getQValue(0,0));
            maxValues.get(s).put(m, getQValue(0,0));
       }
    }

    public boolean checkAction(Move m, HashMap<Move,Double> last, HashMap<Move,Double> max){
        if(last.containsKey(m)){
            //    for(Move y : max.keySet()){
            double value = getQValue(last.get(m), max.get(m));
            last.replace(m, last.get(m), value);
            if(qValue > max.get(m)){
                max.replace(m, max.get(m), value);
            }
            return true;
                //      }
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
        reward = (top / bottom);
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
        //for(Status x : maxValues.keySet()){
            //  for(State y : lastValues.keySet()){
            //if(s.equals(x)/* && s.equals(y)*/)
        if(maxValues.containsKey(s)){
            HashMap<Move,Double> state = new HashMap<>();
            for(Move m : maxValues.get(s).keySet()){
                for(Move n : lastValues.get(s).keySet()){
                    if(n.equals(m)){
                        state.put(m,((1 - learn) * lastValues.get(s).get(n)) + (learn * (reward + discount * maxValues.get(s).get(m))));
                    }
                }
            }
            return state;
                //  }
        }
        //}
        return null;
    }

    public double getError(){
        //Normalized error
        return (reward / maxReward);
    }
}