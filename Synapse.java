public class Synapse{

    private double weights;
    private Neuron first;
    private Neuron last;

    public Synapse(){
        weights = (Randomizer.getRgen(201) / 100.0) - 1.0;
    }

    public void setConnection(Neuron a, Neuron b){
        first = a;
        last = b;
    }

    public void feedforward(double input){
        input *= weights;
        last.feedforward(input);
    }
}
