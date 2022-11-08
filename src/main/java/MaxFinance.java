public class MaxFinance {
    protected String name;
    protected int sum;

    public MaxFinance(String name, int sum) {
        this.name = name;
        this.sum = sum;
    }

    public void spendMoney(int sum){
        this.sum -= sum;
    }

    public int getSum() {
        return sum;
    }
}
