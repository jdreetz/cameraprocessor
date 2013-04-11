package com.jamesmedia.cameratest;

import java.io.IOException;

import com.jmedia.cameratest.*;
import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class main extends Activity implements PreviewCallback, SurfaceHolder.Callback {

	Camera camera;
	Button startPreview, stopPreview;
	Boolean surfaceAvailable = false;
	SurfaceView view;
	
	@Override
	public void onPreviewFrame(byte[] arg0, Camera arg1) {
		

	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState)	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_screen);
		
	}
	
	@Override
	protected void onStart(){
		super.onStart();
		startPreview = (Button)findViewById(R.id.button1);
		startPreview.setOnClickListener(new startPreview());
		
		stopPreview = (Button)findViewById(R.id.button2);
		stopPreview.setOnClickListener(new stopPreview());
		
		view = (SurfaceView)findViewById(R.id.view1);
		view.getHolder().addCallback(this);
		view.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);		
	}
	
	@Override
	protected void onPause()	{
		super.onPause();
	}
	
	@Override
	protected void onResume()	{
		super.onResume();
	}
	
	@Override
	protected void onStop()	{
		super.onStart();
		if(camera != null)
			camera.release();
	}	
	
	private class startPreview implements View.OnClickListener	{

		@Override
		public void onClick(View v) {	
			if(surfaceAvailable)
			{
				camera = Camera.open();
				Parameters params = camera.getParameters();
				params.setPreviewSize(176,144);
				camera.setParameters(params);
				
				try {
					camera.setPreviewDisplay(view.getHolder());
					camera.startPreview();
				} catch (IOException e) {
					Log.d("error", e.getMessage());
				}
			}
		}		
	}
	
	private class stopPreview implements View.OnClickListener	{

		@Override
		public void onClick(View v) {
			if(camera != null)
				camera.stopPreview();
		}		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
		
		
	}


	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		surfaceAvailable = true;		
	}


	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

		
	}
	
	
}
