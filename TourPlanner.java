import java.util.Scanner;

class Building {
  int buildingNumber;
  String buildingName, abbreviation;
  Boolean large;
}

public class TourPlanner {
  public static void main( String[] args) {
    Scanner keyboard = new Scanner(System.in);

    Building[] buildings = loadBuildings("buildings.txt");

    showAllBuildings(buildings); // for debugging
  }

  public static Building[] loadBuildings( String filename) {
    Scanner file = null;
    try {
      file = new Scanner( new java.io.File(filename));
    }
    catch ( java.io.IOException e) {
      System.err.println("Can't open '" + filename+ "' for reading.");
      System.exit(1);
    }

    int numBuildings = file.nextInt();
    Building[] buildings = new Building[numBuildings];

    // initialize buildings from file
    int buildingNum = 0;
    while ( file.hasNext() ) {
      Building b = getBuilding(file);
      if (b.buildingNumber != buildingNum) {
        System.err.print("Just read building # " + b.buildingNumber);
        System.err.println(", but " + buildingNum + " was expected.");
        System.exit(2);
      }
      buildings[buildingNum] = b;
      buildingNum++;
    }
    file.close();

    return buildings;
  }

  public static void showAllBuildings( Building[] buildings) {
    for ( Building b : buildings) {
      System.out.println( b.buildingNumber + ") " + b.buildingName + " (" +  b.large + ")" );
    }
  }

  public static Building getBuilding( Scanner f ) {
    //any buildings left in the file?
    if ( ! f.hasNextInt() )
      return null;

    Building b = new Building();
    String line;

    // read in the building # for error checking later
    b.buildingNumber = f.nextInt();
    f.nextLine(); // skip "\n" after building #

    b.abbreviation = f.nextLine();
    b.buildingName = f.nextLine();
    b.large = f.nextLine().equals("Large");

    // Should be done; return the Building
    return b;
  }
}
