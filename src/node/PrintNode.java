package node;

import core.Connection;
import core.DefaultInputPort;
import core.InputPort;
import core.Node;
import lombok.Getter;
import message.Message;

@Getter
public class PrintNode implements Node {
    private String id;
    private InputPort inputPort;
    private volatile boolean running = true;

    public PrintNode(String id) {
        this.id = id;
        this.inputPort = new DefaultInputPort("in", this);
    }

    public void printThread(Connection inCon) {
        Thread thread = new Thread(() -> {
            try {
                while (running) {
                    Message message = inCon.poll();
                    if (message != null) {
                        process(message);
                    } else {
                        Thread.sleep(10);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
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
