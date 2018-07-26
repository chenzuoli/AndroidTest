package top.wetech.czl.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PhoneActivity extends AppCompatActivity {
    private EditText etPhone;
    private Button btnPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        etPhone = (EditText) findViewById(R.id.et_phone_num);
        btnPhone = (Button) findViewById(R.id.btn_call_phone);

        btnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editable phoneText = etPhone.getText();
                if (phoneText == null || !phoneText.toString().trim().matches("\\d+")) {
                    Toast.makeText(PhoneActivity.this, "sorry, the phone number is blank!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneText.toString()));
//                intent.setAction("android.intent.action.CALL");
//                intent.setData(Uri.parse("tel:" + phoneText.toString()));
                if (ActivityCompat.checkSelfPermission(PhoneActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(PhoneActivity.this, "sorry, the app is permissed to call phone!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(PhoneActivity.this, "call the phone " + phoneText.toString(), Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

    }
}
