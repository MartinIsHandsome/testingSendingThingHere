package pakcageForEverything;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class instantiationOfToDo {
	private JFrame frame;
	private JTextField missionInput;
	private JPanel missionPanel;
	private ArrayList<classForMissions> missions;

	public instantiationOfToDo() {
		frame = new JFrame("-[Daily Missions 0.01]-");
		frame.setSize(600, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		missions = new ArrayList<>();
		setupFrame();
	}

	protected void setupFrame() {
		frame.setLayout(null);

		missionInput = new JTextField();
		missionInput.setFont(new Font("Arial", Font.PLAIN, 20));
		missionInput.setBounds(100, 100, 400, 50);
		frame.add(missionInput);

		JButton addButton = new JButton("Add Mission");
		addButton.setFont(new Font("Arial", Font.PLAIN, 20));
		addButton.setBounds(120, 180, 350, 50);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMission();
			}
		});
		frame.add(addButton);

		missionPanel = new JPanel();
		missionPanel.setLayout(new BoxLayout(missionPanel, BoxLayout.Y_AXIS));
		missionPanel.setBounds(100, 250, 400, 500);
		JScrollPane scrollPane = new JScrollPane(missionPanel);
		scrollPane.setBounds(100, 250, 400, 500);
		frame.add(scrollPane);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void addMission() {
		String missionName = missionInput.getText();
		if (!missionName.trim().isEmpty()) {
			classForMissions newMission = new classForMissions(missionName);
			missions.add(newMission);
			displayMission(newMission);
			missionInput.setText(""); // Clear input after adding
		} else {
			JOptionPane.showMessageDialog(frame, "Please enter a mission name.");
		}
	}

	private void displayMission(classForMissions mission) {
		JPanel singleMissionPanel = new JPanel();
		singleMissionPanel.setLayout(new BoxLayout(singleMissionPanel, BoxLayout.X_AXIS));

		JLabel textInfo = new JLabel(mission.getNameOfMission() + " \n");
		textInfo.setFont(new Font("Arial", Font.PLAIN, 20));
		
		JButton missionButton = new JButton("X");
		missionButton.setFont(new Font("Arial", Font.PLAIN, 8));

		missionButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Action for each mission button
				removeMission(mission, singleMissionPanel);
			}
		});

		// Add label and button to the horizontal panel for this mission
		singleMissionPanel.add(textInfo);
		singleMissionPanel.add(missionButton);

		missionPanel.add(singleMissionPanel);

		missionPanel.revalidate(); // Refresh panel to show new button
	}

	private void removeMission(classForMissions mission, JPanel singleMissionPanel) {
		missions.remove(mission);
		missions.remove(mission); // Remove mission from list
		missionPanel.remove(singleMissionPanel); // Remove button from panel
		missionPanel.revalidate();
		missionPanel.repaint(); // Refresh panel to reflect removal
	}
}
