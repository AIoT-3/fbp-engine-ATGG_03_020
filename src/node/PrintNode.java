package node;

import core.Node;
import message.Message;

public class PrintNode implements Node {
    private String id;

    public PrintNode(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String process(Message message) {
        return "[ID - "+id+"] "+message;
    }
}
