package core;

import message.Message;

public interface OutputPort {
    String getName();
    void connect(Connection connection);
    void send(Message message);
}
