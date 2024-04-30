import java.util.*;
public class DirectedEdge {
        private final int start;
        private final int end;
        private final double weight;

        /**
         * Initializes a directed edge from vertex {@code v} to vertex {@code w} with
         * the given {@code weight}.
         * @param start the tail vertex
         * @param end the head vertex
         * @param weight the weight of the directed edge
         * @throws IllegalArgumentException if either {@code v} or {@code w}
         *    is a negative integer
         * @throws IllegalArgumentException if {@code weight} is {@code NaN}
         */
        public DirectedEdge(int start, int end, double weight) {
            if (start < 0) throw new IllegalArgumentException("Vertex names must be non-negative integers");
            if (end < 0) throw new IllegalArgumentException("Vertex names must be non-negative integers");
            if (Double.isNaN(weight)) throw new IllegalArgumentException("Weight is NaN");
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        /**
         * Returns the tail vertex of the directed edge.
         * @return the tail vertex of the directed edge
         */
        public int from() {
            return start;
        }

        /**
         * Returns the head vertex of the directed edge.
         * @return the head vertex of the directed edge
         */
        public int to() {
            return end;
        }

        /**
         * Returns the weight of the directed edge.
         * @return the weight of the directed edge
         */
        public double weight() {
            return weight;
        }

        /**
         * Returns a string representation of the directed edge.
         * @return a string representation of the directed edge
         */
        public String toString() {
            return start + "->" + end + " " + String.format("%5.2f", weight);
        }
}
