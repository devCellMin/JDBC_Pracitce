package worldcup;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class K_League_Player extends JFrame implements ActionListener, KeyListener {
   JPanel nnorthp;
   JPanel northp;   
   JPanel southp;   

   JLabel title;
   JLabel name;
   JLabel age;
   JLabel weight;
   JLabel position;
   JLabel backNumber;
   JLabel hometown1;

   JTextField tname;
   JTextField tage;
   JTextField tweight;
   JTextField tposition;
   JTextField tbackNumber;
   JTextField thometown1;

   JButton menu;
   JButton insert;

   Font f1, f2;

   boolean flag = false;

   // JTable Component
   DefaultTableModel dtm;
   JTable table;
   JScrollPane scroll;   

   // Database Component
   DBConn dbconn;
   Statement stmt;
   PreparedStatement pstmt;
   ResultSet rs = null;
   int idx;

   //K_League_Insert(K_League_GUI gui) {
   K_League_Player() {

   //   this.gui = gui;
      this.setTitle("World Cup Player");
      
      this.setLayout(null);
      this.setBounds(50, 50, 650, 800);      

      nnorthp = new JPanel();
      nnorthp.setLayout(null);
      nnorthp.setBounds(0, 0, 650, 50);

      title = new JLabel("K_League_Player 선수 추가", JLabel.CENTER);
      title.setBounds(0, 10, 600, 25);

      f1 = new Font("돋움", Font.BOLD, 23);
      f2 = new Font("serif", Font.BOLD, 20);

      title.setFont(f1);
      title.setForeground(Color.WHITE);
      nnorthp.setBackground(Color.BLACK);
      nnorthp.add(title);
      this.add(nnorthp);

      northp = new JPanel();
      northp.setLayout(null);
      northp.setBounds(0, 60, 630, 330);

      northp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" 입력 "),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

      name = new JLabel("이 름");
      tname = new JTextField(20);
      tname.setText(null);
      age = new JLabel("나 이");
      tage = new JTextField(20);
      weight = new JLabel("몸무게");
      tweight = new JTextField(20);
      position = new JLabel("포지션");
      tposition = new JTextField(20);
      backNumber = new JLabel("등번호");
      tbackNumber = new JTextField();
      hometown1 = new JLabel("연고지");
      thometown1 = new JTextField();

      name.setBounds(50, 40, 35, 25);
      tname.setBounds(170, 40, 180, 25);
      age.setBounds(50, 90, 35, 25);
      tage.setBounds(170, 90, 180, 25);
      weight.setBounds(50, 140, 70, 25);
      tweight.setBounds(170, 140, 180, 25);
      position.setBounds(50, 190, 70, 25);
      tposition.setBounds(170, 190, 180, 25);
      backNumber.setBounds(50, 240, 70, 25);
      tbackNumber.setBounds(170, 240, 180, 25);
      hometown1.setBounds(50, 290, 70, 25);
      thometown1.setBounds(170, 290, 180, 25);

      northp.add(name);
      northp.add(tname);
      northp.add(age);
      northp.add(tage);
      northp.add(weight);
      northp.add(tweight);
      northp.add(position);
      northp.add(tposition);
      northp.add(backNumber);
      northp.add(tbackNumber);
      northp.add(hometown1);
      northp.add(thometown1);
      this.add(northp);

///////////////////////////////////////////////
      // 텍스트 필드 방향키 입력시 이벤트 처리
      tname.addKeyListener(this);
      tage.addKeyListener(this);
      tweight.addKeyListener(this);
      tposition.addKeyListener(this);
      tbackNumber.addKeyListener(this);
      thometown1.addKeyListener(this);

      southp = new JPanel();
      southp.setLayout(null);
      southp.setBounds(0, 600, 600, 200);

      menu = new JButton("메인 메뉴");
      insert = new JButton("선수 추가");

      menu.setBounds(170, 70, 150, 50);
      insert.setBounds(340, 70, 150, 50);

      menu.setFont(f2);
      menu.setBackground(Color.BLUE);
      menu.setForeground(Color.YELLOW);
      insert.setFont(f2);
      insert.setBackground(Color.YELLOW);
      insert.setForeground(Color.BLACK);

      insert.addActionListener(this);
      menu.addActionListener(this);

      southp.add(menu);
      southp.add(insert);
      this.add(southp);

//////////////////////////////////////////////////////////////            
      // 테이블 컬럼명 1차원 배열
      String columnNames[] = { "번 호", "이 름", "나 이", "몸무게", "포지션", "등번호", "연고지" };
      // 테이블 데이터 2차원 배열
      Object[][] rowData = {     };

      dtm = new DefaultTableModel(rowData, columnNames);      
      table = new JTable(dtm);    // 모델에 테이블 연결
      scroll = new JScrollPane(table);
      scroll.setBounds(5, 400, 620, 200);
      this.add(scroll);

      table.setBackground(new Color(255, 255, 204));
      table.setRowHeight(15); // 테이블 행 높이 조절      

      this.setVisible(true);
   }

   public static void main(String[] args) {
      // TODO Auto-generated method stub
      new K_League_Player();
   }

   @Override
   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      
   }

}