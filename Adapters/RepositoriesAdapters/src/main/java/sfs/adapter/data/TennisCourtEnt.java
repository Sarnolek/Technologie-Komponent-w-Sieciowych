package sfs.adapter.data;

public class TennisCourtEnt extends SportsFacilityEnt {

    private SurfaceTypeEnt surfaceTypeEnt;
    private boolean isIndoor;
    public TennisCourtEnt(String name, double pricePerHour, int capacity, SurfaceTypeEnt surfaceTypeEnt, boolean isIndoor) {
        super(name, pricePerHour, capacity);
        this.surfaceTypeEnt = surfaceTypeEnt;
        this.isIndoor = isIndoor;
    }

    public SurfaceTypeEnt getSurfaceType() {
        return surfaceTypeEnt;
    }

    public void setSurfaceType(SurfaceTypeEnt surfaceTypeEnt) {
        this.surfaceTypeEnt = surfaceTypeEnt;
    }

    public boolean isIndoor() {
        return isIndoor;
    }

    public void setIndoor(boolean indoor) {
        isIndoor = indoor;
    }

    @Override
    public String toString() {
        return "TennisCourt{" +
                super.toString() +
                ", surfaceType=" + surfaceTypeEnt +
                ", isIndoor=" + isIndoor +
                '}';
    }
}
