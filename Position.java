public class Position implements Comparable<Position>{
  PageEntry p;
  int wordIndex;
  public Position(PageEntry l,int w){
    p = l;
    wordIndex = w;
  }
  public PageEntry getPageEntry(){
    return p;
  }
  public int getWordIndex(){
    return wordIndex;
  }
  public boolean equals(Position x)
  {
    if(this==x)
      return true;
    else if (x==null) {
      return false;
    }
    else
    return p.equals(x.p)&& x.wordIndex==wordIndex;
  }
  public int compareTo(Position x)
  {
    return wordIndex-x.wordIndex;
  }
}
