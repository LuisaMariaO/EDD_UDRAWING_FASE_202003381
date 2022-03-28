/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import app.Capa;
import app.Cliente;
import app.Imagen;
import app.Manage;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Luisa María Ortiz
 */
public class Clientei extends javax.swing.JFrame {
    Cliente cliente;
    Manage manager;
    String imgactual;
    public Clientei(Cliente cliente,Manage manager) {
        ImageIcon img = new ImageIcon("src\\interfaz\\Img\\iconv.png");
        this.setIconImage(img.getImage());
        this.cliente = cliente;
        this.manager=manager;
        
        initComponents();
        
        this.jLabel_nombre.setText(cliente.nombre+" - "+cliente.dpi);
        this.imgactual="";
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel_nombre = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel_visualizador = new javax.swing.JLabel();
        jComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextField_Reportes = new javax.swing.JTextField();
        jButton_visualizar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_archivo = new javax.swing.JMenu();
        jMenuItem_ccapas = new javax.swing.JMenuItem();
        jMenuItem_cimagenes = new javax.swing.JMenuItem();
        jMenuItem_calbumnes = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_nombre.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_nombre.setFont(new java.awt.Font("RomanD", 1, 18)); // NOI18N
        jLabel_nombre.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jLabel_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 780, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Img/bannercliente.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 100));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Img/exit_opt.png"))); // NOI18N
        jButton1.setToolTipText("Cerrar sesión");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 130, 50, 50));

        jLabel_visualizador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaz/Img/blank.png"))); // NOI18N
        jLabel_visualizador.setToolTipText("Click si la imagen no se ve completa.");
        jLabel_visualizador.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel_visualizador.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_visualizador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_visualizadorMouseClicked(evt);
            }
        });
        jPanel1.add(jLabel_visualizador, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, 600, 370));

        jComboBox.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ver árbol de imágenes", "Ver árbol de capas", "Ver listado de álbumes", "Ver capa", "Ver imagen y árbol de capas" }));
        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 180, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Estado de las estructuras");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jTextField_Reportes.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTextField_Reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ReportesActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField_Reportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 210, 30));

        jButton_visualizar.setBackground(new java.awt.Color(255, 102, 0));
        jButton_visualizar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jButton_visualizar.setForeground(new java.awt.Color(255, 255, 255));
        jButton_visualizar.setText("Visualizar");
        jButton_visualizar.setBorder(null);
        jButton_visualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_visualizarActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_visualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 90, 30));

        jMenu_archivo.setText("Archivo");
        jMenu_archivo.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jMenu_archivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu_archivoActionPerformed(evt);
            }
        });

        jMenuItem_ccapas.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jMenuItem_ccapas.setText("Cargar Capas");
        jMenuItem_ccapas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_ccapasActionPerformed(evt);
            }
        });
        jMenu_archivo.add(jMenuItem_ccapas);

        jMenuItem_cimagenes.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jMenuItem_cimagenes.setText("Cargar Imágenes");
        jMenuItem_cimagenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_cimagenesActionPerformed(evt);
            }
        });
        jMenu_archivo.add(jMenuItem_cimagenes);

        jMenuItem_calbumnes.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jMenuItem_calbumnes.setText("Cargar Álbumes");
        jMenuItem_calbumnes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_calbumnesActionPerformed(evt);
            }
        });
        jMenu_archivo.add(jMenuItem_calbumnes);

        jMenuBar1.add(jMenu_archivo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Login login = new Login(this.manager);
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem_ccapasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_ccapasActionPerformed
       JFileChooser chooser = new JFileChooser();
         
         FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "json");
         chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
         File fichero = chooser.getSelectedFile();
        this.manager.cargarCapas(this.cliente, fichero);
            System.out.println("PRE");
        this.cliente.arbolCapas.preOrden();
            System.out.println("POST");
            this.cliente.arbolCapas.postOrden();
            System.out.println("IN");
            this.cliente.arbolCapas.inOrden();
        }
      
    }//GEN-LAST:event_jMenuItem_ccapasActionPerformed

    private void jMenuItem_calbumnesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_calbumnesActionPerformed
         JFileChooser chooser = new JFileChooser();
         
         FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "json");
         chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
         File fichero = chooser.getSelectedFile();
        this.manager.cargarAlbumes(this.cliente, fichero); 
        }
    }//GEN-LAST:event_jMenuItem_calbumnesActionPerformed

    private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxActionPerformed

    private void jTextField_ReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ReportesActionPerformed

    private void jButton_visualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_visualizarActionPerformed
        if(this.jComboBox.getSelectedIndex()==0){
            try {
                manager.graficarImagenes(this.cliente);
                Thread.sleep(1000);
                this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
              

       } catch (InterruptedException e) {}
            ImageIcon imagen = new ImageIcon("Reportes de Usuario\\"+this.cliente.dpi+"\\ArbolImagenes.png");
            this.imgactual="Reportes de Usuario\\"+this.cliente.dpi+"\\ArbolImagenes.png";
            this.jLabel_visualizador.setIcon(imagen);
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        else if(this.jComboBox.getSelectedIndex()==1){
             try {
                manager.graficarCapas(this.cliente);
                Thread.sleep(1000);
                this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
              

       } catch (InterruptedException e) {}
            ImageIcon imagen = new ImageIcon("Reportes de Usuario\\"+this.cliente.dpi+"\\ArbolCapas.png");
            this.imgactual="Reportes de Usuario\\"+this.cliente.dpi+"\\ArbolCapas.png";
            this.jLabel_visualizador.setIcon(imagen);
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        
        }
        else if(this.jComboBox.getSelectedIndex()==2){
            try {
                manager.graficarAlbumes(this.cliente);
                Thread.sleep(1000);
                this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
              

       } catch (InterruptedException e) {}
            ImageIcon imagen = new ImageIcon("Reportes de Usuario\\"+this.cliente.dpi+"\\ListaAlbumes.png");
            this.imgactual="Reportes de Usuario\\"+this.cliente.dpi+"\\ArbolImagenes.png";
            this.jLabel_visualizador.setIcon(imagen);
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        else if(this.jComboBox.getSelectedIndex()==3){
            Capa capa = this.cliente.arbolCapas.buscar((this.jTextField_Reportes.getText()));
            try {
                if(capa!=null){
                
               capa.matriz.graficaLogica(String.valueOf(this.cliente.dpi));
            }
            else{
                System.out.println("No la encontre");
            }
               
                Thread.sleep(1000);
                this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
              

       } catch (InterruptedException e) {}
            ImageIcon imagen = new ImageIcon("Reportes de Usuario\\"+this.cliente.dpi+"\\capaLogica"+capa.id+".png");
            this.imgactual="Reportes de Usuario\\"+this.cliente.dpi+"\\capaLogica"+capa.id+".png";
            this.jLabel_visualizador.setIcon(imagen);
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); 
        }
        else if(this.jComboBox.getSelectedIndex()==4){
            try {
                Imagen img = this.cliente.arbolImagenes.buscar(Integer.valueOf(this.jTextField_Reportes.getText()));
                if(img!=null){
                manager.graficarImgCapas(this.cliente, img);
                }
                else{
                     JOptionPane.showMessageDialog(null, "Imagen no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                }
                Thread.sleep(1000);
                this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
              

       } catch (InterruptedException e) {}
            ImageIcon imagen = new ImageIcon("Reportes de Usuario\\"+this.cliente.dpi+"\\ImagenyCapas.png");
            this.imgactual="Reportes de Usuario\\"+this.cliente.dpi+"\\ImagenyCapas.png";
            this.jLabel_visualizador.setIcon(imagen);
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        this.jTextField_Reportes.setText("");
    }//GEN-LAST:event_jButton_visualizarActionPerformed

    private void jMenuItem_cimagenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_cimagenesActionPerformed
          JFileChooser chooser = new JFileChooser();
         
         FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "json");
         chooser.setFileFilter(filter);
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
         File fichero = chooser.getSelectedFile();
        this.manager.cargarImagenes(this.cliente, fichero);
        }
      
    }//GEN-LAST:event_jMenuItem_cimagenesActionPerformed

    private void jMenu_archivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu_archivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu_archivoActionPerformed

    private void jLabel_visualizadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_visualizadorMouseClicked
         try {

            File file = new File (this.imgactual);
            Desktop.getDesktop().open(file);

     }catch (IOException ex) {System.out.println(ex);}
    }//GEN-LAST:event_jLabel_visualizadorMouseClicked

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_visualizar;
    private javax.swing.JComboBox<String> jComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_nombre;
    private javax.swing.JLabel jLabel_visualizador;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem_calbumnes;
    private javax.swing.JMenuItem jMenuItem_ccapas;
    private javax.swing.JMenuItem jMenuItem_cimagenes;
    private javax.swing.JMenu jMenu_archivo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField_Reportes;
    // End of variables declaration//GEN-END:variables
}
