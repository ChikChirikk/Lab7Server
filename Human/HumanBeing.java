package Human;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * element of collection
 */
public class HumanBeing implements Comparable<HumanBeing>, Serializable {
    private String owner;
    private long id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDate creationDate;
    private boolean realHero;
    private boolean hasToothpick;
    private Double impactSpeed;
    private String soundtrackName;
    private Double minutesOfWaiting;
    private WeaponType weaponType;
    private Car car;
    private static long n = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        n++;
        this.setId(n);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(java.time.LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isRealHero() {
        return realHero;
    }

    public void setRealHero(boolean realHero) {
        this.realHero = realHero;
    }

    public boolean isHasToothpick() {
        return hasToothpick;
    }

    public void setHasToothpick(boolean hasToothpick) {
        this.hasToothpick = hasToothpick;
    }

    public Double getImpactSpeed() {
        return impactSpeed;
    }

    public void setImpactSpeed(Double impactSpeed) {
        this.impactSpeed = impactSpeed;
    }

    public String getSoundtrackName() {
        return soundtrackName;
    }

    public void setSoundtrackName(String soundtrackName) {
        this.soundtrackName = soundtrackName;
    }

    public Double getMinutesOfWaiting() {
        return minutesOfWaiting;
    }

    public void setMinutesOfWaiting(Double minutesOfWaiting) {
        this.minutesOfWaiting = minutesOfWaiting;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public static long getN() {
        return n;
    }

    public static void setN(long n) {
        HumanBeing.n = n;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public class Coordinates implements Serializable {
        private long x;
        private long y;

        public long getX() {
            return x;
        }

        public void setX(long x) {
            this.x = x;
        }

        public long getY() {
            return y;
        }

        public void setY(long y) {
            this.y = y;
        }

    }

    public class Car implements Serializable {
        private String name;
        private Boolean cool;

        public String getCarName() {
            return name;
        }

        public void setCarName(String name) {
            this.name = name;
        }

        public Boolean isCarCool() {
            return cool;
        }

        public void setCarCool(Boolean cool) {
            this.cool = cool;
        }
    }

    public enum WeaponType implements Serializable {
        HAMMER("Молоток"),
        RIFLE("Винтовка"),
        KNIFE("Нож"),
        BAT("Дубинка");
        String title;

        WeaponType(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

    }

    public boolean isOwner(String username) {
        if (owner.equals(username)) return true;
        else return false;
    }

    public String getHuman() {
        return "Челик[id:" + this.getId() + "]:\nВладелец: " + this.getOwner() + ";\n\tИмя: " + this.getName() + ";\n\tСоздан: " + this.getCreationDate() + "; \n\tКоординаты: " + "\n\t\tx=" + this.coordinates.getX() +
                ";\n\t\ty=" + this.coordinates.getY() + "; \n\tНастоящий герой: " + this.isRealHero() + ";\n\tЕсть ли зубочистка: " + this.isHasToothpick() + ";\n\tСкорость удара: " +
                this.getImpactSpeed() + ";\n\tНазвание саундтрека: " + this.getSoundtrackName() + ";\n\tВремя ожидания: " + this.getMinutesOfWaiting() + ";\n\tТип оружия: " +
                this.weaponType.getTitle() + ";\n\tМашина:" + "\n\t\tМарка машины: " + this.car.getCarName() + ";\n\t\tПантовая ли тачка:" + this.car.isCarCool() + ".";
    }

    @Override
    public int compareTo(HumanBeing human) {
        return this.getHuman().length() - human.getHuman().length();
    }

    public long getIdAndCheck(String username){
        if (this.owner == username) return id;
        else return -1;
    }

    public int compareToAndCheckUser(String username, HumanBeing human) {
        if (this.owner == username) return this.getHuman().length() - human.getHuman().length();
        else return -1;
    }
}