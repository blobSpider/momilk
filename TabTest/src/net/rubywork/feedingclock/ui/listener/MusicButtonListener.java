package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.ui.support.AppContext;
import android.app.Activity;
import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MusicButtonListener implements OnClickListener {
	
	private AppContext appContext;
	private MediaPlayer mediaPlayer;
	private Button controlBtn;
	
	public MusicButtonListener() {
		this.appContext = AppContext.getInstance();
		Activity activity = appContext.getMainActivity();
		this.mediaPlayer = MediaPlayer.create(appContext.getMainActivity(), R.raw.whitenoise);
		this.controlBtn = (Button) activity.findViewById(R.id.play);
	}
	
	@Override
	public void onClick(View view) {
		mediaPlayer.setLooping(true);
		if(!mediaPlayer.isPlaying()){
			mediaPlayer.start();
			controlBtn.setText("Stop");
		}else{
			mediaPlayer.pause();
			controlBtn.setText("Play");
		}
	}
}
