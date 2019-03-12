public class Simulation{

    private Fighter f1;
    private Fighter f2;
    private Fighter f3;

    public Simulation(){
        f1 = new Fighter("Fighter 1", 5, 0);
        f2 = new Fighter("Fighter 2", 5, 0);
        f3 = new Fighter("Fighter 3", 5, 0);
    }

    public void sim(){
        int times = 10000;
        for(int i = times; i > 0; i--){
            int j = 1;
            f1.reset();
            f2.reset();
            f3.reset();
            Battle b = new Battle(f1,f2,i);
            double battle = times + 1 - i;
            if(i <= 25){
                System.out.println("\n ====================Battle #" + battle  + "-" + j + "==================== \n" );
            } 
            j++;
            b.run();
            f1.reset();
            f2.reset();
            f3.reset();
            b = new Battle(f1,f3,i);
            if(i <= 25){
                System.out.println("\n ====================Battle #" + battle  + "-" + j + "==================== \n" );
            } 
            j++;
            b.run();
            f1.reset();
            f2.reset();
            f3.reset();
            b = new Battle(f2,f3,i);
            if(i <= 25){
                System.out.println("\n ====================Battle #" + battle  + "-" + j + "==================== \n" );
            } 
            b.run();
            j++;
            f1.reset();
            f2.reset();
            f3.reset();
            b = new Battle(f2,f1,i);
            if(i <= 25){
                System.out.println("\n ====================Battle #" + battle  + "-" + j + "==================== \n" );
            } 
            j++;
            b.run();
            f1.reset();
            f2.reset();
            f3.reset();
            b = new Battle(f3,f1,i);
            if(i <= 25){
                System.out.println("\n ====================Battle #" + battle  + "-" + j + "==================== \n" );
            } 
            j++;
            b.run();
            f1.reset();
            f2.reset();
            f3.reset();
            b = new Battle(f3,f2,i);
            if(i <= 25){
                System.out.println("\n ====================Battle #" + battle  + "-" + j + "==================== \n" );
            } 
            b.run();
            if(battle % 100 == 0){
                System.out.println(battle / 100);
                System.out.println(f1.getName() + " wins " + (100.0 * f1.getWins() / (4.0 * battle)) + "% of the time");
                System.out.println(f2.getName() + " wins " + (100.0 * f2.getWins() / (4.0 * battle)) + "% of the time");
                System.out.println(f3.getName() + " wins " + (100.0 * f3.getWins() / (4.0 * battle)) + "% of the time");
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
    }
}
