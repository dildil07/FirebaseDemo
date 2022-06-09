package pas22123.genap22.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private TextView nameTxtView, emailTxtView;
    private Button logoutBtn;
    private ImageView profileImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        ImageView imageView = (ImageView) findViewById(R.id.imgViewProfile);
        Glide.with(this).load(currentUser.getPhotoUrl()).into(imageView);

        nameTxtView = findViewById(R.id.txtViewNama);
        emailTxtView = findViewById(R.id.txtViewEmail);

        nameTxtView.setText(currentUser.getDisplayName());
        emailTxtView.setText(currentUser.getEmail());

        logoutBtn = findViewById(R.id.btnLogout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}