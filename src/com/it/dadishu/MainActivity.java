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
	int longth = 10*1000;//��Ϸ����ʱ��
	int time = 0;//��Ϸ����ʱ��

	@Override
	protected void onCreate(Bundle savedInstanceState) {//���������߳���
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btns = new ImageButton[6];
		btns[0] = (ImageButton) findViewById(R.id.imageButton1);
		btns[1] = (ImageButton) findViewById(R.id.imageButton2);
		btns[2] = (ImageButton) findViewById(R.id.imageButton3);
		btns[3] = (ImageButton) findViewById(R.id.imageButton4);
		btns[4] = (ImageButton) findViewById(R.id.imageButton5);
		btns[5] = (ImageButton) findViewById(R.id.imageButton6);

		// index = new Random().nextInt(6);// ��ʱ�ǳ��̣����Կ���д�����߳���

		// Android�涨���еĸ��Ľ�����ʾ�Ĵ������д�����߳���
		// ������Ҫ������ImageView��ʾ����Ĵ���д�����߳���
		// Ϊ������û����飬��Ҫ����ʱ�Ĳ����ŵ����߳���

		new Thread(new Runnable() {
			@Override
			public void run() {// �������(��ʱ)���붼�������߳�������
				while (time < longth) {// ����ͣ�������
					// ÿ��2���ӵ��������һ��
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					time = time + 2000;
					// System.out.println("aaaa");

					index = new Random().nextInt(6);// ��ʱ�ǳ��̣����������߳�������

					// ��������߳��б�д�����߳������еĴ���
					runOnUiThread(new Runnable() {

						@Override
						public void run() {// �˴���д���������߳������еĴ���
							btns[index]
									.setImageResource(R.drawable.ic_launcher);
							// ΪImageButton���һ����ǩ����ʾ��ǰ��ʾ���ǵ���
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
		// �Ʒ֣�ÿ�ε������жϵ�ǰ������λ���ǲ��ǵ��������score+1,���򲻼�
		// ��ȡImageButton��������ʾ��ͼƬ�����߷�ӳ��
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
