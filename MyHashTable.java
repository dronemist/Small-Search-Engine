public class MyHashTable{
  int size;
  Linkedlist<WordEntry> [] hashTable;
  @SuppressWarnings("unchecked")
  public MyHashTable(){
    size = 997;
    hashTable = new Linkedlist[size];
  }
  private int getHashIndex(String str){
    long l=0;
    long prod = 1;
    for(int i=0;i<str.length();i++)
    {
      l += ((int)str.charAt(i)*prod)%size;
      prod = (prod*13)%size;
    }
    return (int)(l%size);
  }
  void addPositionsForWord(WordEntry w){
      int hashindex = getHashIndex(w.str);
      Linkedlist<WordEntry> temp = new Linkedlist<>();
      if(hashTable[hashindex]!=null)
        temp = hashTable[hashindex];
      else
          hashTable[hashindex] = temp;
      if(temp.isEmpty())
      {
          //word entry clone here
          temp.insertRear(w.cloneThis());
      }
      else
      {
        node <WordEntry> itr = temp.front();
        while(itr!=null)
        {
          if(itr.data.str.equals(w.str))
            break;
          itr=itr.next;
        }
        if(itr==null)
        {
          //clone word entry here
          temp.insertRear(w.cloneThis());
        }
        else
        {
          itr.data.addPositions(w.getAllPositionsForThisWord());
        }
      }
  }
  Myset<PageEntry> getPages(String str){
    Myset<PageEntry> pages = new Myset<>();
    int hashindex = getHashIndex(str);
    Linkedlist<WordEntry> temp = new Linkedlist<>();
    if(hashTable[hashindex]!=null)
      temp = hashTable[hashindex];
    else
      hashTable[hashindex] = temp;
    node <WordEntry> itr = null;
    if(!temp.isEmpty())
      itr = temp.front();
    while(itr!=null)
    {
      if(itr.data.str.equals(str))
        break;
      itr=itr.next;
    }
    if(itr!=null)
      {
        Linkedlist<Position> temp2 = itr.data.getAllPositionsForThisWord();
        node <Position> itr2 = temp2.front();
        while(itr2!=null)
        {
          pages.addElement(itr2.data.getPageEntry());
          itr2=itr2.next;
        }
      }
    return pages;
  }
  void addPage(PageEntry p){
    PageIndex index = p.getPageIndex();
    Linkedlist<WordEntry> l = index.list;
    node<WordEntry> temp = l.front();
    while(temp!=null)
    {
      addPositionsForWord(temp.data);
      temp=temp.next;
    }
  }
  Myset<PageEntry> getPagesAllWords(String str[])
  {
    Myset<PageEntry> temp = new Myset<>();
    temp = getPages(str[0]);
    int size = str.length;
    for(int i=1;i<size;i++)
    {
      temp = temp.Intersection(getPages(str[i]));
    }
    return temp;
  }
  Myset<PageEntry> getPagesAnyWords(String str[])
  {
    Myset<PageEntry> temp = new Myset<>();
    temp = getPages(str[0]);
    int size = str.length;
    for(int i=1;i<size;i++)
    {
      temp = temp.Union(getPages(str[i]));
    }
    return temp;
  }
  Myset<PageEntry> getPagesPhrase(String str[])
  {
    Myset<PageEntry> ans = new Myset<>();
    Myset<PageEntry> temp = new Myset<>();
    temp = getPagesAllWords(str);
    node<PageEntry> itr = null;
    if(temp.isEmpty())
      return ans;
    else {
      itr = temp.list.front();
      while(itr!=null)
      {
        if(itr.data.containsPhrase(str)>0)
          ans.addElement(itr.data);
        itr=itr.next;
      }
      return ans;
    }
  }

}
