package in.edu.cpi.cpica;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_password extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_password);

        TextView forgot_password_btn = (TextView)findViewById(R.id.forgot_password_btn);
        forgot_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder password_helper = new AlertDialog.Builder(Login_password.this);
                password_helper.setMessage("Please contact your class mentor in order to reset your account password.");
                password_helper.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = password_helper.create();
                dialog.show();
            }
        });

    }

    public void check_login_password(View v){
        EditText login_password = (EditText) findViewById(R.id.login_password);
        TextView password_error = (TextView) findViewById(R.id.password_error);
        FloatingActionButton password_next_btn = (FloatingActionButton)findViewById(R.id.password_next_btn);
        Boolean error_occurrence =false;
        Animation fab_btn_error_bounce,password_error_fade_anim;
        fab_btn_error_bounce = AnimationUtils.loadAnimation(this,R.anim.fab_btn_error_bounce);
        password_error_fade_anim=AnimationUtils.loadAnimation(this,R.anim.fade_out_fade_in_using_reverse);
        Vibrator buzzer = (Vibrator)this.getSystemService(Context.VIBRATOR_SERVICE);
        String login_username = getIntent().getExtras().getString("login_username");

        if (login_password.length() < 4 && login_password.length() > 0 ) {
            password_error.setText("Password is invalid.");
            error_occurrence =true;
        }
        else if (login_password.length() < 1 ) {
            password_error.setText("Please enter your password.");
            error_occurrence =true;
        }
        else {
            error_occurrence =false;
        }

        if(error_occurrence==false){
            if(login_username.toString().substring(0,3).contentEquals("CPI")){
                Intent i = new Intent(this, Admin_dashboard.class);
                startActivity(i);
            }
            else{
                Intent i = new Intent(this, Student_dashboard.class);
                startActivity(i);
            }
            password_next_btn.setRippleColor(Color.WHITE);
            password_error.setAlpha(0);
        }
        else{
            long[] buzz_pattern= {0,30,70,20};
            buzzer.vibrate(buzz_pattern,-1);
            password_next_btn.startAnimation(fab_btn_error_bounce);
            password_error.startAnimation(password_error_fade_anim);
            password_error.setAlpha(1);
            password_next_btn.setRippleColor(Color.RED);
        }
    }

}
