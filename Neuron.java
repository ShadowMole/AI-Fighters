import java.util.ArrayList;
public class Neuron{

    private double sums;
    private ArrayList<Synapse> connections;
    private int selection;
    private double bias;
    private double lastInput;
    private double lastOutput;

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
        lastInput = sums;
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
        lastInput = sums;
        activation();
        lastOutput = sums;
        double answer = sums;
        sums = 0.0;
        return Math.round((answer * selection) * 10) / 10;
    }

    public double getLastInput(){
        return lastInput;
    }

    public double primeActivation(){
        return (Math.exp(lastInput - bias)) / ((Math.exp(lastInput - bias) + 1) * (Math.exp(lastInput - bias) + 1));
    }

    public double getLastOutput(){
        return lastOutput;
    }
    
    public ArrayList<Synapse> getConnections(){
        return connections;
    }
}
