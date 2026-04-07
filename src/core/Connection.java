package core;

import lombok.Setter;
import message.Message;

import java.util.LinkedList;
import java.util.Queue;

@Setter
public class Connection {
    private String id;
    private Queue<Message> buffer = new LinkedList<>();
    private InputPort target;

    public void deliver(Message message) {
        this.buffer.offer(message);
        if (target != null) {
            target.receive(this.buffer.poll());
        }
    }

    public int getBufferSize() {
        return buffer.size();
    }
}
