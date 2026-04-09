package core;

import lombok.Setter;
import message.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Setter
public class Connection {
    private String id;
    private BlockingQueue<Message> buffer = new LinkedBlockingQueue<>(100);
    private InputPort target;

    public Message poll(){
        return this.buffer.poll();
    }

    public void deliver(Message message) {
        try {
            buffer.put(message);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public int getBufferSize() {
        return buffer.size();
    }
}
