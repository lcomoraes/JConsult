package br.com.jconsult.widgets;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Classe responsável por criar a máscara do campo EditText Número Ùnico 
 * @author luan
 */
public class NumeroUnicoEditText extends EditText {
	
	private boolean isUpdating;
	 
	/*
	* Maps the cursor position from phone number to masked number... 1234567890
	* => (12) 3456-7890
	*/
	
	/*
	 * Número único -  
	 * 1234567-12.1234.8.10.1234
	 * 20 caracteres
	 */
	//private int positioning[] = { 1, 2, 3, 6, 7, 8, 9, 11, 12, 13, 14, 15 };
	private int positioning[] = { 1, 2, 3, 6, 7, 8, 9, 11, 12, 13, 14, 15,16,17,18,19,20,21,22,23,24,25,26};
	
	public NumeroUnicoEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	 
	}
	 
	public NumeroUnicoEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	 
	}
	 
	public NumeroUnicoEditText(Context context) {
		super(context);
		initialize();
		 
	}
	 
	public String getCleanText() {
		String text = NumeroUnicoEditText.this.getText().toString();
		 
		text.replaceAll("[^0-9]*", "");
		return text;
	 
	}
	 
	private void initialize() {
	 
		//final int maxNumberLength = 11;
		final int maxNumberLength = 21;
		this.setKeyListener(keylistenerNumber);
		 
		//this.setText("( ) - ");
		this.setText(" - . . . .");
		this.setSelection(1);
		 
		this.addTextChangedListener(new TextWatcher() {
		public void afterTextChanged(Editable s) {
		String current = s.toString();
	 
	/*
	* Ok, here is the trick... calling setText below will recurse
	* to this function, so we set a flag that we are actually
	* updating the text, so we don't need to reprocess it...
	*/
	if (isUpdating) {
		isUpdating = false;
		return;
		 
	}
	 
	/* Strip any non numeric digit from the String... */
	String number = current.replaceAll("[^0-9]*", "");
	if (number.length() > 21)
	number = number.substring(0, 21);
	int length = number.length();
	 
	/* Pad the number to 10 characters... */
	String paddedNumber = padNumber(number, maxNumberLength);
	 
	/* Split phone number into parts... */
	
	//1234567-12.1234.8.10.1234
	//(12) 3456-7890
	
	/*String ddd = paddedNumber.substring(0, 2);
	String part1 = paddedNumber.substring(2, 6);
	String part2 = paddedNumber.substring(6, 11).trim();*/
	
	String part1 = paddedNumber.substring(0, 7);
	String part2 = paddedNumber.substring(7, 9);
	String part3 = paddedNumber.substring(9, 13);
	String part4 = paddedNumber.substring(13, 14);
	String part5 = paddedNumber.substring(15, 17);
	String part6 = paddedNumber.substring(17, 21).trim();
	
	/* build the masked phone number... */
	//String phone = "(" + ddd + ") " + part1 + "-" + part2;
	String numeroUnico = part1 + " - " + part2 + " . " + part3 + " . " + part4 + " . " + part5 + " . " + part6;
	  
	/*
	* Set the update flag, so the recurring call to
	* afterTextChanged won't do nothing...
	*/
	isUpdating = true;
	NumeroUnicoEditText.this.setText(numeroUnico);
	 
	NumeroUnicoEditText.this.setSelection(positioning[length]);
	 
	}
	 
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	 
	}
	 
	public void onTextChanged(CharSequence s, int start, int before,
		int count) {
		 
		}
		});
	}
	 
	protected String padNumber(String number, int maxLength) {
		String padded = new String(number);
		for (int i = 0; i < maxLength - number.length(); i++)
		padded += " ";
		return padded;
		 
	}
	 
	private final KeylistenerNumber keylistenerNumber = new KeylistenerNumber();
	 
	private class KeylistenerNumber extends NumberKeyListener {
	 
	public int getInputType() {
		return InputType.TYPE_CLASS_NUMBER
		| InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
		 
	}
	 
	@Override
	protected char[] getAcceptedChars() {
		return new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8',
		'9' };
		 
		}
	}
}