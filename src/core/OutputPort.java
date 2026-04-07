package core;

import message.Message;

public interface OutputPort {
    void getName();
    void connect(Connection connection);
    void send(Message message);
}
