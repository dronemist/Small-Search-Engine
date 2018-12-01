import java.math.*;
public class MyTreeNode<T>{
  public T data;
  MyTreeNode<T> left;
  MyTreeNode<T> right;
  MyTreeNode<T> parent;
  int height;
  public MyTreeNode(){
    left = null;
    right = null;
    data = null;
    parent = null;
    height = 0;
  }
  public boolean hasLeft(){
    return left!=null;
  }
  public boolean hasRight(){
    return right!=null;
  }
  public boolean hasParent(){
    return parent!=null;
  }
  public boolean hasChild(){
    return hasLeft()||hasRight();
  }
  public boolean isAvl(){
    int lh = -1;
    int rh = -1;
    if(hasLeft())
      lh = left.height;
    if(hasRight())
        rh = right.height;
    return Math.abs(lh-rh)<=1;
  }
  public boolean isRight(MyTreeNode<T> temp)
  {
    return right==temp;
  }
  public boolean isLeft(MyTreeNode<T> temp)
  {
    return left==temp;
  }
}
