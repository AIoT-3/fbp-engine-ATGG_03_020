package core;

import message.Message;

public interface InputPort {
    void receive(Message message);
}
