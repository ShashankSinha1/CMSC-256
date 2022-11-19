package cmsc256;

public class HashTableLinearProbing<K,V> extends HashTableOpenAddressing<K,V>{

    public int linearProbe(int index, K keyIn){
        boolean found = false;
        int removedStateIndex = -1;

        if(table[index] == null)
            return index;

        while(!found && table[index] != null){
            if(table[index].isIn()) {
                if (keyIn.equals(table[index].getKey()))
                    found = true;
                else
                    index = (index + 1) % table.length;
            }

            else{
                if(removedStateIndex == -1){
                    removedStateIndex = index;
                }
                index = (index +1)% table.length;
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
            throw new IllegalArgumentException("Null input, try again.");
        }

        V oldValue = null;
        Entry<K,V> entry = new Entry<>(keyIn, valueIn);
        int index = super.getHashIndex(keyIn);
        index = linearProbe(index, keyIn);

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
        index = linearProbe(index, keyIn);
        Entry<K, V> item = table[index];
        if((item != null) && item.isIn()){
            return item.getValue();
        }
        return null;
    }

    @Override
    public boolean contains(K keyIn) {
        return getValue(keyIn)!= null;
    }

    public V remove(K keyIn){
        V removedValue = null;
        int index = super.getHashIndex(keyIn);
        index = linearProbe(index, keyIn);

        if(index != -1 && table[index]!= null){
            removedValue = table[index].getValue();
            table[index].setToRemoved();
            numEntries--;
        }
        return removedValue;
    }
}
