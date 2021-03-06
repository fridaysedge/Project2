import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class NotesSearchWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1242405414797815636L;
	private JPanel jPanel1;
	private JButton jButton1;
	private JTextField jTextField1;
	private JTable jTable1;
	private JLabel jLabel1;
	private JScrollPane jScrollPane1;
	private DefaultTableModel jTable1Model ;

	public NotesSearchWindow ()
	{
		initGUI();
		pack();
		this.setLocationRelativeTo(null);
        setSize(800, 504);
		this.setVisible(true);
	}
	private void initGUI() {
		try {
			{
				jPanel1 = new JPanel();
				FlowLayout jPanel1Layout = new FlowLayout();
				jPanel1Layout.setAlignment(FlowLayout.LEFT);
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.NORTH);
				{
					jLabel1 = new JLabel();
					jPanel1.add(jLabel1);
					jLabel1.setText("Enter keywords:");
				}
				{
					jTextField1 = new JTextField();
					jPanel1.add(jTextField1);
					jTextField1.setPreferredSize(new java.awt.Dimension(192, 23));
				}
				{
					jButton1 = new JButton();
					jPanel1.add(jButton1);
					jButton1.setText("Search");
					jButton1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent evt) {
							jButton1ActionPerformed(evt);
						}
					});
				}
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1, BorderLayout.CENTER);
				{
					jTable1Model = 
						new DefaultTableModel(
								new String[] { "Rank","Flight #", "From", "To", "Departs", "Arrives","Seat","Meal","Price","Notes" },0);
					jTable1 = new JTable();
					jScrollPane1.setViewportView(jTable1);
					jTable1.setModel(jTable1Model);
					jTable1.setPreferredSize(new Dimension(800,100));
				}
			}
			{
				this.setSize(800, 250);
				this.setTitle("Search Notes");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void jButton1ActionPerformed(ActionEvent evt) {
		this.jTable1Model.setRowCount(0);
	
		DateFormat df = DateFormat.getDateTimeInstance();
		
		Reservation [] reservations = DatabaseAccess.SearchReservationNotes(jTextField1.getText());
		if (reservations != null)
		{
			for (int i=0;i<reservations.length;i++)
			{
				Reservation r = reservations[i];
				jTable1Model.addRow(
							new Object[] { 
								r.Relavance,
								r.Flight.FlightNumber,
								r.Flight.DepartureAirport.AirportName,
								r.Flight.ArrivalAirport.AirportName,
								df.format(r.Flight.DepartureTime),
								df.format(r.Flight.ArrivalTime),
								r.Seat,
								r.MealOptions,
								Double .toString(r.PricePaid),
								r.NotesAboutReservation
							}
						);
			}
		}
	}

}
