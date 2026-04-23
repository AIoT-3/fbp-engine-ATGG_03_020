package node;

import lombok.Getter;
import message.Message;

import java.util.concurrent.*;

@Getter
public class TimerNode extends AbstractNode {
    private final long interval;
    private int ticketCount;
    private ScheduledExecutorService scheduler;

    public TimerNode(String id,int ticketCount, long interval) {
        super(id);
        this.ticketCount = ticketCount;
        this.interval = interval;
        addOutPort("out");
    }

    @Override
    protected void onProcess(Message message) {}

    @Override
    public void initialize() {
        this.scheduler = Executors.newSingleThreadScheduledExecutor();

        this.scheduler.scheduleAtFixedRate(()-> {
            try {
                Message msg = new Message("msg");
                msg.withEntry("tick", (double) ticketCount);
                msg.withEntry("timestamp", System.currentTimeMillis());
                send("out", msg);
                ticketCount++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        },0,interval,TimeUnit.MICROSECONDS);
    }

    public void shutdown(){
        scheduler.shutdown();
    }
}
