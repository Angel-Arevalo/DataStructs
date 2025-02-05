package Trees;

public class DisjoinSets {
    private int[] parents, ranks;

    public DisjoinSets(int length) 
    {
        parents = new int[length];
        ranks = new int[length];
        makeSet(length);
    }

    private void makeSet(int length) {
        for(int i = 0; i < length; i++)
        {
            parents[i] = i;
            ranks[i] = 0;
        }
    }

    public int find(int index) 
    {
        if(index < 0 || parents.length <= index) 
            throw new IllegalArgumentException("Index out of bount");

        while(index != parents[index])
            index = parents[index];

        return index;
    }

    public void cup(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if(parentX == parentY)
            return;

        if(ranks[parentX] > ranks[parentY]) 
        {
            parents[parentY] = parentX;
        }
        else
        {
            parents[parentX] = parentY;
            if(ranks[parentX] == ranks[parentY]) 
                ranks[parentY]++;
        }
    }

    public String toString() {
        StringBuffer toR = new StringBuffer("[");
        StringBuffer heigths = new StringBuffer("[");
        for(int i = 0; i < parents.length; i++)
        {
            int k = parents[i], j = ranks[i];
            toR.append(k+1);
            heigths.append(j);
            if(i != parents.length - 1)
            {
                toR.append(", ");
                heigths.append(", ");
            }
        }
        toR.append("]\n");
        heigths.append("]");
        return toR.append(heigths).toString();
    }
}
