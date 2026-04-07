package core;

import message.Message;

public class DefaultInputPort implements InputPort{
    private Node owner;

    public DefaultInputPort(Node owner){
        this.owner = owner;
    }
    @Override
    public void receive(Message message) {
        owner.process(message);
    }

}
