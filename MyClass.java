import java.util.Scanner;

public class MyClass {
    public enum Status {
        choosingAction, choosingCoffeeType, fillingResources, displayingRemaining, takingEarnings, exit
    }
    public enum CoffeeType {
        espresso, latte, cappuccino, nothing
    }

    public static Status readAction(Status currentStatus, Scanner scanner) {
        String action = scanner.next();
        if (action.equals("exit")) {
            currentStatus = Status.exit;
        } else if (action.equals("remaining")) {
            currentStatus = Status.displayingRemaining;
        } else if (action.equals("buy")) {
            currentStatus = Status.choosingCoffeeType;
        } else if (action.equals("take")) {
            currentStatus = Status.takingEarnings;
        } else if (action.equals("fill")) {
            currentStatus = Status.fillingResources;
        } else {
            System.out.println("Invalid input.");
        }
        return currentStatus;
    }

    public static CoffeeType readCoffeeOption(CoffeeType chosenCoffeeType, Scanner scanner) {
        String option = scanner.next();
        if (option.equals("1")) {
            chosenCoffeeType = CoffeeType.espresso;
        } else if (option.equals("2")) {
            chosenCoffeeType = CoffeeType.latte;
        } else if (option.equals("3")) {
            chosenCoffeeType = CoffeeType.cappuccino;
        } else if (option.equals("back")) {
            chosenCoffeeType = CoffeeType.nothing;
        } else {
            chosenCoffeeType = CoffeeType.nothing;
            System.out.println("Invalid selection.");
        }
        return chosenCoffeeType;
    }

    public static void displayStatus(int amountWater, int amountMilk, int amountCoffeeBeans, int numCups, int money) {
        System.out.println("The coffee machine has:");
        System.out.println(amountWater + " of water");
        System.out.println(amountMilk + " of milk");
        System.out.println(amountCoffeeBeans + " of coffee beans");
        System.out.println(numCups + " of disposable cups");
        System.out.println(money + " of money");
    }
    public static boolean checkWater(int needed, int amountWater) {
        // Check whether there is enough water
        if (needed <= amountWater) {
            return true;
        } else {
            System.out.println("Sorry, not enough water!");
            return false;
        }
    }

    public static boolean checkMilk(int needed, int amountMilk) {
        // Check whether there is enough milk
        if (needed <= amountMilk) {
            return true;
        } else {
            System.out.println("Sorry, not enough milk!");
            return false;
        }
    }

    public static boolean checkCoffeeBeans(int needed, int amountCoffeeBeans) {
        // Check whether there are enough coffee beans
        if (needed <= amountCoffeeBeans) {
            return true;
        } else {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }
    }

    public static boolean checkCups(int numCups) {
        // Check whether there are enough coffee beans
        if (numCups > 0) {
            return true;
        } else {
            System.out.println("Sorry, not enough cups!");
            return false;
        }
    }

    public static void makeCoffee() {
        System.out.println("I have enough resources, making you a coffee!");
    }

    public static void main(String[] args) {

        char[] array = new char[0];
        Scanner scanner = new Scanner(System.in);
        Status currentStatus = Status.choosingAction;
        CoffeeType chosenCoffeeType = CoffeeType.nothing;
        // Initial resources
        int amountWater = 400;
        int amountMilk = 540;
        int amountCoffeeBeans = 120;
        int numCups = 9;
        int money = 550;
        boolean enoughResource;


        while (true) {
            if (currentStatus == Status.choosingAction) {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                currentStatus = readAction(currentStatus, scanner);
            } else if (currentStatus == Status.exit) {
                break;
            } else if (currentStatus == Status.displayingRemaining) {
                displayStatus(amountWater, amountMilk, amountCoffeeBeans, numCups, money);
                currentStatus = Status.choosingAction;
            } else if (currentStatus == Status.choosingCoffeeType) {
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                chosenCoffeeType = readCoffeeOption(chosenCoffeeType, scanner);
                switch(chosenCoffeeType) {
                    case espresso:
                        enoughResource = checkWater(250, amountWater) && checkCoffeeBeans(16, amountCoffeeBeans) && checkCups(numCups);
                        if (enoughResource) {
                            makeCoffee();
                            amountWater -= 250;
                            amountCoffeeBeans -= 16;
                            numCups -= 1;
                            money += 4;
                        }
                        currentStatus = Status.choosingAction;
                        break;
                    case latte:
                        enoughResource = checkWater(350, amountWater) && checkMilk(75, amountMilk) && checkCoffeeBeans(20, amountCoffeeBeans) && checkCups(numCups);
                        if (enoughResource) {
                            makeCoffee();
                            amountWater -= 350;
                            amountMilk -= 75;
                            amountCoffeeBeans -= 20;
                            numCups -= 1;
                            money += 7;
                        }
                        currentStatus = Status.choosingAction;
                        break;
                    case cappuccino:
                        enoughResource = checkWater(200, amountWater) && checkMilk(100, amountMilk) && checkCoffeeBeans(12, amountCoffeeBeans) && checkCups(numCups);
                        if (enoughResource) {
                            makeCoffee();
                            amountWater -= 200;
                            amountMilk -= 100;
                            amountCoffeeBeans -= 12;
                            numCups -= 1;
                            money += 6;
                        }
                        currentStatus = Status.choosingAction;
                        break;
                    case nothing:
                        currentStatus = Status.choosingAction;
                        break;
                    default:
                        break;
                }
            } else if (currentStatus == Status.fillingResources) {
                System.out.println("Write how many ml of water do you want to add:");
                amountWater += scanner.nextInt();
                System.out.println("Write how many ml of milk do you want to add:");
                amountMilk += scanner.nextInt();
                System.out.println("Write how many grams of coffee beans do you want to add:");
                amountCoffeeBeans += scanner.nextInt();
                System.out.println("Write how many disposable cups of coffee do you want to add:");
                numCups += scanner.nextInt();
                currentStatus = Status.choosingAction;
            } else if (currentStatus == Status.takingEarnings) {
                System.out.println("I gave you $" + money);
                money = 0;
                currentStatus = Status.choosingAction;
            } else {
                continue;
            }
        }
    }
}
