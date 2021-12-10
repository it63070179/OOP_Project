
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Tanadon Parosin
 */
public class BorrowBook extends javax.swing.JFrame {

    /**
     * Creates new form BorrowBook
     */
    public BorrowBook() {
        initComponents();
    }
    
    /*to fetch the book details from details from database and display book details panel*/
    public void getBookDetails(){
        int bookId = Integer.parseInt(txt_bookId.getText());
        
        try{
            Connection con = DBConnection.getConnection(); 
            PreparedStatement pst = con.prepareStatement("select * from book_details where book_id = ?");
            pst.setInt(1, bookId);
            ResultSet rs = pst.excuteQuery();
            
            if (rs.next){
                lbl_bookId.setText(rs.getString("book_id"));
                lbl_bookName.setText(rs.getString("book_name"));
                lbl_Arthur.setText(rs.getString("author"));
                lbl_quantity.setText(rs.getString("quantity"));
                
            }
            else {
                lbl_bookError.setText("Invalid Book ID");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
     /*to fetch the username from details from database and display book details panel*/
    public void getUserDetails(){
        int username = Integer.parseInt(txt_userName.getText());
        
        try{
            Connection con = DBConnection.getConnection(); 
            PreparedStatement pst = con.prepareStatement("select * from username_details where username_id = ?");
            pst.setInt(1, username);
            ResultSet rs = pst.excuteQuery();
            
            if (rs.next){
                lbl_userId.setText(rs.getString("user_id"));
                lbl_username.setText(rs.getString("username"));
                lbl_email.setText(rs.getString("email"));
                lbl_phone.setText(rs.getString("email"));
            }
            else {
                lbl_userError.setText("Invalid Username");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    /** Insert Borrow book detail to database **/
    public boolean borrowBook(){
        boolean isBorrowed;
        int bookId = Integer.parseInt(txt_bookId.getText());
        int user_Id = Integer.parseInt(txt_userName.getText());
        String bookName = lbl_bookName.getText();
        String userName = lbl_username.getText();
        
        Date uDate_Borrow = date_Borrow.getDatoFecha();
        Date uDate_Due = date_Due.getDatoFecha();        
        
        Long l1 = uDate_Borrow.getTime();
        Long l2 = uDate_Due.getTime();
        
        java.sql.Date sDate_Borrow = new java.sql.Date(l1);
        java.sql.Date sDate_Due = new java.sql.Date(l2);
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "inser into borrow_book_details(book_id,book_name,user_id,username, borrow_date, due_date, status) values(?,?,?,?,?,?,?)";
            PrepareStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setString(2, bookName);
            pst.setInt(3, user_Id);
            pst.setString(4, userName);
            pst.setDate(5, sDate_Borrow);
            pst.setDate(6, sDate_Due);
            pst.setString(7, "pending");
            
            ResultSet rs = pst.executeUpdate();
            
            if (rs.next() > 0){
                isBorrowed = true;
            }
            else{
                isBorrowed = false;
            }
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return isBorrowed;
    }
    
    /* updating book count */
    
    public void updateBookCount(){
        int bookId = Integer.parseInt(txt_bookId.getText());
        try{
            Connection con = DBConnection.getConnection();
            String sql = "update book_details set quantity = quantity - 1 where book_id = ?";
            PrepareStatement pat = con.prepareStatement(sql);
            pst.setInt(1, bookId);
        
            int rowCount = pst.executeUpdate();
            
            if (rowCount > 0){
                JOptionPane.showMessageDialog(this, "book count updated");
                int initialCount = Integer.parseInt(lbl_quantity.getText());
                lbl_quantity.setText(Integer.toString(initialCount - 1));
            }
            
            else{
                JOptionPane.showMessageDialog(this, "can't update book count");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            
        }
    }
    
    
    /* checking whether book already allocated or not */
    public boolean isAlreadyBorrowed(){
        
        boolean isAlreadyBorrowed = false;
        int bookId = Integer.parseInt(txt_bookId.getText());
        int user_Id = Integer.parseInt(txt_studentId.getText());
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "select * from borrow_book_details where book_id = ? and user_id = ? and staus = ?";
            PrepareStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setInt(2, user_id);
            pst.setString(3, "pending");
            
            int rowCount = pst.executeUpdate();
            
            if (rowCount > 0){
                isAlreadyBorrowed = true;
            }
            else{
                isAlreadyBorrowed = false;
            }
        }
        catch (Exception){
            e.printStackTrace();
        }
        return isAlreadyBorrowed;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_Arthur = new javax.swing.JLabel();
        lbl_quantity = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_bookError = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_phone = new javax.swing.JLabel();
        lbl_userId = new javax.swing.JLabel();
        lbl_username = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_email = new javax.swing.JLabel();
        lbl_userError = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_userName = new app.bolivia.swing.JCTextField();
        txt_bookId = new app.bolivia.swing.JCTextField();
        date_Due = new rojeru_san.componentes.RSDateChooser();
        date_Borrow = new rojeru_san.componentes.RSDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panel_main.setForeground(new java.awt.Color(255, 255, 255));
        panel_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(232, 255, 254));

        jPanel2.setBackground(new java.awt.Color(9, 13, 142));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Arthur : ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Book ID : ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Book Name : ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Quantity : ");

        jLabel7.setFont(new java.awt.Font("Berlin Sans FB", 0, 28)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel7.setText(" Book Detail");

        lbl_bookError.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_bookError.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addComponent(lbl_Arthur, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(31, 31, 31)
                                .addComponent(lbl_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_bookId, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_bookName, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(60, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbl_bookError, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(55, 55, 55)
                    .addComponent(jLabel3)
                    .addContainerGap(267, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(jLabel4)
                    .addContainerGap(237, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(70, 70, 70)
                    .addComponent(jLabel7)
                    .addContainerGap(86, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(lbl_bookId, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(lbl_bookName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Arthur, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(87, 87, 87)
                .addComponent(lbl_bookError, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(231, 231, 231)
                    .addComponent(jLabel3)
                    .addContainerGap(397, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(299, 299, 299)
                    .addComponent(jLabel4)
                    .addContainerGap(329, Short.MAX_VALUE)))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(71, 71, 71)
                    .addComponent(jLabel7)
                    .addContainerGap(479, Short.MAX_VALUE)))
        );

        jPanel3.setBackground(new java.awt.Color(72, 106, 234));

        jLabel10.setFont(new java.awt.Font("Berlin Sans FB", 0, 28)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-male-user-100.png"))); // NOI18N
        jLabel10.setText("   Users Detail");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Phone Number : ");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("User ID : ");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("User name : ");

        lbl_phone.setBackground(new java.awt.Color(255, 255, 255));

        lbl_userId.setBackground(new java.awt.Color(255, 255, 255));

        lbl_username.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Email : ");

        lbl_email.setBackground(new java.awt.Color(255, 255, 255));

        lbl_userError.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbl_userError.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbl_userError, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17)
                                .addComponent(jLabel11))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbl_username, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbl_userId, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(55, 55, 55)
                    .addComponent(jLabel12)
                    .addContainerGap(260, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addComponent(jLabel13)
                    .addContainerGap(232, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(184, Short.MAX_VALUE)
                    .addComponent(lbl_email, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(45, 45, 45)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(lbl_userId, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(lbl_username, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbl_phone, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65)
                .addComponent(lbl_userError, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(231, 231, 231)
                    .addComponent(jLabel12)
                    .addContainerGap(397, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(299, 299, 299)
                    .addComponent(jLabel13)
                    .addContainerGap(329, Short.MAX_VALUE)))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(372, Short.MAX_VALUE)
                    .addComponent(lbl_email, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(251, 251, 251)))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Book ID :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Username");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Borrow Date");

        txt_userName.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 5, 1, new java.awt.Color(27, 27, 176)));
        txt_userName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_userName.setPhColor(new java.awt.Color(35, 8, 194));
        txt_userName.setPlaceholder("Enter Username");
        txt_userName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_userNameFocusLost(evt);
            }
        });
        txt_userName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_userNameActionPerformed(evt);
            }
        });

        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 5, 1, new java.awt.Color(27, 27, 176)));
        txt_bookId.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_bookId.setPhColor(new java.awt.Color(35, 8, 194));
        txt_bookId.setPlaceholder("Enter Book ID");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });

        date_Borrow.setPlaceholder("Select ");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel14.setText("Due Date");

        jButton1.setBackground(new java.awt.Color(22, 48, 195));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(9, 13, 142));
        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(9, 13, 142));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-borrow-book-100.png"))); // NOI18N
        jLabel1.setText(" Borrow Book ");

        jPanel4.setBackground(new java.awt.Color(72, 106, 234));

        jLabel15.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("x");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("-");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(23, 23, 23))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(date_Borrow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(date_Due, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(135, 135, 135)
                                .addComponent(jLabel14)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(116, 116, 116))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_bookId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(txt_userName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(28, 28, 28))
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_bookId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_userName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_Borrow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(date_Due, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );

        panel_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 650));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_userNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_userNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_userNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (isAlreadyBorrowed() == false){
            if (borrowBook() == true ){
                JOptionPane.showMessageDialog(this, "book borrowed successfully");
                updateBookCount(); 
            }
            else{
                JOptionPane.showMessageDialog(this, "can't borrow the book");
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "this user already has this book");
        }
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        this.setExtendedState(HomePage.ICONIFIED);
    }//GEN-LAST:event_jLabel16MouseClicked

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
        if (!txt_bookId.getText().equals("")){
           getBookDetails(); 
        }
        
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_userNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_userNameFocusLost
        if (!txt_userName.getText().equals("")){
           getUserDetails(); 
        }
    }//GEN-LAST:event_txt_userNameFocusLost

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(BorrowBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BorrowBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BorrowBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BorrowBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BorrowBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_Borrow;
    private rojeru_san.componentes.RSDateChooser date_Due;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lbl_Arthur;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_phone;
    private javax.swing.JLabel lbl_quantity;
    private javax.swing.JLabel lbl_userError;
    private javax.swing.JLabel lbl_userId;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JPanel panel_main;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_userName;
    // End of variables declaration//GEN-END:variables
}
