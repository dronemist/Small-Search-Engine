import java.math.*;
public class SearchResult implements Comparable<SearchResult>{
  PageEntry page;
  float relevance;
  public SearchResult(PageEntry p,float r)
  {
    page = p;
    relevance = r;
  }
  public PageEntry getPageEntry()
  {
    return page;
  }
  public float getRelevance()
  {
    return relevance;
  }
  public int compareTo(SearchResult otherObject)
  {
    return (int)Math.ceil(relevance-otherObject.relevance);
  }
}
