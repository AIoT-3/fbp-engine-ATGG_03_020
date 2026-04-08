package core;

import lombok.Setter;
import message.Message;
import node.GeneratorNode;
import node.PrintNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Setter
public class Connection {
    private String id;
    private BlockingQueue<Message> buffer = new LinkedBlockingQueue<>(100);
    private InputPort target;

    public void deliver(Message message) {
        Thread producer = new Thread(() -> {
            for(int i = 0; i<5; i++){
                GeneratorNode generatorNode = new GeneratorNode("gen");
                try {
                    this.buffer.put(message);
                    Thread.sleep(1000);
                    if (target != null) {
                        target.receive(buffer.take());
                    }
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
            try{
                buffer.put(message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() ->{

            PrintNode printNode = new PrintNode("print");
            Connection connection = new Connection();
            try{
            while(true) {
                Message remove = buffer.take();
                connection.buffer.poll();
                printNode.process(remove);

            }} catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });
        producer.start();
        consumer.start();
    }

    public int getBufferSize() {
        return buffer.size();
    }
}
