import lombok.Data;

@Data
class Coordinates {
    double longitude;
    double latitude;

    Coordinates(double longitude, double latitude){
        this.longitude=longitude;
        this.latitude=latitude;
    }

    @Override
    public String toString() {
        return "{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
