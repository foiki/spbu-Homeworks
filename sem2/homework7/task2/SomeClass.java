package group144.kireev;

public class LinkedList<E> extends AbstractSequentialList<E> implements List<E>, Deque<E>, Cloneable, Serializable { 
 
public LinkedList() {
}

public LinkedList(Collection<E> arg1) {
}

public boolean add(Object arg1) {
}

public void add(int arg1, Object arg2) {
}

public boolean remove(Object arg1) {
}

public Object remove() {
}

public Object remove(int arg1) {
}

public Object get(int arg1) {
}

public Object clone() {
}

public int indexOf(Object arg1) {
}

public void clear() {
}

public int lastIndexOf(Object arg1) {
}

public boolean contains(Object arg1) {
}

public int size() {
}

public Object[] toArray(Object[] arg1) {
}

public Object[] toArray() {
}

public Spliterator<T> spliterator() {
}

public boolean addAll(int arg1, Collection<E> arg2) {
}

public boolean addAll(Collection<E> arg1) {
}

public Object set(int arg1, Object arg2) {
}

public Object poll() {
}

public Object peek() {
}

public Object element() {
}

public ListIterator<E> listIterator(int arg1) {
}

public void push(Object arg1) {
}

public void addLast(Object arg1) {
}

public Object pollFirst() {
}

public void addFirst(Object arg1) {
}

public boolean offerFirst(Object arg1) {
}

public boolean offerLast(Object arg1) {
}

public Object removeFirst() {
}

public Object removeLast() {
}

public Object pollLast() {
}

public Object getFirst() {
}

public Object getLast() {
}

public Object peekFirst() {
}

public Object peekLast() {
}

public boolean removeFirstOccurrence(Object arg1) {
}

public boolean removeLastOccurrence(Object arg1) {
}

public boolean offer(Object arg1) {
}

public Object pop() {
}

public Iterator<E> descendingIterator() {
}

public Iterator<E> iterator() {
}

public boolean equals(Object arg1) {
}

public int hashCode() {
}

public List<E> subList(int arg1, int arg2) {
}

public ListIterator<E> listIterator() {
}

public String toString() {
}

public boolean isEmpty() {
}

public boolean containsAll(Collection<E> arg1) {
}

public boolean retainAll(Collection<E> arg1) {
}

public boolean removeAll(Collection<E> arg1) {
}

public final native void wait(long arg1) {
}

public final void wait(long arg1, int arg2) {
}

public final void wait() {
}

public final native Class<T> getClass() {
}

public final native void notify() {
}

public final native void notifyAll() {
}

public Object[] toArray(IntFunction<R> arg1) {
}

public Stream<T> stream() {
}

public boolean removeIf(Predicate<T> arg1) {
}

public Stream<T> parallelStream() {
}

public void forEach(Consumer<T> arg1) {
}

public void replaceAll(UnaryOperator<T> arg1) {
}

public void sort(Comparator<T> arg1) {
}

static final class LLSpliterator<E> extends Object implements Spliterator<T> { 
 
public static final int ORDERED;
public static final int DISTINCT;
public static final int SORTED;
public static final int SIZED;
public static final int NONNULL;
public static final int IMMUTABLE;
public static final int CONCURRENT;
public static final int SUBSIZED;


public void forEachRemaining(Consumer<T> arg1) {
}

public int characteristics() {
}

public boolean tryAdvance(Consumer<T> arg1) {
}

public Spliterator<T> trySplit() {
}

public long estimateSize() {
}

public final native void wait(long arg1) {
}

public final void wait(long arg1, int arg2) {
}

public final void wait() {
}

public boolean equals(Object arg1) {
}

public String toString() {
}

public native int hashCode() {
}

public final native Class<T> getClass() {
}

public final native void notify() {
}

public final native void notifyAll() {
}

public long getExactSizeIfKnown() {
}

public boolean hasCharacteristics(int arg1) {
}

public Comparator<T> getComparator() {
}

}private class DescendingIterator extends Object implements Iterator<E> { 
 

public void remove() {
}

public Object next() {
}

public boolean hasNext() {
}

public final native void wait(long arg1) {
}

public final void wait(long arg1, int arg2) {
}

public final void wait() {
}

public boolean equals(Object arg1) {
}

public String toString() {
}

public native int hashCode() {
}

public final native Class<T> getClass() {
}

public final native void notify() {
}

public final native void notifyAll() {
}

public void forEachRemaining(Consumer<T> arg1) {
}

}private static class Node<E> extends Object  { 
 

public final native void wait(long arg1) {
}

public final void wait(long arg1, int arg2) {
}

public final void wait() {
}

public boolean equals(Object arg1) {
}

public String toString() {
}

public native int hashCode() {
}

public final native Class<T> getClass() {
}

public final native void notify() {
}

public final native void notifyAll() {
}

}private class ListItr extends Object implements ListIterator<E> { 
 

public void add(Object arg1) {
}

public void remove() {
}

public void forEachRemaining(Consumer<T> arg1) {
}

public Object next() {
}

public boolean hasNext() {
}

public void set(Object arg1) {
}

public int nextIndex() {
}

public Object previous() {
}

public int previousIndex() {
}

public boolean hasPrevious() {
}

public final native void wait(long arg1) {
}

public final void wait(long arg1, int arg2) {
}

public final void wait() {
}

public boolean equals(Object arg1) {
}

public String toString() {
}

public native int hashCode() {
}

public final native Class<T> getClass() {
}

public final native void notify() {
}

public final native void notifyAll() {
}

}
}