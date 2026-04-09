package node;

import core.Connection;
import core.DefaultInputPort;
import core.InputPort;
import core.Node;
import lombok.Getter;
import message.Message;

@Getter
public class PrintNode implements Node {
    private final String id;
    private final InputPort inputPort;
    private volatile boolean running = true;

    public PrintNode(String id) {
        this.id = id;
        this.inputPort = new DefaultInputPort("in", this);
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public String process(Message message) {
        String result = "[ID - " + id + "] " + message;
        System.out.println(result);
        return result;
    }
}
