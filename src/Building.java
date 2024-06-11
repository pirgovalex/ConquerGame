public  class Building {
    static String name;
    static int row;
    static int col;
    static int constructionTime;
    static  int constructionCost;
    static int  approval;
    static int disapproval;

    public Building(String name, int row, int col, int constructionTime, int constructionCost, int approval, int disapproval) {
        this.name = name;
        this.row = row;
        this.col = col;
        this.constructionTime = constructionTime;
        this.constructionCost = constructionCost;
        this.approval = approval;
        this.disapproval = disapproval;
    }

    public static char symbol() {
        switch (name) {
            case "Laboratory": return 'L';
            case "Port": return 'P';
            case "University": return 'U';
            case "Barracks": return 'B';
            case "Tax Office": return 'T';
            case"Church" : return 'C';
            case "Media" : return 'M';
            case "Theatre" : return 'H';

            // Добави символи за други сгради тук
            default: return '?';
        }
    }
}