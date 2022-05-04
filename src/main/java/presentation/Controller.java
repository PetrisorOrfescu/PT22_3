package presentation;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import model.Client;
import model.Orders;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.NoSuchElementException;

public class Controller {
    MainView mainView ;
    OrderManagerView orderManagerView;
    ClientManagerView clientManagerView;
    ProductManagerView productManagerView;
    public Controller(MainView view) {
        this.mainView = view;
        mainView.setVisible(true);
        mainView.jButton1ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.dispose();
                productManagerView = new ProductManagerView();
                DefaultTableModel tableModel = (DefaultTableModel)productManagerView.getjTable1().getModel();
                productManagerView.setVisible(true);
                productManagerView.jButton4ActionPerformed(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        productManagerView.dispose();
                        mainView.setVisible(true);
                    }
                });
                productManagerView.jButton1ActionPerformed(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s1,s2,s3,s4;
                        s1 = productManagerView.getjTextField1().getText();//id
                        s2 = productManagerView.getjTextField2().getText();//name
                        s3 = productManagerView.getjTextField3().getText();//price
                        s4 = productManagerView.getjTextField4().getText();//stock
                        if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty())
                        {
                            JOptionPane.showMessageDialog(productManagerView,"Please Input valid Data");
                        }
                        else
                        {
                            Product prod = new Product(Integer.parseInt(s1),s2,Double.parseDouble(s3),Integer.parseInt(s4));
                            ProductBLL productBLL = new ProductBLL();
                            try{
                                Product p = productBLL.findProductById(prod.getId());
                                JOptionPane.showMessageDialog(clientManagerView,"ID already in use");
                            }catch (IndexOutOfBoundsException exx) {
                                try{
                                    Product pins = productBLL.insertProduct(prod);
                                    JOptionPane.showMessageDialog(clientManagerView,"Product added successfully!");}
                                catch (IllegalArgumentException exception){
                                    JOptionPane.showMessageDialog(clientManagerView,"Please input valid data! (review price and/or stock)");
                                }
                            }
                        }
                        ProductBLL prodBll = new ProductBLL();
                        List<Product> prodList = prodBll.findAll();
                        tableModel.getDataVector().removeAllElements();
                        for(Product p:prodList){
                            String data[] ={String.valueOf(p.getId()),p.getName(),String.valueOf(p.getPrice()),String.valueOf(p.getStock())};
                            tableModel.addRow(data);
                        }
                    }
                });
                productManagerView.jButton3ActionPerformed(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s1 = productManagerView.getjTextField1().getText();
                        if(s1.isEmpty()){
                            JOptionPane.showMessageDialog(productManagerView,"Please input a valid ID");
                        }
                        else{
                            ProductBLL prodBll = new ProductBLL();
                            try{
                                Product product = prodBll.findProductById(Integer.parseInt(s1));
                                String s2 = productManagerView.getjTextField2().getText();//name
                                String s3 = productManagerView.getjTextField3().getText();//price
                                String s4 = productManagerView.getjTextField4().getText();//stock
                                Product newProduct = new Product();
                                newProduct.setId(product.getId());
                                if(s2.isEmpty())
                                    newProduct.setName(product.getName());
                                else
                                    newProduct.setName(s2);
                                if(s3.isEmpty())
                                    newProduct.setPrice(product.getPrice());
                                else
                                    newProduct.setPrice(Double.parseDouble(s3));
                                if(s4.isEmpty())
                                    newProduct.setStock(product.getStock());
                                else
                                    newProduct.setStock(Integer.parseInt(s4));
                                product = prodBll.deleteProductById(product.getId());
                                try{
                                    prodBll.insertProduct(newProduct);
                                    JOptionPane.showMessageDialog(productManagerView,"Product updated successfully");
                                }catch (Exception exception){
                                    JOptionPane.showMessageDialog(productManagerView,"Could not update product because of invalid data!");
                                    prodBll.insertProduct(product);
                                    exception.printStackTrace();
                                }
                            }catch (IndexOutOfBoundsException exception){
                                JOptionPane.showMessageDialog(productManagerView,"ID not found!");
                            }
                        }
                        ProductBLL prodBll = new ProductBLL();
                        List<Product> prodList = prodBll.findAll();
                        tableModel.getDataVector().removeAllElements();
                        for(Product p:prodList) {
                            String data[] = {String.valueOf(p.getId()), p.getName(), String.valueOf(p.getPrice()), String.valueOf(p.getStock())};
                            tableModel.addRow(data);
                        }

                    }
                });
                productManagerView.jButton2ActionPerformed(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s1 = productManagerView.getjTextField1().getText();
                        if(s1.isEmpty() ){
                            JOptionPane.showMessageDialog(productManagerView,"Please insert a valid ID");
                        }
                        else
                        {
                            int id = Integer.parseInt(s1);
                            try {
                                ProductBLL prodBll = new ProductBLL();
                                Product prod  = prodBll.deleteProductById(id);
                                JOptionPane.showMessageDialog(productManagerView, "Product deleted successfully");
                            }catch (NoSuchElementException exception){
                                JOptionPane.showMessageDialog(productManagerView,"ID not found");
                            }
                        }
                        ProductBLL prodBll = new ProductBLL();
                        List<Product> prodList = prodBll.findAll();
                        tableModel.getDataVector().removeAllElements();
                        for(Product p:prodList) {
                            String data[] = {String.valueOf(p.getId()), p.getName(), String.valueOf(p.getPrice()), String.valueOf(p.getStock())};
                            tableModel.addRow(data);
                        }
                    }
                });
            }
        });
        mainView.jButton2ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.dispose();
                clientManagerView = new ClientManagerView();
                clientManagerView.setVisible(true);
                DefaultTableModel tableModel = (DefaultTableModel)clientManagerView.getjTable1().getModel();
                clientManagerView.jButton4ActionPerformed(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clientManagerView.dispose();
                        mainView.setVisible(true);
                    }
                });
                clientManagerView.jButton1ActionPerformed(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s1,s2,s3,s4,s5;
                        s1 = clientManagerView.getjTextField1().getText();//id
                        s2 = clientManagerView.getjTextField2().getText();//name
                        s3 = clientManagerView.getjTextField3().getText();//address
                        s4 = clientManagerView.getjTextField4().getText();//email
                        s5 = clientManagerView.getjTextField5().getText();//age
                        if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty() || s5.isEmpty()) {
                        JOptionPane.showMessageDialog(clientManagerView,"Please input valid data!");
                        }
                        else
                    {
                        Client c1 = new Client(Integer.parseInt(s1), s2, s3, s4, Integer.parseInt(s5));
                        ClientBLL cda = new ClientBLL();
                        try{
                            Client c = cda.findClientById(c1.getId());
                            JOptionPane.showMessageDialog(clientManagerView,"ID already in use");
                        }catch (IndexOutOfBoundsException exx) {
                            try{
                                Client cins = cda.insertClient(c1);
                                JOptionPane.showMessageDialog(clientManagerView,"Client added successfully!");}
                            catch (IllegalArgumentException exception){
                                JOptionPane.showMessageDialog(clientManagerView,"Please input valid data! (review email and/or age)");
                            }
                        }
                    }
                        ClientBLL cbl = new ClientBLL();
                        List<Client> listClients = cbl.findAll();
                        tableModel.getDataVector().removeAllElements();
                        for(Client c:listClients){
                            String data[] ={String.valueOf(c.getId()),c.getName(),c.getAddress(),c.getEmail(), String.valueOf(c.getAge())};
                            tableModel.addRow(data);
                        }
                    }

                });
                clientManagerView.jButton2ActionPerformed(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s1;
                        s1 = clientManagerView.getjTextField1().getText();//id
                        if (s1.isEmpty()) {
                            JOptionPane.showMessageDialog(clientManagerView, "Please input valid data!");
                        } else
                        {
                            ClientBLL cda = new ClientBLL();
                            try {
                                Client client = cda.deleteClientById(Integer.parseInt(s1));
                                JOptionPane.showMessageDialog(clientManagerView,"Client deleted successfully!");
                            }catch (NoSuchElementException exception){
                                JOptionPane.showMessageDialog(clientManagerView,"ID not found!");
                            }
                        }
                        ClientBLL cbl = new ClientBLL();
                        List<Client> listClients = cbl.findAll();
                        tableModel.getDataVector().removeAllElements();
                        for(Client c:listClients){
                            String data[] ={String.valueOf(c.getId()),c.getName(),c.getAddress(),c.getEmail(), String.valueOf(c.getAge())};
                            tableModel.addRow(data);
                        }

                    }
                });
                clientManagerView.jButton3ActionPerformed(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(clientManagerView.getjTextField1().getText().isEmpty())
                        {
                            JOptionPane.showMessageDialog(clientManagerView,"Please insert an ID");
                        }else {


                            int id = Integer.parseInt(clientManagerView.getjTextField1().getText());
                            ClientBLL cbl = new ClientBLL();
                            Client c1;
                            try {
                                c1 = cbl.findClientById(id);
                                String newName, newAddress, newEmail, newAge;
                                newName = clientManagerView.getjTextField2().getText();//name
                                newAddress = clientManagerView.getjTextField3().getText();//address
                                newEmail = clientManagerView.getjTextField4().getText();//email
                                newAge = clientManagerView.getjTextField5().getText();//age
                                int age;
                                if (newAge.equals(""))
                                    age = 0;
                                else age = Integer.parseInt(newAge);
                                Client c2 = new Client(id, newName, newAddress, newEmail, age);
                                if (c2.getName().isEmpty())
                                    c2.setName(c1.getName());
                                if (c2.getAddress().isEmpty())
                                    c2.setAddress(c1.getAddress());
                                if (c2.getEmail().isEmpty())
                                    c2.setEmail(c1.getEmail());
                                if (c2.getAge() == 0)
                                    c2.setAge(c1.getAge());
                                Client c3 = cbl.deleteClientById(id);
                                try {
                                    c3 = cbl.insertClient(c2);
                                    JOptionPane.showMessageDialog(clientManagerView, "Client updated successfully!");
                                } catch (IllegalArgumentException exception) {
                                    JOptionPane.showMessageDialog(clientManagerView, "Please input valid data! (review email and/or age)");
                                }

                            } catch (IndexOutOfBoundsException exx) {
                                JOptionPane.showMessageDialog(clientManagerView, "ID not found!");

                            }
                            }
                            ClientBLL cbl2 = new ClientBLL();
                            List<Client> listClients = cbl2.findAll();
                            tableModel.getDataVector().removeAllElements();
                            for (Client c : listClients) {
                                String data[] = {String.valueOf(c.getId()), c.getName(), c.getAddress(), c.getEmail(), String.valueOf(c.getAge())};
                                tableModel.addRow(data);
                            }
                        }


                });
            }
        });
        mainView.jButton3ActionPerformed(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.dispose();
                orderManagerView= new OrderManagerView();
                DefaultTableModel tableModel = (DefaultTableModel)orderManagerView.getjTable1().getModel();
                orderManagerView.setVisible(true);
                orderManagerView.jButton3ActionPerformed(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        orderManagerView.dispose();
                        mainView.setVisible(true);
                    }
                });
                orderManagerView.jButton1ActionPerformed(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String s1,s2,s3,s4;
                        s1 = orderManagerView.getjTextField1().getText();//amount
                        s2 = orderManagerView.getjTextField2().getText();//prodid
                        s3 = orderManagerView.getjTextField3().getText();//clientid
                        s4 = orderManagerView.getjTextField4().getText();//id
                        if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty())
                        {
                            JOptionPane.showMessageDialog(orderManagerView,"Please insert valid data!");
                        }
                        else {
                            ClientBLL clientBLL = new ClientBLL();
                            ProductBLL productBLL = new ProductBLL();
                            OrderBLL orderBLL = new OrderBLL();
                            try {
                                Orders order = orderBLL.findOrderByID(Integer.parseInt(s4));
                                JOptionPane.showMessageDialog(orderManagerView, "ID already in use");
                            }catch (IndexOutOfBoundsException exx){
                                try{
                                    Client cl = clientBLL.findClientById(Integer.parseInt(s3));
                                    Product prod;
                                    try {
                                        prod = productBLL.findProductById(Integer.parseInt(s2));
                                        if(Integer.parseInt(s1)>prod.getStock())
                                            JOptionPane.showMessageDialog(orderManagerView,"Insufficient Stock");
                                        else
                                        {
                                            Orders order = new Orders(Integer.parseInt(s4),cl.getId(),prod.getId(),Integer.parseInt(s1),Integer.parseInt(s1)*prod.getPrice());
                                            int newStock = prod.getStock()-Integer.parseInt(s1);
                                            prod.setStock(newStock);
                                            Product prod2 = productBLL.deleteProductById(prod.getId());
                                            prod2 = productBLL.insertProduct(prod);
                                            try{
                                                orderBLL.insertOrder(order);
                                                JOptionPane.showMessageDialog(orderManagerView,"Order created Successfully");
                                            }catch (NoSuchElementException exception){
                                                JOptionPane.showMessageDialog(orderManagerView,"Order could not be created");
                                            }
                                        }

                                    }catch (IndexOutOfBoundsException exception){
                                        JOptionPane.showMessageDialog(orderManagerView,"Product with id "+s2+" not found");
                                    }
                                }catch(IndexOutOfBoundsException exception){
                                    JOptionPane.showMessageDialog(orderManagerView,"Client with id "+s3+" not found");
                                }
                            }
                        }
                        OrderBLL cbl2 = new OrderBLL();
                        List<Orders> listOrders= cbl2.findAll();
                        tableModel.getDataVector().removeAllElements();
                        for (Orders c : listOrders) {
                           String data[] = {String.valueOf(c.getID()),String.valueOf(c.getClientID()),String.valueOf(c.getProductID()),String.valueOf(c.getTotalPrice())};
                            tableModel.addRow(data);
                        }

                    }
                });
                orderManagerView.jButton2ActionPerformed(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        OrderBLL orderBLL = new OrderBLL();
                        String s = orderManagerView.getjTextField4().getText();
                        if (s.isEmpty())
                            JOptionPane.showMessageDialog(orderManagerView, "Please insert a valid ID");
                        else {
                            try {
                                Orders order = orderBLL.findOrderByID(Integer.parseInt(s));
                                order = orderBLL.deleteOrderById(Integer.parseInt(s));
                                ProductBLL productBLL = new ProductBLL();
                                Product prod = productBLL.findProductById(order.getProductID());
                                int newStock = prod.getStock() + order.getProductAmount();
                                prod.setStock(newStock);
                                Product prod2 = productBLL.deleteProductById(prod.getId());
                                prod2 = productBLL.insertProduct(prod);
                                JOptionPane.showMessageDialog(orderManagerView, "Order cancelled successfully");

                            } catch (IndexOutOfBoundsException exception) {
                                JOptionPane.showMessageDialog(orderManagerView, "ID not found");
                            }}
                            OrderBLL cbl2 = new OrderBLL();
                            List<Orders> listOrders = cbl2.findAll();
                            tableModel.getDataVector().removeAllElements();
                            for (Orders c : listOrders) {
                                String data[] = {String.valueOf(c.getID()), String.valueOf(c.getClientID()), String.valueOf(c.getProductID()), String.valueOf(c.getTotalPrice())};
                                tableModel.addRow(data);
                            }
                        }

                });
            }
        });
    }
}
