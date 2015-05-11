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
import net.apocalypselabs.register.MoneyTextWatcher;
import net.apocalypselabs.register.Pay;

public class Main extends Activity implements View.OnKeyListener {
    private EditText in;
    private TextView to;
    public double total = 0.0;

    private void openCheckout(int n) {
        Intent intent = new Intent(this.getApplicationContext(), (Class)Pay.class);
        intent.putExtra("method", n);
        intent.putExtra("amount", this.total);
        this.startActivity(intent);
    }

    public void addToTotal(String string) {
        String string2 = string.replaceAll("\\$", "").replaceAll(",", "");
        try {
            double d = Double.parseDouble((String)string2);
            if (d + this.total >= 10000.0) {
                Toast.makeText(this, getString(R.string.limit), (int)0).show();
            } else {
                this.total = d + this.total;
            }
        }
        catch (Exception var3_4) {
            Toast.makeText((Context)this, (CharSequence)"Invalid Input!", (int)0).show();
        }
        this.to.setText((CharSequence)(this.getString(R.string.total) + ": " + NumberFormat.getCurrencyInstance().format(this.total)));
        this.in.setText((CharSequence)this.getString(R.string.zeros));
    }

    /*
     * Enabled aggressive block sorting
     */
    public void checkout(View view) {
        if (view.equals((Object)this.findViewById(R.id.cashBtn))) {
            this.openCheckout(V.CASH);
            return;
        } else {
            if (view.equals((Object)this.findViewById(R.id.checkBtn))) {
                this.openCheckout(V.CHECK);
                return;
            }
            if (view.equals((Object)this.findViewById(R.id.creditBtn))) {
                this.openCheckout(V.CREDIT);
                return;
            }
            if (view.equals((Object)this.findViewById(R.id.giftBtn))) {
                this.openCheckout(V.GIFT);
                return;
            }
            if (!view.equals((Object)this.findViewById(R.id.bitcoinBtn))) return;
            {
                this.openCheckout(V.BITCOIN);
                return;
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main);
        in = (EditText)this.findViewById(R.id.priceBox);
        to = (TextView)this.findViewById(R.id.totalBox);
        to.setText(getString(R.string.total) + ": " + NumberFormat.getCurrencyInstance().format(0.0));
        in.setOnKeyListener((View.OnKeyListener)this);
        in.addTextChangedListener(new MoneyTextWatcher(in));
        if (bundle != null) {
            this.total = bundle.getDouble("total");
            this.addToTotal("0.0");
        }
        this.in.append((CharSequence)"");
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean onKey(View view, int n, KeyEvent keyEvent) {
        switch (n) {
            default: {
                return false;
            }
            case 111: {
                this.total = 0.0;
                this.in.setText((CharSequence)this.getString(R.string.zeros));
                this.to.setText((CharSequence)this.getString(R.string.zeros));
                do {
                    return true;
                } while (true);
            }
            case 66: 
            case 160: 
        }
        this.addToTotal(this.in.getText().toString());
        return true;
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putDouble("total", this.total);
        super.onSaveInstanceState(bundle);
    }
}

