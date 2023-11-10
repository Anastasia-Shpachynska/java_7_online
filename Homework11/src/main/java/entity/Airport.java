package entity;

public class Airport extends BaseEntity {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id='" + getId() + '\'' +
                " name='" + name + '\'' +
                '}';
    }
}
