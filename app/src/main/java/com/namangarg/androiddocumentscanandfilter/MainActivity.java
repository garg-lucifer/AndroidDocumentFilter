package com.namangarg.androiddocumentscanandfilter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.namangarg.androiddocumentscannerandfilter.DocumentFilter;
import com.namangarg.androiddocumentscannerandfilter.Filters.BlackAndWhiteFilter;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int IMAGE_REQUEST = 1;
    DocumentFilter documentFilter;
    Button load, g1, g2, g3, g4, g5;
    ImageView imageView;
    Bitmap gb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load = findViewById(R.id.load);
        g1 = findViewById(R.id.g1);
        g2 = findViewById(R.id.g2);
        g3 = findViewById(R.id.g3);
        g4 = findViewById(R.id.g4);
        g5 = findViewById(R.id.g5);
        imageView = findViewById(R.id.image);

        documentFilter = new DocumentFilter();

        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentFilter.getBlackAndWhiteFilter(gb1, new DocumentFilter.CallBack<Bitmap>() {
                    @Override
                    public void onCompleted(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });

        g2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentFilter.getGreyScaleFilter(gb1, new DocumentFilter.CallBack<Bitmap>() {
                    @Override
                    public void onCompleted(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });

        g3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentFilter.getLightenFilter(gb1, new DocumentFilter.CallBack<Bitmap>() {
                    @Override
                    public void onCompleted(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });

        g4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentFilter.getMagicFilter(gb1, new DocumentFilter.CallBack<Bitmap>() {
                    @Override
                    public void onCompleted(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });

        g5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                documentFilter.getShadowRemoval(gb1, new DocumentFilter.CallBack<Bitmap>() {
                    @Override
                    public void onCompleted(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        });

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            try {
                gb1 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageURI(uri);
        }
    }
}