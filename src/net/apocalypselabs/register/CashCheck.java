package net.apocalypselabs.register;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;
import net.apocalypselabs.register.Main;
import net.apocalypselabs.register.MoneyTextWatcher;
import net.apocalypselabs.register.Thanks;

/*
 * Failed to analyse overrides
 */
public class CashCheck extends Activity implements View.OnKeyListener {
    private double amount;
    private EditText in;
    private TextView to;

    public void add(double d) {
        String string = this.in.getText().toString().replaceAll("\\$", "").replaceAll(",", "");
        try {
            double d2 = d + Double.parseDouble((String)string);
            in.setText((CharSequence)NumberFormat.getCurrencyInstance().format(d2));
            return;
        }
        catch (Exception var4_4) {
            Toast.makeText((Context)this, (CharSequence)"Invalid Input!", (int)0).show();
            return;
        }
    }

    public void add(View view) {
        if (view.equals((Object)this.findViewById(R.id.oneBtn))) {
            this.add(1.0);
            return;
        } else {
            if (view.equals((Object)this.findViewById(R.id.twoBtn))) {
                this.add(2.0);
                return;
            }
            if (view.equals((Object)this.findViewById(R.id.fiveBtn))) {
                this.add(5.0);
                return;
            }
            if (view.equals((Object)this.findViewById(R.id.tenBtn))) {
                this.add(10.0);
                return;
            }
            if (view.equals((Object)this.findViewById(R.id.twentyBtn))) {
                this.add(20.0);
                return;
            }
        }
    }

    public void checkout() {
        double paid;
        String string = in.getText().toString().replaceAll("\\$", "").replaceAll(",", "");
        try {
            paid = Double.parseDouble((String)string);
        }
        catch (Exception var2_4) {
            Toast.makeText(this, "Invalid Input!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this.getApplicationContext(), Thanks.class);
        intent.putExtra("total", amount);
        intent.putExtra("paid", paid);
        this.startActivity(intent);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(R.layout.cashcheck);
        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null) {
            this.amount = bundle2.getDouble("amount");
            this.to = (TextView)this.findViewById(R.id.totalBox);
            this.to.setText((CharSequence)(this.getString(R.string.total) + ": " + NumberFormat.getCurrencyInstance().format(this.amount)));
            this.in = (EditText)this.findViewById(R.id.priceBox);
            this.in.addTextChangedListener((TextWatcher)new MoneyTextWatcher(this.in));
            this.in.setOnKeyListener((View.OnKeyListener)this);
            return;
        }
        Toast.makeText((Context)this, (CharSequence)this.getString(R.string.argerror), (int)1).show();
        this.startActivity(new Intent(this.getApplicationContext(), (Class)Main.class));
    }

    public boolean onKey(View view, int n, KeyEvent keyEvent) {
        switch (n) {
            default: {
                return false;
            }
            case 66: 
            case 160: 
        }
        this.checkout();
        return true;
    }
}

