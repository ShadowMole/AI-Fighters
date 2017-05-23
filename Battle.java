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
        while(time > 0 && f1.getHealth() > 0 && f2.getHealth() > 0){
            f1.act();
            f2.act();
            time--;
            if(time % 1000 == 0){
                System.out.println("Time Left: " + time/1000 + " seconds");
                System.out.println(f1.getName() + " Health: " + f1.getHealth());
                System.out.println(f2.getName() + " Health: " + f2.getHealth() + "\n");
            }
        }
        if(time == 0){
            System.out.println("There is a tie.");
        }else if(f2.getHealth() == 0){
            System.out.println("Fighter 1 wins!!!");
        }else{
            System.out.println("Fighter 2 wins!!!");
        }
    }
}
