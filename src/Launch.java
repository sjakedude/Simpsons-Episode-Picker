import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import java.awt.Desktop;

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
import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Map;
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
	ArrayList<Episode> favorites = new ArrayList<Episode>();
	
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
		btnNewButton.addActionListener(new ActionListener()  {
			public void actionPerformed(ActionEvent e) {
				Random rand = new Random();
				int a = episodes.size();
				System.out.println(a);
				int i = Math.abs(rand.nextInt(a) + 1);
				System.out.println(i + " : " + episodes.get(i).season + " " + episodes.get(i).episode);
				String s = episodes.get(i).season;
				String ep = episodes.get(i).episode;
				
				File file1 = new File("/Volumes/KESU/.data/Season" + s + "/" + "s" + s + "e" + ep + ".mp4"); 
				File file2 = new File("/Volumes/KESU/.data/Season" + s + "/" + "s" + s + "e" + ep + ".avi"); 
				File file3 = new File("/Volumes/KESU/.data/Season" + s + "/" + "s" + s + "e" + ep + ".mkv"); 

		        // check if the file exists 
		        boolean exists1 = file1.exists(); 
		        boolean exists2 = file2.exists();
		        boolean exists3 = file3.exists();
		        
		        Runtime runTime = Runtime.getRuntime();
		        
		        if(exists1 == true) 
		        {
		            Desktop dt = Desktop.getDesktop();
		            try {
						dt.open(file1);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}            
		        } else if (exists2 == true) {
		            Desktop dt = Desktop.getDesktop();
		            try {
						dt.open(file2);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
		        } else if (exists3 == true) {
		            Desktop dt = Desktop.getDesktop();
		            try {
						dt.open(file3);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        } else {
		        	System.out.println("File Does NOT Exist :(");
		        }
			}
		});
		btnNewButton.setBounds(570, 164, 186, 96);
		panel.add(btnNewButton);
				
		JPanel panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, "name_570087615836805");
		panel_2.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Favorites");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.show(false);
				panel_2.show(true);
				try {
					File file = new File(".favorites.txt");
					Scanner scan = new Scanner(file);
					if (favorites.isEmpty()) {
						while (scan.hasNextLine()) {
							String line = scan.nextLine();
								if (line.contains("2") || line.contains("1")) {
									String[] tokens = line.split("_");
									favorites.add(new Episode(tokens[0], tokens[1], tokens[2], tokens[3]));
								}
						}
					} else {
						favorites.clear();
						while (scan.hasNextLine()) {
							String line = scan.nextLine();
							String[] tokens = line.split("_");
							if (line.contains("1") || line.contains("2")) {
									favorites.add(new Episode(tokens[0], tokens[1], tokens[2], tokens[3]));
							}
						}
				}
					scan.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				populateFavoritesTable();
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
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String season = textField.getText();
				String episode = textField_1.getText();
				
				try {
					playSpecificEpisode(season, episode);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
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
				try {
					playEpisode(table.getSelectedRow());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
			    	if (episode.title.toLowerCase().contains(textField_2.getText().toLowerCase()) || episode.airDate.toLowerCase().contains(textField_2.getText().toLowerCase()) || episode.season.toLowerCase().contains(textField_2.getText().toLowerCase()) || episode.episode.toLowerCase().contains(textField_2.getText().toLowerCase())) {
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
		btnClearSearch.setBounds(210, 243, 133, 29);
		panel_1.add(btnClearSearch);
		
		JButton btnAddToFavorites = new JButton("Add To Favorites");
		btnAddToFavorites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				favorites.add(new Episode(table.getModel().getValueAt(table.getSelectedRow(), 0).toString(), table.getModel().getValueAt(table.getSelectedRow(), 1).toString(), table.getModel().getValueAt(table.getSelectedRow(), 2).toString(), table.getModel().getValueAt(table.getSelectedRow(), 3).toString()));
				PrintWriter writer;
				try {
					writer = new PrintWriter(".favorites.txt", "UTF-8");
					for (Episode episode : favorites) {
						writer.println(episode.season + "_" + episode.episode + "_" + episode.title + "_" + episode.airDate);
					}
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAddToFavorites.setBounds(407, 243, 133, 29);
		panel_1.add(btnAddToFavorites);
		

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
		
		JLabel lblSpecials = new JLabel("Favorites");
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
		
		JButton btnNewButton_4 = new JButton("Remove From Favorites");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table_1.getSelectedRow();
				String title = table_1.getModel().getValueAt(index, 2).toString();
				File file = new File(".favorites.txt");
				int count = favorites.size();
				for (int i = 0; i < count; i++) {
					favorites.remove(0);
				}
				Scanner scan;
				try {
					scan = new Scanner(file);
					while (scan.hasNextLine()) {
						String line = scan.nextLine();
						if (!line.isEmpty()) {
							if (!line.contains(title)) {
								String tokens[] = line.split("_");
								for (String t : tokens) {
									System.out.println("t: " + t);
								}
								System.out.println("------");
								System.out.println(title);
								favorites.add(new Episode(tokens[0], tokens[1], tokens[2], tokens[3]));
							}

						}
					}
				} catch (FileNotFoundException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				file.delete();
				PrintWriter writer;
				try {
					writer = new PrintWriter(".favorites.txt", "UTF-8");
					for (Episode episode : favorites) {
						System.out.println("episode ereasdc, writing new list");
						writer.println(episode.season + "_" + episode.episode + "_" + episode.title + "_" + episode.airDate);
					}
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				populateFavoritesTable();
			}
		});
		btnNewButton_4.setBounds(288, 243, 185, 29);
		panel_2.add(btnNewButton_4);
		
		JButton btnPlaySelectedEpisode_1 = new JButton("Play Selected Episode");
		btnPlaySelectedEpisode_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table_1.getSelectedRow();
				String s = table_1.getModel().getValueAt(index, 0).toString();
				String ep = table_1.getModel().getValueAt(index, 1).toString();
				try {
					playSpecificEpisode(s, ep);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnPlaySelectedEpisode_1.setBounds(562, 243, 185, 29);
		panel_2.add(btnPlaySelectedEpisode_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(31, 34, 716, 197);
		panel_2.add(scrollPane_1);
		
		TableModel tableModel_1 = new DefaultTableModel(getColumnNames(), favorites.size());		
		table_1 = new JTable(tableModel_1);
		scrollPane_1.setViewportView(table_1);
		
		getAllEpisodeData();
	}

	private String[] getColumnNames() {
		String[] columnNames = {"Season",
				"Episode",
                "Title",
                "Air Date"};
		return columnNames;
	}

	private void getAllEpisodeData() {
		
		Populator p = new Populator();
		episodes = p.run();
	    
    	DefaultTableModel model = (DefaultTableModel) table.getModel();
	    int count = model.getRowCount();
	    for (int i = 0; i < count; i++) {
			model.removeRow(0);
	    }
    	
	    for (int i = 0; i < episodes.size(); i++) {	    	
	    	model.addRow(new Object[]{episodes.get(i).season, episodes.get(i).episode, episodes.get(i).title, episodes.get(i).airDate});
	    }			    
	}
	
	private void playEpisode(int index) throws IOException {
		String ep = "s" + table.getModel().getValueAt(index, 0) + "e" + table.getModel().getValueAt(index, 1);
		String s = table.getModel().getValueAt(index, 0).toString();
		System.out.println(ep);

		File file1 = new File("/Volumes/KESU/.data/Season" + s + "/" + ep + ".mp4"); 
		File file2 = new File("/Volumes/KESU/.data/Season" + s + "/" + ep + ".avi"); 
		File file3 = new File("/Volumes/KESU/.data/Season" + s + "/" + ep + ".mkv"); 

        // check if the file exists 
        boolean exists1 = file1.exists(); 
        boolean exists2 = file2.exists();
        boolean exists3 = file3.exists();
        
        Runtime runTime = Runtime.getRuntime();
        
        if(exists1 == true) 
        {
            Desktop dt = Desktop.getDesktop();
            dt.open(file1);            
        } else if (exists2 == true) {
            Desktop dt = Desktop.getDesktop();
            dt.open(file2);	
        } else if (exists3 == true) {
            Desktop dt = Desktop.getDesktop();
            dt.open(file3);
        } else {
        	System.out.println("File Does NOT Exist :(");
        }
	}
	
	private void playSpecificEpisode(String season, String episode) throws IOException {
	
		File file1 = new File("/Volumes/KESU/.data/Season" + season + "/" + "s" + season + "e" + episode + ".mp4"); 
		File file2 = new File("/Volumes/KESU/.data/Season" + season + "/" + "s" + season + "e" + episode + ".avi"); 
		File file3 = new File("/Volumes/KESU/.data/Season" + season + "/" + "s" + season + "e" + episode + ".mkv"); 

		System.out.println(file1);
		System.out.println(file2);
		System.out.println(file3);

		
        // check if the file exists 
        boolean exists1 = file1.exists(); 
        boolean exists2 = file2.exists();
        boolean exists3 = file3.exists();
        
        Runtime runTime = Runtime.getRuntime();
        
        if(exists1 == true) 
        {
            Desktop dt = Desktop.getDesktop();
            dt.open(file1);            
        } else if (exists2 == true) {
            Desktop dt = Desktop.getDesktop();
            dt.open(file2);	
        } else if (exists3 == true) {
            Desktop dt = Desktop.getDesktop();
            dt.open(file3);
        } else {
        	System.out.println("File Does NOT Exist :(");
        }
	}
	
	private void populateFavoritesTable() {
		
		System.out.println("populate favorites");
    	DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
	    int count = model1.getRowCount();
	    for (int i = 0; i < count; i++) {
			model1.removeRow(0);
			System.out.println("remove row");
	    }
	    System.out.println("size of array: " + favorites.size());
	    for (int i = 0; i < favorites.size(); i++) {	    
	    	model1.addRow(new Object[]{favorites.get(i).season, favorites.get(i).episode, favorites.get(i).title, favorites.get(i).airDate});
	    }			    
		
	}
}
