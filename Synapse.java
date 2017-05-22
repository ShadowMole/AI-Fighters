public class Synapse{
    
    private double[] weights;
    private Neuron first;
    private Neuron last;
    
    public Synapse(int n){
        weights = new double[n];
        for(int i = 0; i < weights.length; i++){
            weights[i] = (Randomizer.getRgen(201) / 100.0) - 1.0;
        }
    }
    
    public void setConnection(Neuron a, Neuron b){
        first = a;
        last = b;
    }
    
    public void feedforward(double[] input){
        for(int i = 0; i < input.length; i++){
            input[i] *= weights[i];
        }
        last.feedforward(input);
    }
}
