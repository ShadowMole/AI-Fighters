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
        sums[0] = 600 / (1 + Math.exp(-1 * sums[0]));
        sums[1] = 700 / (1 + Math.exp(-1 * sums[0]));
        sums[2] = 700 / (1 + Math.exp(-1 * sums[0]));
        sums[3] = 60 / (1 + Math.exp(-1 * sums[0]));
        sums[4] = 60 / (1 + Math.exp(-1 * sums[0]));
        sums[5] = 40 / (1 + Math.exp(-1 * sums[0]));
        sums[6] = 40 / (1 + Math.exp(-1 * sums[0]));
        sums[7] = 100 / (1 + Math.exp(-1 * sums[0]));
        sums[8] = 100 / (1 + Math.exp(-1 * sums[0]));
        sums[9] = 1 / (1 + Math.exp(-1 * sums[0]));
        sums[10] = 1 / (1 + Math.exp(-1 * sums[0]));
    }

    public long selection(){
        double sum  = 0;
        for(int i = 0; i < sums.length; i++){
            sum += sums[i];
            sums[i] = 0.0;
        }
        sum = selection / (1 + Math.exp(-1 * sum));
        return Math.round(sum);
    }
}
