package sample;

import java.util.Random;

public class Task2 {

    private int imeageArray [][];
    private int frame_width, frame_height;
    private int pic_width, pic_height;
    private int pic_x, pic_y;

    public Task2(int frame_width, int frame_height, int pic_width, int pic_height, int pic_x, int pic_y) {

        this.frame_width = frame_width;
        this.frame_height = frame_height;
        this.pic_width = pic_width;
        this.pic_height = pic_height;
        this.pic_x = pic_x;
        this.pic_y = pic_y;

        imeageArray = new int[frame_height][frame_width];
        Random r = new Random();
        for (int i = 0; i < frame_height; i++) {
            for (int j = 0; j < frame_width; j++){
                imeageArray [i][j] = r.nextInt(10);
            }
        }
    }

    public void imageShift(){
        for (int i = 0; i < pic_height; i++){
            for (int j = 0; j < pic_width; j++){
                imeageArray [i][j] = imeageArray[i+pic_y][j+pic_x];
            }
        }
    }

    public String printArrayToString() {
        String s = "";
        for (int i = 0; i < frame_height; i++){
            s = s + "\n";
            for (int j=0; j < frame_width; j++){
                s = s + imeageArray[i][j] + " ";
            }
        }
        s = s + "\n";
        return s;
    }
}

