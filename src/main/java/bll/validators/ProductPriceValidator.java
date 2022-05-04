package bll.validators;

import model.Product;

public class ProductPriceValidator implements Validator<Product> {
    private static final double MIN_PRICE = 0;
    private static final double MAX_PRICE = 10000;

    public void validate(Product t) {

        if (t.getPrice() < MIN_PRICE || t.getPrice() > MAX_PRICE) {
            throw new IllegalArgumentException("The Product Price limit is not respected!");
        }

    }
}
