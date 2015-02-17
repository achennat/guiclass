package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Toolbar extends JPanel implements ActionListener {

	private JButton hellobutton;
	private JButton goodbyebutton;
	private StringListener textListener;

	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());

		hellobutton = new JButton("Hello");
		goodbyebutton = new JButton("Goodbye");

		hellobutton.addActionListener(this);
		goodbyebutton.addActionListener(this);

		setLayout(new FlowLayout(FlowLayout.LEFT));

		add(hellobutton);
		add(goodbyebutton);
	}

	public void setStringListener(StringListener listener) {
		this.textListener = listener;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();

		if (clicked == hellobutton) {
			if (textListener != null)
				textListener.textEmitted("Hello\n");
		} else {
			if (textListener != null)
				textListener.textEmitted("Goodbye\n");
		}
	}
}
