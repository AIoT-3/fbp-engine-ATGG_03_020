package core;

import message.Message;

import java.util.ArrayList;
import java.util.List;

public class DefaultOutputPort implements OutputPort {
    List<Connection> connectionList = new ArrayList<>();
    private Node owner;

    public DefaultOutputPort(Node owner) {
        this.owner = owner;
    }

    @Override
    public String getName() {
        return "output";
    }

    @Override
    public void connect(Connection connection) {
        connectionList.add(connection);
    }
    
    @Override
    public void send(Message message) {
        for(Connection connection : connectionList){
            connection.deliver(message);
        }
    }
}

