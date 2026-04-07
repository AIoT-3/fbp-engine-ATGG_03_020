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
        this.outputPort = new DefaultOutputPort(this);
    }

    @Override
    public String process(Message message) {
        return "";
    }

    public void generate(String key, Object value){
        Message message = new Message();
    }
}
