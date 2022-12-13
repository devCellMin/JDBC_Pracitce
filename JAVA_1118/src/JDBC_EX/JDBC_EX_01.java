package JDBC_EX;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JDBC_EX_01 extends JFrame implements ActionListener, MouseListener{
   JTable table;           // JTable
   DefaultTableModel dtm;
   JScrollPane scroll;
   
   Toolkit tk;            // 이미지 아이콘(없어도 됨)
   Image logo;
   
   JPanel nnorthp;
   JPanel northp;
   JPanel orthp;
   JPanel tablep;
   JPanel southp;
   
   JLabel title;
   JLabel name;
   JLabel age;
   JLabel gender1;
   JLabel addr;
   JLabel phone;
   JLabel email;
   
   String g ;               // 성별 값
   String quary;            // Quary 문 
   String category = null;  // 검색 값
   
   CheckboxGroup gender;   // 성별 그룹처리
   Checkbox male, female;
   
   JTextField tname;
   JTextField tage;
   JTextField taddr;
   JTextField tphone1;
   JTextField tphone2;
   JTextField tphone3;
   
   JTextField temail1;
   JTextField temail2;
   JComboBox emailcb;
      
   JComboBox search;
   JTextField tsearch;
   JButton bsearch;
   JButton tbsearch;
   
   JButton input;
   JButton edit;
   JButton delete;
   JButton exit;
   
//   String driver = "com.mysql.jdbc.Driver";
//   String user = "root";
//   String pass = "1111";
//   String dbURL = "jdbc:mysql://localhost/member";
//     
//   Connection conn;
//   Statement stmt;
//   PreparedStatement pstmt;
//   ResultSet rs = null;
//   
   Font f1, f2;
   int selRow = 0;
   
   public JDBC_EX_01() {
      this.setTitle("자바 주소록 관리 프로그램");
      this.setBounds(0,0,600,650);
      
      Toolkit tk = Toolkit.getDefaultToolkit();  // 이미지 얻어와서 출력
      logo = tk.getImage("C:\\users.png");      // 프레임에 이미지 출력 
      this.setIconImage(logo);                   // JFrame에 올리기
      
       this.setLayout(null);     // 프레임 자유롭게 배치
      
      northp = new JPanel();
      nnorthp = new JPanel();
      nnorthp.setLayout(null);  // 컨버전스 레이블 올린 패널  
      nnorthp.setBounds(0,0,600,40);
      
      JLabel title = new JLabel("주소록관리 프로그램");
      title.setBounds(70,10,600,25);
      
      f1 = new Font("돋움", Font.BOLD, 23);
      f2 = new Font("serif", Font.BOLD, 20);
      
      title.setFont(f1);
      title.setForeground(Color.WHITE);
      
      nnorthp.setBackground(Color.BLACK);
      nnorthp.add(title);    // 컨버전스 레이블 패널에 올리기         
      this.add(nnorthp);     // 패널 프레임에 올리기
   
      name = new JLabel("이 름");
      tname = new JTextField(20);
      age = new JLabel("나 이");
      tage = new JTextField(20);
      
      gender1 = new JLabel("성 별");
      gender = new CheckboxGroup();
      male = new Checkbox("남", gender, true);
      female = new Checkbox("여", gender, false);
               
      addr = new JLabel("주 소");
      taddr = new JTextField(20);
      
      phone = new JLabel("연락처");
      tphone1 = new JTextField();
      JLabel phone1 = new JLabel("   -");
      JLabel phone2 = new JLabel("   -");
      tphone2 = new JTextField();
      tphone3 = new JTextField();
      email = new JLabel("이메일");
      temail1 = new JTextField();
      JLabel email1 = new JLabel("  @");
      temail2 = new JTextField();
      
      emailcb = new JComboBox();
      emailcb.addItem("직접입력");
      emailcb.addItem("naver.com");
      emailcb.addItem("daum.net");
      emailcb.addItem("gmail.com");
      emailcb.addItem("nate.com");
      
      name.setBounds(30,20,35,25);
      tname.setBounds(70,20,180,25);
      age.setBounds(260,20,35,25);
      tage.setBounds(300,20,100,25);
      gender1.setBounds(410,20,30,25);
      male.setBounds(450,20,40,25);
      female.setBounds(500,20,80,25);
      addr.setBounds(30,55,35,25);
      taddr.setBounds(70,55,470,25);
      phone.setBounds(25,90,40,25);
      tphone1.setBounds(70,90,140,25);
      phone1.setBounds(210,90,25,25);
      tphone2.setBounds(235,90,140,25);
      phone2.setBounds(375, 90, 25, 25);
      tphone3.setBounds(400, 90, 140, 25);
      email.setBounds(25,125,40,25);
      temail1.setBounds(70, 125, 140, 25);
      email1.setBounds(210,125,25,25);
      temail2.setBounds(235,125,160, 25);
      emailcb.setBounds(410,125,130,25);
      
      northp.add(name);     // 패널 올리기
      northp.add(tname);
      northp.add(age);
      northp.add(tage);
      northp.add(gender1);
      northp.add(male);
      northp.add(female);
      northp.add(addr);
      northp.add(taddr);
      northp.add(phone);
      northp.add(tphone1);
      northp.add(tphone2);
      northp.add(tphone3);
      northp.add(phone1);
      northp.add(phone2);
      northp.add(email);
      northp.add(email1);
      northp.add(temail1);
      northp.add(temail2);
      northp.add(emailcb);
      
      northp.setLayout(null);
      northp.setBounds(0,30,600,160);  // 두번째 패널(이름, 이메일...)
      this.add(northp);

      orthp = new JPanel();            // 카테고리 패널   
      search = new JComboBox();
      
      search.addItem("검색할 카테고리를 선택하세요");
      search.addItem("name");
      search.addItem("age");
      search.addItem("gender");
      search.addItem("addr");
      search.addItem("phone");
      search.addItem("email");
      
      tsearch = new JTextField();
      bsearch = new JButton("조회");
      tbsearch = new JButton("전체조회");
      
      search.setBounds(40,42,170,25);
      tsearch.setBounds(230, 42, 140, 25);
      bsearch.setBounds(380,42,74,25);
      tbsearch.setBounds(470,42,90,25);
      
      bsearch.setBackground(Color.GREEN);
      bsearch.setForeground(Color.RED);
      tbsearch.setBackground(Color.GREEN);
      tbsearch.setForeground(Color.RED);
      
      orthp.add(search);
      orthp.add(tsearch);
      orthp.add(bsearch);
      orthp.add(tbsearch);
      
      orthp.setBounds(0,160,600,80);
      orthp.setBackground(Color.BLUE);
   
      orthp.setLayout(null);
      this.add(orthp);
      
      String columnNames[] = {"<HTML><HEAD><H2><font color = red>이름</font></H2></HEAD></HTML>"
            ,"<HTML><HEAD><H2><font color = green>나이</font></H2></HEAD></HTML>"
            ,"<HTML><HEAD><H2><font color = blue>성별</font></H2></HEAD></HTML>"
            ,"<HTML><HEAD><H2><font color = pink>주소</font></H2></HEAD></HTML>"
            ,"<HTML><HEAD><H2><font color = orange>연락처</font></H2></HEAD></HTML>"
            ,"<HTML><HEAD><H2><font color = black>이메일</font></H2></HEAD></HTML>"
            ,"<HTML><HEAD><H2><font color = purple>INDEX</font></H2></HEAD></HTML>"
      };
      //String columnNames[] = {"이름","나이","성별","주소","연락처","이메일","INDEX"};
      
      Object[][] rowData ={};
      
      dtm = new DefaultTableModel(rowData,columnNames);
      table = new JTable(dtm);
      scroll = new JScrollPane(table);   //스크롤메인에 테이블 추가(필드명 표시)
   
      scroll.setBounds(0,230,600,250);
      this.add("Center",scroll);
      
       southp = new JPanel();              // 입력, 종료  패널
      
      input = new JButton("입력");
      edit = new JButton("수정");
      delete = new JButton("삭제");
      exit = new JButton("종료");
      
      input.addActionListener(this);
      edit.addActionListener(this);
      delete.addActionListener(this);
      exit.addActionListener(this);
      bsearch.addActionListener(this);  // 조회
      tbsearch.addActionListener(this); // 전체조회 
      search.addActionListener(this);   // 콤보(필드명)
      emailcb.addActionListener(this);  // 콤보(메일)
      table.addMouseListener(this);
      
      input.setBounds(0,70,142,40);
      edit.setBounds(146,70,142,40);
      delete.setBounds(292,70,142,40);
      exit.setBounds(438,70,138,40);
      
      input.setFont(f2);
      input.setBackground(Color.BLUE);
      input.setForeground(Color.YELLOW);
      edit.setFont(f2);
      edit.setBackground(Color.YELLOW);
      edit.setForeground(Color.BLACK);
      delete.setFont(f2);
      delete.setBackground(Color.GREEN);
      delete.setForeground(Color.RED);
      exit.setFont(f2);
      exit.setBackground(Color.MAGENTA);
      exit.setForeground(Color.WHITE);
      
      southp.add(input);
      southp.add(edit);
      southp.add(delete);
      southp.add(exit);
      
      southp.setBounds(5,500,600,200);
      southp.setLayout(null);
      this.add(southp,"SOUTH");
      
      this.setVisible(true);
   }

   @Override
   public void mouseClicked(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      
   }

}