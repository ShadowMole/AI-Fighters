public class VThread extends Thread{

    private Fighter f;
    private QLearn q;
    private Status s;

    public VThread(Fighter f, QLearn q, Status s){
        this.q = q;
        this.s = s;
        this.f = f;
    }  

    public void run() {  
        f.setValues(q.findState(s));
        Thread.currentThread().interrupt();
        return;
    }  
}  