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
    public static void main(String[] args) throws IOException, ClassNotFoundException, CloneNotSupportedException
    {
        List<String> list = new Solution();
        Solution list2 = null;
        for (int i = 1; i < 16; i++)
        {
            list.add(String.valueOf(i));
        }
        Solution tt = (Solution)((Solution) list).clone();
        System.out.println("List:");
        ((Solution) list).printSol();
        System.out.println("Clone:");
        tt.printSol();
        System.out.println("---------------------------------");
        System.out.println("");

        list.remove("7");
        System.out.println("list.remove(\"7\")\t7, 15");
        System.out.println("List:");
        ((Solution) list).printSol();
        System.out.println("Clone:");
        tt.printSol();
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println("");

        try(
                FileOutputStream fos = new FileOutputStream("c:\\2204");
                ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            System.out.println("WRITE");
            oos.writeObject(list);
        }catch (Exception exc) {exc.printStackTrace();}

        list.remove("2");
        System.out.println("list.remove(\"2\")\t2, 5, 6, 11, 12, 13, 14");
        System.out.println("List:");
        ((Solution) list).printSol();
        System.out.println("Clone:");
        tt.printSol();
        System.out.println("---------------------------------");
        System.out.println("");

        tt.remove("1");
        System.out.println("tt.remove(\"1\")\t1, 3, 4, 7, 8, 9, 10, 15");
        System.out.println("List:");
        ((Solution) list).printSol();
        System.out.println("Clone:");
        tt.printSol();
        System.out.println("---------------------------------");
        System.out.println("");

        list.remove("7");
        System.out.println("list.remove(\"7\") no changes");
        System.out.println("List:");
        ((Solution) list).printSol();
        System.out.println("Clone:");
        tt.printSol();
        System.out.println("---------------------------------");
        System.out.println("");

        list.remove("2");
        System.out.println("list.remove(\"2\") no changes");
        System.out.println("List:");
        ((Solution) list).printSol();
        System.out.println("Clone:");
        tt.printSol();
        System.out.println("---------------------------------");
        System.out.println("");

        list.remove("5");
        System.out.println("list.remove(\"5\") no changes");
        System.out.println("List:");
        ((Solution) list).printSol();
        System.out.println("Clone:");
        tt.printSol();
        System.out.println("---------------------------------");
        System.out.println("");

        try(
                FileInputStream fis = new FileInputStream("c:\\2204");
                ObjectInputStream ois = new ObjectInputStream(fis))
        {
            list2 = (Solution)ois.readObject();
        }catch (Exception exc) {exc.printStackTrace();}
        System.out.println("\nREAD\n");
        System.out.println("List2:");
        ((Solution) list2).printSol();
        System.out.println("Clone:");
        System.out.println("---------------------------------");
        System.out.println("");

        System.out.println("=============== Iterator test ===============");
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            String a = itr.next();
            System.out.print(a + " ");
        }
        System.out.println("\nExpected size 6 = " + list.size());

        System.out.println("\nIter remove 4");
        Iterator<String> itr2 = list.iterator();
        while (itr2.hasNext()) {
            if (itr2.next().contains("4")) {
                itr2.remove();
            }
        }

        Iterator<String> itr3 = list.iterator();
        while (itr3.hasNext()) {
            String a = itr3.next();
            System.out.print(a + " ");
        }
        System.out.println("\nCLEAR\n");
        list2.clear();
        System.out.println("List2:");
        ((Solution) list2).printSol();
        System.out.println("---------------------------------");
        System.out.println("");
        System.out.println(list2.beParents);
    }

//    public static void main(String[] args) {
//        List<String> list = new Solution();
//        for (int i = 1; i < 16; i++) {
//            list.add(String.valueOf(i));
//        }
//        System.out.println("Expected 3, actual is " + ((Solution) list).getParent("8"));
//        list.remove("5");
//        System.out.println("Expected null, actual is " + ((Solution) list).getParent("11"));
//
//        for (String n : list)
//            System.out.print(n + " ");
//    }

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
        final String element = x.item;
        final Node next = x.next;
        final Node prev = x.prev;

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
        if (last == x && !beParents.contains(x.parent) && x != root) beParents.add(0, x.parent);
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

    void linkBefore(String e, Node succ) {
        // assert succ != null;
        final Node pred = succ.prev;
        final Node newNode = new Node(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
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
        return listIterator(0);
    }

    public ListIterator<String> listIterator(int index) {
        checkPositionIndex(index);
        return new ListItr(index);
    }
    private class ListItr implements ListIterator<String> {
        private Node lastReturned;
        private Node next;
        private int nextIndex;
        private int expectedModCount = modCount;

        ListItr(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
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
            if (next == null)
                linkLast(e);
            else
                linkBefore(e, next);
            nextIndex++;
            expectedModCount++;
        }

        final void checkForComodification() {
            if (false)
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
        if (value.equals(first.item)) return first.left.item;
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
        if (value.equals(first.item)) return first.right.item;
        while(!value.equals(next.item)){
            if (next == last) return null;
            next = next.next;
        }
        if (next.right != null)
            return next.right.item;
        return null;
    }

    Node find (String str){
        if (str == null || first == null) return null;
        Node next = first;
        while (next != null){
            if (str.equals(next.item)) return next;
            next = next.next;
        }
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
    public Object clone() {
        Solution clone = new Solution();
//        if (size() == 0) {return clone;}
//        Node nextNode = first;
//        Node prev;
//        Node next;
//        Node par;
//        Node left;
//        Node right;
//        clone.first = new Node(null, first.item, null);
//        if (size() > 1)
//        {
//            prev = clone.first;
//            next = clone.first;
//            do
//            {
//                left = nextNode.left;
//                right = nextNode.right;
//                nextNode = nextNode.next;
//                par = nextNode.parent;
//                clone.last = new Node(prev, nextNode.item, null);
//                if (par == null) clone.last.parent = null;
//                else clone.last.parent = clone.find(par.item);
//                clone.last.prev.next = clone.last;
//                prev = clone.last;
//            }
//            while (nextNode != last);
//            for (Node n : beParents)
//                while (next != null)
//                {
//                    if (n.item.equals(next.item))
//                        clone.beParents.add(next);
//                    if (next.next != null)
//                        next = next.next;
//                    else
//                    {
//                        next = clone.first;
//                        break;
//                    }
//                }
//            next = clone.first;
//            nextNode = first;
//            while (nextNode != null){
//                if (nextNode.left == null) next.left = null;
//                else next.left = clone.find(nextNode.left.item);
//                if (nextNode.right == null) next.right = null;
//                else next.right = clone.find(nextNode.right.item);
//                next = next.next;
//                nextNode = nextNode.next;
//            }
//        }else {
//            clone.first.item = first.item;
//            clone.last = clone.first;
//            clone.hasSecond = hasSecond;
//            clone.size = size;
//            clone.root = new Node(null, null, clone.first);
//            clone.root.left = clone.first;
//            return clone;
//        }
//        clone.hasSecond = hasSecond;
//        clone.size = size;
//        clone.root = new Node(null, null, clone.first);
//        clone.root.left = clone.first;
//        if (root.right != null) clone.root.right = clone.first.next;
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
