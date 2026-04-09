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

    public void producer() {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    generate("data", "메시지-" + i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        producer.start();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String process(Message message) {
        return "";
    }

    public void generate(String key, Object value) {
        Message message = new Message(key);
        message.withEntry(key, value);
        this.outputPort.send(message);

    }
}
