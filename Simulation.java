public class Simulation{

    private Fighter f1;
    private Fighter f2;

    public Simulation(){
        f1 = new Fighter("Fighter 1", 15, 0);
        f2 = new Fighter("Fighter 2", 15, 0);
    }

    public void sim(){
        int times = 10000;
        for(int i = times; i > 0; i--){
            f1.reset();
            f2.reset();
            Battle b = new Battle(f1,f2,i);
            double battle = times + 1 - i;
            if(battle % 1000 == 0){
                System.out.println(battle / 1000);
            }
            if(i <= 25){
                System.out.println("\n ====================Battle #" + battle + "==================== \n" );
            }    
            b.run();
        }
        System.out.println("");
        System.out.println(f1.getName() + " Wins: " + f1.getWins());
        System.out.println(f2.getName() + " Wins: " + f2.getWins());
        double[] picks = f1.getPicks();
        System.out.println("Uppercut: " + picks[0]);
        System.out.println("Cross: " + picks[1]);
        System.out.println("Shielded Cross: " + picks[2]);
        System.out.println("Quick Block: " + picks[3]);
        System.out.println("Full Block: " + picks[4]);
        System.out.println("Jab: " + picks[5]);
        System.out.println("Shielded Jab: " + picks[6]);
    }
}
