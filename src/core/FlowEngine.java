package core;

import flow.Flow;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class FlowEngine {
    private Map<String, Flow> flows;
    private State state;
    private enum State {INITIALIZED, RUNNING, STOPPED}

    public FlowEngine(Map<String, Flow> flows) {
        this.flows = flows;
        this.state = State.INITIALIZED;
    }

    public Flow register(String id, Flow flow) {
        flows.put(id, flow);
        System.out.println("[Engine] 플로우 "+id+" 등록됨");
        return flow;
    }

    public void startFlow(String flowId) {
        Flow flow = flows.get(flowId);
        if (flow == null) {
            throw new IllegalArgumentException();
        }
        List<String> error = flow.validate();
        if (!error.isEmpty()) {
            throw new IllegalStateException(error.toString());
        }
        System.out.println("[Engine] 플로우 "+flowId+" 시작됨");
        this.state = State.RUNNING;
        flow.initialize();
    }

    public void stopFlow(String flowId) {
        Flow flow = flows.get(flowId);
        flow.shutdown();
        System.out.println("[Engine] 플로우 "+flowId+" 정지됨");
    }

    public void shutdown() {
        flows.values().forEach(flow -> flow.shutdown());
        this.state = State.STOPPED;
    }

}
