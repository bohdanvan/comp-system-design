package com.bvan.cs.core.topology;

/**
 * @author bvanchuhov
 */
public class TopologyProperties {
    /**
     * n
     */
    private int vertices;

    /**
     * D
     */
    private int diameter;

    /**
     * D*
     */
    private double averageDiameter;

    /**
     * S
     */
    private int degree;

    public int getVertices() {
        return vertices;
    }

    public TopologyProperties setVertices(int vertices) {
        this.vertices = vertices;
        return this;
    }

    public int getDiameter() {
        return diameter;
    }

    public TopologyProperties setDiameter(int diameter) {
        this.diameter = diameter;
        return this;
    }

    public double getAverageDiameter() {
        return averageDiameter;
    }

    public TopologyProperties setAverageDiameter(double averageDiameter) {
        this.averageDiameter = averageDiameter;
        return this;
    }

    public int getDegree() {
        return degree;
    }

    public TopologyProperties setDegree(int degree) {
        this.degree = degree;
        return this;
    }

    /**
     * C = D * n * S
     * @return C
     */
    public int getPrice() {
        return diameter * vertices * degree;
    }

    /**
     * T = 2 * D* / S
     * @return T
     */
    public double getTopologicalTraffic() {
        return 2 * averageDiameter / degree;
    }

    @Override
    public String toString() {
        return String.format(
                "n = %7d; S = %4d; D = %4d; D* = %10.3f; C = %10d; T = %7.3f",
                getVertices(),
                getDegree(),
                getDiameter(),
                getAverageDiameter(),
                getPrice(),
                getTopologicalTraffic());
    }
}
