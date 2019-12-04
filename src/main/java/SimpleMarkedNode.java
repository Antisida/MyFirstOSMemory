import java.util.Objects;

public class SimpleMarkedNode {
    private long id;
    private long wayId;
    private boolean visited;
    private int subgraphId;

    public SimpleMarkedNode(long id, long wayId) {
        this.id = id;
        this.wayId = wayId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getWayId() {
        return wayId;
    }

    public void setWayId(long wayId) {
        this.wayId = wayId;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getSubgraphId() {
        return subgraphId;
    }

    public void setSubgraphId(int subgraphId) {
        this.subgraphId = subgraphId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleMarkedNode that = (SimpleMarkedNode) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SimpleMarkedNode{" +
                "id=" + id +
                ", wayId=" + wayId +
                ", visited=" + visited +
                ", subgraphId=" + subgraphId +
                '}';
    }
}
