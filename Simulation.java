public class Simulation{

    private Fighter f1;
    private Fighter f2;

    public Simulation(){
        f1 = new Fighter("Fighter 1", 100, 0, 15);
        f2 = new Fighter("Fighter 2", 500, 15, 0);
    }

    public void sim(){
        for(int i = 100; i > 0; i--){
            f1.reset();
            f2.reset();
            Battle b = new Battle(f1,f2,i);
            double battle = 101 - i;
            System.out.println("\n Battle #" + battle + "\n" );
            b.run();
        }
    }
}
