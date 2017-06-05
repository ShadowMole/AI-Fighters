public class Battle{

    private Fighter f1;
    private Fighter f2;
    private static int time;

    public Battle(Fighter f1, Fighter f2, int i){
        this.f1 = f1;
        this.f2 = f2;
        f1.setSim(i);
        f2.setSim(i);
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
        }else if(f2.getHealth() <= 0){
            System.out.println("Fighter 1 wins!!!");
        }else if(f1.getHealth() <= 0){
            System.out.println("Fighter 2 wins!!!");
        }
        f1.getBrain().learn((f2.getHealth() - f2.getTotalHealth()) / (f1.getHealth() - f1.getTotalHealth()));
       // f2.getBrain().learn((f1.getHealth() - f1.getTotalHealth()) / (f2.getHealth() - f2.getTotalHealth()));
        try{
            Thread.sleep(10);
        }
        catch(Exception e){
            System.out.print(e);
        }
    }
}
