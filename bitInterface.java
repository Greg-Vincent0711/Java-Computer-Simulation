public interface bitInterface{
    void set(boolean value);
    void toggle();
    void set();
    void clear();
    boolean getValue();
    bit and(bit other);
    bit or(bit other);
    bit xor(bit other);
    bit not();
    String toString();
}