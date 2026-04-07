package core;

import message.Message;

public interface InputPort {
    String getName();
    void receive(Message message);
}
