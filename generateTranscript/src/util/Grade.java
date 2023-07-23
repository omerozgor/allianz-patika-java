package util;

public enum Grade {
    A("A",4),
    B("B",3),
    C("C",2),
    D("D",1),
    F("F",0);

    public final String stringValue;
    public final int numericValue;

    Grade(String stringValue, int numericValue) {
        this.stringValue = stringValue;
        this.numericValue = numericValue;
    }

    @Override
    public String toString() {
        return "Grade" +
                " " + stringValue  +
                " corresponds to numeric grade " + numericValue + ".";
    }
}
