package node;

import lombok.Getter;
import message.Message;

@Getter
public class SplitNode extends AbstractNode {
    private final String key;
    private final double threshold;

    public SplitNode(String id,String key,double threshold) {
        super(id);
        addInputPort("in");
        addOutPort("match");
        addOutPort("mismatch");
        this.key = key;
        this.threshold = threshold;
    }

    @Override
    protected void onProcess(Message message) {
        Double value = message.get(key);
        if(value>=threshold){
            send("match",message);
        }else {
            send("mismatch",message);
        }
    }
}
