import java.util.Scanner;

public class Cinema {
    final static Scanner scanner = new Scanner(System.in);

    private static void printArray(String[][] array, int rowsNum, int seatsNum){
        System.out.println("Cinema:");
        for (int i = 0; i <=rowsNum; i++) {
            for (int j = 0; j <=seatsNum; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int rowsNum = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seatsNum = scanner.nextInt();

        int ticketNum = 0;
        int currentIncome = 0;

        int totalSeats = rowsNum * seatsNum;
        int totalIncome;
        if (totalSeats <= 60){
            totalIncome = totalSeats * 10;
        }
        else{
            int frontHalf = rowsNum/2;
            totalIncome = (frontHalf * seatsNum * 10) + ((rowsNum-frontHalf) * seatsNum *8);
        }

        String [][] seat = arrangement(rowsNum, seatsNum);
        while (true){
            System.out.println("1. Show the seats\n2. Buy a ticket\n3. Statistics\n0. Exit");
            int option = scanner.nextInt();
            if (option == 0){
                break;
            }
            else if (option == 1){
                printArray(seat, rowsNum, seatsNum);
            }
            else if (option == 2){
                currentIncome += ticketReservation(rowsNum, seatsNum, totalSeats, seat);
                ticketNum += 1;
            }
            else if (option == 3){
                statistics(ticketNum, totalSeats, currentIncome, totalIncome);
            }
            else{
                System.out.println("Invalid Option");
            }
        }
    }

    static String[][] arrangement(int rowsNum, int seatsNum){
        String[][] seat = new String[rowsNum+1][seatsNum+1];
        seat[0][0] = " ";
        for (int i = 1; i <=seatsNum; i++){
            seat[0][i] = String.valueOf(i);
        }
        for (int i = 1; i <=rowsNum; i++) {
            seat[i][0] = String.valueOf(i);
        }
        for (int i = 1; i <=rowsNum; i++){
            for (int j = 1; j <= seatsNum; j++){
                seat[i][j] = "S";
            }
        }
        return seat;
    }

    static int ticketReservation(int rowsNum, int seatsNum, int totalSeats, String[][] seat){
        int ticketPrice;
        int row, col;
        while (true) {
            System.out.println("Enter a row number:");
            row = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            col = scanner.nextInt();

            if (row <= rowsNum && col <= seatsNum) {
                if (seat[row][col].equals("S")) {
                    if (totalSeats <= 60) {
                        ticketPrice = 10;
                        break;
                    } else {
                        int frontHalf = rowsNum / 2;
                        if (row <= frontHalf) {
                            ticketPrice = 10;
                            break;
                        } else {
                            ticketPrice = 8;
                            break;
                        }
                    }
                } else if (seat[row][col].equals("B")) {
                    System.out.println("That ticket has already been purchased!");
                }
            }
            else{
                System.out.println("Wrong input!");
            }
        }

        System.out.println("Ticket price: $" + ticketPrice + "\n");
        seat[row][col] = "B";
        return ticketPrice;
    }

    static void statistics(int ticketNum, int totalSeats, int currentIncome, int totalIncome){
        double percent = (float) ((Float.intBitsToFloat(ticketNum)*100)/Float.intBitsToFloat(totalSeats));
        System.out.println("Number of purchased tickets: " + ticketNum);
        System.out.printf("Percentage: %.2f%%\n", percent);
        System.out.println("Current Income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }
}