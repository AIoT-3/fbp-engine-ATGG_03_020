package core;

import message.Message;

public interface Node {
    String getId();
    String process(Message message);
}
