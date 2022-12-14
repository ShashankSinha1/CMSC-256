package cmsc256;


import java.util.Iterator;

public class HashTableQuadraticProbing<K,V> extends HashTableOpenAddressing<K,V> {

    public int quadraticProbe(int index, K keyIn) {
        boolean found = false;
        int i = 0;
        int removedStateIndex = -1;

        if(table[index] == null) {
            return index;
        }
        while(!found && table[index] != null){
            if(table[index].isIn()) {
                if (keyIn == table[index].getKey())
                    found = true;
                else {
                        index = (index + i*i) % table.length;
                        i++;
                }
            }

            else{
                if(removedStateIndex == -1){
                    removedStateIndex = index;
                }
                index = (index + i*i) % table.length;
                i++;
            }
        }
        if (found || removedStateIndex == -1){
            return index;
        }
        else
            return removedStateIndex;

    }
    public V put(K keyIn, V valueIn){
        if(valueIn == null || keyIn == null){
            throw new IllegalArgumentException("Null value, try again");
        }

        V oldValue = null;
        Entry<K,V> entry = new Entry<>(keyIn, valueIn);
        int index = super.getHashIndex(keyIn);
        index = quadraticProbe(index, keyIn);

        if (table[index] == null || table[index].isRemoved()){
            table[index] = entry;
            numEntries++;
        }
        else{
            oldValue = table[index].getValue();
            table[index] = entry;
        }

        if(isFull()){
            enlargeHashTable();
        }
        return oldValue;
    }

    public V getValue(K keyIn){
        int index = super.getHashIndex(keyIn);
        index = quadraticProbe(index, keyIn);
        Entry<K, V> item = table[index];
        if((item != null) && item.isIn()){
            return item.getValue();
        }
        return null;
    }

    public boolean contains(K keyIn) {
        return getValue(keyIn)!= null;
    }


    public V remove(K keyIn){
        V removedValue = null;
        int index = super.getHashIndex(keyIn);
        index = quadraticProbe(index, keyIn);

        if(index != -1 && table[index]!= null){
            removedValue = table[index].getValue();
            table[index].setToRemoved();
            numEntries--;
        }
        return removedValue;
    }
    public static void main(String[] args){
        HashTableQuadraticProbing<String, Integer> purchases = new HashTableQuadraticProbing<>();

        String[] names = {"Pax", "Eleven", "Angel", "Abigail", "Jack"};
        purchases.put(names[0], 654);
        purchases.put(names[1], 341);
        purchases.put(names[2], 70);
        purchases.put(names[3], 867);
        purchases.put(names[4], 5309);

        System.out.println("Contents with quadratic probing:\n" + purchases.toString());

        System.out.println("Replaced old value was " + purchases.put(names[1], 170));
        System.out.println("Contents after changing Eleven to 170:\n" + purchases.toString());

        System.out.println("Calling getValue() on Pax, Eleven, & Angel:");
        System.out.println("\tPax: " + purchases.getValue(names[0]));
        System.out.println("\tEleven: " + purchases.getValue(names[1]));
        System.out.println("\tAngel: " + purchases.getValue(names[2]));

        purchases.remove(names[0]);
        purchases.remove(names[2]);
        System.out.println("Contents after removing Pax & Angel:\n" + purchases);

        purchases.put("Gino", 348);
        System.out.println("Contents after adding Gino:\n" + purchases);

        Iterator<String> keyIter = purchases.getKeyIterator();
        Iterator<Integer> valueIter = purchases.getValueIterator();
        System.out.println("Contents of the hash table:");
        while(keyIter.hasNext())
            System.out.println("Key-" + keyIter.next() + " : Value-" + valueIter.next());
    }

}
