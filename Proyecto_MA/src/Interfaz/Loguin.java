package Interfaz;

import WS.Administrador;
import WS.Servicio;
import WS.Servicio_Service;
import java.util.List;
import javax.swing.JOptionPane;

public class Loguin extends javax.swing.JFrame {

    Servicio_Service ws = new Servicio_Service();
    Servicio servicio;

    public Loguin() {
        initComponents();
        servicio = ws.getServicioPort();
        setLocationRelativeTo(null);
        jLblTexto.setText(null);
    }

    private void validarAdmin() {
        String user = jTxtUser.getText();
        String pass = jTxtPass.getText();
        List<Administrador> lista = servicio.listaAdministradores(user);

        if (jTxtPass.getText().isEmpty() || jTxtUser.getText().isEmpty()) {
            jLblTexto.setText("Debe ingresar todos los campos");
        } else if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error de Ingreso", JOptionPane.ERROR_MESSAGE);
            jLblTexto.setText(null);
        }else if (lista.get(0).getCedula().equals(user) && lista.get(0).getContraseña().equals(pass)) {
            Menu menu = new Menu();
            menu.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error de Ingreso", JOptionPane.ERROR_MESSAGE);
            jLblTexto.setText(null);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTxtUser = new javax.swing.JTextField();
        jBtnLogon = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTxtPass = new javax.swing.JPasswordField();
        jLblTexto = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SISTEMA DE");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("VALIDACIÓN");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jTxtUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTxtUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(jTxtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 300, 30));

        jBtnLogon.setBackground(new java.awt.Color(0, 0, 0));
        jBtnLogon.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jBtnLogon.setForeground(new java.awt.Color(255, 255, 255));
        jBtnLogon.setText("LOGIN");
        jBtnLogon.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 0));
        jBtnLogon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnLogonActionPerformed(evt);
            }
        });
        jPanel1.add(jBtnLogon, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 323, 130, 40));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/x.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 40, 40));

        jTxtPass.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTxtPass.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.add(jTxtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 240, 300, 30));

        jLblTexto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLblTexto.setForeground(new java.awt.Color(255, 0, 0));
        jLblTexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLblTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 300, 230, 20));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Login.jpg"))); // NOI18N
        jPanel1.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnLogonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnLogonActionPerformed
        validarAdmin();
    }//GEN-LAST:event_jBtnLogonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Loguin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loguin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loguin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loguin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loguin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo;
    private javax.swing.JButton jBtnLogon;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLblTexto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jTxtPass;
    private javax.swing.JTextField jTxtUser;
    // End of variables declaration//GEN-END:variables
}
