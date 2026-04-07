package message;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Getter
@ToString
public class Message {
    private String id;
    private LocalDateTime timestamp;
    private Map<String, Object> payload;

    public Message(String id){
        this.id = id;
        this.timestamp = LocalDateTime.now();
        this.payload = new HashMap<>();
    }

    public <T> T get(String key) {
        return (T) payload.get(key);
    }

    public void withEntry(String key, Object value){
        this.payload.put(key,value);
    }

    public boolean hasKey(String key){
        return payload.containsKey(key);
    }

    public Message withoutKey(String key){
        this.payload.remove(key);
        return this;
    }

}
