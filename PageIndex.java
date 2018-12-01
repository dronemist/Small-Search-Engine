public class PageIndex{
  Linkedlist<WordEntry> list;
  public PageIndex(){
    list = new Linkedlist<>();
  }
  WordEntry getWordEntry(String str)
  {
    node<WordEntry> temp = null;
    if(!list.isEmpty())
      temp=list.front();
    while(temp!=null)
    {
      if(temp.data.str.equals(str))
        break;
      temp=temp.next;
    }
    if(temp!=null)
      return temp.data;
    else {
      return null;
    }
  }
  void addPositionForWord(String str,Position p)
  {
    WordEntry temp = getWordEntry(str);
    if(temp!=null)
    {
      temp.addPosition(p);
    }
    else
    {
      WordEntry e = new WordEntry(str);
      e.addPosition(p);
      list.insertRear(e);
    }
  }
  void addTreePositionForWord(String str, Position p)
  {
    WordEntry temp = getWordEntry(str);
    if(temp!=null)
    {
      temp.addPositionForTree(p);
    }
  }
  Linkedlist<WordEntry> getWordEntries(){
    return list;
  }
}
