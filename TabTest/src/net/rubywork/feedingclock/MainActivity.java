package net.rubywork.feedingclock;

import java.util.HashMap;
import java.util.Map;

import net.rubywork.feedingclock.ui.listener.ChangeButtonListener;
import net.rubywork.feedingclock.ui.listener.HistoryMenuViewCallback;
import net.rubywork.feedingclock.ui.listener.LeftButtonListener;
import net.rubywork.feedingclock.ui.listener.MusicButtonListener;
import net.rubywork.feedingclock.ui.listener.PauseButtonListener;
import net.rubywork.feedingclock.ui.listener.RightButtonListener;
import net.rubywork.feedingclock.ui.listener.StopButtonListener;
import net.rubywork.feedingclock.ui.listener.SubMenuButtonListener;
import net.rubywork.feedingclock.ui.listener.SubMenuButtonListener.MenuViewCallback;
import net.rubywork.feedingclock.ui.listener.SubMenuButtonListener.MenuViewEntry;
import net.rubywork.feedingclock.ui.support.AppContext;
import net.rubywork.feedingclock.ui.support.LastFeedingRecordThread;
import net.rubywork.feedingclock.ui.view.MainMenuButton;
import net.rubywork.feedingclock.ui.view.SubMenuButton;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	{
		AppContext.getInstance().setMainActivity(this); // * important : First of all, it must do invocation!
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		init();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		AppContext.getInstance().setLastFeedingRecordThread(new LastFeedingRecordThread()).start();
	}

	private void init() {
		initView();
		initListeners();
	}

	private static final int [] MENU_BUTTON_IDs = {R.id.menuSettingsButton, R.id.menuMusicButton, R.id.menuHistoryButton, R.id.menuRecordButton};  // it determined buttons order for displaying.
	private static final int [] CONTAINER_VIEW_IDs = {R.id.moreContainer, R.id.soundContainer, R.id.historyContainer, R.id.feedingContainer};
	
	private void initView() {
		initMenuButtonAndContainerView();
	}
	
	private void initMenuButtonAndContainerView(){
		SubMenuButton[] subButtons = new SubMenuButton[MENU_BUTTON_IDs.length];
		MainMenuButton mainButton = (MainMenuButton) findViewById(R.id.mainButton);
		mainButton.setImageView((ImageView) findViewById(R.id.mainButtonImage));
		mainButton.setButtons(subButtons);
		
		Map<SubMenuButton, MenuViewEntry> menuButtonViewMap = new HashMap<SubMenuButton, MenuViewEntry>();
		SubMenuButtonListener subMenuButtonListener = new SubMenuButtonListener(mainButton, menuButtonViewMap);
		MenuViewCallback [] menuViewCallbacks = {null, null, new HistoryMenuViewCallback(), null};
		
		for(int i = 0; i<MENU_BUTTON_IDs.length; i++){
			subButtons[i] = (SubMenuButton) findViewById(MENU_BUTTON_IDs[i]);
			menuButtonViewMap.put(subButtons[i], new MenuViewEntry(findViewById(CONTAINER_VIEW_IDs[i]), menuViewCallbacks[i]));

			subButtons[i].initAnimation(subButtons.length, i);
			subButtons[i].setOnClickListener(subMenuButtonListener);
		}
	}

	private void initListeners() {
		findViewById(R.id.leftButton).setOnClickListener(new LeftButtonListener());
		findViewById(R.id.rightButton).setOnClickListener(new RightButtonListener());
		// new BottleButtonOnClickListener(this);

		View pauseButton = findViewById(R.id.pauseResumeButton);
		pauseButton.setOnClickListener(new PauseButtonListener(pauseButton));
		View stopButton = findViewById(R.id.stopButton);
		StopButtonListener stopButtonListener = new StopButtonListener();
		stopButton.setOnTouchListener(stopButtonListener);
		stopButton.setOnClickListener(stopButtonListener);

		View changeButton = findViewById(R.id.changeButton);
		ChangeButtonListener changeButtonListener = new ChangeButtonListener();
		changeButton.setOnTouchListener(changeButtonListener);
		changeButton.setOnClickListener(changeButtonListener);

		findViewById(R.id.ticTok).setOnClickListener(new MusicButtonListener());
	}
}