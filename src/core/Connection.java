package core;

import lombok.Getter;
import lombok.Setter;
import message.Message;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Setter
@Getter
public class Connection {
    private String id;
    private BlockingQueue<Message> buffer;
    private InputPort target;

    public Connection(){
        this(100);
    }
    public Connection(int capacity) {
        this.buffer = new LinkedBlockingQueue<>(capacity);
    }
    public Message poll(){
        try {
            return this.buffer.take();
        } catch (InterruptedException e){
            throw new IllegalStateException();
        }
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

    public void bk() {






    }
}
