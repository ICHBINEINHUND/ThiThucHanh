public class Product {
    private String name;
    private double originalPrice;
    private String category;
    private double percentDiscount;

    public Product() {
    }

    public Product(String name, double originalPrice, String category, double percentDiscount) {
        this.name = name;
        this.originalPrice = originalPrice;
        this.category = category;
        this.percentDiscount = percentDiscount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(double percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public double getSalePrice() {
        return originalPrice - (originalPrice * percentDiscount / 100);
    }

    @Override
    public String toString() {
        return String.format("Product{name='%s', originalPrice=%.2f, category='%s', percentDiscount=%.2f%%, salePrice=%.2f}",
                name, originalPrice, category, percentDiscount, getSalePrice());
    }
}
