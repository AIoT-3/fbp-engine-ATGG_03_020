package node;

import core.*;
import message.Message;


public class FilterNode implements Node {
    private final String id;
    private final String key;
    private final double threshold;
    private final InputPort inputPort;
    private final OutputPort outputPort;

    public FilterNode(String id, String key, double threshold, InputPort inputPort, OutputPort outputPort){
        this.id = id;
        this.key = key;
        this.threshold = threshold;
        this.inputPort = new DefaultInputPort("in",this);
        this.outputPort = new DefaultOutputPort(this);
    }

    @Override
    public String process(Message message) {
        if(message ==null||!message.hasKey(key)){
            return "null";
        }
        Object value = message.get(key);

        if(value instanceof Number){
            double numValue = ((Number) value).doubleValue();

            if(numValue >= threshold){
                outputPort.send(message);
                return "Pass"+value;
            }
        }
        return "Filter out";
    }

    @Override
    public String getId() {
        return id;
    }

    public InputPort getinputPort(){
        return inputPort;
    }
    public OutputPort getoutputPort(){
        return outputPort;
    }
}
