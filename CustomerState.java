public enum CustomerState{
    BUYING ("B"), PAYING ("P"), LEAVING ("L");
    
    String abbrev;
    
    CustomerState(String s) {
        abbrev = s;
    }
    
    public String getAbbrev() {
        return abbrev;
    }
}