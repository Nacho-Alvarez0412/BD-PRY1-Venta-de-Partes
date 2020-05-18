/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

/**
 *
 * @author nacho
 */
public class DeletePartMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public DeletePartMenu() {
        initComponents();
        this.setSize(995, 650);
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        BackgroundLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        InsertButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        ParteComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        BackgroundLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/AppTitle_1.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(370, 0, 290, 240);

        BackgroundLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Background1.jpg"))); // NOI18N
        getContentPane().add(BackgroundLabel1);
        BackgroundLabel1.setBounds(-20, -290, 1020, 450);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/Tool.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 230, 160, 180);

        InsertButton.setBackground(new java.awt.Color(213, 213, 213));

        InsertButton.setForeground(new java.awt.Color(4, 83, 125));
        InsertButton.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        InsertButton.setForeground(new java.awt.Color(4, 83, 125));
        InsertButton.setText("Borrar");
        InsertButton.setToolTipText("");
        InsertButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        InsertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertButtonActionPerformed(evt);
            }
        });
        getContentPane().add(InsertButton);
        InsertButton.setBounds(440, 430, 150, 50);

        ExitButton.setBackground(new java.awt.Color(213, 213, 213));
        ExitButton.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        ExitButton.setForeground(new java.awt.Color(4, 83, 125));
        ExitButton.setText("Atrás");
        ExitButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ExitButton);
        ExitButton.setBounds(20, 530, 170, 50);

        ParteComboBox.setFont(new java.awt.Font("Arial Narrow", 0, 18)); // NOI18N
        ParteComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        getContentPane().add(ParteComboBox);
        ParteComboBox.setBounds(410, 330, 210, 40);

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(4, 83, 125));
        jLabel2.setText("Borrar Parte");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(430, 260, 180, 50);

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(4, 83, 125));
        jLabel4.setText("Borrar Parte");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 410, 190, 50);

        BackgroundLabel.setBackground(new java.awt.Color(255, 255, 255));
        BackgroundLabel.setOpaque(true);
        getContentPane().add(BackgroundLabel);
        BackgroundLabel.setBounds(0, 160, 1020, 450);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void InsertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InsertButtonActionPerformed

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
            java.util.logging.Logger.getLogger(DeletePartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeletePartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeletePartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeletePartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DeletePartMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackgroundLabel;
    private javax.swing.JLabel BackgroundLabel1;
    public javax.swing.JButton ExitButton;
    public javax.swing.JButton InsertButton;
    public javax.swing.JComboBox<String> ParteComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    // End of variables declaration//GEN-END:variables
}
