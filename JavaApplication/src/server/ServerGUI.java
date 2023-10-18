package server;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JTextArea;

public class ServerGUI extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    ArrayList<PrintWriter> clientOutputStreams = new ArrayList<>();
    ArrayList<String> users = new ArrayList<>();
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" yyyy-MM-dd ");  
    LocalDateTime now = LocalDateTime.now();  
    
    public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       PrintWriter client;
       int id_privateChat ;

       public ClientHandler(Socket clientSocket, PrintWriter user)//, int useridnumber) 
       {
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
                ta_chat.append("\n["+dtf.format(now)+"] "+"Unexpected error... \n");
            }

       }
       
       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat" , privatemsg = "private" ;
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null) 
                {
                    ta_chat.append("\nReceived: " + message + "\n");
                    data = message.split(":");
                    int counter2steps = 0;
                    for (String token:data) 
                    {
                    	if(counter2steps!=2)
                        ta_chat.append(token + " ");
                        counter2steps++;
                    }

                    if (data[2].equals(connect)) 
                    {
                        tellEveryone((data[0] + ":" + data[1] + ":" + chat));
                        userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                        tellEveryone((data[0] + "  has :Disconnected" + ":" + chat));

                        clientOutputStreams.remove(getid(data[0]));
                        userRemove(data[0]);

                    } 
                    else if (data[2].equals(chat)) 
                    {
                        tellEveryone(message);
                    } 
                    else if (data[2].equals(privatemsg)) {
                        
                        int recievedID  = getid(data[3]);
                       if (recievedID != -1) {
                        	tellthispersononly(message, recievedID, data[3]);
						} else {
							tellthispersononly(message, recievedID, data[0]);
						}
                        
                        
                    }else if (data[2].equals("request")) {
                        
                        int recievedID  ;
                        StringBuilder stringBuilder = new StringBuilder();
                             for (String current_user : users)
                     {
                                recievedID  = getid(current_user);
                                stringBuilder.append(current_user).append(", With ID = ").append(recievedID);
                               stringBuilder.append(".   ");
                    }           
                             recievedID  = getid(data[0]);
                             String finalString = stringBuilder.toString();
                             finalString = data[0]+ ":" +finalString+ ":" + "request" ;
                             tellthispersononly(finalString, recievedID, data[0]); // d[0] here is the reciever person which is the in this case the er itself 
                    }
                    else 
                    {
                        ta_chat.append("No Conditions were met. \n");
                    }
                } 
             } 
             catch (Exception ex) 
             {
               
                ta_chat.append("Lost the connection. \n");
             } 
	} 
    }

       
    public ServerGUI() {
        this.setLocationRelativeTo(null);
        this.setTitle("Wired Server side");
        

        initComponents();
        this.setBackground(new Color(0,0,0,1));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new Components.RoundPanel();
        roundPanel2 = new Components.RoundPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        b_end = new Components.Button();
        b_start = new Components.Button();
        roundPanel1 = new Components.RoundPanel();
        button2 = new Components.Button();
        b_users = new Components.Button();
        b_clear = new Components.Button();
        end = new Components.Button();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        minimize = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 51, 51));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(14, 47, 86));
        jPanel1.setForeground(new java.awt.Color(14, 47, 86));

        roundPanel2.setBackground(new java.awt.Color(102, 170, 217));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/landscape-1-1.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Museo Sans Cyrl 700", 0, 32)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setText("Server Side");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(11, 11, 11)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        b_end.setBackground(new java.awt.Color(255, 0, 0));
        b_end.setEffectColor(new java.awt.Color(255,178,178));
        b_end.setText("Stop");
        b_end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_endActionPerformed(evt);
            }
        });

        b_start.setBackground(new java.awt.Color(0, 204, 0));
        b_start.setEffectColor(new java.awt.Color(163,236,163));
        b_start.setText("Start");
        b_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_startActionPerformed(evt);
            }
        });

        roundPanel1.setBackground(new java.awt.Color(102, 170, 217));

        button2.setBackground(new java.awt.Color(255, 137, 0));
        button2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/download.png"))); // NOI18N
        button2.setText("Download");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        b_users.setBackground(new java.awt.Color(255, 137, 0));
        b_users.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/users.png"))); // NOI18N
        b_users.setText("Users");
        b_users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_usersActionPerformed(evt);
            }
        });

        b_clear.setBackground(new java.awt.Color(255, 137, 0));
        b_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/clear.png"))); // NOI18N
        b_clear.setText("Clear Logs");
        b_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_clearActionPerformed(evt);
            }
        });

        end.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/exit.png"))); // NOI18N
        end.setText("Log out");
        end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_users, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(roundPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(b_users, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(b_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 196, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(b_start, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(b_end, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(minimize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(minimize, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b_start, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(b_end, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_endActionPerformed

        try
        {
            ta_chat.append("\n");
            ta_chat.append("\n");
            ta_chat.append("\n");
            ta_chat.append("\n");
            tellEveryone("Server: is stopping and all users will be disconnected:Chat");
            ta_chat.append("Server stopping ... \n");
            ta_chat.setText("");
            ta_chat.setText("closing everything ...");
            Thread.sleep(500);
            System.exit(0);

        }
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}

        tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        ta_chat.append("Server stopping... \n");

        ta_chat.setText("");
    }//GEN-LAST:event_endActionPerformed

    private void b_endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_endActionPerformed
        try
        {
           
            ta_chat.append("\n");
            ta_chat.append("\n");
            ta_chat.append("\n");
            ta_chat.append("\n");
            tellEveryone("Server: is stopping and all users will be disconnected:Chat");
            ta_chat.append("Server stopping ... \n"); 
            ta_chat.setText("");
            ta_chat.setText("closing everything ...");
            Thread.sleep(500);
        }
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}

        tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        ta_chat.append("Server stopping... \n");

        ta_chat.setText("");
    }//GEN-LAST:event_b_endActionPerformed

    private void b_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_startActionPerformed
         Thread starter = new Thread(new ServerStart());
        starter.start();
        ta_chat.setEditable(false);
        ta_chat.append("["+dtf.format(now)+"]"+" Server has been started \n Waiting for connection ...");
        changetxtareafontsize(ta_chat) ;
    }//GEN-LAST:event_b_startActionPerformed

    private void b_usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_usersActionPerformed
     try{ 
         Users user = new Users();
         user.setVisible(true);
     }catch(Exception e){
         System.out.println("Error:"+e);
        }    }//GEN-LAST:event_b_usersActionPerformed

    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_clearActionPerformed
        ta_chat.setText("");
    }//GEN-LAST:event_b_clearActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
    try{               
         String s=ta_chat.getText();
           if(s.length()>0)
                {
                    FileDialog fd= new FileDialog(this,"Save File As",FileDialog.SAVE);
                        fd.setFile("Journal.txt");
                        fd.setDirectory("c:\\windows\\temp");
                        fd.setVisible(true);
                        String path=fd.getDirectory()+fd.getFile();

                        FileOutputStream fos=new FileOutputStream(path);
                            System.out.println(s);
                            byte[] b=s.getBytes();
                            fos.write(b);
                            fos.close();
                       }
                   }catch(Exception e)
                            {    
                                System.out.println(e);
                            }

    }//GEN-LAST:event_button2ActionPerformed

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        this.setState(ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked

   
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerGUI().setVisible(true);
            }
        });
    }
    
    public class ServerStart implements Runnable 
    {
        
		@Override
        public void run() 
        {
 
            try 
            {
                   @SuppressWarnings("resource")
		ServerSocket serverSock = new ServerSocket(1337);
                while (true) 
                {
		    Socket clientSock = serverSock.accept();
		    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                    clientOutputStreams.add(writer);
		    Thread listener = new Thread(new ClientHandler(clientSock, writer)); //, id));
		    listener.start();
		     ta_chat.append("Got a connection. \n");
			
                }
            }
            catch (Exception ex)
            {
                ta_chat.append("Error making a connection. \n");
            }
        }
    }
    
    public int getid(String data){
        
       int userid = users.indexOf(data);
        return userid ;
    }
    
    public void userAdd (String data) 
    {
        String message, s = ": :Connect", done = "Server: :Done", name = data;
        users.add(name); // add everynew user in this array list
        for (String token:users) // advanced for loop to get each name from the the array
        {
            message = (token + s); // message will be his name plus the string connect
            tellEveryone(message);
        }
        tellEveryone(done);
    }
    
    public void userRemove (String data) 
    {
        @SuppressWarnings("unused")
		String message, s = ": :Disconnect", done = "Server: :Done", name = data;
        users.remove(name);
        for (String UserName:users) 
        {
            message = (UserName + s);
            tellEveryone(message);
        }
    }
    
    public void tellthispersononly(String msg , int personid, String recievername){
        
    if (personid == -1) {
    	
		msg =  "The Server ... : The User is Not Found in the online users your message has not been deliverd  :private";
		personid=getid(recievername);
		try 
        {
            PrintWriter writer =   clientOutputStreams.get(personid);  
            writer.println(msg);
            writer.flush();
            ta_chat.append("Sending to {"+recievername+"} only this msg : Message not sent because the User not found in the online Users \n");
            ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
        }catch (Exception ex) 
        {
        	ta_chat.append("Error in telling this to "+ recievername +"." +"\n");
        }
		
	} else {

		
    	
        if (clientOutputStreams.get(personid)!= null) {
                
            try 
            {
                PrintWriter writer =    clientOutputStreams.get(personid); //clientOutputStreams[personid];   //(PrintWriter) it.next();
                writer.println(msg);
                writer.flush();
		ta_chat.append("Sending to {"+recievername+"} only this msg :  " + msg + "\n");
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
            }catch (Exception ex) 
            {
		ta_chat.append("Error in telling this "+ recievername +"." +"\n");
            }
        }
        else
        {
            ta_chat.append("Error in telling this ... his ID not found OR His outputstream is null "+ recievername +"." +"\n");
        }
    		}
    }
    
    public void changetxtareafontsize(JTextArea txtarea){
    	Font font1 = new Font("SansSerif", Font.BOLD, 12);
    	txtarea.setFont(font1);
    }
    
    public void tellEveryone(String message) 
    {
	 @SuppressWarnings("rawtypes")
	Iterator it = clientOutputStreams.iterator(); // itrator for looping 
        
       while (it.hasNext()) 
       
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();  //clientOutputStreams[i];   //(PrintWriter) it.next();
                writer.println(message);
                writer.flush();
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
            
        } 
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.Button b_clear;
    private Components.Button b_end;
    private Components.Button b_start;
    private Components.Button b_users;
    private Components.Button button2;
    private Components.Button end;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel minimize;
    private Components.RoundPanel roundPanel1;
    private Components.RoundPanel roundPanel2;
    private javax.swing.JTextArea ta_chat;
    // End of variables declaration//GEN-END:variables
}
