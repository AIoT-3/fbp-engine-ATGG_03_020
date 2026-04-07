package core;

import message.Message;

public class DefaultInputPort implements InputPort {
    private Node owner;
    private String name;

    public DefaultInputPort(String name, Node owner) {
        this.owner = owner;
        this.name = name;
    }

    @Override
    public String getName() {
        return "input";
    }

    @Override
    public void receive(Message message) {
        owner.process(message);
    }

}
