package com.it.dadishu;

import java.util.Random;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageButton[] btns;
	int index;
	int longth = 10*1000;//游戏限制时间
	int time = 0;//游戏进行时间

	@Override
	protected void onCreate(Bundle savedInstanceState) {//运行在主线程中
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btns = new ImageButton[6];
		btns[0] = (ImageButton) findViewById(R.id.imageButton1);
		btns[1] = (ImageButton) findViewById(R.id.imageButton2);
		btns[2] = (ImageButton) findViewById(R.id.imageButton3);
		btns[3] = (ImageButton) findViewById(R.id.imageButton4);
		btns[4] = (ImageButton) findViewById(R.id.imageButton5);
		btns[5] = (ImageButton) findViewById(R.id.imageButton6);

		// index = new Random().nextInt(6);// 耗时非常短，所以可以写在主线程中

		// Android规定所有的更改界面显示的代码必须写到主线程中
		// 所以需要将设置ImageView显示地鼠的代码写到主线程中
		// 为了提高用户体验，需要将耗时的操作放到子线程中

		new Thread(new Runnable() {
			@Override
			public void run() {// 这里面的(耗时)代码都是在子线程中运行
				while (time < longth) {// 地鼠不停的钻出来
					// 每隔2秒钟地鼠钻出来一个
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					time = time + 2000;
					// System.out.println("aaaa");

					index = new Random().nextInt(6);// 耗时非常短，所以在主线程中运行

					// 如何在子线程中编写在主线程中运行的代码
					runOnUiThread(new Runnable() {

						@Override
						public void run() {// 此处编写的是在主线程中运行的代码
							btns[index]
									.setImageResource(R.drawable.ic_launcher);
							// 为ImageButton添加一个标签，表示当前显示的是地鼠
							btns[index].setTag("dishu");
						}
					});
				}

				System.out.println("nbnb " + score);
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(MainActivity.this, "" + score, 1).show();
					}
				});

			}
		}).start();

	}

	int score;

	public void fun(View view) {
		ImageButton btn = (ImageButton) view;

		String tag = (String) btn.getTag();
		// 计分，每次单击都判断当前所单击位置是不是地鼠，如果是score+1,否则不加
		// 获取ImageButton现在所显示的图片，曲线反映；
		if ("dishu".equals(tag)) {
			score++;
			btn.setImageResource(R.drawable.ic);
			btn.setTag("beijing");
		}
		Toast.makeText(this, "" + score, 1).show();
		btn.setImageResource(R.drawable.ic);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
