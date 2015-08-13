class Status {
    int statVal;
    final int max;
    StatBar bar;
    
    /**
     * Creates a new Status, initially at max/max.
     */
    public Status(int max) {
        this(max, max);
    }
    
    public Status(int max, int init) {
        this.max = max;
        this.bar = new StatBar(max);
        setVal(init);
    }
    
    public int getVal() {
        return statVal;
    }
    
    public StatBar getStatBar() {
        return bar;
    }
    
    public void setVal(int val) {
        if(val < 0) val = 0;
        if(val > max) val = max;
        statVal = val;
        
        bar.update(statVal);
    }
    
    public void decrement() {
        if(statVal > 0) statVal--;
        bar.update(statVal);
    }
    
    public void increment() {
        if(statVal < max) statVal++;
        bar.update(statVal);
    }
}