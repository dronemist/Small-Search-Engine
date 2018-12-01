import java.lang.*;
public class Myset<T>{
  Linkedlist<T> list;
  public Myset(){
    list = new Linkedlist<>();
  }
  public boolean isEmpty(){
    return list.isEmpty();
  }
  public int size(){
    return list.size();
  }
  public node<T> front(){
    return list.front();
  }
  public node<T> rear(){
    return list.rear();
  }
  public boolean isMember(T o){
    return list.isMember(o);
  }
  public void addElement(T o){
      if(isMember(o) == false)
      {
        list.insertRear(o);
      }
    }
  public void Delete(T o){
      try {
        list.deleteObject(o);
      }
      catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Error- Object not in set");
      }
  }
  public Myset<T> Union(Myset<T> a){
    node<T> temp = new node<>();
    Myset<T> c = new Myset<>();
    temp = a.front();
    while(temp != null)
    {
      c.addElement(temp.data);
      temp = temp.next;
    }
    temp = front();
    while(temp != null)
    {
      c.addElement(temp.data);
      temp = temp.next;
    }
    return c;
  }
  public void print(){
    node<T> n = front();
    if(n==null)
    {
      System.out.println("list empty");
    }
    while(n!=null)
    {
      System.out.print(n.data + " ");
      n = n.next;
    }
  }
  public Myset<T> Intersection(Myset<T> a){
    Myset<T> c = new Myset<>();
    node<T> temp = new node<>();
    temp = a.front();
    while(temp != null)
    {
      if(isMember(temp.data))
        c.addElement(temp.data);
      temp = temp.next;
    }
    return c;
  }
}
