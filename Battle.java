public class Battle{

    private Fighter f1;
    private Fighter f2;
    private static int time;

    public Battle(){
        f1 = new Fighter("Fighter 1", 100);
        f2 = new Fighter("Fighter 2", 500);
        f1.setEnemy(f2);
        f2.setEnemy(f1);
        time = 90000;
    }
    
    public static int getTime(){
        return time;
    }
    
    public void run(){
        
    }
}
