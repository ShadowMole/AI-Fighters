public class Simulation{

    private Fighter f1;
    private Fighter f2;
    private Fighter f3;
    private Fighter f4;
    private Fighter f5;
    private Fighter f6;
    
    private Fighter f7;
    private Fighter f8;
    private Fighter f9;
    private Fighter f10;
    private Fighter f11;
    
    private Fighter[] fighters;

    public Simulation(){
        f1 = new Fighter("Fighter 1", 5, 0, 0, .2, .1, false);
        f2 = new Fighter("Fighter 2", 5, 0, 0, .1, .2, false);
        f3 = new Fighter("Fighter 3", 0, 5, 0, .2, .1, false);
        f4 = new Fighter("Fighter 4", 0, 5, 0, .1, .2, false);
        f5 = new Fighter("Fighter 5", 0, 0, 5, .2, .1, false);
        f6 = new Fighter("Fighter 6", 0, 0, 5, .1, .2, false);
        
        f7 = new Fighter("Fighter 7", 5, 0, 0, 0, 0, true);
        f8 = new Fighter("Fighter 8", 0, 5, 0, 0, .0, true);
        f9 = new Fighter("Fighter 9", 0, 0, 5, 0, 0, true);
        f10 = new Fighter("Fighter 10", 5, 5, 5, 0, 0, true);
        f11 = new Fighter("Fighter 11", 0, 0, 0, 0, 0, true);
        
        fighters = new Fighter[11];
        fighters[0] = f1;
        fighters[1] = f2;
        fighters[2] = f3;
        fighters[3] = f4;
        fighters[4] = f5;
        fighters[5] = f6;
        fighters[6] = f7;
        fighters[7] = f8;
        fighters[8] = f9;
        fighters[9] = f10;
        fighters[10] = f11;
    }

    public void sim(){
        int times = 10000;
        int limit = 1;
        for(int i = times; i > 0; i--){
            int j = 1;
            double battle = times + 1 - i;
            for(int k = 0; k < fighters.length; k++){
                for(int m = 0; m < fighters.length; m++){
                    if(k != m){
                        fighters[k].reset();
                        fighters[m].reset();
                        Battle b = new Battle(fighters[k], fighters[m], i);
                        if(i <= limit && (!fighters[k].getRandom() || !fighters[m].getRandom())){
                            System.out.println("\n ====================Battle #" + (int) battle  + "-" + j + "==================== \n" );
                        } 
                        j++;
                        b.run(limit);
                    }
                }
            }
            /*f1.reset();
            f2.reset();
            f3.reset();
            Battle b = new Battle(f1,f2,i);
            double battle = times + 1 - i;
            if(i <= limit){
                System.out.println("\n ====================Battle #" + (int) battle  + "-" + j + "==================== \n" );
            } 
            j++;
            b.run(limit);
            f1.reset();
            f2.reset();
            f3.reset();
            b = new Battle(f1,f3,i);
            if(i <= limit){
                System.out.println("\n ====================Battle #" + (int) battle  + "-" + j + "==================== \n" );
            } 
            j++;
            b.run(limit);
            f1.reset();
            f2.reset();
            f3.reset();
            b = new Battle(f2,f3,i);
            if(i <= limit){
                System.out.println("\n ====================Battle #" + (int) battle  + "-" + j + "==================== \n" );
            } 
            b.run(limit);
            j++;
            f1.reset();
            f2.reset();
            f3.reset();
            b = new Battle(f2,f1,i);
            if(i <= limit){
                System.out.println("\n ====================Battle #" + (int) battle  + "-" + j + "==================== \n" );
            } 
            j++;
            b.run(limit);
            f1.reset();
            f2.reset();
            f3.reset();
            b = new Battle(f3,f1,i);
            if(i <= limit){
                System.out.println("\n ====================Battle #" + (int) battle  + "-" + j + "==================== \n" );
            } 
            j++;
            b.run(limit);
            f1.reset();
            f2.reset();
            f3.reset();
            b = new Battle(f3,f2,i);
            if(i <= limit){
                System.out.println("\n ====================Battle #" + (int) battle  + "-" + j + "==================== \n" );
            } 
            b.run(limit);*/
            if(battle % 100 == 0){
                System.out.println(battle / 100);
                for(int n = 0; n < fighters.length; n++){
                    System.out.println(fighters[n].getName() + " wins " + (100.0 * fighters[n].getWinRate()) + "% of the time");
                    if(n == 5){
                        System.out.println("");
                    }
                }
                System.out.println("");
                /*double[] picks = f1.getPicks();
                System.out.println("Uppercut: " + picks[0]);
                System.out.println("Cross: " + picks[1]);
                System.out.println("Shielded Cross: " + picks[2]);
                System.out.println("Quick Block: " + picks[3]);
                System.out.println("Full Block: " + picks[4]);
                System.out.println("Jab: " + picks[5]);
                System.out.println("Shielded Jab: " + picks[6]);*/
            }
        }
        System.out.println("");
        System.out.println(f1.getName() + " Wins: " + f1.getWins());
        double[] picks = f1.getPicks();
        
        System.out.println("Uppercut: " + picks[0]);
        System.out.println("Cross: " + picks[1]);
        System.out.println("Shielded Cross: " + picks[2]);
        System.out.println("Quick Block: " + picks[3]);
        System.out.println("Full Block: " + picks[4]);
        System.out.println("Jab: " + picks[5]);
        System.out.println("Shielded Jab: " + picks[6]);
        System.out.println("");
        
        System.out.println(f2.getName() + " Wins: " + f2.getWins());
        picks = f2.getPicks();
        System.out.println("Uppercut: " + picks[0]);
        System.out.println("Cross: " + picks[1]);
        System.out.println("Shielded Cross: " + picks[2]);
        System.out.println("Quick Block: " + picks[3]);
        System.out.println("Full Block: " + picks[4]);
        System.out.println("Jab: " + picks[5]);
        System.out.println("Shielded Jab: " + picks[6]);
        System.out.println("");
        
        System.out.println(f3.getName() + " Wins: " + f3.getWins());
        picks = f3.getPicks();
        System.out.println("Uppercut: " + picks[0]);
        System.out.println("Cross: " + picks[1]);
        System.out.println("Shielded Cross: " + picks[2]);
        System.out.println("Quick Block: " + picks[3]);
        System.out.println("Full Block: " + picks[4]);
        System.out.println("Jab: " + picks[5]);
        System.out.println("Shielded Jab: " + picks[6]);
        System.out.println("");
        
        System.out.println(f4.getName() + " Wins: " + f4.getWins());
        picks = f4.getPicks();
        System.out.println("Uppercut: " + picks[0]);
        System.out.println("Cross: " + picks[1]);
        System.out.println("Shielded Cross: " + picks[2]);
        System.out.println("Quick Block: " + picks[3]);
        System.out.println("Full Block: " + picks[4]);
        System.out.println("Jab: " + picks[5]);
        System.out.println("Shielded Jab: " + picks[6]);
        System.out.println("");
        
        System.out.println(f5.getName() + " Wins: " + f5.getWins());
        picks = f5.getPicks();
        System.out.println("Uppercut: " + picks[0]);
        System.out.println("Cross: " + picks[1]);
        System.out.println("Shielded Cross: " + picks[2]);
        System.out.println("Quick Block: " + picks[3]);
        System.out.println("Full Block: " + picks[4]);
        System.out.println("Jab: " + picks[5]);
        System.out.println("Shielded Jab: " + picks[6]);
        System.out.println("");
        
        System.out.println(f6.getName() + " Wins: " + f6.getWins());
        picks = f6.getPicks();
        System.out.println("Uppercut: " + picks[0]);
        System.out.println("Cross: " + picks[1]);
        System.out.println("Shielded Cross: " + picks[2]);
        System.out.println("Quick Block: " + picks[3]);
        System.out.println("Full Block: " + picks[4]);
        System.out.println("Jab: " + picks[5]);
        System.out.println("Shielded Jab: " + picks[6]);
        System.out.println("");

        System.out.println(f7.getName() + " Wins: " + f7.getWins());
        System.out.println(f8.getName() + " Wins: " + f8.getWins());
        System.out.println(f9.getName() + " Wins: " + f9.getWins());
        System.out.println(f10.getName() + " Wins: " + f10.getWins());
        System.out.println(f11.getName() + " Wins: " + f11.getWins());
    }
}
