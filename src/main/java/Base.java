public class Base {
    MaxFood maxFood;
    MaxClothes maxClothes;
    MaxFinance maxFinance;
    Other other;

    public Base(MaxFood maxFood, MaxClothes maxClothes, MaxFinance maxFinance, Other other) {
        this.maxFood = maxFood;
        this.maxClothes = maxClothes;
        this.maxFinance = maxFinance;
        this.other = other;
    }

    public void spendMoneyFood(int sum){
        maxFood.spendMoney(sum);
    }
    public void spendMoneyClothes(int sum){
        maxClothes.spendMoney(sum);
    }
    public void spendMoneyFinance(int sum){
        maxFinance.spendMoney(sum);
    }
    public void spendMoneyOther(int sum){
        other.spendMoney(sum);
    }

}
