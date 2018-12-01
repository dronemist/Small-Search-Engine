public class Linkedlist<T>{
  node<T> front,rear = new node<>();
  int size;
  public Linkedlist(){
    front = null;
    rear = null;
    size = 0;
  }
  node<T> createNode(T t){
    node<T> n = new node<>();
    n.data = t;
    n.next = null;
    return n;
  }
  public int size(){
    return size;
  }
  public node<T> front(){
    return front;
  }
  public node<T> rear(){
    return rear;
  }
  public boolean isEmpty(){
    return size == 0;
  }
  public boolean isMember(T t){
    node<T> temp = front;
    while(temp != null)
    {
      if((temp.data).equals(t))
        return true;
      temp = temp.next;
    }
    return false;
  }
  public void insertRear(T t){
    if(rear == null)
    {
      rear = createNode(t);
      front = rear;
    }
    else
    {
      node<T> n = createNode(t);
      rear.next = n;
      rear = n;
    }
    size++;
  }
  public Linkedlist<T> clonelist(){
    Linkedlist<T> x = new Linkedlist<>();
    node<T> temp = new node<>();
    temp=null;
    if(size!=0)
      temp=front;
    while(temp!=null)
    {
      x.insertRear(temp.data);
      temp=temp.next;
    }
    return x;
  }
  void deleteObject(T o){
    node<T> i = new node<>();
    node<T> j = new node<>();
    i = front;
    int f = 0;
    if(o.equals(i.data) && i!=null)
      {
        if(rear==front)
        {
          front = null;
          rear = null;
        }
        front = i.next;
        f=1;
      }
    while(i != null && (!o.equals(i.data)))
    {
      j=i;
      i=i.next;
    }
    if(i!=null)
    {
      j.next = i.next;
      if(i==rear)
        rear = j;
      i = null;
      f=1;
    }
    if(f==1)
      size--;
    if(f==0)
    {
      throw new IllegalArgumentException("Element not present,line(81),ExchangeList.java");
    }
      }
    }
    /*public int Search(Object o){
      node temp = front;
      int pos = 1;
      while(temp!=null)
      {
          if(temp.data == o)
          {
            return pos;
          }
          temp =temp.next;
          pos+=1;
      }
      return -1;
    }*/
    // public void print(){
    //   node n = front;
    //   if(n==null)
    //   {
    //     System.out.println("list empty");
    //   }
    //   while(n!=null)
    //   {
    //     System.out.print(n.data + " ");
    //     n = n.next;
    //   }
    //
    // }
