public class WordEntry{
  String str;
  Linkedlist<Position> list;
  MyAvlTree<Position> positionTree;
  public WordEntry(String word)
  {
    str = word;
    list = new Linkedlist<>();
    positionTree = new MyAvlTree<>();
  }
  void addPositionForTree(Position p)
  {
    positionTree.normalInsert(p);
  }
  void addPosition(Position position){
    list.insertRear(position);
  }
  void addPositions(Linkedlist <Position> positions)
  {
      node<Position> temp = positions.front();
      while(temp!=null)
      {
        list.insertRear(temp.data);
        temp = temp.next;
      }
  }
  Linkedlist <Position> getAllPositionsForThisWord(){
    return list;
  }
  public WordEntry cloneThis(){
    WordEntry t = new WordEntry(str);
    t.list = list.clonelist();
    t.positionTree = positionTree.cloneTree(positionTree.root);
    return t;
  }
  public boolean equals(WordEntry w)
  {
    if(this==w)
    return true;
    else if (w==null) {
      return false;
    }
    else
    {
      return this.str.equals(w.str);
    }
  }
}
