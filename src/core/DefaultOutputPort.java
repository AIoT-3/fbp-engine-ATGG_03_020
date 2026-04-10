package core;

import lombok.Getter;
import message.Message;

import java.util.ArrayList;
import java.util.List;

public class DefaultOutputPort implements OutputPort {
    List<Connection> connectionList = new ArrayList<>();
    private Node owner;
    @Getter
    private String name;

    public DefaultOutputPort(String name, Node owner) {
        this.owner = owner;
        this.name = name;
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

