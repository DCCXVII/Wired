package SignUpGUI;

import LoginGUI.LoginGUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

    public class SignupGUI extends javax.swing.JFrame {

    
    public SignupGUI() {
        initComponents();
      try {
	     Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/loginform","root","");
	     statement = con.createStatement();
             System.out.println("Access avec success");
		 }catch(Exception e) {
			 System.out.println("Error :"+e);
         }
      this.setBackground(new Color(0,0,0,1));
    }
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new Components.RoundPanel();
        Email = new Components.MyTextField();
        Username = new Components.MyTextField();
        Password = new Components.MyPasswordField();
        signup = new Components.Button();
        jLabel14 = new javax.swing.JLabel();
        Login = new javax.swing.JButton("Log In");
        jLabel5 = new javax.swing.JLabel();
        buttonOutLine1 = new Components.ButtonOutLine();
        jPanel2 = new Components.RoundPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        minimize = new javax.swing.JLabel();
        close = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Email.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/mail.png")));
        Email.setText("");
        Email.setHint("Email");
        Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailActionPerformed(evt);
            }
        });
        jPanel1.add(Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 310, 360, -1));

        Username.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/user.png")));
        Username.setText("");
        Username.setHint("Username");
        Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameActionPerformed(evt);
            }
        });
        jPanel1.add(Username, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 360, -1));

        Password.setPrefixIcon(new ImageIcon(getClass().getResource("/Icons/pass.png")));
        Password.setHint("Password");
        Password.setText("");
        jPanel1.add(Password, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 370, 360, -1));

        signup.setBackground(new java.awt.Color(36, 119, 218));
        signup.setForeground(new java.awt.Color(240, 246, 253));
        signup.setEffectColor(new java.awt.Color(240, 246, 253));
        signup.setText("Sign Up");
        signup.setFont(new Font("sansserif", 1, 14));
        signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupActionPerformed(evt);
            }
        });
        jPanel1.add(signup, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 450, 210, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(25,118,211));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Already you have an account?");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 560, 213, 30));

        Login.setForeground(new Color(14,47,86));
        Login.setFont(new Font("museo sans cyrillic 700 free",1,12));
        Login.setContentAreaFilled(false);
        Login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Login.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        Login.setText("Log In");
        Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginActionPerformed(evt);
            }
        });
        jPanel1.add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 560, 100, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/landscape-1-1.png")));
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, -1, -1));

        buttonOutLine1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Profile.png"))); // NOI18N
        buttonOutLine1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOutLine1ActionPerformed(evt);
            }
        });
        jPanel1.add(buttonOutLine1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 80, 160, 170));

        jPanel2.setBackground(new java.awt.Color(25, 118, 211));
        jPanel2.setPreferredSize(new java.awt.Dimension(600, 600));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 250, -1, -1));

        jLabel3.setFont(new java.awt.Font("Museo Sans Cyrl 700", 0, 32)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(246, 249, 255));
        jLabel3.setText("Have fun with");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 230, 50));

        jLabel1.setFont(new java.awt.Font("Museo Sans Cyrl 700", 0, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(246, 249, 255));
        jLabel1.setText("all your friends !");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 250, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Sign Up - Icon.png"))); // NOI18N
        jLabel11.setToolTipText("");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 440, 420));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 510, 590));

        minimize.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        minimize.setForeground(new java.awt.Color(255, 255, 255));
        minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/minimize.png"))); // NOI18N
        minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });
        jPanel1.add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 20, 20));

        close.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        close.setForeground(new java.awt.Color(255, 255, 255));
        close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fail.png"))); // NOI18N
        close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeMouseClicked(evt);
            }
        });
        jPanel1.add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, 20, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 960, 610));

        setSize(new java.awt.Dimension(981, 633));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private boolean CheckingInput(String a, String b){
        if(a.equals("") || b.equals("")){
            return true;
        }
        else{
            return false;
        }
    }
    
    private boolean CheckingValues(String a, String b){
        if(a.length() >= 8 || b.length() >=8){
            return true;
            
       }else{
            return false;
        }
    }
    
    private boolean AlreadyExists(String a, String b)
    {
        boolean exists = false;
       try{
           String Query = "SELECT * FROM loginusers WHERE Username='"+a+"' AND Password='"+b+"'";
                    Statement updateStmt = con.createStatement();
                    rs = updateStmt.executeQuery(Query);
                    if(rs.next())
                        exists = true;
                    
       }catch(SQLException EXP){
           EXP.getStackTrace();
       }
       return exists;
    }
   
    
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
    }//GEN-LAST:event_formWindowOpened

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EmailActionPerformed

    private void UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameActionPerformed

    private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed

     if(CheckingInput(Username.getText(), Password.getText())==true || !Email.getText().contains("@gmail.com")){
            JOptionPane.showMessageDialog(null, "Username or Password wrong, please try again","Input Error",JOptionPane.WARNING_MESSAGE);
        }
        else{
            if (!CheckingValues(Username.getText(), Password.getText())) {
                JOptionPane.showMessageDialog(null, "Username or Password must be upper than 8 characters","Input Error",JOptionPane.WARNING_MESSAGE);
            }
            else{
                try{
                 statement = con.createStatement();
                 if(AlreadyExists(Username.getText(), Password.getText()))
                 {JOptionPane.showMessageDialog(null, "User Already exists!","User Aleady exists ",JOptionPane.WARNING_MESSAGE);}
       
                 else{
                 String query = "INSERT INTO loginusers(UserID,Username,Password,Email,Status) VALUES(?,?,?,?,?)";
                 PreparedStatement ps = con.prepareStatement(query);
                 ps.setString(1,"0");
                 ps.setString(2, Username.getText());
                 ps.setString(3, String.valueOf(Password.getText()));
                 ps.setString(4, Email.getText());
                 ps.setString(5,"offline");
                 int nb = ps.executeUpdate();
                 JOptionPane.showMessageDialog(null, "Inserted Successfully");
                 Username.setText("");
                 Password.setText("");
                 Email.setText("");
                       }
                }catch(SQLException Se){
                    JOptionPane.showMessageDialog(null, Se);
                }
                
            }
        }        
    }//GEN-LAST:event_signupActionPerformed

    private void LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginActionPerformed
                    dispose();
                    LoginGUI logingui = new LoginGUI();
                    logingui.show();
    }//GEN-LAST:event_LoginActionPerformed

    private void buttonOutLine1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOutLine1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonOutLine1ActionPerformed

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked

    private void closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeMouseClicked
       System.exit(0);
    }//GEN-LAST:event_closeMouseClicked

    private void closeMouseExited(java.awt.event.MouseEvent evt){
        close.setForeground(new Color(25,118,211));
    }
    
         
    
   
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
            java.util.logging.Logger.getLogger(SignupGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignupGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignupGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignupGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SignupGUI().setVisible(true);
        });
        
        // make connection with data base : 
    }
        

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.MyTextField Email;
    private javax.swing.JButton Login;
    private Components.MyPasswordField Password;
    private Components.MyTextField Username;
    private javax.swing.ButtonGroup buttonGroup1;
    private Components.ButtonOutLine buttonOutLine1;
    private javax.swing.JLabel close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel minimize;
    private Components.Button signup;
    // End of variables declaration//GEN-END:variables

    
    public byte[] Image_Profile = null;
    public static String FichNom = null;
    private Connection con;
    private Statement statement;
    private ResultSet rs;
     
}
