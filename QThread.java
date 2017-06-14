import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  

class QThread implements Runnable { 

    private QLearn q;
    private State s;
    private Move m;
    private double health;
    private double enemy;

    public QThread(QLearn q, State s, Move m, double health, double enemy){  
        this.q = q;
        this.s = s;
        this.m = m;
        this.health = health;
        this.enemy = enemy;
    }  

    public void run() {  
         q.newQValue(s, m, health, enemy);
    }  
}  