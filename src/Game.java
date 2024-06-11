import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.*;

public class Game {
    public static List<Building> completedBuildings = new ArrayList<>();
    static boolean isLab = false;
    static boolean isPort = false;
    static boolean isUni = false;
    static boolean isBar = false;
    static boolean isTax = false;

    static List<Building> buildingsInConstruction = new ArrayList<>();

    static char[][] map = {
            {'$', '-','-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-','-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-','-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-','-', '-', '-', '@', 'V', 'V', 'V', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-','-', '-', '-', 'V', 'V', 'V', 'V', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-','-', '-', '-', '@', 'V', 'V', '@', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-','-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
            {'-', '-','-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-'},
            {'$', '-','-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '$'}
    };

     //Намери нначалната позиция бро
    static boolean isExpanding=false;
    static int budget = 500000; // Начален бюджет :)
    static int turn = 0; // Текущ ход
    static boolean gameOver = false;

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        // Отпечатване на първоначалната карта
        printMap();

        // Игрален цикъл
        while (!gameOver) {
            if(isExpanding==false&&budget>0){
                //System.out.println("Approval: "+ Building.approval);
                for(Building building: completedBuildings ){
                    System.out.println(building.name);
                }
                for(Building building: buildingsInConstruction ){
                    System.out.println(building.name);
                }

                System.out.println("Ход: " + (++turn));
                System.out.println("Меню:");
                System.out.println("0. Експанзия!");
                System.out.println("1. Построяване на лаборатория");
                System.out.println("2. Построяване на пристанище");
                System.out.println("3. Построяване на университет");
                System.out.println("4. Построяване на казарма");
                System.out.println("5. Построяване на данъчни");
                System.out.println("6. Построяване на театър");
                System.out.println("7. Построяване на медии");
                System.out.println("8. Построяване на църква");
                System.out.println("9. НАМЕРИ ПЪТ КЪМ БОГАТСТВО!");
                System.out.println("16. Покажи карта");
                System.out.println("17. Покажи рейтинги");
                System.out.println("18. Завършване на хода");
                if (isBar==true){
                    System.out.println("11-Извикай силите на злото!(Отвличане и крадене!)");
                }
                if (isPort==true){
                    System.out.println("12-ECONOMICS");

                }
                if (isTax==true){
                    System.out.println("13 -ВЗЕМИ КРЕДИТ!!");
                }
                if (isUni==true){
                    System.out.println("14 - НАУЧЕН ПРОГРЕС" );
                }
                if (isLab==true){
                    System.out.println("15 - EVIL PLAN");
                }

                System.out.print("Изберете опция: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 0:
                        Expansion(map);
                        break;

                    case 1:
                        buildBuilding("Laboratory");
                        break;
                    case 2:
                        buildBuilding("Port");
                        break;
                    case 3:
                        buildBuilding("University");
                        break;
                    case 4:
                        buildBuilding("Barracks");
                        break;
                    case 5:
                        buildBuilding("Tax Office");
                        break;
                    case 6:
                        buildBuilding("Theatre");
                        break;
                    case 7:
                        buildBuilding("Media");
                        break;
                    case 8:
                        buildBuilding("Church");
                        break;
                    case 9:
                        System.out.println(TradeCorridorBuilder.main(map));
                        break;


                    case 16:
                        printMap();
                        break;
                    case 17:
                        showRating();
                        break;

                    case 18:
                        nextTurn();
                        break;
                    case 11:
                        Barracks();
                        break;
                    case 12:
                        Port();
                        break;
                    case 13:
                        taxOffice();
                        break;
                    case 14:
                        University();
                        break;
                    case 15:
                        Laboratory();
                        break;


                    default:
                        System.out.println("Невалиден избор. Моля, опитайте отново.");
                        break;
                }
            }
            else if(budget<=0){
                System.out.println("You're in debt!! GAME OVER");
                gameOver=true;
            }
        }
    }
    public static void showRating() {

            System.out.println("Approval: " + Building.approval);
            System.out.println("Disapproval: " + Building.disapproval);
            System.out.println("Бюджет: "+budget);
    }

    public static char[][] Expansion(char[][] array) {
        isExpanding = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Къде ще се случи експанзията?");
        System.out.println("1. Север?");
        System.out.println("2. Юг?");
        System.out.println("3. Изток?");
        System.out.println("4. Запад?");
        int input = scanner.nextInt();

        switch (input) {
            case 1:

                expandNorth(array);
                break;
            case 2:

                expandSouth(array);
                break;
            case 3:

                expandEast(array);
                break;
            case 4:

                expandWest(array);
                break;
            default:
                System.out.println("Невалиден избор!");
                break;
        }

        isExpanding = false;
        return array;
    }

    // Функция за експанзия на север
    private static void expandNorth(char[][] array) {
        int newVCount = 0;
        budget-=10000;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == '@'&&newVCount<4||array[i][j] == 'V' && array[i - 1][j] == '-'&&newVCount<4) {
                    array[i - 1][j] = 'V';newVCount++;
                }
            }
        }
        isExpanding=false;
    }

    // Функция за експанзия на юг
    private static void expandSouth(char[][] array) {
        budget-=10000;
        int newVCount = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == '@'&&newVCount<4||array[i][j] == 'V' && array[i + 1][j] == '-'&&newVCount<4) {
                    array[i + 1][j] = 'V';
                    newVCount++;
                }
            }
        }
        isExpanding=false;
    }


    // Функция за експанзия на изток
    private static void expandEast(char[][] array) {
        int newVCount=0;
        budget-=50000;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length - 1; j++) {
                if (array[i][j]== '@'&&newVCount<3||array[i][j] == 'V' && array[i][j + 1] == '-') {
                    array[i][j + 1] = 'V';
                    j++;
                }
            }
        }
    }



    // Функция за експанзия на запад
    private static void expandWest(char[][] array) {
        int newVCount = 0;
        budget-=50000;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length-1; j++) {
                if (array[i][j]== '@'&&newVCount<3||array[i][j] == 'V' && array[i][j - 1] == '-'&&newVCount<3) {
                    array[i][j - 1] = 'V';newVCount++;
                }
            }
        }isExpanding=false;
    }

    public static void University(){

        Scanner scanner = new Scanner(System.in);

        // Отпечатване на наличните подобрения за университета
        System.out.println("Възможни подобрения за университета:");
        System.out.println("1. Двигател тайфун");
        System.out.println("2. Диагонална струя");
        System.out.print("Изберете подобрение за изучаване: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Проверка дали има достатъчно бюджет и ходове за изучаване на подобрението
                if (budget >= 2000 && turn >= 2) {
                    budget -= 2000; // Намаляване на бюджета
                    turn += 2; // Увеличаване на броя на ходовете

                    // Добавяне на ефектите от подобрението
                    System.out.println("Успешно изучихте подобрението: Двигател тайфун");
                    // Добавете съответните ефекти на подобрението тук
                } else {
                    System.out.println("Нямате достатъчно бюджет или ходове за изучаване на подобрението.");
                }
                break;
            case 2:
                // Проверка дали има достатъчно бюджет и ходове за изучаване на подобрението
                if (budget >= 3000 && turn >= 1) {
                    budget -= 3000; // Намаляване на бюджета
                    turn += 1; // Увеличаване на броя на ходовете


                    System.out.println("Успешно изучихте подобрението: Диагонална струя");

                } else {
                    System.out.println("Нямате достатъчно бюджет или ходове за изучаване на подобрението.");
                }
                break;
            default:
                System.out.println("Невалиден избор.");
                break;
        }
    }


    public static void Barracks(){
        int stealCounter =0;
        stealCounter++;
        System.out.println("OTKRADNAH NESHTO");
        if(stealCounter<6){ budget+=500;}
        else if(stealCounter%6==0){
            budget+=1500;
            Building.disapproval+=5;

        }

    }

    public static void Laboratory(){
        System.out.println("MYAHAHAHA");
    }

    public static void Port(){
        System.out.println("RAZVIH SE IKONOMICHESKI!!!");
    }
    public static void taxOffice(){
        int Credit=0;

        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("Kolko pari iskash?");
            Credit = scanner.nextInt();
            if (Credit<=0){
                System.out.println("Кредита не е негативен!!");
                Credit=0;

            }
        }
        while (Credit <= 0);
        budget+=Credit;
        System.out.println("Current balance = "+ budget);
    }

    // Метод за построяване на сграда
    public static void buildBuilding(String buildingName) {
        if(!isExpanding){
            Scanner scanner = new Scanner(System.in);

            System.out.print("Въведете позиция (row, col), където да се построи " + buildingName + ": ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            // Проверка дали позицията е валидна и свободна за построяване
            if (isValidPosition(row, col)) {
                map[row][col] = getBuildingSymbol(buildingName);
                System.out.println(buildingName + " е построена на позиция (" + row + ", " + col + ")");



            } else {
                System.out.println("Позиция (" + row + ", " + col + ") не е валидна или е заета.");
            }
        }
    }

    // Метод за проверка дали позицията е валидна и свободна за построяване
    public static boolean isValidPosition(int row, int col) {
        return row >= 0 && row < map.length && col >= 0 && col < map[0].length && map[row][col] == '-';
    }

    // Метод за връщане на символа на сградата по нейното име
    public static char getBuildingSymbol(String buildingName) {
        if (!isExpanding) {
            switch (buildingName) {
                case "Laboratory":
                    buildLaboratory(Building.row, Building.col);
                    isLab = true;
                    return 'L';
                case "Port":

                    buildPort(Building.row, Building.col);
                    isPort = true;
                    return 'P';
                case "University":

                    buildUniversity(Building.row, Building.col);
                    isUni = true;
                    return 'U';
                case "Barracks":

                    buildBarracks(Building.row, Building.col);
                    isBar = true;
                    return 'B';
                case "Tax Office":
                    buildTaxOffice(Building.row, Building.col);
                    isTax = true;
                    return 'T';
                case "Theatre":
                    buildTheatre(Building.row, Building.col);
                    return  'H';
                case "Media":
                    buildMedia(Building.row, Building.col);
                    return 'M';
                case "Church":
                    buildChurch(Building.row, Building.col);
                    return 'C';
                //  символи за други сгради тук
                default:
                    return '?';
            }
        }
        return '?';
    }





    //Метотд за отпечатване на картата
    public static void printMap() {
        for (char[] row : map) {
            for (char cell : row) {
                System.out.print(cell+" ");
            }
            System.out.println();
        }
    }
    public static void buildTheatre(int row, int col){
            buildingsInConstruction.add(new Building("Theatre", row, col, 2, 100, +5, 0));
            System.out.println("Започва строеж на лаборатория на позиция (" + row + ", " + col + ")");
            completedBuildings.add(new Building("Theatre", row, col, 2, 100, +5, 0));

            System.out.println("Позицията е заета!");

        }
        public static void buildMedia(int row, int col){

                buildingsInConstruction.add(new Building("Media", row, col, 2, 500, 5, 0));
                System.out.println("Започва строеж на лаборатория на позиция (" + row + ", " + col + ")");
                completedBuildings.add(new Building("Media", row, col, 2, 500, 5, 0));

                System.out.println("Позицията е заета!");

        }
        public static void buildChurch(int row, int col){


                buildingsInConstruction.add(new Building("Media", row, col, 2, 100, 5, 0));
                System.out.println("Започва строеж на лаборатория на позиция (" + row + ", " + col + ")");
                completedBuildings.add(new Building("Media", row, col, 2, 100, 5, 0));

        }
    // = метод за строеж на лаборатория
    public static void buildLaboratory(int row, int col) {


            buildingsInConstruction.add(new Building("Laboratory", row, col, 2, 1000, 0, 5));
            System.out.println("Започва строеж на лаборатория на позиция (" + row + ", " + col + ")");
            completedBuildings.add(new Building("Laboratory", row, col, 2, 1000, 0, 5));




    }

    //  метод за строеж на пристанище
    public static void buildPort(int row, int col) {

            buildingsInConstruction.add(new Building("Port", row, col, 2, 1000, 0, 5));
            System.out.println("Започва строеж на пристанище на позиция (" + row + ", " + col + ")");
            completedBuildings.add(new Building("Port", row, col, 2, 1000, 0, 5));


    }

    //метод за строеж на университет
    public static void buildUniversity(int row, int col) {
            buildingsInConstruction.add(new Building("University", row, col, 3, 2000, 10, 0));
            System.out.println("Започва строеж на университет на позиция (" + row + ", " + col + ")");
            completedBuildings.add(new Building("University", row, col, 3, 2000, 10, 0));

    }

    //  метод за строеж на казарма
    public static void buildBarracks(int row, int col) {

            buildingsInConstruction.add(new Building("Barracks", row, col, 3, 2000, 0, 1));
            System.out.println("Започва строеж на казарма на позиция (" + row + ", " + col + ")");
            completedBuildings.add(new Building("Barracks", row, col, 3, 2000, 0, 1));


    }

    //  метод за строеж на данъчни
    public static void buildTaxOffice(int row, int col) {

            buildingsInConstruction.add(new Building("Tax Office", row, col, 3, 2000, 0, 5));
            System.out.println("Започва строеж на данъчни на позиция (" + row + ", " + col + ")");
            completedBuildings.add(new Building("Tax Office", row, col, 3, 2000, 0, 5));


    }

    // Метод за преминаване към следващ ход
    // Метод за преминаване към следващ ход
    // Метод за преминаване към следващ ход
    public static void nextTurn() {
        turn++;
        System.out.println("Ход: " + turn);

        // Проверка на сградите в строеж
        Iterator<Building> iterator = buildingsInConstruction.iterator();
        while (iterator.hasNext()) {
            Building building = iterator.next();
            building.constructionTime--;
            if (building.constructionTime <= 0) {
                map[building.row][building.col] = building.symbol();
                completedBuildings.add(building);
                budget -= building.constructionCost;
                System.out.println(building.name + " е построена на позиция (" + building.row + ", " + building.col + ")");
                iterator.remove(); // Remove the completed building from the buildingsInConstruction list
            }
        }

        // Обновяване на икономиката и другите параметри
        updateEconomy();
    }



    // Метод за обновяване на икономиката
    public static void updateEconomy() {
        for (Building building : completedBuildings) {
            switch (building.name) {
                case "Laboratory":
                    budget += 500;
                    budget -= 1000;
                    Building.approval += 5;
                    Building.disapproval -= 0;
                    break;
                case "Port":
                    budget -= 1000;
                    Building.approval += 5;
                    Building.disapproval -= 0;
                    break;
                case "University":
                    budget += 500;
                    budget -= 1000;
                    Building.approval += 10;
                    Building.disapproval -= 0;
                    break;
                case "Barracks":
                    budget -= 1000;
                    Building.approval -= 0;
                    Building.disapproval += 1;
                    break;
                case "Tax Office":
                    budget += 1000;
                    budget -= 500;
                    Building.approval -= 0;
                    Building.disapproval += 5;
                    break;
                case "Theatre":
                    Building.approval+=5;
                    budget-=100;
                    break;
                case "Media":
                    Building.approval+=5;
                    budget-=500;
                    break;
                case "Church":
                    Building.approval+=5;
                    budget-=100;
                    break;
                // Други сгради и техните ефекти върху икономиката
            }
        }
    }

}
