import java.io.*;
import java.util.*;
public class PageEntry{
  int numWords;
  String name;
  PageIndex index = new PageIndex();
  public PageEntry(String pageName){
    try {
      numWords =0;
      name = pageName;
      FileInputStream fstream = new FileInputStream("webpages/"+ pageName);
      Scanner s = new Scanner(fstream);
      String wd="";
      int cnt = 1;
      int cntNonConnector = 1;
      while(s.hasNextLine())
      {
          Myset<String> x = new Myset<>();
          x.addElement("a");x.addElement("an");x.addElement("the");x.addElement("they");x.addElement("these");x.addElement("this");x.addElement("for");
          x.addElement("is");x.addElement("are");x.addElement("was");x.addElement("of");x.addElement("or");x.addElement("and");x.addElement("does");x.addElement("will");
          x.addElement("whose");
          wd = s.nextLine();
          wd = wd.toLowerCase();
          //String words[] = wd.split("\\s++|\\{|}|[|]|<|>|\\(|\\)|\\.|,|;|'|\"|\\?|#|!|-|:");
        wd = wd.replace(',',' ');
        wd = wd.replace('{',' ');
        wd = wd.replace('}',' ');
        wd = wd.replace('[',' ');
        wd = wd.replace(']',' ');
        wd = wd.replace('<',' ');
        wd = wd.replace('>',' ');
        wd = wd.replace('=',' ');
        wd = wd.replace('(',' ');
        wd = wd.replace(')',' ');
        wd = wd.replace('.',' ');
        wd = wd.replace(';',' ');
        wd = wd.replace('"',' ');
        wd = wd.replace('?',' ');
        wd = wd.replace('#',' ');
        wd = wd.replace('!',' ');
        wd = wd.replace('-',' ');
        wd = wd.replace(':',' ');
        wd = wd.replace('\'',' ');
          Scanner l = new Scanner(wd);
          while(l.hasNext())
          {
            String word = l.next();
              if(!x.isMember(word))
              {
                Position p =new Position(this, cnt);
                Position p2 = new Position(this,cntNonConnector);
                String temp = word;
                if(word.equals("stacks"))
                {
                  temp = "stack";
                }
                else if (word.equals("applications")) {
                  temp="application";
                }
                else if(word.equals("structures")){
                  temp = "structure";
                }
                index.addPositionForWord(temp,p);
                index.addTreePositionForWord(temp, p2);
                cnt++;
                numWords++;
                cntNonConnector++;

            }
            else {
              cnt++;
              //numWords++;
            }
          }
          l.close();
        }

      s.close();
    } catch(FileNotFoundException e) {
        System.out.println("webpage "+ pageName +" not found");
    }
  }

  PageIndex getPageIndex(){
    return index;
  }
float getTermFrequency(String word){
  node<WordEntry> temp = new node<>();
  if(!index.getWordEntries().isEmpty())
    temp =index.getWordEntries().front();
  else
      return 0f;
  WordEntry e = null;
  while(temp!=null){
    if(temp.data.str.equals(word))
      {
        e = temp.data;
        break;
      }
    temp=temp.next;
  }
  float ans = 0f;
  if (e!=null) {
    ans = ((float)e.list.size())/numWords;
  }
  return ans;
}
// WordEntry getWordEntry(String str)
// {
//
// }
public boolean equals(PageEntry p)
{
  if(p==this)
    return true;
  else if (this==null)
    return false;
  else {
    return this.name.equals(p.name);
  }
}
public int containsPhrase(String[] str)
{
  int size = str.length;
  int cnt=0;
  WordEntry w1 = index.getWordEntry(str[0]);
  int f = 1;
  MyTreeNode<Position> temp = w1.positionTree.getSmallestElement();
  while(temp!=null)
  {
    f=1;
    for(int i=1;i<size;i++)
    {
      WordEntry w2 = index.getWordEntry(str[i]);
      if(w2!=null){
        if(!w2.positionTree.isMember(new Position(this,temp.data.getWordIndex()+i)))
        {
          f=0;
          break;
        }
      }
      else {
        return 0;
      }
    }
    if(f==1)
      cnt++;
    temp = w1.positionTree.getInorderSuccessor(temp.data);
  }
  return cnt;
}
// public static void main(String[] args) {
//   PageEntry p =new PageEntry("stack_datastructure_wiki");
//   String str[] = new String[4];
//   str[0] = "computer";str[1]="science";str[2] = "stack"; str[3] = "lifo";
//   System.out.println(p.containsPhrase(str));
// }
}
