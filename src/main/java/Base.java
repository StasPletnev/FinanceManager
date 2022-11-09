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

    public void spendMoneyFood(int sum) {
        maxFood.spendMoney(sum);
    }

    public void spendMoneyClothes(int sum) {
        maxClothes.spendMoney(sum);
    }

    public void spendMoneyFinance(int sum) {
        maxFinance.spendMoney(sum);
    }

    public void spendMoneyOther(int sum) {
        other.spendMoney(sum);
    }

    public Object typeResponse(String type) {
        if (type.equals("еда")) {
            return maxFood;
        } else if (type.equals("одежда")) {
            return maxClothes;
        } else if (type.equals("финансы")) {
            return maxFinance;
        } else {
            return other;
        }
    }

}
