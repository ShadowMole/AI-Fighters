public class Network{

    private Neuron[] input;
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
        input = new Neuron[n];
        output = new Neuron(x);
        first = new Neuron[10];
        second = new Neuron[10];
        last = new Neuron[x];
        answer = new Neuron(x);
        for(int i = 0; i < input.length; i++){
            input[i] = new Neuron(x);
        }
        for(int i = 0; i < first.length; i++){
            first[i] = new Neuron(x);
        }
        for(int i = 0; i < second.length; i++){
            second[i] = new Neuron(x);
        }
        for(int i = 0; i < last.length; i++){
            last[i] = new Neuron(x);
        }
        setConnections(n);
    }

    public void setConnections(int x){
        for(Neuron m : input){
            for(Neuron n : first){
                m.addConnection(x, n);
            }
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
        info[0] = info[0] / 600;
        info[1] = info[1] / 500;
        info[2] = info[2] / 500;
        info[3] = info[3] / 60;
        info[4] = info[4] / 60;
        info[5] = info[5] / 25;
        info[6] = info[6] / 25;
        info[7] = info[7] / 150;
        info[8] = info[8] / 150;
        info[9] = info[9] / 50;
        info[10] = info[10] / 50;
        info[11] = info[11] / 2;
        info[12] = info[12] / 2;
        for(int i = 0; i < input.length; i++){
            input[i].feedforward(info[i]);
            input[i].fire();
        }
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
