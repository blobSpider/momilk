package net.rubywork.feedingclock.ui.listener;

import net.rubywork.feedingclock.R;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.View;
import android.view.View.OnClickListener;

public class MusicButtonListener implements OnClickListener {
	
	private SoundPool soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
	
	@Override
	public void onClick(View view) {
		this.soundPool.play(soundPool.load(view.getContext(), R.raw.tictok, 1), 1, 1, 0, 0, 1);
	}
}
