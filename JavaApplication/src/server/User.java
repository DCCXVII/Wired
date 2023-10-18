
package server;


public class User {

    int id;
    String Username,Email,Status;
    public int getId() {
        return id;
    }

    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return Email;
    }

    public String getStatus() {
        return Status;
    }
    
         
public User(int id , String username , String  Email,String Status){
    this.id = id;
    this.Username = username;
    this.Email = Email;
    this.Status = Status;
}

}