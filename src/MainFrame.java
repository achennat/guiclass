import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;

	public MainFrame() {
		super("Hello World");

		setLayout(new BorderLayout());

		textPanel = new TextPanel();
		toolbar = new Toolbar();
		formPanel = new FormPanel();
		fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new PersonFileFilter());

		setJMenuBar(createMenuBar());

		toolbar.setStringListener(new StringListener() {

			@Override
			public void textEmitted(String text) {
				textPanel.append(text);

			}

		});

		formPanel.setFormListener(new FormListener() {
			public void formEventOccured(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCat = e.getAgeCategory();
				String empCat = e.getEmploymentCategory();
				String taxId = e.getTaxId();
				boolean usCitizen = e.getUsCitizen();
				String gender = e.getGender();

				textPanel.append(name + ": " + occupation + ": " + ageCat
						+ ", " + empCat + ", " + taxId + ", " + usCitizen
						+ ", " + gender + "\n");
			}
		});
		add(formPanel, BorderLayout.WEST);
		add(textPanel, BorderLayout.CENTER);
		add(toolbar, BorderLayout.NORTH);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 500));
		setSize(600, 500);
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");

		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);

		JMenu windowMenu = new JMenu("Window");
		JMenu showMenu = new JMenu("Show");

		final JMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		showFormItem.setSelected(true);

		showMenu.add(showFormItem);
		windowMenu.add(showMenu);

		menuBar.add(fileMenu);
		menuBar.add(windowMenu);

		showFormItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();

				formPanel.setVisible(showFormItem.isSelected());
			}

		});

		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);

		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				ActionEvent.CTRL_MASK));

		importDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
					System.out.println(fileChooser.getSelectedFile());
			}
		});

		exportDataItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION)
					System.out.println(fileChooser.getSelectedFile());
			}
		});

		exitItem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				int action = JOptionPane.showConfirmDialog(MainFrame.this,
						"Exit?", "Confirm Exit", JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.OK_OPTION)
					System.exit(0);
			}

		});
		return menuBar;
	}
}
