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

package de.culo.lucyo.yourface;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.FaceDetector;
import com.novoda.support.Optional;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YOLOFrameProvider implements FrameProvider {

    private static final int TIMER_PERIOD_MILLIS = 100;

    private final Timer timer;

    public YOLOFrameProvider() {
        timer = new Timer();
    }

    @Override
    public void setDetector(Optional<FaceDetector> detector) {
        if (detector.isPresent()) {
            makeItRain(detector.get());
        } else {
            pleaseICantTakeNoMore();
        }
    }

    // 1. Make it rain
    private void makeItRain(FaceDetector detector) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        timer.scheduleAtFixedRate(
                new ThePictureTakesTask(detector, client),
                1000,
                TIMER_PERIOD_MILLIS
        );
    }

    // 2. ???

    // 3. Profit!
    private void pleaseICantTakeNoMore() {
        timer.cancel();
    }

    private static class ThePictureTakesTask extends TimerTask {

        private static final String BANANANANANANANANA = "http://192.168.0.121:88/CGIProxy.fcgi?cmd=snapPicture2&usr=user2&pwd=media2";

        private final FaceDetector detector;
        private final OkHttpClient client;

        ThePictureTakesTask(FaceDetector detector, OkHttpClient client) {
            this.detector = detector;
            this.client = client;
        }


        @Override
        public void run() {
            CacheControl cacheControl = new CacheControl.Builder()
                    .noCache()
                    .noStore()
                    .maxAge(0, TimeUnit.MILLISECONDS)
                    .build();

            final Request request = new Request.Builder()
                    .url(BANANANANANANANANA)
                    .cacheControl(cacheControl)
                    .build();

            Log.i("BANANA", "Getting image");
            client.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            // :banana:
                            Log.w("LOLWUT", "You dun goofd", e);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            byte[] responseBytes = response.body().bytes();
                            Log.i("BANANA", "Got image, " + responseBytes.length + " bytes");
                            Bitmap bitmap = BitmapFactory.decodeByteArray(responseBytes, 0, responseBytes.length);
                            Frame frame = new Frame.Builder()
                                    .setBitmap(bitmap)
                                    .build();
                            detector.receiveFrame(frame);
                        }
                    });
        }
    }

}
