package Trees;

import DinamicList.DinamicList;

public class Heaps<T extends Comparable<T>> {
  private DinamicList<T> heap;

  public Heaps() {
    heap = new DinamicList<T>();
  }

  public void insert(T item) {
    heap.PushBack(item);
    siftUp(heap.size()-1);
  }

  private void siftUp(int i) {
    if(i == 0) return;

    int parentIndex = (i-1)/2;
    T parent = heap.get(parentIndex);
    T currentItem = heap.get(i);
    if(parent.compareTo(currentItem) < 0) {
      swap(i, parentIndex);
      siftUp(parentIndex);
    }
  }

  private void siftDown(int i) {
    int size = heap.size();
    if(size <= i) return;
    if(size <= 2*i) return;

    T leftChild = heap.get(2*i);
    T parent = heap.get(i);
    if(size <= 2*i+1) {
      if(leftChild.compareTo(parent) > 0) {
        swap(i, 2*i);
        siftDown(2*i);
      }
      return;
    }
    T rigthChild = heap.get(2*i+1);

    if(rigthChild.compareTo(leftChild) < 0) {
      if(leftChild.compareTo(parent) > 0) {
        swap(i, 2*i);
        siftDown(2*i);
      }
      return;
    }else {
      if(rigthChild.compareTo(parent) > 0) {
        swap(i, 2*i+1);
        siftDown(2*i+1);
      }
    }
  }

  private void swap(int i, int j) {
    T p = heap.get(i), t = heap.get(j);
    heap.set(i, t);
    heap.set(j, p);
  }

  public void delete(int index) {
    int size = heap.size();
    if(index < 0 || size <= index) return;
    if(size == 1) {
      heap = new DinamicList<T>();
    }
    swap(index, size - 1);
    

  }

  public void print() {
    heap.print();
  }
}