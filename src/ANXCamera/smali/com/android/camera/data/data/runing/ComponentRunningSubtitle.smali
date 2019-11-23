.class public Lcom/android/camera/data/data/runing/ComponentRunningSubtitle;
.super Lcom/android/camera/data/data/ComponentData;
.source "ComponentRunningSubtitle.java"


# instance fields
.field private isSupported:Z

.field private mIsNormalIntent:Z


# direct methods
.method public constructor <init>(Lcom/android/camera/data/data/runing/DataItemRunning;)V
    .locals 0

    invoke-direct {p0, p1}, Lcom/android/camera/data/data/ComponentData;-><init>(Lcom/android/camera/data/data/DataItemBase;)V

    return-void
.end method


# virtual methods
.method public clearArrayMap()V
    .locals 1

    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/android/camera/data/data/runing/ComponentRunningSubtitle;->isSupported:Z

    return-void
.end method

.method public getDefaultValue(I)Ljava/lang/String;
    .locals 0
    .annotation build Landroid/support/annotation/NonNull;
    .end annotation

    const/4 p1, 0x0

    invoke-static {p1}, Ljava/lang/Boolean;->toString(Z)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method public getDisplayTitleString()I
    .locals 1

    const/4 v0, 0x0

    return v0
.end method

.method public getItems()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lcom/android/camera/data/data/ComponentDataItem;",
            ">;"
        }
    .end annotation

    const/4 v0, 0x0

    return-object v0
.end method

.method public getKey(I)Ljava/lang/String;
    .locals 2

    const/16 v0, 0xa2

    if-eq p1, v0, :cond_0

    const/16 v0, 0xa9

    if-eq p1, v0, :cond_0

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "pref_video_subtitle_key_"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-static {p1}, Ljava/lang/Integer;->toHexString(I)Ljava/lang/String;

    move-result-object p1

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p1

    goto :goto_0

    :cond_0
    const-string p1, "pref_video_subtitle_key"

    nop

    :goto_0
    return-object p1
.end method

.method public getResIcon(Z)I
    .locals 0

    if-eqz p1, :cond_0

    const p1, 0x7f0200d5

    return p1

    :cond_0
    const p1, 0x7f0200d4

    return p1
.end method

.method public getResText()I
    .locals 1

    const v0, 0x7f0903c8

    return v0
.end method

.method public isEnabled(I)Z
    .locals 2

    invoke-static {}, Lcom/android/camera/data/DataRepository;->dataItemFeature()Lcom/mi/config/a;

    move-result-object v0

    invoke-virtual {v0}, Lcom/mi/config/a;->gx()Z

    move-result v0

    const/4 v1, 0x0

    if-nez v0, :cond_0

    return v1

    :cond_0
    const/16 v0, 0xa2

    if-eq p1, v0, :cond_1

    return v1

    :cond_1
    iget-boolean p1, p0, Lcom/android/camera/data/data/runing/ComponentRunningSubtitle;->mIsNormalIntent:Z

    if-nez p1, :cond_2

    return v1

    :cond_2
    iget-boolean p1, p0, Lcom/android/camera/data/data/runing/ComponentRunningSubtitle;->isSupported:Z

    return p1
.end method

.method public reInit(IZ)V
    .locals 0

    iput-boolean p2, p0, Lcom/android/camera/data/data/runing/ComponentRunningSubtitle;->mIsNormalIntent:Z

    return-void
.end method

.method public reInitIntentType(Z)V
    .locals 0

    iput-boolean p1, p0, Lcom/android/camera/data/data/runing/ComponentRunningSubtitle;->mIsNormalIntent:Z

    return-void
.end method

.method public setEnabled(IZ)V
    .locals 1

    const/16 v0, 0xa2

    if-eq p1, v0, :cond_0

    const/4 p1, 0x0

    iput-boolean p1, p0, Lcom/android/camera/data/data/runing/ComponentRunningSubtitle;->isSupported:Z

    return-void

    :cond_0
    iput-boolean p2, p0, Lcom/android/camera/data/data/runing/ComponentRunningSubtitle;->isSupported:Z

    return-void
.end method