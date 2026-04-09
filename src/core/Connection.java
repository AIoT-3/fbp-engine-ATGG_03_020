package core;

import lombok.Setter;
import message.Message;

import node.PrintNode;


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

    public void consumer() {
        Thread consumer = new Thread(() -> {
            PrintNode printNode = new PrintNode("print");
            try {
                while (true) {
                    Message remove = this.buffer.take();
                    printNode.process(remove);
                    if(target !=null){
                        target.receive(remove);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        consumer.start();
    }

    public int getBufferSize() {
        return buffer.size();
    }
}
