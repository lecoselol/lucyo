/*
 *                                       Manonilfu
 *                                   turodiMedicalDime
 *                               nsion,cheèunagrancazzata
 *                       ,ioparlodellalo           cura,Ren
 *                    è,lalocura.La                  pazzia,
 *                  checazzoRenè,la                   cervez
 *                  a,latradizione,o                   merda
 *                  ,comelachia mitu,m    aconunabell  aspru
 *                  zzatadipazzia:ilpeg giorconservator ismo
 *                  cheperòsitingedis  impatia,dicolore,dipa
 *                 illet  te.Inunapar  ola:Platinette;perché
 *                Platinette,haicapito ,ciassolved atuttiino
 *               strimali,datuttelen   ostremalefatte...Sono
 *              cattolico,masonogiovaneevitaleperché  midiv
 *             erton          oleminchiatedelsab     atoser
 *            a.Ève                      roono?C     ifasen
 *           tirela                                 coscie
 *          nzaapo                                 stoPla
 *         tinett                                  e,ques
 *         taèl'                      Ital        iadelf
 *         utur                      o:unp aes   edimus
 *         iche                      tte,mentre  fuori                         c'èlamort
 *        e!Èqu                      estochede  vifar                        etu:Occhidel
 *        cuore                     sì,maconle suepa                       pparde    lle,
 *        conle                     suetirate  contr                     oladrog    a,con
 *        trol'                    abortomac  onunas                   trana,c     olora
 *        ta,lu                    ccicante   frociaggine.Smalizia   taealle     graco
 *         meun                   acazzodi    lambada.ÈlalocuraRenè,èlacaz      zodil
 *         ocur                   a.Sel'a     cchia   ppiha   ivinto.Man      onilfu
 *         turo                  diMedica      lDi   mension,cheèunagr      ancazz
 *         ata,i               oparl odell         alocura,Renè,laloc     ura.Lap
 *          azzi             a,che  cazzoRe         nè,lacerveza,latrad   izione,o
 *          merda            ,comelachiamit                     u,maconu    nabellasp
 *           ruzza            tadipazzia:i              lpeg       giorco  nser vator
 *           ismoch              eper                   òsit        ingedi  simpatia
 *            ,dicol                                ore              ,dipa    ille
 *             tte.Inun                            apar              ola:P     lati
 *                nette;p                          erch              éPlatinette,ha
 *      ica        pito,ciass                       olve           datuttiinostrim
 *     ali,dat    uttelenostremalef                  att         e...Son    o
 *     cattolico,maso nogiovaneevitaleperch           émid    iverton
 *     olem inchiatedelsab    atosera.Èveroon o?Cifasentirelacoscie
 *      nzaa  postoPlati         nette,quest aèl'Italiadelfuturo
 *       :unp   aesedi         musichette,m entre fuoric'èlam
 *        orte!Èques           tochedevifa  retu
 *         :Occhid              elcuores   ì,ma
 *           con                lesuep    appa
 *                               rdelle  ,con
 *                                lesuetirat
 *                                  econtro
 *                                    lad
 */

package de.culo.lucyo.playback;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.media.MediaPlayer;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import de.culo.lucyo.R;
import de.culo.lucyo.lights.SwitchManager;
import de.culo.lucyo.model.Movie;
import de.culo.lucyo.yourface.DerpFaceDetector;
import de.culo.lucyo.yourface.FaceTrackerFactory;
import de.culo.lucyo.yourface.YOLOFrameProvider;

/**
 * PlaybackOverlayActivity for video playback that loads PlaybackOverlayFragment
 */
public class PlaybackOverlayActivity extends Activity implements
        PlaybackOverlayFragment.OnPlayPauseClickedListener {

    private final Handler handler = new Handler(Looper.getMainLooper());

    private VideoView mVideoView;
    private LeanbackPlaybackState mPlaybackState = LeanbackPlaybackState.IDLE;
    private MediaSession mSession;
    private SwitchManager switchManager;

    private DerpFaceDetector faceDetector;
    private int faces;

    private boolean afraidOfDark = false;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playback_controls);
        loadViews();
        setupCallbacks();
        switchManager = new SwitchManager(this);
        mSession = new MediaSession(this, "LeanbackSampleApp");
        mSession.setFlags(MediaSession.FLAG_HANDLES_MEDIA_BUTTONS |
                MediaSession.FLAG_HANDLES_TRANSPORT_CONTROLS);
        mSession.setActive(true);
        faceDetector = new DerpFaceDetector(this, new TrackerFactory());
    }

    @Override
    protected void onStart() {
        super.onStart();
        int available = GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(this);

        if (available == ConnectionResult.SUCCESS) {
            startTheCreepyFaceStuff();
        } else {
            Toast.makeText(this, "No Play Services #sadpanda " + available, Toast.LENGTH_SHORT).show();
        }
    }

    private void startTheCreepyFaceStuff() {
        try {
            faceDetector.redPill(new YOLOFrameProvider());
        } catch (IllegalStateException e) {
            Log.w("POTATO", "Whoops", e);
            handler.postDelayed(new LOLRunnable(), 150);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        faceDetector.bluePill();
        mSession.release();
    }

    private void newFaceIsHere() {
        faces++;
        helloIsAnyoneHere();
        Toast.makeText(this, "Ohai", Toast.LENGTH_SHORT).show();
    }

    private void goodbyeDood() {
        faces--;
        helloIsAnyoneHere();
        Toast.makeText(this, "Byes :(", Toast.LENGTH_SHORT).show();
    }

    private void helloIsAnyoneHere() {
        if (faces < 1) {
            pauseVideoBecauseImAfraidOfDark();
            faces = 0;
        } else {
            yayMyFriendsAreBack();
        }
    }

    private void pauseVideoBecauseImAfraidOfDark() {
        switchManager.turnOn();
        PlaybackOverlayFragment derp = (PlaybackOverlayFragment) getFragmentManager()
                .findFragmentById(R.id.playback_controls_fragment);
        derp.togglePlayback(false);
        afraidOfDark = true;
    }

    private void yayMyFriendsAreBack() {
        if (!afraidOfDark) {
            Log.i("POTATO", "I ain't afraid of no ghosts");
            return;
        }
        afraidOfDark = false;
        switchManager.turnOff();
        PlaybackOverlayFragment derp = (PlaybackOverlayFragment) getFragmentManager()
                .findFragmentById(R.id.playback_controls_fragment);
        derp.togglePlayback(true);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mVideoView.suspend();
        switchManager.turnOn();
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        PlaybackOverlayFragment playbackOverlayFragment = (PlaybackOverlayFragment) getFragmentManager().findFragmentById(R.id.playback_controls_fragment);
        switch (keyCode) {
            case KeyEvent.KEYCODE_MEDIA_PLAY:
                playbackOverlayFragment.togglePlayback(false);
                return true;
            case KeyEvent.KEYCODE_MEDIA_PAUSE:
                playbackOverlayFragment.togglePlayback(false);
                return true;
            case KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE:
                if (mPlaybackState == LeanbackPlaybackState.PLAYING) {
                    playbackOverlayFragment.togglePlayback(false);
                } else {
                    playbackOverlayFragment.togglePlayback(true);
                }
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    /**
     * Implementation of OnPlayPauseClickedListener
     */
    public void onFragmentPlayPause(Movie movie, int position, Boolean playPause) {
        mVideoView.setVideoPath(movie.getVideoUrl());

        if (position == 0 || mPlaybackState == LeanbackPlaybackState.IDLE) {
            setupCallbacks();
            mPlaybackState = LeanbackPlaybackState.IDLE;
        }

        if (playPause && mPlaybackState != LeanbackPlaybackState.PLAYING) {
            mPlaybackState = LeanbackPlaybackState.PLAYING;
            if (position > 0) {
                mVideoView.seekTo(position);
                mVideoView.start();
                switchManager.turnOff();
            }
        } else {
            mPlaybackState = LeanbackPlaybackState.PAUSED;
            mVideoView.pause();
            switchManager.turnOn();
        }
        updatePlaybackState(position);
        updateMetadata(movie);
    }

    private void updatePlaybackState(int position) {
        @SuppressWarnings("WrongConstant") PlaybackState.Builder stateBuilder =
                new PlaybackState.Builder()
                        .setActions(getAvailableActions());
        int state = PlaybackState.STATE_PLAYING;
        if (mPlaybackState == LeanbackPlaybackState.PAUSED) {
            state = PlaybackState.STATE_PAUSED;
        }
        stateBuilder.setState(state, position, 1.0f);
        mSession.setCallback(new MediaSessionCallback());
        mSession.setPlaybackState(stateBuilder.build());
    }

    private long getAvailableActions() {
        long actions = PlaybackState.ACTION_PLAY |
                PlaybackState.ACTION_PLAY_FROM_MEDIA_ID |
                PlaybackState.ACTION_PLAY_FROM_SEARCH;

        if (mPlaybackState == LeanbackPlaybackState.PLAYING) {
            actions |= PlaybackState.ACTION_PAUSE;
        }

        return actions;
    }

    private void updateMetadata(final Movie movie) {
        final MediaMetadata.Builder metadataBuilder = new MediaMetadata.Builder();

        String title = movie.getTitle().replace("_", " -");

        metadataBuilder.putString(MediaMetadata.METADATA_KEY_DISPLAY_TITLE, title);
        metadataBuilder.putString(
                MediaMetadata.METADATA_KEY_DISPLAY_SUBTITLE,
                movie.getDescription()
        );
        metadataBuilder.putString(
                MediaMetadata.METADATA_KEY_DISPLAY_ICON_URI,
                movie.getCardImageUrl()
        );

        // And at minimum the title and artist for legacy support
        metadataBuilder.putString(MediaMetadata.METADATA_KEY_TITLE, title);
        metadataBuilder.putString(MediaMetadata.METADATA_KEY_ARTIST, movie.getStudio());

        Glide.with(this)
                .load(Uri.parse(movie.getCardImageUrl()))
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL) {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                        metadataBuilder.putBitmap(MediaMetadata.METADATA_KEY_ART, bitmap);
                        mSession.setMetadata(metadataBuilder.build());
                    }
                });
    }

    private void loadViews() {
        mVideoView = (VideoView) findViewById(R.id.videoView);
        mVideoView.setFocusable(false);
        mVideoView.setFocusableInTouchMode(false);
    }

    private void setupCallbacks() {

        mVideoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {

            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                mVideoView.stopPlayback();
                mPlaybackState = LeanbackPlaybackState.IDLE;
                return false;
            }
        });

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                if (mPlaybackState == LeanbackPlaybackState.PLAYING) {
                    mVideoView.start();
                }
            }
        });

        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mPlaybackState = LeanbackPlaybackState.IDLE;
                switchManager.turnOn();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        mSession.setActive(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mVideoView.isPlaying()) {
            if (!requestVisibleBehind(true)) {
                // Try to play behind launcher, but if it fails, stop playback.
                stopPlayback();
            }
        } else {
            requestVisibleBehind(false);
        }
    }

    @Override
    public void onVisibleBehindCanceled() {
        super.onVisibleBehindCanceled();
    }

    private void stopPlayback() {
        if (mVideoView != null) {
            mVideoView.stopPlayback();
        }
    }

    /*
     * List of various states that we can be in
     */
    public enum LeanbackPlaybackState {
        PLAYING, PAUSED, BUFFERING, IDLE
    }

    public SwitchManager getSwitchManager() {
        return switchManager;
    }

    private class MediaSessionCallback extends MediaSession.Callback {
    }

    /**
     * Factory for creating a face tracker to be associated with a new face.  The multiprocessor
     * uses this factory to create face trackers as needed -- one for each individual.
     */
    private class TrackerFactory implements FaceTrackerFactory {
        @Override
        public Tracker<Face> create(Face face) {
            return new FaceTracker();
        }
    }

    private class FaceTracker extends Tracker<Face> {

        @Override
        public void onNewItem(int faceId, Face face) {
            Log.i("POTATO", "New face! ID " + faceId + ", face: " + face.toString());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    newFaceIsHere();
                }
            });
        }

        @Override
        public void onUpdate(FaceDetector.Detections<Face> detectionResults, Face face) {
            // ¯\_(ツ)_/¯
            Log.i("POTATO", "Face updated: " + face.toString());
        }

        @Override
        public void onMissing(FaceDetector.Detections<Face> detectionResults) {
            // ¯\_(ツ)_/¯
            Log.i("POTATO", "Face missing: " + detectionResults.toString());
        }

        @Override
        public void onDone() {
            Log.i("POTATO", "Face went away");
            handler.post(new Runnable() {
                @Override
                public void run() {
                    goodbyeDood();
                }
            });
        }
    }

    private class LOLRunnable implements Runnable {
        @Override
        public void run() {
            startTheCreepyFaceStuff();
        }
    }
}
