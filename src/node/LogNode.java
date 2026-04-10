package node;

import message.Message;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LogNode extends AbstractNode{

    public LogNode(String id) {
        super(id);
        addInputPort("in");
        addOutPort("out");
    }

    @Override
    protected void onProcess(Message message) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        String formatTime = LocalTime.now().format(dateTimeFormatter);
        System.out.println("["+formatTime+"] ["+id+"]"+message);
        send("out",message);
    }
}
