package node;

import core.DefaultInputPort;
import core.InputPort;
import core.Node;
import lombok.Getter;
import message.Message;

@Getter
public class PrintNode extends AbstractNode {
    private volatile boolean running = true;

    public PrintNode(String id) {
        super(id);
        this.running = true;
        addInputPort("in");
    }

    @Override
    protected void onProcess(Message message) {
        String result = "[ID - " + id + "] " + message;
        System.out.println(result);
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void shutdown() {
        super.shutdown();
    }
}
