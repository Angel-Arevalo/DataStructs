package Trees;

public class VST<T extends Comparable<T>> extends BinaryTree<T> {

  public VST() {
    super();
  }

  public VST(T e) {
    super(e);
  }

  @Override
  public void add(T e) {
    TreeNode<T> m = find(e);
    super.add(e);
    if(m != null && m.compareTo(e) == 0)
      balance(m);
  }

  @Override
  public void delete(T e) {
    TreeNode<T> node = find(e);
    super.delete(e);
  }
  
  private void balance(TreeNode<T> added) {
    TreeNode<T> parend = added;

    while(parend != null) {
      int leftSize = height(parend.liberal);
      int rightSize = height(parend.conservador);

      if(Math.abs(leftSize - rightSize) <= 1) {
        parend = parend.parentNode;
        continue;
      }

      if(rightSize < leftSize) {
        TreeNode<T> leftNode = parend.liberal;
        int l = height(leftNode.liberal);
        int r = height(leftNode.conservador);
        if(r <= l) {
          case1(parend, leftNode);
        }else {
          TreeNode<T> help = leftNode.conservador;
          case2(leftNode, help);
          case1(parend, help);
        }
      }else {
        TreeNode<T> rightNode = parend.conservador;
        int l = height(rightNode.liberal);
        int r = height(rightNode.conservador);
        if(l <= r) {
          case2(parend, rightNode);
        }else {
          TreeNode<T> help = rightNode.liberal;
          case1(rightNode, help);
          case2(parend, help);
        }
      }

      parend = parend.parentNode;
    }


  }

  // X must to be the parend of Y
  private void case1(TreeNode<T> X, TreeNode<T> Y) {
    if(X == null || Y == null) {
      throw new NullPointerException();
    }

    X.liberal = Y.conservador;
    if(Y.conservador != null) {
      Y.conservador.parentNode = X;
    }

    Y.conservador = X;
    Y.parentNode = X.parentNode;

    update(X, Y);
    X.parentNode = Y;
  }

  // Y is the parent of X, but Y < X
  private void case2(TreeNode<T> X, TreeNode<T> Y) {
    if(X == null || Y == null) {
      throw new NullPointerException();
    }

    X.conservador = Y.liberal;
    if(Y.liberal != null) {
      Y.liberal.parentNode = X;
    }
    Y.liberal = X;
    Y.parentNode = X.parentNode;

    update(X, Y);
    X.parentNode = Y;
  }

  // X is the oldChild, Y is the new Child
  private void update(TreeNode<T> X, TreeNode<T> Y) {
    TreeNode<T> parend = X.parentNode;
    if(parend != null) {
      if(parend.liberal == X)
          parend.liberal = Y;
      else parend.conservador = Y;
    }else {
      root = Y;
    }
  }
}