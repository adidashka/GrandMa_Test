package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Task4 {

    public int countTest;
    public Test mTests[];


    public Task4() throws IOException {

        Scanner scanner = new Scanner ( new File( "D-small-practice.in" ) );

        countTest = scanner.nextInt();

        mTests = new Test[countTest];
        for (int i = 0; i < countTest; i++) {
            int countKey = scanner.nextInt();;
            int countChest = scanner.nextInt();
            ArrayList<Integer> listStartKeys = new ArrayList<>();
            for (int j = 0; j < countKey; j++) {
                listStartKeys.add(scanner.nextInt());
            }
            ArrayList<Chest> listChests = new ArrayList<>();
            for (int j = 0; j < countChest; j++) {

                int chestNumber = j + 1;
                int keyTypeToOpenChest = scanner.nextInt();
                int countKeysInside = scanner.nextInt();
                ArrayList<Integer> listKeyTypesInside = new ArrayList<>();
                for (int k = 0; k < countKeysInside; k++) {
                    listKeyTypesInside.add(scanner.nextInt());
                }
                listChests.add(new Chest(chestNumber, keyTypeToOpenChest, countKeysInside, listKeyTypesInside));
            }
            mTests[i] = new Test(countKey, countChest, listStartKeys, listChests);
        }
    }

    public void calculateTests() {
        String s = "";
        for (int i = 0; i < countTest; i++) {

            ArrayList<Integer> answer = null;
            if (isSolution(mTests[i])){
                answer = openChests(new ArrayList<Integer>(), mTests[i].getListChests(), mTests[i].getListStartKeys());
            }

            if ( answer == null ) {
                s = s +  "Case #" + (i+1) +": IMPOSSIBLE\n";
            } else {
                s = s + "Case #" + (i+1) +": ";
                for ( int j : answer ) {
                    s = s + j + " ";
                }
                s = s + "\n";
            }
        }
        toFile(s);
    }

    private void toFile (String string) {
        try(FileWriter writer = new FileWriter("Result.txt", false)) {
            writer.write(string);
            writer.flush();
        }
        catch(IOException ex){
        }
    }

    private ArrayList<Integer> openChests(ArrayList<Integer> openedChests, ArrayList<Chest> chests, ArrayList<Integer> keysThatWeHave) {

        if (openedChests.size() == chests.size())
            return openedChests;

        if (keysThatWeHave.size()==0)
            return null;

        if (!canViable(chests, openedChests, keysThatWeHave)) {
            return null;
        }

        for (Chest chest : chests) {
            boolean isOpen = openedChests.contains(chest.getChestNumber());
            boolean canOpen = keysThatWeHave.contains(chest.getKeyTypeToOpenChest());
            if (!isOpen &&canOpen){

                ArrayList<Integer> newOpenChests = new ArrayList<>(openedChests);
                newOpenChests.add(chest.getChestNumber());

                ArrayList<Integer> newKeysThatWeHave = new ArrayList<>(keysThatWeHave);
                newKeysThatWeHave.remove ( Integer.valueOf ( chest.getKeyTypeToOpenChest() ) );

                newKeysThatWeHave.addAll ( chest.getListKeyTypesInside() );

                ArrayList<Integer> result = openChests(newOpenChests, chests, newKeysThatWeHave);
                    if ( result != null ) return result;
            }
        }
        return null;
    }

    private boolean isSolution (Test test){
        ArrayList<Integer> allKeysThatWeHave = new ArrayList<>(test.getListStartKeys());
        ArrayList<Integer> allKeysThatWeNeed = new ArrayList<>();

        for (Chest chest : test.getListChests()) {
            allKeysThatWeNeed.add(chest.getKeyTypeToOpenChest());
            if (chest.getCountKeysInside()>0){
                allKeysThatWeHave.addAll(chest.getListKeyTypesInside());
            }
        }

        if (allKeysThatWeNeed.size()>allKeysThatWeHave.size())
            return false;
        else {
            Collections.sort(allKeysThatWeHave);
            Collections.sort(allKeysThatWeNeed);
            if (allKeysThatWeNeed.size()==allKeysThatWeHave.size()){
                for (int i = 0; i < allKeysThatWeHave.size(); i++){
                    if (!(allKeysThatWeNeed.get(i).equals(allKeysThatWeHave.get(i))))
                        return false;
                }
            }
            else {
                Set<Integer> uniqueKeys = new HashSet<Integer>(allKeysThatWeNeed);
                for (Integer uniqueKey : uniqueKeys){
                    int countKeyWeHave = Collections.frequency(allKeysThatWeHave, uniqueKey);
                    int countKeyWeNeed = Collections.frequency(allKeysThatWeNeed, uniqueKey);
                    if (countKeyWeHave<countKeyWeNeed)
                        return false;
                }
            }
        }

        return true;

    }

    private static boolean canViable (ArrayList<Chest> chests, ArrayList<Integer> openedChests, ArrayList<Integer> keysThatWeHave) {
        ArrayList<Integer> mKeys = new ArrayList<>(keysThatWeHave);
        ArrayList<Integer> mChests = new ArrayList<>(openedChests);
        boolean added;
        do {
            added = false;
            for (Chest chest : chests) {
                if (!mChests.contains(chest.getChestNumber()) && mKeys.contains(chest.getKeyTypeToOpenChest())) {
                    mChests.add(chest.getChestNumber());
                    mKeys.addAll(chest.getListKeyTypesInside());
                    added = true;
                }
            }
        } while (added);
        return mChests.size() == chests.size();
    }

    public static class Test {

        private int countKey;
        private int countChest;
        private ArrayList<Integer> listStartKeys;
        private ArrayList<Chest> listChests;

        public Test(int countKey, int countChest, ArrayList<Integer> listStartKeys, ArrayList<Chest> listChests) {
            this.countKey = countKey;
            this.countChest = countChest;
            this.listStartKeys = listStartKeys;
            this.listChests = listChests;
        }

        public int getCountKey() {
            return countKey;
        }

        public int getCountChest() {
            return countChest;
        }

        public ArrayList<Integer> getListStartKeys() {
            return listStartKeys;
        }

        public ArrayList<Chest> getListChests() {
            return listChests;
        }

    }

    public static class Chest {

        private int chestNumber;
        private int keyTypeToOpenChest;
        private int countKeysInside;
        private ArrayList<Integer> listKeyTypesInside;

        public Chest(int chestNumber, int keyTypeToOpenChest, int countKeysInside, ArrayList<Integer> listKeyTypesInside) {
            this.chestNumber = chestNumber;
            this.keyTypeToOpenChest = keyTypeToOpenChest;
            this.countKeysInside = countKeysInside;
            this.listKeyTypesInside = listKeyTypesInside;
        }

        public int getChestNumber() {
            return chestNumber;
        }

        public int getKeyTypeToOpenChest() {
            return keyTypeToOpenChest;
        }

        public int getCountKeysInside() {
            return countKeysInside;
        }

        public ArrayList<Integer> getListKeyTypesInside() {
            return listKeyTypesInside;
        }
    }
}



