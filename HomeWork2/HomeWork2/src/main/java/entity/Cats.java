package entity;

public class Cats  extends BaseEntity {

    public int length;
    private String Nickname;
    private String SpesiesAnimal;
    private float age;

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String nickname) {
        Nickname = nickname;
    }

    public String getSpesiesAnimal() {
        return SpesiesAnimal;
    }

    public void setSpesiesAnimal(String spesiesAnimal) {
        SpesiesAnimal = spesiesAnimal;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public float getAge() {
        return age;
    }
}
