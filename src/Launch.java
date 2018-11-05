import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class Launch {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private JTextField textField_2;
	private JTable table_1;
	ArrayList<Episode> episodes = new ArrayList<Episode>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launch window = new Launch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Launch() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 780, 302);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "name_569193322618776");
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Play Random Episode Now");
		btnNewButton.setBounds(570, 164, 186, 96);
		panel.add(btnNewButton);
				
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, "name_570087615836805");
		panel_2.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Specials");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.show(false);
				panel_2.show(true);
			}
		});
		btnNewButton_1.setBounds(570, 52, 186, 96);
		panel.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(41, 198, 40, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblS = new JLabel("S");
		lblS.setBounds(31, 203, 23, 16);
		panel.add(lblS);
		
		JLabel lblE = new JLabel("E");
		lblE.setBounds(114, 203, 23, 16);
		panel.add(lblE);
		
		
		
		textField_1 = new JTextField();
		textField_1.setBounds(124, 198, 40, 26);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Play Episode");
		btnNewButton_2.setBounds(31, 231, 133, 29);
		panel.add(btnNewButton_2);
		
		JLabel lblPlaySpecificEpisode = new JLabel("Play Specific Episode");
		lblPlaySpecificEpisode.setBounds(30, 164, 134, 16);
		panel.add(lblPlaySpecificEpisode);
		
		JLabel lblTheSimpsonsEpisode = new JLabel("The Simpsons Episode Picker");
		lblTheSimpsonsEpisode.setBounds(293, 6, 210, 16);
		panel.add(lblTheSimpsonsEpisode);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, "name_569201548215921");
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 74, 717, 157);
		panel_1.add(scrollPane);
    	
		TableModel tableModel = new DefaultTableModel(getColumnNames(), episodes.size());
    	table = new JTable(tableModel);

		//table = new JTable(getAllEpisodeData(), getColumnNames());
		scrollPane.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel lblYo = new JLabel("All Episodes");
		lblYo.setBounds(347, 8, 128, 16);
		panel_1.add(lblYo);
		
		JButton btnPlaySelectedEpisode = new JButton("Play Selected Episode");
		btnPlaySelectedEpisode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: PLAY SELECTED ROW
				table.getSelectedRow();
			}
		});
		btnPlaySelectedEpisode.setBounds(591, 243, 159, 29);
		panel_1.add(btnPlaySelectedEpisode);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_1.show(false);
				panel.show(true);
			}
		});
		btnBack.setBounds(33, 243, 117, 29);
		panel_1.add(btnBack);
		
		textField_2 = new JTextField();
		textField_2.setBounds(106, 36, 515, 26);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	ArrayList<Episode> search = new ArrayList<Episode>();
			    for (Episode episode : episodes) {
			    	if (episode.title.contains(textField_2.getText()) || episode.airDate.contains(textField_2.getText()) || episode.season.contains(textField_2.getText()) || episode.episode.contains(textField_2.getText())) {
			    		search.add(episode);
			    	}
			    }
			    Object[][] data = new Object[search.size()][4];
			    for (int i = 0; i < search.size(); i++) {
				   	data[i][0] = search.get(i).season;
				   	data[i][1] = search.get(i).episode;
				   	data[i][2] = search.get(i).title;
				   	data[i][3] = search.get(i).airDate;
				}
			    DefaultTableModel model = (DefaultTableModel) table.getModel();
			    				    	
			    int count = model.getRowCount();
			    for (int i = 0; i < count; i++) {
				    model.removeRow(0);
			    }
				for (int i = 0; i < search.size(); i++) {	
				    model.addRow(new Object[]{search.get(i).season, search.get(i).episode, search.get(i).title, search.get(i).airDate});
				}			    
			}
		});
		btnSearch.setBounds(633, 36, 117, 29);
		panel_1.add(btnSearch);
		
		JLabel lblKeyword = new JLabel("Keyword");
		lblKeyword.setBounds(33, 41, 61, 16);
		panel_1.add(lblKeyword);
		
		JButton btnClearSearch = new JButton("Clear Search");
		btnClearSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAllEpisodeData();
			}
		});
		btnClearSearch.setBounds(263, 243, 238, 29);
		panel_1.add(btnClearSearch);
		

		TableColumn column = null;
		for (int i = 0; i < 3; i++) {
		    column = table.getColumnModel().getColumn(i);
		    if (i == 0 || i == 1) {
		        column.setPreferredWidth(1);
		    } else {
		        column.setPreferredWidth(300);
		    }
		}
		
		JButton btnNewButton_3 = new JButton("All Episodes");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.show(false);
				panel_1.show(true);
		    	getAllEpisodeData();
			}
		});
		
		btnNewButton_3.setBounds(24, 52, 163, 96);
		panel.add(btnNewButton_3);
		
		JLabel lblSpecials = new JLabel("Specials");
		lblSpecials.setBounds(373, 6, 72, 16);
		panel_2.add(lblSpecials);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_2.show(false);
				panel.show(true);
			}
		});
		btnBack_1.setBounds(31, 243, 117, 29);
		panel_2.add(btnBack_1);
		
		JButton btnChristmas = new JButton("Christmas");
		btnChristmas.setBounds(31, 34, 117, 29);
		panel_2.add(btnChristmas);
		
		JButton btnTreeHouse = new JButton("Tree House");
		btnTreeHouse.setBounds(146, 34, 117, 29);
		panel_2.add(btnTreeHouse);
		
		JButton btnNewButton_4 = new JButton("Other");
		btnNewButton_4.setBounds(630, 34, 117, 29);
		panel_2.add(btnNewButton_4);
		
		JButton btnPlaySelectedEpisode_1 = new JButton("Play Selected Episode");
		btnPlaySelectedEpisode_1.setBounds(562, 243, 185, 29);
		panel_2.add(btnPlaySelectedEpisode_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(31, 68, 716, 163);
		panel_2.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		

	}

	private String[] getColumnNames() {
		String[] columnNames = {"Season",
				"Episode",
                "Title",
                "Air Date"};
		return columnNames;
	}

	private void getAllEpisodeData() {
		
		episodes.clear();
		episodes.add(new Episode("1", "1", "Simpsons Roasting on an Open Fire", "December 17, 1989"));
		episodes.add(new Episode("1", "2", "Bart the Genius", "January 14, 1990"));
		episodes.add(new Episode("1", "3", "Homer's Odyssey", "November 1, 1990"));
		episodes.add(new Episode("1", "4", "There's No Disgrace Like Home", "January 28, 1990"));
		episodes.add(new Episode("1", "5", "Bart the General", "February 4, 1990"));
		episodes.add(new Episode("1", "6", "Moaning Lisa", "February 11, 1990"));
	    episodes.add(new Episode("1", "7", "The Call of the Simpsons", "February 18, 1990"));
	    episodes.add(new Episode("1", "8", "The Telltale Head", "February 25, 1990"));
	    episodes.add(new Episode("1", "9", "Life on the Fast Lane", "March 18, 1990"));
	    episodes.add(new Episode("1", "10", "Homer's Night Out", "March 25, 1990"));
	    episodes.add(new Episode("1", "11", "The Crepes of Wrath", "April 15, 1990"));
	    episodes.add(new Episode("1", "12", "Krusty Gets Busted", "April 29, 1990"));
	    episodes.add(new Episode("1", "13", 	"Some Enchanted Evening", "May 13, 1990"));
	    
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
	    int count = model.getRowCount();
	    for (int i = 0; i < count; i++) {
			model.removeRow(0);
	    }
    	
	    for (int i = 0; i < episodes.size(); i++) {	    	
	    	model.addRow(new Object[]{episodes.get(i).season, episodes.get(i).episode, episodes.get(i).title, episodes.get(i).airDate});
	    }			    
	}
}
