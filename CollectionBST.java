class CollectionBST{
    // to complete
    String collectionName;
    int wordSize;
    DictionaryBST[] dic;
    int step = 0;
    ArrayWord[] subArray;
    //initializes the collection of dictionaries
    CollectionBST(DictionaryBST dict){
        collectionName = dict.getName();
        wordSize = dict.getSize();
        dic = new DictionaryBST[dict.getMaxWordSize()];
        for(int i = 0; i < dic.length; i++){
            dic[i] = new DictionaryBST();
        }

        subArray = dict.extractSubArrayInOrder();
        for (int i = 0; i < subArray.length; i++) {
            dic[i].initDictionary(subArray[i]);
        }
    }

    //prints out infor about the collection of dictionaries
    public void info(){
        System.out.println("The collection named '" + collectionName + "' is of size " + wordSize);
        System.out.println("Dict --> size --> nblevels");
        for(int i = 0; i < dic.length; i++){
            System.out.println((i + 1) + " --> " + dic[i].getSize() + " -->" + dic[i].getMaxNbLevel());
        }
    }
    //returns the number of steps
    public int getStep(){
        return step;
    }
    //searches the collection of dictionaries
    public boolean search(String search){
        for(int i = 0; i < dic.length; i++){
            if(dic[i].search(search)){
                return true;
            }
            step++;
        }
        return false;
    }
    
}

////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
