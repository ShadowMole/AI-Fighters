public class Synapse{

    private double weights;
    private Neuron first;
    private Neuron last;
    private double lastWeight;
    private double deltaOutput;

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

    public void lastLearning(double error){
        deltaOutput = error * last.primeActivation();
        double deltaWeight = last.getLastOutput() * deltaOutput;
        lastWeight = weights;
        weights += deltaWeight;
    }

    public void learning(double deltaOutput, double oldWeight){
        double deltaHidden = deltaOutput * oldWeight * first.primeActivation();
        double deltaWeight = deltaHidden * first.getLastInput();
        weights += deltaWeight;
    }

    public double getWeight(){
        return weights;
    }

    public double getLastWeight(){
        return lastWeight;
    }

    public double getDeltaOutput(){
        return deltaOutput;
    }
}
