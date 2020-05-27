package Controller;


import Human.HumanBeing;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * class receiver contains collection and managing methods
 *
 * @author Diana
 * @author Polina
 */
public class HumanCollection implements Serializable {
    private static List<HumanBeing> humans = Collections.synchronizedList(new ArrayList<HumanBeing>());
    private static LocalDate dateCreation;


    public void setStream(Stream<HumanBeing> stream) {
        humans = (ArrayList<HumanBeing>) stream.collect(Collectors.toList());
        stream.close();
    }

    public static void setDateCreation(LocalDate dateCreation1) {
        dateCreation = dateCreation1;
    }

    public static LocalDate getDateCreation() {
        return dateCreation;
    }

    public ArrayList<HumanBeing> getArray() {
        return (ArrayList<HumanBeing>) humans;
    }

    public void setArray(ArrayList array) {
        this.humans = array;
    }

    public void removeHuman(String key) {
        this.humans.remove(key);
    }

    public HumanBeing getHuman(int ind) {
        return this.humans.get(ind);
    }

    public void removeHuman(int key) {
        this.humans.remove(key);
    }

    public int getSize() {
        return this.humans.size();
    }

    public void clear() {
        this.humans.clear();
    }

    public String getInfo() {
        return "Тип коллекции: ArrayList;\nKоличество элементов коллекции: " + this.getSize() + ";\nДата создания кол"
                + "лекции: " + dateCreation + ".";
    }

    public int findIndexOfElemById(int id) {
        int i = 0;
        for (HumanBeing human : humans) {
            if (human.getId() == id) return i;
            i++;
        }
        return -1;
    }

    public HumanBeing findHumanById(int id) {
        int index = this.findIndexOfElemById(id);
        if (index == -1) return null;
        else return humans.get(index);
    }

    public void addToCollection(HumanBeing human) {
        humans.add(human);
    }

    public void toSortArray() {
        TreeSet<HumanBeing> set = new TreeSet<>();
        for (HumanBeing human : humans) set.add(human);
        ArrayList<HumanBeing> temp = new ArrayList();
        for (HumanBeing human : set) temp.add(human);
        this.setArray(temp);
    }

}	

