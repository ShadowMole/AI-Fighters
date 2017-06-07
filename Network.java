import java.util.ArrayList;
import java.util.HashMap;
public class Network{

    private Neuron[] input;
    private Neuron[] first;
    private Neuron[] second;
    private Neuron[] last;
    private Neuron[] output;
    private double error;
    private double[] outputs;

    /**
     * Constructor for objects of class Network
     */
    public Network(int n, int x){
        input = new Neuron[n];
        first = new Neuron[10];
        second = new Neuron[10];
        output = new Neuron[x];
        last = new Neuron[x];
        for(int i = 0; i < input.length; i++){
            input[i] = new Neuron(x);
            input[i].setCommand("");
        }
        for(int i = 0; i < first.length; i++){
            first[i] = new Neuron(x);
            first[i].setCommand("");
        }
        for(int i = 0; i < second.length; i++){
            second[i] = new Neuron(x);
            second[i].setCommand("");
        }
        for(int i = 0; i < last.length; i++){
            last[i] = new Neuron(x);
            last[i].setCommand("");
        }
        for(int i = 0; i < output.length; i++){
            output[i] = new Neuron(x);
        }
        output[0].setCommand("Monday");
        output[0].setCommand("Tuesday");
        output[0].setCommand("Wednesday");
        output[0].setCommand("Thursday");
        output[0].setCommand("Friday");
        output[0].setCommand("Saturday");
        output[0].setCommand("Sunday");
        outputs = new double[x];
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
            for(Neuron m : output){
                n.addConnection(x, m);
            }
        } 
    }

    public int makeDecision(double[] info, HashMap<Move,Double> state){
        info[0] = info[0] / 600;
        info[1] = info[1] / 500;
        info[2] = info[2] / 500;
        info[3] = info[3] / 60;
        info[4] = info[4] / 60;
        info[5] = info[5] / 25;
        info[6] = info[6] / 25;
        info[7] = info[7] / 150;
        info[8] = info[8] / 50;
        info[9] = info[9] / 2;
        info[10] = info[10] / 2;
        info[11] = info[11] / 90000;
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
        if(state != null){
            for(int i = 0; i < output.length; i++){
                outputs[i] = checkNeuron(state, output[i]);
            }
        }else{
            for(int i = 0; i < output.length; i++){
                outputs[i] = output[i].selection();
            }
        }
        return getSelection();
    }

    public void learn(double error){
        this.error = .1 * error;
        ArrayList<Double> hiddenDelta = new ArrayList<>();
        for(Neuron n : last){
            for(Synapse s : n.getConnections()){
                s.lastLearning(error);
                hiddenDelta.add(s.getHiddenDelta(error));
            }
        }
        ArrayList<Double> deltaWeights = new ArrayList<>();
        for(Neuron n : second){
            for(Double d : hiddenDelta){
                deltaWeights.add(n.getLastInput() * d);
            }
        }
        int count = 0;
        for(Neuron n : second){
            for(Synapse s : n.getConnections()){
                s.learning(deltaWeights.get(count));
                count++;
            }
        }
        hiddenDelta = new ArrayList<>();
        for(Neuron n : second){
            for(Synapse s : n.getConnections()){
                hiddenDelta.add(s.getHiddenDelta(error));
            }
        }
        deltaWeights = new ArrayList<>();
        for(Neuron n : first){
            for(Double d : hiddenDelta){
                deltaWeights.add(n.getLastInput() * d);
            }
        }
        count = 0;
        for(Neuron n : first){
            for(Synapse s : n.getConnections()){
                s.learning(deltaWeights.get(count));
                count++;
            }
        }
        hiddenDelta = new ArrayList<>();
        for(Neuron n : first){
            for(Synapse s : n.getConnections()){
                hiddenDelta.add(s.getHiddenDelta(error));
            }
        }
        deltaWeights = new ArrayList<>();
        for(Neuron n : second){
            for(Double d : hiddenDelta){
                deltaWeights.add(n.getLastInput() * d);
            }
        }
        count = 0;
        for(Neuron n : input){
            for(Synapse s : n.getConnections()){
                s.learning(deltaWeights.get(count));
                count++;
            }
        }
    }

    public int getSelection(){
        int x = -1;
        for(int i = 0; i < outputs.length; i++){
            if(x == -1){
                x = i;
            }else if(outputs[i] > outputs[x]){
                x = i;
            }
        }
        return x;
    }

    public double[] getOutputs(){
        return outputs;
    }

    public double checkNeuron(HashMap<Move,Double> state, Neuron n){
        for(Move a : state.keySet()){
            if(n.getCommand().equals(a.getName())){
                return n.selection() * state.get(a);
            }
        }
        return n.selection();
    }
}
