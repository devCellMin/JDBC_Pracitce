package worldcup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class K_League_GUI extends JFrame {
    JPanel nnorthp, northp;
    JLabel title;
    JButton input, edit, delete, search, exit;
    Font f1, f2;

    DBConnection dbconn;
    K_League_Insert insertFrame;
    K_League_Edit editFrame;
    K_League_Search searchFrame;
    K_League_Delete deleteFrame;
    MyactionListener actionListener;

    // Constructor
    public K_League_GUI() {
        setLayout(null);

        nnorthp = new JPanel();
        nnorthp.setLayout(null);
        nnorthp.setBounds(0, 0, 600, 50);

        title = new JLabel("K_League_Player", JLabel.CENTER);
        title.setBounds(0, 20, 600, 20);

        f1 = new Font("돋움", Font.BOLD, 23);
        f2 = new Font("serif", Font.BOLD, 20);

        title.setFont(f1);
        title.setForeground(Color.WHITE);
        nnorthp.setBackground(Color.BLACK);
        nnorthp.add(title);
        this.add(nnorthp);

        northp = new JPanel();
        northp.setLayout(null);
        northp.setBounds(30, 60, 530, 480);

        northp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(" 메뉴선택 "),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));


        input = new JButton("선수 등록");
        edit = new JButton("선수 수정");
        search = new JButton("선수 검색");
        delete = new JButton("선수 삭제");
        exit = new JButton("종료");

        input.setBounds(130, 50, 142, 40);
        edit.setBounds(130, 130, 142, 40);
        search.setBounds(130, 210, 142, 40);
        delete.setBounds(130, 290, 142, 40);
        exit.setBounds(130, 370, 142, 40);

        input.setFont(f2);
        input.setBackground(Color.BLUE);
        input.setForeground(Color.YELLOW);
        edit.setFont(f2);
        edit.setBackground(Color.YELLOW);
        edit.setForeground(Color.BLACK);
        search.setFont(f2);
        search.setBackground(Color.GREEN);
        search.setForeground(Color.RED);
        delete.setFont(f2);
        delete.setBackground(Color.CYAN);
        delete.setForeground(Color.RED);
        exit.setFont(f2);
        exit.setBackground(Color.MAGENTA);
        exit.setForeground(Color.WHITE);

        northp.add(input);
        northp.add(edit);
        northp.add(search);
        northp.add(delete);
        northp.add(exit);
        this.add(northp);

        // Add Listener
        actionListener = new MyactionListener(this);
        input.addActionListener(actionListener);
        edit.addActionListener(actionListener);
        search.addActionListener(actionListener);
        delete.addActionListener(actionListener);
        exit.addActionListener(actionListener);

        setTitle("K_League_GUI");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private class MyactionListener implements ActionListener {
        K_League_GUI gui;
        DBConnection dbconn;
        public MyactionListener(K_League_GUI gui) {
            this.gui = gui;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Object ae = e.getSource();

            if(ae == input) {
                gui.setVisible(false);
                insertFrame = new K_League_Insert(gui);
            }else if(ae == edit) {
                gui.setVisible(false);
                editFrame = new K_League_Edit(gui);
            }else if(ae == search) {
                gui.setVisible(false);
                searchFrame = new K_League_Search(gui);
            }else if(ae == delete){
                gui.setVisible(false);
                deleteFrame = new K_League_Delete(gui);
            }else if(ae == exit) {
                System.exit(0);
            }
        }
    }
}
