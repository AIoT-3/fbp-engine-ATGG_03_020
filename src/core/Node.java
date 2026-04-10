package core;

import message.Message;

public interface Node {
    String getId();
    void process(Message message);
    void initialize();
    void shutdown();
}
