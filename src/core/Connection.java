package core;

import lombok.Setter;
import message.Message;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Setter
public class Connection {
    private String id;
    private BlockingQueue<Message> buffer = new LinkedBlockingQueue<>();
    private InputPort target;

    public void deliver(Message message) {
        try {
            this.buffer.put(message);
            if (target != null) {
                target.receive(buffer.take());
            }
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }

    public int getBufferSize() {
        return buffer.size();
    }
}
