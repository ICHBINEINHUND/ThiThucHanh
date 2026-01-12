import java.util.List;

public class Question3_Main {
    
    public static void main(String[] args) {
        System.out.println("=== Product Management Application ===\n");
        
        ProductManagement pm = new ProductManagement();
        
        System.out.println("1. Enter products:");
        List<Product> products = pm.initiateProductList();
        
        System.out.println("\n========== ALL PRODUCTS ==========");
        System.out.printf("%-35s %-10s %-10s %-10s %-10s%n", 
                          "Name", "Original", "Category", "Discount", "SalePrice");
        System.out.println("------------------------------------------------------------");
        
        for (Product p : products) {
            System.out.printf("%-35s $%-9.0f %-10s %-9.0f%% $%-9.0f%n",
                    p.getName(),
                    p.getOriginalPrice(),
                    p.getCategory(),
                    p.getPercentDiscount(),
                    p.getSalePrice());
        }
        
        System.out.println("\n------------------------------------------------------------");
        long count = pm.countProducts(products);
        System.out.println("Products count (original price > $1000): " + count);
        
        System.out.println("\n------------------------------------------------------------");
        System.out.println("Products with min sale price:");
        List<Product> minPriceProducts = pm.getProductWithMinSalePrice(products);
        
        for (Product p : minPriceProducts) {
            System.out.println("Name = " + p.getName());
        }
        
        System.out.println("\n=== Completed! ===");
    }
}
