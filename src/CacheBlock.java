public class CacheBlock {
    private int tag;
    private int data;
    private boolean valid;


    public CacheBlock(int tag, int data, boolean valid) {
        this.tag = -1;
        this.data = 0;
        this.valid = false;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public void setData(int tag, int data) {
        this.tag = tag;
        this.data = data;
        this.valid = true;
    }

}
