public class Battle{

    private Fighter f1;
    private Fighter f2;
    private static int time;
    private int sim;

    public Battle(Fighter f1, Fighter f2, int i){
        this.f1 = f1;
        this.f2 = f2;
        f1.setSim(i);
        f2.setSim(i);
        f1.setEnemy(f2);
        f2.setEnemy(f1);
        time = 90000;
        sim = i;
    }

    public static int getTime(){
        return time;
    }

    public void run(int limit){
        while(time > 0 && f1.getHealth() > 0 && f2.getHealth() > 0){
            f1.act();
            f2.act();
            time--;

            if(sim <= limit){
                if(time % 1000 == 0){
                    System.out.println("Time Left: " + time/1000 + " seconds");
                    System.out.println(f1.getName() + " Health: " + f1.getHealth());
                    System.out.println(f2.getName() + " Health: " + f2.getHealth() + "\n");
                } 
            }
        }
        if(time == 0 || (f2.getHealth() <= 0 && f1.getHealth() <= 0)){
            if(sim <= limit){
                System.out.println("There is a tie.");
            }
        }else if(f2.getHealth() <= 0){
            if(sim <= limit){
                System.out.println(f1.getName() + " wins!!!");
            }
            f1.incWins();
        }else if(f1.getHealth() <= 0){
            if(sim <= limit){
                System.out.println(f2.getName() + " wins!!!");
            }
            f2.incWins();
        }
        if(!f1.getName().equals("Fighter 3")){
            f1.endBattleLearn();
        }
        if(!f2.getName().equals("Fighter 3")){
            f2.endBattleLearn();
        }
        /*try{
        Thread.sleep(10);
        }
        catch(Exception e){
        System.out.print(e);
        }*/
    }
}
