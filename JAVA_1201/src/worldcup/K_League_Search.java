package worldcup;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class K_League_Search extends JFrame {
    K_League_GUI gui;
    JPanel nnorthp, northp, southp;
    JLabel title, name;
    JTextField tname;
    JButton menu, search, clear;
    Font f1, f2;

    boolean flag = false;
    boolean DBConnectTF = true;

    // JTable Component
    DefaultTableModel dtm;
    JTable table;
    JScrollPane scroll;

    // Database Component
    DBConnection dbconn;
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs = null;
    MyActionListener myListener;

    public K_League_Search(K_League_GUI gui) {
        // DB 연결
        this.dbconn = DBConnection.getInstance();
        this.gui = gui;

        // Setting
        this.setTitle("World Cup Player");
        this.setLayout(null);
        this.setSize(650, 600);
        this.setLocationRelativeTo(null);

        nnorthp = new JPanel();
        nnorthp.setLayout(null);
        nnorthp.setBounds(0, 0, 650, 50);

        title = new JLabel("K_League_Player 선수 검색", JLabel.CENTER);
        title.setBounds(0, 20, 600, 25);

        f1 = new Font("돋움", Font.BOLD, 23);
        f2 = new Font("serif", Font.BOLD, 20);

        title.setFont(f1);
        title.setForeground(Color.WHITE);
        nnorthp.setBackground(Color.BLACK);
        nnorthp.add(title);
        this.add(nnorthp);

        northp = new JPanel();
        northp.setLayout(null);
        northp.setBounds(0, 60, 630, 130);

        northp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" 입력 "),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        name = new JLabel("이름 검색 :");
        tname = new JTextField(20);
        tname.setText(null);
        search = new JButton("검 색");
        clear = new JButton("초기화");

        name.setBounds(50, 40, 70, 30);
        tname.setBounds(130, 40, 180, 30);
        search.setBounds(320, 40, 100, 30);
        clear.setBounds(430, 40, 130, 30);

        search.setFont(f1);
        search.setBackground(Color.CYAN);
        search.setForeground(Color.RED);
        clear.setFont(f1);
        clear.setBackground(Color.CYAN);
        clear.setForeground(Color.RED);

        northp.add(name);
        northp.add(tname);
        northp.add(search);
        northp.add(clear);

        this.add(northp);

        //=======================================================================================
        southp = new JPanel();
        southp.setLayout(new FlowLayout(FlowLayout.CENTER));
        southp.setBounds(0, 510, 600, 90);

        menu = new JButton("메인 메뉴");

        menu.setSize(150, 50);

        menu.setFont(f2);
        menu.setBackground(Color.BLUE);
        menu.setForeground(Color.YELLOW);

        southp.add(menu);
        this.add(southp);

        //=======================================================================================
        // 테이블 컬럼명 1차원 배열
        String columnNames[] = { "번 호", "이 름", "나 이", "몸무게", "포지션", "등번호", "연고지" };
        // 테이블 데이터 2차원 배열
        Object[][] rowData = {     };

        dtm = new DefaultTableModel(rowData, columnNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(dtm);    // 모델에 테이블 연결
        scroll = new JScrollPane(table);
        scroll.setBounds(5, 200, 620, 300);
        this.add(scroll);

        table.setBackground(new Color(255, 255, 204));
        table.setRowHeight(15); // 테이블 행 높이 조절

        // Set ActionListener
        myListener = new MyActionListener(gui);
        search.addActionListener(myListener);
        clear.addActionListener(myListener);
        menu.addActionListener(myListener);

        // DB 레코드 테이블 표시
        player_Display();

        this.setVisible(true);
    }

    private void player_Display() {
        dtm.setRowCount(0); // JTable 레코드 포인터(0) rowCount 초기화

        try {
            String query = "Select * From player";
            pstmt = dbconn.conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String idx = rs.getString(1);
                String name = rs.getString(2);
                String age = rs.getString(3);
                String weight = rs.getString(4);
                String position = rs.getString(5);
                String backNumber = rs.getString(6);
                String hometown = rs.getString(7);

                // JTable 배열로 생성 추가
                Object[] rowData = {idx, name, age, weight, position, backNumber, hometown};
                dtm.addRow(rowData);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e1) {
            JOptionPane.showMessageDialog(null, "DB Connection ERR");
            DBConnectTF = false;
        }
    }

    private void player_Display(String searchName) {
        dtm.setRowCount(0); // JTable 레코드 포인터(0) rowCount 초기화
        int inx = 0;

        try {
            String query = "Select * From player Where name Like ?";
            pstmt = dbconn.conn.prepareStatement(query);
            pstmt.setString(1, "%"+searchName+"%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                String idx = rs.getString(1);
                String name = rs.getString(2);
                String age = rs.getString(3);
                String weight = rs.getString(4);
                String position = rs.getString(5);
                String backNumber = rs.getString(6);
                String hometown = rs.getString(7);

                // JTable 배열로 생성 추가
                Object[] rowData = {idx, name, age, weight, position, backNumber, hometown};
                dtm.addRow(rowData);
                inx++;
            }

            if(inx == 0) {
                JOptionPane.showMessageDialog(null, "검색된 정보가 없습니다.");
                tname.setText("");
                player_Display();
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e1) {
            JOptionPane.showMessageDialog(null, "DB Connection ERR");
        }
    }

    class MyActionListener implements ActionListener {
        K_League_GUI gui;

        public MyActionListener(K_League_GUI gui) {
            this.gui = gui;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ae = e.getSource();

            if(ae == search && DBConnectTF == true) {
                player_Display(tname.getText());

            }else if(ae == clear && DBConnectTF == true) {
                player_Display();
                tname.setText("");

            }else if(ae == menu) {
                gui.setVisible(true);
                dispose();
            }
        }
    }
}
