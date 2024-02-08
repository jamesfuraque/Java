public class GPS_Collections {
    private int max;
    private int num;
    private GPS_Coordinates[] coordinates;

    public GPS_Collections(int max) {
        this.max = max;
        this.num = 0;
        this.coordinates = new GPS_Coordinates[max];
    }

    public boolean addCoordinates(double longitude, double latitude, String name) {
        if (num < max) {
            GPS_Coordinates newCoordinate = new GPS_Coordinates(longitude, latitude, name);
            int index = num;

            // Insert in descending order by latitude + longitude
            while (index > 0 && compareCoordinates(newCoordinate, coordinates[index - 1]) > 0) {
                coordinates[index] = coordinates[index - 1];
                index--;
            }

            coordinates[index] = newCoordinate;
            num++;
            return true;
        } else {
            System.out.println("GPS_Collections is full. Cannot add more coordinates.");
            return false;
        }
    }

    public GPS_Coordinates searchGPS(double longitude, double latitude) {
        for (int i = 0; i < num; i++) {
            if (coordinates[i].getLongitude() == longitude && coordinates[i].getLatitude() == latitude) {
                return coordinates[i];
            }
        }
        return null; // Not found
    }

    private int compareCoordinates(GPS_Coordinates coord1, GPS_Coordinates coord2) {
        // Compare by latitude + longitude
        double value1 = coord1.getLatitude() + coord1.getLongitude();
        double value2 = coord2.getLatitude() + coord2.getLongitude();
        return Double.compare(value2, value1);
    }
}
