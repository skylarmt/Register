package net.apocalypselabs.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import net.apocalypselabs.register.CashCheck;
import net.apocalypselabs.register.Main;

/*
 * Failed to analyse overrides
 */
public class Pay
extends Activity {
    double amount = 0.0;
    int type = -1;

    public void loadBitcoin() {
    }

    public void loadCash() {
        Intent intent = new Intent(this.getApplicationContext(), (Class)CashCheck.class);
        intent.putExtra("amount", this.amount);
        this.startActivity(intent);
    }

    public void loadCheck() {
    }

    public void loadCredit() {
    }

    public void loadGift() {
    }

    public void loadMethod() {
        switch (this.type) {
            case V.CASH:
                loadCash();
                break;
            case V.CHECK:
                loadCheck();
                break;
            case V.CREDIT:
                loadCredit();
                break;
            case V.GIFT:
                loadGift();
                break;
            case V.BITCOIN:
				loadBitcoin();
				break;
			default:
				break;
		}
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null) {
            type = bundle2.getInt("method");
            amount = bundle2.getDouble("amount");
            loadMethod();
            return;
        } else {
    	    Toast.makeText((Context)this, (CharSequence)this.getString(R.string.argerror), Toast.LENGTH_SHORT).show();
        	startActivity(new Intent(this.getApplicationContext(), (Class)Main.class));
    	}
	}
}

