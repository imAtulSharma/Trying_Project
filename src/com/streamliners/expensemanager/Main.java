package com.streamliners.expensemanager;

public class Main {
    /**
     * Number of digits to be printed with blank spaces
     */
    static int noOfDigits;

    /**
     * Represent color to be used in purpose of console printing
     */
    public static class ConsoleColors {
        // Reset
        public static final String RESET = "\033[0m";  // Text Reset

        // Regular Colors
        public static final String BLACK = "\033[0;30m";   // BLACK
        public static final String RED = "\033[0;31m";     // RED
        public static final String GREEN = "\033[0;32m";   // GREEN
        public static final String YELLOW = "\033[0;33m";  // YELLOW
        public static final String BLUE = "\033[0;34m";    // BLUE
        public static final String PURPLE = "\033[0;35m";  // PURPLE
        public static final String CYAN = "\033[0;36m";    // CYAN
        public static final String WHITE = "\033[0;37m";   // WHITE

        // Bold
        public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
        public static final String RED_BOLD = "\033[1;31m";    // RED
        public static final String GREEN_BOLD = "\033[1;32m";  // GREEN
        public static final String YELLOW_BOLD = "\033[1;33m"; // YELLOW
        public static final String BLUE_BOLD = "\033[1;34m";   // BLUE
        public static final String PURPLE_BOLD = "\033[1;35m"; // PURPLE
        public static final String CYAN_BOLD = "\033[1;36m";   // CYAN
        public static final String WHITE_BOLD = "\033[1;37m";  // WHITE

        // Underline
        public static final String BLACK_UNDERLINED = "\033[4;30m";  // BLACK
        public static final String RED_UNDERLINED = "\033[4;31m";    // RED
        public static final String GREEN_UNDERLINED = "\033[4;32m";  // GREEN
        public static final String YELLOW_UNDERLINED = "\033[4;33m"; // YELLOW
        public static final String BLUE_UNDERLINED = "\033[4;34m";   // BLUE
        public static final String PURPLE_UNDERLINED = "\033[4;35m"; // PURPLE
        public static final String CYAN_UNDERLINED = "\033[4;36m";   // CYAN
        public static final String WHITE_UNDERLINED = "\033[4;37m";  // WHITE

        // Background
        public static final String BLACK_BACKGROUND = "\033[40m";  // BLACK
        public static final String RED_BACKGROUND = "\033[41m";    // RED
        public static final String GREEN_BACKGROUND = "\033[42m";  // GREEN
        public static final String YELLOW_BACKGROUND = "\033[43m"; // YELLOW
        public static final String BLUE_BACKGROUND = "\033[44m";   // BLUE
        public static final String PURPLE_BACKGROUND = "\033[45m"; // PURPLE
        public static final String CYAN_BACKGROUND = "\033[46m";   // CYAN
        public static final String WHITE_BACKGROUND = "\033[47m";  // WHITE

        // High Intensity
        public static final String BLACK_BRIGHT = "\033[0;90m";  // BLACK
        public static final String RED_BRIGHT = "\033[0;91m";    // RED
        public static final String GREEN_BRIGHT = "\033[0;92m";  // GREEN
        public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
        public static final String BLUE_BRIGHT = "\033[0;94m";   // BLUE
        public static final String PURPLE_BRIGHT = "\033[0;95m"; // PURPLE
        public static final String CYAN_BRIGHT = "\033[0;96m";   // CYAN
        public static final String WHITE_BRIGHT = "\033[0;97m";  // WHITE

        // Bold High Intensity
        public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // BLACK
        public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // RED
        public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // GREEN
        public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// YELLOW
        public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // BLUE
        public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// PURPLE
        public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // CYAN
        public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // WHITE

        // High Intensity backgrounds
        public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// BLACK
        public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// RED
        public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// GREEN
        public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// YELLOW
        public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// BLUE
        public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // PURPLE
        public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // CYAN
        public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // WHITE
    }

    public static void main(String[] args) {
        int[] maxPercentage = {10, 20, 30, 20, 20};

        int[] miniWallet = {3, 20, 550, 8, 100};
        int totalAmount = 60;

        noOfDigits = getNumberOfDigits(findMax(miniWallet));

        if(!calculateAmount(totalAmount, 0, miniWallet, maxPercentage)) {
            System.out.print(ConsoleColors.YELLOW + "\n\nError! Not sufficient money");
            return;
        }

        System.out.println("\n" + ConsoleColors.GREEN);
        for (int i = 0; i < 5; i++) {
                System.out.print(String.format("%" + noOfDigits +"d ", miniWallet[i]));
        }
        System.out.print("\tTotal Amount = " + totalAmount);
    }

    /**
     * Calculate and deduct the amount from wallet as required
     * @param totalAmount total amount to be deducted
     * @param miniWallet wallet from which the amount should be deducted
     * @param maxPercentage maximum percentage to be deduct from the first round only
     * @return true if sufficient amount deducted otherwise false
     */
    private static boolean calculateAmount(int totalAmount, int stationeryPosition, int[] miniWallet, int[] maxPercentage) {
        printArray(miniWallet, -1, ConsoleColors.GREEN);
        System.out.print("\tTotal Amount = " + totalAmount);

        int actualAmount = totalAmount;

        // Deducting amounts only using their percentages
        for (int i = 0; totalAmount > 0 && i < 5; i++) {
            if (miniWallet[i] == 0) continue;

            int payableAmount = actualAmount * maxPercentage[i] / 100;

            if (payableAmount < miniWallet[i]) {
                miniWallet[i] -= payableAmount;
                totalAmount -= payableAmount;
            } else {
                totalAmount -= miniWallet[i];
                miniWallet[i] -= miniWallet[i];
            }
            printArray(miniWallet, i, ConsoleColors.RED);
            System.out.print("\tTotal Amount = " + totalAmount);
        }

        // Deducting the remaining amount from wallet
        for (int i = 0; totalAmount > 0 && i < 5; i++) {
            // This mini wallet is the stationery wallet we only can use specified percentage and not more than that
            if (i == stationeryPosition) {
                printArray(miniWallet, i, ConsoleColors.YELLOW);
                System.out.print("\tTotal Amount = " + totalAmount);
                continue;
            }
            if (totalAmount < miniWallet[i]) {
                miniWallet[i] -= totalAmount;
                totalAmount -= totalAmount;
            } else {
                totalAmount -= miniWallet[i];
                miniWallet[i] -= miniWallet[i];
            }
            printArray(miniWallet, i, ConsoleColors.RED);
            System.out.print("\tTotal Amount = " + totalAmount);
        }

        return totalAmount == 0;
    }

    /**
     * To print array
     * @param miniWallet array of the mini wallet
     * @param deductionPlace place where the amount is deducted
     * @param color color to be used at that place
     */
    private static void printArray(int[] miniWallet, int deductionPlace, String color) {
        System.out.println();
        for (int i = 0; i < 5; i++) {
            if (i == deductionPlace) System.out.print(color);
            System.out.print(String.format("%" + noOfDigits +"d ", miniWallet[i]) + ConsoleColors.RESET);
        }
    }

    /**
     * Get max number out of the array
     * @param array array to be searched in
     * @return maximum number
     */
    private static int findMax(int[] array) {
        int max = array[0];
        for (int x : array) {
            if (max < x) max = x;
        }
        return max;
    }

    /**
     * to getNumber of digits in a integer
     * @param number number to be find the digits in
     * @return number of digits in a integer
     */
    private static int getNumberOfDigits(int number) {
        int count = 0;
        while (number > 0) {
            number /= 10;
            count++;
        }
        return count;
    }
}
