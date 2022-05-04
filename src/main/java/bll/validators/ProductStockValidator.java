package bll.validators;

import model.Product;

public class ProductStockValidator implements Validator<Product> {
    private static final int MIN_STOCK= 0;
    private static final int MAX_STOCK = 10000;

    public void validate(Product t) {

        if (t.getStock() < MIN_STOCK || t.getPrice() > MAX_STOCK) {
            throw new IllegalArgumentException("The Product Stock limit is not respected!");
        }

    }
}