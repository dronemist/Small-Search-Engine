import java.util.*;
public class MySort<Sortable extends Comparable<Sortable>>{
  ArrayList<Sortable> sortThisList(Myset<Sortable> listOfSortableEntries){
    ArrayList<Sortable> x = new ArrayList<>();
    node<Sortable> temp = new node<>();
    if(listOfSortableEntries.isEmpty())
      return x;
    else {
      temp = listOfSortableEntries.front();
      while(temp!=null)
      {
        x.add(temp.data);
        temp=temp.next;
      }
      int pos;
      Sortable max;
      for(int i=0;i<x.size();i++)
      {
        pos=i;
        max = x.get(i);
        for(int j=i+1;j<x.size();j++)
        {
          if(x.get(j).compareTo(max)>0)
          {
            max = x.get(j);
            pos=j;
          }
        }
        Collections.swap(x, pos, i);
      }
      return x;
    }
  }
}
