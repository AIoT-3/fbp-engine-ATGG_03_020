package core;

import message.Message;

public interface InputPort {
    void getName();
    void receive(Message message);
}
