package bll;

import com.itextpdf.text.pdf.PdfWriter;
import dao.OrderDAO;
import model.Orders;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {
    private OrderDAO orderDAO;
    public OrderBLL(){
        orderDAO = new OrderDAO();
    }
    public Orders findOrderByID(int id){
        Orders order = orderDAO.findById(id);
        if(order == null) {
            throw new NoSuchElementException("Order with ID: " + id + " was not found");
        }
        return order;
    }
    public List<Orders> findAll(){
        return orderDAO.findAll();
    }
    public Orders deleteOrderById(int id) {
        Orders ct = orderDAO.deleteByID(id);
        if (ct == null) {
            throw new NoSuchElementException("The Order with id =" + id + " was not found!");
        }
        return ct;
    }
    public Orders insertOrder(Orders order) {

        Orders ct = orderDAO.insert(order);
        if (ct == null) {
            throw new NoSuchElementException("The Product with id =" + order.getID() + " could not be inserted!");
        }
        Document bill = new Document();
        try {
            PdfWriter.getInstance(bill,new FileOutputStream("bill.pdf"));
            bill.open();
            Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
            Chunk chunk = new Chunk("Order ID: "+String.valueOf(ct.getID())+'\n'
                    +"Client ID: "+String.valueOf(ct.getClientID())+'\n'
                    +"Product ID: "+String.valueOf(ct.getProductID())+'\n'
                    +"Product amount: "+String.valueOf(ct.getProductAmount())+'\n'
                    +"Bill Total Value: "+String.valueOf(ct.getTotalPrice()), font);
            bill.add(new Paragraph("Billing Details: "+'\n'+'\n'));
            bill.add(new Paragraph(chunk));
            bill.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return ct;
    }
}
