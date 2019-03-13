import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  

public class QThread extends Thread { 

    private QLearn q;
    private Status s;
    private Move m;
    private double health;
    private double enemy;
    private double winrate;

    public QThread(QLearn q, Status s, Move m, double health, double enemy, double wr){
        this.q = q;
        this.s = s;
        this.m = m;
        this.health = health;
        this.enemy = enemy;
        winrate = wr;
    }  

    public void run() {  
        q.newQValue(s, m, health, enemy, winrate);
        Thread.currentThread().interrupt();
        return;
    }  
}  