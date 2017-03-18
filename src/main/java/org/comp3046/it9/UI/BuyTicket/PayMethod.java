package org.comp3046.it9.UI.BuyTicket;

import org.comp3046.it9.Entity.Movie;
import org.comp3046.it9.UI.Menu.MemberMenu;
import org.comp3046.it9.UI.Menu.TopBar;
import org.comp3046.it9.UI.Register.JTextFieldLimit;
import org.comp3046.it9.UI.Search.SearchResult;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class PayMethod {
	// parent
	private final MemberMenu memberMenu;
	// previous
	private final SearchResult searchResult;
    // target
    private final Movie movie;
    private final String[] selectedSeat;

	private JFrame frame;
	private JPanel topbar;
	private JLabel lblLoginer, lblMovieName, lblSelectdSeat, lblpatmentMethod, lblCardNo, lblExpiryDate, lblSecurity,
			lblnew;
	private TopBar tb;
	private JButton btnCancel, btnPrint;
	private JSeparator separator;
	private JRadioButton rdbtnCash;
	private JRadioButton rdbtnCreditCard;
	private JTextField textField_cardNo, textField_YY, textField_MM;
	private JTextField textField_Security;

	public PayMethod(MemberMenu memberMenu, SearchResult searchResult,
                     Movie movie, String[] selectedSeat) {
        this.memberMenu = memberMenu;
        this.searchResult = searchResult;
        this.movie = movie;
        this.selectedSeat = selectedSeat;

		tb = new TopBar();
		frame = new JFrame();
		frame.getContentPane().setLayout(null);

		frame.setBounds(100, 100, 524, 342);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("XXX Cinema - Select Payment Method");

		initialize();
		tb.clock();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		topbar = new JPanel();
		topbar.setLayout(null);
		topbar.setBounds(0, 0, 504, 40);

		lblLoginer = new JLabel("Login as ");
		lblLoginer.setText(lblLoginer.getText() + memberMenu.getCustomer().getName() + "-Member");
		lblLoginer.setBounds(304, 10, 200, 15);

		lblLoginer.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		topbar.add(lblLoginer);

		separator = new JSeparator();
		separator.setBounds(10, 35, 534, 2);
		topbar.add(separator);

		frame.getContentPane().add(tb.topbarLayout(
				topbar, memberMenu.getCustomer().getUid() + "", memberMenu.getCustomer().getName()
		));

		lblMovieName = new JLabel(movie.getName() + "ih");
		lblMovieName.setBounds(122, 50, 280, 40);
		lblMovieName.setHorizontalAlignment(JLabel.CENTER);
		lblMovieName.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 26));
		frame.getContentPane().add(lblMovieName);

		lblSelectdSeat = new JLabel("Selected Seat: ");
		lblSelectdSeat.setBounds(122, 80, 280, 40);
		lblSelectdSeat.setHorizontalAlignment(JLabel.CENTER);
		lblSelectdSeat.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 24));
		frame.getContentPane().add(lblSelectdSeat);

		for (int i = 0; i < selectedSeat.length; i++) {
			lblSelectdSeat.setText(lblSelectdSeat.getText() + selectedSeat[i] + " ");

		}

		separator = new JSeparator();
		separator.setBounds(10, 120, 534, 2);
		frame.getContentPane().add(separator);

		lblpatmentMethod = new JLabel("Payment Method");
		lblpatmentMethod.setBounds(20, 130, 177, 40);

		lblpatmentMethod.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		frame.getContentPane().add(lblpatmentMethod);

		rdbtnCash = new JRadioButton("Cash");
		rdbtnCash.setBounds(205, 130, 107, 40);
		rdbtnCash.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		rdbtnCash.addActionListener(new selectPayMethodAction());
		rdbtnCash.setSelected(true);
		frame.getContentPane().add(rdbtnCash);

		rdbtnCreditCard = new JRadioButton("Credit Card");
		rdbtnCreditCard.setBounds(313, 130, 143, 40);
		rdbtnCreditCard.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		rdbtnCreditCard.addActionListener(new selectPayMethodAction());
		frame.getContentPane().add(rdbtnCreditCard);

		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnCreditCard);
		group.add(rdbtnCash);

		lblCardNo = new JLabel("Card No.");
		lblCardNo.setBounds(20, 160, 177, 40);
		lblCardNo.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		frame.getContentPane().add(lblCardNo);

		textField_cardNo = new JTextField();
		textField_cardNo.setBounds(174, 173, 138, 21);
		frame.getContentPane().add(textField_cardNo);
		textField_cardNo.setDocument(new JTextFieldLimit(16));
		textField_cardNo.setColumns(16);

		lblExpiryDate = new JLabel("Expiry Date");
		lblExpiryDate.setBounds(20, 190, 177, 40);
		lblExpiryDate.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		frame.getContentPane().add(lblExpiryDate);

		textField_YY = new JTextField();
		textField_YY.setBounds(174, 204, 33, 21);
		frame.getContentPane().add(textField_YY);
		textField_YY.setDocument(new JTextFieldLimit(2));
		textField_YY.setColumns(2);

		lblnew = new JLabel("/");
		lblnew.setBounds(208, 204, 10, 21);
		lblnew.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		frame.getContentPane().add(lblnew);

		textField_MM = new JTextField();
		textField_MM.setBounds(220, 204, 33, 21);
		frame.getContentPane().add(textField_MM);
		textField_MM.setDocument(new JTextFieldLimit(2));
		textField_MM.setColumns(2);

		lblSecurity = new JLabel("Security Code");
		lblSecurity.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		lblSecurity.setBounds(20, 220, 177, 40);
		frame.getContentPane().add(lblSecurity);

		textField_Security = new JTextField();
		textField_Security.setColumns(3);
		textField_Security.setDocument(new JTextFieldLimit(3));
		textField_Security.setBounds(174, 233, 138, 21);
		frame.getContentPane().add(textField_Security);

		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(393, 266, 87, 23);
		btnCancel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(btnCancel); //
		btnCancel.addActionListener(new CancelAction());

		btnPrint = new JButton("Process to payment");
		btnPrint.setBounds(208, 266, 143, 23);
		btnPrint.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		frame.getContentPane().add(btnPrint); //
		btnPrint.addActionListener(new PrintAction());
	}

	public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    private PayMethod getSelf() {
        return this;
    }

    public SearchResult getSearchResult() {
        return searchResult;
    }

    public void dispose() {
        frame.dispose();
    }

	private class PrintAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			new BuyTicket(memberMenu, getSelf(), movie, selectedSeat);
			frame.setVisible(false);
		}
	}

	private class selectPayMethodAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			JRadioButton btn = (JRadioButton) event.getSource();
			if (btn.getText().equals("Cash")) {

				textField_cardNo.setEditable(false);
				textField_YY.setEditable(false);
				textField_MM.setEditable(false);
				textField_Security.setEditable(false);
				lblCardNo.setEnabled(false);
				lblExpiryDate.setEnabled(false);
				lblSecurity.setEnabled(false);

			} else {
				textField_cardNo.setEditable(true);
				textField_YY.setEditable(true);
				textField_MM.setEditable(true);
				textField_Security.setEditable(true);
				lblCardNo.setEnabled(true);
				lblExpiryDate.setEnabled(true);
				lblSecurity.setEnabled(true);

			}

		}
	}

	private class CancelAction implements ActionListener {
		public void actionPerformed(ActionEvent event) {
            getSearchResult().getSearchMovie().dispose();
            getSearchResult().dispose();
            memberMenu.setVisible(true);
			frame.dispose();
		}
	}

    // http://stackoverflow.com/q/8383975/2388501
    private class CreditCardTextFieldContextAction implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            check();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            check();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            check();
        }

        private void check() {
            String cardNo = textField_cardNo.getText();
            if (cardNo.length() == 0) {
                lblCardNo.setText("Card No. ");
                return;
            }
            String msg = "Card No. " + getCreditCardType(cardNo);
            lblCardNo.setText(msg);
        }

        // http://stackoverflow.com/q/72768/2388501
        public String getCreditCardType(String CreditCardNumber) {
            final Pattern regVisa = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
            final Pattern regMaster = Pattern.compile("^5[1-5][0-9]{14}$");
            final Pattern regExpress = Pattern.compile("^3[47][0-9]{13}$");
            final Pattern regDiners = Pattern.compile("^3(?:0[0-5]|[68][0-9])[0-9]{11}$");
            final Pattern regDiscover = Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{12}$");
            final Pattern regJCB = Pattern.compile("^(?:2131|1800|35\\d{3})\\d{11}$");


            if (regVisa.matcher(CreditCardNumber).matches())
                return "Visa";
            else if (regMaster.matcher(CreditCardNumber).matches())
                return "MasterCard";
            else if (regExpress.matcher(CreditCardNumber).matches())
                return "American Express";
            else if (regDiners.matcher(CreditCardNumber).matches())
                return "Diners";
            else if (regDiscover.matcher(CreditCardNumber).matches())
                return "Discovers";
            else if (regJCB.matcher(CreditCardNumber).matches())
                return "JCB";
            else
                return "";
        }
    }

}
