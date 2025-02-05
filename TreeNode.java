package Trees;

public class TreeNode<K extends Comparable<K>> implements Comparable<TreeNode<K>>{
  K key;
  TreeNode<K> liberal;
  TreeNode<K> conservador;
  TreeNode<K> parentNode;
  int height;

  public TreeNode(TreeNode<K> parent, K key) {
    this.key = key;
    parentNode = parent;
    liberal = null;
    conservador = null;
    height = 1;
  }

  public TreeNode(K key) {
    this.key = key;
    liberal = null;
    conservador = null;
    parentNode = null;
    height = 1;
  }

  public TreeNode<K> getLeft() {
    return liberal;
  }

  public void setLeft(TreeNode<K> x) {
    liberal = x;
  }

  public TreeNode<K> getRigth() {
    return conservador;
  }

  public void setRigth(TreeNode<K> x) {
    liberal = x;
  }

  public TreeNode<K> getParent() {
    return parentNode;
  }

  public K getKey() {
    return key;
  }

  public void setKey(K k) {
    key = k;
  }

  @Override
  public int compareTo(TreeNode<K> another) {
    if(another == null) return 1;
    return key.compareTo(another.key);
  }

  public int compareTo(K an) {
    return key.compareTo(an);
  }

  public String toString() {
    return key.toString();
  }
}