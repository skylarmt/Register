package net.apocalypselabs.register;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.text.*;

import com.stripe.android.model.Card;
import java.util.HashMap;
import com.stripe.model.Charge;
import com.stripe.android.*;
import com.stripe.android.model.*;
import java.util.*;

public class Credit extends Activity
{
	private double amount;
    private TextView to;

	@Override
	public void onCreate(Bundle state)
	{
		super.onCreate(state);
		setContentView(R.layout.credit);
		Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null)
		{
            amount = bundle2.getDouble("amount");
            to = (TextView)this.findViewById(R.id.creditTotalBox);
            to.setText(this.getString(R.string.total) + ": "
					   + NumberFormat.getCurrencyInstance().format(amount));
            return;
        }
        Toast.makeText((Context)this, (CharSequence)this.getString(R.string.argerror), (int)1).show();
        this.startActivity(new Intent(this.getApplicationContext(), (Class)Main.class));
	}

	public void checkout()
	{
        double paid = amount;
        Intent intent = new Intent(this.getApplicationContext(), Thanks.class);
        intent.putExtra("total", amount);
        intent.putExtra("paid", amount);
        this.startActivity(intent);
    }

	public void next(View v)
	{
		Toast.makeText(this, "Charging...", Toast.LENGTH_SHORT).show();
		try
		{
			String number = ((EditText)findViewById(R.id.cardNumber)).getText().toString();
			String expiry = ((EditText)findViewById(R.id.cardExp)).getText().toString();
			String cvv = ((EditText)findViewById(R.id.cardCvv)).getText().toString();
			int month = Integer.parseInt(expiry.substring(0, 2));
			int year = Integer.parseInt(getString(R.string.yearPrefix) + expiry.substring(2));
			Card card = new Card(number, month, year, cvv);
			if (!(card.validateCard() && card.validateCVC() && card.validateExpiryDate()))
			{
				Toast.makeText(this, "Error: Invalid card data!", Toast.LENGTH_SHORT).show();
				return;
			}
			Stripe stripe = new Stripe(getString(R.string.stripe_pubkey));
			stripe.createToken(
				card,
				new TokenCallback() {
					public void onSuccess(Token token)
					{
						try
						{
							Map chargeMap = new HashMap<String, Object>();
							chargeMap.put("amount", (int)(amount * 100));
							chargeMap.put("currency", "usd");
							chargeMap.put("card", token.getId());

							Charge charge = Charge.create(chargeMap, getString(R.string.stripe_pubkey));
							if (charge.getPaid())
							{
								checkout();
							}
							else
							{
								System.err.println("Charge unsuccessful!");
							}
						}
						catch (Exception ex)
						{
							System.err.println("Card Error: "+ex.getMessage());
						}
					}
					public void onError(Exception error)
					{
						System.err.println("Card error: "+error.getMessage());
					}
				}
			);
		}
		catch (Exception ex)
		{
			Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
		}
	}
}
