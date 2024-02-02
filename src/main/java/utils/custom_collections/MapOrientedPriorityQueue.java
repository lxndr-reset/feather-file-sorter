package utils.custom_collections;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class MapOrientedPriorityQueue<T, V> extends PriorityQueue<T> {
    private Map<T, V> orienteer;

    public MapOrientedPriorityQueue(Comparator<? super T> comparator) {
        super(comparator);
        this.orienteer = new HashMap<>();
    }

    public Map<T, V> getOrienteer() {
        return orienteer;
    }

    public void setOrienteer(Map<T, V> orienteer) {
        this.orienteer = orienteer;
    }

    @Override
    public boolean add(T t) {
        return super.add(t);
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return super.containsAll(c);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public <T1> T1[] toArray(IntFunction<T1[]> generator) {
        return super.toArray(generator);
    }

    @Override
    public Stream<T> stream() {
        return super.stream();
    }

    @Override
    public Stream<T> parallelStream() {
        return super.parallelStream();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean offer(T t) {
        return super.offer(t);
    }

    @Override
    public T peek() {
        return super.peek();
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return super.toArray(a);
    }

    @Override
    public Iterator<T> iterator() {
        return super.iterator();
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public T poll() {
        return super.poll();
    }

    @Override
    public Comparator<? super T> comparator() {
        return super.comparator();
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return super.removeIf(filter);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return super.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return super.retainAll(c);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        super.forEach(action);
    }
}
