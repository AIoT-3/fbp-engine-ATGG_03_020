package core;

import lombok.Setter;
import message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Setter
public class Connection {
    private String id;
    private Queue<Message> buffer;
    private InputPort target;

    public void deliver(Message message) {
        if (target != null) {
            target.receive(message);
        }
    }

    public int getBufferSize() {
        return buffer.size();
    }

}
