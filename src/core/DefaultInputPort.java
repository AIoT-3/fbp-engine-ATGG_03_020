package core;

import lombok.Getter;
import message.Message;

public class DefaultInputPort implements InputPort {
    private Node owner;
    @Getter
    private String name;

    public DefaultInputPort(String name, Node owner) {
        this.owner = owner;
        this.name = name;
    }

    @Override
    public void receive(Message message) {
        owner.process(message);
    }

}
