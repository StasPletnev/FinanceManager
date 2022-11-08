public class MaxClothes {
    protected String name;
    protected int sum;

    public MaxClothes(String name, int sum) {
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
