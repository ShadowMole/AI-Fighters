import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Network{

    private Neuron[] input;
    private Neuron[] first;
    private Neuron[] second;
    private Neuron[] last;
    private Neuron[] output;
    private double error;
    private double[] outputs;
    private ExecutorService executor;

    /**
     * Constructor for objects of class Network
     */
    public Network(int n, int x){
        executor = Executors.newFixedThreadPool(5);
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
        output[0].setCommand("Uppercut");
        output[1].setCommand("Cross");
        output[2].setCommand("Shielded Cross");
        output[3].setCommand("Quick Block");
        output[4].setCommand("Fulll Block");
        output[5].setCommand("Jab");
        output[6].setCommand("Shielded Jab");
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
        info[0] = info[0] / 500;
        info[1] = info[1] / 500;
        info[2] = info[2] / 50;
        info[3] = info[3] / 50;
        info[4] = info[4] / 25;
        info[5] = info[5] / 25;
        info[6] = info[6] / 100;
        info[7] = info[7] / 100;
        info[8] = info[8] / 90000;
        info[9] = info[9] / 1200;
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
        this.error = error;
        ArrayList<Double> hiddenDelta = new ArrayList<>();
        for(Neuron n : last){
            for(Synapse s : n.getConnections()){
                s.lastLearning(error);
                hiddenDelta.add(s.getHiddenDelta());
            }
        }
        double deltaOutput = 0;
        int i = 0;
        for(Neuron n : output){
            deltaOutput += n.getDeltaOutput(error);
            i++;
        }
        deltaOutput /= i;
        int count = 0;
        for(Neuron n : second){
            for(Synapse s : n.getConnections()){
                if(count == hiddenDelta.size()){
                    count = 0;
                }
                //Runnable thread = new NThread(s, hiddenDelta.get(count), deltaOutput);
                //executor.execute(thread);
                s.learning(hiddenDelta.get(count), deltaOutput);
                count++;
            }
        }
        count = 0;
        for(Neuron n : first){
            for(Synapse s : n.getConnections()){
                if(count == hiddenDelta.size()){
                    count = 0;
                }
                //Runnable thread = new NThread(s, hiddenDelta.get(count), deltaOutput);
                //executor.execute(thread);
                s.learning(hiddenDelta.get(count), deltaOutput);
                count++;
            }
        }
        count = 0;
        for(Neuron n : input){
            for(Synapse s : n.getConnections()){
                if(count == hiddenDelta.size()){
                    count = 0;
                }
                //Runnable thread = new NThread(s, hiddenDelta.get(count), deltaOutput);
                //executor.execute(thread);
                s.learning(hiddenDelta.get(count), deltaOutput);
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
        double delete = n.selection();
        return 0;
    }
}
