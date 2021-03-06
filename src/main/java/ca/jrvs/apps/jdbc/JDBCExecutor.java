package ca.jrvs.apps.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExecutor {
    public static void main(String... args)
    {
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost", "hplussport",
                "postgres", "password");

        try{
            Connection connection = dcm.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM CUSTOMER");
            while(resultSet.next()){
                System.out.println(resultSet.getInt(1));
            }
            CustomerDAO customerDAO = new CustomerDAO(connection);
            /*Customer customer = new Customer();
            customer.setFirstName("Faika");
            customer.setLastName("Hoque");
            customer.setEmail("faika.hoque@gmail.com");
            customer.setPhone("(514) 294 7909");
            customer.setAddress("1234 scarbourgh");
            customer.setCity("Toronto");
            customer.setState("ON");
            customer.setZipCode("23145");
            customerDAO.create(customer);*/
           /* Customer customer = customerDAO.findByID(1000);
            System.out.println(customer.getFirstName()+ " " + customer.getLastName());*/
           /* OrderDAO orderDAO = new OrderDAO(connection);
           Order order = orderDAO.findById(1000);
           System.out.println(order);
            */
           Customer customer = customerDAO.findByID(10000);
           customer.setEmail("faikahoque@gmail.com");
           customer = customerDAO.update(customer);

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
