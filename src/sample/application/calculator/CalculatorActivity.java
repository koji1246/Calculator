package sample.application.calculator;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.os.Bundle;
import android.app.Activity;
import android.text.ClipboardManager;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class CalculatorActivity extends Activity {
	//インスタンス変数
	String strTemp = "";
	String strResult = "0";
	int operator = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void numKeyOnClick(View v){
    	String strInKey = (String) ((Button)v).getText();
    	
    	if(strInKey.equals(".")){
    		if(strTemp.length()==0){
    				strTemp="0.";
    		}else{
    			if(strTemp.indexOf(".")==-1){
    					strTemp=strTemp+".";
    			
    			}
    		}
    	}else{
    		strTemp=strTemp+strInKey;
    	}
    	showNuber(strTemp);
    }

	private void showNuber(String strNum) {
		DecimalFormat form = new DecimalFormat("#,##0");
		String strDecimal="";
		String strInt = "";
		String fText = "";
		
		if(strNum.length()>0){
			int decimalPoint=strNum.indexOf(".");
			if(decimalPoint>-1){
				strDecimal=strNum.substring(decimalPoint);
				strInt=strNum.substring(0,decimalPoint);
			}else{
				strInt=strNum;
			}
			fText=form.format(Double.parseDouble(strInt))+strDecimal;
		}else fText="0";
		((TextView)findViewById(R.id.displayPanel)).setText(fText);
		
	}
	
	public void functionKeyOnClick(View v){
		switch(v.getId()){
			case R.id.keypadAC:
				this.strTemp="";
				this.strResult="0";
				this.operator=0;
				break;
			case R.id.keypadC:
				this.strTemp="";
				break;
			case R.id.keypadBS:
				if(this.strTemp.length()==0)return;
				else strTemp=strTemp.substring(0,strTemp.length()-1);
				break;
			case R.id.keypadCopy:
				ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
				cm.setText(((TextView)findViewById(R.id.displayPanel)).getText());
				return;
		}
		showNuber(strTemp);
	}
	
	public void operatorKeyOnClick(View v){
		if(operator!=0){
			if(strTemp.length()>0){
				strResult=doCalc();
				showNuber(strResult);
			}
			else{
				if(strTemp.length()>0){
					strResult=strTemp;
				}
			}
			strTemp="";
			
			if (v.getId()==R.id.keypadEq){
				operator=0;
			}else{
				operator=v.getId();
			}
		}
		
	
	}

	private String doCalc() {
		BigDecimal bd1=new BigDecimal (this.strResult);
    	BigDecimal bd2=new BigDecimal (this.strTemp);
		BigDecimal result = BigDecimal.ZERO;
		
		switch(operator){
		case R.id.keypadAdd:
			result=bd1.add(bd2);
			break;
		}
		return null;
	}
}
