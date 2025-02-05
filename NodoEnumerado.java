package Trees;

public class NodoEnumerado<T extends Comparable<T>> implements Comparable<NodoEnumerado<T>>{
  private int numero;
  private T key;
  private NodoEnumerado<T> left, rigth, parent;

  public NodoEnumerado(T key, int numero) {
    this.key = key;
    this.numero = numero;
    left = null;
    rigth = null;
    parent = null;
  } 

  public NodoEnumerado(NodoEnumerado<T> padre, T key) {
    parent = padre;
    this.key = key;
  }

  public NodoEnumerado<T> getLeft() {
    return left;
  }

  public NodoEnumerado<T> getRigth() {
    return rigth;
  }

  public NodoEnumerado<T> getParent() {
    return parent;
  }

  public T getKey() {
    return key;
  }

  public int getNumber() {
    return numero;
  }

  public void setLeft(NodoEnumerado<T> left) {
    this.left = left;
    this.left.numero = numero*2;
  }

  public void setRigth(NodoEnumerado<T> rigth) {
    this.rigth = rigth;
    this.rigth.numero = numero*2 + 1;
  }

  public void setKey(T key) {
    this.key = key;
  }

  @Override
  public int compareTo(NodoEnumerado<T> e) {
    return key.compareTo(e.key);
  }
}