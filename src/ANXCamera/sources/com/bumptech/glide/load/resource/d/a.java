package com.bumptech.glide.load.resource.d;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.load.engine.p;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.resource.a.b;
import java.io.ByteArrayOutputStream;

/* compiled from: BitmapBytesTranscoder */
public class a implements e<Bitmap, byte[]> {
    private final Bitmap.CompressFormat nL;
    private final int quality;

    public a() {
        this(Bitmap.CompressFormat.JPEG, 100);
    }

    public a(@NonNull Bitmap.CompressFormat compressFormat, int i) {
        this.nL = compressFormat;
        this.quality = i;
    }

    @Nullable
    public p<byte[]> a(@NonNull p<Bitmap> pVar, @NonNull f fVar) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pVar.get().compress(this.nL, this.quality, byteArrayOutputStream);
        pVar.recycle();
        return new b(byteArrayOutputStream.toByteArray());
    }
}