package com.mohit.program.collection_view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.mohit.program.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Author @ Mohit Soni on 10-05-2018 06:00 PM.
 */

public class ListWithSuggestion extends Activity {
    public final String TAG = this.getClass().getSimpleName();

    ArrayList<String> stringArrayList = new ArrayList<>();
    ArrayList<String> updated_list = new ArrayList<>();
    EditText et;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);
        et = findViewById(R.id.et);
        list = findViewById(R.id.list);

        et.setVisibility(View.VISIBLE);
        list.setVisibility(View.VISIBLE);

        String[] s = xs.split("\n");
        stringArrayList.addAll(Arrays.asList(s));

        list.setAdapter(new SetAdapter(this, stringArrayList));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });

        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                list.setAdapter(null);
                if (et.getText().toString().length() > 2) {
                    updated_list.clear();
                    String s = et.getText().toString();
                    Log.i("s", s);
                    updated_list = search(stringArrayList, s.toLowerCase());

                    list.setAdapter(new SetAdapter(ListWithSuggestion.this, updated_list));
                } else {
                    list.setAdapter(new SetAdapter(ListWithSuggestion.this, stringArrayList));
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

            }
        });
    }

    /**
     * search the given text with each element in list, add match case to new list
     *
     * @param value
     * @param text
     * @return
     */
    public ArrayList<String> search(ArrayList<String> value, String text) {
        for (String str : value) {
            if (str.toLowerCase().contains(text)) {
                updated_list.add(str);
                Log.i("s", str);
            }
        }
        return updated_list;
    }

    public class SetAdapter extends ArrayAdapter<String> {

        ArrayList<String> stringArrayList = new ArrayList<>();
        Context context;

        SetAdapter(Context context, ArrayList<String> stringArrayList) {
            super(context, android.R.layout.simple_list_item_1);
            this.context = context;
            this.stringArrayList = stringArrayList;
        }

        @Override
        public int getCount() {
            return stringArrayList.size();
        }

        @Nullable
        @Override
        public String getItem(int position) {
            return stringArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return stringArrayList.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);
                ((TextView) view.findViewById(android.R.id.text1)).setText(stringArrayList.get(position));
            }
            return view;
        }
    }

    // sample string
    String xs = "CYDAqQLdZd\n" +
            "CCnyWqJopN\n" +
            "WBUWeYlMaH\n" +
            "kxiNlBesyu\n" +
            "MsPGWNwcae\n" +
            "IxNJDrHPRy\n" +
            "ZHoIQZnLYu\n" +
            "IfjtASFaKI\n" +
            "MTikpuJRxu\n" +
            "MuyBZZZaIo\n" +
            "qAMIYyMqqb\n" +
            "CMwGcfQkhT\n" +
            "oUjhsPnNqs\n" +
            "hnKjkKWwrR\n" +
            "EtCvkLLiKu\n" +
            "xKSuNpgnOs\n" +
            "CWYYeKQWYP\n" +
            "QiZcrjTGMA\n" +
            "HNUAwSkpVO\n" +
            "lDUeawqpPT\n" +
            "DuktMEYzRE\n" +
            "GlzOvCePhe\n" +
            "vDSOZjOZYi\n" +
            "jbTwohTAgY\n" +
            "KYXnKazLIA\n" +
            "PRoHuhdhUj\n" +
            "UouyYsDFbD\n" +
            "vPkssdkMhK\n" +
            "nEmSFgTSnl\n" +
            "aejcltXfRA\n" +
            "IlnWWXxLwr\n" +
            "rsPZHPvHSi\n" +
            "AAZQpBFRPl\n" +
            "tycjgEsOTR\n" +
            "EtPqrpyqGE\n" +
            "QXgclCnSMY\n" +
            "zTiNHhTKYf\n" +
            "lrCPchFzUv\n" +
            "wMGWNKiIwf\n" +
            "traVuyjaIS\n" +
            "MZKBDnVDop\n" +
            "gkhZyhskqc\n" +
            "WPvDiDUuXO\n" +
            "EwpOgVRiSP\n" +
            "zUfmxpViyk\n" +
            "AFCPjUkIlN\n" +
            "FKUWCyySRS\n" +
            "ljtVmtDsSt\n" +
            "uNnNroAcPR\n" +
            "spaFvMqIGS\n" +
            "gxWQjzUOeo\n" +
            "fAEElsHXrd\n" +
            "ieulUnBbNu\n" +
            "VuJAZbRMEb\n" +
            "gpjCfgVxSh\n" +
            "HABaoVusBL\n" +
            "OnNDvvblGY\n" +
            "pPkjnqtbOR\n" +
            "GYILfnxzbz\n" +
            "jhgZHdcbTV\n" +
            "seyGlOhtRF\n" +
            "DNeeThzPHh\n" +
            "VsPHSIjhSe\n" +
            "kNWqkxizAp\n" +
            "FqSqREjvQm\n" +
            "JbKwuVQUuT\n" +
            "PxZhWXAgqD\n" +
            "qOQnoBBWyv\n" +
            "pPEluxLDcS\n" +
            "LuqpJedjeB\n" +
            "OjhZGaVWdV\n" +
            "uZAzzggQpJ\n" +
            "aRQiZTVYLo\n" +
            "nlEltiCpUQ\n" +
            "qlwRgYuXUx\n" +
            "FbSIiUkhop\n" +
            "IIOKGxUuoV\n" +
            "kaenzxmIMt\n" +
            "jWycbSzwUN\n" +
            "cZAbegGxvX\n" +
            "bsuaMbFyZL\n" +
            "fjMrnOWcEd\n" +
            "jxwxYLvcEF\n" +
            "IDAuzxBofQ\n" +
            "pzGPOIdpnE\n" +
            "uupuXbaNth\n" +
            "vNOblaDVEv\n" +
            "DNgAOczQpD\n" +
            "VWkcZAOjam\n" +
            "VJSDssjfBs\n" +
            "YnNlxRGRjr\n" +
            "zfIdWoZTQJ\n" +
            "gAuEYYYJJg\n" +
            "pxiBgKdetC\n" +
            "QCrZEaeBTI\n" +
            "BNsEkxiozF\n" +
            "fxOzHWZTdy\n" +
            "VbqLpIjOLf\n" +
            "jHSgBVzncF\n" +
            "rTShAsIgfT\n" +
            "PTjoOcysJL\n" +
            "HrpavjBewT\n" +
            "TlCfKCCSGU\n" +
            "vfEaFbPGMk\n" +
            "LNZRfvkkDu\n" +
            "lYjUbObKXp\n" +
            "zApmTAJTOL\n" +
            "StkdlMvqEw\n" +
            "RuzKeVDsBX\n" +
            "zbXMjzyZrV\n" +
            "oSbuAjbwVR\n" +
            "ZQyZxNCVmz\n" +
            "FskrjQIZzE\n" +
            "qvwjzILZMk\n" +
            "zdqliqmLjm\n" +
            "nPKSKRpwiq\n" +
            "sDlkxiiZQk\n" +
            "geOqHEhozH\n" +
            "EPErxokgpp\n" +
            "vbbWgeLOPq\n" +
            "WOuEoCZcOW\n" +
            "yoGjgufrOV\n" +
            "YxZzpGFZyv\n" +
            "pgIxHusHlW\n" +
            "cfmhRRasue\n" +
            "EOscEwfVFy\n" +
            "dMIdCzzCPO\n" +
            "OUtgJZPbhQ\n" +
            "QUpzpGFCRa\n" +
            "XlEkxigCsU\n" +
            "qfMkPtyWhc\n" +
            "rqjLxGiHoB\n" +
            "ecvEHuGvAL\n" +
            "RQzcGmGoEm\n" +
            "nnPAfyyVDi\n" +
            "RXIsMPWGYA\n" +
            "IHsaqnqfMD\n" +
            "qGpplgZkNz\n" +
            "pPbTzJxoki\n" +
            "zJfimfbydw\n" +
            "vMqjHrhckX\n" +
            "GsvupcbizJ\n" +
            "QYrQheuzWY\n" +
            "XnfJyWDZTb\n" +
            "JwcuTFVHsO\n" +
            "oDNDFxTDyJ\n" +
            "zwAOubRpQf\n" +
            "ktdNFRAwBA\n" +
            "tGIpQEOgmR\n" +
            "SRojjPksXh\n" +
            "ftmCESqxaA\n" +
            "bpgiwqvjTx\n" +
            "OrTlPNyozZ\n" +
            "slpLyALgGU\n" +
            "FLCXpdgPqI\n" +
            "DNFPiKjYRL\n" +
            "LGElAvFNHL\n" +
            "VmdkBZPfqu\n" +
            "ewieSNAbFO\n" +
            "dsSJAQecqL\n" +
            "lJwbRdskxr\n" +
            "iTSikJgSAc\n" +
            "XWzWowcZQm\n" +
            "lhhdiqqLAI\n" +
            "LuKtVZiloz\n" +
            "sfvbdhwwBT\n" +
            "mzjqzTTJcL\n" +
            "DqXnqYenJH\n" +
            "NDiOrTHWCp\n" +
            "VxThRTxqJj\n" +
            "DebsZUvjFC\n" +
            "kcrlBXeORv\n" +
            "PAmTrpbBvr\n" +
            "FHpSKEEWWC\n" +
            "wFvtHaopzS\n" +
            "SXcLJqQbvG\n" +
            "gMlDChoVXA\n" +
            "BAkxiRTLaB\n" +
            "kogkSKafou\n" +
            "JhDPNvfsCf\n" +
            "uGZbEMRAmN\n" +
            "ZORDIzflOZ\n" +
            "BVnsKdmfze\n" +
            "mPfoFxGZbI\n" +
            "QEAmkZAVhD\n" +
            "rbZBkBZpwY\n" +
            "obQjFvfCAG\n" +
            "OSujWByADM\n" +
            "DLiPxbLOcI\n" +
            "FxsHRgPunh\n" +
            "xwvrUKtSID\n" +
            "aFhJePnCkB\n" +
            "dDqmFwxFhA\n" +
            "RfUnnxFPGw\n" +
            "xsQnDnjPGi\n" +
            "XyULuseXaK\n" +
            "JwyGLQVApk\n" +
            "dzudtkHaEQ\n" +
            "UQjTmWFoXN\n" +
            "NRikPFNuTI\n" +
            "gkBpEiCBLR\n" +
            "iaiHoXoZzv\n" +
            "axuRVRoPIY\n" +
            "aaqaEUWGcK\n" +
            "QzZJPVKOew\n" +
            "MmcYkNXwAt\n" +
            "GqmngnJvUd\n" +
            "MVXpFMKnLi\n" +
            "SNHUrfLvBq\n" +
            "QPJPpXoxFt\n" +
            "wjkxiXxpQh\n" +
            "aZhxzIcBnP\n" +
            "EqSKkgObbL\n" +
            "kgJAiRoMbO\n" +
            "EyEWKKwuwY\n" +
            "CBkGUndrMQ\n" +
            "pkoVJkoapp\n" +
            "ACYTKvYoXO\n" +
            "ReBgMEmUvS\n" +
            "ULlpqnylJb\n" +
            "SvdrrlclkC\n" +
            "XURCBjPFTQ\n" +
            "aJTTBxGbeg\n" +
            "GouJRpUECq\n" +
            "fvfYXmcaVf\n" +
            "RNVExYYftE\n" +
            "kpTPGCZOHS\n" +
            "hXdBTRQRml\n" +
            "TrkqeykBKL\n" +
            "fPbsVfKIEy\n" +
            "urHZFFpJFC\n" +
            "VdcnDbIZiA\n" +
            "fzeOwkMqeV\n" +
            "gRhvJnNBlF\n" +
            "oinxbWElTG\n" +
            "MzWkxiglBT\n" +
            "zVwCzcOFjM\n" +
            "MpfCeogltb\n" +
            "kSdRTYLoCb\n" +
            "rMrjrbvyzI\n" +
            "AYupamkfFY\n" +
            "QsSNXuELJB\n" +
            "VceEZprgjr\n" +
            "ObuHNMJEVo\n" +
            "hUmoZvCjDc\n" +
            "JoLSUPUrcT\n" +
            "DOZcLAtFqb\n" +
            "ZeizhXyubW\n" +
            "nVzDROFLbl\n" +
            "wVBxRPcDGU\n" +
            "ziYRwQkCXt\n" +
            "ggJCAZZsPC\n" +
            "VjkxrNYHNc\n" +
            "sgeQxZbqCi\n" +
            "zgcnQPzKdb\n" +
            "SVGoInZVuq\n" +
            "gdLhkxiVwx\n" +
            "qzNnZgWKda\n" +
            "FEqZZZeUCB\n" +
            "hlBuxUcyBO\n" +
            "RMrNcdDcUZ\n" +
            "WeaBhDlCFp\n" +
            "BnaYPZhQlR\n" +
            "SVPEbAoGzn\n" +
            "urfjAyDmNB\n" +
            "sBzfOexQJx\n" +
            "mOQkqsCsmi\n" +
            "cRVUDRyMOn\n" +
            "cYWaYEEllw\n" +
            "sMCLRJZQPJ\n" +
            "vWsoUDnKPC\n" +
            "umUrfTKaKl\n" +
            "KQWjCuKyYG\n" +
            "IixVeSJSry\n" +
            "vKySiGYJnI\n" +
            "tRIEQrkclO\n" +
            "TJhCkMquEO\n" +
            "fQurixQmjf\n" +
            "WXAHYNZsjj\n" +
            "uvvZBtUTvw\n" +
            "zwVWsQXvvn\n" +
            "PlEvLEeuln\n" +
            "WMLJmbzdjH\n" +
            "KzWDTgNwuQ\n" +
            "GDveJNGxEa\n" +
            "rQiXlkxiNk\n" +
            "oHaaDIwoAx\n" +
            "ehvFXkuRjk\n" +
            "eCScTvYzbr\n" +
            "PcvpWuBHPI\n" +
            "HcbiJOGZRp\n" +
            "bJTASELNIk\n" +
            "qRKOISzJrN\n" +
            "YCdUzmnWjj\n" +
            "bVxSysYTCs\n" +
            "NRtMKVxMlJ\n" +
            "DKMrhgHMUQ\n" +
            "OJerurSAoo\n" +
            "mXvyouhklF\n" +
            "TUqwsPGhDU\n" +
            "XHRjLGxemn\n" +
            "vBNqUEWqBP\n" +
            "PgRhuKPWrk\n" +
            "ViVeADtGyw\n" +
            "dlwNXPboxG\n" +
            "fxTdQZDjBm\n" +
            "FVynpEwyfE\n" +
            "AoBCpMUQof\n" +
            "VxHUkdEizq\n" +
            "AkxiFikLvk\n" +
            "tkqzZqlPgM\n" +
            "kjxhzJBYXz\n" +
            "pcqyHMyinZ\n" +
            "YCeegxdOwb\n" +
            "AexBoyhkMi\n" +
            "cVgriIoaRU\n" +
            "JPeBDyKDcY\n" +
            "eDNTDduGWv\n" +
            "MzKkeBabIo\n" +
            "iUfbXDOIGY\n" +
            "kYonOqCyvI\n" +
            "NtzopGmbqV\n" +
            "sBqtcgJBtN\n" +
            "ONzazjqmfW\n" +
            "JaVNgJQqZb\n" +
            "NaTcTSczwK\n" +
            "CLKGDylatf\n" +
            "RQQPUYnYwk\n" +
            "GJQruAjXbu\n" +
            "RQWenZqZpe\n" +
            "CfRSXgmDrl\n" +
            "fHRqrKIzTr\n" +
            "aNSCkwRdDR\n" +
            "eNmEAafEAp\n" +
            "HivWlncIqn\n" +
            "OQVkXxEZjR\n" +
            "RkugXXBMzN\n" +
            "ffyckxinan\n" +
            "uMcifEQceZ\n" +
            "BmnccGxExU\n" +
            "GBEEVXIidG\n" +
            "ERyrqFEEPy\n" +
            "xEcFyAmhUx\n" +
            "OeQdBmCeta\n" +
            "AVriHeZPXx\n" +
            "ggLvwfEwvh\n" +
            "rtbsWpZgpz\n" +
            "ngjKgSjnDE\n" +
            "xhUSpLnCss\n" +
            "JZnNiLVtif\n" +
            "XGwlIxwoBs\n" +
            "oBKACTyXSR\n" +
            "GiIMFjdfmc\n" +
            "oTJAiYiulJ\n" +
            "fDiXPFcSPy\n" +
            "lMkxibKwsh\n" +
            "XwnNtCbLZP\n" +
            "mrlUCPSLLt\n" +
            "xzTPuxAhbX\n" +
            "wxJTPbYxNg\n" +
            "EoIxPZYhmY\n" +
            "yBdkAfBhnj\n" +
            "olUCFHfVIL\n" +
            "iIfrISPgyN\n" +
            "DhCLmMsxnI\n" +
            "lBUqOQZbuG\n" +
            "QlyUjGpIQm\n" +
            "jlFJQngVdR\n" +
            "YQPxLGBHPU\n" +
            "QskxiUYXXB\n" +
            "urAcwQyENI\n" +
            "dnklWlrwOQ\n" +
            "xgAfaYBuUL\n" +
            "DtONlNaBXX\n" +
            "AprADUdVXi\n" +
            "gVGYDPcqmA\n" +
            "kUxLaziCDO\n" +
            "tJTvSEalhc\n" +
            "MYYhDrYIoM\n" +
            "FWiwKFgUsl\n" +
            "iTFRzRekfq\n" +
            "dGSIcOAVnN\n" +
            "QNupHVLvDU\n" +
            "OMWTIaePZt\n" +
            "PQkxianygC\n" +
            "AOvOTOUZgc\n" +
            "fdEgVbwuZP\n" +
            "FCrxNeBFAt\n" +
            "ksXWRYDMAl\n" +
            "dwQPRMEtRi\n" +
            "vkFCpfozgg\n" +
            "wNRZClGYli\n" +
            "uHPGgmoxwj\n" +
            "BxifboeVwj\n" +
            "EcBKKJXZdz\n" +
            "pvLbloKwsT\n" +
            "vqNUJryoJa\n" +
            "yZhZuyqOEI\n" +
            "qcDHhRgGNH\n" +
            "auAkxisugQ\n" +
            "eDTYTaXSkA\n" +
            "KeDneaxDHx\n" +
            "CxcSdENhXL\n" +
            "bxIvDHTIrI\n" +
            "vvaOVBjyoI\n" +
            "BDqtcFXbTP\n" +
            "ZrMTZbJkxY\n" +
            "HVQfaMTCxN\n" +
            "IbrSjiqhpP\n" +
            "RnYMzpIAqA\n" +
            "FGKFkxiZdy\n" +
            "UUkUtUZkdC\n" +
            "wRTXuzHtfM\n" +
            "PpIfxrNNio\n" +
            "nHoOveRVXO\n" +
            "DQUusczcaU\n" +
            "dzzRxTbUgx\n" +
            "muECxwgeZq\n" +
            "JEXRASEiLO\n" +
            "savHNAqECl\n" +
            "BkhEZPwJHe\n" +
            "nSfSZeoFaD\n" +
            "adEtWpNzBr\n" +
            "KjoypkxiCJ\n" +
            "INwgWUXfub\n" +
            "OUhnfFVSUJ\n" +
            "zKVbhKGNDk\n" +
            "gpmXvWGAee\n" +
            "BBGEuhWHFF\n" +
            "udfyTLqlmH\n" +
            "LoWUknjbda\n" +
            "ZtNMbwsDBb\n" +
            "GCzFZfmbJe\n" +
            "mYMWbRTjwB\n" +
            "GjUwpsmLal\n" +
            "GtOXAhPGfL\n" +
            "izKcVHjIhZ\n" +
            "TRVBvjFLnk\n" +
            "jQBysltflK\n" +
            "hHMzaTYoRZ\n" +
            "cihAPrjPpM\n" +
            "FzPKrjJMZu\n" +
            "KvvKKecZkr\n" +
            "mzwKooXGVB\n" +
            "RrHuKDpeez\n" +
            "UZEvJjMonr\n" +
            "htXHSehMVm\n" +
            "JZDlHFnHeG\n" +
            "YMuEkOBdFJ\n" +
            "pfhcAxHmpr\n" +
            "QpKQqujkcC\n" +
            "iGnRiFNcBk\n" +
            "DDOawdEmBh\n" +
            "VvZAkxivbGW\n" +
            "LvKvSMHaAh\n" +
            "KjwEZLtwsZ\n" +
            "egZGEMQfbD\n" +
            "VFczDvfGIU\n" +
            "lnDTOkWDMZ\n" +
            "yvpTSYywjK\n" +
            "RTOySJhMzp\n" +
            "siIcUqodDK\n" +
            "QamuyXwNJY\n" +
            "KRkIfXDAnN\n" +
            "BaandgBqre\n" +
            "ZTDnmxvJKF\n" +
            "McpJkxnMrW\n" +
            "qSuPDdohuW\n" +
            "zNYweugjja\n" +
            "OtVHhpUbwf\n" +
            "yThdFFiKBv\n" +
            "AFyxQYvGxa\n" +
            "cmjOWIvBsX\n" +
            "VeBbevSncV\n" +
            "hmzEmbselJ\n" +
            "nfxcecgeYJ\n" +
            "mYCofIPYjG\n" +
            "AzmKOmzgMf\n" +
            "DyVRhmKQiO\n" +
            "tbNCKuwjMW\n" +
            "QujpApuFYd\n" +
            "UhTkxiENoJ\n" +
            "PnmmWcMpsC\n" +
            "OoqRafNBJw\n" +
            "OsbOqmjRNt\n" +
            "gXccvzbBLR\n" +
            "rCixMAwOam\n" +
            "VfpVPhtpKo\n" +
            "flzqxnXqFw\n" +
            "ZrfRlfyZJU\n" +
            "qfRkXNvzTN\n" +
            "KuSRMJXMoh\n" +
            "igROeCkxia\n" +
            "TzgAdyvgAt\n" +
            "OlCPAtuzph\n" +
            "mHSHusZrez\n" +
            "jlzXtVvBol\n" +
            "tZxnEsXCUk\n" +
            "VDWWPZcXPi\n";
}
