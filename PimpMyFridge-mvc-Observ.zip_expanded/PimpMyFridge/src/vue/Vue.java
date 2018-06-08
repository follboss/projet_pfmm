package vue;

import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import controlleur.Controlleur;
import observer.Observateur;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vue implements Observateur {
	
	//initailisation du controller,
	private Controlleur controlle;
	//
	private JFrame frame;
	//initialisation des series de données
	static XYSeries series = new XYSeries("Evolution de la température interieur");
	static XYSeries seriesRosee = new XYSeries("Point de rosée");
	static XYSeries seriesConsigne = new XYSeries("Consigne");
	static XYSeries seriesHumidite = new XYSeries("Evolution de l humidite");
	static XYSeriesCollection dataset = new XYSeriesCollection(series);
	static XYSeriesCollection dataset2 = new XYSeriesCollection(seriesHumidite);
	private JTable table;
	private int x = 0;
	//initialisation du bouton on/off
	private JToggleButton tglbtnNewToggleButton;
	private JButton btnRose;
	private JButton btnConsigne;

	// constructeur de la vue
	public Vue(Controlleur control) {
		controlle = control;
		initialize();
	}

	// fonction d'initialisation de la vue
	private void initialize() {

		dataset.addSeries(seriesRosee);
		dataset.addSeries(seriesConsigne);

		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 1123, 675);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		getFrame().setContentPane(panel);

		JLabel lblNewLabel = new JLabel("PimpMyFridge");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		// affichage
		JPanel panel_1 = new JPanel();

		JPanel panel_2 = new JPanel();

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 898, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE))
				.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addGap(5)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE).addGap(35)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(81, Short.MAX_VALUE)));

		table = new JTable();
		// classe anonyme ( initalisation de la table des valeurs)
		table.setModel(
				new DefaultTableModel(
						new Object[][] { { "Température interieur", 0 }, { "Consigne", 0 },
								{ "Température extérieur", 0 }, { "Point de rosée", 0 }, { "Humidité", 0 }, },
						new String[] { "", "Valeur" }) {
					
					//// pour le model de la jtable
					private static final long serialVersionUID = 1L;
					Class[] columnTypes = new Class[] { Object.class, Integer.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});

		scrollPane.setViewportView(table);
		// actionner listener en liason  avec l'action cummutation de l'etat du frigo
		tglbtnNewToggleButton = new JToggleButton("On");
		tglbtnNewToggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlle.commuter();
			}
		});
		tglbtnNewToggleButton.setBackground(new Color(0, 255, 0));
		
		// slider de changement d'état de la consigne
		JSlider slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				controlle.setConsigne(slider.getValue());
			}
		});
		slider.setValue(7);
		slider.setMaximum(10);
		slider.setToolTipText("TÂ° Consigne");

		JLabel labelCsgMin = new JLabel(slider.getMinimum() + "");
		labelCsgMin.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel labelCsgMax = new JLabel(slider.getMaximum() + "");
		labelCsgMax.setHorizontalAlignment(SwingConstants.CENTER);

		btnConsigne = new JButton("Consigne");

		btnRose = new JButton("Rosée");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addGap(1)
				.addComponent(tglbtnNewToggleButton, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(labelCsgMin, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(slider, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addComponent(labelCsgMax, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE).addGap(57)
				.addComponent(btnConsigne, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE).addGap(161)
				.addComponent(btnRose, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE).addGap(162)));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addComponent(labelCsgMin, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
										.addComponent(slider, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
										.addComponent(tglbtnNewToggleButton, Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
										.addGroup(Alignment.LEADING,
												gl_panel_1.createParallelGroup(Alignment.BASELINE)
														.addComponent(labelCsgMax, GroupLayout.DEFAULT_SIZE, 41,
																Short.MAX_VALUE)
														.addComponent(btnConsigne, GroupLayout.DEFAULT_SIZE, 41,
																Short.MAX_VALUE)))
								.addComponent(btnRose, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);

		// iniatlisation des 2 graphe a l'ihm
		JFreeChart chart = ChartFactory.createXYLineChart("Température interieur", "Time (seconds)", "Température",
				Vue.dataset);
		// (seconds)", "Humidité %", View.dataset3);
		JFreeChart chart2 = ChartFactory.createXYLineChart("Humidité", "Time (seconds)", "Humidité %",
				Vue.dataset2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));

		panel_2.add(new ChartPanel(chart));
		panel_2.add(new ChartPanel(chart2));
	}

	@Override
	// override de la methode update de l'osvervateur
	//methode permettant la MAJ de l'ihm en fonction des changements de données
	public void update() {
		// on recupere le point de rosée, les temps, consigne
		float interieur = controlle.getInterieur();
		float exterieur = controlle.getExterieur();
		float humidite = controlle.getHumidite();
		float rosee = controlle.getPointRosee();
		int consigne = controlle.getConsigne();

		x++;
		// ajout de valeurs aux graphes
		series.add(x, interieur);
		seriesRosee.add(x, rosee);
		seriesConsigne.add(x, consigne);
		seriesHumidite.add(x, humidite);
		
		//changement d'état en fonction des de l'évolution de la data
		if (controlle.getEtat()) {
			tglbtnNewToggleButton.setText("ON");
			tglbtnNewToggleButton.setBackground(new Color(0, 255, 0));
		} else {
			tglbtnNewToggleButton.setText("OFF");
			tglbtnNewToggleButton.setBackground(new Color(255, 0, 0));
		}

		if (controlle.isConsigne()) {
			btnConsigne.setBackground(new Color(0, 255, 0));
		} else {
			btnConsigne.setBackground(new Color(255, 0, 0));
		}

		if (controlle.isRosee()) {
			btnRose.setBackground(new Color(0, 255, 0));
		} else {
			btnRose.setBackground(new Color(255, 0, 0));
		}
			//mise a jour en temps réel de la jtable avec recuperation de données
		table.setModel(new DefaultTableModel(new Object[][] { { "Température interieur", interieur },
				{ "Consigne", consigne }, { "Température extérieur", exterieur }, { "Point de rosée", rosee },
				{ "Humidité", humidite }, }, new String[] { "", "Valeur" }) {
			/**
					 * 
					 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { Object.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
