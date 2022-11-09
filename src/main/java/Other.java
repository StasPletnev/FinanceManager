public class Other {
    protected String name;
    protected int sum;

    public Other(String name, int sum) {
        this.name = name;
        this.sum = sum;
    }

    public void spendMoney(int sum) {
        this.sum -= sum;
    }

    public int getSum() {
        return sum;
    }
}
