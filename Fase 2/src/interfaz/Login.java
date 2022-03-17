/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import app.Manage;
/**
 *
 * @author Luisa María Ortiz
 */
public class Login extends javax.swing.JFrame {
    Manage manager;
    public Login(Manage manage) {
         ImageIcon img = new ImageIcon("src\\interfaz\\Img\\iconv.png");
        this.setIconImage(img.getImage());
        this.manager=manage;

        initComponents(); 
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Fondo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TextField_usuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        TextField_contrasena = new javax.swing.JPasswordField();
        Button_iniciar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(145, 100));
        setPreferredSize(new java.awt.Dimension(974, 594));
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setPreferredSize(new java.awt.Dimension(979, 594));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Img/icon.png"))); // NOI18N
        bg.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 200, -1, -1));

        jLabel2.setBackground(new java.awt.Color(204, 153, 255));
        jLabel2.setFont(new java.awt.Font("Roboto", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("UDrawing Paper");
        bg.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 350, 350, 60));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Img/bg.jpg"))); // NOI18N
        Fondo.setText("jLabel1");
        bg.add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 0, 410, 590));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Usuario");
        bg.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        TextField_usuario.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        bg.add(TextField_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 400, 40));

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Contraseña");
        bg.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));
        bg.add(TextField_contrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 400, 40));

        Button_iniciar.setBackground(new java.awt.Color(0, 153, 153));
        Button_iniciar.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        Button_iniciar.setText("Iniciar Sesión");
        Button_iniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Button_iniciarActionPerformed(evt);
            }
        });
        bg.add(Button_iniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 170, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Button_iniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Button_iniciarActionPerformed
        
        if(this.TextField_usuario.getText().equals("admin") && this.TextField_contrasena.getText().equals("EDD2022")){
            Admin admin = new Admin(this.manager);
            admin.setVisible(true);
            this.dispose();
        }
        else{
            System.out.println("No eres el admin");
        }
        this.TextField_usuario.setText("");
        this.TextField_contrasena.setText("");
        
        
    }//GEN-LAST:event_Button_iniciarActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Button_iniciar;
    private javax.swing.JLabel Fondo;
    private javax.swing.JPasswordField TextField_contrasena;
    private javax.swing.JTextField TextField_usuario;
    private javax.swing.JPanel bg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
