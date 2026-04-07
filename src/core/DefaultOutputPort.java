package core;

import message.Message;

import java.util.ArrayList;
import java.util.List;

public class DefaultOutputPort implements OutputPort{
    List<Connection> connectionList = new ArrayList<>();
    private Connection connection;
    private Node owner;

    public DefaultOutputPort(Node owner){
        this.owner = owner;
    }

    @Override
    public void connect(Connection connection){
        connectionList.add(connection);
    }

    @Override
    public void getName() {

    }

    @Override
    public void send(Message message) {
        connection.deliver(message);

    }
}

