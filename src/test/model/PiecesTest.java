package model;


import java.util.List;

public class PiecesTest {

    public boolean listOfMovesEquals(List<int[]> list1, List<int[]> list2){
        boolean equals = true;
        for (int[] list1Move : list1) {
            boolean tempEquals = false;
            for (int [] list2Move : list2) {
                if (list1Move[0] == list2Move[0] && list1Move[1] == list2Move[1]) {
                    tempEquals = true;
                    break;
                }
            }
            equals &= tempEquals;
        }
        for (int[] list2Move : list2) {
            boolean tempEquals = false;
            for (int [] list1Move : list1) {
                if (list1Move[0] == list2Move[0] && list1Move[1] == list2Move[1]){
                    tempEquals = true;
                }
            }
            equals &= tempEquals;
        }
        return equals;
    }
}
