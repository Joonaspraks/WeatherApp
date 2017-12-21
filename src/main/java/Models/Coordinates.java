package Models;

import lombok.Data;

@Data
public class Coordinates {
    double longitude;
    double latitude;

    public Coordinates(double longitude, double latitude){
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
