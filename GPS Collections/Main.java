import java.util.Scanner;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Create a GPS_Collections object with a maximum capacity of 5
        GPS_Collections gpsCollection = new GPS_Collections(15);

        // Add coordinates to the GPS_Collections
        gpsCollection.addCoordinates(37.7749, -122.4194, "San Francisco");
        gpsCollection.addCoordinates(34.0522, -118.2437, "Los Angeles");
        gpsCollection.addCoordinates(40.7128, -74.0060, "New York");
        gpsCollection.addCoordinates(41.8781, -87.6298, "Chicago");
        gpsCollection.addCoordinates(32.7767, -96.7970, "Dallas");

        // Try to add one more coordinate, which should fail because the collection is full
        gpsCollection.addCoordinates(30.2500, -97.7500, "Austin");

        // Search for a coordinate
        GPS_Coordinates result = gpsCollection.searchGPS(34.0522, -118.2437);
        if (result != null) {
            System.out.println("Coordinate found: " + result.getName());
        } else {
            System.out.println("Coordinate not found.");
        }

        System.out.print("Enter latitude: ");
        double userLatitude = scanner.nextDouble();
        System.out.print("Enter longitude: ");
        double userLongitude = scanner.nextDouble();

        // Search for the coordinates based on the entered latitude and longitude
        GPS_Coordinates resultByCoordinates = gpsCollection.searchGPS(userLongitude, userLatitude);
        if (resultByCoordinates != null) {
            System.out.println("Coordinates found based on entered latitude and longitude:");
            System.out.println("Latitude: " + resultByCoordinates.getLatitude());
            System.out.println("Longitude: " + resultByCoordinates.getLongitude());
            System.out.println("Place Name: " + resultByCoordinates.getName());
        } else {
            System.out.println("Coordinates not found based on entered latitude and longitude.");
        }

        scanner.close();
    }
}
