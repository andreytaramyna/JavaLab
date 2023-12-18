public class Eggs extends Food{
    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    private String Number;
    public Eggs(String number) {
        super("Яйцо моё");
        this.Number = number;
    }
    @Override
    public boolean equals(Object arg0) {
        if (super.equals(arg0)) { // Шаг 1
            if (!(arg0 instanceof Eggs)) return false; // Шаг 2
            return Number.equals(((Eggs)arg0).Number); // Шаг 3
        } else
            return false;
    }

    @Override
    public void consume() {
        System.out.println(  this.toString()+  " съедено");
    }
    @Override
    public String toString(){
        return "Яиц в количестве " + Number;
    }
}