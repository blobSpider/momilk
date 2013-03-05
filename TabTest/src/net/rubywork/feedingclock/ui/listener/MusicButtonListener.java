package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import net.rubywork.feedingclock.ui.support.AppContext;
import android.media.MediaPlayer;
import android.view.View;
import android.view.View.OnClickListener;

public class MusicButtonListener implements OnClickListener {
	
	private MediaPlayer whiteNoise = MediaPlayer.create(AppContext.getInstance().getMainActivity(), R.raw.whitenoise);
	
	@Override
	public void onClick(View view) {
		whiteNoise.start();
	}

}
