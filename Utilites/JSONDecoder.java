package Utilites;

import Controller.HumanCollection;
import Human.HumanBeing;

import java.util.HashMap;

/**
 * convert from JSON to array of hashmaps with
 * the parameters of instances of classes, where the
 * index points to an element in the future collection
 *
 * @author Polina
 */
public class JSONDecoder {
    protected static String data;
    protected static String[] arrOfParamsOfExemplars;

    protected static int getNumberOfExemplars(String data) {
        int count = 0;
        for (char element : data.toCharArray())
            if (element == '{') count++;
        return count;
    }

    /**
     * @param data JSON file
     * @return list of hashmaps with parameters of collection's exemplar
     */
    public static HashMap[] getParamsOfExemplars(String data) {
        try {
            int number = JSONDecoder.getNumberOfExemplars(data);
            String param = "";
            String exmpl = data;
            exmpl = exmpl.replace("[", "");
            exmpl = exmpl.replace("]", "");
            exmpl = exmpl.replace("\"[\":", "");
            exmpl = exmpl.replace(" ", "");
            exmpl = exmpl.replace("		", "");
            exmpl = exmpl.replace("\"", "");
            exmpl = exmpl.replace("},", "");
            exmpl = exmpl.replace("}", "");
            exmpl = exmpl.replace("{", "");
            exmpl = exmpl.replace("	\r\n" + "	", "-");
            String[] arrOfExmpls = exmpl.split("-");
            HashMap[] mapsOfParams = new HashMap[number];
            for (int i = 0; i < number; i++) {
                HashMap mapOfParams = new HashMap();
                String[] lines = arrOfExmpls[i].split(",\r\n");
                for (int k = 0; k < lines.length; k++) {
                    String temp = lines[k];
                    temp = temp.replace("\r\n", "");
                    temp = temp.replace("	", "");
                    String[] tempArr = temp.split(":");
                    mapOfParams.put(tempArr[0], tempArr[1]);
                }
                mapsOfParams[i] = mapOfParams;
            }
            return mapsOfParams;
        } catch (Exception e) {
            return null;
        }

    }

    public static String collectionToFile(HumanCollection humans) {
        String data = "[\r\n";
        int i = 0;
        for (HumanBeing human : humans.getArray()) {
            i++;
            if (i == 0) data += "\r\n";
            data += "\t{\r\n";
            data += "\t\t\"name\": \"" + human.getName() + "\",\r\n";
            data += "\t\t\"x\": \"" + human.getCoordinates().getX() + "\",\r\n";
            data += "\t\t\"y\": \"" + human.getCoordinates().getY() + "\",\r\n";
            data += "\t\t\"realHero\": " + human.isRealHero() + ",\r\n";
            data += "\t\t\"hasToothpick\": " + human.isHasToothpick() + ",\r\n";
            data += "\t\t\"impactSpeed\": \"" + human.getImpactSpeed() + "\",\r\n";
            data += "\t\t\"soundtrackName\": \"" + human.getSoundtrackName() + "\",\r\n";
            data += "\t\t\"minutesOfWaiting\": \"" + human.getMinutesOfWaiting() + "\",\r\n";
            data += "\t\t\"weaponeType\": \"" + human.getWeaponType() + "\",\r\n";
            data += "\t\t\"carName\": \"" + human.getCar().getCarName() + "\",\r\n";
            data += "\t\t\"carCool\": " + human.getCar().isCarCool() + ",\r\n\t";
            data += "\t\"id\": " + human.getId() + "\r\n\t}";
            if (i != humans.getSize()) data += ",\r\n";
        }
        data += "\r\n]";
        return data;
    }
}
