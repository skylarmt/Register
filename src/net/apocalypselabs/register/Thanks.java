package net.apocalypselabs.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;
import net.apocalypselabs.register.Main;

/*
 * Failed to analyse overrides
 */
public class Thanks
extends Activity {
    public void newtrans(View view) {
        this.startActivity(new Intent(this.getApplicationContext(), Main.class));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.thanks);
        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null) {
            double total = bundle2.getDouble("total");
            double paid = bundle2.getDouble("paid");
            ((TextView) findViewById(R.id.total_box)).setText(getString(R.string.total) + ": " + NumberFormat.getCurrencyInstance().format(total));
            ((TextView) findViewById(R.id.change_box)).setText(getString(R.string.change) + ": " + NumberFormat.getCurrencyInstance().format(paid-total));
            return;
        } else {
      		Toast.makeText(this, (CharSequence)this.getString(R.string.argerror), Toast.LENGTH_SHORT).show();
      		startActivity(new Intent(getApplicationContext(), Main.class));
		}
    }
}

