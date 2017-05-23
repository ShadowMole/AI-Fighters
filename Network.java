public class Network{

    private Neuron input;
    private Neuron[] first;
    private Neuron[] second;
    private Neuron[] last;
    private Neuron output;
    private Neuron answer;

    /**
     * Constructor for objects of class Network
     */
    public Network(int n, int x)
    {
        input = new Neuron(n, x);
        output = new Neuron(n, x);
        first = new Neuron[10];
        second = new Neuron[10];
        last = new Neuron[x];
        answer = new Neuron(n, x);

        for(int i = 0; i < first.length; i++){
            first[i] = new Neuron(n, x);
        }
        for(int i = 0; i < second.length; i++){
            second[i] = new Neuron(n, x);
        }
        for(int i = 0; i < last.length; i++){
            last[i] = new Neuron(n, x);
        }
        setConnections(n);
    }

    public void setConnections(int x){
        for(Neuron n : first){
            input.addConnection(x, n);
        }
        for(Neuron n : first){
            for(Neuron m : second){
                n.addConnection(x, m);
            }
        }
        for(Neuron n : second){
            for(Neuron m : last){
                n.addConnection(x, m);
            }
        }
        for(Neuron n : last){
            n.addConnection(x, output);
        }
        output.addConnection(x, answer);
    }

    public int makeDecision(double[] info){
        input.feedforward(info);
        input.fire();
        for(Neuron n : first){
            n.fire();
        }
        for(Neuron n : second){
            n.fire();
        }
        for(Neuron n : last){
            n.fire();
        }
        output.fire();
        int selection = (int) answer.selection();
        return selection;
    }
}
