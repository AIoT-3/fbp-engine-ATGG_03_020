package flow;

import core.Connection;
import core.InputPort;
import core.OutputPort;
import lombok.Getter;
import node.AbstractNode;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Flow {
    private String id;
    private Map<String, AbstractNode> nodes;
    private List<String> connections;

    public Flow(String id) {
        this.id = id;
        this.nodes = new HashMap<>();
        this.connections = new ArrayList<>();
    }

    public Flow addNode(AbstractNode node) {
        nodes.put(node.getId(), node);
        return this;
    }

    public Flow connect(String sourceNodeId, String sourcePort, String targetNodeId, String targetPort) {
        AbstractNode sourceNode = nodes.get(sourceNodeId);
        AbstractNode targetNode = nodes.get(targetNodeId);
        if (sourceNode == null || targetNode == null) throw new IllegalArgumentException();

        OutputPort out = sourceNode.getOutputPort(sourcePort);
        InputPort in = targetNode.getInputPort(targetPort);
        if (out == null || in == null) throw new IllegalArgumentException();

        Connection connection = new Connection();
        out.connect(connection);
        connection.setTarget(in);
        connection.start();

        String connectionId = sourceNodeId + ":" + sourcePort + "->" + targetNodeId + ":" + targetPort;
        connections.add(connectionId);
        return this;
    }

    public void initialize() {
        nodes.values().forEach(node -> {
            node.initialize();
        });
    }

    public void shutdown() {
        nodes.values().forEach(node -> {
            node.shutdown();
        });
    }

    public List<String> validate() {
        if (nodes.isEmpty()) {
            throw new IllegalArgumentException();
        }

        for (String connectionId : connections) {
            String sourceNodeId = connectionId.split("->")[0].split(":")[0];
            String targetNodeId = connectionId.split("->")[1].split(":")[0];

            if (!nodes.containsKey(sourceNodeId)) {
                throw new IllegalArgumentException();
            }
            if (!nodes.containsKey(targetNodeId)) {
                throw new IllegalArgumentException();
            }
        }

        Map<String, List<String>> graph = new HashMap<>();
        for (String connectionId : connections) {
            String source = connectionId.split("->")[0].split(":")[0];
            String target = connectionId.split("->")[1].split(":")[0];
            graph.computeIfAbsent(source, k -> new ArrayList<>()).add(target);
        }

        Map<String, String> state = new HashMap<>();
        nodes.keySet().forEach(node -> {
            state.put(node, "UNVISITED");
        });
        List<String> errors = new ArrayList<>();
        nodes.keySet().forEach(node -> {
            if (state.get(node).equals("UNVISITED")) {
                dfs(node, state, graph, errors);
            }
        });
        return errors;
    }

    void dfs(String nodeId, Map<String, String> state, Map<String, List<String>> graph, List<String> errors) {
        state.put(nodeId, "VISITING");

        List<String> nextNodes = graph.getOrDefault(nodeId, new ArrayList<>());
        for (String next : nextNodes) {
            if (state.get(next).equals("VISITING")) {
                errors.add("순환 참조 발견: " + nodeId + " -> " + next);
            } else if (state.get(next).equals("UNVISITED")) {
                dfs(next, state, graph, errors);
            }
        }

        state.put(nodeId, "VISITED");
    }
}