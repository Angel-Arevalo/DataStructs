package Trees;

import DinamicList.QueuesDinamic;
import DinamicList.DinamicList;

public class BinaryTree<T extends Comparable<T>> {
  protected TreeNode<T> root;
  public BinaryTree() {
    root = null;
  }

  public BinaryTree(T key) {
    root = new TreeNode<T>(key);
  }

  public void add(T newItem) {
    if(root == null) {
      root = new TreeNode<T>(newItem);
      return;
    }

    TreeNode<T> ref = find(newItem);
    TreeNode<T> t = new TreeNode<>(ref, newItem);
    int compa = ref.compareTo(t);
    if(compa == 0 || ref == null) return;

    if(compa < 0) {
      ref.conservador = t;
    }else {
      ref.liberal = t;
    }
  }

  public TreeNode<T> find(T element) {
    TreeNode<T> ref = root;
    TreeNode<T> toR = ref;

    while(ref != null) {
      int compa = ref.key.compareTo(element);
      if(compa == 0) return ref;

      toR = ref;
      if(0 < compa) {
        ref = ref.liberal;
      }
      if(compa < 0) {
        ref = ref.conservador;
      }
    }
    return toR;
  }

  static public void print(TreeNode root) {
    TreeNode ref = root;

    if(ref.liberal != null) {
      print(ref.liberal);
    }
    System.out.println(ref.key);

    if(ref.conservador != null) {
      print(ref.conservador);
    }
  }


  static public <K extends Comparable<K>> void printC(TreeNode<K> root) {
    QueuesDinamic<TreeNode<K>> q = new QueuesDinamic<>();
    q.add(root);

    while(0 < q.size()) {
      TreeNode<K> n = q.remove();
      System.out.println(n.key);
      if(n.liberal != null) {
        q.add(n.liberal);
      }if(n.conservador != null) {
        q.add(n.conservador);
      }
    }
  }

  static public void print(BinaryTree tree) {
    print(tree.root);
  }

  static public <K extends Comparable<K>> void printC(BinaryTree<K> tree) {
    printC(tree.root);
  }

  public static <K extends Comparable<K>> int height(BinaryTree<K> tree) {
    return height(tree.root);
  }

  protected static <K extends Comparable<K>> int height(TreeNode<K> node) {
    if(node == null) return 0;
    return 1 + Math.max(height(node.liberal), height(node.conservador));
  }

  public TreeNode<T> next(TreeNode<T> node) {
    if(node == null) return null;
    TreeNode<T> ref = null;

    if(node.conservador != null) {
      ref = node.conservador;

      while(ref.liberal != null) {
        ref = ref.liberal;
      }
      return ref;
    }
    ref = node.parentNode;
    while(ref != null && ref.compareTo(node) < 0) {
      ref = ref.parentNode;
    }
    return ref;
  }

  public DinamicList<T> find(T min, T max) {
    if(max.compareTo(min) < 0) return null;

    TreeNode<T> m = new TreeNode<>(min);
    TreeNode<T> obj = new TreeNode<>(max);
    TreeNode<T> ref = root;

    while(ref.liberal != null && 0 < ref.compareTo(m)) {
      ref = ref.liberal;
      if(ref.compareTo(m) < 0) {
        ref = ref.parentNode;
        break;
      }
    }
    DinamicList<T> toR = new DinamicList<>();
    while(ref != null && 0 < obj.compareTo(ref)) {
      toR.PushBack(ref.key);
      ref = next(ref);
    }
    return toR;
  }


  public static <K extends Comparable<K>> DinamicList<K> toArray(BinaryTree<K> tree) {
    DinamicList<K> toR = new DinamicList<>();
    if(tree == null || tree.root == null) return toR;
    toArray(tree.root, toR);
    return toR;
  }

  private static <K extends Comparable<K>> void toArray(TreeNode<K> node, DinamicList<K> list) {
    if(node == null) return;

    if(node.liberal != null)
      toArray(node.liberal, list);

    list.PushBack(node.key);

    if(node.conservador != null) 
        toArray(node.conservador, list);
  }

  

  public void delete(T item) {
    TreeNode<T> node = find(item);

    if(node.liberal == null && node.conservador == null) {
      if(node == root) root = null;
      else {
        TreeNode<T> h = node.parentNode;
        if(h.liberal == node) {
          h.liberal = null;
        }else h.conservador = null;
      }
      return;
    }

    if(node.liberal != null && node.conservador == null) {
      if(node == root) {
        root = node.liberal;
        root.parentNode = null;
      }else {
        TreeNode<T> h = node.parentNode;
        if(h.liberal == node) {
          h.liberal = node.liberal;
        }else h.conservador = node.liberal;
        node.liberal.parentNode = h;
      }

      node.liberal = null;
      return;
    }

    if(node.liberal == null && node.conservador != null) {
      if(node == root) {
        root = node.conservador;
        root.parentNode = null;
        node.conservador = null;
      }else {
        TreeNode<T> h = node.parentNode;
        if(h.liberal == node) {
          h.liberal = node.conservador;
        }else h.conservador = node.conservador;
        node.conservador.parentNode = h;
      }
      node.conservador = null;
      return;
    }

    TreeNode<T> help = next(node), h = help.parentNode;
    node.key = help.key;

    if(h.liberal == help) {
      h.liberal = help.conservador;
    }else h.conservador = help.conservador;
    if(help.conservador != null) {
      help.conservador.parentNode = h;
    }
    help.conservador = null;
    help.parentNode = null;
  }
}