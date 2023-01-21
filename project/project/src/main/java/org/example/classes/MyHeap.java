package org.example.classes;


import java.util.ArrayList;

public class MyHeap{

    ArrayList<Entry> heap = new ArrayList<>();


    static class Entry{
        Integer key;
        User value;

        public Entry(Integer key , User value) {
            this.key = key;
            this.value = value;
        }

    }

    public int parent(int j){return (j-1)/2; }
    public int left(int j){return 2*j+1 ; }
    public int right(int j){return 2*j+2 ; }
    public boolean hasLeft(int j){return left(j) < heap.size();}
    public boolean hasRight(int j){return right(j) < heap.size();}



    public void swap (int i , int j){

        Entry temp = heap.get(i);
        heap.set(i , heap.get(j));
        heap.set(j , temp);

    }

    public void upHeap(int j){

        while (j>0){
            int p = parent(j);
            if (heap.get(j).key <= heap.get(p).key) break;
            swap(j , p);
            j=p;
        }
    }

    public void downHeap(int j){
        while (hasLeft(j)){
            int leftIndex = left(j);
            int bigChildIndex = leftIndex;

            if (hasRight(j)){
                int rightIndex = right(j);
                if (heap.get(leftIndex).key < heap.get(rightIndex).key)
                    bigChildIndex = rightIndex;
            }

            if (heap.get(bigChildIndex).key <= heap.get(j).key) break;
            swap(j , bigChildIndex);
            j = bigChildIndex;
        }
    }

    public User removeMax(){
        if (heap.isEmpty())
            return null;

        Entry answer = heap.get(0);

        swap( 0 , heap.size()-1);
        heap.remove(heap.size()-1);
        downHeap(0);
        return answer.value;
    }

    public Entry insert(Integer key , User value){
        Entry newest = new Entry(key , value);
        heap.add(newest);
        upHeap(heap.size()-1);
        return newest;
    }

    public boolean isEmpty(){
        if (heap.isEmpty())
            return true;
        return false;
    }
}