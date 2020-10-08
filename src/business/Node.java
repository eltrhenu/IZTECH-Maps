package business;

public abstract class Node implements Comparable<Node>{
    private String id;
    private String category;
    private CategoryType type;
    private String location;


    public Node(String id,String category, CategoryType type, String location) {
        this.category = category;
        this.id = id;
        this.type = type;
        this.location = location;

    }

    public String getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public CategoryType getType() {
        return type;
    }



    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", type=" + type +
                ", location='" + location + '\'' +
                '}'+ "\n";
    }
    @Override
    public int compareTo(Node other) {
        int thisIdInt = Integer.parseInt(this.getId());
        int otherIdInt = Integer.parseInt(other.getId());

        if (thisIdInt == otherIdInt) {
            return 0;
        } else if (thisIdInt < otherIdInt) {
            return 1;
        } else {
            return -1;
        }
    }
}
