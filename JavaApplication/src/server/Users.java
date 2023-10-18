package server;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Components.Button;
import java.sql.*;


public class Users extends JFrame{
    public JTable tab = new JTable();
    public DefaultTableModel model; 
    private Connection con=null;
    private Statement st=null;
    private ResultSet rs=null;
    private Button updateButton = new  Button();
    
    
    public Users() {
        
        this.setTitle("Users");
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
	this.setSize(700,500);
        updateButton.setBackground(new java.awt.Color(240,246,253));
        updateButton.setForeground(new java.awt.Color(14,47,86));
        updateButton.setText("Update");
        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/update.png")));
        updateButton.setSize(12,12);
        getContentPane().add(updateButton,BorderLayout.SOUTH);
        show_user();
    }
    
    
    
    
    
    public  ArrayList<User> UsersList(){
        ArrayList<User> UsersList = new ArrayList<>();
        try{ 
             Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginform","root","");
             st = con.createStatement();
	     System.out.println("Userlist");
	     String query1 = "SELECT * FROM loginusers";
	     rs = st.executeQuery(query1);
             User user;
             while(rs.next()){
                 user = new User(rs.getInt("UserID"), rs.getString("Username"), rs.getString("Email"), rs.getString("Status"));
                 UsersList.add(user);
             }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return UsersList;
    }

   public void show_user(){
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginform","root","");
             st = con.createStatement();
	     System.out.println("show users");
              model = new DefaultTableModel(new String[]{
                 "ID","Username","Email","Status"},0);
             String sql = "SELECT * FROM loginusers";
             rs = st.executeQuery(sql);
             while(rs.next()){
                 int a = rs.getInt("UserID");
                 String b = rs.getString("Username");
                 String c = rs.getString("Email");
                 String d =  rs.getString("Status");
                 model.addRow(new Object[]{a,b,c,d});
                 tab.setModel(model);
                 
                 JScrollPane pane_users = new JScrollPane(tab);
                 getContentPane().add(pane_users,BorderLayout.CENTER);
                 
             }
             
       }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);
                     }
   }
   
    public static void main(String[] args) {
        Users user = new Users();
        user.setVisible(true);
        
        
    }
    
}
