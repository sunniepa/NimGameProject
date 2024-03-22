/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.nimgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import com.mycompany.nimgame.HomePage;
import com.mycompany.nimgame.EnterName2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

/**
 *
 * @author DELL
 */
public class PlayWithPerson extends javax.swing.JFrame {

    /**
     * Creates new form PlayGame
     */
    public PlayWithPerson(String namep1, String namep2) {
        initComponents();
        //init();
        txt_nguoi1.setText(namep1);
        txt_nguoi2.setText(namep2);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        File f = new File("file.txt");
        if (f.exists()) {
            open();
        } else {
         init();
        }
    }

    int[] arrayA, arrayB, arrayC, arrayD, arrayE;
    int numA, numB, numC, numD, numE, total;
    int choseClick = 0;
    int location = 0;
    int nguoi1;
    int nguoi2;
    int count = 0;
    int remaining;

    private void init() {
        Random rd = new Random();
        numA = rd.nextInt(7 - 1) + 1;
        numB = rd.nextInt(7 - 1) + 1;
        numC = rd.nextInt(7 - 1) + 1;
        numD = rd.nextInt(7 - 1) + 1;
        numE = rd.nextInt(7 - 1) + 1;
        total = numA + numB + numC + numD + numE;
        remaining = total;
        nguoi1 = 1;
        nguoi2 = 2;
        location = 0;
        choseClick = 0;
        remain();

        arrayA = new int[numA];
        arrayB = new int[numB];
        arrayC = new int[numC];
        arrayD = new int[numD];
        arrayE = new int[numE];

        for (int i = 0; i < numA; i++) {
            arrayA[i] = 1;
        }
        for (int i = 0; i < numB; i++) {
            arrayB[i] = 1;
        }
        for (int i = 0; i < numC; i++) {
            arrayC[i] = 1;
        }
        for (int i = 0; i < numD; i++) {
            arrayD[i] = 1;
        }
        for (int i = 0; i < numE; i++) {
            arrayE[i] = 1;
        }

        txt_totalNum.setText(String.valueOf(total));
        int y = 80;
        creLab(colA);
        creLab(colB);
        creLab(colC);
        creLab(colD);
        creLab(colE);

        createBtn(colA, arrayA, numA);
        createBtn(colB, arrayB, numB);
        createBtn(colC, arrayC, numC);
        createBtn(colD, arrayD, numD);
        createBtn(colE, arrayE, numE);

        nguoichoi1.setBackground(Color.GREEN);
        nguoichoi2.setBackground(Color.GRAY);
    }

    public void creLab(JPanel colName) {
        JLabel lb = new JLabel();
        colName.add(lb);
        lb.setSize(50, 30);
        if (colName == colA) {
            lb.setText("Cột A");
        } else if (colName == colB) {
            lb.setText("Cột B");
        } else if (colName == colC) {
            lb.setText("Cột C");
        } else if (colName == colD) {
            lb.setText("Cột D");
        } else if (colName == colE) {
            lb.setText("Cột E");
        }
        lb.setLocation(26, 30);
        lb.setFont(new Font("Segoe UI", Font.BOLD, 14));
    }

    public void createBtn(JPanel colName, int[] array, int num) {
        int y = 80;
        creLab(colName);
        for (int i = 0; i < num; i++) {
            if (array[i] == 1) {
                JButton btni = new JButton();
                btni.setBackground(Color.PINK);
                btni.setSize(50, 50);
                btni.setLocation(25, y);
                y = y + 70;
                colName.add(btni);
                int temp = i;

                btni.addActionListener((e) -> {
                    if (btni.getBackground() == Color.BLACK) {
                        btni.setBackground(Color.PINK);
                        array[temp] = 1;
                        if (choseClick == 1) {
                            location -= colName.getX();
                        }
                        choseClick -= 1;
                        count--;
                    } else {
                        if (location == 0 || location == colName.getX()) {
                            btni.setBackground(Color.BLACK);
                            array[temp] = 0;
                            choseClick += 1;
                            if (location == 0) {
                                location += colName.getX();
                            } else {
                                location += 0;
                            }
                            count++;
                        } else {
                            JOptionPane.showMessageDialog(null, "Không được chọn khác cột!!!");
                        }
                    }
                });
            }
        }
    }

    public void createCol(JPanel colName, int[] array, int num) {
        choseIsZero();
        colName.removeAll();
        createBtn(colName, array, num);
        repaint();
        choseClick = 0;
        location = 0;
    }

    public void remain() {
        txt_remaining.setText(String.valueOf(remaining));
    }

    public void resetInit() {
        colA.removeAll();
        colB.removeAll();
        colC.removeAll();
        colD.removeAll();
        colE.removeAll();
        init();
        count = 0;
        location = 0;
    }

    public void replay() {
        resetInit();
        repaint();
    }

    public void isWin() {
        if (remaining == 0) {
            String ten;
            nguoichoi1.setBackground(Color.WHITE);
            nguoichoi2.setBackground(Color.WHITE);
            if (nguoi1 >= nguoi2) {
                ten = txt_nguoi1.getText();
            } else {
                ten = txt_nguoi2.getText();
            }
            JOptionPane.showConfirmDialog(null, "Chúc mừng người chơi " + ten + " chiến thắng",
                    "Thông báo", JOptionPane.CLOSED_OPTION);
            replay();
        }
    }

    public void playerColor() {
        if (nguoi1 < nguoi2) {
            nguoichoi1.setBackground(Color.GRAY);
            nguoichoi2.setBackground(Color.GREEN);
            nguoi1++;
        } else {
            nguoichoi2.setBackground(Color.GRAY);
            nguoichoi1.setBackground(Color.GREEN);
            nguoi2++;
        }
    }

    public void choseIsZero() {
        if (choseClick == 0) {
            JOptionPane.showMessageDialog(null, "Bạn phải chọn ít nhất 1 phần tử", "Thông báo", JOptionPane.YES_OPTION);
            playerColor();
        }
    }

    //Luu trang thai hien tai
    public void save() {
        try {
            PrintWriter bw = new PrintWriter("file.txt", "UTF-8");
            int tempA = 0;
            int tempB = 0;
            int tempC = 0;
            int tempD = 0;
            int tempE = 0;
            int numAmount = 0;
            if (choseClick != 0) {
                if (location == colA.getX()) {
                    tempA += choseClick;
                    numAmount += choseClick;
                } else if (location == colB.getX()) {
                    tempB += choseClick;
                    numAmount += choseClick;
                } else if (location == colC.getX()) {
                    tempC += choseClick;
                    numAmount += choseClick;
                } else if (location == colD.getX()) {
                    tempD += choseClick;
                    numAmount += choseClick;
                } else if (location == colE.getX()) {
                    tempE += choseClick;
                    numAmount += choseClick;
                }
            }
            for (int i = 0; i < numA; i++) {
                if (arrayA[i] == 1) {
                    tempA += 1;
                    numAmount++;
                }
            }
            bw.write(tempA + ";");

            for (int i = 0; i < numB; i++) {
                if (arrayB[i] == 1) {
                    tempB += 1;
                    numAmount++;
                }
            }
            bw.write(tempB + ";");

            for (int i = 0; i < numC; i++) {
                if (arrayC[i] == 1) {
                    tempC += 1;
                    numAmount++;
                }
            }
            bw.write(tempC + ";");

            for (int i = 0; i < numD; i++) {
                if (arrayD[i] == 1) {
                    tempD += 1;
                    numAmount++;
                }
            }
            bw.write(tempD + ";");

            for (int i = 0; i < numE; i++) {
                if (arrayE[i] == 1) {
                    tempE += 1;
                    numAmount++;
                }
            }
            bw.write(tempE + ";" + total + ";" + numAmount);
//            bw.print(";" + p1lb.getText());
//            bw.print(";" + p2lb.getText());
            bw.write(";" + nguoi1 + ";" + nguoi2);
            bw.write(";" + txt_nguoi1.getText() + ";" + txt_nguoi2.getText());
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loaddata() {
        colA.removeAll();
        colB.removeAll();
        colC.removeAll();
        colD.removeAll();
        colE.removeAll();
        choseClick = 0;
        location = 0;
        arrayA = new int[numA];
        for (int i = 0; i < numA; i++) {
            arrayA[i] = 1;
        }
        arrayB = new int[numB];
        for (int i = 0; i < numB; i++) {
            arrayB[i] = 1;
        }
        arrayC = new int[numC];
        for (int i = 0; i < numC; i++) {
            arrayC[i] = 1;
        }
        arrayD = new int[numD];
        for (int i = 0; i < numD; i++) {
            arrayD[i] = 1;
        }
        arrayE = new int[numE];
        for (int i = 0; i < numE; i++) {
            arrayE[i] = 1;
        }

        txt_totalNum.setText(String.valueOf(total));
        remain();
        creLab(colA);
        creLab(colB);
        creLab(colC);
        creLab(colD);
        creLab(colE);

        createBtn(colA, arrayA, numA);
        createBtn(colB, arrayB, numB);
        createBtn(colC, arrayC, numC);
        createBtn(colD, arrayD, numD);
        createBtn(colE, arrayE, numE);
        if (nguoi1>=nguoi2) {
            nguoichoi2.setBackground(Color.GREEN);
            nguoichoi1.setBackground(Color.GRAY);
            
        } else {
            nguoichoi1.setBackground(Color.GREEN);
            nguoichoi2.setBackground(Color.GRAY);
        }
        //remain();
        repaint();
    }

    public void open() {
        try {
            FileReader f = new FileReader("file.txt", StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(f);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                } else {
                    String txt[] = line.split(";");
                    numA = Integer.parseInt(txt[0]);
                    numB = Integer.parseInt(txt[1]);
                    numC = Integer.parseInt(txt[2]);
                    numD = Integer.parseInt(txt[3]);
                    numE = Integer.parseInt(txt[4]);
                    total = Integer.parseInt(txt[5]);
                    txt_totalNum.setText(txt[5]);
                    remaining=Integer.parseInt(txt[6]);
                    nguoi1 = Integer.parseInt(txt[7]);
                    nguoi2 = Integer.parseInt(txt[8]);
                    txt_nguoi1.setText(txt[9]);
                    txt_nguoi2.setText(txt[10]);
                }
            }
            loaddata();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private PlayWithPerson(String back_Button_Example) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bb = new javax.swing.JPanel();
        NimGame = new javax.swing.JLabel();
        btn_choi = new javax.swing.JButton();
        btn_thoat = new javax.swing.JButton();
        btn_trove = new javax.swing.JButton();
        txt_totalNum = new javax.swing.JLabel();
        txt_remaining = new javax.swing.JLabel();
        lb_soluongbandau = new javax.swing.JLabel();
        nguoichoi1 = new javax.swing.JPanel();
        txt_nguoi1 = new javax.swing.JLabel();
        lb_soluongconlai = new javax.swing.JLabel();
        btn_choilai = new javax.swing.JButton();
        nguoichoi2 = new javax.swing.JPanel();
        txt_nguoi2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        colA = new javax.swing.JPanel();
        colB = new javax.swing.JPanel();
        colC = new javax.swing.JPanel();
        colD = new javax.swing.JPanel();
        colE = new javax.swing.JPanel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chơi với người");
        setResizable(false);

        bb.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NimGame.setFont(new java.awt.Font("Ravie", 1, 48)); // NOI18N
        NimGame.setText("NIM GAME");
        bb.add(NimGame, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 25, -1, -1));

        btn_choi.setBackground(java.awt.SystemColor.activeCaption);
        btn_choi.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btn_choi.setText("Chơi");
        btn_choi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_choiActionPerformed(evt);
            }
        });
        bb.add(btn_choi, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 690, 150, 50));

        btn_thoat.setBackground(java.awt.SystemColor.activeCaption);
        btn_thoat.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btn_thoat.setText("Thoát");
        btn_thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thoatActionPerformed(evt);
            }
        });
        bb.add(btn_thoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 690, 150, 50));

        btn_trove.setBackground(java.awt.SystemColor.activeCaption);
        btn_trove.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btn_trove.setText("Trở về");
        btn_trove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_troveActionPerformed(evt);
            }
        });
        bb.add(btn_trove, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 690, 150, 50));

        txt_totalNum.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_totalNum.setText("totalNum");
        bb.add(txt_totalNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 18, -1, -1));

        txt_remaining.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txt_remaining.setText("remaining");
        bb.add(txt_remaining, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        lb_soluongbandau.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lb_soluongbandau.setText("Số lượng ban đầu:");
        bb.add(lb_soluongbandau, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        nguoichoi1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txt_nguoi1.setBackground(new java.awt.Color(255, 255, 255));
        txt_nguoi1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        txt_nguoi1.setForeground(new java.awt.Color(255, 255, 255));
        txt_nguoi1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_nguoi1.setText("Player1");
        txt_nguoi1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        txt_nguoi1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout nguoichoi1Layout = new javax.swing.GroupLayout(nguoichoi1);
        nguoichoi1.setLayout(nguoichoi1Layout);
        nguoichoi1Layout.setHorizontalGroup(
            nguoichoi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nguoichoi1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_nguoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        nguoichoi1Layout.setVerticalGroup(
            nguoichoi1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nguoichoi1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_nguoi1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bb.add(nguoichoi1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 180, 70));

        lb_soluongconlai.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lb_soluongconlai.setText("Số lượng còn lại:");
        bb.add(lb_soluongconlai, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 160, -1));

        btn_choilai.setBackground(java.awt.SystemColor.activeCaption);
        btn_choilai.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        btn_choilai.setText("Chơi lại");
        btn_choilai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_choilaiActionPerformed(evt);
            }
        });
        bb.add(btn_choilai, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 690, 150, 50));

        nguoichoi2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txt_nguoi2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        txt_nguoi2.setForeground(new java.awt.Color(255, 255, 255));
        txt_nguoi2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_nguoi2.setText("Player2");
        txt_nguoi2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout nguoichoi2Layout = new javax.swing.GroupLayout(nguoichoi2);
        nguoichoi2.setLayout(nguoichoi2Layout);
        nguoichoi2Layout.setHorizontalGroup(
            nguoichoi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nguoichoi2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_nguoi2, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                .addContainerGap())
        );
        nguoichoi2Layout.setVerticalGroup(
            nguoichoi2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nguoichoi2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_nguoi2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bb.add(nguoichoi2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1163, 350, 180, 70));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        colA.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        colA.setPreferredSize(new java.awt.Dimension(100, 485));

        javax.swing.GroupLayout colALayout = new javax.swing.GroupLayout(colA);
        colA.setLayout(colALayout);
        colALayout.setHorizontalGroup(
            colALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        colALayout.setVerticalGroup(
            colALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout colBLayout = new javax.swing.GroupLayout(colB);
        colB.setLayout(colBLayout);
        colBLayout.setHorizontalGroup(
            colBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        colBLayout.setVerticalGroup(
            colBLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout colCLayout = new javax.swing.GroupLayout(colC);
        colC.setLayout(colCLayout);
        colCLayout.setHorizontalGroup(
            colCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        colCLayout.setVerticalGroup(
            colCLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout colDLayout = new javax.swing.GroupLayout(colD);
        colD.setLayout(colDLayout);
        colDLayout.setHorizontalGroup(
            colDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        colDLayout.setVerticalGroup(
            colDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout colELayout = new javax.swing.GroupLayout(colE);
        colE.setLayout(colELayout);
        colELayout.setHorizontalGroup(
            colELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        colELayout.setVerticalGroup(
            colELayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(colA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(colB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(colC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(colD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(colE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(colA, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                    .addComponent(colB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(colC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(colD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(colE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );

        bb.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 110, 920, 560));

        background.setBackground(new java.awt.Color(255, 255, 255));
        background.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        background.setIcon(new javax.swing.ImageIcon("..\\NimGame\\src\\main\\java\\images\\Bio.png")); // NOI18N
        bb.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 750));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bb, javax.swing.GroupLayout.PREFERRED_SIZE, 1364, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_choiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_choiActionPerformed
        choseIsZero();
         remaining -=choseClick;
        if (location == colA.getX()) {
            createCol(colA, arrayA, numA);
        } else if (location == colB.getX()) {
            createCol(colB, arrayB, numB);
        } else if (location == colC.getX()) {
            createCol(colC, arrayC, numC);
        } else if (location == colD.getX()) {
            createCol(colD, arrayD, numD);
        } else if (location == colE.getX()) {
            createCol(colE, arrayE, numE);
        }
        remain();
        playerColor();
        isWin();
    }//GEN-LAST:event_btn_choiActionPerformed

    private void btn_thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thoatActionPerformed
        int optionWindowClosing = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thoát khỏi trò chơi không?", "Thoát", JOptionPane.YES_NO_OPTION);
        if (optionWindowClosing == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btn_thoatActionPerformed

    private void btn_troveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_troveActionPerformed

//                int trove = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn trở về", "Trở về", JOptionPane.YES_NO_OPTION);
//                if (trove == JOptionPane.YES_OPTION) {
//                    HomePage homePage = new HomePage();
//                    homePage.setVisible(true);
//                    setVisible(false);
//                }
        Object[] option = {
            "Có",
            "Không"
        };
        int x = JOptionPane.showOptionDialog(null, "Bạn có muốn lưu trạng thái hiện hành hay không ?", "Thông báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
        if (x == JOptionPane.YES_OPTION) {
            save();
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
            setVisible(false);
        } else {
            HomePage homePage = new HomePage();
            homePage.setVisible(true);
            setVisible(false);
            File f = new File("file.txt");
            f.delete();
        }

    }//GEN-LAST:event_btn_troveActionPerformed

    private void btn_choilaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_choilaiActionPerformed
        replay();
    }//GEN-LAST:event_btn_choilaiActionPerformed

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
            java.util.logging.Logger.getLogger(PlayWithPerson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayWithPerson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayWithPerson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayWithPerson.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayWithPerson("Player1", "Player2").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NimGame;
    private javax.swing.JLabel background;
    private javax.swing.JPanel bb;
    private javax.swing.JButton btn_choi;
    private javax.swing.JButton btn_choilai;
    private javax.swing.JButton btn_thoat;
    private javax.swing.JButton btn_trove;
    private javax.swing.JPanel colA;
    private javax.swing.JPanel colB;
    private javax.swing.JPanel colC;
    private javax.swing.JPanel colD;
    private javax.swing.JPanel colE;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lb_soluongbandau;
    private javax.swing.JLabel lb_soluongconlai;
    private javax.swing.JPanel nguoichoi1;
    private javax.swing.JPanel nguoichoi2;
    private javax.swing.JLabel txt_nguoi1;
    private javax.swing.JLabel txt_nguoi2;
    private javax.swing.JLabel txt_remaining;
    private javax.swing.JLabel txt_totalNum;
    // End of variables declaration//GEN-END:variables

    private void addActionListener(ActionListener actionListener) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
