import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ProductManagement {
    
    private Scanner scanner = new Scanner(System.in);

    public List<Product> initiateProductList() {
        List<Product> products = new ArrayList<>();
        
        System.out.print("Enter number of products to add: ");
        int n = Integer.parseInt(scanner.nextLine().trim());
        
        for (int i = 1; i <= n; i++) {
            System.out.println("\n--- Product " + i + " ---");
            
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            
            System.out.print("Enter original price: ");
            double price = Double.parseDouble(scanner.nextLine().trim());
            
            System.out.print("Enter category: ");
            String category = scanner.nextLine();
            
            System.out.print("Enter percent discount: ");
            double discount = Double.parseDouble(scanner.nextLine().trim());
            
            products.add(new Product(name, price, category, discount));
        }
        
        return products;
    }

    public List<Product> getProductWithMinSalePrice(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return new ArrayList<>();
        }
        
        double minSalePrice = products.stream()
                .mapToDouble(Product::getSalePrice)
                .min()
                .orElse(0);
        
        return products.stream()
                .filter(p -> p.getSalePrice() == minSalePrice)
                .collect(Collectors.toList());
    }

    public long countProducts(List<Product> products) {
        if (products == null || products.isEmpty()) {
            return 0;
        }
        
        return products.stream()
                .filter(p -> p.getOriginalPrice() > 1000)
                .count();
    }
}
