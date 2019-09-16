package com.bluemind.bluemind.newsfeed;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bluemind.bluemind.R;

import java.io.IOException;

public class CreatePost extends AppCompatActivity {
    private ImageView imageView1, imageView2, imageView3;
    private static final int READ_REQUEST_CODE = 42;
    private Button attachImages, cretePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);

        imageView1 = (ImageView) findViewById(R.id.image1);
        imageView2 = (ImageView) findViewById(R.id.image2);
        imageView3 = (ImageView) findViewById(R.id.image3);

        imageView1.setVisibility(View.GONE);
        imageView2.setVisibility(View.GONE);
        imageView3.setVisibility(View.GONE);

        attachImages = (Button) findViewById(R.id.attachPictureOrVideo);
        attachImages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performFileSearch();
            }
        });

        cretePost = (Button) findViewById(R.id.postContents);
        cretePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void performFileSearch() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        super.onActivityResult(requestCode, resultCode, resultData);
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = resultData.getData();
            try{
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                imageView1.setImageBitmap(bitmap);
                imageView1.setVisibility(View.VISIBLE);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
