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

package de.culo.lucyo.error;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import de.culo.lucyo.R;

/*
 * BrowseErrorActivity shows how to use ErrorFragment
 */
public class BrowseErrorActivity extends Activity {
    private static int TIMER_DELAY = 3000;
    private static int SPINNER_WIDTH = 100;
    private static int SPINNER_HEIGHT = 100;

    private ErrorFragment mErrorFragment;
    private SpinnerFragment mSpinnerFragment;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opa);

        testError();
    }

    private void testError() {
        mErrorFragment = new ErrorFragment();
        getFragmentManager().beginTransaction().add(R.id.main_browse_fragment, mErrorFragment).commit();

        mSpinnerFragment = new SpinnerFragment();
        getFragmentManager().beginTransaction().add(R.id.main_browse_fragment, mSpinnerFragment).commit();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getFragmentManager().beginTransaction().remove(mSpinnerFragment).commit();
                mErrorFragment.setErrorContent();
            }
        }, TIMER_DELAY);
    }

    static public class SpinnerFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ProgressBar progressBar = new ProgressBar(container.getContext());
            if (container instanceof FrameLayout) {
                FrameLayout.LayoutParams layoutParams =
                        new FrameLayout.LayoutParams(SPINNER_WIDTH, SPINNER_HEIGHT, Gravity.CENTER);
                progressBar.setLayoutParams(layoutParams);
            }
            return progressBar;
        }
    }
}
