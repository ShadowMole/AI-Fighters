import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  

class NThread extends Thread{ 

    private Synapse s;
    private double a;
    private double b;

    public NThread(Synapse s, double a, double b){  
        this.s = s;
        this.a = a;
        this.b = b;
    }  

    public void run() {  
        s.learning(a,b);
        Thread.currentThread().interrupt();
        return;
    }  
}  