package sfs.adapter.data;

public class GymEnt extends SportsFacilityEnt {
    private int areaInSqm;
    private boolean hasSauna;

    public GymEnt(String name, double pricePerHour, int capacity, int areaInSqm, boolean hasSauna) {
        super(name, pricePerHour, capacity);
        this.areaInSqm = areaInSqm;
        this.hasSauna = hasSauna;
    }

    public int getAreaInSqm() {
        return areaInSqm;
    }

    public void setAreaInSqm(int areaInSqm) {
        this.areaInSqm = areaInSqm;
    }

    public boolean isHasSauna() {
        return hasSauna;
    }

    public void setHasSauna(boolean hasSauna) {
        this.hasSauna = hasSauna;
    }

    @Override
    public String toString() {
        return "Gym{" +
                super.toString() +
                ", areaInSqm=" + areaInSqm +
                ", hasSauna=" + hasSauna +
                '}';
    }
}
