package com.bvan.cs.core.connector;

/**
 * @author bvanchuhov
 */
public final class ConnectorFactory {

    private static final LinearConnector LINEAR_CONNECTOR = new LinearConnector();
    private static final RingConnector RING_CONNECTOR = new RingConnector();
    private static final HypercubeConnector HYPERCUBE_CONNECTOR = new HypercubeConnector();

    private ConnectorFactory() {
    }

    public static Connector simpleConnector() {
        return linearConnector();
    }

    public static Connector linearConnector() {
        return LINEAR_CONNECTOR;
    }

    public static Connector ringConnector() {
        return RING_CONNECTOR;
    }

    public static Connector hypercubeConnector() {
        return HYPERCUBE_CONNECTOR;
    }
}
