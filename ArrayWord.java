///////////////////////////////////////////////////////
class ArrayWord{ 
    Word[] words = new Word[1000000];
    int size = 0;
    int letterCount;
    ArrayWord(){

    }
    //inserts word into array
    public void insert(Word data){
        words[size] = data;
        size++;
    }
    //displays array
    public void display(){
        for(int i = 0; i < size; i++){
            System.out.println(words[i].getWord());
        }
    }
    //return size of array
    public int getSize(){
        return size;
    }

    //returns random word
    public Word getRandomWord(){
        int random = (int) Math.random() * size;
        return words[random];
    }
    //deletes last word and returns the word
    public Word deleteLast(){
        Word last = words[size - 1];
        words[size - 1] = null;
        size--;
        return last;
    }
    //sets an index in the array to a word
    public void set(Word word, int index){
        words[index] = word;
        size++;
    }
    //shuffles the array
    public void shuffle(){
        for (int i = 0; i < size - 1; i++) {
            int rand = (int) Math.random() * (size);
            Word temp = words[i];
            words[i] = words[rand];
            words[rand] = temp;
        }
    }
    //displays the array in correct format
    public void displayAll(){
        for (int i = 0; i < size; i++) {
            if(words[i] == null)
                continue;
            System.out.println(i + " " + words[i].getWord() + "; " + words[i].getWordLength() + "; " + words[i].getDictName());
        }
    }
    //doesn't work
    public void plotBST(int xB, int yB){
        StdDraw.setCanvasSize(xB, yB);
        StdDraw.setXscale(0, xB);
        StdDraw.setYscale(0, yB);
        double x = xB / 2;
        double y = yB;
        double x2 = x / 2;
        double y2 = y = 10;
        StdDraw.filledCircle(x , y, 4);
        for(int i = 0; i < size; i++){
            StdDraw.filledCircle(x * 1.5, y, 4);
            x = x / 2;
            y = y - 15;
        }

    }

    
}

