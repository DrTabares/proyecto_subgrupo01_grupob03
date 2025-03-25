package proyectoSubgrupo01;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Arrays;

public class GenerateInfoFilesSB01 {

    public static void main(String[] args) {
        try {
            List<String> sellers = loadSellersFromFile("salespeople.txt"); // Get sellers from default list in initial .txt file
            createProductsFile(10); // Generate products, the 10 is modified depending on the inventory
            
            // Generate sales files for each salesperson in the list
            for (String seller : sellers) {
                String[] data = seller.split(";");
                createSalesMenFile(5, data[2] + " " + data[3], Long.parseLong(data[1]));
            }
            
            System.out.println("The files were generated successfully.");
        } catch (IOException e) {
            System.out.println("Error generating files: " + e.getMessage());
        }
    }

    public static List<String> loadSellersFromFile(String filePath) throws IOException {
        List<String> sellers = new ArrayList<>();
        File file = new File(filePath);
        
        if (!file.exists()) {
            throw new FileNotFoundException("the file " + filePath + " does not exist.");
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
        	sellers.add(line);
        }
        reader.close();
        
        return sellers;
    }
    
    // This method generates the quantity of products sold by each salesperson.

    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {
        String fileName = "sales_" + id + ".txt";
        FileWriter writer = new FileWriter(fileName);
        writer.write("CC;" + id + ";" + name + "\n");
        writer.write("ProductID;" + "Sales Product Quantity" + "\n");

        Random random = new Random();
        for (int i = 0; i < randomSalesCount; i++) {
            int productId = random.nextInt(10) + 1;
            int quantity = random.nextInt(5) + 1;
            writer.write(productId + ";" + quantity + "\n");
        }

        writer.close();
    }
    
    // This method generates the file containing the list of products with their price and ID.

    public static void createProductsFile(int productsCount) throws IOException {
        FileWriter writer = new FileWriter("products.txt");

        List<String> productNames = Arrays.asList(
                "Jeans", "T-shirts", "Shoes", "Hats", "Sunglasses",
                "Shirts", "Flip flops", "Jackets", "Accesories", 
                "Dresses"
            );

            Random random = new Random();
            for (int i = 1; i <= productsCount; i++) {
                String productName = productNames.get(random.nextInt(productNames.size()));
                int price = (random.nextInt(50) + 1) * 1000; 
                writer.write(i + ";" + productName + ";" + price + "\n");
            }

        writer.close();
    }
}
