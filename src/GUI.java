import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollBar;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField keyField;

	int layer = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public GUI() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		setTitle("Scrambler");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		UIManager.setLookAndFeel(
	    UIManager.getSystemLookAndFeelClassName());

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		final JTextArea editorPane = new JTextArea();
		editorPane.setFont(new Font("Cambria", Font.PLAIN, 14));
		editorPane.setWrapStyleWord(true);
		editorPane.setLineWrap(true);
		contentPane.add(editorPane, BorderLayout.CENTER);

		final JLabel lblStatus = new JLabel("Status:");
		contentPane.add(lblStatus, BorderLayout.SOUTH);
		
		JScrollBar scrollBar = new JScrollBar();
		contentPane.add(scrollBar, BorderLayout.EAST);

		JMenuItem mntmNew = new JMenuItem("");
		mntmNew.setHorizontalAlignment(SwingConstants.LEFT);
		mntmNew.setIcon(new ImageIcon(GUI.class
				.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		menuBar.add(mntmNew);

		JMenuItem mntmSave = new JMenuItem("");
		mntmSave.setHorizontalAlignment(SwingConstants.LEFT);
		mntmSave.setIcon(new ImageIcon(GUI.class
				.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		menuBar.add(mntmSave);

		JMenuItem mntmLoad = new JMenuItem("");
		mntmLoad.setHorizontalAlignment(SwingConstants.LEFT);
		mntmLoad.setIcon(new ImageIcon(
				GUI.class
						.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
		menuBar.add(mntmLoad);

		JMenuItem mntmEncryptcustom = new JMenuItem("");
		mntmEncryptcustom.setHorizontalAlignment(SwingConstants.LEFT);
		mntmEncryptcustom
				.setIcon(new ImageIcon(
						GUI.class
								.getResource("/javax/swing/plaf/metal/icons/ocean/maximize-pressed.gif")));
		mntmEncryptcustom.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String input = editorPane.getText();
				if (input == null)
					JOptionPane
							.showMessageDialog(
									contentPane,
									"Please enter an encryption key to use (numerical)",
									"No encryption key!",
									JOptionPane.PLAIN_MESSAGE);
				String key = keyField.getText();
				String out = Scramble.scramble(input, key);
				editorPane.setText(out);
				layer++;
				lblStatus.setText("Encrypted. Layer " + layer);
			}
		});
		menuBar.add(mntmEncryptcustom);

		JMenuItem mntmAbout = new JMenuItem("");
		mntmAbout.setHorizontalAlignment(SwingConstants.LEFT);
		mntmAbout.setIcon(new ImageIcon(GUI.class
				.getResource("/javax/swing/plaf/metal/icons/ocean/info.png")));
		mntmAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(contentPane,
						"Scrambler. \nJamie Henson (c) 2011.",
						"About Scrambler", JOptionPane.PLAIN_MESSAGE);
			}
		});

		JMenuItem menuItem = new JMenuItem("");
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String input = editorPane.getText();
				if (input == null)
					JOptionPane
							.showMessageDialog(
									contentPane,
									"Please enter an encryption key to use (numerical)",
									"No encryption key!",
									JOptionPane.PLAIN_MESSAGE);
				String key = keyField.getText();
				String out = Scramble.descramble(input, key);
				editorPane.setText(out);
				layer--;
				lblStatus.setText("Decrypted. Layer " + layer);
			}
		});
		menuItem.setIcon(new ImageIcon(
				GUI.class
						.getResource("/javax/swing/plaf/metal/icons/ocean/iconify-pressed.gif")));
		menuItem.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(menuItem);
		menuBar.add(mntmAbout);

		keyField = new JTextField();
		keyField.setFont(new Font("Tahoma", Font.BOLD, 18));
		menuBar.add(keyField);
		keyField.setColumns(10);
	}
}
