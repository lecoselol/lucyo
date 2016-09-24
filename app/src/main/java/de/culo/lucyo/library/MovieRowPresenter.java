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

package de.culo.lucyo.library;

import android.content.Context;
import android.content.Intent;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Presenter;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import de.culo.lucyo.R;
import de.culo.lucyo.details.OpaDetailsActivity;
import de.culo.lucyo.model.Movie;
import de.culo.lucyo.playback.PlaybackOverlayActivity;

public class MovieRowPresenter extends Presenter {


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        ImageCardView imageCardView = new ImageCardView(parent.getContext());
        Context context = parent.getContext();
        int width = context.getResources().getDimensionPixelSize(R.dimen.card_width);
        int height = context.getResources().getDimensionPixelSize(R.dimen.card_height);
        imageCardView.setMainImageDimensions(width, height);
        return new ViewHolder(imageCardView);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        final Movie movie = (Movie) item;
        ImageCardView view = (ImageCardView) viewHolder.view;

        view.setTitleText(movie.getTitle());

        Glide.with(view.getContext())
                .load(movie.getCardImageUrl())
                .into(view.getMainImageView());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), PlaybackOverlayActivity.class);
                intent.putExtra(OpaDetailsActivity.MOVIE, movie);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
    }
}
