import java.util. * ;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System. in );

    System.out.println("Enter the number of rows:");
    int rows = scanner.nextInt() + 1;

    System.out.println("Enter the number of seats in each row:");
    int seats = scanner.nextInt() + 1;
    System.out.println();

    char[][] array = createArray(rows, seats);

    double[] stats = new double[4];

    stats[0] = 0;

    int allSeats = (rows - 1) * (seats - 1);
    int firstRow = (rows - 1) / 2;
    int secondRow = (rows - 1) - firstRow;

    if (allSeats < 60) {
      stats[3] = allSeats * 10;
    } else {
      stats[3] = (firstRow * (seats - 1) * 10) + (secondRow * (seats - 1) * 8);
    }

    boolean isTrue = true;
    while (isTrue) {
      System.out.println("1. Show the seats");
      System.out.println("2. Buy a ticket");
      System.out.println("3. Statistics");
      System.out.println("0. Exit");
      int menu = scanner.nextInt();

      switch (menu) {
      case 1:
        showTheSeats(array);
        break;
      case 2:
        buyTicket(array, rows, seats, stats);
        break;
      case 3:
        statistics(stats);
        break;
      case 0:
        isTrue = false;
        break;
      default:
        break;

      }
    }
  }

  public static char[][] createArray(int rows, int seats) {
    char[][] array = new char[rows][seats];
    array[0][0] = ' ';

    char chSeats = '\u0031';
    for (int i = 1; i < seats; i++) {
      array[0][i] = chSeats;
      chSeats++;
    }

    char chRows = '\u0031';
    for (int i = 1; i < rows; i++) {
      array[i][0] = chRows;
      chRows++;
    }

    for (int i = 1; i < rows; i++) {
      for (int j = 1; j < seats; j++) {
        array[i][j] = 'S';
      }
    }

    return array;

  }

  public static void showTheSeats(char[][] array) {
    System.out.println("Cinema:");
    for (char[] arr: array) {
      for (char elem: arr) {
        System.out.print(elem);
        System.out.print(' ');
      }
      System.out.println();
    }
  }

  public static void buyTicket(char[][] array, int rows, int seats, double[] stats) {
    Scanner scanner = new Scanner(System. in );

    boolean isTrue = true;

    while (isTrue) {
      System.out.println("Enter a row number:");
      int row = scanner.nextInt();

      System.out.println("Enter a seat number in that row:");
      int seat = scanner.nextInt();

      if (row < 1 || row > array.length - 1 || seat < 1 || seat > array.length - 1) {
        System.out.println("Wrong input!");
        continue;
      } else if (array[row][seat] == 'B') {
        System.out.println("That ticket has already been purchased!");
        continue;
      } else {
        int rowHalf = rows / 2;
        int totalSeats = (rows - 1) * (seats - 1);

        if (totalSeats < 60) {
          System.out.println("Ticket price: $10");
          stats[0] = stats[0] + 1;
          stats[2] += 10;
        } else if (row < rowHalf) {
          System.out.println("Ticket price: $10");
          stats[0] = stats[0] + 1;
          stats[2] += 10;
        } else {
          System.out.println("Ticket price: $8");
          stats[0] = stats[0] + 1;
          stats[2] += 8;
        }

        array[row][seat] = 'B';
        stats[1] = (100 * stats[0]) / totalSeats;

        isTrue = false;
      }
    }
  }

  public static void statistics(double[] stats) {
    System.out.println("Number of purchased tickets: " + (int) stats[0]);
    System.out.printf("Percentage: %.2f%% \n", stats[1]);
    System.out.println("Current income: $" + (int) stats[2]);
    System.out.println("Total income: $" + (int) stats[3]);
  }
}
