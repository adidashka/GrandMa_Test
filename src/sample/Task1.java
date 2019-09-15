package sample;

import java.util.Random;

public class Task1 {
    private int mass [][];
    private int n;

    private int a;

    public Task1(int n) {
        this.n = n;
        mass = new int[n][n];
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++){
                mass [i][j] = r.nextInt(10);
            }
        }
    }

    public void rotation90 () {

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n-i-1; j++){
                a = mass[i][j];
                mass[i][j] = mass[n - j - 1][i];
                mass[n - j - 1][i] = mass[n - i - 1][n - j - 1];
                mass[n - i - 1][n - j - 1] = mass[j][n - i - 1];
                mass[j][n - i - 1] = a;
            }
        }
    }

    public String printArrayToString() {
        String s = "";
        for (int i = 0; i < mass.length; i++){
            s = s + "\n";
            for (int j=0; j < mass.length; j++){
                s = s + mass[i][j] + " ";
            }
        }
        s = s + "\n";
        return s;
    }
}
