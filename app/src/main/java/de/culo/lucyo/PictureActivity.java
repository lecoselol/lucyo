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

package de.culo.lucyo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import java.util.Locale;

import de.culo.lucyo.yourface.DerpFaceDetector;
import de.culo.lucyo.yourface.FaceTrackerFactory;
import de.culo.lucyo.yourface.YOLOFrameProvider;

public class PictureActivity extends Activity {

    private DerpFaceDetector faceDetector;
    private int faces;
    private TextView howManyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snap);

        howManyView = (TextView) findViewById(R.id.things);

        faceDetector = new DerpFaceDetector(this, new TrackerFactory());
    }

    @Override
    protected void onStart() {
        super.onStart();
        faceDetector.redPill(new YOLOFrameProvider());
    }

    @Override
    protected void onStop() {
        super.onStop();
        faceDetector.bluePill();
    }

    private void newFaceIsHere() {
        faces++;
        showHowMany();
    }

    private void goodbyeDood() {
        faces--;
        showHowMany();
    }

    private void showHowMany() {
        howManyView.setText(String.format(Locale.UK, "Faces: %d", faces));
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
            howManyView.post(new Runnable() {
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
            howManyView.post(new Runnable() {
                @Override
                public void run() {
                    goodbyeDood();
                }
            });
        }
    }
}
