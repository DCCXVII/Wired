package LoginGUI;

import client.ClientGUI;
import SignUpGUI.SignupGUI;
import java.awt.Font;
import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Cursor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;



    public class LoginGUI extends javax.swing.JFrame {

   
    public LoginGUI() {
        this.setTitle("Wired Login");
        initComponents();
          try {
	     Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginform","root","");
             statement = con.createStatement();
             System.out.println("LoginGUI : Access avec success");
		 }catch(Exception e) {
			 System.out.println("Error :"+e);
		 }
        this.setBackground(new Color(0,0,0,1));
    }
    
     // ***************** Checking the inputs value***************** //
    
    public boolean CheckingInput(String a, String b){
        if(a.equals("") || b.equals("")){
            return true;
        }
        else{
            return false;
        }
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new Components.RoundPanel();
        minimize = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new Components.RoundPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        Username = new Components.MyTextField();
        Password = new Components.MyPasswordField();
        signin = new Components.Button();
        joinus = new javax.swing.JButton("Join Us");
        jLabel5 = new javax.swing.JLabel();
        close = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(12, 123, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.white);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        minimize.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        minimize.setForeground(new java.awt.Color(255, 255, 255));
        minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/minimize.png"))); // NOI18N
        minimize.setToolTipText("Minimize");
        minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });
        jPanel2.add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 10, 20, 20));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Log in - Icon.png"))); // NOI18N
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 580, 350));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 30)); // NOI18N
        jLabel3.setBackground(new java.awt.Color(25, 118, 211));
        jLabel3.setFont(new java.awt.Font("Museo Sans Cyrl 700", 0, 32)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(25, 118, 211));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Stay Connected!");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 280, 41));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/landscape-1-1.png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 180, 70));

        jPanel1.setBackground(new java.awt.Color(25, 118, 211));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Museo Sans Cyrl 700", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(199, 226, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Hello! Let's get started");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 170, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(199, 226, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Don't have an account?");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 213, 30));

        Username.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/user.png")));
        Username.setText("");
        Username.setHint("Username");
        Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameActionPerformed(evt);
            }
        });
        jPanel1.add(Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 310, -1));

        Password.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/pass.png")));
        Password.setHint("Password");
        Password.setText("");
        jPanel1.add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 310, -1));

        signin.setBackground(new java.awt.Color(240, 246, 253));
        signin.setForeground(new java.awt.Color(36, 119, 218));
        signin.setText("Sign In");
        signin.setFont(new Font("sansserif", 1, 14));
        signin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signinActionPerformed(evt);
            }
        });
        jPanel1.add(signin, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 320, 140, 40));

        joinus.setForeground(new Color(246,249,255));
        joinus.setFont(new Font("museo sans cyrillic 700 free",1,12));
        joinus.setContentAreaFilled(false);
        joinus.setCursor(new Cursor(Cursor.HAND_CURSOR));
        joinus.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        joinus.setText("Join Us");
        joinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                joinusActionPerformed(evt);
            }
        });
        jPanel1.add(joinus, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 450, 100, -1));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 30)); // NOI18N
        jLabel5.setBackground(new java.awt.Color(25, 118, 211));
        jLabel5.setFont(new java.awt.Font("Museo Sans Cyrl 700", 0, 32)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(246, 249, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Sign In");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 110, 41));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 410, 510));

        close.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        close.setForeground(new java.awt.Color(255, 255, 255));
        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fail.png"))); // NOI18N
        close.setToolTipText("Close");
        close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        jPanel2.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 20, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1010, 530));

        getAccessibleContext().setAccessibleName("login");

        setSize(new java.awt.Dimension(1032, 556));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
         this.setState(ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked

    private void UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameActionPerformed

    private void joinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_joinusActionPerformed
                    dispose();
                    SignupGUI signup = new SignupGUI();
                    signup.show();
    }//GEN-LAST:event_joinusActionPerformed

    private void signinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signinActionPerformed
        if(CheckingInput(Username.getText(), Password.getText())==true){
            JOptionPane.showMessageDialog(null, "Username or Password wrong, please try again","Input Error",JOptionPane.WARNING_MESSAGE);
        }
        else{
            try {
                statement = con.createStatement();
                String query = "SELECT * FROM loginusers WHERE Username='"+Username.getText()+"' AND Password='"+Password.getText()+"' AND Status ='offline'";
                rs = statement.executeQuery(query);
                if(rs.next()){
                    
                    // changing the status at database : 
                    String updateQuery = "UPDATE loginusers SET Status='online' WHERE Username='"+Username.getText()+"' AND Password='"+Password.getText()+"'";
                    Statement updateStmt = con.createStatement();
                    int count = updateStmt.executeUpdate(updateQuery);
                    
                    // login true :
                    dispose();
                    ClientGUI homePage = new ClientGUI();
                    homePage.tf_username.setText(Username.getText());
                    homePage.USERNAME = Username.getText();
                    homePage.PASSWORD = Password.getText();
                    homePage.show();
                }
                else{
                    // login false :
                    JOptionPane.showMessageDialog(this, "Username or password is Wrong or Maybe he is online already");
                    Username.setText("");
                    Password.setText("");
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(LoginGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }//GEN-LAST:event_signinActionPerformed

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
        try{String updateQuery = "UPDATE loginusers SET Status='0' WHERE Username='"+Username.getText()+"' AND Password='"+Password.getText()+"'";
                    Statement updateStmt = con.createStatement();
                    int count = updateStmt.executeUpdate(updateQuery);}catch(SQLException e){
                        e.getStackTrace();
                    }
        System.exit(0);
    }//GEN-LAST:event_closeMouseClicked
   
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new LoginGUI().setVisible(true);
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.MyPasswordField Password;
    private Components.MyTextField Username;
    private javax.swing.JLabel close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton joinus;
    private javax.swing.JLabel minimize;
    private Components.Button signin;
    // End of variables declaration//GEN-END:variables
    
    private Connection con;
    private Statement statement;
    private ResultSet rs;
   
    
}
