/*
 * Decompiled with CFR 0_92.
 * 
 * Could not load the following classes:
 *  android.text.Editable
 *  android.text.TextWatcher
 *  android.widget.EditText
 *  java.lang.CharSequence
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.ref.WeakReference
 *  java.math.BigDecimal
 *  java.text.NumberFormat
 */
package net.apocalypselabs.register;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.lang.ref.*;

/*
 * Failed to analyse overrides
 */
public class MoneyTextWatcher
implements TextWatcher {
    private final WeakReference<EditText> editTextWeakReference;

    public MoneyTextWatcher(EditText editText) {
        editTextWeakReference = new WeakReference(editText);
    }

    public void afterTextChanged(Editable editable) {
        EditText editText = (EditText) editTextWeakReference.get();
        if (editText == null) {
            return;
        }
        String string = editable.toString();
        editText.removeTextChangedListener((TextWatcher)this);
        BigDecimal bigDecimal = new BigDecimal(string.toString().replaceAll("[$,.]", "")).setScale(2, 3).divide(new BigDecimal(100), 3);
        String string2 = NumberFormat.getCurrencyInstance().format((Object)bigDecimal);
        editText.setText((CharSequence)string2);
        editText.setSelection(string2.length());
        editText.addTextChangedListener((TextWatcher)this);
    }

    public void beforeTextChanged(CharSequence charSequence, int n, int n2, int n3) {
    }

    public void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
    }
}

