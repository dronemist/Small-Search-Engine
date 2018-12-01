import java.math.*;
import java.util.*;
public class MyAvlTree<T extends Comparable<T>>{
  MyTreeNode<T> root;
  public MyAvlTree(){
    root = null;
  }
  public MyAvlTree<T> cloneTree(MyTreeNode<T> root)
  {
    MyAvlTree<T> temp = new MyAvlTree<>();
    if(root==null)
      return temp;
    else {
      temp.normalInsert(root.data);
      if(root.hasLeft())
        temp.root.left = cloneTree(root.left).root;
      if(root.hasRight())
        temp.root.right = cloneTree(root.right).root;
      return temp;
    }
  }
  void printTree()
  {
    BTreePrinter.printNode(root);
  }
  public MyTreeNode<T> getSmallestElement(){
    MyTreeNode<T> temp = new MyTreeNode<>();
    temp = root;
    if(temp!=null)
    {
      while(temp.hasLeft())
      {
        temp=temp.left;
      }
      return temp;
    }
    else
    {
      return null;
    }
  }
  public MyTreeNode<T> getRoot(){
    return root;
  }
  public boolean isMember(T x)
  {
    MyTreeNode<T> temp = searchReturnNode(x);
    if(temp==null)
      return false;
    else {
      return true;
    }
  }
  // public T searchReturnElement(T x){
  //   T lTurn = null;
  //   if(root==null)
  //     return lTurn;
  //   else {
  //     lTurn = null;
  //     MyTreeNode<T> temp = new MyTreeNode<>();
  //     temp = root;
  //     while(temp!=null)
  //     {
  //         if(temp.data.compareTo(x)>0){
  //           lTurn = temp.data;
  //           temp = temp.left;
  //         }
  //         else if (temp.data.compareTo(x)<0) {
  //           temp = temp.right;
  //         }
  //         else {
  //           return temp.data;
  //         }
  //     }
  //     return lTurn;
  //   }
  // }
  public MyTreeNode<T> searchReturnNode(T x)
  {
    MyTreeNode<T> temp = new MyTreeNode<>();
    if(root==null)
    {
      return null;
    }
    else {
      temp = root;
      while(temp!=null)
      {
        if(temp.data.compareTo(x)>0)
          temp = temp.left;
        else if (temp.data.compareTo(x)<0) {
          temp = temp.right;
        }
        else {
          return temp;
        }
      }
      return temp;
    }
  }
  public MyTreeNode<T> getInorderSuccessor(T x)
  {
    MyTreeNode<T> temp,lTurn = new MyTreeNode<>();
    //temp = searchReturnNode(x);
    if(root==null)
      return null;
    else {
          temp = root;
          lTurn = null;
          while(temp!=null)
          {
              if(temp.data.compareTo(x)>0)
              {
                lTurn = temp;
                temp=temp.left;
              }
              else{
                temp=temp.right;
              }
          }
        if(lTurn!=null)
          return lTurn;
        else {
          return null;
        }
    }
  }
  MyTreeNode<T> highestChild(MyTreeNode<T> z){
    MyTreeNode<T> y = new MyTreeNode<>();
    if(z.hasChild())
    {
      if(z.hasLeft()&&z.hasRight())
      {
        if(z.right.height>z.left.height)
          y = z.right;
        else
          y = z.left;
      }
      else if (z.hasRight()) {
          y = z.right;
      }
      else {
        y = z.left;
      }
    }
    return y;
  }
  public boolean isRoot(MyTreeNode<T> x)
  {
    return x==root;
  }
  void restructure(MyTreeNode<T> ubNode)
  {
    MyTreeNode<T> z = ubNode;
    MyTreeNode<T> y,x = new MyTreeNode<>();
    MyTreeNode<T> a,b,c,d = new MyTreeNode<>();
    int a1,b1,c1,d1;
    a = null;b = null;c=null;d=null;
    a1=-1;b1=-1;c1=-1;d1=-1;
    y = highestChild(z);
    x = highestChild(y);
    if(z.isRight(y))
    {
      if(y.isLeft(x))
      {
        if(z.hasLeft())
          {
            a = z.left;
            a1 = a.height;
          }
        if(x.hasLeft())
        {
          b = x.left;
          b1=b.height;
        }
        if(x.hasRight())
        {
          c = x.right;
          c1 = c.height;
        }
        if(y.hasRight())
          {
            d = y.right;
            d1 = d.height;
          }
          if(z.hasParent())
          {
            if(z.parent.isRight(z))
              z.parent.right = x;
            else {
              z.parent.left = x;
            }
          }
          x.parent = z.parent;
          if(isRoot(z))
            root = x;
          x.left = z;
          x.right = y;
          z.parent =x; y.parent= x;
          z.left = a;z.right = b; y.left = c;y.right = d;
          z.height =  Math.max(a1, b1) +1;
          y.height =  Math.max(c1, d1) +1;
          x.height =  Math.max(z.height, y.height) +1;
      }
      else if (y.isRight(x)) {
        if(z.hasLeft())
          {
            a = z.left;
            a1 = a.height;
          }
        if(y.hasLeft())
        {
          b = y.left;
          b1=b.height;
        }
        if(x.hasLeft())
        {
          c = x.left;
          c1 = c.height;
        }
        if(x.hasRight())
          {
            d = x.right;
            d1 = d.height;
          }
          if(z.hasParent())
          {  if(z.parent.isRight(z))
              z.parent.right = y;
            else {
              z.parent.left = y;
            }
          }
          if(isRoot(z))
            root = y;
          y.parent = z.parent;
          y.left = z;
          y.right = x;
          z.parent =y; x.parent= y;
          z.left = a;z.right = b; x.left = c;x.right = d;
          z.height =  Math.max(a1, b1) +1;
          x.height =  Math.max(c1, d1) +1;
          y.height =  Math.max(z.height, x.height) +1;
      }
    }
    else if (z.isLeft(y)) {
      if(y.isLeft(x))
      {
        if(x.hasLeft())
          {
            a = x.left;
            a1 = a.height;
          }
        if(x.hasRight())
        {
          b = x.right;
          b1=b.height;
        }
        if(y.hasRight())
        {
          c = y.right;
          c1 = c.height;
        }
        if(z.hasRight())
          {
            d = z.right;
            d1 = d.height;
          }
          if(z.hasParent())
          {  if(z.parent.isRight(z))
              z.parent.right = y;
            else {
              z.parent.left = y;
            }
          }
          if(isRoot(z))
            root = y;
          y.parent = z.parent;
          y.left = x;
          y.right = z;
          z.parent =y; x.parent= y;
          z.left = c;z.right = d; x.left = a;x.right = b;
          z.height =  Math.max(c1, d1) +1;
          x.height =  Math.max(a1, b1) +1;
          y.height =  Math.max(z.height, x.height) +1;
      }
      else if (y.isRight(x)) {
        if(y.hasLeft())
          {
            a = y.left;
            a1 = a.height;
          }
        if(x.hasLeft())
        {
          b = x.left;
          b1=b.height;
        }
        if(x.hasRight())
        {
          c = x.right;
          c1 = c.height;
        }
        if(z.hasRight())
          {
            d = z.right;
            d1 = d.height;
          }
          if(z.hasParent())
          {  if(z.parent.isRight(z))
              z.parent.right = x;
            else {
              z.parent.left = x;
            }
          }
          if(isRoot(z))
            root = x;
          x.parent = z.parent;
          x.left = y;
          x.right = z;
          z.parent =x; y.parent= x;
          z.left = c;z.right = d; y.left = a;y.right = b;
          z.height =  Math.max(c1, d1) +1;
          y.height =  Math.max(a1, b1) +1;
          x.height =  Math.max(z.height, y.height) +1;
      }
    }
  }
  /*private MyTreeNode<T>*/void normalInsert(T x)
  {
    MyTreeNode<T> temp = new MyTreeNode<>();
    MyTreeNode<T> node = new MyTreeNode<>();
    node.data = x;
    if(root==null)
    {
      root = new MyTreeNode<>();
      root.data = x;
      root.height = 0;
      //return root;
    }
    else
    {
        temp = root;
        while(temp!=null)
        {
          if(temp.data.compareTo(x)>=0)
          {
            if(temp.left!=null)
              temp = temp.left;
            else {
              node.parent = temp;
              temp.left = node;
              break;
            }
          }
          else if (temp.data.compareTo(x)<0) {
            if(temp.right!=null)
              temp=temp.right;
            else
            {
              node.parent = temp;
              temp.right = node;
              break;
            }
          }
        }
        temp = node.parent;
        while(temp!=null)
        {
          int prev = temp.height;
          if(!temp.isAvl())
          {
            restructure(temp);
          }
          if(temp.hasChild())
          {
              if(temp.hasLeft()&&temp.hasRight())
                temp.height = Math.max(temp.left.height,temp.right.height)+1;
              else if (temp.hasRight()) {
                temp.height = temp.right.height+1;
              }
              else {
                temp.height = temp.left.height + 1;
              }
          }
          int now = temp.height;
          if(prev==now)
          {
            break;
          }
          temp = temp.parent;
        }
    }
  }
}
class BTreePrinter {

    public static <T extends Comparable<?>> void printNode(MyTreeNode<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<MyTreeNode<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<MyTreeNode<T>> newNodes = new ArrayList<MyTreeNode<T>>();
        for (MyTreeNode<T> node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(MyTreeNode<T> node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}
