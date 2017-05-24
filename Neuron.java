import java.util.ArrayList;
public class Neuron{

    private double sums;
    private ArrayList<Synapse> connections;
    private int selection;
    private double bias;

    public Neuron(int x){
        connections = new ArrayList<>();
        selection = x - 1;
        bias = (Randomizer.getRgen(101) / 100.0);
    }

    public void addConnection(int x, Neuron n){
        Synapse s = new Synapse();
        s.setConnection(this, n);
        connections.add(s);
    }

    public void feedforward(double input){
        sums += input;    
    }

    public void fire(){
        activation();
        for(Synapse c : connections){
            c.feedforward(sums);
        }
        sums = 0.0;
    }

    public void activation(){            
        sums = 1 / (1 + Math.exp((-1  * sums) - bias));
    }

    public long selection(){
        activation();
        return Math.round((sums * selection) * 10) / 10;
    }
}
