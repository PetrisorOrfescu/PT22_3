package bll;

import bll.validators.ProductPriceValidator;
import bll.validators.ProductStockValidator;
import bll.validators.Validator;
import dao.ProductDAO;
import model.Client;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new ProductPriceValidator());
        validators.add(new ProductStockValidator());
        productDAO = new ProductDAO();
    }

    public Product findProductById(int id) {
        Product ct = productDAO.findById(id);
        if (ct == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return ct;
    }
    public List<Product> findAll(){
        return productDAO.findAll();
    }
    public Product deleteProductById(int id) {
        Product ct = productDAO.deleteByID(id);
        if (ct == null) {
            throw new NoSuchElementException("The Product with id =" + id + " was not found!");
        }
        return ct;
    }
    public Product insertProduct(Product product) {

        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        Product ct = productDAO.insert(product);
        if (ct == null) {
            throw new NoSuchElementException("The Product with id =" + product.getId() + " could not be inserted!");
        }
        return ct;

    }
}
