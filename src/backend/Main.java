package backend;


public class Main {
    public static void main(String[] args) throws Exception {
        Model model = new Model();
        Model.initConnection();

        String[][] str = Model.sortFlightsBy("afds");
        for(int i = 0; i < str.length; i++){
            for (int j = 0; j < str[0].length; j++){
                System.out.print(str[i][j] + " ");
            }
            System.out.println();
        }
    }
}