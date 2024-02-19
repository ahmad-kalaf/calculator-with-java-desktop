import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener
{
	private JFrame _frame;
	private JTextField _textfield;
	private JButton[] _numButons;
	private JButton[] _funcButtons;
	private JButton _addButton, _subButton, _mulButton, _divButton;
	private JButton _decButton, _equButton, _delButton, _clrButton, _negButton;
	private JPanel _panel;
	private Font _font;
	private double _num1, _num2, _result;
	private char _operator;
	ImageIcon _icon;
	
	public Calculator()
	{
		_frame = new JFrame("Calculator");
		_textfield = new JTextField();
		_numButons = new JButton[10];
		_funcButtons = new JButton[9];
		_addButton = new JButton("+");
		_subButton = new JButton("-");
		_mulButton = new JButton("x");
		_divButton = new JButton("\u00F7");
		_decButton = new JButton(".");
		_delButton = new JButton("DEL");
		_equButton = new JButton("=");
		_clrButton = new JButton("CLR");
		_negButton = new JButton("(-)");
		_panel = new JPanel();
		_font = new Font("Agency FB", Font.PLAIN, 30);
		_num1 = 0;
		_num2 = 0;
		_result = 0;
		_icon = new ImageIcon("src/myIcon.png");
		
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.setSize(420, 550);
		_frame.setLayout(null);
		_frame.setResizable(false);

		_textfield.setBounds(50, 25, 300, 50);
		_textfield.setFont(_font);
		_textfield.setEditable(false);

		_funcButtons[0] = _addButton;
		_funcButtons[1] = _subButton;
		_funcButtons[2] = _mulButton;
		_funcButtons[3] = _divButton;
		_funcButtons[4] = _decButton;
		_funcButtons[5] = _equButton;
		_funcButtons[6] = _delButton;
		_funcButtons[7] = _clrButton;
		_funcButtons[8] = _negButton;
		
		for(int i = 0; i < 9; i++)
		{
			_funcButtons[i].addActionListener(this);
			_funcButtons[i].setFont(_font);
			_funcButtons[i].setFocusable(false);
		}	
		for(int i = 0; i < 10; i++)
		{
			_numButons[i] = new JButton(String.valueOf(i));
			_numButons[i].addActionListener(this);
			_numButons[i].setFont(_font);
			_numButons[i].setFocusable(false);
		}
		
		_panel.setBounds(50, 100, 300, 300);
		_panel.setLayout(new GridLayout(4,4,10,10));
		// 1st row
		_panel.add(_numButons[1]);
		_panel.add(_numButons[2]);
		_panel.add(_numButons[3]);
		_panel.add(_addButton);
		// 2nd row
		_panel.add(_numButons[4]);
		_panel.add(_numButons[5]);
		_panel.add(_numButons[6]);
		_panel.add(_subButton);
		// 3rd row
		_panel.add(_numButons[7]);
		_panel.add(_numButons[8]);
		_panel.add(_numButons[9]);
		_panel.add(_mulButton);
		// 4th row
		_panel.add(_decButton);
		_panel.add(_numButons[0]);
		_panel.add(_equButton);
		_panel.add(_divButton);
		
		_negButton.setBounds(50, 430, 100, 50);
		_delButton.setBounds(150, 430, 100, 50);
		_clrButton.setBounds(250, 430, 100, 50);
		
		_frame.add(_panel);
		_frame.add(_delButton);
		_frame.add(_clrButton);
		_frame.add(_negButton);
		_frame.add(_textfield);
		_frame.setIconImage(_icon.getImage());
		_frame.setVisible(true);		
	}
	
	public static void main(String[] args)
	{
		Calculator calc = new Calculator();
	}
	
	/**
	 * check if _textfield is empty
	 * @return true if _textfield is empty
	 * 		   false else
	 */
	private boolean textfieldIsEmpty()
	{
		return _textfield.getText().isBlank();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(int i = 0; i < 10; i++)
		{
			if(e.getSource() == _numButons[i])
			{
				_textfield.setText(_textfield.getText().concat(String.valueOf(i)));
			}
		}
//		JButton b = (JButton)e.getSource();
//		_textfield.setText(_textfield.getText().concat(b.getText()));
		if(e.getSource() == _decButton)
		{
			// if the text field is empty, set it to 0. ...
			if(_textfield.getText().length() == 0)
			{
				_textfield.setText("0.");
			}
			// if the textfield already contains a dec, do nothing
			if(!_textfield.getText().contains("."))
			{
				_textfield.setText(_textfield.getText().concat("."));
			}
		}
		if(!textfieldIsEmpty())
		{
			if(e.getSource() == _addButton)
			{
				_num1 = Double.parseDouble(_textfield.getText());
				_operator = '+';
				_textfield.setText("");
			}
			if(e.getSource() == _subButton)
			{
				_num1 = Double.parseDouble(_textfield.getText());
				_operator = '-';
				_textfield.setText("");
			}
			if(e.getSource() == _mulButton)
			{
				_num1 = Double.parseDouble(_textfield.getText());
				_operator = '*';
				_textfield.setText("");
			}
			if(e.getSource() == _divButton)
			{
				_num1 = Double.parseDouble(_textfield.getText());
				_operator = '/';
				_textfield.setText("");
			}
		}
		
		if(e.getSource() == _equButton)
		{
			_num2 = Double.parseDouble(_textfield.getText());
			switch (_operator) 
			{
			case'+':
				_result = _num1 + _num2;
				break;
			case'-':
				_result = _num1 - _num2;
				break;
			case'*':
				_result = _num1 * _num2;
				break;
			case'/':
				_result = _num1 / _num2;
				break;
			}
			_textfield.setText(String.valueOf(_result));
			_num1 = _result;
		}
		
		if(e.getSource() == _negButton)
		{
			if(textfieldIsEmpty())
			{
				_textfield.setText("-1");
			}
			else
			{
				double d = Double.parseDouble(_textfield.getText());
				d *= -1;
				_textfield.setText(String.valueOf(d));
			}
		}
		
		if(e.getSource() == _delButton && !textfieldIsEmpty())
		{
			if(_textfield.getText().length() <= 1)
			{
				_textfield.setText("");
			}
			else
			{
				_textfield.setText(_textfield.getText().substring(0, _textfield.getText().length()-1));
			}
		}
		
		if(e.getSource() == _clrButton)
		{
			_textfield.setText("");
		}
		
	}

}
//