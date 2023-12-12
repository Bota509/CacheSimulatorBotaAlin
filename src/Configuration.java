public class Configuration {
    public final int blockSize;
    public final WritePolicy writeHitPolicy;
    public final int associativity;

    public Configuration(int blockSize, WritePolicy writeHitPolicy, int associativity) {
        this.blockSize = blockSize;
        this.writeHitPolicy = writeHitPolicy;
        this.associativity = associativity;
    }

}
