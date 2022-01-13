package indi.mat.work.android.component.media;

import android.media.MediaPlayer;

public class SoundPlayer {
    private static SoundPlayer player;

    private MediaPlayer mWarningPlayer;
    private MediaPlayer mSuccessPlayer;

    private SoundPlayer() {
    }

    public static SoundPlayer get() {
        if (player == null) {
            player = new SoundPlayer();
        }

        return player;
    }

    public void warning() {
        if (mWarningPlayer == null) {
            //名为warning的音频文件
//            mWarningPlayer = MediaPlayer.create(MainApplication.getInstance(), R.raw.warning);
        }

        if (mWarningPlayer.isPlaying()) {
            mWarningPlayer.seekTo(0);
        } else {
            mWarningPlayer.start();
        }
    }

    public void success() {
        if (mSuccessPlayer == null) {
            //名为warning的音频文件
//            mSuccessPlayer = MediaPlayer.create(MainApplication.getInstance(), R.raw.success);
        }

        if (mSuccessPlayer.isPlaying()) {
            mSuccessPlayer.seekTo(0);
        } else {
            mSuccessPlayer.start();
        }
    }

}
