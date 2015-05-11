/*
 * Decompiled with CFR 0_92.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.view.View
 *  android.widget.TextView
 *  android.widget.Toast
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.String
 *  java.text.NumberFormat
 */
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
        this.startActivity(new Intent(this.getApplicationContext(), (Class)Main.class));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968579);
        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null) {
            double d = bundle2.getDouble("total");
            double d2 = bundle2.getDouble("paid");
            ((TextView)this.findViewById(2131099660)).setText((CharSequence)(this.getString(2131034113) + ": " + NumberFormat.getCurrencyInstance().format(d)));
            ((TextView)this.findViewById(2131099661)).setText((CharSequence)(this.getString(2131034124) + ": " + NumberFormat.getCurrencyInstance().format(d2 - d)));
            return;
        }
        Toast.makeText((Context)this, (CharSequence)this.getString(2131034120), (int)1).show();
        this.startActivity(new Intent(this.getApplicationContext(), (Class)Main.class));
    }
}

