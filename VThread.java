import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.HashMap;

class VThread implements Runnable { 

    private Fighter f;
    private QLearn q;
    private State s;

    public VThread(Fighter f, QLearn q, State s){  
        this.q = q;
        this.s = s;
        this.f = f;
    }  

    public void run() {  
       f.setValues(q.findState(s));
    }  
}  