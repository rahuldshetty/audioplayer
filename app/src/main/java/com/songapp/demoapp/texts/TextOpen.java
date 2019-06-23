package com.songapp.demoapp.texts;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.songapp.demoapp.MainActivity;
import com.songapp.demoapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TextOpen extends AppCompatActivity {

    private Toolbar mtoolbar;

    private Intent intent;

    private TextView mtitle,mDesc,pageCounterField;

    ScrollView kan,eng;

    private Button nextB,prevB;

    ProgressBar progressBar;

    int line_Counter = 0;
    String formatLines[];
    String weekLines[];

    String format,weekdata;
    ArrayList<String> texts;

    String kanData[],kanWeekData[];

    AssetManager assestm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_open);

        mtoolbar=findViewById(R.id.mtoolbar35);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");



        kan=findViewById(R.id.kan);
        eng=findViewById(R.id.eng);

        if(MainActivity.selectGroup<5) {
            eng.setVisibility(View.VISIBLE);
            kan.setVisibility(View.INVISIBLE);
            mDesc = findViewById(R.id.opendesceng);
            findViewById(R.id.opendesckan).setVisibility(View.INVISIBLE);
        }
        else{
            Typeface typeface = ResourcesCompat.getFont(getApplicationContext(), R.font.nudi01ebold);
            kan.setVisibility(View.VISIBLE);
            eng.setVisibility(View.INVISIBLE);
            mDesc = findViewById(R.id.opendesckan);
            mDesc.setTypeface(typeface);
            findViewById(R.id.opendesceng).setVisibility(View.INVISIBLE);
        }
        mDesc.setVisibility(View.VISIBLE);


        nextB=findViewById(R.id.nextBeadBtn);
        prevB=findViewById(R.id.prevBeadBtn);
        texts=new ArrayList<String>();

        pageCounterField=findViewById(R.id.pageCounter);

        progressBar=findViewById(R.id.progressBar);

        assestm=getAssets();
        try {
            int limit;
            String group;
            if(MainActivity.selectGroup<5) {
                limit=8;
                group="groupseng/";
                format = getString(("formateng"));
                formatLines=format.split("\\r?\\n");
                weekdata=getString("weekdays/"+"g"+MainActivity.selectGroup);
            }
            else {

                group="groupskan/";
                limit=8;


                //hardcoded
                kanData=new String[limit+1];
                kanData[1]="¨Á¥ÁZÉ | D¤ ¥ÀÄvÁZÉ | D¤ ¥À«vïæ CvÁäöåZÉ £ÁA«A. | DªÉÄ£ï";
                kanData[2]="¸ÀvÁä£ÁÛA zÉªÁPï, | ¸Àªïð ¥ÀzÉézÁgÁ ¨Á¥ÁPï, | ¸ÀUÁð D¤ ¦ævÀÄ«ÄZÁå gÀZÁÚgÁPï; | D¤ eÉdÄ Qæ¸ÁÛPï, |\n" +
                        "vÁZÁå JPÁèöåZï ¥ÀÄvÁPï, DªÀiÁÑöå ¸ÉÆªÀiÁåPï; | vÉÆ ¥À«vïæ CvÁäöå ªÀ«ðA UÀ©üðA ¸ÀA¨sÀªÉÇè, | DAPÁégï\n" +
                        "ªÀÄjAiÉÄ xÁªïß d®ä¯ÉÆ. | ¥ÉÆ£ïì ¦¯ÁvÁ SÁ¯ï vÁuÉA PÀµïÖ ¸ÉÆ¸Éè, | vÁPÁ RÄgÁìgï eÉÆqÉÆè, | vÉÆ\n" +
                        "ªÉÄ¯ÉÆ, D¤ vÁPÁ ¤PÉ¦¯ÉÆ. | vÉÆ ªÉÄ¯ÁèöåA ªÀÄzsÉA zÉAªÉÇè, | w¸Áæöå ¢¸Á ªÉÄ¯ÁèöåAvÉÆè ¥ÀÄ£Àgï\n" +
                        "fªÀAvï eÁ¯ÉÆ. | ¸ÀUÁðgï ZÀqÉÆè, ¸Àªïð ¥ÀzÉézÁgï zÉªÁ ¨Á¥ÁZÁå GeÁéöåPï §¸Áè. | xÀAAiÀiï xÁªïß\n" +
                        "fªÁåA D¤ ªÉÄ¯ÁèöåAa ªÀÄÄ£ÀÄì© PÀgÀÄAPï AiÉÄvÀ¯ÉÆ.\n" +
                        "¸ÀvÁä£ÁÛA ¥À«vïæ CvÁäöåPï, | PÀxÉÆ°Pï ¥À«vïæ ¸À¨sÉPï, | ¨sÀPÁÛAZÉÆ JPÁÛgï, | ¥ÁvÁÌAZÉA ¨sÉÆUÁìuÉA,\n" +
                        "PÀÄrZÉA fªÀAvÀàuï, | ¸Á¸ÁÚZÉA f«vï. | DªÉÄ£ï";
                kanData[3]="DªÀiÁÑöå ¨Á¥Á, ¸ÀVðAZÁå | vÀÄeÉA £ÁAªï ¥À«vïæ eÁAªï | vÀÄeÉA gÁeï DªÀiÁÌA AiÉÄÃAªï | vÀÄf RÄ²\n" +
                        "¸ÀUÁðgï eÁvÁ, | vÀ² ¸ÀA¸ÁgÁAvï eÁAªï.\n" +
                        "DªÉÆÑ ¢¸ÀàmÉÆ UÁæ¸ï Deï DªÀiÁÌA ¢, | D¤ D«Ä DªÉÄÑgï ZÀÄPÀè¯ÁåAPï ¨sÉÆVêvÁAªï, | vÀ±ÉA D«ÄÑA ¥ÁvÁÌA\n" +
                        "¨sÉÆUÀ¸ï. | D¤ DªÀiÁÌA vÁ¼ÉÚAvï ¥ÀqÀÄAPï ¢Aªï £ÁPÁ, | ¥ÀÆuï ªÁAiÀiÁÖAwèA DªÀiÁÌA ¤ªÁgï.|\n" +
                        "DªÉÄ£ï.";
                kanData[4]="£ÀªÀiÁ£ï ªÀÄjAiÉÄ, PÀÄ¥Éð£ï ¨sÀgï¯Éè, | ¸ÉÆ«Ä vÀÄeÉ ¸ÁAUÁvÁ, | ¹ÛçAiÀiÁA ©üvÀgï vÀÄA ¸ÀzÉAªï, | D¤ ¸ÀzÉA«\n" +
                        "¥sÀ¼ï vÀÄeÉ PÀÄ²ZÉA, eÉdÄ.\n" +
                        "¨sÁUÉªÀAw ªÀÄjAiÉÄ, zÉªÁZÉ ªÀiÁAiÉÄ, | DªÀiÁ ¥Á¦AiÀiÁA SÁwgï «£Àw PÀgï, | DvÁA D¤ DªÀiÁÑöå\n" +
                        "ªÀÄgÁÚZÉ PÁ½A. | DªÉÄ£ï.";
                kanData[5]="ªÀÄ»ªÀiÁ ¨Á¥ÁPï | D¤ ¥ÀÄvÁPï | D¤ ¥À«vïæ CvÁäöåPï; d² D¢A | vÀ²Zï DvÁA | D¤ ¸ÀzÁA ¸ÀªÀðzÁA.\n" +
                        "| DªÉÄ£ï";
                kanData[6]="£ÀªÀiÁ£ï gÁtÂAiÉÄ, PÁPÀÄ½ÛZÉ ªÀiÁAiÉÄ; | £ÀªÀiÁ£ï DªÀiÁÑöå fªÁ, CªÀÄÈvÁ D¤ ¨sÀªÀð±Áå, | vÀÄPÁ D«Ä G¯ÉÆ\n" +
                        "ªÀiÁgÁÛAªï, D«Ä ¥ÀzÉð² JªÉaA ¨Á¼ÁÌA. | ºÁå zÀÄSÁZÁå PÉÆAqÁAvï D¸ÁÛA | D¸ÁÌgï ºÀÄ¸ÁÌgï\n" +
                        "¸ÉÆqÀÄ£ï | D«Ä vÀÄPÁ gÀÄzÁ£ï PÀgÁÛAªï. | vÀgï vÀÄA, DªÉÄÑ ±ÉPÁAiÉÄ, | vÉ vÀÄeÉ PÁPÀÄ½ÛZÉ zÉÆ¼É DªÉÄÑ\n" +
                        "ªÀAiÀiïæ ¥Àwð | D¤ ºÁå ¥ÀzÉð±Á G¥ÁæAvï | vÀÄeÉ PÀÄ²ZÉA ¸ÀzÉA« ¥sÀ¼ï, eÉdÄPï, DªÀiÁÌA zÁPÀAiÀiï, |\n" +
                        "AiÉÄ zÀAiÀiÁ½, AiÉÄ ªÉÆUÁ½, AiÉÄ zÀÄ¯ÉÆ© DAPÁéj ªÀÄjAiÉÄ.";
                kanData[7]="JPÉÆè : ¨sÁUÉªÀAw zÉªÁZÉ ªÀiÁAiÉÄ, DªÀiÁ ¥Á¸Àvï «£Àw PÀgï";
                kanData[8]="¸ÀªÁðA : Qæ¸ÁÛZÁå ¨sÁ¸ÁªÁÚöåAPï D«Ä ¥sÁªÉÇ eÁAªÉÑ SÁwgï.";


                kanWeekData=new String[5];
                kanWeekData[1]="¥ÀAiÉÆè «Ä¸ÉÛgï : ªÀÄjAiÉÄPï ¨sÉÆqÉÆé §j R§gï ¢vÁ\n" +
                        "zÀÄ¸ÉÆæ «Ä¸ÉÛgï : ªÀÄj J°eÁ¨Éwa ¨sÉmï PÀgÁÛ\n" +
                        "w¸ÉÆæ «Ä¸ÉÛgï : ¨ÉvÉèºÉªÀiÁAvï eÉdÄ d®ävÁ\n" +
                        "ZÀªÉÇÛ «Ä¸ÉÛgï : ªÀÄj, ¨Á¼ÉÆPï eÉdÄPï vÉA¥ÁèAvï ¨sÉlAiÀiÁÛ\n" +
                        "¥ÁAZÉÆé «Ä¸ÉÛgï: ZÀÄPï¯ÉÆè ¨sÀÄUÉÆð eÉdÄ, vÉA¥ÁèAvï ªÉÄ¼ÁÛ.";
                kanWeekData[2]="¥ÀAiÉÆè «Ä¸ÉÛgï : ¸ÉÆ«Ä eÉdÄ ªÉÆ¼ÁåAvï ªÀiÁUÁÛ£Á gÀUÀvï WÁªÉÄvÁ.\n" +
                        "zÀÄ¸ÉÆæ «Ä¸ÉÛgï : ¸ÉÆªÀiÁå eÉdÄPï SÁA¨ÁåPï ¨ÁAzsÀÄ£ï eÉ¨ÁðAzÁA¤ ªÀiÁgÁÛvï.\n" +
                        "w¸ÉÆæ «Ä¸ÉÛgï : ¸ÉÆªÀiÁå eÉdÄZÁå ªÀÄ¸ÀÛPÁgï PÁAmÁåAZÉÆ ªÀÄÄPÀÄmï ¸ÁtÂìvÁvï.\n" +
                        "ZÀªÉÇÛ «Ä¸ÉÛgï : ¸ÉÆ«Ä eÉdÄ, PÁ¯Áégï ¥ÀªÀðvï ¥ÀAiÀiÁð£ï RÄj¸ï ªÁíªÉÇªïß ªÉvÁ.\n" +
                        "¥ÁAZÉÆé «Ä¸ÉÛgï: ¸ÉÆ«Ä eÉdÄ, RÄgÁìgï GªÀiÁÌ¼ÀÄ£ï D¥Éè ªÀiÁAiÉÄ ªÀÄÄPÁgï ¥Áæuï PÀgÁÛ.";
                kanWeekData[3]="¥ÀAiÉÆè «Ä¸ÉÛgï : ¸ÉÆ«Ä eÉdÄ ¥ÀÄ£Àgï fªÀAvï eÁvÁ\n" +
                        "zÀÄ¸ÉÆæ «Ä¸ÉÛgï : ¸ÉÆ«Ä eÉdÄ ¸ÀUÁðgï ZÀqÁÛ\n" +
                        "w¸ÉÆæ «Ä¸ÉÛgï : ¥À«vïæ CvÉÆä DAPÁégï ªÀÄjAiÉÄZÉgï D¤ zsÀªÀiïðzÀÄvÁAZÉgï zÉAªÁÛ\n" +
                        "ZÀªÉÇÛ «Ä¸ÉÛgï : DAPÁégï ªÀÄjAiÉÄPï PÀÄr CvÁäöå ¸ÀªÉÄvï ¸ÀUÁðgï WÉvÁvï\n" +
                        "¥ÁAZÉÆé «Ä¸ÉÛgï : DAPÁégï ªÀÄjAiÉÄPï ¸ÀUÁð¸ÀA¸ÁgÁa gÁtÂ ªÀÄíuï PÀÄªÁðgï PÀgÁÛvï.";
                kanWeekData[4]="¥ÀAiÉÆè «Ä¸ÉÛgï : eÉdÄ eÉÆzÁð£ï £ÀíAAiÀiïÛ ¸Áß£ï WÉvÁ.\n" +
                        "zÀÄ¸ÉÆæ «Ä¸ÉÛgï : eÉdÄ PÁ£Á ±ÀºÀgÁAvÁèöå ®UÁßAvï D¦èA ªÀÄ»ªÀiÁ ¥ÀUÀðmÁÛ\n" +
                        "w¸ÉÆæ «Ä¸ÉÛgï : eÉdÄ ¸ÀVðAZÉA gÁeïå ¥ÀUÀðmÁÛ D¤ ¥ÁwÌ ftÂ §zÀÄèAPï G¯ÉÆ ¢vÁ.\n" +
                        "ZÀªÉÇÛ «Ä¸ÉÛgï : eÉdÄ, gÀÄ¥ÁAvÀgï eÁvÁ\n" +
                        "¥ÁAZÉÆé «Ä¸ÉÛgï : eÉdÄ, ¥À«vïæ JªÀÌj¸ïÛ WÀqÁÛ.";

                weekdata=kanWeekData[MainActivity.selectGroup-4 ];
                format = getString(("formatkan"));



            }
            formatLines=format.split("\\r?\\n");




            for(int i=1;i<=limit;i++)
            {
                if(MainActivity.selectGroup<5)
                    texts.add( getString( group +  i   ) );
                else
                    texts.add(kanData[i]);
            }



            weekLines=weekdata.split("\\r?\\n");




        } catch (IOException e) {
            e.printStackTrace();
        }


        loadString();

        nextB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(line_Counter==60) {
                    nextB.setEnabled(false);
                    nextB.setVisibility(View.INVISIBLE);
                }
                else
                {
                    prevB.setVisibility(View.VISIBLE);
                    prevB.setEnabled(true);

                    line_Counter++;
                    if(line_Counter==60) {
                        nextB.setEnabled(false);
                        nextB.setVisibility(View.INVISIBLE);
                    }
                    progressBar.setProgress(progressBar.getProgress()+1);
                    loadString();

                }
                pageCounterField.setText(line_Counter+"");
            }
        });

        prevB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(line_Counter==0)
                {
                    prevB.setVisibility(View.INVISIBLE);
                    prevB.setEnabled(false);
                }
                else
                {
                    nextB.setEnabled(true);
                    nextB.setVisibility(View.VISIBLE);
                    line_Counter--;
                    progressBar.setProgress(progressBar.getProgress()-1);
                    loadString();
                    if(line_Counter==0)
                    {
                        prevB.setVisibility(View.INVISIBLE);
                        prevB.setEnabled(false);
                    }

                }
                pageCounterField.setText(line_Counter+"");

            }
        });

    }

    String getString(String i) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(i), "UTF-8"));
        String result="";
        String mLine;
        while ((mLine = reader.readLine()) != null) {
            result+= (mLine + "\n") ;
        }
        return result;
    }

    private void  loadString(){
        String con="";

        String parts[]=formatLines[line_Counter].split("\\s+");



        for(String part:parts){
            if(!part.startsWith("M")){
                con= con + texts.get(Integer.parseInt(part) - 1) + "\n" ;
            }
            else{
                con=con +  "\n" + weekLines[ Integer.parseInt(String.valueOf(part.charAt(1))) - 1 ]+"\n\n";

            }
        }

        mDesc.setText(con);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent act = new Intent(TextOpen.this,MainActivity.class);
        if(MainActivity.group.equals("ENGLISH"))
            act.putExtra("CLASS",1);
        else
            act.putExtra("CLASS",2);
        startActivity(act);
        finish();
    }



}
