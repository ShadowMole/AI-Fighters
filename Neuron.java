import java.util.ArrayList;
public class Neuron{

    private double[] sums;
    private ArrayList<Synapse> connections;
    private int selection;

    public Neuron(int n, int x){
        sums = new double[n];
        connections = new ArrayList<>();
        for(int i = 0; i < sums.length; i++){
            sums[i] = 0.0;
        }
        selection = x;
    }

    public void addConnection(int x, Neuron n){
        Synapse s = new Synapse(x);
        s.setConnection(this, n);
        connections.add(s);
    }

    public void feedforward(double[] input){
        for(int i = 0; i < sums.length; i++){
            sums[i] += input[i];
        }     
    }

    public void fire(){
        activation();
        for(Synapse c : connections){
            c.feedforward(sums);
        }
        for(int i = 0; i < sums.length; i++){
            sums[i] = 0.0;
        }
    }

    public void activation(){            
        sums[0] = 600 / (1 + Math.exp(-1 * (sums[0]/(600/4))));
        sums[1] = 700 / (1 + Math.exp(-1 * (sums[1]/(700/4))));
        sums[2] = 700 / (1 + Math.exp(-1 * (sums[2]/(700/4))));
        sums[3] = 60 / (1 + Math.exp(-1 * (sums[3]/(60/4))));
        sums[4] = 60 / (1 + Math.exp(-1 * (sums[4]/(60/4))));
        sums[5] = 40 / (1 + Math.exp(-1 * (sums[5]/(40/4))));
        sums[6] = 40 / (1 + Math.exp(-1 * (sums[6]/(40/4))));
        sums[7] = 100 / (1 + Math.exp(-1 * (sums[7]/(100/4))));
        sums[8] = 100 / (1 + Math.exp(-1 * (sums[8]/(100/4))));
        sums[9] = 1 / (1 + Math.exp(-1 * (sums[9]/(1/4))));
        sums[10] = 1 / (1 + Math.exp(-1 * (sums[10]/(1/4))));
    }

    public long selection(){
      /*  for(int i = 0; i < sums.length; i++){
            System.out.println(i + " = " + sums[i]);
        } */
        double sum  = 0;
        for(int i = 0; i < sums.length; i++){
            sum += sums[i];
            sums[i] = 0.0;
        }
        sum = (selection - 1) / (1 + Math.exp(-1 * (sum/(2500 / 4))));
        return Math.round(sum * 10) / 10;
    }
}
