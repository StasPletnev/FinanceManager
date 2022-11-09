public class Response {
    protected String name;
    protected int sum;

    public Response(String name, int sum) {
        this.name = name;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Response{" +
                "name='" + name + '\'' +
                ", sum=" + sum +
                '}';
    }
}
