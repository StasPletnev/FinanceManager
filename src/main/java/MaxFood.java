public class MaxFood {
    protected String name;
    protected int sum;

    public MaxFood(String name, int sum) {
        this.name = name;
        this.sum = sum;
    }

    public void spendMoney(int sum){
        this.sum = this.sum - sum;
    }

    public int getSum() {
        return sum;
    }
}
