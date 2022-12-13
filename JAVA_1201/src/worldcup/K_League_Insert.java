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
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class K_League_Insert extends JFrame implements ActionListener, KeyListener {
	K_League_GUI gui;
	JPanel nnorthp, northp, southp;
	JLabel title, name, age, weight, position, backNumber, hometown1;
	JTextField tname, tage, tweight, tposition, tbackNumber, thometown1;
	JButton menu, insert;
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
	int idx;

	//K_League_Insert(K_League_GUI gui) {
	public K_League_Insert(K_League_GUI gui) {
		// DB 연결
		this.dbconn = DBConnection.getInstance();
		this.gui = gui;

		this.setTitle("World Cup Player");
		this.setLayout(null);
		this.setSize(650, 800);
		this.setLocationRelativeTo(null);

		nnorthp = new JPanel();
		nnorthp.setLayout(null);
		nnorthp.setBounds(0, 0, 650, 50);

		title = new JLabel("K_League_Player 선수 추가", JLabel.CENTER);
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

		//=======================================================================================
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
      	scroll.setBounds(5, 400, 620, 200);
      	this.add(scroll);

      	table.setBackground(new Color(255, 255, 204));
      	table.setRowHeight(15); // 테이블 행 높이 조절
		
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int ch = e.getKeyCode();

		if(ch == KeyEvent.VK_UP) {
			if(tname.isFocusOwner()) {
				thometown1.requestFocus();
			}else if(tage.isFocusOwner()) {
				tname.requestFocus();
			}else if(tweight.isFocusOwner()) {
				tage.requestFocus();
			}else if(tposition.isFocusOwner()) {
				tweight.requestFocus();
			}else if(tbackNumber.isFocusOwner()) {
				tposition.requestFocus();
			}else if(thometown1.isFocusOwner()) {
				tbackNumber.requestFocus();
			}
		} else if(ch == KeyEvent.VK_DOWN) {
			if(tname.isFocusOwner()) {
				tage.requestFocus();
			}else if(tage.isFocusOwner()) {
				tweight.requestFocus();
			}else if(tweight.isFocusOwner()) {
				tposition.requestFocus();
			}else if(tposition.isFocusOwner()) {
				tbackNumber.requestFocus();
			}else if(tbackNumber.isFocusOwner()) {
				thometown1.requestFocus();
			}else if(thometown1.isFocusOwner()) {
				tname.requestFocus();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object ae = e.getSource();
		if(ae == insert) {
			if(tname.getText().length() == 0) {
				JOptionPane.showMessageDialog(this, "선수 이름은 필수 입력사항 입니다.");
				tclear();
			} else if(DBConnectTF) {
				if(JOptionPane.showConfirmDialog(this,"선수를 추가 하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION)
						== JOptionPane.YES_OPTION) {
					int result = 0;

					// 선수 이름 중복체크
					String query = "Select * From player Where name = ? and age = ?";
					try {
						pstmt = dbconn.conn.prepareStatement(query);
						pstmt.setString(1, tname.getText());
						pstmt.setString(2, tage.getText());
						rs = pstmt.executeQuery();

						while (rs.next()) {
							JOptionPane.showMessageDialog(this, "입력하신 선수와 동일한 사람이 존재합니다.");
							return;
						}

						query = "Insert into player (idx, name, age, weight, position, backNumber, hometown)" +
								"values(?, ?, ?, ?, ?, ?, ?)";
						pstmt = dbconn.conn.prepareStatement(query);
						pstmt.setInt(1, 0); // 0은 정수, 문자로 null
						pstmt.setString(2, tname.getText());
						pstmt.setString(3, tage.getText());
						pstmt.setString(4, tweight.getText());
						pstmt.setString(5, tposition.getText());
						pstmt.setString(6, tbackNumber.getText());
						pstmt.setString(7, thometown1.getText());
						result = pstmt.executeUpdate();
						JOptionPane.showMessageDialog(this, "선수등록이 완료되었습니다.");
						player_Display();
						tclear();

					} catch (SQLException se) {
						JOptionPane.showMessageDialog(this, se.getMessage());
					}
				}
			}
		}
		if(ae == menu) {
			this.dispose();
			gui.setVisible(true);
		}
	}

	public void tclear() {
		tname.setText("");
		tage.setText("");
		tweight.setText("");
		tposition.setText("");
		tbackNumber.setText("");
		thometown1.setText("");
	}
}