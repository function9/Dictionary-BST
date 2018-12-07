import java.io.*;
import java.util.*; 

class DictionaryBST{
    private int size;
    NodeWord root;
    private String name; // name of the dictionary
    private String mainName;
    int step = 0;
    int maxsize = 0;

    DictionaryBST(){
        root = null;
    }


    /// loads dictionary into BST
    public void loadDictionary(String fName){
        name = fName + ".txt";
        mainName = fName;
        int index = 0;
        try {
            File file = new File(name);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Word ins = new Word(scanner.nextLine());
                ins.setDictName(fName);
                insert(ins);
                size++;
                if(ins.getWordLength() > maxsize){
                    maxsize = ins.getWordLength();
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //returns size of dictionary
    public int getSize(){
        return size;
    }
    //returns size of the greatest length word
    public int getMaxWordSize(){
        return maxsize;
    }
    //returns name of dictionary
    public String getName(){
        return mainName;
    }
    //prints out each value in BST
    public void displayInOrder(){
        inOrder(root);
    }
    //prints out info about BST
    public void info(){
        System.out.println("The size of the dictionary is " + getSize() + " using " + getMaxNbLevel() + " BST levels");
    }
    //shows tree depending on String input
    public void show(String word){
        NodeWord current = root;
        if(word.equals("word")){
            System.out.println("The tree using type (word) looks like:");
            inOrderWord(current);
        }
        else if (word.equals("size")){
            System.out.println("The tree using type (size) looks like:");
            inOrderIndex(current);
        }
        else if (word.equals("id")){
            System.out.println("The tree using type (id) looks like:");
            inOrderID(current);
        }


    }
    //helper method for Insert
    private void recInsert(NodeWord current, Word i){
        if(i.getWord().compareTo(current.data.getWord()) < 0){
            if(current.left == null) {
                current.left = new NodeWord(i);
                current.left.index = (current.index * 2) + 1;
            }
            else
                recInsert(current.left, i);
        }
        else if(current.right == null) {
                current.right = new NodeWord(i);
                current.right.index = (current.index * 2) + 2;
            }
        else
            recInsert(current.right, i);
        }
    //inserts word into tree
    public void insert(Word i){
        if (root == null) {
            root = new NodeWord(i);
            root.index = 0;
        }
        else
            recInsert(root, i);
        size++;
    }
    //helper method for display in order
    private void inOrder(NodeWord current){
        if(current != null){
            inOrder(current.left);
            System.out.println(current.data.getWord() + "; " + current.data.getWordLength() + "; " + current.data.getDictName());
            inOrder(current.right);
        }
    }
    //helper method for show
    private void inOrderWord(NodeWord current){
        if(current != null){
            int x = (int) Math.ceil(Math.log(current.index) / Math.log(2));
            inOrderWord(current.right);
            for(int i = 0; i < x; i++){
                System.out.print("\t");
            }
            System.out.println(current.data.getWord());
            inOrderWord(current.left);
        }
    }
    //helper method for show
    private void inOrderID(NodeWord current){
        if(current != null){
            int x = (int) Math.ceil(Math.log(current.index) / Math.log(2));
            inOrderID(current.right);
            for(int i = 0; i < x; i++){
                System.out.print("\t");
            }
            System.out.println(current.data.getDictName());
            inOrderID(current.left);
        }
    }
    //helper method for show
    private void inOrderIndex(NodeWord current){
        if(current != null){
            int x = (int) Math.ceil(Math.log(current.index) / Math.log(2));
            inOrderIndex(current.right);
            for(int i = 0; i < x; i++){
                System.out.print("\t");
            }
            System.out.println(current.index);
            inOrderIndex(current.left);
        }
    }
    //returns the max amount of levels in BST
    public int getMaxNbLevel(){
        if(root == null) return 0;
        int max = inOrderNB(root);
        return log(max) - 1;
    }
    //helper method for getMaxNBLevel
    private int log(long max) {
        if(max < 0) {
            return 0;
        }
        else if (max == 0){
            return 1;
        }
        return (int) Math.ceil(Math.log(max + 1) / Math.log(2)) + 1;
    }
    //helper method for getMaxNBLevel
    public int inOrderNB(NodeWord current){
        if(current.left == null) {
            if(current.right == null) {
                return current.index;
            }
            else {
                return inOrderNB(current.right);
            }
        }
        else if(current.right == null) {
            return inOrderNB(current.left);
        }
        else
            return Math.max(inOrderNB(current.left), inOrderNB(current.right));
    }
    //extracts BST into an array of Words
    public ArrayWord extractArrayInOrder(){
        System.out.println("\nArray extraction using in-order traversal");
        ArrayWord word = new ArrayWord();
        NodeWord current = root;
        inOrderArray(current, word);
        return word;
    }
    //helper method for extractArrayInOrder
    public void inOrderArray(NodeWord current, ArrayWord word){
        if(current!= null){
           inOrderArray(current.left, word);
           word.insert(current.data);
           inOrderArray(current.right, word);
        }
    }
    //returns the number of steps
    public int getStep(){
        return step;
    }
    //searches BST for given String
    public boolean search(String search){
        NodeWord x = root;
        while (x != null) {
            int val = search.compareTo(x.data.getWord());
            if (val < 0){
                x = x.left;
                step++;
            }
            else if (val > 0){
                x = x.right;
                step++;
            }
            else return true;
        }
        return false;
    }
    //shuffles ArrayWord and insert it into BST
    public void initDictionary(ArrayWord aery){
        aery.shuffle();
        while(aery.getSize() > 0) {
            insert(aery.deleteLast());
        }
    }
    //extracts subArray while keeping the order
    public ArrayWord[] extractSubArrayInOrder(){
        ArrayWord[] arrayWords = new ArrayWord[getMaxWordSize()];
        for (int i = 0; i < arrayWords.length; i++) {
            arrayWords[i] = new ArrayWord();
        }
        ArrayWord extract = extractArrayInOrder();
        int exSize = extract.getSize();
        for (int i = 0; i < exSize; i++) {
            Word last = extract.deleteLast();
            arrayWords[last.getWordLength() - 1].insert(last);
        }
        return arrayWords;
    }
    //Creates a BST if any anagrams are found
    public DictionaryBST createBSTAnagram(String anagram){
        char[] charArray = anagram.toCharArray();
        Arrays.sort(charArray);
        String sorted = new String(charArray);

        ArrayWord fuck = new ArrayWord();
        inOrderArray(root, fuck);
        ArrayWord matching = new ArrayWord();

        for (int i = 0; i < fuck.getSize(); i++) {
            if(sorted.length() == fuck.words[i].getWordLength()){
                char[] c = fuck.words[i].getWord().toCharArray();
                Arrays.sort(c);
                String sort = new String(c);
                if(sort.equals(sorted)){
                    matching.insert(fuck.words[i]);
                }
            }
        }
        DictionaryBST an = new DictionaryBST();
        an.initDictionary(matching);
        return an;
    }
    //checks the spelling of words in files
    public void spellCheckFile(String fileName){
        String cName = fileName;
        String[][] storage = new String[1000][1000];
        int xIndex = 0;
        try {
            File file = new File(cName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String line1 = line.replaceAll("\\p{Punct}|\\d", "");
                String[] wordsLine = line1.split("\\s+");
                storeWords(wordsLine, storage, xIndex);
                xIndex++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayWord[] printout = new ArrayWord[storage.length];
        for (int i = 0; i < xIndex; i++) {
            printout[i] = new ArrayWord();
            for (int j = 0; j < storage[i].length; j++) {
                printout[i].insert(searchCheck(storage[i][j]));
            }
        }
        int i = 0;
        while(printout[i] != null) {
            for (int j = 0; j < printout[i].getSize(); j++) {
                System.out.print(printout[i].words[j].getWord() + " ");
            }
            System.out.print("\n[");
            for (int j = 0; j < printout[i].getSize(); j++) {
                System.out.print(printout[i].words[j].getDictName() + "-");
            }
            System.out.println("]");
            i++;
        }

    }
    //helper method for spellCheckFile
    public void storeWords(String[] words, String[][] storage, int xIndex){
        storage[xIndex] = new String[words.length];
        for(int i = 0; i < words.length; i++){
            storage[xIndex][i] = words[i];
        }
        xIndex++;
    }
    //helper method for spellCheck File
    public Word searchCheck(String search){
        NodeWord x = root;
        NodeWord y = root;
        while (x != null) {
            int val = search.compareTo(x.data.getWord());
            if (val < 0){
                x = x.left;
            }
            else if (val > 0){
                x = x.right;
            }
            else return x.data;
        }
        while(y != null){
            int val = search.toLowerCase().compareTo((y.data.getWord()));
            if (val < 0){
                y = y.left;
            }
            else if (val > 0){
                y = y.right;
            }
            else return y.data;
        }
        Word notFound = new Word("");
        notFound.setDictName("No ID");
        notFound.setWord("(" + search + ")");
        return notFound;
    }
    //converts BST to and Array while keeping indexes
    public ArrayWord convertToArrayInOrder(){
        ArrayWord word = new ArrayWord();
        App6R(root, word);
        return word;
    }
    //helper method for convertToArrayInOrder
    public void App6R(NodeWord current, ArrayWord word){
        if(current!= null){
            App6R(current.left, word);
            word.set(current.data, current.index);
            App6R(current.right, word);
        }
    }







}

////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
