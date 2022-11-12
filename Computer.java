public class Computer {
    private bit runningState =  new bit();
    private Memory internalMemory =  new Memory();

    public void fetch(){}
    public void decode(){}
    public void execute(){}
    public void store(){}

    //run cycle
    public void run(){
        while(runningState.getValue()){
            fetch();
            decode();
            execute();
            store();
        }
    }
}
