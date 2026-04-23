package node;

import message.Message;

public class DelayNode extends AbstractNode{
    private long delayMs;
    public DelayNode(String id,long delayMs) {
        super(id);
        this.delayMs = delayMs;
        addInputPort("in");
        addOutPort("out");
    }

    @Override
    protected void onProcess(Message message) {
        try {
            Thread.sleep(delayMs);
            send("out",message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

        }
    }
}
