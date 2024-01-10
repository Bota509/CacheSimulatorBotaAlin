package body;

import java.math.BigInteger;

public class Block {

    public BigInteger tag;
    public  boolean isDirty;
    public boolean isValid;

   public Block()
    {
        tag = BigInteger.valueOf(0);
        isDirty = false;
        isValid = true;
    }


    public BigInteger getTag() {
        return tag;
    }

    public void setTag(BigInteger tag) {
        this.tag = tag;
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void setDirty(boolean dirty) {
        isDirty = dirty;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
