package body;

import Enums.Replacement;
import Enums.WritePolicy;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

public class Cache {


    WritePolicy writePolicy;
    Replacement replacement;


    public double numMisses = 0;
    public double numReads = 0;
    public double numWrites = 0;
    public double numHits = 0;

    private int blockSize;
    private int associativity;
    private int numSets;
    public int cacheSize;
    Block[][] blocks;
    ArrayList<LinkedList<Integer>> metadata;

    public Cache(int associativity, int cacheSize, WritePolicy writePolicy, Replacement replacement,int blockSize)
    {
        this.associativity = associativity;
        this.blockSize = blockSize;
        this.cacheSize = cacheSize;
        this.numSets = cacheSize / (blockSize * associativity);
        this.blocks = new Block[numSets][associativity];
        this.metadata = new ArrayList<LinkedList<Integer>>();
        this.writePolicy = writePolicy;
        this.replacement = replacement;

        // Initialize blocks.
        for (int i = 0; i < blocks.length; i++)
            for (int j = 0; j < blocks[i].length; j++)
                blocks[i][j] = new Block();

        // Initialize metadata.
        for (int i = 0; i < numSets; i++)
            metadata.add(new LinkedList<Integer>());
    }

    // Finds a free cache block to be used.
    public int getFreeBlock(int setNumber)
    {
        LinkedList<Integer> set = metadata.get(setNumber);

        // Check if there is a free block.
        for (int i = 0; i < associativity; i++)
            if (blocks[setNumber][i].isValid)  //if is empty -> to modify
                return i;

        int i = set.remove();
        return i;
    }

    // Returns the index for the given tag. Returns -1 if the tag is not in the cache.
    public int indexOf(BigInteger tag, int setNumber)
    {
        for (int i = 0; i < associativity; i++)
            if (blocks[setNumber][i].tag != null && blocks[setNumber][i].tag.compareTo(tag) == 0)
                return i;

        return -1;
    }

    // Reads a tag from the cache in the specified set.
    public void read(BigInteger tag, int setNumber)
    {
        int index = indexOf(tag, setNumber);

        // Check for a hit.
        if (index != -1)
        {
            updateMetadata(setNumber, index);
            numHits++;
        }

        // Check for a miss.
        else
        {
            numMisses++;
            index = getFreeBlock(setNumber);
            Block block = blocks[setNumber][index];

            block.tag = tag;
            block.isValid = false;
            numReads++;

            if (writePolicy == WritePolicy.WRITE_BACK)
            {
                if (block.isDirty)
                    numWrites++;

                block.isDirty = false;
            }

            updateMetadata(setNumber, index);
        }
    }

    // Updates the cache metadata according to the specified replacement policy.
    public void updateMetadata(int setNumber, int index)
    {
        LinkedList<Integer> set = metadata.get(setNumber);

        // Check if the queue is empty.
        if (set.size() == 0)
        {
            set.add(index);
        }
        else
        {
            if (replacement == Replacement.LRU)
            {
                int targetIndex = set.indexOf(index);

                if (targetIndex != -1)
                    set.remove(targetIndex);
            }

            set.add(index);
        }
    }

    // Writes a tag to the cache in the specified set.
    public void write(BigInteger tag, int setNumber)
    {
        Block block;
        int index = indexOf(tag, setNumber);

        // Check for a hit.
        if (index != -1)
        {
            block = blocks[setNumber][index];

            block.tag = tag;
            block.isValid = false;
            numHits++;
            // Check the replacement policy.
            switch (writePolicy)
            {
                case WRITE_THROUGH:
                    //writes in memory directly
                    numWrites++;
                    break;

                case WRITE_BACK:
                    //sets the isDirty bit to true and write to memory when the block is replaced or evicted
                    block.isDirty = true;
                    break;
            }
            updateMetadata(setNumber, index);
        }

        // Check for a miss.
        else
        {
            numMisses++;
            index = getFreeBlock(setNumber);
            block = blocks[setNumber][index];
            block.tag = tag;
            block.isValid = false;
            numReads++;

            // Check the replacement policy.
            switch (writePolicy)
            {
                case WRITE_THROUGH:
                    numWrites++;
                    break;

                case WRITE_BACK:
                    if (block.isDirty)
                        numWrites++;
                    blocks[setNumber][index].isDirty = true;
                    break;
            }

            updateMetadata(setNumber, index);
        }
    }

    public double getNumMisses() {
        return numMisses;
    }
    public double getNumReads() {
        return numReads;
    }
    public int getNumOfSets() {
        return numSets;
    }
    public double getNumHits()
    {
        return numHits;
    }
    public double getNumWrites() {
        return numWrites;
    }

}