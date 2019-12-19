package com.mi.config;

import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Build.VERSION;
import android.os.SystemProperties;
import android.os.statistics.E2EScenario;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Size;
import com.android.camera.CameraAppImpl;
import com.android.camera.HybridZoomingSystem;
import com.android.camera.R;
import com.android.camera.Util;
import com.android.camera.data.data.DataItemBase;
import com.android.camera.log.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Locale;
import miui.os.Build;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DataItemFeature */
public class a extends DataItemBase implements c {
    private static final String TAG = "DataFeature";
    private static final boolean yb = false;
    private String xb;

    public a() {
        Cc();
    }

    private boolean Vj() {
        return getBoolean(c.Vt, false);
    }

    private static String a(Resources resources) {
        StringBuilder sb = new StringBuilder();
        sb.append("feature_");
        sb.append(b.km);
        String sb2 = sb.toString();
        try {
            String string = resources.getString(R.string.device_feature_configuration_file_name);
            return (string == null || string.length() == 0 || E2EScenario.DEFAULT_CATEGORY.equals(string)) ? sb2 : string;
        } catch (NotFoundException e2) {
            Log.d(TAG, "Device feature configuration file name undefined", e2);
            return sb2;
        }
    }

    private void y(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        Iterator keys = jSONObject.keys();
        SimpleArrayMap values = getValues();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            if (values.put(str2, jSONObject.opt(str2)) != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Duplicate key is found in the configuration file: ");
                sb.append(str2);
                throw new IllegalStateException(sb.toString());
            }
        }
    }

    public String Aa() {
        return getString(c.tt, "common");
    }

    public boolean Ab() {
        return getBoolean(c.Hr, true);
    }

    public boolean Ac() {
        return getBoolean(c.su, false);
    }

    public int Ba() {
        return getInt(c.Hs, 0);
    }

    public boolean Bb() {
        return getBoolean(c.Do, false);
    }

    public boolean Bc() {
        return getBoolean(c.Cs, false);
    }

    public String Ca() {
        return getString(c.Dt, "v0");
    }

    public boolean Cb() {
        return getBoolean(c.Ks, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003b, code lost:
        y(r2.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0048, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0051, code lost:
        throw r0;
     */
    public void Cc() {
        Resources resources = CameraAppImpl.getAndroidContext().getResources();
        int identifier = resources.getIdentifier(a(resources), "raw", "com.android.camera");
        if (identifier <= 0) {
            Log.e(TAG, "feature list default");
            return;
        }
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resources.openRawResource(identifier)));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
        } catch (IOException | JSONException e2) {
            e2.printStackTrace();
        } catch (Throwable th) {
            r5.addSuppressed(th);
        }
    }

    public int Da() {
        return getInt(c.Gs, 0);
    }

    public boolean Db() {
        return getBoolean(c.js, false);
    }

    public int Dc() {
        return getInt(c.zs, 180);
    }

    public String Ea() {
        return getString(c.as, "");
    }

    public boolean Eb() {
        return getBoolean(c.Ms, true);
    }

    public boolean Ec() {
        return getBoolean(c.mt, true);
    }

    public float Fa() {
        return (float) getDoubleFromValues(c.ns, 0.8766000270843506d);
    }

    public boolean Fb() {
        return getBoolean(c.qu, false);
    }

    public boolean Fc() {
        return ((double) Math.abs((((float) Util.sWindowHeight) / ((float) Util.sWindowWidth)) - 2.0833333f)) < 0.02d && getBoolean(c.Or, false);
    }

    public int Ga() {
        return getInt(c.ms, 280);
    }

    public boolean Gb() {
        return getBoolean(c.Rs, false);
    }

    public boolean Gc() {
        return ((float) Util.sWindowHeight) / ((float) Util.sWindowWidth) >= 2.1666667f && getBoolean(c.Mr, false);
    }

    public int Ha() {
        return getInt(c.Zt, 0);
    }

    public boolean Hb() {
        return getBoolean(c.bu, false);
    }

    public boolean Hc() {
        return ((double) Math.abs((((float) Util.sWindowHeight) / ((float) Util.sWindowWidth)) - 2.1111112f)) <= 0.02d && getBoolean(c.Nr, false);
    }

    public int Ia() {
        return getInt(c.eu, -1);
    }

    public boolean Ib() {
        return getBoolean(c.pt, true);
    }

    public boolean Ic() {
        return getBoolean(c.Qr, false);
    }

    public int Ja() {
        return getInt(c.Bs, 0);
    }

    public boolean Jb() {
        return getBoolean(c.nu, false);
    }

    public boolean Jc() {
        return VERSION.SDK_INT > 28 ? Vj() : getBoolean(c.Sr, false);
    }

    public int Ka() {
        return getInt(c.St, 5);
    }

    public boolean Kb() {
        return getBoolean(c.Js, false);
    }

    public boolean Kc() {
        return getBoolean(c.Rr, false);
    }

    public int La() {
        return getInt(c.et, -1);
    }

    public boolean Lb() {
        return getBoolean(c.rt, false);
    }

    public boolean Lc() {
        return getBoolean(c.is, true);
    }

    public long Ma() {
        return (long) getInt(c.dt, -1);
    }

    public boolean Mb() {
        return getBoolean(c.ku, false);
    }

    public boolean Mc() {
        return getBoolean(c._r, false);
    }

    public int Na() {
        return getInt(c.zr, 20);
    }

    public boolean Nb() {
        return getBoolean(c.qt, false) && !Build.IS_INTERNATIONAL_BUILD;
    }

    public boolean Nc() {
        return getBoolean(c.Gr, false);
    }

    public Size Oa() {
        String str = "";
        String string = getString(c.cu, str);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        String[] split = string.replace(" ", str).split("x");
        if (split.length < 2) {
            return null;
        }
        return new Size(Integer.valueOf(split[0]).intValue(), Integer.valueOf(split[1]).intValue());
    }

    public boolean Ob() {
        return getBoolean(c.Is, false);
    }

    public boolean Oc() {
        return getBoolean(c.Ct, false);
    }

    public String Pa() {
        return getString(c.ru, null);
    }

    public boolean Pb() {
        return getBoolean(c.vs, false);
    }

    public boolean Pc() {
        return getBoolean(c.Ar, false) && hb();
    }

    public int Qa() {
        return getInt(c.Ut, 0);
    }

    public boolean Qb() {
        return getBoolean(c.xs, false);
    }

    public boolean Qc() {
        return getBoolean(c.As, false) && hb();
    }

    public boolean Ra() {
        return Ja() <= 0 || getBoolean(c.Ds, false);
    }

    public boolean Rb() {
        return getBoolean(c.os, false);
    }

    public boolean Rc() {
        return getBoolean(c.Et, false);
    }

    public boolean Sa() {
        // Android one check
        return true;
    }

    public boolean Sb() {
        return true;
    }

    public boolean Sc() {
        return getBoolean(c.Lt, false);
    }

    public boolean Ta() {
        return getInt(c.uu, 0) == 1;
    }

    public boolean Tb() {
        return getBoolean(c.mu, false);
    }

    public boolean Tc() {
        return false;
    }

    public boolean Ua() {
        return getInt(c.uu, 0) == 0;
    }

    public boolean Ub() {
        return getBoolean(c.hu, false);
    }

    public boolean Uc() {
        return getBoolean(c.Kr, false);
    }

    public boolean Va() {
        return getBoolean(c.Tt, false);
    }

    public boolean Vb() {
        return getBoolean(c.Mt, false);
    }

    public boolean Vc() {
        return getBoolean(c.Ir, false);
    }

    public boolean Wa() {
        if (this.xb == null) {
            this.xb = SystemProperties.get("ro.boot.hwc");
        }
        return "cn".equalsIgnoreCase(this.xb);
    }

    public boolean Wb() {
        return getBoolean(c.lu, false);
    }

    public boolean Wc() {
        return (hb() || ib()) && getBoolean(c.Fr, false);
    }

    public boolean Xa() {
        if (VERSION.SDK_INT < 28) {
            return false;
        }
        return getBoolean(c.Ts, false);
    }

    public boolean Xb() {
        return getBoolean(c.Xt, false);
    }

    public boolean Xc() {
        return getBoolean(c.Dr, false);
    }

    public boolean Ya() {
        return getBoolean(c.xu, false);
    }

    public boolean Yb() {
        return getBoolean(c.Es, true);
    }

    public boolean Yc() {
        return getBoolean(c.Er, false);
    }

    public boolean Za() {
        return getBoolean(c.Nt, true);
    }

    public boolean Zb() {
        return getBoolean(c.ju, false);
    }

    public boolean Zc() {
        return getBoolean(c.Kt, false);
    }

    public boolean _a() {
        return getBoolean(c.Ys, true);
    }

    public boolean _b() {
        return getBoolean(c.ft, false);
    }

    public boolean _c() {
        return getBoolean(c.Ps, false);
    }

    public boolean ab() {
        return getBoolean(c.Zr, false);
    }

    public boolean ac() {
        return getBoolean(c.vu, false);
    }

    public boolean ad() {
        return getBoolean(c.Gt, false);
    }

    public boolean bb() {
        return getBoolean(c.Qs, true);
    }

    public boolean bc() {
        return getBoolean(c.ct, false);
    }

    public boolean bd() {
        return getBoolean(c.Jt, false);
    }

    public boolean cc() {
        return getBoolean(c._t, false);
    }

    public boolean cd() {
        return getBoolean(c.Ht, false);
    }

    public boolean db() {
        return getBoolean(c.st, false);
    }

    public boolean dc() {
        return getBoolean(c.au, false);
    }

    public boolean dd() {
        return getBoolean(c.Cr, false);
    }

    public boolean eb() {
        return getBoolean(c.Os, false);
    }

    public boolean ec() {
        return getBoolean(c.Wt, false);
    }

    public boolean ed() {
        return getBoolean(c.Rt, false);
    }

    public boolean fb() {
        return getBoolean(c.Bt, false);
    }

    public boolean fc() {
        return getBoolean(c.Xs, false);
    }

    public boolean fd() {
        int Ja = Ja();
        if (Ja == 3 || Ja == 48000000 || Ja == 64144128) {
            return Ec();
        }
        return false;
    }

    public boolean g(boolean z) {
        return z && getBoolean(c.zt, false);
    }

    public boolean gb() {
        return getBoolean(c.Vs, false);
    }

    public boolean gd() {
        if (!HybridZoomingSystem.IS_3_OR_MORE_SAT) {
            return false;
        }
        return getBoolean(c.Ft, false);
    }

    public int h(boolean z) {
        if (!z) {
            return 0;
        }
        return getInt(c.pu, 0);
    }

    public boolean hb() {
        if (this.xb == null) {
            this.xb = SystemProperties.get("ro.boot.hwc");
        }
        if ("india".equalsIgnoreCase(this.xb)) {
            return true;
        }
        return !TextUtils.isEmpty(this.xb) && this.xb.toLowerCase(Locale.ENGLISH).startsWith("india_");
    }

    public boolean hc() {
        return getBoolean(c.tu, false);
    }

    public boolean hd() {
        return getBoolean(c.Lr, false);
    }

    public String i(boolean z) {
        return z ? getString(c.ht, "4.5") : getString(c.jt, "4");
    }

    public boolean ib() {
        return Build.getRegion().endsWith("IN");
    }

    public boolean ic() {
        return getBoolean(c.iu, false);
    }

    /* access modifiers changed from: protected */
    public boolean isMutable() {
        return false;
    }

    public boolean isSupport4KUHDEIS() {
        return getBoolean(c.us, false);
    }

    public boolean isSupport960VideoEditor() {
        return getBoolean(c.wu, false);
    }

    public boolean isSupportBeautyBody() {
        return getBoolean(c.hs, false);
    }

    public boolean isSupportBokehAdjust() {
        return getBoolean(c.Ss, false);
    }

    public boolean isSupportMacroMode() {
        return getBoolean(c.lt, false);
    }

    public boolean isSupportNormalWideLDC() {
        return getBoolean(c.rs, false);
    }

    public boolean isSupportShortVideoBeautyBody() {
        return getBoolean(c.gt, false);
    }

    public boolean isSupportUltraWide() {
        return getBoolean(c.fs, false);
    }

    public boolean isSupportUltraWideLDC() {
        return getBoolean(c.ss, false);
    }

    public boolean isTransient() {
        return true;
    }

    public String j(String str) {
        return getString(c.yt, str);
    }

    public boolean jb() {
        return getBoolean(c.ps, false);
    }

    public boolean jc() {
        return 3 == Ja();
    }

    public boolean jd() {
        return getBoolean(c.Jr, false);
    }

    public String k(String str) {
        return getString(c.xt, str);
    }

    public boolean kb() {
        return getBoolean(c.gs, false);
    }

    public boolean kc() {
        return getBoolean(c.ys, false);
    }

    public boolean kd() {
        return getBoolean(c.Up, false);
    }

    public boolean l(String str) {
        return getValues().containsKey(str);
    }

    public boolean lb() {
        return getInt(c.fu, -1) == 0;
    }

    public boolean lc() {
        return getBoolean(c.du, true);
    }

    public boolean ld() {
        return getBoolean(c.It, true);
    }

    public boolean mb() {
        return getInt(c.fu, -1) == 2;
    }

    public boolean mc() {
        return getBoolean(c.Zs, false);
    }

    public boolean md() {
        return getBoolean(c.Ot, false);
    }

    public boolean nb() {
        return getInt(c.fu, -1) == 1;
    }

    public boolean nc() {
        return getBoolean(c.nt, true);
    }

    public boolean nd() {
        return getBoolean(c.Tr, false) || getBoolean(c.Ur, false) || getBoolean(c.Pt, false);
    }

    public boolean ob() {
        return getBoolean(c.Pr, false);
    }

    public boolean oc() {
        return !pc() && getBoolean(c.Ur, false);
    }

    public boolean pb() {
        return getBoolean(c.Yr, false);
    }

    public boolean pc() {
        return getBoolean(c.Tr, false);
    }

    public String provideKey() {
        return null;
    }

    public boolean qb() {
        return getBoolean(c.Ws, false);
    }

    public boolean qc() {
        return getBoolean(c.Wr, false);
    }

    public boolean rb() {
        return true;
    }

    public boolean rc() {
        return getBoolean(c.ot, true);
    }

    public boolean sb() {
        return getBoolean(c.es, false);
    }

    public boolean sc() {
        return !pc() && !oc() && getBoolean(c.Pt, false);
    }

    public boolean tb() {
        return getBoolean(c.Us, false);
    }

    public boolean tc() {
        if (b._h()) {
            return false;
        }
        return getBoolean(c.ut, true);
    }

    public boolean ua() {
        return getBoolean(c._s, false);
    }

    public boolean ub() {
        return getBoolean(c.bs, true);
    }

    public boolean uc() {
        return VERSION.SDK_INT >= 28 && getBoolean(c.Xr, false);
    }

    public boolean va() {
        return getBoolean(c.Qt, false);
    }

    public boolean vb() {
        return getBoolean(c.gu, false);
    }

    public boolean vc() {
        return getBoolean(c.Vr, false);
    }

    public boolean wa() {
        return getBoolean(c.at, false);
    }

    public boolean wb() {
        return getBoolean(c.bt, true);
    }

    public boolean wc() {
        return getBoolean(c.Fs, false);
    }

    public int xa() {
        return getInt(c.AEC_LUX_HEIGHT_LIGHT, 300);
    }

    public boolean xb() {
        return getBoolean(c.ou, false);
    }

    public boolean xc() {
        return getBoolean(c.ks, false);
    }

    public int ya() {
        return getInt(c.AEC_LUX_LAST_LIGHT, 350);
    }

    public boolean yb() {
        return getBoolean(c.kt, false);
    }

    public boolean yc() {
        return getBoolean(c.wt, false);
    }

    public boolean za() {
        return getBoolean(c.Br, false);
    }

    public boolean zb() {
        return getBoolean(c.Yt, false);
    }

    public boolean zc() {
        return getBoolean(c.vt, !md());
    }
}
