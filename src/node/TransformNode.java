package node;

import message.Message;

import java.util.function.Function;

public class TransformNode extends AbstractNode {
    private final Function<Message, Message> transformer;

    public TransformNode(String id, Function<Message, Message> transformer) {
        super(id);
        this.transformer = transformer;
        addInputPort("in");
        addOutPort("out");
    }

    @Override
    protected void onProcess(Message message) {
        Message msg = transformer.apply(message);
        if (msg != null) {
            send("out", msg);
        }
    }
}
