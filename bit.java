public class bit implements bitInterface {
    private boolean state;
    public bit(boolean state){
        this.state = state;
    }

    public bit(){
        this.state = false;
    }

    public void set(boolean value){
        this.state = value;
    }

    //set state to false or true depending on initial value to toggle
    public void toggle(){
        if(this.state == true){
            this.state = false;
        } else{
            this.state = true; 
        }
    }

    public void set(){
        this.state = true;
    }

    public void clear(){
        this.state = false;
    }

    public boolean getValue(){
        return this.state;
    }

    public bit and(bit other){
        if(this.getValue() == false){
            //And returns false with one false value. If either are false, return a false value
            return new bit(false);
        }
        else if(other.getValue() == false){
            return new bit(false);
        } else{
            return new bit(true);
        }
    }

    public bit or(bit other){
        //if one is false check if the other is false
        if(this.getValue() == false){
            if(other.getValue() == false){
                return new bit(false);
            }
        }
        return new bit(true);
    }

    public bit xor(bit other){
        //result must be false with the same boolean value no matter what
        if(this.getValue() == other.getValue()){
            return new bit(false);
        } 
        else{
            //otherwise, return a new bit
            return new bit(true);
        }
    }

    public bit not(){
        bit bitRef = new bit(this.state);
        //toggling a reference to "this" bit in order to keep from changing its value
        bitRef.toggle();
        return new bit(bitRef.getValue());
    }

    @Override
    public String toString(){
        if(this.state == false){
            System.out.println("State is false. ");
            return "0";
        } else{
            System.out.println("State is true. ");
            return "1";
        }
    }
}
