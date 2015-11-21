package com.bvan.cs.core.adjaster;

/**
 * @author bvanchuhov
 */
public final class AdjasterFactory {
    private static final LinearAdjaster LINEAR_ADJASTER = new LinearAdjaster();
    private static final RingAdjaster RING_ADJASTER = new RingAdjaster();

    private AdjasterFactory() {
    }

    public static Adjaster simpleAdjaster() {
        return linearAdjaster();
    }

    public static Adjaster linearAdjaster() {
        return LINEAR_ADJASTER;
    }

    public static Adjaster ringAdjaster() {
        return RING_ADJASTER;
    }
}
