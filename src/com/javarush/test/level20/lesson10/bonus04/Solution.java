package com.javarush.test.level20.lesson10.bonus04;

import java.io.*;
import java.util.*;

/* Свой список
Посмотреть, как реализован LinkedList.
Элементы следуют так: 1->2->3->4  и так 4->3->2->1
По образу и подобию создать Solution.
Элементы должны следовать так:
1->3->7->15
    ->8...
 ->4->9
    ->10
2->5->11
    ->12
 ->6->13
    ->14
Удалили 2 и 9
1->3->7->15
    ->8
 ->4->10
Добавили 16,17,18,19,20 (всегда добавляются на самый последний уровень к тем элементам, которые есть)
1->3->7->15
       ->16
    ->8->17
       ->18
 ->4->10->19
        ->20
Удалили 18 и 20
1->3->7->15
       ->16
    ->8->17
 ->4->10->19
Добавили 21 и 22 (всегда добавляются на самый последний уровень к тем элементам, которые есть.
Последний уровень состоит из 15, 16, 17, 19. 19 последний добавленный элемент, 10 - его родитель.
На данный момент 10 не содержит оба дочерних элемента, поэтому 21 добавился к 10. 22 добавляется в следующий уровень.)
1->3->7->15->22
       ->16
    ->8->17
 ->4->10->19
        ->21

Во внутренней реализации элементы должны добавляться по 2 на каждый уровень
Метод getParent должен возвращать элемент, который на него ссылается.
Например, 3 ссылается на 7 и на 8, т.е.  getParent("8")=="3", а getParent("13")=="6"
Строки могут быть любыми.
При удалении элемента должна удаляться вся ветка. Например, list.remove("5") должен удалить "5", "11", "12"
Итерироваться элементы должны в порядке добавления
Доступ по индексу запрещен, воспользуйтесь при необходимости UnsupportedOperationException
Должно быть наследование AbstractList<String>, List<String>, Cloneable, Serializable
Метод main в тестировании не участвует
*/
public class Solution
    extends AbstractList<String>
    implements List<String>, Cloneable, Serializable
{
    public static void main(String[] args) {
        List<String> list = new Solution();
        for (int i = 1; i < 16; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
        list.remove("5");
        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));

        for (String n : list)
            System.out.print(n + " ");
    }

    int size = 0;
    boolean hasSecond = false;
    Node root;
    Node first;
    Node last;
    ArrayList<Node> beParents = new ArrayList<>();

    public Solution(){
        rootInit();
    }

    public void printSol(){
        for (String n : this){
            System.out.print(n + "<-" + this.getParent(n) + " L" + this.getLeft(n) + " R" + this.getRight(n) + "\t");
        }
        System.out.println("");
        System.out.println("size = " + this.size());
        for (Node s : this.beParents)
            System.out.print(s.item + " . ");
        System.out.println("");
    }

    @Override
    public int size()
    {
        return size;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Solution strings = (Solution) o;

        if (size != strings.size) return false;
        if (hasSecond != strings.hasSecond) return false;
        if (first != null ? !first.equals(strings.first) : strings.first != null) return false;
        if (last != null ? !last.equals(strings.last) : strings.last != null) return false;
        return beParents != null ? beParents.equals(strings.beParents) : strings.beParents == null;
    }

    @Override
    public int hashCode()
    {
        int result = super.hashCode();
        result = 31 * result + size;
        result = 31 * result + (hasSecond ? 1 : 0);
        result = 31 * result + (first != null ? first.hashCode() : 0);
        result = 31 * result + (last != null ? last.hashCode() : 0);
        result = 31 * result + (beParents != null ? beParents.hashCode() : 0);
        return result;
    }
    //    @Override
//    public String get(int index)
//    {
//        new UnsupportedOperationException();
//        return null;
//    }

    private static class Node implements Serializable{
        String item;
        Node next;
        Node prev;
        Node parent;
        Node left;
        Node right;

        Node(Node prev, String element, Node next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
            this.parent = null;
            this.left = null;
            this.right = null;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (item != null ? !item.equals(node.item) : node.item != null) return false;
            if (next != null ? !next.equals(node.next) : node.next != null) return false;
            if (prev != null ? !prev.equals(node.prev) : node.prev != null) return false;
            if (parent != null ? !parent.equals(node.parent) : node.parent != null) return false;
            if (left != null ? !left.equals(node.left) : node.left != null) return false;
            return right != null ? right.equals(node.right) : node.right == null;
        }

        @Override
        public int hashCode()
        {
            int result = item != null ? item.hashCode() : 0;
            result = 31 * result + (next != null ? next.hashCode() : 0);
            result = 31 * result + (prev != null ? prev.hashCode() : 0);
            result = 31 * result + (parent != null ? parent.hashCode() : 0);
            result = 31 * result + (left != null ? left.hashCode() : 0);
            result = 31 * result + (right != null ? right.hashCode() : 0);
            return result;
        }
    }

    private void rootInit(){
        root = new Node(null, null, null);
        first = root;
        last = root;
    }

    public boolean add(String e) {
        linkLast(e);
        return true;
    }

    void linkLast(String e) {
        final Node l = last;
        final Node newNode = new Node(l, e, null);
        last = newNode;
        if (l == root){
            first = newNode;
            beParents.add(first);
            first.parent = null;
            first.prev = null;
            root.next = first;
            root.left = first;
        }else if (l == null){
            first = newNode;
            beParents.add(first);
            first.parent = null;
        }else{
            l.next = newNode;
            beParents.add(last);
            if (l == first && !hasSecond){
                root.right = last;
                hasSecond = true;
                beParents.remove(root);
            }else{
                Node bePar = beParents.get(0);
                last.parent = bePar;
                if (bePar.left == null)
                    bePar.left = last;
                else if (bePar.right == null){
                    bePar.right = last;
                    beParents.remove(0);
                }
            }
        }
        size++;
        modCount++;
    }

    public void clear(){
        remove(root);
        beParents.clear();
        rootInit();
    }

    public boolean remove(Object o) {
        if (o == null) {
            for (Node x = first; x != null; x = x.next){
                if (x.item == null){
                    unlink(x);
                    return true;
                }
            }
        }else if (o == root){
            size++;
            for (Node x = root; x != null; x = x.next)
            {
                if (x.item == null)
                {
                    unlink(x);
                    return true;
                }
            }
        }
        else {
            for (Node x = first; x != null; x = x.next){
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    String unlink(Node x) {
        int count = 0;
        final String element = x.item;
        final Node next = x.next;
        final Node prev = x.prev;
        if (last == x && !beParents.contains(x.parent) && x != root) beParents.add(0, x.parent);

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        if (x.parent == null){
            if (x == root.left)
                root.left = null;
            else if(x == root.right)
                root.right = null;
        }else if (x == x.parent.left){
            x.parent.left = x.parent.right;
            x.parent.right = null;
        }else x.parent.right = null;
        if (beParents.contains(x)) beParents.remove(x);
        if (x.right != null){
            unlink(x.right);
            x.right = null;
        }
        if (x.left != null){
            unlink(x.left);
            x.left = null;
        }
        return element;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }


@Override
    public Iterator<String> iterator() {
    checkPositionIndex(0);
    return new ListItr();
    }

    private class ListItr implements ListIterator<String> {
        private Node lastReturned;
        private Node next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr() {
            next = (0 == size) ? null : node(0);
            nextIndex = 0;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public String next() {
            checkForComodification();
            if (!hasNext())
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.item;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public String previous() {
            checkForComodification();
            if (!hasPrevious())
                throw new NoSuchElementException();

            lastReturned = next = (next == null) ? last : next.prev;
            nextIndex--;
            return lastReturned.item;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void remove() {
            checkForComodification();
            if (lastReturned == null)
                throw new IllegalStateException();

            Node lastNext = lastReturned.next;
            unlink(lastReturned);
            if (next == lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = null;
            expectedModCount++;
            expectedModCount = modCount;

        }

        public void set(String e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            checkForComodification();
            lastReturned.item = e;
        }

        public void add(String e) {
            checkForComodification();
            lastReturned = null;
            linkLast(e);
            nextIndex++;
            expectedModCount++;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }

    Node node(int index) {
        if (index < (size >> 1)) {
            Node x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    public String getParent(String value) {
        if (value == null || first == null) return null;
        Node next = first;
        if (value.equals(first.item)) return null;
        while(!value.equals(next.item)){
            if (next == last) return null;
            next = next.next;
        }
        if (next.parent != null)
            return next.parent.item;
        return null;
    }

    String getLeft(String value) {
        if (value == null || first == null) return null;
        Node next = first;
        if (value.equals(first.item))
            if (first.left == null) return null;
            else return first.left.item;
        while(!value.equals(next.item)){
            if (next == last) return null;
            next = next.next;
        }
        if (next.left != null)
            return next.left.item;
        return null;
    }

    String getRight(String value) {
        if (value == null || first == null) return null;
        Node next = first;
        if (value.equals(first.item))
            if (first.right == null) return null;
            else return first.right.item;
        while(!value.equals(next.item)){
            if (next == last) return null;
            next = next.next;
        }
        if (next.right != null)
            return next.right.item;
        return null;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node x = first; x != null; x = x.next) {
                if (o.equals(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    public String get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

@Override
    public Solution clone() {
        Solution clone = new Solution();
        byte[] bytes = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos))
        {
            oos.writeObject(this);
            bytes = baos.toByteArray();
        }catch (Exception exc){exc.printStackTrace();}

        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais))
        {
            clone = (Solution) ois.readObject();
        }catch (Exception exc){exc.printStackTrace();}
        return clone;
    }
}
