package node;

import core.*;
import message.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class FilterNode extends AbstractNode {
    private final String key;
    private final double threshold;
    private final InputPort inputPort;
    private final OutputPort outputPort;
    private BlockingQueue<Message> buffer = new LinkedBlockingQueue<>();
    private volatile boolean running = true;

    public FilterNode(String id, String key, double threshold) {
        super(id);
        this.key = key;
        this.threshold = threshold;
        this.inputPort = new DefaultInputPort("in", this);
        this.outputPort = new DefaultOutputPort("out",this);
        addInputPort("in");
        addOutPort("out");
        this.running = true;
    }
    public InputPort getinputPort() {
        return inputPort;
    }

    public OutputPort getoutputPort() {
        return outputPort;
    }
    @Override
    protected void onProcess(Message message) {
        Object value = message.get(key);

        if (value instanceof Number) {
            double numValue = ((Number) value).doubleValue();
            if (numValue >= threshold) {
                send("out",message);
            }
        }
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void shutdown() {

    }


}
