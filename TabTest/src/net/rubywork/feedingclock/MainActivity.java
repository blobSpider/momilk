package net.rubywork.feedingclock;

import java.util.HashMap;
import java.util.Map;

import net.rubywork.feedingclock.ui.listener.BottleButtonListener;
import net.rubywork.feedingclock.ui.listener.BottleMeasureButtonListener;
import net.rubywork.feedingclock.ui.listener.BottleMilkDecreaseButtonListener;
import net.rubywork.feedingclock.ui.listener.BottleMilkIncreaseButtonListener;
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
import net.rubywork.feedingclock.ui.support.ResourceUtils;
import net.rubywork.feedingclock.ui.view.MainMenuButton;
import net.rubywork.feedingclock.ui.view.SubMenuButton;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	{
		// AppContext.getInstance().setMainActivity(this); // * important :
		// First of all, it must do invocation!
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppContext.getInstance().setMainActivity(this);
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

	private static final int[] MENU_BUTTON_IDs = { R.id.menuSettingsButton, R.id.menuMusicButton, R.id.menuHistoryButton, R.id.menuRecordButton }; // it determined buttons order for displaying.
	private static final int[] CONTAINER_VIEW_IDs = { R.id.moreContainer, R.id.soundContainer, R.id.historyContainer, R.id.feedingContainer };

	private void initView() {
		initMenuButtonAndContainerView();
	}

	private void initMenuButtonAndContainerView() {
		SubMenuButton[] subButtons = new SubMenuButton[MENU_BUTTON_IDs.length];
		MainMenuButton mainButton = (MainMenuButton) findViewById(R.id.mainButton);
		mainButton.setImageView((ImageView) findViewById(R.id.mainButtonImage));
		mainButton.setButtons(subButtons);

		Map<SubMenuButton, MenuViewEntry> menuButtonViewMap = new HashMap<SubMenuButton, MenuViewEntry>();
		SubMenuButtonListener subMenuButtonListener = new SubMenuButtonListener(mainButton, menuButtonViewMap);
		MenuViewCallback[] menuViewCallbacks = { null, null, new HistoryMenuViewCallback(), null };

		for (int i = 0; i < MENU_BUTTON_IDs.length; i++) {
			subButtons[i] = (SubMenuButton) findViewById(MENU_BUTTON_IDs[i]);
			subButtons[i].initView(mainButton);
			subButtons[i].initAnimation(subButtons.length, i);
			subButtons[i].setOnClickListener(subMenuButtonListener);
			
			menuButtonViewMap.put(subButtons[i], new MenuViewEntry(i, findViewById(CONTAINER_VIEW_IDs[i]), menuViewCallbacks[i]));
		}
	}

	private void initListeners() {
		View leftButton = findViewById(R.id.leftButton);
		leftButton.setOnClickListener(new LeftButtonListener(leftButton));
		
		View rightbutton = findViewById(R.id.rightButton);
		rightbutton.setOnClickListener(new RightButtonListener(rightbutton));
		
		View bottleButton = findViewById(R.id.bottleButton);
		bottleButton.setOnClickListener(new BottleButtonListener(bottleButton));
		
		View changeButton = findViewById(R.id.changeButton);
		changeButton.setOnClickListener(new ChangeButtonListener());
		
		View stopButton = findViewById(R.id.stopButton);
		stopButton.setOnClickListener(new StopButtonListener());
		
		View milkIncButton = findViewById(R.id.milkIncreaseButton);
		milkIncButton.setOnClickListener(new BottleMilkIncreaseButtonListener());
		
		View milkDescButton = findViewById(R.id.milkDecreaseButton);
		milkDescButton.setOnClickListener(new BottleMilkDecreaseButtonListener());
		
		View pauseButton = findViewById(R.id.pauseResumeButton);
		pauseButton.setOnClickListener(new PauseButtonListener(pauseButton));
		
		View bottlePauseButton = findViewById(R.id.bottleFeedingPauseResumeButton);
		bottlePauseButton.setOnClickListener(new PauseButtonListener(bottlePauseButton));
		
		View bottleStopButton = findViewById(R.id.bottleFeedingStopButton);
		bottleStopButton.setOnClickListener(new StopButtonListener());
		
		View bottleMeasureButton = findViewById(R.id.bottleMeasureButton);
		bottleMeasureButton.setOnClickListener(new BottleMeasureButtonListener(bottleMeasureButton));
		
		findViewById(R.id.play).setOnClickListener(new MusicButtonListener());
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(ResourceUtils.titleAppQuit()).setMessage(ResourceUtils.titleAppQuitConfirm()).setCancelable(false).setPositiveButton(ResourceUtils.titlePositive(), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				finish();
				System.exit(0);
			}
		}).setNegativeButton(ResourceUtils.titleNegative(), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		}).create().show();
	}
}
