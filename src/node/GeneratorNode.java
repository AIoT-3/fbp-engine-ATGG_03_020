package node;

import core.DefaultOutputPort;
import core.Node;
import core.OutputPort;
import lombok.Getter;
import message.Message;

@Getter
public class GeneratorNode implements Node {
    private String id;
    private OutputPort outputPort;

    public GeneratorNode(String id) {
        this.id = id;
        this.outputPort = new DefaultOutputPort("out", this);
    }

    public void generate(String key, Object value) {
        Message message = new Message(key);
        message.withEntry(key, value);
        this.outputPort.send(message);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void process(Message message) {
    }

    @Override
    public void initialize() {

    }

    @Override
    public void shutdown() {

    }


}
