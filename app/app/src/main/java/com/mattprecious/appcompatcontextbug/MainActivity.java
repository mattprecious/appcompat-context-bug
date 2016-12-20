package com.mattprecious.appcompatcontextbug;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public final class MainActivity extends AppCompatActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    FrameLayout parent = new FrameLayout(this);
    parent.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));

    // Correct context is used when parent is attached to window before inflating the text view.
    // setContentView(parent);

    LayoutInflater inflater = LayoutInflater.from(this)
        .cloneInContext(new ContextThemeWrapper(this, R.style.ThemeOverlay_AppCompat_Dark));

    inflater.inflate(R.layout.text, parent, true);

    // Wrong context is used when parent is not attached to the window when inflating the text view.
    setContentView(parent);
  }
}
