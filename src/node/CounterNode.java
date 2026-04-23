package node;

import message.Message;

public class CounterNode extends AbstractNode{
    private int count;

    public CounterNode(String id) {
        super(id);
        addInputPort("in");
        addOutPort("out");
        this.count = 0;
    }

    @Override
    protected void onProcess(Message message) {
        count++;
        Message msg = new Message(id);
        msg.getPayload().putAll(message.getPayload());
        message.withEntry("count",count);
        send("out",msg);
    }

    public void shutdown(){
        System.out.println("["+getId()+"] "+ "총 처리 메시지 : "+count+"건");
    }
}
