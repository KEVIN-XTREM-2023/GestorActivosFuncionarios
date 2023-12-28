
package Interfaz;

public class Menu extends javax.swing.JFrame {

    public Menu() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jBtnFuncionarios = new javax.swing.JButton();
        jBtnCrear = new javax.swing.JButton();
        jBtnSalir = new javax.swing.JButton();
        jBtnValidacion = new javax.swing.JButton();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBtnFuncionarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Funcionarios.jpg"))); // NOI18N
        jBtnFuncionarios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnFuncionarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnFuncionariosActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnFuncionarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 140, 140));

        jBtnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/crearValidacion.jpg"))); // NOI18N
        jBtnCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCrearActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 150, 150));

        jBtnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Salir.jpg"))); // NOI18N
        jBtnSalir.setContentAreaFilled(false);
        jBtnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 130, 140));

        jBtnValidacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/validaciones.jpg"))); // NOI18N
        jBtnValidacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBtnValidacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnValidacionActionPerformed(evt);
            }
        });
        jPanel2.add(jBtnValidacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, 130, 140));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/menu.jpg"))); // NOI18N
        jPanel2.add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnSalirActionPerformed
        Loguin lg = new Loguin();
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnSalirActionPerformed

    private void jBtnFuncionariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnFuncionariosActionPerformed
        ListaFuncionarios ventana = new ListaFuncionarios();
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnFuncionariosActionPerformed

    private void jBtnValidacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnValidacionActionPerformed
        ListaValidaciones ventana = new ListaValidaciones();
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnValidacionActionPerformed

    private void jBtnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCrearActionPerformed
        VentanaValidacion ventana = new VentanaValidacion();
        ventana.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBtnCrearActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo;
    private javax.swing.JButton jBtnCrear;
    private javax.swing.JButton jBtnFuncionarios;
    private javax.swing.JButton jBtnSalir;
    private javax.swing.JButton jBtnValidacion;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
