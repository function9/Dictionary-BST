class Word{
    // private variables 
    private String name;
    private String dictName = "No ID";
    private boolean found = false;

    //constructor
    Word(String string){
        name = string;
    }
    // methods
    //returns word String
    public String getWord(){
        return name;
    }
    public void setFound(boolean set){
        found = set;
    }
    //sets word.name
    public void setWord(String set){
        name = set;
    }
    //sets Dictionary Name
    public void setDictName(String set){
        dictName = set;
    }
    //returns dictionary name
    public String getDictName(){
        return dictName;
    }
    //returns word length
    public int getWordLength(){
        return name.length();
    }

}
