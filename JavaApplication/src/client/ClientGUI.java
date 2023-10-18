package client;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ClientGUI extends javax.swing.JFrame {

  private static final long serialVersionUID = 1L;
  String username, address = "localhost";

  @SuppressWarnings({ "unchecked", "rawtypes" })
  ArrayList<String> users = new ArrayList();

  int port = 1337;
  Boolean isConnected = false;
  Socket sock;
  BufferedReader reader;
  PrintWriter writer;

  //--------------------------//
  /**
   * ListenThread is a public void function that creates a new thread and starts it.
   *
   * @param  None
   * @return None
   */
  public void ListenThread() {
    Thread IncomingReader = new Thread(new IncomingReader());
    IncomingReader.start();
  }

  //--------------------------//

  /**
   * Adds a user to the list of users.
   *
   * @param  data  the data of the user to be added
   */
  public void userAdd(String data) {
    users.add(data);
  }

  //--------------------------//

  /**
   * Removes the specified data from the users list.
   *
   * @param  data  the data to be removed from the users list
   */
  public void userRemove(String data) {
    users.remove(data);
  }

  /**
   * Sends a disconnect message to the server.
   *
   * @param  none  This function does not take any parameters.
   * @return void  This function does not return any value.
   */
  public void sendDisconnect() {
    String Disconnect = (username + ": :Disconnect");
    try {
      writer.println(Disconnect);
      writer.flush();
    } catch (Exception e) {
      ta_chat.append("Could not send Disconnect message.\n");
    }
  }

  //--------------------------//

  /**
   * Disconnects the user from the chat.
   *
   * @param  None   No parameters required.
   * @return None   No return value.
   */
  public void Disconnect() {
    try {
      ta_chat.append("Disconnected.\n");
      isConnected = false;
      tf_username.setEditable(true);
    } catch (Exception ex) {
      ta_chat.append("Failed to disconnect. \n");
    }
  }

  public ClientGUI() {
    this.setLocationRelativeTo(null);
    this.setTitle("Wired");
    initComponents();
    this.setBackground(new java.awt.Color(0, 0, 0, 1));
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      con =
        DriverManager.getConnection(
          "jdbc:mysql://localhost:3306/loginform",
          "root",
          ""
        );
      statement = con.createStatement();
      System.out.println("Access avec success");
    } catch (Exception e) {
      System.out.println("Error :" + e);
    }
  }

  public class IncomingReader implements Runnable {

    /**
     * Runs the function.
     *
     * @param  paramName	description of parameter
     * @return         	description of return value
     */
    @Override
    public void run() {
      String[] data;
      String stream, connect = "Connect", disconnect = "Disconnect", chat =
        "Chat", privatemsg = "private";

      try {
        while ((stream = reader.readLine()) != null) {
          data = stream.split(":");

          if (data[2].equals(chat)) {
            ta_chat.append(data[0] + ": " + data[1] + "\n");
            ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
          } else if (data[2].equals(connect)) {
            ta_chat.removeAll();
            userAdd(data[0]); // name of the client "data [0]"
          } else if (data[2].equals(disconnect)) {
            userRemove(data[0]);
          } else if (data[2].equals(privatemsg)) {
            ta_chat.append(
              "Private Message from " + data[0] + " : " + data[1] + "\n"
            );
            ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
          } else if (data[2].equals("request")) {
            ta_chat.append(" Server replied " + "\n" + data[1] + "\n");
            ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
          }
        }
      } catch (Exception ex) {}
    }
  }

  //--------------------------//

  private void initComponents() {
    roundPanel1 = new Components.RoundPanel();
    roundPanel2 = new Components.RoundPanel();
    buttonOutLine1 = new Components.ButtonOutLine();
    jLabel1 = new javax.swing.JLabel();
    tf_username = new Components.MyTextField();
    b_connect = new Components.Button();
    b_disconnect = new Components.Button();
    button3 = new Components.Button();
    privateMessaging = new Components.Button();
    jLabel4 = new javax.swing.JLabel();
    b_send = new Components.Button();
    tf_chat = new Components.MyTextField();
    roundPanel4 = new Components.RoundPanel();
    jLabel2 = new javax.swing.JLabel();
    tf_address = new Components.MyTextField();
    jLabel3 = new javax.swing.JLabel();
    tf_port = new Components.MyTextField();
    jScrollPane1 = new javax.swing.JScrollPane();
    ta_chat = new javax.swing.JTextArea();
    minimize = new javax.swing.JLabel();
    close = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setUndecorated(true);

    roundPanel1.setBackground(new java.awt.Color(14, 47, 86));

    roundPanel2.setBackground(new java.awt.Color(102, 170, 217));

    buttonOutLine1.setIcon(
      new javax.swing.ImageIcon(getClass().getResource("/Icons/Profile.png"))
    );
    buttonOutLine1.addActionListener(
      new java.awt.event.ActionListener() {
        /**
         * A description of the entire Java function.
         *
         * @param  evt    description of parameter
         * @return        description of return value
         */
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          buttonOutLine1ActionPerformed(evt);
        }
      }
    );

    jLabel1.setFont(new java.awt.Font("Museo Sans Cyrl 700", 0, 14)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(246, 249, 255));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("Username ");

    tf_username.setText("");

    b_connect.setBackground(new java.awt.Color(255, 137, 0));
    b_connect.setIcon(
      new javax.swing.ImageIcon(getClass().getResource("/Icons/Connecte.png"))
    ); // NOI18N
    b_connect.setText("Online");
    b_connect.setToolTipText("Be Online");
    b_connect.addActionListener(
      new java.awt.event.ActionListener() {
        /**
         * A description of the entire Java function.
         *
         * @param  evt   description of parameter
         * @return       description of return value
         */public void actionPerformed(java.awt.event.ActionEvent evt) {
          b_connectActionPerformed(evt);
        }
      }
    );

    b_disconnect.setBackground(new java.awt.Color(255, 137, 0));
    b_disconnect.setIcon(
      new javax.swing.ImageIcon(getClass().getResource("/Icons/Disconnect.png"))
    ); // NOI18N
    b_disconnect.setText("Disconnect");
    b_disconnect.setToolTipText("Disconnect");
    b_disconnect.addActionListener(
      new java.awt.event.ActionListener() {
        /**
         * A description of the entire Java function.
         *
         * @param  evt   description of parameter
         * @return       description of return value
         */
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          b_disconnectActionPerformed(evt);
        }
      }
    );

    button3.setBackground(new java.awt.Color(255, 137, 0));
    button3.setIcon(
      new javax.swing.ImageIcon(getClass().getResource("/Icons/users.png"))
    ); // NOI18N
    button3.setText("Online Users");
    button3.setToolTipText("Online Users");
    button3.addActionListener(
      new java.awt.event.ActionListener() {
        /**
         * A description of the entire Java function.
         *
         * @param  evt	the action event that triggered the function
         * @return         	none
         */
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          button3ActionPerformed(evt);
        }
      }
    );

    privateMessaging.setBackground(new java.awt.Color(255, 137, 0));
    privateMessaging.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/Icons/private-chat.png")
      )
    ); 

    privateMessaging.setText("Private Message");
    privateMessaging.setToolTipText("private chat");
    privateMessaging.addActionListener(
      new java.awt.event.ActionListener() {
         /**
         * A description of the entire Java function.
         *
         * @param  evt  description of parameter
         * @return      description of return value
         */
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          privateMessagingActionPerformed(evt);
        }
      }
    );

    jLabel4.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/Icons/landscape-1-1.png")
      )
    ); // NOI18N

    javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(
      roundPanel2
    );
    roundPanel2.setLayout(roundPanel2Layout);
    roundPanel2Layout.setHorizontalGroup(
      roundPanel2Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          roundPanel2Layout
            .createSequentialGroup()
            .addGap(44, 44, 44)
            .addGroup(
              roundPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(
                  tf_username,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  Short.MAX_VALUE
                )
                .addGroup(
                  roundPanel2Layout
                    .createSequentialGroup()
                    .addGroup(
                      roundPanel2Layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.LEADING
                        )
                        .addComponent(
                          b_connect,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          145,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(
                          b_disconnect,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          145,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(
                          button3,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          145,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(
                          privateMessaging,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          145,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                    )
                    .addGap(0, 15, Short.MAX_VALUE)
                )
            )
            .addGap(37, 37, 37)
        )
        .addGroup(
          roundPanel2Layout
            .createSequentialGroup()
            .addGap(32, 32, 32)
            .addComponent(jLabel4)
            .addContainerGap(
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
        )
        .addGroup(
          javax.swing.GroupLayout.Alignment.TRAILING,
          roundPanel2Layout
            .createSequentialGroup()
            .addContainerGap(
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addGroup(
              roundPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                  roundPanel2Layout
                    .createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(
                      jLabel1,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      78,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                )
                .addComponent(
                  buttonOutLine1,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  137,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
            .addGap(45, 45, 45)
        )
    );
    roundPanel2Layout.setVerticalGroup(
      roundPanel2Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          roundPanel2Layout
            .createSequentialGroup()
            .addContainerGap()
            .addComponent(
              buttonOutLine1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              jLabel1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              19,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              tf_username,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(38, 38, 38)
            .addComponent(
              b_connect,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              48,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(18, 18, 18)
            .addComponent(
              b_disconnect,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              49,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(18, 18, 18)
            .addComponent(
              button3,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              52,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(18, 18, 18)
            .addComponent(
              privateMessaging,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              51,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(44, 44, 44)
            .addComponent(jLabel4)
            .addContainerGap(20, Short.MAX_VALUE)
        )
    );

    b_send.setBackground(new java.awt.Color(255, 137, 0));
    b_send.setIcon(
      new javax.swing.ImageIcon(getClass().getResource("/Icons/send.png"))
    ); // NOI18N
    b_send.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          b_sendActionPerformed(evt);
        }
      }
    );

    tf_chat.setText("");
    tf_chat.setHint("Write here....");

    roundPanel4.setBackground(new java.awt.Color(102, 170, 217));

    jLabel2.setFont(new java.awt.Font("Museo Sans Cyrl 700", 0, 14)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(246, 249, 255));
    jLabel2.setText("Hostname : ");

    tf_address.setText("localhost");

    jLabel3.setFont(new java.awt.Font("Museo Sans Cyrl 700", 0, 14)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(246, 249, 255));
    jLabel3.setText("Port :");

    tf_port.setText("1337");

    javax.swing.GroupLayout roundPanel4Layout = new javax.swing.GroupLayout(
      roundPanel4
    );
    roundPanel4.setLayout(roundPanel4Layout);
    roundPanel4Layout.setHorizontalGroup(
      roundPanel4Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          roundPanel4Layout
            .createSequentialGroup()
            .addGap(38, 38, 38)
            .addComponent(
              jLabel2,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              97,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              tf_address,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              145,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              171,
              Short.MAX_VALUE
            )
            .addComponent(
              jLabel3,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              66,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              tf_port,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              145,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(59, 59, 59)
        )
    );
    roundPanel4Layout.setVerticalGroup(
      roundPanel4Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          roundPanel4Layout
            .createSequentialGroup()
            .addGap(7, 7, 7)
            .addGroup(
              roundPanel4Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(
                  tf_address,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  jLabel2,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  19,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  tf_port,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  jLabel3,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  19,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
            .addContainerGap(
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
        )
    );

    ta_chat.setColumns(20);
    ta_chat.setRows(5);
    jScrollPane1.setViewportView(ta_chat);

    minimize.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    minimize.setForeground(new java.awt.Color(255, 255, 255));
    minimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    minimize.setIcon(
      new javax.swing.ImageIcon(getClass().getResource("/Icons/minimize.png"))
    ); // NOI18N
    minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    minimize.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          minimizeMouseClicked(evt);
        }
      }
    );

    close.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    close.setForeground(new java.awt.Color(255, 255, 255));
    close.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    close.setIcon(
      new javax.swing.ImageIcon(getClass().getResource("/Icons/fail.png"))
    ); // NOI18N
    close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    close.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          closeMouseClicked(evt);
        }
      }
    );

    javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(
      roundPanel1
    );
    roundPanel1.setLayout(roundPanel1Layout);
    roundPanel1Layout.setHorizontalGroup(
      roundPanel1Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          roundPanel1Layout
            .createSequentialGroup()
            .addContainerGap()
            .addComponent(
              roundPanel2,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(56, 56, 56)
            .addGroup(
              roundPanel1Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(
                  roundPanel4,
                  javax.swing.GroupLayout.Alignment.TRAILING,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  Short.MAX_VALUE
                )
                .addComponent(
                  jScrollPane1,
                  javax.swing.GroupLayout.Alignment.TRAILING
                )
                .addGroup(
                  javax.swing.GroupLayout.Alignment.TRAILING,
                  roundPanel1Layout
                    .createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(
                      minimize,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      20,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                    .addPreferredGap(
                      javax.swing.LayoutStyle.ComponentPlacement.RELATED
                    )
                    .addComponent(
                      close,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      20,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                )
                .addGroup(
                  javax.swing.GroupLayout.Alignment.TRAILING,
                  roundPanel1Layout
                    .createSequentialGroup()
                    .addComponent(
                      tf_chat,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      Short.MAX_VALUE
                    )
                    .addGap(18, 18, 18)
                    .addComponent(
                      b_send,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      57,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                )
            )
            .addGap(14, 14, 14)
        )
    );
    roundPanel1Layout.setVerticalGroup(
      roundPanel1Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          roundPanel1Layout
            .createSequentialGroup()
            .addContainerGap()
            .addGroup(
              roundPanel1Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(
                  roundPanel2,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  Short.MAX_VALUE
                )
                .addGroup(
                  javax.swing.GroupLayout.Alignment.TRAILING,
                  roundPanel1Layout
                    .createSequentialGroup()
                    .addGroup(
                      roundPanel1Layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.LEADING
                        )
                        .addComponent(
                          minimize,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          20,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(
                          close,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          20,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                    )
                    .addPreferredGap(
                      javax.swing.LayoutStyle.ComponentPlacement.RELATED
                    )
                    .addComponent(
                      roundPanel4,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                    .addGap(4, 4, 4)
                    .addComponent(jScrollPane1)
                    .addPreferredGap(
                      javax.swing.LayoutStyle.ComponentPlacement.RELATED
                    )
                    .addComponent(
                      b_send,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      38,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                )
                .addGroup(
                  javax.swing.GroupLayout.Alignment.TRAILING,
                  roundPanel1Layout
                    .createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(
                      tf_chat,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                )
            )
            .addContainerGap()
        )
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
      getContentPane()
    );
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          javax.swing.GroupLayout.Alignment.TRAILING,
          layout
            .createSequentialGroup()
            .addContainerGap(
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              roundPanel1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addContainerGap()
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          javax.swing.GroupLayout.Alignment.TRAILING,
          layout
            .createSequentialGroup()
            .addGap(0, 7, Short.MAX_VALUE)
            .addComponent(
              roundPanel1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
        )
    );

    pack();
    setLocationRelativeTo(null);
  } // </editor-fold>//GEN-END:initComponents

  private void buttonOutLine1ActionPerformed(java.awt.event.ActionEvent evt) {} //GEN-FIRST:event_buttonOutLine1ActionPerformed //GEN-LAST:event_buttonOutLine1ActionPerformed

  private void b_connectActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_b_connectActionPerformed
    //users.add(tf_username.getText());

    if (isConnected == false) {
      tf_username.setEditable(false);

      if (tf_username.getText().length() != 0) {
        if (!users.contains(tf_username.getText())) {
          username = tf_username.getText();
          tf_username.setEditable(false);
          tf_port.setEditable(false);
          tf_address.setEditable(false);
          ta_chat.setEditable(false);

          try {
            sock = new Socket(address, port);
            InputStreamReader streamreader = new InputStreamReader(
              sock.getInputStream()
            );
            reader = new BufferedReader(streamreader);
            writer = new PrintWriter(sock.getOutputStream());
            writer.println(username + ": has connected :Connect");
            writer.flush();
            isConnected = true;
            changetxtareafontsize(ta_chat);
          } catch (Exception ex) {
            ta_chat.append("Cannot Connect! Try Again. \n");
            tf_username.setEditable(true);
          }

          ListenThread();
        }
      } else {
        JOptionPane.showMessageDialog(
          null,
          "Write a name for your Client first",
          "Missing Field !!!",
          JOptionPane.ERROR_MESSAGE
        );
      }
    } else if (isConnected == true) {
      ta_chat.append("You are already connected. \n");
    }
  } //GEN-LAST:event_b_connectActionPerformed

  private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_b_disconnectActionPerformed
    sendDisconnect();
    try {
      String updateQuery =
        "UPDATE loginusers SET Status='offline' WHERE Username='" +
        USERNAME +
        "' AND Password='" +
        PASSWORD +
        "'";
      Statement updateStmt = con.createStatement();
      int count = updateStmt.executeUpdate(updateQuery);
    } catch (SQLException e) {
      e.getStackTrace();
    }
    Disconnect();
  } //GEN-LAST:event_b_disconnectActionPerformed

  private void b_sendActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_b_sendActionPerformed
    send_it();
  } //GEN-LAST:event_b_sendActionPerformed

  private void privateMessagingActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_privateMessagingActionPerformed
    JOptionPane.showMessageDialog(
      null,
      "To send a private message to a person, use @Name@ at the begining",
      "private message",
      JOptionPane.INFORMATION_MESSAGE
    );
  } //GEN-LAST:event_privateMessagingActionPerformed

  private void button3ActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_button3ActionPerformed
    ta_chat.append("\n Online users : \n");
    try {
      writer.println(
        username +
        ":" +
        "Request to know who is online " +
        ":" +
        "request" +
        ":" +
        username
      );
      writer.flush();
    } catch (Exception ex) {
      ta_chat.append("Message was not sent. \n");
    }
  } //GEN-LAST:event_button3ActionPerformed

  private void minimizeMouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_minimizeMouseClicked
    this.setState(ICONIFIED);
  } //GEN-LAST:event_minimizeMouseClicked

  private void closeMouseClicked(java.awt.event.MouseEvent evt) { //GEN-FIRST:event_closeMouseClicked
    try {
      String updateQuery =
        "UPDATE loginusers SET Status='offline' WHERE Username='" +
        USERNAME +
        "' AND Password='" +
        PASSWORD +
        "'";
      Statement updateStmt = con.createStatement();
      int count = updateStmt.executeUpdate(updateQuery);
    } catch (SQLException e) {
      e.getStackTrace();
    }

    System.exit(0);
  } //GEN-LAST:event_closeMouseClicked

  public void send_it() {
    String nothing = "";
    String mydata = tf_chat.getText();
    Pattern pattern = Pattern.compile("(@).*\\1");
    Matcher matcher = pattern.matcher(mydata);

    if ((tf_chat.getText()).equals(nothing)) {
      tf_chat.setText("");
      tf_chat.requestFocus();
    } else if (matcher.find()) {
      String[] data = mydata.split("@");
      //  String reciever = data[1] ;
      try {
        writer.println(
          username + ":" + data[2] + ":" + "private" + ":" + data[1]
        ); // data[2] is the txtmsg .. data[1] is the reciever of the private msg name
        writer.flush(); // flushes the buffer
        ta_chat.append(
          "You have sent {  " +
          data[2] +
          "  } as a private message to : " +
          "'" +
          data[1] +
          "'" +
          "\n"
        );
      } catch (Exception ex) {
        ta_chat.append(
          "Message was not sent. \n" + "No online users found by that name  "
        );
      }
    } else {
      try {
        writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
        writer.flush(); // flushes the buffer
      } catch (Exception ex) {
        ta_chat.append("Message was not sent. \n");
      }
      tf_chat.setText("");
      tf_chat.requestFocus();
    }

    tf_chat.setText("");
    tf_chat.requestFocus();
  }

  public static void main(String args[]) {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger
        .getLogger(ClientGUI.class.getName())
        .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger
        .getLogger(ClientGUI.class.getName())
        .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger
        .getLogger(ClientGUI.class.getName())
        .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger
        .getLogger(ClientGUI.class.getName())
        .log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(
      new Runnable() {
        public void run() {
          new ClientGUI().setVisible(true);
        }
      }
    );
  }

  public void changetxtareafontsize(JTextArea txtarea) {
    Font font1 = new Font("SansSerif", Font.BOLD, 13);
    txtarea.setFont(font1);
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private Components.Button b_connect;
  private Components.Button b_disconnect;
  private Components.Button b_send;
  private Components.Button button3;
  private Components.ButtonOutLine buttonOutLine1;
  private javax.swing.JLabel close;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel minimize;
  private Components.Button privateMessaging;
  private Components.RoundPanel roundPanel1;
  private Components.RoundPanel roundPanel2;
  private Components.RoundPanel roundPanel4;
  private javax.swing.JTextArea ta_chat;
  private Components.MyTextField tf_address;
  private Components.MyTextField tf_chat;
  private Components.MyTextField tf_port;
  public Components.MyTextField tf_username;
  // End of variables declaration//GEN-END:variables

  private Connection con;
  private Statement statement;
  private ResultSet rs;

  public String USERNAME;
  public String PASSWORD;
}
