import java.math.*;
import java.util.*;
public class InvertedPageIndex{
  MyHashTable invertedIndex;
  Linkedlist<PageEntry> pageEntryList;
  public InvertedPageIndex(){
    pageEntryList = new Linkedlist<>();
    invertedIndex = new MyHashTable();
  }
  void addPage(PageEntry p)
  {
    if(pageEntryList.isEmpty())
    {
      invertedIndex.addPage(p);
      pageEntryList.insertRear(p);
    }
    else {
      node<PageEntry> temp = new node<>();
      temp = pageEntryList.front();
      while(temp!=null)
      {
        if(temp.data.name.equals(p.name))
          break;
        temp = temp.next;
      }
      if(temp==null)
      {
        invertedIndex.addPage(p);
        pageEntryList.insertRear(p);
      }
      else
      {
        System.out.println("Webpage "+p.name+" is already added");
      }
    }

  }
  public float getRelevanceOfPageSingleWord(PageEntry p,String str)
  {
    return p.getTermFrequency(str)*getInverseDocumentFrequency(str);
  }
  float getInverseDocumentFrequency(String str)
  {
    int n = pageEntryList.size();
    Myset<PageEntry> m = invertedIndex.getPages(str);
    int t = m.size();
    if(t!=0)
      return (float)Math.log(((float)n)/t);
    else
        return Float.MAX_VALUE;
  }
  float getInverseDocumentFrequencyPhrase(String str[])
  {
    int n = pageEntryList.size();
    Myset<PageEntry> m = invertedIndex.getPagesPhrase(str);
    int t = m.size();
    if(t!=0)
      return (float)Math.log(((float)n)/t);
    else
        return Float.MAX_VALUE;
  }
  float getRelevanceOfPage(PageEntry p, String str[],boolean doTheseWordsRepresentAPhrase)
  {
    float tFrequency;
    float relevance = 0f;
    float idFrequency;
    if(doTheseWordsRepresentAPhrase==false)
    {
      for(int i=0;i<str.length;i++)
        {
          tFrequency = p.getTermFrequency(str[i]);
          idFrequency = getInverseDocumentFrequency(str[i]);
          relevance+=(tFrequency*idFrequency);
        }
    }
    else {
      int m = p.containsPhrase(str);
      int k = str.length;
      int totWords = p.numWords;
      idFrequency = getInverseDocumentFrequencyPhrase(str);
      tFrequency = ((float)m/(totWords-(k-1)*m));
      relevance = idFrequency*tFrequency;
    }
    return relevance;
  }
  public Myset<PageEntry> sortPageEntrySet(Myset<PageEntry> m, String str[],boolean phraseQuery)
  {
    Myset<SearchResult> t = new Myset<>();
    if(m.isEmpty())
      return m;
    else {
      node<PageEntry> temp1 = m.front();
      float rel = 0f;
      while(temp1!=null)
      {
        rel = getRelevanceOfPage(temp1.data, str, phraseQuery);
        SearchResult temp2 = new SearchResult(temp1.data, rel);
        t.addElement(temp2);
        temp1=temp1.next;
      }
      MySort<SearchResult> sort = new MySort<>();
      ArrayList<SearchResult> sortedPages = sort.sortThisList(t);
      Myset<PageEntry> m2 = new Myset<>();
      for(int j=0;j<sortedPages.size();j++)
      {
        SearchResult x = sortedPages.get(j);
        m2.addElement(x.getPageEntry());
      }
      return m2;
    }
  }
  Myset<PageEntry> getPagesWhichContainWord(String str){
    Myset<PageEntry> m = invertedIndex.getPages(str);
    Myset<SearchResult> t = new Myset<>();
    if(m.isEmpty())
      return m;
    else {
      node<PageEntry> temp1 = m.front();
      float rel = 0f;
      while(temp1!=null)
      {
        rel = getRelevanceOfPageSingleWord(temp1.data,str);
        SearchResult temp2 = new SearchResult(temp1.data, rel);
        t.addElement(temp2);
        temp1=temp1.next;
      }
      MySort<SearchResult> sort = new MySort<>();
      ArrayList<SearchResult> sortedPages = sort.sortThisList(t);
      Myset<PageEntry> m2 = new Myset<>();
      for(int j=0;j<sortedPages.size();j++)
      {
        SearchResult x = sortedPages.get(j);
        m2.addElement(x.getPageEntry());
      }
      return m2;
    }

  }
  Myset<PageEntry> getPagesWhichContainAllWord(String str[])
  {
    Myset<PageEntry> m = invertedIndex.getPagesAllWords(str);
    Myset<PageEntry> m2 = sortPageEntrySet(m, str, false);
    return m2;
  }
  Myset<PageEntry> getPagesWhichContainAnyWord(String str[])
  {
    Myset<PageEntry> m = invertedIndex.getPagesAnyWords(str);
    Myset<PageEntry> m2 = sortPageEntrySet(m, str, false);
    return m2;
  }
  Myset<PageEntry> getPagesWhichContainPharse(String str[])
  {
    Myset<PageEntry> m = invertedIndex.getPagesPhrase(str);
    Myset<PageEntry> m2 = sortPageEntrySet(m, str, true);
    return m2;
  }
  // public static void main(String[] args) {
  //   PageEntry p1 = new PageEntry("stackoverflow");
  //   PageEntry p2 = new PageEntry("stack_cprogramming");
  //   PageEntry p3 = new PageEntry("stack_datastructure_wiki");
  //   InvertedPageIndex i = new InvertedPageIndex();
  //   i.addPage(p1);i.addPage(p2);i.addPage(p3);
  //   String str[] = new String[4];
  //   str[0] = "data";str[1] = "structure";str[2]="stack";str[3]="first";
  //   System.out.println(i.getPagesWhichContainAnyWord(str).front().next.data.name);
  //   //System.out.println(p3.getTermFrequency(str[1]));
  //   //System.out.println(i.getRelevanceOfPage(p3, str, false));
  //   // i.getPagesWhichContainAnyWord(str)
  // }

}
