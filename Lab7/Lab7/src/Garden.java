import java.util.Comparator;
import java.util.LinkedList;

public class Garden {
    private LinkedList<Plant> plants = new LinkedList<>();

    public void addPlant(String name, String family, String genus, String kind, int age){
        getPlants().add(new Plant(name, family, genus, kind, age));
    }

    public void addPlant(Plant plant){
        getPlants().add(plant);
    }

    public void editPlant(Plant plant, Plant edited){
       if(getPlants().contains(plant)){
           plants.get(plants.indexOf(plant)).edit(edited);
       }
    }

    public LinkedList<Plant> getPlants() {
        return plants;
    }

    public void setPlants(LinkedList<Plant> plants) {
        this.plants = plants;
    }

    public void sort(){
        Comparator<Plant> comparator = new Comparator<Plant>() {
            @Override
            public int compare(Plant o1, Plant o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        plants.sort(comparator);
    }

    public LinkedList<Plant> findByAge(int age){
        LinkedList<Plant> list = new LinkedList<>();
        for (Plant plant:plants){
            if(plant.getAge() == age)
                list.add(plant);
        }
        return list;
    }
}
