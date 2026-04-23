package node;

import core.*;
import message.Message;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractNode implements Node {
    protected final String id;
    private final Map<String, InputPort> inputPorts = new HashMap<>();
    private final Map<String, OutputPort> outputPorts = new HashMap<>();

    public AbstractNode(String id) {
        this.id = id;
    }

    protected void addInputPort(String name) {
        InputPort inputPort = new DefaultInputPort(name, this);
        inputPorts.put(name, inputPort);
    }

    protected void addOutPort(String name) {
        OutputPort outputPort = new DefaultOutputPort(name, this);
        outputPorts.put(name, outputPort);
    }

    public InputPort getInputPort(String name) {
        return inputPorts.get(name);
    }

    public OutputPort getOutputPort(String name) {
        return outputPorts.get(name);
    }

    protected void send(String portName, Message message) {
        OutputPort outputPort = outputPorts.get(portName);
        if (outputPort != null) {
            outputPort.send(message);
        }
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void process(Message message) {
        onProcess(message);
    }

    protected abstract void onProcess(Message message);


    @Override
    public void initialize() {}

    @Override
    public void shutdown() {}
}
