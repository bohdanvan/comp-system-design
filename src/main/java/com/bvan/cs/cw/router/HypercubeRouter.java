package com.bvan.cs.cw.router;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.bvan.requirements.Requirements.requiredNotNegativeArg;
import static java.math.BigInteger.ONE;

/**
 * @author bvanchuhov
 */
public final class HypercubeRouter<T> extends AbstractRouter<T> {

    private int hypercubeDegree;

    public HypercubeRouter(List<T> nodes, Set<T> corruptedNodes, int hypercubeDegree) {
        super(nodes, corruptedNodes);
        this.hypercubeDegree = requiredNotNegativeArg(hypercubeDegree, "hypercubeDegree");
    }

    public HypercubeRouter(List<T> nodes, int hypercubeDegree) {
        this(nodes, new HashSet<T>(), hypercubeDegree);
    }

    @Override
    protected List<Integer> getNextIds(int myId, int finishId) {
        BigInteger myHypercubeId = BigInteger.valueOf(myId);
        BigInteger hypercubeFinishId = BigInteger.valueOf(finishId);

        BigInteger diff = myHypercubeId.xor(hypercubeFinishId);
        List<Integer> bitsForChange = bitsForChange(diff);

        return toNextIds(myHypercubeId, bitsForChange);
    }

    protected List<Integer> getNextIds(int myId) {
        BigInteger myHypercubeId = BigInteger.valueOf(myId);

        List<Integer> bitsForChange = allBitsForChange();

        return toNextIds(myHypercubeId, bitsForChange);
    }

    private List<Integer> toNextIds(BigInteger myHypercubeId, List<Integer> bitsForChange) {
        return bitsForChange.stream()
                .map(bitForChange -> myHypercubeId.flipBit(bitForChange).intValue())
                .collect(Collectors.toList());
    }

    private List<Integer> allBitsForChange() {
        return bitsForChange(BigInteger.ZERO.setBit(hypercubeDegree).subtract(ONE));
    }

    private List<Integer> bitsForChange(BigInteger mask) {
        List<Integer> bitsForChange = new ArrayList<>(oneIndexes(mask, hypercubeDegree));
        bitsForChange.addAll(zeroIndexes(mask, hypercubeDegree));

        return bitsForChange;
    }

    private static List<Integer> oneIndexes(BigInteger n, int size) {
        List<Integer> oneIndexes = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (n.testBit(i)) {
                oneIndexes.add(i);
            }
        }

        return oneIndexes;
    }

    private static List<Integer> zeroIndexes(BigInteger n, int size) {
        List<Integer> oneIndexes = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            if (!n.testBit(i)) {
                oneIndexes.add(i);
            }
        }

        return oneIndexes;
    }
}
