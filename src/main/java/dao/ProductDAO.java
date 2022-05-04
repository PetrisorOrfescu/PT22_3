package dao;


import model.Product;

import java.util.List;

public class ProductDAO extends AbstractDAO<Product> {
    public Product findById(int id) {
        return super.findById(id);
    }
    public List<Product> findAll(){
        return super.findAll();
    }
    public Product deleteByID(int id){
        return super.deleteByID(id);
    }

    public Product insert(Product product)
    {
        return super.insert(product);
    }
}
