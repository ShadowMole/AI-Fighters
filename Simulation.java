public class Simulation{

    private Fighter f1;
    private Fighter f2;

    public Simulation(){
        f1 = new Fighter("Fighter 1", 100, 0, 15);
        f2 = new Fighter("Fighter 2", 500, 15, 0);
    }

    public void sim(){
        for(int i = 11000000; i > 0; i--){
            f1.reset();
            f2.reset();
            Battle b = new Battle(f1,f2,i);
            double battle = 11000001 - i;
            if(battle % 100000 == 0){
                System.out.println(battle / 100000);
            }
            if(i < 25){
                System.out.println("\n ====================Battle #" + battle + "==================== \n" );
            }    
            b.run();
        }
        System.out.println("");
        System.out.println(f1.getName() + " Wins: " + f1.getWins());
        System.out.println(f2.getName() + " Wins: " + f2.getWins());
        double[] picks = f1.getPicks();
        System.out.println("Monday: " + picks[0]);
        System.out.println("Tuesday: " + picks[1]);
        System.out.println("Wednesday: " + picks[2]);
        System.out.println("Thursday: " + picks[3]);
        System.out.println("Friday: " + picks[4]);
        System.out.println("Saturday: " + picks[5]);
        System.out.println("Sunday: " + picks[6]);
    }
}
