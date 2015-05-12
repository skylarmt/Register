package net.apocalypselabs.register;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.text.*;

public class Check extends Activity {
	private double amount;
    private TextView to;
	
	
	@Override
	public void onCreate(Bundle state) {
		super.onCreate(state);
		setContentView(R.layout.check);
		Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null) {
            amount = bundle2.getDouble("amount");
            to = (TextView)this.findViewById(R.id.checkTotalBox);
            to.setText(this.getString(R.string.total) + ": "
					   + NumberFormat.getCurrencyInstance().format(this.amount));
            return;
        }
        Toast.makeText((Context)this, (CharSequence)this.getString(R.string.argerror), (int)1).show();
        this.startActivity(new Intent(this.getApplicationContext(), (Class)Main.class));
	}
	
	public void checkout() {
        double paid = amount;
        Intent intent = new Intent(this.getApplicationContext(), Thanks.class);
        intent.putExtra("total", amount);
        intent.putExtra("paid", amount);
        this.startActivity(intent);
    }
	
	public void next(View v) {
		checkout();
	}
}
