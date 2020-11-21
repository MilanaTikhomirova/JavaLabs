import java.io.Serializable;

public class Plant implements Serializable {
    private String name;
    private String family;
    private String genus;
    private String kind;
    private int age;

    public Plant(){}

    public Plant(Plant plant){
        this.setAge(plant.getAge());
        this.setKind(plant.getKind());
        this.setGenus(plant.getGenus());
        this.setFamily(plant.getFamily());
        this.setName(plant.getName());
    }

    public Plant(String name, String family, String genus, String kind, int age){
        this.setName(name);
        this.setFamily(family);
        this.setGenus(genus);
        this.setKind(kind);
        this.setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void edit(Plant edited){
        this.setAge(edited.getAge());
        this.setKind(edited.getKind());
        this.setGenus(edited.getGenus());
        this.setFamily(edited.getFamily());
        this.setName(edited.getName());
    }
    public Object[] toObject(){
        Object[] objects = new Object[5];
        objects[0] = this.getName();
        objects[1] = this.getFamily();
        objects[2] = this.getGenus();
        objects[3] = this.getKind();
        objects[4] = this.getAge();
        return objects;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name'" + getName() + '\'' +
                ", family='" + getFamily() + '\'' +
                ", genus='" + getGenus() + '\'' +
                ", kind='" + getKind() + '\'' +
                ", age=" + getAge() +
                '}';
    }
}
